package com.seamwhole.serviceerpcore.mapper.ext;

import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Description
 *
 * @Author: qiankunpingtai
 * @Date: 2019/3/29 15:09
 */
public interface UserBusinessExtMapper {
    int batchDeleteUserBusinessByIds(@Param("updateTime") Date updateTime, @Param("updater") Long updater, @Param("ids") String ids[]);
}
