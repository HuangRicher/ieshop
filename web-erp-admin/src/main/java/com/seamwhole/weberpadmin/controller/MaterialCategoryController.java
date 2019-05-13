package com.seamwhole.weberpadmin.controller;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.MaterialCategoryClient;
import com.seamwhole.weberpadmin.constants.BusinessConstants;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/materialCategory")
public class MaterialCategoryController {
    private Logger logger = LoggerFactory.getLogger(MaterialCategoryController.class);

    @Resource
    private MaterialCategoryClient materialCategoryClient;


    @GetMapping(value = "/getAllList")
    public BaseResponseInfo getAllList(@RequestParam("parentId") Long parentId) throws Exception{
        BaseResponseInfo res = materialCategoryClient.getAllList(parentId);
        return res;
    }

    /**
     * 根据id来查询商品名称
     */
    @RequestMapping(value = "/findById")
    public BaseResponseInfo findById(@RequestParam("id") Long id)throws Exception {
        BaseResponseInfo res = materialCategoryClient.findById(id);
        return res;
    }


    /**
     * 获取商品类别树数据
     */
    @RequestMapping(value = "/getMaterialCategoryTree")
    public JSONArray getMaterialCategoryTree(@RequestParam("id") Long id) throws Exception{
       JSONArray arr=materialCategoryClient.getMaterialCategoryTree(id);
        return arr;
    }


    /**
     *  新增商品类别数据
     */
    @RequestMapping(value = "/addMaterialCategory")
    public Object addMaterialCategory(@RequestParam("info") String beanJson) throws Exception {

        return materialCategoryClient.addMaterialCategory(beanJson);
    }


    /**
     *  修改商品类别数据
     */
    @RequestMapping(value = "/editMaterialCategory")
    public Object editMaterialCategory(@RequestParam("info") String beanJson) throws Exception {

        return materialCategoryClient.editMaterialCategory(beanJson);
    }


    /**
     *  批量删除商品类别信息
     */
    @RequestMapping(value = "/batchDeleteMaterialCategory")
    public Object batchDeleteMaterialCategory(@RequestParam("ids") String ids,
                                              @RequestParam(value="deleteType", required =false,defaultValue= BusinessConstants.DELETE_TYPE_NORMAL)
                                                      String deleteType) throws Exception {

        return materialCategoryClient.batchDeleteMaterialCategory(ids,deleteType);
    }
}
