package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.ProductMapper;
import com.seamwhole.servicetradecore.mapper.ext.ProductExtMapper;
import com.seamwhole.servicetradecore.mapper.model.ProductDO;
import com.seamwhole.servicetradecore.model.GoodsSpecification;
import com.seamwhole.servicetradecore.model.Product;
import com.seamwhole.servicetradecore.model.ProductExample;
import com.seamwhole.servicetradecore.service.GoodsSpecificationService;
import com.seamwhole.servicetradecore.service.ProductService;
import com.seamwhole.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductExtMapper productExtMapper;
    @Autowired
    private GoodsSpecificationService goodsSpecificationService;




    public ProductDO queryObject(Integer id) {
        return productExtMapper.queryObject(id);
    }


    public List<ProductDO> queryList(Map<String, Object> map) {
        List<ProductDO> list=productExtMapper.queryList(map);
        List<ProductDO> result = new ArrayList<>();
        //翻译产品规格
        if (null != list && list.size() > 0) {
            for (ProductDO item : list) {
                String specificationIds = item.getGoodsSpecificationIds();
                String specificationValue = "";
                if (!StringUtils.isNullOrEmpty(specificationIds)) {
                    String[] arr = specificationIds.split("_");

                    for (String goodsSpecificationId : arr) {
                        GoodsSpecification entity = goodsSpecificationService.queryObject(Integer.valueOf(goodsSpecificationId));
                        if (null != entity) {
                            specificationValue += entity.getValue() + "；";
                        }
                    }
                }
                item.setSpecificationValue(item.getGoodsName() + " " + specificationValue);
                result.add(item);
            }
        }
        return result;

    }



    public void save(Product goods) {
        productMapper.insertSelective(goods);
    }

    @Override
    public int saveShop(Product product) {
        int result = 0;
        String goodsSpecificationIds = product.getGoodsSpecificationIds();
        if (!StringUtils.isNullOrEmpty(goodsSpecificationIds)) {
            String[] goodsSpecificationIdArr = goodsSpecificationIds.split("_");
            for (int i = 0; i < goodsSpecificationIdArr.length - 1; i++) {
                String[] oneId = goodsSpecificationIdArr[i].split(",");
                String[] twoId = goodsSpecificationIdArr[i + 1].split(",");
                for (int j = 0; j < oneId.length; j++) {
                    for (int k = 0; k < twoId.length; k++) {
                        String strGoodsSpecificationIds = null;
                        if (StringUtils.isNullOrEmpty(oneId[j]) || StringUtils.isNullOrEmpty(twoId[k])){
                            continue;
                        }
                        strGoodsSpecificationIds = oneId[j] + "_" + twoId[k];
                        product.setGoodsSpecificationIds(strGoodsSpecificationIds);
                        Product entity = new Product();
                        BeanUtils.copyProperties(product, entity);
                        result += productMapper.insertSelective(entity);
                    }
                }
            }
        }
        return result;
    }

    public void update(Product goods) {
        productMapper.updateByPrimaryKeySelective(goods);
    }


    public void delete(Integer id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ProductDO> queryShopProductList(Map<String, Object> params) {
        return productExtMapper.queryShopProductList(params);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        ProductExample example=new ProductExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        productMapper.deleteByExample(example);
    }

    @Override
    public void updateById(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public Product getById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<ProductDO> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        Page<ProductDO> page= PageHelper.startPage(pageNum,pageSize);
        List<ProductDO> list=productExtMapper.queryShopProductList(params);
        List<ProductDO> result = new ArrayList<>();
        //翻译产品规格
        if (null != list && list.size() > 0) {
            for (ProductDO item : list) {
                String specificationIds = item.getGoodsSpecificationIds();
                String specificationValue = "";
                if (!StringUtils.isNullOrEmpty(specificationIds)) {
                    String[] arr = specificationIds.split("_");

                    for (String goodsSpecificationId : arr) {
                        GoodsSpecification entity = goodsSpecificationService.queryObject(Integer.valueOf(goodsSpecificationId));
                        if (null != entity) {
                            specificationValue += entity.getValue() + "；";
                        }
                    }
                }
                item.setSpecificationValue(item.getGoodsName() + " " + specificationValue);
                result.add(item);
            }
        }
        PageInfo<ProductDO> pageInfo=page.toPageInfo();
        pageInfo.setList(result);
        return pageInfo;
    }
}
