package com.seamwhole.weberpadmin.client;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.hystrix.MaterialCategoryClientHystrix;
import com.seamwhole.weberpadmin.config.FeignConfig;
import com.seamwhole.weberpadmin.constants.BusinessConstants;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@FeignClient(serviceId = "erp-core-service" ,configuration = FeignConfig.class,fallback = MaterialCategoryClientHystrix.class)
public interface MaterialCategoryClient {

    @GetMapping(value = "/materialCategory/getAllList")
    BaseResponseInfo getAllList(@RequestParam("parentId") Long parentId);

    /**
     * 根据id来查询商品名称
     */
    @GetMapping(value = "/materialCategory/findById")
    BaseResponseInfo findById(@RequestParam("id") Long id);


    /**
     * 获取商品类别树数据
     */
    @GetMapping(value = "/materialCategory/getMaterialCategoryTree")
    JSONArray getMaterialCategoryTree(@RequestParam("id") Long id);


    /**
     *  新增商品类别数据
     */
    @PostMapping(value = "/materialCategory/addMaterialCategory")
    Object addMaterialCategory(@RequestParam("info") String beanJson) ;

    /**
     *  修改商品类别数据
     */
    @PostMapping(value = "/materialCategory/editMaterialCategory")
    Object editMaterialCategory(@RequestParam("info") String beanJson);

    /**
     *  批量删除商品类别信息
     */
    @PostMapping(value = "/materialCategory/batchDeleteMaterialCategory")
    Object batchDeleteMaterialCategory(@RequestParam("ids") String ids,
                                       @RequestParam("deleteType")String deleteType) ;
}
