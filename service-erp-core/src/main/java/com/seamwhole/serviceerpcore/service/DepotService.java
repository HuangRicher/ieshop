package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.mapper.vo.DepotEx;
import com.seamwhole.serviceerpcore.model.Depot;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface DepotService {


    Depot getDepot(long id);

    List<Depot> getDepot();

    List<Depot> getAllList() ;

    List<Depot> select(String name, Integer type, String remark, int offset, int rows) ;

    Long countDepot(String name, Integer type, String remark);

    int insertDepot(String beanJson, HttpServletRequest request) ;

    int updateDepot(String beanJson, Long id) ;

    int deleteDepot(Long id);

    int batchDeleteDepot(String ids);

    int checkIsNameExist(Long id, String name) ;

    List<Depot> findUserDepot();

    List<Depot> findGiftByType(Integer type);

    List<DepotEx> getDepotList(Map<String, Object> parameterMap) ;

    int batchDeleteDepotByIds(String ids);
    /**
     * description:
     *  正常删除，要考虑数据完整性，进行完整性校验
     * @Param: ids
     * @return int
     */
     int batchDeleteDepotByIdsNormal(String ids) throws Exception ;
}
