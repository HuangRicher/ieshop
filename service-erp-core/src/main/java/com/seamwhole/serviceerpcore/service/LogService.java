package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.mapper.vo.LogVo4List;
import com.seamwhole.serviceerpcore.model.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.List;



public interface LogService {


    Log getLog(long id)throws Exception;

    List<Log> getLog()throws Exception;

    List<LogVo4List> select(String operation, Integer usernameID, String clientIp, Integer status, String beginTime, String endTime,
                                   String contentdetails, int offset, int rows)throws Exception;

    Long countLog(String operation, Integer usernameID, String clientIp, Integer status, String beginTime, String endTime,
                        String contentdetails)throws Exception;

    int insertLog(String beanJson, HttpServletRequest request)throws Exception ;

    int updateLog(String beanJson, Long id)throws Exception ;

    int deleteLog(Long id)throws Exception;

    int batchDeleteLog(String ids)throws Exception;

    /**
     * 获取用户id
     * @param request
     * @return
     */
    Long getUserId(HttpServletRequest request)throws Exception;

    String getModule(String apiName)throws Exception;

    void insertLog(String apiName, String type, HttpServletRequest request)throws Exception;

}
