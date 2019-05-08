package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.Unit;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface UnitService {


    Unit getUnit(long id)throws Exception;

    List<Unit> getUnit()throws Exception;

    List<Unit> select(String name, int offset, int rows)throws Exception;

    Long countUnit(String name)throws Exception;

    int insertUnit(String beanJson, HttpServletRequest request)throws Exception;

    int updateUnit(String beanJson, Long id)throws Exception;

    int deleteUnit(Long id)throws Exception;

    int batchDeleteUnit(String ids)throws Exception;

    int checkIsNameExist(Long id, String name)throws Exception;

    int batchDeleteUnitByIds(String ids)throws Exception;

    /**
     *  正常删除，要考虑数据完整性，进行完整性校验
     */
    int batchDeleteUnitByIdsNormal(String ids) throws Exception;
}
