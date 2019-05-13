package com.seamwhole.weberpadmin.controller;

import com.seamwhole.weberpadmin.client.DepotItemClient;
import com.seamwhole.weberpadmin.constants.Constants;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import com.seamwhole.weberpadmin.domain.DepotItemStockWarningCount;
import com.seamwhole.weberpadmin.domain.DepotItemVo4WithInfoEx;
import com.seamwhole.weberpadmin.utils.ExcelUtils;
import com.seamwhole.weberpadmin.utils.ExportExecUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/depotItem")
public class DepotItemController {
    private Logger logger = LoggerFactory.getLogger(DepotItemController.class);

    @Autowired
    private DepotItemClient depotItemClient;


    /**
     * 根据材料信息获取
     * @param materialParam  商品参数
     * @param depotIds  拥有的仓库信息
     */
    @GetMapping(value = "/getHeaderIdByMaterial")
    public BaseResponseInfo getHeaderIdByMaterial(@RequestParam("materialParam") String materialParam,
                                                  @RequestParam("depotIds") String depotIds)throws Exception {
        BaseResponseInfo res =depotItemClient.getHeaderIdByMaterial(materialParam,depotIds);
        return res;
    }

    /**
     * 只根据商品id查询单据列表
     */
    @GetMapping(value = "/findDetailByTypeAndMaterialId")
    public String findDetailByTypeAndMaterialId(
            @RequestParam(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = Constants.CURRENT_PAGE, required = false) Integer currentPage,
            @RequestParam("materialId") String mId)throws Exception {

        return depotItemClient.findDetailByTypeAndMaterialId(pageSize,currentPage,mId);
    }


    /**
     * 根据商品id和仓库id查询库存数量
     */
    @GetMapping(value = "/findStockNumById")
    public String findStockNumById(@RequestParam(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
                                   @RequestParam(value = Constants.CURRENT_PAGE, required = false) Integer currentPage,
                                   @RequestParam("projectId") Integer pid,
                                   @RequestParam("materialId") String mId,
                                   @RequestParam("monthTime") String monthTime) throws Exception{

        return depotItemClient.findStockNumById(pageSize,currentPage,pid,mId,monthTime);
    }


    /**
     * 只根据商品id查询库存数量
     */
    @GetMapping(value = "/findStockNumByMaterialId")
    public String findStockNumByMaterialId(@RequestParam(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
                                           @RequestParam(value = Constants.CURRENT_PAGE, required = false) Integer currentPage,
                                           @RequestParam("materialId") String mId,
                                           @RequestParam("monthTime") String monthTime)  throws Exception{

        return depotItemClient.findStockNumByMaterialId(pageSize,currentPage,mId,monthTime);
    }



    /**
     * 保存明细
     */
    @PostMapping(value = "/saveDetails")
    public String saveDetails(@RequestParam("inserted") String inserted,
                              @RequestParam("deleted") String deleted,
                              @RequestParam("updated") String updated,
                              @RequestParam("headerId") Long headerId) throws Exception{

        return depotItemClient.saveDetails(inserted,deleted,updated,headerId);
    }


    @GetMapping(value = "/getDetailList")
    public BaseResponseInfo getDetailList(@RequestParam("headerId") Long headerId,
                                          @RequestParam("mpList") String mpList)throws Exception {
        BaseResponseInfo res = depotItemClient.getDetailList(headerId,mpList);
        return res;
    }


    /**
     * 查找所有的明细
     */
    @GetMapping(value = "/findByAll")
    public BaseResponseInfo findByAll(@RequestParam("currentPage") Integer currentPage,
                                      @RequestParam("pageSize") Integer pageSize,
                                      @RequestParam("projectId") Integer projectId,
                                      @RequestParam("monthTime") String monthTime,
                                      @RequestParam("headIds") String headIds,
                                      @RequestParam("materialIds") String materialIds,
                                      @RequestParam("mpList") String mpList)throws Exception {
        BaseResponseInfo res = depotItemClient.findByAllByPage(currentPage,pageSize,projectId,monthTime,headIds,materialIds,mpList);
        return res;
    }

    /**
     * 统计总计金额
     */
    @GetMapping(value = "/totalCountMoney")
    public BaseResponseInfo totalCountMoney(@RequestParam("projectId") Integer pid,
                                            @RequestParam("monthTime") String monthTime,
                                            @RequestParam("headIds") String headIds,
                                            @RequestParam("materialIds") String materialIds) throws Exception{
        BaseResponseInfo res = depotItemClient.totalCountMoney(pid,monthTime,headIds,materialIds);
        return res;
    }

    /**
     * 进货统计
     */
    @GetMapping(value = "/buyIn")
    public BaseResponseInfo buyIn(@RequestParam("currentPage") Integer currentPage,
                                  @RequestParam("pageSize") Integer pageSize,
                                  @RequestParam("monthTime") String monthTime,
                                  @RequestParam("headIds") String headIds,
                                  @RequestParam("materialIds") String materialIds,
                                  @RequestParam("mpList") String mpList)throws Exception {
        BaseResponseInfo res = depotItemClient.buyIn(currentPage,pageSize,monthTime,headIds,materialIds,mpList);
        return res;
    }

    /**
     * 销售统计
     */
    @GetMapping(value = "/saleOut")
    public BaseResponseInfo saleOut(@RequestParam("currentPage") Integer currentPage,
                                    @RequestParam("pageSize") Integer pageSize,
                                    @RequestParam("monthTime") String monthTime,
                                    @RequestParam("headIds") String headIds,
                                    @RequestParam("materialIds") String materialIds,
                                    @RequestParam("mpList") String mpList)throws Exception {
        BaseResponseInfo res = depotItemClient.saleOut(currentPage,pageSize,monthTime,headIds,materialIds,mpList);
        return res;
    }

    /**
     * 导出excel表格
     */
    @GetMapping(value = "/exportExcel")
    public BaseResponseInfo exportExcel(@RequestParam("currentPage") Integer currentPage,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam("projectId") Integer projectId,
                                        @RequestParam("monthTime") String monthTime,
                                        @RequestParam("headIds") String headIds,
                                        @RequestParam("materialIds") String materialIds,
                                        HttpServletRequest request, HttpServletResponse response)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "成功";
        try {
            List<DepotItemVo4WithInfoEx> dataList = depotItemClient.findByAll(headIds, materialIds, (currentPage-1)*pageSize, pageSize);
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
            ExportExecUtil.showExec(file, file.getName() + "-" + monthTime, response);
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
     */
    public BigDecimal sumNumber(String type, Integer ProjectId, Long MId, String MonthTime, Boolean isPrev)throws Exception {
        BigDecimal sumNumber = BigDecimal.ZERO;
        try {
            BigDecimal sum = depotItemClient.findByType(type, ProjectId, MId, MonthTime, isPrev);
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
            BigDecimal sum = depotItemClient.findAssembleByType(subType, mType, ProjectId, MId, MonthTime, isPrev);
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
     */
    public BigDecimal sumPrice(String type, Integer ProjectId, Long MId, String MonthTime, Boolean isPrev) throws Exception{
        BigDecimal sumPrice = BigDecimal.ZERO;
        try {
            BigDecimal sum = depotItemClient.findPriceByType(type, ProjectId, MId, MonthTime, isPrev);
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
            BigDecimal sum = depotItemClient.buyOrSale(type, subType, MId, MonthTime, sumType);
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
            BigDecimal sum = depotItemClient.buyOrSale(type, subType, MId, MonthTime, sumType);
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
     */
    @RequestMapping(value = "/batchDeleteDepotItemByIds")
    public Object batchDeleteDepotItemByIds(@RequestParam("ids") String ids) throws Exception {
        return depotItemClient.batchDeleteDepotItemByIds(ids);
    }
    /**
     * 库存预警报表
     */
    @GetMapping(value = "/findStockWarningCount")
    public BaseResponseInfo findStockWarningCount(@RequestParam("currentPage") Integer currentPage,
                                                  @RequestParam("pageSize") Integer pageSize,
                                                  @RequestParam("projectId") Integer pid )throws Exception {
        BaseResponseInfo res = depotItemClient.findStockWarningCountByPage(currentPage,pageSize,pid);
        return res;
    }


    /**
     * 导出库存预警excel表格
     */
    @GetMapping(value = "/exportWarningExcel")
    public BaseResponseInfo exportWarningExcel(@RequestParam("currentPage") Integer currentPage,
                                               @RequestParam("pageSize") Integer pageSize,
                                               @RequestParam("projectId") Integer projectId,
                                               HttpServletResponse response)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "成功";
        try {
            List<DepotItemStockWarningCount> dataList = depotItemClient.findStockWarningCount((currentPage - 1) * pageSize, pageSize, projectId);
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
            ExportExecUtil.showExec(file, file.getName(), response);
            res.code = 200;
        } catch (Exception e) {
            e.printStackTrace();
            message = "导出失败";
            res.code = 500;
        }
        return res;
    }
    }
