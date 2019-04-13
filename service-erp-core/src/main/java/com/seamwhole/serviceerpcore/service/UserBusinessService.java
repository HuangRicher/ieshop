package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.UserBusiness;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface UserBusinessService {


    UserBusiness getUserBusiness(long id);

    List<UserBusiness> getUserBusiness();

    int insertUserBusiness(String beanJson, HttpServletRequest request) ;

    int updateUserBusiness(String beanJson, Long id) ;

    int deleteUserBusiness(Long id);

    int batchDeleteUserBusiness(String ids);

    int checkIsNameExist(Long id, String name);

    List<UserBusiness> getBasicData(String keyId, String type);

    Long checkIsValueExist(String type, String keyId);

    Boolean checkIsUserBusinessExist(String TypeVale, String KeyIdValue, String UBValue);

    int updateBtnStr(Long userBusinessId, String btnStr) ;

    List<UserBusiness> findRoleByUserId(String userId);

    List<UserBusiness> findAppByRoles(String roles);
    
    int batchDeleteUserBusinessByIds(String ids);

    /**
     * 通过功能（RoleFunctions）权限更新应用（RoleApp）权限
     * @param type
     * @param keyId
     * @param functionIds
     * @return
     */
    int insertOrUpdateAppValue(String type, String keyId, String functionIds);
}
