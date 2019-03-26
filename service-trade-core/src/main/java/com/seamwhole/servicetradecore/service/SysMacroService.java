package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.SysMacro;

import java.util.List;
import java.util.Map;

/**
 * 通用字典表Service接口
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-22 11:48:16
 */
public interface SysMacroService {

    /**
     * 根据主键查询实体
     *
     * @param macroId 主键
     * @return 实体
     */
    SysMacro queryObject(Long macroId);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<SysMacro> queryList(Map<String, Object> map);


    PageInfo<SysMacro> queryByPage(Map<String, Object> map,Integer pageNum,Integer pageSize);


    /**
     * 保存实体
     *
     * @param sysMacro 实体
     * @return 保存条数
     */
    int save(SysMacro sysMacro);

    /**
     * 根据主键更新实体
     *
     * @param sysMacro 实体
     * @return 更新条数
     */
    int update(SysMacro sysMacro);

    /**
     * 根据主键删除
     *
     * @param macroId
     * @return 删除条数
     */
    int delete(Long macroId);

    /**
     * 根据主键批量删除
     *
     * @param macroIds
     * @return 删除条数
     */
    int deleteBatch(Long[] macroIds);

    List<SysMacro> queryMacrosByValue(String value);

    /**
     * 查看字典目录列表
     *
     * @param map
     * @return
     */
    List<SysMacro> queryAllParent(Map<String, Object> map);
}
