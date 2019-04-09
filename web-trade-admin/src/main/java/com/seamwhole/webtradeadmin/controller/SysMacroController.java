package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.SysMacro;
import com.seamwhole.webtradeadmin.service.SysMacroService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 通用字典表Controller
 */
@RestController
@RequestMapping("sys/macro")
public class SysMacroController {
    @Autowired
    private SysMacroService sysMacroService;

    /**
     * 所有字典列表
     * @param params 请求参数
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:macro:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<SysMacro> page=sysMacroService.queryByPage(params);

        return ResponseObject.ok().put("page", page);
    }

    /**
     * 根据主键获取字典信息
     * @param macroId 主键
     */
    @RequestMapping("/info/{macroId}")
    @RequiresPermissions("sys:macro:info")
    public ResponseObject info(@PathVariable("macroId") Long macroId) {
        SysMacro sysMacro = sysMacroService.queryObject(macroId);

        return ResponseObject.ok().put("macro", sysMacro);
    }

    /**
     * 新增字典
     * @param sysMacro 字典
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:macro:save")
    public ResponseObject save(@RequestBody SysMacro sysMacro) {
        sysMacroService.save(sysMacro);

        return ResponseObject.ok();
    }

    /**
     * 修改字典
     * @param sysMacro 字典
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:macro:update")
    public ResponseObject update(@RequestBody SysMacro sysMacro) {
        sysMacroService.update(sysMacro);

        return ResponseObject.ok();
    }

    /**
     * 删除字典
     * @param macroIds 主键集
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:macro:delete")
    public ResponseObject delete(@RequestBody Long[] macroIds) {
        sysMacroService.deleteBatch(macroIds);

        return ResponseObject.ok();
    }

    /**
     * 查看字典列表
     * @param params 请求参数
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {

        List<SysMacro> list = sysMacroService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }

    /**
     * 查看字典目录列表
     * @param params 请求参数
     */
    @RequestMapping("/queryAllParent")
    public ResponseObject queryAllParent(@RequestParam Map<String, Object> params) {

        List<SysMacro> list = sysMacroService.queryAllParent(params);

        return ResponseObject.ok().put("list", list);
    }

    /**
     * 根据value查询数据字典
     * @param value value
     */
    @RequestMapping("/queryMacrosByValue")
    public ResponseObject queryMacrosByValue(@RequestParam String value) {

        List<SysMacro> list = sysMacroService.queryMacrosByValue(value);

        return ResponseObject.ok().put("list", list);
    }
}
