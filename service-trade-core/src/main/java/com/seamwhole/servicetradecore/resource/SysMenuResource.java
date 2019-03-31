package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.domain.SysMenuInfo;
import com.seamwhole.servicetradecore.mapper.model.SysMenuDO;
import com.seamwhole.servicetradecore.model.SysMenu;
import com.seamwhole.servicetradecore.service.SysMenuService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sysMenu")
public class SysMenuResource {

    @Autowired
    private SysMenuService sysMenuService;


    @GetMapping("/queryList")
    public List<SysMenuDO> queryList(){
        return sysMenuService.queryList(new HashMap<String, Object>());
    }

    @PostMapping("/queryMenusByPage")
    public PagesInfo<SysMenuDO> queryMenusByPage(@RequestBody Map<String, Object> params){
        int pageNum=(int)params.get("pageNum");
        int pageSize=(int)params.get("pageSize");
        PageInfo<SysMenuDO> pageInfo=sysMenuService.queryByPage(params,pageNum,pageSize);
        return new PagesInfo<SysMenuDO>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
    }

    @PostMapping("/queryMenuList")
    public List<SysMenuDO> queryMenuList(@RequestBody Map<String, Object> params){
        return sysMenuService.queryList(params);
    }


    @GetMapping("/queryNotButtonMenuList")
    public List<SysMenuInfo> queryNotButtonMenuList(){
        //查询列表数据
        List<SysMenu> menuList = sysMenuService.queryNotButtonList();
        List<SysMenuInfo> list=new ArrayList<>();
        for(SysMenu menu:menuList){
            SysMenuInfo info=new SysMenuInfo();
            BeanUtils.copyProperties(menu,info);
            list.add(info);
        }

        //添加顶级菜单
        SysMenuInfo root = new SysMenuInfo();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        list.add(root);
        return list;
    }
    @GetMapping("/getMenuById/{menuId}")
    public SysMenu getMenuById(@PathVariable("menuId") Long menuId){
        return sysMenuService.queryObject(menuId);
    }

    @PostMapping("/saveMenu")
    public void saveMenu(SysMenu menu){
        sysMenuService.save(menu);
    }

    @PostMapping("/updateMenuById")
    public void updateMenuById(SysMenu menu){
        sysMenuService.update(menu);
    }

    @PostMapping("/deleteMenuBatch")
    public void deleteMenuBatch(Long[] menuIds){
        sysMenuService.deleteBatch(menuIds);
    }

    @GetMapping("/getUserMenuList/{userId}")
    public List<SysMenuInfo> getUserMenuList(@PathVariable("userId") Long userId){
        return sysMenuService.getUserMenuList(userId);
    }

}
