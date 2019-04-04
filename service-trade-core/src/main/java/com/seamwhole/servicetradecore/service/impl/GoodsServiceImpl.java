package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.except.CheckException;
import com.seamwhole.servicetradecore.domain.GoodsModel;
import com.seamwhole.servicetradecore.mapper.GoodsMapper;
import com.seamwhole.servicetradecore.mapper.ext.GoodsExtMapper;
import com.seamwhole.servicetradecore.mapper.model.GoodsDO;
import com.seamwhole.servicetradecore.mapper.model.ShopGoodsDO;
import com.seamwhole.servicetradecore.model.*;
import com.seamwhole.servicetradecore.service.GoodsAttributeService;
import com.seamwhole.servicetradecore.service.GoodsGalleryService;
import com.seamwhole.servicetradecore.service.GoodsService;
import com.seamwhole.servicetradecore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsExtMapper goodsExtMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private GoodsAttributeService goodsAttributeService;

    @Autowired
    private GoodsGalleryService goodsGalleryService;




    public Goods queryObject(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }


    public List<GoodsDO> queryList(Map<String, Object> map) {
        return goodsExtMapper.queryList(map);
    }

    @Override
    public PageInfo<GoodsDO> queryByPage(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        Page<GoodsDO> page=PageHelper.startPage(pageNum,pageSize);
        goodsExtMapper.queryList(map);
        return page.toPageInfo();
    }

    public int queryTotal(Map<String, Object> map) {
        return goodsExtMapper.queryTotal(map);
    }


    public void save(Goods goods) {
        goodsMapper.insertSelective(goods);
    }


    public void update(Goods goods) {
        goodsMapper.updateByPrimaryKeySelective(goods);
    }


    public void delete(Integer id) {
        goodsMapper.deleteByPrimaryKey(id);
    }


    public void deleteBatch(Integer[] ids) {
        GoodsExample example=new GoodsExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        goodsMapper.deleteByExample(example);
    }

    public List<GoodsDO> queryHotGoodsList(Map<String, Object> map) {
        return goodsExtMapper.queryHotGoodsList(map);
    }

    public List<GoodsDO> queryCatalogProductList(Map<String, Object> map) {
        return goodsExtMapper.queryCatalogProductList(map);
    }

    @Override
    public PageInfo<GoodsDO> queryCatalogProductByPage(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        Page<GoodsDO> page= PageHelper.startPage(pageNum,pageSize);
        goodsExtMapper.queryCatalogProductList(map);
        return page.toPageInfo();
    }

    @Override
    public PageInfo<ShopGoodsDO> queryShopByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<ShopGoodsDO> page=PageHelper.startPage(pageNum,pageSize);
        goodsExtMapper.queryShopGoodsList(params);
        return page.toPageInfo();
    }

    @Override
    public List<ShopGoodsDO> queryShopList(Map<String, Object> params) {
        return goodsExtMapper.queryShopGoodsList(params);
    }

    @Override
    @Transactional
    public int back(Integer[] ids, Long userId) {
        int result = 0;
        for (Integer id : ids) {
            Goods goodsEntity = queryObject(id);
            goodsEntity.setIsDelete(0);
            goodsEntity.setIsOnSale(1);
            goodsEntity.setUpdateUserId(userId);
            goodsEntity.setUpdateTime(new Date());
            result += goodsMapper.updateByPrimaryKeySelective(goodsEntity);
        }
        return result;
    }

    @Override
    public int queryShopToTal(Map<String, Object> params) {
        return goodsExtMapper.queryShopTotal(params);
    }

    @Override
    public int enSale(Integer id, Long userId) {
        Goods goodsEntity = queryObject(id);
        if (1 == goodsEntity.getIsOnSale()) {
            throw new CheckException("此商品已处于上架状态！");
        }
        goodsEntity.setIsOnSale(1);
        goodsEntity.setUpdateUserId(userId);
        goodsEntity.setUpdateTime(new Date());
        return goodsMapper.updateByPrimaryKeySelective(goodsEntity);
    }

    @Override
    public int unSale(Integer id, Long userId) {
        Goods goodsEntity = queryObject(id);
        if (0 == goodsEntity.getIsOnSale()) {
            throw new CheckException("此商品已处于下架状态！");
        }
        goodsEntity.setIsOnSale(0);
        goodsEntity.setUpdateUserId(userId);
        goodsEntity.setUpdateTime(new Date());
        return goodsMapper.updateByPrimaryKeySelective(goodsEntity);
    }

    @Override
    @Transactional
    public int deleteShopBatch(Integer[] ids, Long userId) {
        int result = 0;
        for (Integer id : ids) {
            result += deleteShopGoods(id, userId);
        }
        return result;
    }

    @Override
    public int deleteShopGoods(Integer id, Long userId) {
        Goods goodsEntity = queryObject(id);
        goodsEntity.setIsDelete(1);
        goodsEntity.setIsOnSale(0);
        goodsEntity.setUpdateUserId(userId);
        goodsEntity.setUpdateTime(new Date());
        return goodsMapper.updateByPrimaryKeySelective(goodsEntity);
    }

    @Override
    public int saveShopGoods(GoodsModel goods, Long userId, Long deptId) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", goods.getName());
        List<ShopGoodsDO> list = queryShopList(map);
        if (null != list && list.size() != 0) {
            throw new CheckException("商品名称已存在！");
        }
        Integer id = goodsExtMapper.queryShopMaxId() + 1;
        goods.setId(id);

        //保存产品信息
        Product productEntity = new Product();
        productEntity.setGoodsId(id);
        productEntity.setGoodsSn(goods.getGoodsSn());
        productEntity.setGoodsNumber(goods.getGoodsNumber());
        productEntity.setRetailPrice(goods.getRetailPrice());
        productEntity.setMarketPrice(goods.getMarketPrice());
        productEntity.setGoodsSpecificationIds("");
        productService.save(productEntity);

        goods.setAddTime(new Date());
        goods.setPrimaryProductId(productEntity.getId());

        //保存商品详情页面显示的属性
        List<GoodsAttribute> attributeEntityList = goods.getAttributeEntityList();
        if (null != attributeEntityList && attributeEntityList.size() > 0) {
            for (GoodsAttribute item : attributeEntityList) {
                item.setGoodsId(id);
                goodsAttributeService.save(item);
            }
        }

        //商品轮播图
        List<GoodsGallery> galleryEntityList = goods.getGoodsImgList();
        if (null != galleryEntityList && galleryEntityList.size() > 0) {
            for (GoodsGallery galleryEntity : galleryEntityList) {
                galleryEntity.setGoodsId(id);
                goodsGalleryService.save(galleryEntity);
            }
        }

        goods.setIsDelete(0);
        goods.setCreateUserDeptId(deptId);
        goods.setCreateUserId(userId);
        goods.setUpdateUserId(userId);
        goods.setUpdateTime(new Date());
        return goodsMapper.insertSelective(goods);
    }
}
