package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.InOutItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface InOutItemService {


    InOutItem getInOutItem(long id);

    List<InOutItem> getInOutItem();

    List<InOutItem> select(String name, String type, String remark, int offset, int rows);

    Long countInOutItem(String name, String type, String remark) ;

    int insertInOutItem(String beanJson, HttpServletRequest request) ;

    int updateInOutItem(String beanJson, Long id);

    int deleteInOutItem(Long id);

    int batchDeleteInOutItem(String ids);

    int checkIsNameExist(Long id, String name);

    List<InOutItem> findBySelect(String type);

    int batchDeleteInOutItemByIds(String ids) ;

    /**
     * create by: qiankunpingtai
     * website：https://qiankunpingtai.cn
     * description:
     *  正常删除，要考虑数据完整性，进行完整性校验
     * create time: 2019/4/10 16:23
     * @Param: ids
     * @return int
     */
    int batchDeleteInOutItemByIdsNormal(String ids) throws Exception;
}
