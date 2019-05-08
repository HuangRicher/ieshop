package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.App;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface AppService {


    List<App> findDock()throws Exception;
    /**
     * description:
     *  桌面功能菜单初始化列表
     * @Param: null
     * @return
     */
    List<App> findDesk()throws Exception;

    App getApp(long id)throws Exception;

    List<App> getApp()throws Exception;

    List<App> select(String name, String type, int offset, int rows) throws Exception;

    Long countApp(String name, String type)throws Exception;

    int insertApp(String beanJson, HttpServletRequest request) throws Exception;

    int updateApp(String beanJson, Long id) throws Exception;

    int deleteApp(Long id)throws Exception;

    int batchDeleteApp(String ids)throws Exception;

    List<App> findRoleAPP()throws Exception;

    List<App> findAppInIds(String ids, String type)throws Exception;


    int batchDeleteAppByIds(String ids)throws Exception;

    List<App> findAppByUserId(String userId)throws Exception;

    /**
     * 通过number列表查询app list
     * @param numberList
     * @return
     */
    List<App> findAppByNumber(List<String> numberList)throws Exception;
}
