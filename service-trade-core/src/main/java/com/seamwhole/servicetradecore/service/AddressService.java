package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.AddressDO;
import com.seamwhole.servicetradecore.model.ShopAddress;

import java.util.List;
import java.util.Map;


public interface AddressService {

    ShopAddress getById(Integer id);

    List<ShopAddress> queryListByUserId(Integer userId);

    List<AddressDO> queryList(Map<String,Object> params);

    PageInfo<AddressDO> queryByPage(Map<String,Object> params,Integer pageNum,Integer pageSize);

    AddressDO queryObject(Integer id);

    int queryTotal(Map<String, Object> map);

    void save(ShopAddress address);

    void updateById(ShopAddress address);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

}
