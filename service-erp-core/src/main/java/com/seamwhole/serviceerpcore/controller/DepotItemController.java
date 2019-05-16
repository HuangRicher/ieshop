package com.seamwhole.serviceerpcore.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seamwhole.serviceerpcore.constants.BusinessConstants;
import com.seamwhole.serviceerpcore.constants.ExceptionConstants;
import com.seamwhole.serviceerpcore.mapper.vo.*;
import com.seamwhole.serviceerpcore.model.*;
import com.seamwhole.serviceerpcore.exception.BusinessRunTimeException;
import com.seamwhole.serviceerpcore.service.DepotItemService;
import com.seamwhole.serviceerpcore.service.MaterialService;
import com.seamwhole.serviceerpcore.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.seamwhole.serviceerpcore.utils.ResponseJsonUtil.returnJson;


@RestController
@RequestMapping(value = "/depotItem")
public class DepotItemController {
    private Logger logger = LoggerFactory.getLogger(DepotItemController.class);

    @Resource
    private DepotItemService depotItemService;

    @Resource
    private MaterialService materialService;

    /**
     * 根据材料信息获取
     * @param materialParam  商品参数
     * @param depotIds  拥有的仓库信息
     * @return
     */
    @GetMapping(value = "/getHeaderIdByMaterial")
    public BaseResponseInfo getHeaderIdByMaterial(@RequestParam(value = "materialParam",defaultValue = "",required = false) String materialParam,
                                                  @RequestParam("depotIds") String depotIds)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        try {
            List<DepotItemVo4HeaderId> depotItemList = depotItemService.getHeaderIdByMaterial(materialParam, depotIds);
            String allReturn = "";
            if (depotItemList != null&&depotItemList.size()>0) {
                for (DepotItemVo4HeaderId d : depotItemList) {
                    Long dl = d.getHeaderid(); //获取对象
                    allReturn = allReturn + dl.toString() + ",";
                }
                /**
                 * 2019-01-17修复depotItemList集合为空时，程序异常
                 * */
                allReturn = allReturn.substring(0, allReturn.length() - 1);
            }
            if (allReturn.equals("null")) {
                allReturn = "";
            }
            res.code = 200;
            res.data = allReturn;
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }

    /**
     * 只根据商品id查询单据列表
     * @param mId
     * @param request
     * @return
     */
    @GetMapping(value = "/findDetailByTypeAndMaterialId/{pageSize}/{currentPage}/{materialId}")
    public String findDetailByTypeAndMaterialId(@PathVariable(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
                                                @PathVariable(value = Constants.CURRENT_PAGE, required = false) Integer currentPage,
                                                @PathVariable("materialId") String mId, HttpServletRequest request)throws Exception {
        Map<String, String> parameterMap = ParamUtils.requestToMap(request);
        parameterMap.put("mId", mId);
        PageQueryInfo queryInfo = new PageQueryInfo();
        Map<String, Object> objectMap = new HashMap<String, Object>();
        if (pageSize != null && pageSize <= 0) {
            pageSize = 10;
        }
        String offset = ParamUtils.getPageOffset(currentPage, pageSize);
        if (StringUtil.isNotEmpty(offset)) {
            parameterMap.put(Constants.OFFSET, offset);
        }
        List<DepotItemVo4DetailByTypeAndMId> list = depotItemService.findDetailByTypeAndMaterialIdList(parameterMap);
        JSONArray dataArray = new JSONArray();
        if (list != null) {
            for (DepotItemVo4DetailByTypeAndMId d: list) {
                JSONObject item = new JSONObject();
                item.put("Number", d.getNumber()); //商品编号
                item.put("Type", d.getNewtype()); //进出类型
                item.put("BasicNumber", d.getBnum()); //数量
                item.put("OperTime", d.getOtime()); //时间
                dataArray.add(item);
            }
        }
        objectMap.put("page", queryInfo);
        if (list == null) {
            queryInfo.setRows(new ArrayList<Object>());
            queryInfo.setTotal(BusinessConstants.DEFAULT_LIST_NULL_NUMBER);
            return returnJson(objectMap, "查找不到数据", ErpInfo.OK.code);
        }
        queryInfo.setRows(dataArray);
        queryInfo.setTotal(depotItemService.findDetailByTypeAndMaterialIdCounts(parameterMap));
        return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
    }

    /**
     * 根据商品id和仓库id查询库存数量
     * @param pageSize
     * @param currentPage
     * @param mId
     * @return
     */
    @GetMapping(value = "/findStockNumById/{pageSize}/{currentPage}/{projectId}/{materialId}/{monthTime}")
    public String findStockNumById(@PathVariable(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
                                   @PathVariable(value = Constants.CURRENT_PAGE, required = false) Integer currentPage,
                                   @PathVariable("projectId") Integer pid,
                                   @PathVariable("materialId") String mId,
                                   @PathVariable("monthTime") String monthTime) throws Exception{
        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("mId", mId);
        parameterMap.put("monthTime", monthTime);
        PageQueryInfo queryInfo = new PageQueryInfo();
        Map<String, Object> objectMap = new HashMap<String, Object>();
        if (pageSize != null && pageSize <= 0) {
            pageSize = 10;
        }
        String offset = ParamUtils.getPageOffset(currentPage, pageSize);
        if (StringUtil.isNotEmpty(offset)) {
            parameterMap.put(Constants.OFFSET, offset);
        }
        parameterMap.put(Constants.PAGE_SIZE, pageSize+"");
        parameterMap.put(Constants.CURRENT_PAGE, currentPage+"");
        List<DepotItemVo4Material> list = depotItemService.findStockNumByMaterialIdList(parameterMap);
        //存放数据json数组
        Long materialId = Long.parseLong(mId);
        JSONArray dataArray = new JSONArray();
        if (null != list) {
            for (DepotItemVo4Material di : list) {
                JSONObject item = new JSONObject();
                BigDecimal prevSum = sumNumber("入库", pid, materialId, monthTime, true).subtract(sumNumber("出库", pid, materialId, monthTime, true));
                BigDecimal InSum = sumNumber("入库", pid, materialId, monthTime, false);
                BigDecimal OutSum = sumNumber("出库", pid, materialId, monthTime, false);
                // +组装(组合件)-组装(普通子件)+拆卸(普通子件)-拆卸(组合件)
                BigDecimal prevAssembleSum = assembleNumber("组装单","组合件", pid, materialId, monthTime, true)
                                            .subtract(assembleNumber("组装单","普通子件", pid, materialId, monthTime, true))
                                            .add(assembleNumber("拆卸单","普通子件", pid, materialId, monthTime, true))
                                            .subtract(assembleNumber("拆卸单","组合件", pid, materialId, monthTime, true));
                BigDecimal notPrevAssembleSum = assembleNumber("组装单","组合件", pid, materialId, monthTime, false)
                                            .subtract(assembleNumber("组装单","普通子件", pid, materialId, monthTime, false))
                                            .add(assembleNumber("拆卸单","普通子件", pid, materialId, monthTime, false))
                                            .subtract(assembleNumber("拆卸单","组合件", pid, materialId, monthTime, false));
                item.put("MaterialId", di.getMaterialid() == null ? "" : di.getMaterialid());
                item.put("MaterialName", di.getMname());
                item.put("MaterialModel", di.getMmodel());
                item.put("thisSum", prevSum.add(InSum).subtract(OutSum).add(prevAssembleSum).add(notPrevAssembleSum));
                dataArray.add(item);
            }
        }
        objectMap.put("page", dataArray);
        if (list == null) {
            queryInfo.setRows(new ArrayList<Object>());
            queryInfo.setTotal(BusinessConstants.DEFAULT_LIST_NULL_NUMBER);
            return returnJson(objectMap, "查找不到数据", ErpInfo.OK.code);
        }
        queryInfo.setRows(list);
        queryInfo.setTotal(depotItemService.findStockNumByMaterialIdCounts(parameterMap));
        return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
    }

    /**
     * 只根据商品id查询库存数量
     * @param pageSize
     * @param currentPage
     * @param mId
     * @return
     */
    @GetMapping(value = "/findStockNumByMaterialId/{pageSize}/{currentPage}/{materialId}/{monthTime}")
    public String findStockNumByMaterialId(@PathVariable(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
                                           @PathVariable(value = Constants.CURRENT_PAGE, required = false) Integer currentPage,
                                           @PathVariable("materialId") String mId,
                                           @PathVariable("monthTime") String monthTime)  throws Exception{
        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("mId", mId);
        parameterMap.put("monthTime", monthTime);
        if(pageSize!=null){
            parameterMap.put(Constants.PAGE_SIZE,pageSize+"");
        }
        if(currentPage!=null){
            parameterMap.put(Constants.CURRENT_PAGE,currentPage+"");
        }
        PageQueryInfo queryInfo = new PageQueryInfo();
        Map<String, Object> objectMap = new HashMap<String, Object>();
        if (pageSize != null && pageSize <= 0) {
            pageSize = 10;
        }
        String offset = ParamUtils.getPageOffset(currentPage, pageSize);
        if (StringUtil.isNotEmpty(offset)) {
            parameterMap.put(Constants.OFFSET, offset);
        }
        List<DepotItemVo4Material> list = depotItemService.findStockNumByMaterialIdList(parameterMap);

        //存放数据json数组
        JSONArray dataArray = new JSONArray();
        if (null != list) {
            for (DepotItemVo4Material di : list) {
                JSONObject item = new JSONObject();
                int InSum = sumNumberByMaterialId("入库", di.getMaterialid());
                int OutSum = sumNumberByMaterialId("出库", di.getMaterialid());
                item.put("MaterialId", di.getMaterialid() == null ? "" : di.getMaterialid());
                item.put("MaterialName", di.getMname());
                item.put("MaterialModel", di.getMmodel());
                item.put("thisSum", InSum - OutSum);
                dataArray.add(item);
            }
        }
        objectMap.put("page", dataArray);
        if (list == null) {
            queryInfo.setRows(new ArrayList<Object>());
            queryInfo.setTotal(BusinessConstants.DEFAULT_LIST_NULL_NUMBER);
            return returnJson(objectMap, "查找不到数据", ErpInfo.OK.code);
        }
        queryInfo.setRows(list);
        queryInfo.setTotal(depotItemService.findStockNumByMaterialIdCounts(parameterMap));
        return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
    }

    /**
     * 仅根据商品Id进行数量合计
     *
     * @param type
     * @param mId
     * @return
     */
    public int sumNumberByMaterialId(String type, Long mId)throws Exception {
        int allNumber = 0;
        try {
            allNumber = depotItemService.findByTypeAndMaterialId(type, mId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allNumber;
    }

    /**
     * 保存明细
     * @param inserted
     * @param deleted
     * @param updated
     * @param headerId
     * @return
     */
    @PostMapping(value = "/saveDetails/{inserted}/{deleted}/{updated}/{headerId}")
    public String saveDetails(@PathVariable("inserted") String inserted,
                              @PathVariable("deleted") String deleted,
                              @PathVariable("updated") String updated,
                              @PathVariable("headerId") Long headerId) throws Exception{
        Map<String, Object> objectMap = new HashMap<String, Object>();
        try {
            depotItemService.saveDetials(inserted,deleted,updated,headerId);
            return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
        } catch (DataAccessException e) {
            e.printStackTrace();
            logger.error(">>>>>>>>>>>>>>>>>>>保存明细信息异常", e);
            return returnJson(objectMap, ErpInfo.ERROR.name, ErpInfo.ERROR.code);
        }
    }

    /**
     * 查询计量单位信息
     *
     * @return
     */
    public String findUnitName(Long mId)throws Exception {
        String unitName = "";
        try {
            unitName = materialService.findUnitName(mId);
            if (unitName != null) {
                unitName = unitName.substring(1, unitName.length() - 1);
                if (unitName.equals("null")) {
                    unitName = "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unitName;
    }

    @GetMapping(value = "/getDetailList")
    public BaseResponseInfo getDetailList(@RequestParam("headerId") Long headerId,
                                          @RequestParam("mpList") String mpList)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<DepotItemVo4WithInfoEx> dataList = new ArrayList<DepotItemVo4WithInfoEx>();
            if(headerId != 0) {
                    dataList = depotItemService.getDetailList(headerId);
            }
            String[] mpArr = mpList.split(",");
            JSONObject outer = new JSONObject();
            outer.put("total", dataList.size());
            //存放数据json数组
            JSONArray dataArray = new JSONArray();
            if (null != dataList) {
                for (DepotItemVo4WithInfoEx diEx : dataList) {
                    JSONObject item = new JSONObject();
                    item.put("Id", diEx.getId());
                    item.put("MaterialId", diEx.getMaterialid() == null ? "" : diEx.getMaterialid());
                    String ratio; //比例
                    if (diEx.getUnitId() == null || diEx.getUnitId().equals("")) {
                        ratio = "";
                    } else {
                        ratio = diEx.getUName();
                        ratio = ratio.substring(ratio.indexOf("("));
                    }
                    //品名/型号/扩展信息/包装
                    String MaterialName = diEx.getMName() + ((diEx.getMModel() == null || diEx.getMModel().equals("")) ? "" : "(" + diEx.getMModel() + ")");
                    String materialOther = getOtherInfo(mpArr, diEx);
                    MaterialName = MaterialName + materialOther + ((diEx.getUName() == null || diEx.getUName().equals("")) ? "" : "(" + diEx.getUName() + ")") + ratio;
                    item.put("MaterialName", MaterialName);
                    item.put("Unit", diEx.getMunit());
                    item.put("OperNumber", diEx.getOpernumber());
                    item.put("BasicNumber", diEx.getBasicnumber());
                    item.put("UnitPrice", diEx.getUnitprice());
                    item.put("TaxUnitPrice", diEx.getTaxunitprice());
                    item.put("AllPrice", diEx.getAllprice());
                    item.put("Remark", diEx.getRemark());
                    item.put("Img", diEx.getImg());
                    item.put("DepotId", diEx.getDepotid() == null ? "" : diEx.getDepotid());
                    item.put("DepotName", diEx.getDepotid() == null ? "" : diEx.getDepotName());
                    item.put("AnotherDepotId", diEx.getAnotherdepotid() == null ? "" : diEx.getAnotherdepotid());
                    item.put("AnotherDepotName", diEx.getAnotherdepotid() == null ? "" : diEx.getAnotherDepotName());
                    item.put("TaxRate", diEx.getTaxrate());
                    item.put("TaxMoney", diEx.getTaxmoney());
                    item.put("TaxLastMoney", diEx.getTaxlastmoney());
                    item.put("OtherField1", diEx.getOtherfield1());
                    item.put("OtherField2", diEx.getOtherfield2());
                    item.put("OtherField3", diEx.getOtherfield3());
                    item.put("OtherField4", diEx.getOtherfield4());
                    item.put("OtherField5", diEx.getOtherfield5());
                    item.put("MType", diEx.getMtype());
                    item.put("op", 1);
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            res.code = 200;
            res.data = outer;
        } catch (Exception e) {
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }


    /**
     * 获取扩展信息
     *
     * @return
     */
    public String getOtherInfo(String[] mpArr, DepotItemVo4WithInfoEx diEx)throws Exception {
        String materialOther = "";
        for (int i = 0; i < mpArr.length; i++) {
            if (mpArr[i].equals("颜色")) {
                materialOther = materialOther + ((diEx.getMColor() == null || diEx.getMColor().equals("")) ? "" : "(" + diEx.getMColor() + ")");
            }
            if (mpArr[i].equals("规格")) {
                materialOther = materialOther + ((diEx.getMStandard() == null || diEx.getMStandard().equals("")) ? "" : "(" + diEx.getMStandard() + ")");
            }
            if (mpArr[i].equals("制造商")) {
                materialOther = materialOther + ((diEx.getMMfrs() == null || diEx.getMMfrs().equals("")) ? "" : "(" + diEx.getMMfrs() + ")");
            }
            if (mpArr[i].equals("自定义1")) {
                materialOther = materialOther + ((diEx.getMOtherField1() == null || diEx.getMOtherField1().equals("")) ? "" : "(" + diEx.getMOtherField1() + ")");
            }
            if (mpArr[i].equals("自定义2")) {
                materialOther = materialOther + ((diEx.getMOtherField2() == null || diEx.getMOtherField2().equals("")) ? "" : "(" + diEx.getMOtherField2() + ")");
            }
            if (mpArr[i].equals("自定义3")) {
                materialOther = materialOther + ((diEx.getMOtherField3() == null || diEx.getMOtherField3().equals("")) ? "" : "(" + diEx.getMOtherField3() + ")");
            }
        }
        return materialOther;
    }

    /**
     * 查找所有的明细
     * @param currentPage
     * @param pageSize
     * @param projectId
     * @param monthTime
     * @param headIds
     * @param materialIds
     * @param mpList
     * @return
     */
    @GetMapping(value = "/findByAllByPage/{currentPage}/{pageSize}/{projectId}/{monthTime}/{headIds}/{materialIds}/{mpList}")
    public BaseResponseInfo findByAllByPage(@PathVariable("currentPage") Integer currentPage,
                                      @PathVariable("pageSize") Integer pageSize,
                                      @PathVariable("projectId") Integer projectId,
                                      @PathVariable("monthTime") String monthTime,
                                      @PathVariable("headIds") String headIds,
                                      @PathVariable("materialIds") String materialIds,
                                      @PathVariable("mpList") String mpList)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<DepotItemVo4WithInfoEx> dataList = depotItemService.findByAll(headIds, materialIds, (currentPage-1)*pageSize, pageSize);
            String[] mpArr = mpList.split(",");
            int total = depotItemService.findByAllCount(headIds, materialIds);
            map.put("total", total);
            //存放数据json数组
            Integer pid = projectId;
            JSONArray dataArray = new JSONArray();
            if (null != dataList) {
                for (DepotItemVo4WithInfoEx diEx : dataList) {
                    JSONObject item = new JSONObject();
                    BigDecimal prevSum = sumNumber("入库", pid, diEx.getMId(), monthTime, true).subtract(sumNumber("出库", pid, diEx.getMId(), monthTime, true));
                    BigDecimal InSum = sumNumber("入库", pid, diEx.getMId(), monthTime, false);
                    BigDecimal OutSum = sumNumber("出库", pid, diEx.getMId(), monthTime, false);
                    BigDecimal prevPrice = sumPrice("入库", pid, diEx.getMId(), monthTime, true).subtract(sumPrice("出库", pid, diEx.getMId(), monthTime, true));
                    BigDecimal InPrice = sumPrice("入库", pid, diEx.getMId(), monthTime, false);
                    BigDecimal OutPrice = sumPrice("出库", pid, diEx.getMId(), monthTime, false);
                    item.put("MaterialName", diEx.getMName());
                    item.put("MaterialModel", diEx.getMModel());
                    //扩展信息
                    String materialOther = getOtherInfo(mpArr, diEx);
                    item.put("MaterialOther", materialOther);
                    item.put("MaterialColor", diEx.getMColor());
                    item.put("MaterialUnit", diEx.getMaterialUnit());
                    BigDecimal unitPrice = BigDecimal.ZERO;
                    if ((prevSum .add(InSum).subtract(OutSum)).compareTo(BigDecimal.ZERO)!= 0) {
                        unitPrice = (prevPrice.add(InPrice).subtract(OutPrice)).divide(prevSum.add(InSum).subtract(OutSum),2, BigDecimal.ROUND_HALF_UP);
                        /**
                         * 2019-01-15通过除法算出金额后，保留两位小数
                         * */
                        DecimalFormat    df   = new DecimalFormat("#.00");
                        unitPrice= new BigDecimal(df.format(unitPrice));
                    }
                    item.put("UnitPrice", unitPrice);
                    item.put("prevSum", prevSum);
                    item.put("InSum", InSum);
                    item.put("OutSum", OutSum);
                    item.put("thisSum", prevSum.add(InSum).subtract(OutSum));
                    item.put("thisAllPrice", prevPrice.add(InPrice).subtract(OutPrice));
                    dataArray.add(item);
                }
            }
            map.put("rows", dataArray);
            res.code = 200;
            res.data = map;
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }

    /**
     * 统计总计金额
     * @param pid
     * @param monthTime
     * @param headIds
     * @param materialIds
     * @return
     */
    @GetMapping(value = "/totalCountMoney/{projectId}/{monthTime}/{headIds}/{materialIds}")
    public BaseResponseInfo totalCountMoney(@PathVariable("projectId") Integer pid,
                                            @PathVariable("monthTime") String monthTime,
                                            @PathVariable("headIds") String headIds,
                                            @PathVariable("materialIds") String materialIds) throws Exception{
        BaseResponseInfo res = new BaseResponseInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<DepotItemVo4WithInfoEx> dataList = depotItemService.findByAll(headIds, materialIds, null, null);
            BigDecimal thisAllPrice = BigDecimal.ZERO;
            if (null != dataList) {
                for (DepotItemVo4WithInfoEx diEx : dataList) {
                    BigDecimal prevPrice = sumPrice("入库", pid, diEx.getMId(), monthTime, true).subtract(sumPrice("出库", pid, diEx.getMId(), monthTime, true));
                    BigDecimal InPrice = sumPrice("入库", pid, diEx.getMId(), monthTime, false);
                    BigDecimal OutPrice = sumPrice("出库", pid, diEx.getMId(), monthTime, false);
                    thisAllPrice = thisAllPrice .add(prevPrice.add(InPrice).subtract(OutPrice));
                }
            }
            map.put("totalCount", thisAllPrice);
            res.code = 200;
            res.data = map;
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }

    /**
     * 进货统计
     * @param currentPage
     * @param pageSize
     * @param monthTime
     * @param headIds
     * @param materialIds
     * @param mpList
     * @return
     */
    @GetMapping(value = "/buyIn/{currentPage}/{pageSize}/{monthTime}/{headIds}/{materialIds}/{mpList}")
    public BaseResponseInfo buyIn(@PathVariable("currentPage") Integer currentPage,
                                  @PathVariable("pageSize") Integer pageSize,
                                  @PathVariable("monthTime") String monthTime,
                                  @PathVariable("headIds") String headIds,
                                  @PathVariable("materialIds") String materialIds,
                                  @PathVariable("mpList") String mpList)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<DepotItemVo4WithInfoEx> dataList = depotItemService.findByAll(headIds, materialIds, (currentPage-1)*pageSize, pageSize);
            String[] mpArr = mpList.split(",");
            int total = depotItemService.findByAllCount(headIds, materialIds);
            map.put("total", total);
            //存放数据json数组
            JSONArray dataArray = new JSONArray();
            if (null != dataList) {
                for (DepotItemVo4WithInfoEx diEx : dataList) {
                    JSONObject item = new JSONObject();
                    BigDecimal InSum = sumNumberBuyOrSale("入库", "采购", diEx.getMId(), monthTime);
                    BigDecimal OutSum = sumNumberBuyOrSale("出库", "采购退货", diEx.getMId(), monthTime);
                    BigDecimal InSumPrice = sumPriceBuyOrSale("入库", "采购", diEx.getMId(), monthTime);
                    BigDecimal OutSumPrice = sumPriceBuyOrSale("出库", "采购退货", diEx.getMId(), monthTime);
                    item.put("MaterialName", diEx.getMName());
                    item.put("MaterialModel", diEx.getMModel());
                    //扩展信息
                    String materialOther = getOtherInfo(mpArr, diEx);
                    item.put("MaterialOther", materialOther);
                    item.put("MaterialColor", diEx.getMColor());
                    item.put("MaterialUnit", diEx.getMaterialUnit());
                    item.put("InSum", InSum);
                    item.put("OutSum", OutSum);
                    item.put("InSumPrice", InSumPrice);
                    item.put("OutSumPrice", OutSumPrice);
                    dataArray.add(item);
                }
            }
            map.put("rows", dataArray);
            res.code = 200;
            res.data = map;
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }

    /**
     * 销售统计
     * @param currentPage
     * @param pageSize
     * @param monthTime
     * @param headIds
     * @param materialIds
     * @param mpList
     * @return
     */
    @GetMapping(value = "/saleOut/{currentPage}/{pageSize}/{monthTime}/{headIds}/{materialIds}/{mpList}")
    public BaseResponseInfo saleOut(@PathVariable("currentPage") Integer currentPage,
                                    @PathVariable("pageSize") Integer pageSize,
                                    @PathVariable("monthTime") String monthTime,
                                    @PathVariable("headIds") String headIds,
                                    @PathVariable("materialIds") String materialIds,
                                    @PathVariable("mpList") String mpList)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<DepotItemVo4WithInfoEx> dataList = depotItemService.findByAll(headIds, materialIds, (currentPage-1)*pageSize, pageSize);
            String[] mpArr = mpList.split(",");
            int total = depotItemService.findByAllCount(headIds, materialIds);
            map.put("total", total);
            //存放数据json数组
            JSONArray dataArray = new JSONArray();
            if (null != dataList) {
                for (DepotItemVo4WithInfoEx diEx : dataList) {
                    JSONObject item = new JSONObject();
                    BigDecimal OutSumRetail = sumNumberBuyOrSale("出库", "零售", diEx.getMId(), monthTime);
                    BigDecimal OutSum = sumNumberBuyOrSale("出库", "销售", diEx.getMId(), monthTime);
                    BigDecimal InSumRetail = sumNumberBuyOrSale("入库", "零售退货", diEx.getMId(), monthTime);
                    BigDecimal InSum = sumNumberBuyOrSale("入库", "销售退货", diEx.getMId(), monthTime);
                    BigDecimal OutSumRetailPrice = sumPriceBuyOrSale("出库", "零售", diEx.getMId(), monthTime);
                    BigDecimal OutSumPrice = sumPriceBuyOrSale("出库", "销售", diEx.getMId(), monthTime);
                    BigDecimal InSumRetailPrice = sumPriceBuyOrSale("入库", "零售退货", diEx.getMId(), monthTime);
                    BigDecimal InSumPrice = sumPriceBuyOrSale("入库", "销售退货", diEx.getMId(), monthTime);
                    item.put("MaterialName", diEx.getMName());
                    item.put("MaterialModel", diEx.getMModel());
                    //扩展信息
                    String materialOther = getOtherInfo(mpArr, diEx);
                    item.put("MaterialOther", materialOther);
                    item.put("MaterialColor", diEx.getMColor());
                    item.put("MaterialUnit", diEx.getMaterialUnit());
                    item.put("OutSum", OutSumRetail.add(OutSum));
                    item.put("InSum", InSumRetail.add(InSum));
                    item.put("OutSumPrice", OutSumRetailPrice.add(OutSumPrice));
                    item.put("InSumPrice", InSumRetailPrice.add(InSumPrice));
                    dataArray.add(item);
                }
            }
            map.put("rows", dataArray);
            res.code = 200;
            res.data = map;
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }

    @GetMapping(value = "/depotItem/findByAll/{headIds}/{materialIds}/{count}/{pageSize}")
    public List<DepotItemVo4WithInfoEx> findByAll(@PathVariable("headIds") String headIds,
                                                  @PathVariable("materialIds")String materialIds,
                                                  @PathVariable("count")Integer count,
                                                  @PathVariable("pageSize")Integer pageSize) throws Exception{

        return depotItemService.findByAll(headIds, materialIds, count, pageSize);
    }


    /**
     * 导出excel表格
     * @param currentPage
     * @param pageSize
     * @param projectId
     * @param monthTime
     * @param headIds
     * @param materialIds
     * @return
     */
    @GetMapping(value = "/exportExcel/{currentPage}/{pageSize}/{projectId}/{monthTime}/{headIds}/{materialIds}")
    public BaseResponseInfo exportExcel(@PathVariable("currentPage") Integer currentPage,
                                        @PathVariable("pageSize") Integer pageSize,
                                        @PathVariable("projectId") Integer projectId,
                                        @PathVariable("monthTime") String monthTime,
                                        @PathVariable("headIds") String headIds,
                                        @PathVariable("materialIds") String materialIds)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "成功";
        try {
            List<DepotItemVo4WithInfoEx> dataList = depotItemService.findByAll(headIds, materialIds, (currentPage-1)*pageSize, pageSize);
            //存放数据json数组
            Integer pid = projectId;
            String[] names = {"名称", "型号", "单位", "单价", "上月结存数量", "入库数量", "出库数量", "本月结存数量", "结存金额"};
            String title = "库存报表";
            List<String[]> objects = new ArrayList<String[]>();
            if (null != dataList) {
                for (DepotItemVo4WithInfoEx diEx : dataList) {
                    String[] objs = new String[9];
                    BigDecimal prevSum = sumNumber("入库", pid, diEx.getMId(), monthTime, true).subtract(sumNumber("出库", pid, diEx.getMId(), monthTime, true));
                    BigDecimal InSum = sumNumber("入库", pid, diEx.getMId(), monthTime, false);
                    BigDecimal OutSum = sumNumber("出库", pid, diEx.getMId(), monthTime, false);
                    BigDecimal prevPrice = sumPrice("入库", pid, diEx.getMId(), monthTime, true).subtract(sumPrice("出库", pid, diEx.getMId(), monthTime, true));
                    BigDecimal InPrice = sumPrice("入库", pid, diEx.getMId(), monthTime, false);
                    BigDecimal OutPrice = sumPrice("出库", pid, diEx.getMId(), monthTime, false);
                    BigDecimal unitPrice = BigDecimal.ZERO;
                    if ((prevSum.add(InSum).subtract(OutSum)).compareTo(BigDecimal.ZERO) != 0) {
                        unitPrice = (prevPrice.add(InPrice).subtract(OutPrice)).divide(prevSum.add(InSum).subtract(OutSum),2, BigDecimal.ROUND_HALF_UP);
                        /**
                         * 2019-01-15通过除法算出金额后，保留两位小数
                         * */
                        DecimalFormat    df   = new DecimalFormat("#.00");
                        unitPrice= new BigDecimal(df.format(unitPrice));
                    }
                    BigDecimal thisSum = prevSum.add(InSum).subtract(OutSum);
                    BigDecimal thisAllPrice = prevPrice.add(InPrice).subtract(OutPrice);
                    objs[0] = diEx.getMName().toString();
                    objs[1] = diEx.getMModel().toString();
                    objs[2] = diEx.getMaterialUnit().toString();
                    objs[3] = unitPrice.toString();
                    objs[4] = prevSum.toString();
                    objs[5] = InSum.toString();
                    objs[6] = OutSum.toString();
                    objs[7] = thisSum.toString();
                    objs[8] = thisAllPrice.toString();
                    objects.add(objs);
                }
            }
            File file = ExcelUtils.exportObjectsWithoutTitle(title, names, title, objects);
            //ExportExecUtil.showExec(file, file.getName() + "-" + monthTime, response);
            res.code = 200;
        } catch (Exception e) {
            e.printStackTrace();
            message = "导出失败";
            res.code = 500;
        }
        /**
         * 2019-01-15response已经返回，finally部分完全没必要
         * */
        return res;
    }

    /**
     * 数量合计
     *
     * @param type
     * @param MId
     * @param MonthTime
     * @param isPrev
     * @return
     */
    public BigDecimal sumNumber(String type, Integer ProjectId, Long MId, String MonthTime, Boolean isPrev)throws Exception {
        BigDecimal sumNumber = BigDecimal.ZERO;
        try {
            BigDecimal sum = depotItemService.findByType(type, ProjectId, MId, MonthTime, isPrev);
            if(sum != null) {
                sumNumber = sum;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sumNumber;
    }

    public BigDecimal assembleNumber(String subType, String mType, Integer ProjectId, Long MId, String MonthTime, Boolean isPrev) throws Exception{
        BigDecimal assembleNumber = BigDecimal.ZERO;
        try {
            BigDecimal sum = depotItemService.findAssembleByType(subType, mType, ProjectId, MId, MonthTime, isPrev);
            if(sum != null) {
                assembleNumber = sum;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assembleNumber;
    }

    /**
     * 价格合计
     *
     * @param type
     * @param MId
     * @param MonthTime
     * @param isPrev
     * @return
     */
    public BigDecimal sumPrice(String type, Integer ProjectId, Long MId, String MonthTime, Boolean isPrev) throws Exception{
        BigDecimal sumPrice = BigDecimal.ZERO;
        try {
            BigDecimal sum = depotItemService.findPriceByType(type, ProjectId, MId, MonthTime, isPrev);
            if(sum != null) {
                sumPrice = sum;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sumPrice;
    }

    public BigDecimal sumNumberBuyOrSale(String type, String subType, Long MId, String MonthTime)throws Exception {
        BigDecimal sumNumber = BigDecimal.ZERO;
        String sumType = "Number";
        try {
            BigDecimal sum = depotItemService.buyOrSale(type, subType, MId, MonthTime, sumType);
            if(sum != null) {
                sumNumber = sum;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sumNumber;
    }

    public BigDecimal sumPriceBuyOrSale(String type, String subType, Long MId, String MonthTime)throws Exception {
        BigDecimal sumPrice = BigDecimal.ZERO;
        String sumType = "Price";
        try {
            BigDecimal sum = depotItemService.buyOrSale(type, subType, MId, MonthTime, sumType);
            if(sum != null) {
                sumPrice = sum;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sumPrice;
    }
    /**
     *  批量删除单据明细信息
     * @Param: ids
     * @return java.lang.Object
     */
    @PostMapping(value = "/batchDeleteDepotItemByIds")
    public Object batchDeleteDepotItemByIds(@RequestParam("ids") String ids) throws Exception {
        JSONObject result = ExceptionConstants.standardSuccess();
        int i= depotItemService.batchDeleteDepotItemByIds(ids);
        if(i<1){
            logger.error("异常码[{}],异常提示[{}],参数,ids[{}]",
                    ExceptionConstants.DEPOT_ITEM_DELETE_FAILED_CODE,ExceptionConstants.DEPOT_ITEM_DELETE_FAILED_MSG,ids);
            throw new BusinessRunTimeException(ExceptionConstants.DEPOT_ITEM_DELETE_FAILED_CODE,
                    ExceptionConstants.DEPOT_ITEM_DELETE_FAILED_MSG);
        }
        return result;
    }


    /**
     * 库存预警报表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/findStockWarningCountByPage/{currentPage}/{pageSize}/{projectId}")
    public BaseResponseInfo findStockWarningCountByPage(@PathVariable("currentPage") Integer currentPage,
                                                  @PathVariable("pageSize") Integer pageSize,
                                                  @PathVariable("projectId") Integer pid )throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<DepotItemStockWarningCount> resList = new ArrayList<DepotItemStockWarningCount>();
            List<DepotItemStockWarningCount> list = depotItemService.findStockWarningCount((currentPage-1)*pageSize, pageSize,pid);
            int total = depotItemService.findStockWarningCountTotal(pid);
            map.put("total", total);
            map.put("rows", list);
            res.code = 200;
            res.data = map;
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }
    /**
     * 导出库存预警excel表格
     * @param currentPage
     * @param pageSize
     * @param projectId
     * @return
     */
    @GetMapping(value = "/exportWarningExcel/{currentPage}/{pageSize}/{projectId}")
    public BaseResponseInfo exportWarningExcel(@RequestParam("currentPage") Integer currentPage,
                                               @RequestParam("pageSize") Integer pageSize,
                                               @RequestParam("projectId") Integer projectId)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "成功";
        try {
            List<DepotItemStockWarningCount> dataList = depotItemService.findStockWarningCount((currentPage - 1) * pageSize, pageSize, projectId);
            //存放数据json数组
            Integer pid = projectId;
            String[] names = {"名称", "型号", "扩展信息", "单位", "入库数量", "出库数量", "库存数量", "安全库存量", "临界库存量"};
            String title = "库存预警报表";
            List<String[]> objects = new ArrayList<String[]>();
            if (null != dataList) {
                for (DepotItemStockWarningCount diEx : dataList) {
                    String[] objs = new String[9];

                    objs[0] = diEx.getMaterialName().toString();
                    objs[1] = diEx.getMaterialModel().toString();
                    objs[2] = diEx.getMaterialOther().toString();
                    objs[3] = diEx.getMaterialUnit().toString();
                    objs[4] = diEx.getBasicInNumber().toString();
                    objs[5] = diEx.getBasicOutNumber() == null ? "0" : diEx.getBasicOutNumber().toString();
                    objs[6] = diEx.getBasicNumber() == null ? "0" : diEx.getBasicNumber().toString();
                    objs[7] = diEx.getSafetystock() == null ? "0" : diEx.getSafetystock().toString();
                    objs[8] = diEx.getBasicLinjieNumber() == null ? "0" : diEx.getBasicLinjieNumber().toString();
                    objects.add(objs);
                }
            }
            File file = ExcelUtils.exportObjectsWithoutTitle(title+pid, names, title, objects);
            //ExportExecUtil.showExec(file, file.getName(), response);
            res.code = 200;
        } catch (Exception e) {
            e.printStackTrace();
            message = "导出失败";
            res.code = 500;
        }
        return res;
    }

    @GetMapping(value = "/findByType/{type}/{projectId}/{mId}/{monthTime}/{isPrev}")
    public BigDecimal findByType(@PathVariable("type")String type,
                          @PathVariable("projectId")Integer projectId,
                          @PathVariable("mId")Long mId,
                          @PathVariable("monthTime")String monthTime,
                          @PathVariable("isPrev")Boolean isPrev) throws Exception{

        return depotItemService.findByType(type,projectId,mId,monthTime,isPrev);
    }



    @GetMapping(value = "/findAssembleByType/{subType}/{mType}/{projectId}/{mId}/{monthTime}/{isPrev}")
    public BigDecimal findAssembleByType(@PathVariable("subType")String subType,
                                  @PathVariable("mType")String mType,
                                  @PathVariable("projectId")Integer projectId,
                                  @PathVariable("mId")Long mId,
                                  @PathVariable("monthTime")String monthTime,
                                  @PathVariable("isPrev")Boolean isPrev) throws Exception{
        return depotItemService.findAssembleByType(subType,mType,projectId,mId,monthTime,isPrev);
    }


    @GetMapping(value = "/findPriceByType/{type}/{projectId}/{mId}/{monthTime}/{isPrev}")
    public BigDecimal findPriceByType(@PathVariable("type")String type,
                               @PathVariable("projectId")Integer projectId,
                               @PathVariable("mId")Long mId,
                               @PathVariable("monthTime")String monthTime,
                               @PathVariable("isPrev")Boolean isPrev)throws Exception{
        return depotItemService.findPriceByType(type,projectId,mId,monthTime,isPrev);
    }


    @GetMapping(value = "/buyOrSale/{type}/{subType}/{mId}/{monthTime}/{sumType}")
    public BigDecimal buyOrSale(@PathVariable("type")String type,
                         @PathVariable("subType")String subType,
                         @PathVariable("mId")Long mId,
                         @PathVariable("monthTime")String monthTime,
                         @PathVariable("sumType")String sumType) throws Exception{

        return depotItemService.buyOrSale(type,subType,mId,monthTime,sumType);
    }


    @GetMapping(value = "/findStockWarningCount/{count}/{pageSize}/{projectId}")
    public List<DepotItemStockWarningCount> findStockWarningCount(@PathVariable("count") Integer count,
                                                           @PathVariable("pageSize")Integer pageSize,
                                                           @PathVariable("projectId")Integer projectId) throws Exception{
        return depotItemService.findStockWarningCount(count,pageSize,projectId);
    }

    }
