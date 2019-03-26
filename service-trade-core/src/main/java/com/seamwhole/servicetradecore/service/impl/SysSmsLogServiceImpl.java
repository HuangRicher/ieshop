package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.PageInfo;
import com.seamwhole.except.CheckException;
import com.seamwhole.servicetradecore.constant.Constant;
import com.seamwhole.servicetradecore.domain.SmsConfigOutInfo;
import com.seamwhole.servicetradecore.mapper.SysSmsLogMapper;
import com.seamwhole.servicetradecore.mapper.ext.SysSmsLogExtMapper;
import com.seamwhole.servicetradecore.mapper.model.SysSmsLogDO;
import com.seamwhole.servicetradecore.model.SysSmsLog;
import com.seamwhole.servicetradecore.model.SysSmsLogWithBLOBs;
import com.seamwhole.servicetradecore.service.SysConfigService;
import com.seamwhole.servicetradecore.service.SysSmsLogService;
import com.seamwhole.servicetradecore.util.SmsUtil;
import com.seamwhole.util.DateUtils;
import com.seamwhole.util.StringUtils;
import com.seamwhole.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysSmsLogServiceImpl implements SysSmsLogService {
    @Autowired
    private SysSmsLogMapper sysSmsLogMapper;
    @Autowired
    private SysSmsLogExtMapper sysSmsLogExtMapper;
    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public SysSmsLog queryObject(String id) {
        return sysSmsLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysSmsLogDO> queryList(Map<String, Object> map) {
        return sysSmsLogExtMapper.queryList(map);
    }

    @Override
    public PageInfo<SysSmsLogDO> queryByPage(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public int save(SysSmsLogWithBLOBs smsLog) {
        smsLog.setId(UUIDUtil.generateUUID());
        return sysSmsLogMapper.insertSelective(smsLog);
    }

    /*@Override
    public int update(SysSmsLog smsLog) {
        return sysSmsLogMapper.updateByPrimaryKeySelective(smsLog);
    }*/

    /*@Override
    public int delete(String id) {
        return smsLogDao.delete(id);
    }*/

    /*@Override
    public int deleteBatch(String[] ids) {
        return smsLogDao.deleteBatch(ids);
    }*/

    @Override
    public SysSmsLogWithBLOBs sendSms(SysSmsLogWithBLOBs smsLog,Long userId) {
        String result = "";
        //获取云存储配置信息
        SmsConfigOutInfo config = sysConfigService.getConfigObject(Constant.SMS_CONFIG_KEY, SmsConfigOutInfo.class);
        if (config==null) {
            throw new CheckException("请先配置短信平台信息");
        }
        if (StringUtils.isNullOrEmpty(config.getName())) {
            throw new CheckException("请先配置短信平台用户名");
        }
        if (StringUtils.isNullOrEmpty(config.getPwd())) {
            throw new CheckException("请先配置短信平台密钥");
        }
        if (StringUtils.isNullOrEmpty(config.getSign())) {
            throw new CheckException("请先配置短信平台签名");
        }
        try {
            /**
             * 状态,发送编号,无效号码数,成功提交数,黑名单数和消息，无论发送的号码是多少，一个发送请求只返回一个sendid，如果响应的状态不是“0”，则只有状态和消息
             */
            result = SmsUtil.crSendSms(config.getName(), config.getPwd(), smsLog.getMobile(), smsLog.getContent(), config.getSign(),
                    DateUtils.format(smsLog.getStime(), "yyyy-MM-dd HH:mm:ss"), smsLog.getExtno());
        } catch (Exception e) {

        }
        String arr[] = result.split(",");
        //发送成功
        if ("0".equals(arr[0])) {
            smsLog.setSendId(arr[1]);
            smsLog.setInvalidNum(Integer.parseInt(arr[2]));
            smsLog.setSuccessNum(Integer.parseInt(arr[3]));
            smsLog.setBlackNum(Integer.parseInt(arr[4]));
            smsLog.setReturnMsg(arr[5]);
        } else {
            //发送失败
            smsLog.setReturnMsg(arr[1]);
        }
        smsLog.setSendStatus(Integer.parseInt(arr[0]));
        try {
            smsLog.setUserId(userId);
        } catch (Exception e) {
            //外部发送短信
            smsLog.setUserId(1L);
        }
        smsLog.setSign(config.getSign());
        if (null == smsLog.getStime()) {
            smsLog.setStime(new Date());
        }
        //保存发送记录
        save(smsLog);
        return smsLog;
    }
}
