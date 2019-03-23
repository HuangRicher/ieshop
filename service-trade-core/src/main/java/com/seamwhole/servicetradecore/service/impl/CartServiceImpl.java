package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.ShopCartMapper;
import com.seamwhole.servicetradecore.mapper.ext.ShopCartExtMapper;
import com.seamwhole.servicetradecore.mapper.model.ShopCartDO;
import com.seamwhole.servicetradecore.model.ShopCart;
import com.seamwhole.servicetradecore.model.ShopCartExample;
import com.seamwhole.servicetradecore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private ShopCartMapper shopCartMapper;

    @Autowired
    private ShopCartExtMapper shopCartExtMapper;


    public ShopCart queryObject(Integer id) {
        return shopCartMapper.selectByPrimaryKey(id);
    }


    public List<ShopCartDO> queryList(Integer userId, Integer goodsId, Integer productId, Boolean checked, String order) {

        List<ShopCartDO> cartInfoList = shopCartExtMapper.queryList(userId,goodsId,productId,checked,order);
        return cartInfoList;
    }


    public int queryTotal(Map<String, Object> map) {
        ShopCartExample example=new ShopCartExample();
        return shopCartMapper.countByExample(example);
    }


    public void save(ShopCart cart) {
        shopCartMapper.insertSelective(cart);
        // 更新购物车搭配减价
        // 判断购物车中是否存在此规格商品
        List<ShopCartDO> cartInfoList = shopCartExtMapper.queryList(cart.getUserId(),null,null,null,"");
        List<Integer> goodsIds = new ArrayList();
        List<ShopCart> cartUpdateList = new ArrayList();
        for (ShopCartDO cartItem : cartInfoList) {
            if (null != cartItem.getChecked() && 1 == cartItem.getChecked()) {
                goodsIds.add(cartItem.getGoodsId());
            }
            if (!cartItem.getRetailPrice().equals(cartItem.getRetailProductPrice())) {
                cartItem.setRetailPrice(cartItem.getRetailProductPrice());
                cartUpdateList.add(cartItem);
            }
        }

        for (ShopCart cartItem : cartInfoList) {
            // 存在原始的
            if (null != cartItem.getChecked() && 1 == cartItem.getChecked()) {
                for (ShopCart cartCrash : cartInfoList) {
                    if (!cartCrash.getId().equals(cartItem.getId())) {
                        cartUpdateList.add(cartItem);
                    }
                }
            }
        }
        if (null != cartUpdateList && cartUpdateList.size() > 0) {
            for (ShopCart cartItem : cartUpdateList) {
                shopCartMapper.updateByPrimaryKeySelective(cartItem);
            }
        }
    }

    public void update(ShopCart cart) {
        shopCartMapper.updateByPrimaryKeySelective(cart);
    }


    public void delete(Integer id) {
        shopCartMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        ShopCartExample example=new ShopCartExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        shopCartMapper.deleteByExample(example);
    }

    public void updateCheck(Integer[] productIds, Integer isChecked, Integer userId) {
        ShopCartExample example=new ShopCartExample();
        example.createCriteria().andProductIdIn(Arrays.asList(productIds)).andUserIdEqualTo(userId);
        ShopCart cart=new ShopCart();
        cart.setChecked(1);
        shopCartMapper.updateByExampleSelective(cart,example);

        // 判断购物车中是否存在此规格商品
        //List<ShopCart> cartInfoList = cartDao.queryList(cartParam);
        List<ShopCartDO> cartInfoList = shopCartExtMapper.queryList(userId,null,null,null,"");
        //Map crashParam = new HashMap();
        List<Integer> goods_ids = new ArrayList();
        List<ShopCart> cartUpdateList = new ArrayList();
        for (ShopCartDO cartItem : cartInfoList) {
            if (null != cartItem.getChecked() && 1 == cartItem.getChecked()) {
                goods_ids.add(cartItem.getGoodsId());
            }
            if (!cartItem.getRetailPrice().equals(cartItem.getRetailProductPrice())) {
                cartItem.setRetailPrice(cartItem.getRetailProductPrice());
                cartUpdateList.add(cartItem);
            }
        }
        if (null != goods_ids && goods_ids.size() > 0) {
            //crashParam.put("goods_ids", goods_ids);
            for (ShopCart cartItem : cartInfoList) {
                // 存在原始的
                if (null != cartItem.getChecked() && 1 == cartItem.getChecked()) {
                    for (ShopCart cartCrash : cartInfoList) {
                        if (null != cartItem.getChecked() && 1 == cartItem.getChecked() && !cartCrash.getId().equals(cartItem.getId())) {
                            cartUpdateList.add(cartCrash);
                            break;
                        }
                    }
                }
            }
        }
        if (null != cartUpdateList && cartUpdateList.size() > 0) {
            for (ShopCart cartItem : cartUpdateList) {
                shopCartMapper.updateByPrimaryKeySelective(cartItem);
            }
        }
    }

    public void deleteByProductIds(Integer[] productIds) {
        ShopCartExample example=new ShopCartExample();
        example.createCriteria().andProductIdIn(Arrays.asList(productIds));
        shopCartMapper.deleteByExample(example);
    }

    public void deleteByUserAndProductIds(Integer userId, Integer[] productIds) {
        ShopCartExample example=new ShopCartExample();
        example.createCriteria().andUserIdEqualTo(userId).andProductIdIn(Arrays.asList(productIds));
        shopCartMapper.deleteByExample(example);
    }

    public void deleteByCart(Integer userId, String sessionId, Integer checked) {
        ShopCartExample example=new ShopCartExample();
        example.createCriteria().andUserIdEqualTo(userId).andSessionIdEqualTo(sessionId).andCheckedEqualTo(1);
        shopCartMapper.deleteByExample(example);
    }

}
