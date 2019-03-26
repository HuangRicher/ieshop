package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.domain.SysMenuInfo;
import com.seamwhole.servicetradecore.mapper.model.SysMenuDO;
import com.seamwhole.servicetradecore.model.SysMenu;

import java.util.List;
import java.util.Map;


/**
 * 菜单管理
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年9月18日 上午9:42:16
 */
public interface SysMenuService {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId   父菜单ID
     * @param menuIdList 用户菜单ID
     */
    List<SysMenuInfo> queryListParentId(Long parentId, List<Long> menuIdList);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenu> queryNotButtonList();

    /**
     * 获取用户菜单列表
     */
    List<SysMenuInfo> getUserMenuList(Long userId);


    /**
     * 查询菜单
     */
    SysMenu queryObject(Long menuId);

    /**
     * 查询菜单列表
     */
    List<SysMenuDO> queryList(Map<String, Object> map);

    PageInfo<SysMenuDO> queryByPage(Map<String, Object> map, Integer pageNum, Integer pageSize);


    /**
     * 保存菜单
     */
    void save(SysMenu menu);

    /**
     * 修改
     */
    void update(SysMenu menu);

    /**
     * 删除
     */
    void deleteBatch(Long[] menuIds);

    /**
     * 查询用户的权限列表
     */
    List<SysMenuDO> queryUserList(Long userId);
}
