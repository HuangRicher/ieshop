package com.seamwhole.servicetradecore.controller;

import com.seamwhole.servicetradecore.annotation.LoginUser;
import com.seamwhole.servicetradecore.constant.RedisKeyConstant;
import com.seamwhole.servicetradecore.controller.model.BuyGoodsModel;
import com.seamwhole.servicetradecore.domain.BuyGoods;
import com.seamwhole.servicetradecore.model.ShopUser;
import com.seamwhole.servicetradecore.redis.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "商品购买")
@RestController
@RequestMapping("/api/buy")
public class BuyController extends BaseController {

    @Autowired
    private RedisService redisService;


    @ApiOperation(value = "商品添加")
    @PostMapping("/add")
    public Object addBuy(@LoginUser ShopUser user,@RequestBody BuyGoodsModel buyGoods) {

        BuyGoods goodsVo = new BuyGoods();
        goodsVo.setGoodsId(buyGoods.getGoodsId());
        goodsVo.setProductId(buyGoods.getProductId());
        goodsVo.setNumber(buyGoods.getNumber());
        redisService.set(user.getId()+ RedisKeyConstant.BUY_GOODS_CACHE,goodsVo);
        return toResponsMsgSuccess("添加成功");
    }
}
