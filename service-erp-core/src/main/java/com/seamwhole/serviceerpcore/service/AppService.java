package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.App;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface AppService {


    List<App> findDock();
    /**
     * description:
     *  桌面功能菜单初始化列表
     * @Param: null
     * @return
     */
    List<App> findDesk();

    App getApp(long id);

    List<App> getApp();

    List<App> select(String name, String type, int offset, int rows) ;

    Long countApp(String name, String type);

    int insertApp(String beanJson, HttpServletRequest request) ;

    int updateApp(String beanJson, Long id) ;

    int deleteApp(Long id);

    int batchDeleteApp(String ids);

    List<App> findRoleAPP();

    List<App> findAppInIds(String ids, String type);


    int batchDeleteAppByIds(String ids);

    List<App> findAppByUserId(String userId);

    /**
     * 通过number列表查询app list
     * @param numberList
     * @return
     */
    List<App> findAppByNumber(List<String> numberList);
}
