package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.controller.model.SysUserModel;
import com.seamwhole.servicetradecore.domain.SysUserInfo;
import com.seamwhole.servicetradecore.mapper.model.SysUserDO;
import com.seamwhole.servicetradecore.model.SysUser;
import com.seamwhole.servicetradecore.service.SysUserRoleService;
import com.seamwhole.servicetradecore.service.SysUserService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sysUser")
public class SysUserResource {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;


    @GetMapping("/queryByUserName/{username}")
    public SysUser queryByUserName(@PathVariable("username") String username){
        return sysUserService.queryByUserName(username);
    }

    @GetMapping("/queryAllPerms/{userId}")
    List<String> queryAllPerms(@PathVariable("userId")Long userId){
        return sysUserService.queryAllPerms(userId);
    }

    @PostMapping("/updateSysUserStatus")
    public void updateSysUserStatus(@RequestBody SysUser user){
        sysUserService.updateSysUserStatus(user.getUserId(),user.getStatus());
    }

    @PostMapping("/queryUserByPage")
    public PagesInfo<SysUserDO> queryUserByPage(@RequestBody SysUserModel userModel){
        int pageNum=userModel.getPageNum();
        int pageSize=userModel.getPageSize();
        Map<String,Object> params=new HashMap<>();
        PageInfo<SysUserDO> data= sysUserService.queryByPage(params,pageNum,pageSize);
        return new PagesInfo<SysUserDO>(data.getPageNum(),data.getPageSize(),data.getTotal(),data.getPages(),data.getList());
    }

    @PostMapping("/updatePassword/{userId}/{password}/{newPassword}")
    public int updatePassword(@PathVariable("userId") Long userId,
                              @PathVariable("password") String password,
                              @PathVariable("newPassword") String newPassword){
        return sysUserService.updatePassword(userId,password,newPassword);
    }

    @GetMapping("/getUserInfoById/{userId}")
    public SysUserInfo getUserInfoById(@PathVariable("userId")Long userId){
        SysUser user = sysUserService.queryObject(userId);
        SysUserInfo info=new SysUserInfo();
        BeanUtils.copyProperties(user,info);
        //获取用户所属的角色列表
        List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        info.setRoleIdList(roleIdList);
        return info;
    }

    @PostMapping("/saveUser")
    public void saveUser(SysUserModel user){
        sysUserService.save(user);
    }

    @PostMapping("/updateUser")
    public void updateUser(SysUserModel user){
        sysUserService.update(user);
    }

    @PostMapping("/deleteUserBatch")
    public void deleteUserBatch(SysUserModel user){
        sysUserService.deleteBatch(user.getUserIds());
    }
}
