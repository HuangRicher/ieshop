package com.seamwhole.weberpadmin.client;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.hystrix.SupplierClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.constants.BusinessConstants;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = SupplierClientHystrix.class)
public interface SupplierClient {

    /**
     * 更新供应商-只更新预付款，其余用原来的值
     */
    @PostMapping(value = "/supplier/updateAdvanceIn")
    String updateAdvanceIn(@RequestParam("supplierId") Long supplierId,
                           @RequestParam("advanceIn") BigDecimal advanceIn);

    /**
     * 查找客户信息-下拉框
     */
    @PostMapping(value = "/supplier/findBySelect_cus")
    JSONArray findBySelectCus();

    /**
     * 查找供应商信息-下拉框
     */
    @PostMapping(value = "/supplier/findBySelect_sup")
    JSONArray findBySelectSup() ;

    /**
     * 查找会员信息-下拉框
     */
    @PostMapping(value = "/supplier/findBySelect_retail")
    JSONArray findBySelectRetail();

    /**
     * 根据id查找信息
     */
    @GetMapping(value = "/supplier/findById")
    BaseResponseInfo findById(@RequestParam("supplierId") Long supplierId);

    /**
     * 批量设置状态-启用或者禁用
     */
    @PostMapping(value = "/supplier/batchSetEnable")
    String batchSetEnable(@RequestParam("enabled") Boolean enabled,
                          @RequestParam("supplierIDs") String supplierIDs);

    /**
     * 用户对应客户显示
     */
    @PostMapping(value = "/supplier/findUserCustomer")
    JSONArray findUserCustomer(@RequestParam("UBType") String type,
                               @RequestParam("UBKeyId") String keyId);

    /**
     * 生成excel表格
     */
    @GetMapping(value = "/supplier/exportExcel/{supplier}/{type}/{phonenum}/{telephone}/{description}")
    BaseResponseInfo exportExcel(@PathVariable("supplier") String supplier,
                                 @PathVariable("type") String type,
                                 @PathVariable("phonenum") String phonenum,
                                 @PathVariable("telephone") String telephone,
                                 @PathVariable("description") String description);

    /**
     * 导入excel表格-供应商
     */
    @PostMapping(value = "/supplier/importExcelVendor")
    void importExcelVendor(@RequestParam("file")MultipartFile supplierFile) ;

    /**
     * 导入excel表格-客户
     */
    @PostMapping(value = "/supplier/importExcelCustomer")
    void importExcelCustomer(@RequestParam("file")MultipartFile supplierFile);

    /**
     * 导入excel表格-会员
     */
    @PostMapping(value = "/supplier/importExcelMember")
    void importExcelMember(@RequestParam("file") MultipartFile supplierFile);


    /**
     *  批量删除供应商信息
     */
    @PostMapping(value = "/supplier/batchDeleteSupplierByIds")
    Object batchDeleteSupplierByIds(@RequestParam("ids") String ids,
                                    @RequestParam("deleteType")String deleteType);

}
