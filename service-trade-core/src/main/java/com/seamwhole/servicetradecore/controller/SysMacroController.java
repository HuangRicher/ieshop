package com.seamwhole.servicetradecore.controller;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.controller.model.SysMacroModel;
import com.seamwhole.servicetradecore.model.SysMacro;
import com.seamwhole.servicetradecore.service.SysMacroService;
import com.seamwhole.servicetradecore.util.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用字典表Controller
 */
@RestController
@RequestMapping("/old/sys/macro")
public class SysMacroController {
    @Autowired
    private SysMacroService sysMacroService;

    /**
     * 所有字典列表
     */
    @RequestMapping("/list")
    public ResponseObject list(@RequestBody SysMacroModel macroModel) {
        //查询列表数据
        Map<String,Object> params=new HashMap<>();
        PageInfo<SysMacro> pageInfo=sysMacroService.queryByPage(params,macroModel.getPageNum(),macroModel.getPageSize());
        return ResponseObject.ok().put("page", pageInfo);
    }

    /**
     * 根据主键获取字典信息
     */
    @RequestMapping("/info/{macroId}")
    public ResponseObject info(@PathVariable("macroId") Long macroId) {
        SysMacro sysMacro = sysMacroService.queryObject(macroId);
        return ResponseObject.ok().put("macro", sysMacro);
    }

    /**
     * 新增字典
     *
     * @param sysMacro 字典
     * @return R
     */
    @RequestMapping("/save")
    public ResponseObject save(@RequestBody SysMacro sysMacro) {
        sysMacroService.save(sysMacro);
        return ResponseObject.ok();
    }

    /**
     * 修改字典
     */
    @RequestMapping("/update")
    public ResponseObject update(@RequestBody SysMacro sysMacro) {
        sysMacroService.update(sysMacro);
        return ResponseObject.ok();
    }

    /**
     * 删除字典
     */
    @RequestMapping("/delete")
    public ResponseObject delete(@RequestBody Long[] macroIds) {
        sysMacroService.deleteBatch(macroIds);
        return ResponseObject.ok();
    }

    /**
     * 查看字典列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {
        List<SysMacro> list = sysMacroService.queryList(params);
        return ResponseObject.ok().put("list", list);
    }

    /**
     * 查看字典目录列表
     */
    @RequestMapping("/queryAllParent")
    public ResponseObject queryAllParent(@RequestParam Map<String, Object> params) {
        List<SysMacro> list = sysMacroService.queryAllParent(params);
        return ResponseObject.ok().put("list", list);
    }

    /**
     * 根据value查询数据字典
     */
    @RequestMapping("/queryMacrosByValue")
    public ResponseObject queryMacrosByValue(@RequestParam String value) {
        List<SysMacro> list = sysMacroService.queryMacrosByValue(value);
        return ResponseObject.ok().put("list", list);
    }
}
