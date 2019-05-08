package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.SystemConfig;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface SystemConfigService {


    SystemConfig getSystemConfig(long id)throws Exception;

    List<SystemConfig> getSystemConfig()throws Exception;
    
    List<SystemConfig> select(String companyName, int offset, int rows)throws Exception;

    Long countSystemConfig(String companyName)throws Exception;

    int insertSystemConfig(String beanJson, HttpServletRequest request)throws Exception ;

    int updateSystemConfig(String beanJson, Long id)throws Exception;

    int deleteSystemConfig(Long id)throws Exception;

    int batchDeleteSystemConfig(String ids)throws Exception;

    int checkIsNameExist(Long id, String name)throws Exception ;
    
    int batchDeleteSystemConfigByIds(String ids)throws Exception;
}
