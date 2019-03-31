package com.seamwhole.webtradeadmin.controller;


import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ShopUser;
import com.seamwhole.webtradeadmin.info.ShopUserDO;
import com.seamwhole.webtradeadmin.service.ShopUserService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import com.seamwhole.webtradeadmin.util.excel.ExcelExport;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("user")
public class ShopUserController {
    @Autowired
    private ShopUserService shopUserService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<ShopUserDO> page=shopUserService.queryShopUseByPage(params);
        return ResponseObject.ok().put("page", page);
    }


    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("user:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        ShopUser user = shopUserService.getShopUserById(id);
        return ResponseObject.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("user:save")
    public ResponseObject save(@RequestBody ShopUser user) {
        shopUserService.saveShopUser(user);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:update")
    public ResponseObject update(@RequestBody ShopUser user) {
        shopUserService.updateShopUserById(user);

        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        shopUserService.deleteShopUserBatch(ids);
        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {
        List<ShopUserDO> userList = shopUserService.queryShopUserList(params);
        return ResponseObject.ok().put("list", userList);
    }

    /**
     * 总计
     */
    @RequestMapping("/queryTotal")
    public ResponseObject queryTotal(@RequestParam Map<String, Object> params) {
        int sum = shopUserService.queryShopUserTotal(params);
        return ResponseObject.ok().put("userSum", sum);
    }

    /**
     * 导出会员
     */
    @RequestMapping("/export")
    @RequiresPermissions("user:export")
    public ResponseObject export(@RequestParam Map<String, Object> params, HttpServletResponse response) {

        List<ShopUserDO> userList = shopUserService.queryShopUserList(params);

        ExcelExport ee = new ExcelExport("会员列表");

        String[] header = new String[]{"会员名称", "性别", "会员级别", "手机号码"};

        List<Map<String, Object>> list = new ArrayList<>();

        if (userList != null && userList.size() != 0) {
            for (ShopUserDO userEntity : userList) {
                LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                map.put("USERNAME", userEntity.getUsername());
                map.put("GENDER", userEntity.getGender() == 1 ? "男" : (userEntity.getGender() == 2 ? "女" : "未知"));
                map.put("LEVEL_NAME", userEntity.getLevelName());
                map.put("MOBILE", userEntity.getMobile());
                list.add(map);
            }
        }
        ee.addSheetByMap("会员", list, header);
        ee.export(response);
        return ResponseObject.ok();
    }
}
