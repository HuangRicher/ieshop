package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.mapper.vo.AccountVo4InOutList;
import com.seamwhole.serviceerpcore.mapper.vo.AccountVo4List;
import com.seamwhole.serviceerpcore.model.Account;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account getAccount(long id);

    List<Account> getAccount();

    List<AccountVo4List> select(String name, String serialNo, String remark, int offset, int rows);

    Long countAccount(String name, String serialNo, String remark);

    int insertAccount(String beanJson, HttpServletRequest request) ;

    int updateAccount(String beanJson, Long id) ;

    int deleteAccount(Long id);

    int batchDeleteAccount(String ids) ;

    int checkIsNameExist(Long id, String name) ;

    List<Account> findBySelect();

    /**
     * 单个账户的金额求和-入库和出库
     *
     * @param id
     * @return
     */
    BigDecimal getAccountSum(Long id, String timeStr, String type);

    /**
     * 单个账户的金额求和-收入、支出、转账的单据表头的合计
     *
     * @param id
     * @return
     */
    BigDecimal getAccountSumByHead(Long id, String timeStr, String type);

    /**
     * 单个账户的金额求和-收款、付款、转账、收预付款的单据明细的合计
     *
     * @param id
     * @return
     */
    BigDecimal getAccountSumByDetail(Long id, String timeStr, String type);

    /**
     * 单个账户的金额求和-多账户的明细合计
     *
     * @param id
     * @return
     */
    BigDecimal getManyAccountSum(Long id, String timeStr, String type);

    List<AccountVo4InOutList> findAccountInOutList(Long accountId, Integer offset, Integer rows) ;


    int findAccountInOutListCount(Long accountId);

    int updateAmountIsDefault(Boolean isDefault, Long accountId) ;

    int batchDeleteAccountByIds(String ids);
    /**
     * description:
     *  正常删除，要考虑数据完整性，进行完整性校验
     * @Param: ids
     * @return int
     */
    int batchDeleteAccountByIdsNormal(String ids) throws Exception;
}
