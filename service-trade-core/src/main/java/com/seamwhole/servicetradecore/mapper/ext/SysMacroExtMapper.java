package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.model.SysMacro;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMacroExtMapper {

    /**
     * 查询数据字段
     *
     * @param value
     * @return
     */
    List<SysMacro> queryMacrosByValue(@Param("value") String value);
}
