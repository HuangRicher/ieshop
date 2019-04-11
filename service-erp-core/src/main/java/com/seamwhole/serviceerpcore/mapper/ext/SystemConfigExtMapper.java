package com.seamwhole.serviceerpcore.mapper.ext;

import com.seamwhole.serviceerpcore.model.SystemConfig;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SystemConfigExtMapper {

    List<SystemConfig> selectByConditionSystemConfig(
            @Param("companyName") String companyName,
            @Param("offset") Integer offset,
            @Param("rows") Integer rows);

    Long countsBySystemConfig(
            @Param("companyName") String companyName);

    int batchDeleteSystemConfigByIds(@Param("updateTime") Date updateTime, @Param("updater") Long updater, @Param("ids") String ids[]);
}