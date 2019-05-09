package com.seamwhole.serviceerpcore.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface CommonQueryManager {


    /**
     * 查询单条
     *
     * @param apiName 接口名称
     * @param id      ID
     */
    Object selectOne(String apiName, String id) throws Exception;

    /**
     * 查询
     * @param apiName
     * @param parameterMap
     * @return
     */
    List<?> select(String apiName, Map<String, String> parameterMap)throws Exception;

    /**
     * 计数
     * @param apiName
     * @param parameterMap
     * @return
     */
    Long counts(String apiName, Map<String, String> parameterMap)throws Exception;

    /**
     * 插入
     * @param apiName
     * @param beanJson
     * @return
     */
    int insert(String apiName, String beanJson, HttpServletRequest request) throws Exception;

    /**
     * 更新
     * @param apiName
     * @param beanJson
     * @param id
     * @return
     */
    int update(String apiName, String beanJson, Long id, HttpServletRequest request)throws Exception;

    /**
     * 删除
     * @param apiName
     * @param id
     * @return
     */
    int delete(String apiName, Long id, HttpServletRequest request)throws Exception;

    /**
     * 批量删除
     * @param apiName
     * @param ids
     * @return
     */
    int batchDelete(String apiName, String ids, HttpServletRequest request)throws Exception;

    /**
     * 判断是否存在
     * @param apiName
     * @param id
     * @param name
     * @return
     */
    int checkIsNameExist(String apiName, Long id, String name) throws Exception;


}