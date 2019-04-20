package com.seamwhole.servicetradecore.controller;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.annotation.IgnoreAuth;
import com.seamwhole.servicetradecore.model.ShopBrand;
import com.seamwhole.servicetradecore.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@Api(tags = "品牌")
@RestController
@RequestMapping("/api/brand")
public class BrandController extends BaseController {

    @Autowired
    private BrandService brandService;

    /**
     * 分页获取品牌
     */
    @ApiOperation(value = "分页获取品牌")
    @PostMapping("list")
    @IgnoreAuth
    public Object list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        //查询列表数据
        //Map params = new HashMap();
        //params.put("fields", "id, name, floor_price, app_list_pic_url");
        PageInfo<ShopBrand> pageInfo=brandService.queryByPage(size,page,null," id asc ");
        //
        return toResponsSuccess(pageInfo);
    }

    /**
     * 品牌详情
     */
    @ApiOperation(value = "品牌详情")
    @PostMapping("detail")
    @IgnoreAuth
    public Object detail(@RequestParam Integer id) {
        Map<String, Object> resultObj = new HashMap();
        //查询列表数据
        ShopBrand entity = brandService.getById(id);
        //
        resultObj.put("brand", entity);
        return toResponsSuccess(resultObj);
    }
}