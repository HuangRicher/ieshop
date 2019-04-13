package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.mapper.vo.LogVo4List;
import com.seamwhole.serviceerpcore.model.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.List;



public interface LogService {


    Log getLog(long id);

    List<Log> getLog();

    List<LogVo4List> select(String operation, Integer usernameID, String clientIp, Integer status, String beginTime, String endTime,
                                   String contentdetails, int offset, int rows);

    Long countLog(String operation, Integer usernameID, String clientIp, Integer status, String beginTime, String endTime,
                        String contentdetails);

    int insertLog(String beanJson, HttpServletRequest request) ;

    int updateLog(String beanJson, Long id) ;

    int deleteLog(Long id);

    int batchDeleteLog(String ids);

    /**
     * 获取用户id
     * @param request
     * @return
     */
    Long getUserId(HttpServletRequest request);

    String getModule(String apiName);

    void insertLog(String apiName, String type, HttpServletRequest request);

}
