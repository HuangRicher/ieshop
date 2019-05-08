package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.mapper.vo.AccountItemVo4List;
import com.seamwhole.serviceerpcore.model.AccountItem;
import org.springframework.dao.DataAccessException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface AccountItemService {

    AccountItem getAccountItem(long id)throws Exception;

    List<AccountItem> getAccountItem()throws Exception;

    List<AccountItem> select(String name, Integer type, String remark, int offset, int rows) throws Exception;

    Long countAccountItem(String name, Integer type, String remark) throws Exception;

    int insertAccountItem(String beanJson, HttpServletRequest request)throws Exception;

    int updateAccountItem(String beanJson, Long id)throws Exception;

    int deleteAccountItem(Long id)throws Exception;


    int batchDeleteAccountItem(String ids) throws Exception;

    int checkIsNameExist(Long id, String name)throws Exception;

    int insertAccountItemWithObj(AccountItem accountItem)throws Exception;


    int updateAccountItemWithObj(AccountItem accountItem)throws Exception;

    List<AccountItemVo4List> getDetailList(Long headerId)throws Exception;


    String saveDetials(String inserted, String deleted, String updated, Long headerId, String listType) throws Exception ;


    int batchDeleteAccountItemByIds(String ids)throws Exception;
}
