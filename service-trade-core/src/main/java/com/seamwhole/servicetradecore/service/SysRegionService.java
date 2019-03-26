package com.seamwhole.servicetradecore.service;


import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.SysRegionDO;
import com.seamwhole.servicetradecore.model.SysRegion;

import java.util.List;
import java.util.Map;

/**
 * Service接口
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-11-04 11:19:31
 */
public interface SysRegionService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    SysRegion queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<SysRegionDO> queryList(Map<String, Object> map);


    PageInfo<SysRegionDO> queryByPage(Map<String, Object> map,Integer pageNum,Integer pageSize);


    /**
     * 保存实体
     *
     * @param region 实体
     * @return 保存条数
     */
    int save(SysRegion region);

    /**
     * 根据主键更新实体
     *
     * @param region 实体
     * @return 更新条数
     */
    int update(SysRegion region);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Integer[] ids);
}
