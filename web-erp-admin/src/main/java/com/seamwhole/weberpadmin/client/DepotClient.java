package com.seamwhole.weberpadmin.client;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.hystrix.DepotClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.constants.BusinessConstants;
import com.seamwhole.weberpadmin.constants.Constants;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = DepotClientHystrix.class)
public interface DepotClient {

    @GetMapping(value = "/depot/getAllList")
    BaseResponseInfo getAllList() ;

    /**
     * 用户对应仓库显示
     */
    @PostMapping(value = "/depot/findUserDepot")
    JSONArray findUserDepot(@RequestParam("UBType") String type, @RequestParam("UBKeyId") String keyId);


    @GetMapping(value = "/depot/findDepotByUserId")
    JSONArray findDepotByUserId(@RequestParam("UBType") String type, @RequestParam("UBKeyId") String keyId);

    /**
     * 查询仓库列表信息
     */
    @GetMapping(value = "/depot/getDepotList/{pageSize}/{currentPage}/{search}")
    String getDepotList(@PathVariable(value = Constants.PAGE_SIZE) Integer pageSize,
            @PathVariable(value = Constants.CURRENT_PAGE) Integer currentPage,
            @PathVariable(value = Constants.SEARCH) String search) ;

    /**
     *  批量删除仓库信息
     */
    @PostMapping(value = "/depot/batchDeleteDepotByIds")
    Object batchDeleteDepotByIds(@RequestParam("ids") String ids, @RequestParam(value="deleteType")String deleteType);



    @PostMapping(value = "/updateDepotIsDefault")
    String updateDepotIsDefault(@RequestParam("isDefault") Boolean isDefault,
                                        @RequestParam("depotID") Long depotID);
}
