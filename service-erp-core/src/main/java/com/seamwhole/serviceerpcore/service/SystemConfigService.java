package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.SystemConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface SystemConfigService {


    SystemConfig getSystemConfig(long id);

    List<SystemConfig> getSystemConfig();
    
    List<SystemConfig> select(String companyName, int offset, int rows);

    Long countSystemConfig(String companyName);

    int insertSystemConfig(String beanJson, HttpServletRequest request) ;

    int updateSystemConfig(String beanJson, Long id);

    int deleteSystemConfig(Long id);

    int batchDeleteSystemConfig(String ids);

    int checkIsNameExist(Long id, String name) ;
    
    int batchDeleteSystemConfigByIds(String ids);
}
