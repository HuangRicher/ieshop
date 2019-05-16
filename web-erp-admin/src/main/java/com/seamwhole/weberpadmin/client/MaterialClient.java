package com.seamwhole.weberpadmin.client;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.hystrix.MaterialClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.constants.BusinessConstants;
import com.seamwhole.weberpadmin.constants.Constants;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import com.seamwhole.weberpadmin.domain.MaterialInfo;
import com.seamwhole.weberpadmin.domain.MaterialVo4Unit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = MaterialClientHystrix.class)
public interface MaterialClient {

    @PostMapping(value = "/material/checkIsExist")
    String checkIsExist(@RequestBody MaterialInfo materialInfo);

    /**
     * 批量设置状态-启用或者禁用
     */
    @PostMapping(value = "/material/batchSetEnable")
    String batchSetEnable(@RequestParam("enabled") Boolean enabled,
                          @RequestParam("materialIDs") String materialIDs);

    /**
     * 根据id来查询商品名称
     */
    @GetMapping(value = "/material/findById")
    BaseResponseInfo findById(@RequestParam("id") Long id) ;

    /**
     * 查找商品信息-下拉框
     */
    @GetMapping(value = "/material/findBySelect")
    JSONArray findBySelect(@RequestParam("mpList") String mpList);


    /**
     * 查找商品信息-统计排序
     */
    @GetMapping(value = "/material/findByOrder")
    BaseResponseInfo findByOrder();

    /**
     * 生成excel表格
     */
    @GetMapping(value = "/material/exportExcel/{name}/{model}/{categoryId}/{categoryIds}")
    BaseResponseInfo exportExcel(@PathVariable("name") String name,
                                 @PathVariable("model") String model,
                                 @PathVariable("categoryId") Long categoryId,
                                 @PathVariable("categoryIds") String categoryIds);

    /**
     * excel表格导入
     */
    @PostMapping(value = "/material/importExcel")
    void importExcel(@RequestParam("file") MultipartFile materialFile);


    @GetMapping(value = "/material/getMaterialEnableSerialNumberList/{pageSize}/{currentPage}/{search}")
    String getMaterialEnableSerialNumberList(@PathVariable(value = Constants.PAGE_SIZE) Integer pageSize,
                                             @PathVariable(value = Constants.CURRENT_PAGE) Integer currentPage,
                                             @PathVariable(value = Constants.SEARCH) String search);

    /**
     *  批量删除商品信息
     */
    @PostMapping(value = "/material/batchDeleteMaterialByIds")
    Object batchDeleteMaterialByIds(@RequestParam("ids") String ids,
                                    @RequestParam(value="deleteType")String deleteType);

    @GetMapping(value = "/material/findByAll/{name}/{model}/{categoryId}/{categoryIds}")
    List<MaterialVo4Unit> findByAll(@PathVariable("name") String name,
                                    @PathVariable("model")String model,
                                    @PathVariable("categoryId")Long categoryId,
                                    @PathVariable("categoryIds")String categoryIds);
}
