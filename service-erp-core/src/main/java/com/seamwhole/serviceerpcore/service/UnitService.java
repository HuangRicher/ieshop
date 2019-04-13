package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.Unit;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface UnitService {


    Unit getUnit(long id);

    List<Unit> getUnit();

    List<Unit> select(String name, int offset, int rows);

    Long countUnit(String name);

    int insertUnit(String beanJson, HttpServletRequest request);

    int updateUnit(String beanJson, Long id);

    int deleteUnit(Long id);

    int batchDeleteUnit(String ids);

    int checkIsNameExist(Long id, String name);

    int batchDeleteUnitByIds(String ids);

    /**
     *  正常删除，要考虑数据完整性，进行完整性校验
     */
    int batchDeleteUnitByIdsNormal(String ids) throws Exception;
}
