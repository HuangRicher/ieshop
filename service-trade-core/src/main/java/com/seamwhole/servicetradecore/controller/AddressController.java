package com.seamwhole.servicetradecore.controller;

import com.seamwhole.servicetradecore.annotation.LoginUser;
import com.seamwhole.servicetradecore.controller.model.AddressModel;
import com.seamwhole.servicetradecore.domain.AddressInfo;
import com.seamwhole.servicetradecore.model.ShopAddress;
import com.seamwhole.servicetradecore.model.ShopUser;
import com.seamwhole.servicetradecore.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Api(tags = "收货地址")
@RestController
@RequestMapping("/api/address")
public class AddressController extends BaseController{

    @Autowired
    private AddressService addressService;

    /**
     * 获取用户的收货地址
     */
    @ApiOperation(value = "获取用户的收货地址接口", response = Map.class)
    @PostMapping("list")
    public Object list(@LoginUser ShopUser loginUser) {
        List<ShopAddress> addressEntities = addressService.queryListByUserId(loginUser.getId());
        List<AddressInfo> addressInfos=new ArrayList<>();
        for(ShopAddress address:addressEntities){
            AddressInfo info=new AddressInfo();
            BeanUtils.copyProperties(address,info);
            addressInfos.add(info);
        }
        return toResponsSuccess(addressInfos);
    }

    /**
     * 获取收货地址的详情
     */
    @ApiOperation(value = "获取收货地址的详情", response = Map.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "收获地址ID", required = true, dataType = "Integer")})
    @PostMapping("detail")
    public Object detail(Integer id, @LoginUser ShopUser loginUser) {
        ShopAddress entity = addressService.getById(id);
        //判断越权行为
        if (entity!=null && !entity.getUserId().equals(loginUser.getId())) {
            return toResponsObject(403, "您无权查看", "");
        }
        AddressInfo info=new AddressInfo();
        BeanUtils.copyProperties(entity,info);
        return toResponsSuccess(info);
    }

    /**
     * 添加或更新收货地址
     */
    @ApiOperation(value = "添加或更新收货地址", response = Map.class)
    @PostMapping("save")
    public Object save(@LoginUser ShopUser loginUser,@RequestBody AddressModel address) {
        ShopAddress entity = new ShopAddress();
        if (null != address) {
            entity.setId(address.getId());
            entity.setUserId(loginUser.getId());
            entity.setUserName(address.getUserName());
            entity.setPostalCode(address.getPostalCode());
            entity.setProvinceName(address.getProvinceName());
            entity.setCityName(address.getCityName());
            entity.setCountyName(address.getCountyName());
            entity.setDetailInfo(address.getDetailInfo());
            entity.setNationalCode(address.getNationalCode());
            entity.setTelNumber(address.getTelNumber());
            entity.setIsDefault(address.isDefault()?1:0);
        }
        if (null == entity.getId() || entity.getId() == 0) {
            entity.setId(null);
            addressService.save(entity);
        } else {
            addressService.updateById(entity);
        }
        return toResponsSuccess(entity);
    }

    /**
     * 删除指定的收货地址
     */
    @ApiOperation(value = "删除指定的收货地址", response = Map.class)
    @PostMapping("delete")
    public Object delete(@LoginUser ShopUser loginUser,Integer id) {

        ShopAddress entity = addressService.getById(id);
        //判断越权行为
        if (!entity.getUserId().equals(loginUser.getId())) {
            return toResponsObject(403, "您无权删除", "");
        }
        addressService.delete(id);
        return toResponsSuccess("");
    }
}