package com.seamwhole.serviceerpcore.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.seamwhole.serviceerpcore.constants.BusinessConstants;
import com.seamwhole.serviceerpcore.constants.ExceptionConstants;
import com.seamwhole.serviceerpcore.exception.BusinessRunTimeException;
import com.seamwhole.serviceerpcore.mapper.AccountHeadMapper;
import com.seamwhole.serviceerpcore.mapper.ext.AccountHeadExtMapper;
import com.seamwhole.serviceerpcore.mapper.ext.AccountItemExtMapper;
import com.seamwhole.serviceerpcore.mapper.vo.AccountHeadVo4ListEx;
import com.seamwhole.serviceerpcore.model.AccountHead;
import com.seamwhole.serviceerpcore.model.AccountHeadExample;
import com.seamwhole.serviceerpcore.model.AccountItem;
import com.seamwhole.serviceerpcore.model.User;
import com.seamwhole.serviceerpcore.service.AccountHeadService;
import com.seamwhole.serviceerpcore.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccountHeadServiceImpl implements AccountHeadService{
    private Logger logger = LoggerFactory.getLogger(AccountHeadServiceImpl.class);

    @Resource
    private AccountHeadMapper accountHeadMapper;

    @Resource
    private AccountHeadExtMapper accountHeadExtMapper;
    @Resource
    private UserServiceImpl userService;
    @Resource
    private LogServiceImpl logService;
    @Resource
    private AccountItemExtMapper accountItemExtMapper;

    public AccountHead getAccountHead(long id) {
        return accountHeadMapper.selectByPrimaryKey(id);
    }

    public List<AccountHead> getAccountHead() {
        AccountHeadExample example = new AccountHeadExample();
        return accountHeadMapper.selectByExample(example);
    }

    public List<AccountHeadVo4ListEx> select(String type, String billNo, String beginTime, String endTime, int offset, int rows) {
        List<AccountHeadVo4ListEx> resList = new ArrayList<AccountHeadVo4ListEx>();
        List<AccountHeadVo4ListEx> list = accountHeadExtMapper.selectByConditionAccountHead(type, billNo, beginTime, endTime, offset, rows);
        if (null != list) {
            for (AccountHeadVo4ListEx ah : list) {
                if(ah.getChangeamount() != null) {
                    ah.setChangeamount(ah.getChangeamount().abs());
                }
                if(ah.getTotalprice() != null) {
                    ah.setTotalprice(ah.getTotalprice().abs());
                }
                resList.add(ah);
            }
        }
        return resList;
    }

    public Long countAccountHead(String type, String billNo, String beginTime, String endTime) {
        return accountHeadExtMapper.countsByAccountHead(type, billNo, beginTime, endTime);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int insertAccountHead(String beanJson, HttpServletRequest request) {
        AccountHead accountHead = JSONObject.parseObject(beanJson, AccountHead.class);
        return accountHeadMapper.insertSelective(accountHead);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateAccountHead(String beanJson, Long id) {
        AccountHead accountHead = JSONObject.parseObject(beanJson, AccountHead.class);
        accountHead.setId(id);
        return accountHeadMapper.updateByPrimaryKeySelective(accountHead);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int deleteAccountHead(Long id) {
        return accountHeadMapper.deleteByPrimaryKey(id);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteAccountHead(String ids) {
        List<Long> idList = StringUtil.strToLongList(ids);
        AccountHeadExample example = new AccountHeadExample();
        example.createCriteria().andIdIn(idList);
        return accountHeadMapper.deleteByExample(example);
    }

    public int checkIsNameExist(Long id, String name) {
        AccountHeadExample example = new AccountHeadExample();
        example.createCriteria().andIdNotEqualTo(id).andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        List<AccountHead> list = accountHeadMapper.selectByExample(example);
        return list.size();
    }

    public Long getMaxId() {
        return accountHeadExtMapper.getMaxId();
    }

    public BigDecimal findAllMoney(Integer supplierId, String type, String mode, String endTime) {
        String modeName = "";
        if (mode.equals("实际")) {
            modeName = "ChangeAmount";
        } else if (mode.equals("合计")) {
            modeName = "TotalPrice";
        }
        return accountHeadExtMapper.findAllMoney(supplierId, type, modeName, endTime);
    }

    public List<AccountHeadVo4ListEx> getDetailByNumber(String billNo) {
        List<AccountHeadVo4ListEx> resList = new ArrayList<AccountHeadVo4ListEx>();
        List<AccountHeadVo4ListEx> list = accountHeadExtMapper.getDetailByNumber(billNo);
        if (null != list) {
            for (AccountHeadVo4ListEx ah : list) {
                if(ah.getChangeamount() != null) {
                    ah.setChangeamount(ah.getChangeamount().abs());
                }
                if(ah.getTotalprice() != null) {
                    ah.setTotalprice(ah.getTotalprice().abs());
                }
                resList.add(ah);
            }
        }
        return resList;
    }
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteAccountHeadByIds(String ids) {
        logService.insertLog(BusinessConstants.LOG_INTERFACE_NAME_ACCOUNT_HEAD,
                new StringBuffer(BusinessConstants.LOG_OPERATION_TYPE_DELETE).append(ids).toString(),
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
        User userInfo=userService.getCurrentUser();
        String [] idArray=ids.split(",");
        return accountHeadExtMapper.batchDeleteAccountHeadByIds(new Date(),userInfo==null?null:userInfo.getId(),idArray);
    }
    /**
     * create by: qiankunpingtai
     * website：https://qiankunpingtai.cn
     * description:
     *  正常删除，要考虑数据完整性，进行完整性校验
     * create time: 2019/4/10 15:49
     * @Param: ids
     * @return int
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteAccountHeadByIdsNormal(String ids) throws Exception {
        /**
         * 校验
         * 1、财务子表	jsh_accountitem
         * 是否有相关数据
         * */
        int deleteTotal=0;
        if(StringUtils.isEmpty(ids)){
            return deleteTotal;
        }
        String [] idArray=ids.split(",");
        /**
         * 校验财务子表	jsh_accountitem
         * */
        List<AccountItem> accountItemList=accountItemExtMapper.getAccountItemListByHeaderIds(idArray);
        if(accountItemList!=null&&accountItemList.size()>0){
            logger.error("异常码[{}],异常提示[{}],参数,HeaderIds[{}]",
                    ExceptionConstants.DELETE_FORCE_CONFIRM_CODE,ExceptionConstants.DELETE_FORCE_CONFIRM_MSG,ids);
            throw new BusinessRunTimeException(ExceptionConstants.DELETE_FORCE_CONFIRM_CODE,
                    ExceptionConstants.DELETE_FORCE_CONFIRM_MSG);
        }
        /**
         * 校验通过执行删除操作
         * */
        deleteTotal= batchDeleteAccountHeadByIds(ids);
        return deleteTotal;
    }
}
