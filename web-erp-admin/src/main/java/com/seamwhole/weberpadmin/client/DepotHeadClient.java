package com.seamwhole.weberpadmin.client;

import com.seamwhole.weberpadmin.client.hystrix.DepotHeadClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import com.seamwhole.weberpadmin.domain.DepotHeadInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = DepotHeadClientHystrix.class)
public interface DepotHeadClient {

    /**
     * 批量设置状态-审核或者反审核
     */
    @PostMapping(value = "/depotHead/batchSetStatus")
    String batchSetStatus(@RequestParam("status") String status,
                          @RequestParam("depotHeadIDs") String depotHeadIDs);

    /**
     * 单据编号生成接口
     */
    @GetMapping(value = "/depotHead/buildNumber")
    BaseResponseInfo buildNumber();

    /**
     * 获取最大的id
     */
    @GetMapping(value = "/depotHead/getMaxId")
    BaseResponseInfo getMaxId();

    /**
     * 查找单据_根据月份(报表)
     */
    @GetMapping(value = "/depotHead/findByMonth")
    BaseResponseInfo findByMonth(@RequestParam("monthTime") String monthTime);

    /**
     * 入库出库明细接口
     */
    @PostMapping(value = "/depotHead/findInDetail")
    BaseResponseInfo findInDetail(@RequestBody DepotHeadInfo depotHeadInfo);

    /**
     * 入库出库统计接口
     */
    @PostMapping(value = "/depotHead/findInOutMaterialCount")
    BaseResponseInfo findInOutMaterialCount(@RequestBody DepotHeadInfo depotHeadInfo);

    /**
     * 对账单接口
     */
    @GetMapping(value = "/depotHead/findStatementAccount")
    BaseResponseInfo findStatementAccount(@RequestBody DepotHeadInfo info);

    /**
     * 查询单位的累计应收和累计应付，零售不能计入
     */
    @GetMapping(value = "/depotHead/findTotalPay/{supplierId}/{endTime}/{supType}")
    BaseResponseInfo findTotalPay(@PathVariable("supplierId") Integer supplierId,
                                  @PathVariable("endTime") String endTime,
                                  @PathVariable("supType") String supType);

    /**
     * 根据编号查询单据信息
     */
    @GetMapping(value = "/depotHead/getDetailByNumber")
    BaseResponseInfo getDetailByNumber(@RequestParam("number") String number);



    /**
     *  新增单据主表及单据子表信息
     */
    @PostMapping(value = "/depotHead/addDepotHeadAndDetail/{info}/{inserted}/{deleted}/{updated}")
    Object addDepotHeadAndDetail(@PathVariable("info") String beanJson,
                                 @PathVariable("inserted") String inserted,
                                 @PathVariable("deleted") String deleted,
                                 @PathVariable("updated") String updated);

    /**
     * 更新单据主表及单据子表信息
     */
    @PostMapping(value = "/depotHead/updateDepotHeadAndDetail")
    Object updateDepotHeadAndDetail(@RequestBody DepotHeadInfo info);

    /**
     *  删除单据主表及子表信息
     */
    @PostMapping(value = "/depotHead/deleteDepotHeadAndDetail")
    Object deleteDepotHeadAndDetail(@RequestParam("id") Long id) ;

    /**
     *  删除单据主表及子表信息
     */
    @PostMapping(value = "/depotHead/batchDeleteDepotHeadAndDetail")
    Object batchDeleteDepotHeadAndDetail(@RequestParam("ids") String ids);
}
