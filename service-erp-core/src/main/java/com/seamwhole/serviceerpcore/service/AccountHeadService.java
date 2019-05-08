package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.mapper.vo.AccountHeadVo4ListEx;
import com.seamwhole.serviceerpcore.model.AccountHead;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;


public interface AccountHeadService {


    AccountHead getAccountHead(long id)throws Exception;

    List<AccountHead> getAccountHead()throws Exception;

    List<AccountHeadVo4ListEx> select(String type, String billNo, String beginTime, String endTime, int offset, int rows)throws Exception;

    Long countAccountHead(String type, String billNo, String beginTime, String endTime)throws Exception;

    int insertAccountHead(String beanJson, HttpServletRequest request)throws Exception;

    int updateAccountHead(String beanJson, Long id)throws Exception;

    int deleteAccountHead(Long id)throws Exception;

    int batchDeleteAccountHead(String ids)throws Exception;

    int checkIsNameExist(Long id, String name) throws Exception;

    Long getMaxId()throws Exception;

    BigDecimal findAllMoney(Integer supplierId, String type, String mode, String endTime)throws Exception;

    List<AccountHeadVo4ListEx> getDetailByNumber(String billNo) throws Exception;

    int batchDeleteAccountHeadByIds(String ids)throws Exception;
    /**
     * description:
     *  正常删除，要考虑数据完整性，进行完整性校验
     * @Param: ids
     * @return int
     */
    int batchDeleteAccountHeadByIdsNormal(String ids) throws Exception ;
}
