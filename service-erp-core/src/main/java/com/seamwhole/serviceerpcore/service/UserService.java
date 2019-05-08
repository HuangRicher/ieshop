package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.mapper.vo.TreeNodeEx;
import com.seamwhole.serviceerpcore.mapper.vo.UserEx;
import com.seamwhole.serviceerpcore.model.User;
import com.seamwhole.serviceerpcore.utils.JshException;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface UserService {


    User getUser(long id)throws Exception;

    List<User> getUser()throws Exception;

    List<User> select(String userName, String loginName, int offset, int rows)throws Exception;

    Long countUser(String userName, String loginName)throws Exception;


    /**
     * 添加事务控制
     * @Param: beanJson
     * @Param: request
     * @return int
     */
    int insertUser(String beanJson, HttpServletRequest request)throws Exception;


    /**
     * 添加事务控制
     * @Param: beanJson
     * @Param: id
     * @return int
     */
    int updateUser(String beanJson, Long id) throws Exception;


    /**
     * 添加事务控制
     * create time: 2019/1/11 14:32
     * @Param: user
     * @return int
     */
    int updateUserByObj(User user) throws Exception;

    /**
     *  添加事务控制
     * @Param: md5Pwd
     * * @Param: id
     * @return int
     */
    int resetPwd(String md5Pwd, Long id) throws Exception;


    int deleteUser(Long id)throws Exception;

    int batchDeleteUser(String ids)throws Exception;

    int validateUser(String username, String password) throws JshException ;

    User getUserByUserName(String username)throws Exception;

    int checkIsNameExist(Long id, String name)throws Exception;

    /**
     *  获取当前用户信息
     * @Param:
     * @return com.jsh.erp.datasource.entities.User
     */
    User getCurrentUser()throws Exception;

    List<UserEx> getUserList(Map<String, Object> parameterMap) throws Exception;

    void addUserAndOrgUserRel(UserEx ue) throws Exception;


    UserEx addUser(UserEx ue) throws Exception;

    UserEx registerUser(UserEx ue, Integer manageRoleId) throws Exception;

    void updateUserTenant(User user) throws Exception;

    void updateUserAndOrgUserRel(UserEx ue) throws Exception;


    UserEx updateUser(UserEx ue)throws Exception;


    /**
     * description:
     *  检查用户名称和登录名不能重复
     * @Param: userEx
     * @return void
     */
    void checkUserNameAndLoginName(UserEx userEx)throws Exception;


    /**
     * 通过用户名获取用户列表
     * */
    List<User> getUserListByUserName(String userName)throws Exception;


    /**
     * 通过登录名获取用户列表
     * */
    List<User> getUserListByloginName(String loginName)throws Exception;

    /**
     * 批量删除用户
     * */
    void batDeleteUser(String ids)throws Exception;

    List<TreeNodeEx> getOrganizationUserTree()throws Exception;
}
