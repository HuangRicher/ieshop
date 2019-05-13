package com.seamwhole.weberpadmin.controller;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.DepotClient;
import com.seamwhole.weberpadmin.constants.BusinessConstants;
import com.seamwhole.weberpadmin.constants.Constants;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/depot")
public class DepotController {
    private Logger logger = LoggerFactory.getLogger(DepotController.class);

    @Autowired
    private DepotClient depotClient;

    @GetMapping(value = "/getAllList")
    public BaseResponseInfo getAllList() throws Exception{
        BaseResponseInfo res = depotClient.getAllList();
        return res;
    }

    /**
     * 用户对应仓库显示
     * @param type
     * @param keyId
     * @return
     */
    @PostMapping(value = "/findUserDepot")
    public JSONArray findUserDepot(@RequestParam("UBType") String type, @RequestParam("UBKeyId") String keyId) throws Exception{
        JSONArray arr = depotClient.findUserDepot(type,keyId);
        return arr;
    }

    @RequestMapping(value = "/findDepotByUserId")
    public JSONArray findDepotByUserId(@RequestParam("UBType") String type, @RequestParam("UBKeyId") String keyId) throws Exception{
        JSONArray arr = depotClient.findDepotByUserId(type,keyId);
        return arr;
    }


    /**
     * 查询仓库列表信息
     * @Param: pageSize
     * @Param: currentPage
     * @Param: search
     * @return java.lang.String
     */
    @RequestMapping(value = "/getDepotList")
    public String getDepotList(@RequestParam(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
                               @RequestParam(value = Constants.CURRENT_PAGE, required = false) Integer currentPage,
                               @RequestParam(value = Constants.SEARCH, required = false) String search) throws Exception{

        return depotClient.getDepotList(pageSize,currentPage,search);
    }


    /**
     *  批量删除仓库信息
     * @Param: ids
     * @return java.lang.Object
     */
    @RequestMapping(value = "/batchDeleteDepotByIds")
    public Object batchDeleteDepotByIds(@RequestParam("ids") String ids,
                                        @RequestParam(value="deleteType", required =false,defaultValue= BusinessConstants.DELETE_TYPE_NORMAL)
                                                String deleteType) throws Exception {

        return depotClient.batchDeleteDepotByIds(ids,deleteType);
    }


    @PostMapping(value = "/updateDepotIsDefault")
    public String updateDepotIsDefault(@RequestParam("isDefault") Boolean isDefault,
                                        @RequestParam("depotID") Long depotID) throws Exception{
        return depotClient.updateDepotIsDefault(isDefault,depotID);
    }
}
