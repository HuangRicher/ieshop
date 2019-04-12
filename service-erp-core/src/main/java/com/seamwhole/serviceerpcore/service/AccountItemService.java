package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.mapper.vo.AccountItemVo4List;
import com.seamwhole.serviceerpcore.model.AccountItem;
import org.springframework.dao.DataAccessException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface AccountItemService {

    AccountItem getAccountItem(long id);

    List<AccountItem> getAccountItem();

    List<AccountItem> select(String name, Integer type, String remark, int offset, int rows) ;

    Long countAccountItem(String name, Integer type, String remark) ;

    int insertAccountItem(String beanJson, HttpServletRequest request);

    int updateAccountItem(String beanJson, Long id);

    int deleteAccountItem(Long id);


    int batchDeleteAccountItem(String ids) ;

    int checkIsNameExist(Long id, String name);

    int insertAccountItemWithObj(AccountItem accountItem);


    int updateAccountItemWithObj(AccountItem accountItem);

    List<AccountItemVo4List> getDetailList(Long headerId);


    String saveDetials(String inserted, String deleted, String updated, Long headerId, String listType) throws DataAccessException ;


    int batchDeleteAccountItemByIds(String ids);
}
