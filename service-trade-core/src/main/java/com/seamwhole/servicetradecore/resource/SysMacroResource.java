package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.SysMacro;
import com.seamwhole.servicetradecore.service.SysMacroService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sysMacro")
public class SysMacroResource {

    @Autowired
    private SysMacroService sysMacroService;


    @PostMapping("/queryByPage")
    public PagesInfo<SysMacro> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<SysMacro> page=sysMacroService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<SysMacro>(page.getPageNum(),page.getPageSize(),page.getTotal(),page.getPages(),page.getList());
    }

    @PostMapping("/queryObject")
    public SysMacro queryObject(@PathVariable("macroId")Long macroId){
        return sysMacroService.queryObject(macroId);
    }

    @PostMapping("/save")
    public void save(@RequestBody SysMacro sysMacro){
        sysMacroService.save(sysMacro);
    }

    @PostMapping("/update")
    public void update(@RequestBody SysMacro sysMacro){
        sysMacroService.update(sysMacro);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Long[] macroIds){
        sysMacroService.deleteBatch(macroIds);
    }

    @PostMapping("/queryList")
    public List<SysMacro> queryList(@RequestBody Map<String, Object> params){
        return sysMacroService.queryList(params);
    }

    @PostMapping("/queryAllParent")
    public List<SysMacro> queryAllParent(@RequestBody Map<String, Object> params){
        return sysMacroService.queryAllParent(params);
    }

    @PostMapping("/queryMacrosByValue/{value}")
    public List<SysMacro> queryMacrosByValue(@PathVariable("value") String value){
        return sysMacroService.queryMacrosByValue(value);
    }
}
