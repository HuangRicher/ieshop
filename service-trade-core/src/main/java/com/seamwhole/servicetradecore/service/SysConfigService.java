package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.SysConfig;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息
 */
public interface SysConfigService {

    /**
     * 保存配置信息
     */
    void save(SysConfig config);

    /**
     * 更新配置信息
     */
    void update(SysConfig config);

    /**
     * 根据key，更新value
     */
    void updateValueByKey(String key, String value);

    /**
     * 删除配置信息
     */
    void deleteBatch(Long[] ids);

    /**
     * 获取List列表
     */
    List<SysConfig> queryList(Map<String, Object> map);

    PageInfo<SysConfig> queryByPage(Map<String, Object> map,Integer pageNum,Integer pageSize);

    SysConfig queryObject(Long id);

    /**
     * 根据key，获取配置的value值
     *
     * @param key          key
     * @param defaultValue 缺省值
     */
    String getValue(String key, String defaultValue);

    /**
     * 根据key，获取value的Object对象
     *
     * @param key   key
     * @param clazz Object对象
     */
    <T> T getConfigObject(String key, Class<T> clazz);

}
