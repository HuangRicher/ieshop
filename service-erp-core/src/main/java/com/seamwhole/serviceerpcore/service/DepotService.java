package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.mapper.vo.DepotEx;
import com.seamwhole.serviceerpcore.model.Depot;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface DepotService {


    Depot getDepot(long id)throws Exception;

    List<Depot> getDepot()throws Exception;

    List<Depot> getAllList()throws Exception ;

    List<Depot> select(String name, Integer type, String remark, int offset, int rows) throws Exception;

    Long countDepot(String name, Integer type, String remark)throws Exception;

    int insertDepot(String beanJson, HttpServletRequest request) throws Exception;

    int updateDepot(String beanJson, Long id)throws Exception ;

    int deleteDepot(Long id)throws Exception;

    int batchDeleteDepot(String ids)throws Exception;

    int checkIsNameExist(Long id, String name) throws Exception;

    List<Depot> findUserDepot()throws Exception;

    List<Depot> findGiftByType(Integer type)throws Exception;

    List<DepotEx> getDepotList(Map<String, Object> parameterMap) throws Exception;

    int batchDeleteDepotByIds(String ids)throws Exception;
    /**
     * description:
     *  正常删除，要考虑数据完整性，进行完整性校验
     * @Param: ids
     * @return int
     */
     int batchDeleteDepotByIdsNormal(String ids) throws Exception ;

    int updateDepotIsDefault(Boolean isDefault, Long depotID)throws Exception;
}
