package com.seamwhole.servicetradecore.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.constant.Constant;
import com.seamwhole.servicetradecore.domain.SysMenuInfo;
import com.seamwhole.servicetradecore.mapper.SysMenuMapper;
import com.seamwhole.servicetradecore.mapper.ext.SysMenuExtMapper;
import com.seamwhole.servicetradecore.mapper.model.SysMenuDO;
import com.seamwhole.servicetradecore.model.SysMenu;
import com.seamwhole.servicetradecore.model.SysMenuExample;
import com.seamwhole.servicetradecore.service.SysMenuService;
import com.seamwhole.servicetradecore.service.SysRoleMenuService;
import com.seamwhole.servicetradecore.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Autowired
	private SysMenuExtMapper sysMenuExtMapper;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	@Override
	public List<SysMenuInfo> queryListParentId(Long parentId, List<Long> menuIdList) {
		SysMenuExample example=new SysMenuExample();
		example.createCriteria().andParentIdEqualTo(parentId).andStatusEqualTo(0);
		example.setOrderByClause(" order_num asc ");
		List<SysMenu> menuList = sysMenuMapper.selectByExample(example);
		if(menuIdList == null){
			List<SysMenuInfo> list=new ArrayList<>();
			for(SysMenu menu:menuList){
				SysMenuInfo info=new SysMenuInfo();
				BeanUtils.copyProperties(menu,info);
				list.add(info);
			}
			return list;
		}
		List<SysMenuInfo> userMenuList = new ArrayList<>();
		for(SysMenu menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				SysMenuInfo info=new SysMenuInfo();
				BeanUtils.copyProperties(menu,info);
				userMenuList.add(info);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenu> queryNotButtonList() {
		SysMenuExample example=new SysMenuExample();
		example.createCriteria().andTypeNotEqualTo(2).andStatusEqualTo(0);
		example.setOrderByClause(" order_num asc ");
		return sysMenuMapper.selectByExample(example);
	}

	@Override
	public List<SysMenuInfo> getUserMenuList(Long userId) {
		//系统管理员，拥有最高权限
		if(userId == 1){
			return getAllMenuList(null);
		}
		
		//用户菜单列表
		List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
		return getAllMenuList(menuIdList);
	}
	
	@Override
	public SysMenu queryObject(Long menuId) {
		return sysMenuMapper.selectByPrimaryKey(menuId);
	}

	@Override
	public List<SysMenuDO> queryList(Map<String, Object> map) {
		return sysMenuExtMapper.queryList(map);
	}

	@Override
	public PageInfo<SysMenuDO> queryByPage(Map<String, Object> map, Integer pageNum, Integer pageSize) {
		Page<SysMenuDO> page= PageHelper.startPage(pageNum,pageSize);
		sysMenuExtMapper.queryList(map);
		return page.toPageInfo();
	}

	@Override
	public void save(SysMenu menu) {
		sysMenuMapper.insertSelective(menu);
	}

	@Override
	public void update(SysMenu menu) {
		sysMenuMapper.updateByPrimaryKey(menu);
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] menuIds) {
		sysMenuExtMapper.deleteBatch(menuIds);
	}
	
	@Override
	public List<SysMenuDO> queryUserList(Long userId) {
		return sysMenuExtMapper.queryUserList(userId);
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenuInfo> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表
		List<SysMenuInfo> menuList = queryListParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		
		return menuList;
	}

	/**
	 * 递归
	 */
	private List<SysMenuInfo> getMenuTreeList(List<SysMenuInfo> menuList, List<Long> menuIdList){
		List<SysMenuInfo> subMenuList = new ArrayList<>();
		
		for(SysMenuInfo entity : menuList){
			if(entity.getType() == Constant.MenuType.CATALOG.getValue()){//目录
				entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		return subMenuList;
	}
}
