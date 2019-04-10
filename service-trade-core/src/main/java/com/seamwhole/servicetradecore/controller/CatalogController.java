package com.seamwhole.servicetradecore.controller;

import com.seamwhole.servicetradecore.domain.CategoryInfo;
import com.seamwhole.servicetradecore.model.Category;
import com.seamwhole.servicetradecore.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(tags = "栏目")
@RestController
@RequestMapping("/api/catalog")
public class CatalogController extends BaseController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 获取分类栏目数据
     */
    @ApiOperation(value = "获取分类栏目数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "id", paramType = "query", required = false),
            @ApiImplicitParam(name = "page", value = "page", paramType = "query", required = false),
            @ApiImplicitParam(name = "size", value = "size", paramType = "query", required = false)})
    @PostMapping(value = "index")
    public Object index(Integer id,
                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map<String, Object> resultObj = new HashMap();
        Map params = new HashMap();
        params.put("page", page);
        params.put("limit", size);
        params.put("sidx", "sort_order");
        params.put("order", "asc");
        params.put("parent_id", 0);
        //查询列表数据
        List<Category> data = categoryService.queryList(params);
        //
        CategoryInfo currentCategoryInfo = new CategoryInfo();
        Category currentCategory=null;
        if (null != id) {
            currentCategory = categoryService.queryObject(id);
        }
        if (null == currentCategory && null != data && data.size() != 0) {
            currentCategory = data.get(0);
        } else {
            currentCategory = new Category();
        }
        BeanUtils.copyProperties(currentCategory,currentCategoryInfo);
        //获取子分类数据
        if (null != currentCategory && null != currentCategory.getId()) {
            params.put("parent_id", currentCategory.getId());
            currentCategoryInfo.setSubCategoryList(categoryService.queryList(params));
        }

        resultObj.put("categoryList", data);
        resultObj.put("currentCategory", currentCategoryInfo);
        return toResponsSuccess(resultObj);
    }

    /**
     */
    @ApiOperation(value = "分类目录当前分类数据接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "id", paramType = "query", required = false)})
    @PostMapping(value = "current")
    public Object current(Integer id) {
        Map<String, Object> resultObj = new HashMap();
        Map params = new HashMap();
        params.put("parent_id", 0);
        CategoryInfo currentCategoryInfo = new CategoryInfo();
        Category currentCategory = null;
        if (null != id) {
            currentCategory = categoryService.queryObject(id);
        }
        BeanUtils.copyProperties(currentCategory,currentCategoryInfo);
        //获取子分类数据
        if (null != currentCategory && null != currentCategory.getId()) {
            params.put("parent_id", currentCategory.getId());
            currentCategoryInfo.setSubCategoryList(categoryService.queryList(params));
        }
        resultObj.put("currentCategory", currentCategoryInfo);
        return toResponsSuccess(resultObj);
    }
}