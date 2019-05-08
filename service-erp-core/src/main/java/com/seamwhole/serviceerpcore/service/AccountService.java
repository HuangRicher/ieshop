package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.mapper.vo.AccountVo4InOutList;
import com.seamwhole.serviceerpcore.mapper.vo.AccountVo4List;
import com.seamwhole.serviceerpcore.model.Account;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Service
public interface AccountService {


    Account getAccount(long id) throws Exception;

    List<Account> getAccount()  throws Exception;

    List<AccountVo4List> select(String name, String serialNo, String remark, int offset, int rows) throws Exception;

    Long countAccount(String name, String serialNo, String remark) throws Exception;

    int insertAccount(String beanJson, HttpServletRequest request) throws Exception;

    int updateAccount(String beanJson, Long id) throws Exception ;

    int deleteAccount(Long id) throws Exception;

    int batchDeleteAccount(String ids) throws Exception;

    int checkIsNameExist(Long id, String name) throws Exception ;

    List<Account> findBySelect() throws Exception;

    /**
     * 单个账户的金额求和-入库和出库
     *
     * @param id
     * @return
     */
    BigDecimal getAccountSum(Long id, String timeStr, String type) throws Exception;

    /**
     * 单个账户的金额求和-收入、支出、转账的单据表头的合计
     *
     * @param id
     * @return
     */
    BigDecimal getAccountSumByHead(Long id, String timeStr, String type) throws Exception;

    /**
     * 单个账户的金额求和-收款、付款、转账、收预付款的单据明细的合计
     *
     * @param id
     * @return
     */
    BigDecimal getAccountSumByDetail(Long id, String timeStr, String type)  throws Exception;

    /**
     * 单个账户的金额求和-多账户的明细合计
     *
     * @param id
     * @return
     */
    BigDecimal getManyAccountSum(Long id, String timeStr, String type)  throws Exception;

    List<AccountVo4InOutList> findAccountInOutList(Long accountId, Integer offset, Integer rows) throws Exception;

    int findAccountInOutListCount(Long accountId) throws Exception;

    int updateAmountIsDefault(Boolean isDefault, Long accountId)  throws Exception;

    int batchDeleteAccountByIds(String ids)  throws Exception;


    /**
     * description:
     *  正常删除，要考虑数据完整性，进行完整性校验
     * @Param: ids
     * @return int
     */
    int batchDeleteAccountByIdsNormal(String ids) throws Exception;
}
