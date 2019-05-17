package com.seamwhole.serviceerpcore.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.serviceerpcore.constants.BusinessConstants;
import com.seamwhole.serviceerpcore.constants.ExceptionConstants;
import com.seamwhole.serviceerpcore.model.Depot;
import com.seamwhole.serviceerpcore.mapper.vo.DepotEx;
import com.seamwhole.serviceerpcore.exception.BusinessRunTimeException;
import com.seamwhole.serviceerpcore.service.DepotService;
import com.seamwhole.serviceerpcore.service.UserBusinessService;
import com.seamwhole.serviceerpcore.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.seamwhole.serviceerpcore.utils.ResponseJsonUtil.returnJson;

@RestController
@RequestMapping(value = "/depot")
public class DepotController {
    private Logger logger = LoggerFactory.getLogger(DepotController.class);

    @Resource
    private DepotService depotService;

    @Resource
    private UserBusinessService userBusinessService;

    @GetMapping(value = "/getAllList")
    public BaseResponseInfo getAllList() throws Exception{
        BaseResponseInfo res = new BaseResponseInfo();
        try {
            List<Depot> depotList = depotService.getAllList();
            res.code = 200;
            res.data = depotList;
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }

    /**
     * 用户对应仓库显示
     * @param type
     * @param keyId
     * @param request
     * @return
     */
    @PostMapping(value = "/findUserDepot")
    public JSONArray findUserDepot(@RequestParam("UBType") String type, @RequestParam("UBKeyId") String keyId) throws Exception{
        JSONArray arr = new JSONArray();
        try {
            List<Depot> dataList = depotService.findUserDepot();
            //开始拼接json数据
            JSONObject outer = new JSONObject();
            outer.put("id", 1);
            outer.put("text", "仓库列表");
            outer.put("state", "open");
            //存放数据json数组
            JSONArray dataArray = new JSONArray();
            if (null != dataList) {
                for (Depot depot : dataList) {
                    JSONObject item = new JSONObject();
                    item.put("id", depot.getId());
                    item.put("text", depot.getName());
                    //勾选判断1
                    Boolean flag = false;
                    try {
                        flag = userBusinessService.checkIsUserBusinessExist(type, keyId, "[" + depot.getId().toString() + "]");
                    } catch (Exception e) {
                        logger.error(">>>>>>>>>>>>>>>>>设置用户对应的仓库：类型" + type + " KeyId为： " + keyId + " 存在异常！");
                    }
                    if (flag == true) {
                        item.put("checked", true);
                    }
                    //结束
                    dataArray.add(item);
                }
            }
            outer.put("children", dataArray);
            arr.add(outer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    @GetMapping(value = "/findDepotByUserId")
    public JSONArray findDepotByUserId(@RequestParam("UBType") String type, @RequestParam("UBKeyId") String keyId) throws Exception{
        JSONArray arr = new JSONArray();
        try {
            List<Depot> dataList = depotService.findUserDepot();
            //开始拼接json数据
            if (null != dataList) {
                for (Depot depot : dataList) {
                    JSONObject item = new JSONObject();
                    //勾选判断1
                    Boolean flag = false;
                    try {
                        flag = userBusinessService.checkIsUserBusinessExist(type, keyId, "[" + depot.getId().toString() + "]");
                    } catch (DataAccessException e) {
                        logger.error(">>>>>>>>>>>>>>>>>查询用户对应的仓库：类型" + type + " KeyId为： " + keyId + " 存在异常！");
                    }
                    if (flag == true) {
                        item.put("id", depot.getId());
                        item.put("depotName", depot.getName());
                        arr.add(item);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    /**
     * 查询仓库列表信息
     * @Param: pageSize
     * @Param: currentPage
     * @Param: search
     * @return java.lang.String
     */
    @GetMapping(value = "/getDepotList/{pageSize}/{currentPage}/{search}")
    public String getDepotList(@PathVariable(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
            @PathVariable(value = Constants.CURRENT_PAGE, required = false) Integer currentPage,
            @PathVariable(value = Constants.SEARCH, required = false) String search) throws Exception{
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        //查询参数
        JSONObject obj=JSON.parseObject(search);
        Set<String> key= obj.keySet();
        for(String keyEach: key){
            parameterMap.put(keyEach,obj.getString(keyEach));
        }
        PageQueryInfo queryInfo = new PageQueryInfo();
        Map<String, Object> objectMap = new HashMap<String, Object>();
        if (pageSize == null || pageSize <= 0) {
            pageSize = BusinessConstants.DEFAULT_PAGINATION_PAGE_SIZE;
        }
        if (currentPage == null || currentPage <= 0) {
            currentPage = BusinessConstants.DEFAULT_PAGINATION_PAGE_NUMBER;
        }
        PageHelper.startPage(currentPage,pageSize,true);
        List<DepotEx> list = depotService.getDepotList(parameterMap);
        //获取分页查询后的数据
       PageInfo<DepotEx> pageInfo = new PageInfo<>(list);
        objectMap.put("page", queryInfo);
        if (list == null) {
            queryInfo.setRows(new ArrayList<Object>());
            queryInfo.setTotal(BusinessConstants.DEFAULT_LIST_NULL_NUMBER);
            return returnJson(objectMap, "查找不到数据", ErpInfo.OK.code);
        }
        queryInfo.setRows(list);
        queryInfo.setTotal(pageInfo.getTotal());
        return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
    }
    /**
     *  批量删除仓库信息
     * @Param: ids
     * @return java.lang.Object
     */
    @PostMapping(value = "/batchDeleteDepotByIds")
    public Object batchDeleteDepotByIds(@RequestParam("ids") String ids, @RequestParam(value="deleteType",
            required =false,defaultValue=BusinessConstants.DELETE_TYPE_NORMAL)String deleteType) throws Exception {
        JSONObject result = ExceptionConstants.standardSuccess();
        int i=0;
        if(BusinessConstants.DELETE_TYPE_NORMAL.equals(deleteType)){
            i= depotService.batchDeleteDepotByIdsNormal(ids);
        }else if(BusinessConstants.DELETE_TYPE_FORCE.equals(deleteType)){
            i= depotService.batchDeleteDepotByIds(ids);
        }else{
            logger.error("异常码[{}],异常提示[{}],参数,ids[{}],deleteType[{}]",
                    ExceptionConstants.DELETE_REFUSED_CODE,ExceptionConstants.DELETE_REFUSED_MSG,ids,deleteType);
            throw new BusinessRunTimeException(ExceptionConstants.DELETE_REFUSED_CODE,
                    ExceptionConstants.DELETE_REFUSED_MSG);
        }
        if(i<1){
            logger.error("异常码[{}],异常提示[{}],参数,ids[{}]",
                    ExceptionConstants.DEPOT_DELETE_FAILED_CODE,ExceptionConstants.DEPOT_DELETE_FAILED_MSG,ids);
            throw new BusinessRunTimeException(ExceptionConstants.DEPOT_DELETE_FAILED_CODE,
                    ExceptionConstants.DEPOT_DELETE_FAILED_MSG);
        }
        return result;
    }
    @PostMapping(value = "/updateDepotIsDefault")
    public String updateDepotIsDefault(@RequestParam(value = "isDefault",required = false,defaultValue = "false") Boolean isDefault,
                                        @RequestParam("depotID") Long depotID) throws Exception{
        Map<String, Object> objectMap = new HashMap<String, Object>();
        int res = depotService.updateDepotIsDefault(isDefault, depotID);
        if(res > 0) {
            return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
        } else {
            return returnJson(objectMap, ErpInfo.ERROR.name, ErpInfo.ERROR.code);
        }
    }
}
