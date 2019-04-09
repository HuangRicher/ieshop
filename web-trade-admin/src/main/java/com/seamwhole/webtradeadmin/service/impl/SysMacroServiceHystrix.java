package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.SysMacro;
import com.seamwhole.webtradeadmin.service.SysMacroService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SysMacroServiceHystrix implements SysMacroService {
    @Override
    public PagesInfo<SysMacro> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public SysMacro queryObject(Long macroId) {
        return null;
    }

    @Override
    public void save(SysMacro sysMacro) {

    }

    @Override
    public void update(SysMacro sysMacro) {

    }

    @Override
    public void deleteBatch(Long[] macroIds) {

    }

    @Override
    public List<SysMacro> queryList(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<SysMacro> queryAllParent(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<SysMacro> queryMacrosByValue(String value) {
        return null;
    }
}
