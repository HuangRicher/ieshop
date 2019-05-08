package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.InOutItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface InOutItemService {


    InOutItem getInOutItem(long id)throws Exception;

    List<InOutItem> getInOutItem()throws Exception;

    List<InOutItem> select(String name, String type, String remark, int offset, int rows)throws Exception;

    Long countInOutItem(String name, String type, String remark)throws Exception ;

    int insertInOutItem(String beanJson, HttpServletRequest request)throws Exception ;

    int updateInOutItem(String beanJson, Long id)throws Exception;

    int deleteInOutItem(Long id)throws Exception;

    int batchDeleteInOutItem(String ids)throws Exception;

    int checkIsNameExist(Long id, String name)throws Exception;

    List<InOutItem> findBySelect(String type)throws Exception;

    int batchDeleteInOutItemByIds(String ids)throws Exception ;

    /**
     *  正常删除，要考虑数据完整性，进行完整性校验
     * @Param: ids
     * @return int
     */
    int batchDeleteInOutItemByIdsNormal(String ids) throws Exception;
}
