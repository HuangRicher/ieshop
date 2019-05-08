package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.UserBusiness;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface UserBusinessService {


    UserBusiness getUserBusiness(long id)throws Exception;

    List<UserBusiness> getUserBusiness()throws Exception;

    int insertUserBusiness(String beanJson, HttpServletRequest request) throws Exception;

    int updateUserBusiness(String beanJson, Long id)throws Exception ;

    int deleteUserBusiness(Long id)throws Exception;

    int batchDeleteUserBusiness(String ids)throws Exception;

    int checkIsNameExist(Long id, String name)throws Exception;

    List<UserBusiness> getBasicData(String keyId, String type)throws Exception;

    Long checkIsValueExist(String type, String keyId)throws Exception;

    Boolean checkIsUserBusinessExist(String TypeVale, String KeyIdValue, String UBValue)throws Exception;

    int updateBtnStr(Long userBusinessId, String btnStr) throws Exception;

    List<UserBusiness> findRoleByUserId(String userId)throws Exception;

    List<UserBusiness> findAppByRoles(String roles)throws Exception;
    
    int batchDeleteUserBusinessByIds(String ids)throws Exception;

    /**
     * 通过功能（RoleFunctions）权限更新应用（RoleApp）权限
     * @param type
     * @param keyId
     * @param functionIds
     * @return
     */
    int insertOrUpdateAppValue(String type, String keyId, String functionIds)throws Exception;
}
