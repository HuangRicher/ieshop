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


    public User getUser(long id);

    public List<User> getUser();

    public List<User> select(String userName, String loginName, int offset, int rows);

    public Long countUser(String userName, String loginName);
    /**
     * create by: cjl
     * description:
     * 添加事务控制
     * create time: 2019/1/11 14:30
     * @Param: beanJson
     * @Param: request
     * @return int
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int insertUser(String beanJson, HttpServletRequest request);
    /**
     * create by: cjl
     * description:
     * 添加事务控制
     * create time: 2019/1/11 14:31
     * @Param: beanJson
     * @Param: id
     * @return int
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateUser(String beanJson, Long id) ;
    /**
     * create by: cjl
     * description:
     * 添加事务控制
     * create time: 2019/1/11 14:32
     * @Param: user
     * @return int
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateUserByObj(User user) ;
    /**
     * create by: cjl
     * description:
     *  添加事务控制
     * create time: 2019/1/11 14:33
     * @Param: md5Pwd
     * @Param: id
     * @return int
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int resetPwd(String md5Pwd, Long id) ;

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int deleteUser(Long id);

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteUser(String ids);

    public int validateUser(String username, String password) throws JshException ;

    public User getUserByUserName(String username);

    public int checkIsNameExist(Long id, String name);
    /**
     * create by: cjl
     * description:
     *  获取当前用户信息
     * create time: 2019/1/24 10:01
     * @Param:
     * @return com.jsh.erp.datasource.entities.User
     */
    public User getCurrentUser();

    public List<UserEx> getUserList(Map<String, Object> parameterMap) throws Exception;

    public void addUserAndOrgUserRel(UserEx ue) throws Exception;


    public UserEx addUser(UserEx ue) throws Exception;

    public UserEx registerUser(UserEx ue, Integer manageRoleId) throws Exception;

    public void updateUserTenant(User user) throws Exception;

    public void updateUserAndOrgUserRel(UserEx ue) throws Exception;


    public UserEx updateUser(UserEx ue);


    /**
     * description:
     *  检查用户名称和登录名不能重复
     * @Param: userEx
     * @return void
     */
    public void checkUserNameAndLoginName(UserEx userEx);


    /**
     * 通过用户名获取用户列表
     * */
    public List<User> getUserListByUserName(String userName);


    /**
     * 通过登录名获取用户列表
     * */
    public List<User> getUserListByloginName(String loginName);

    /**
     * 批量删除用户
     * */
    public void batDeleteUser(String ids);

    public List<TreeNodeEx> getOrganizationUserTree();
}
