package com.seamwhole.servicetradecore.service;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.SysSmsLogDO;
import com.seamwhole.servicetradecore.model.SysSmsLog;
import com.seamwhole.servicetradecore.model.SysSmsLogWithBLOBs;

import java.util.List;
import java.util.Map;

/**
 * 发送短信日志Service
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-12-16 23:38:05
 */
public interface SysSmsLogService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    SysSmsLog queryObject(String id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<SysSmsLogDO> queryList(Map<String, Object> map);


    PageInfo<SysSmsLogDO> queryByPage(Map<String, Object> map,Integer pageNum,Integer pageSize);


    /**
     * 保存实体
     *
     * @param smsLog 实体
     * @return 保存条数
     */
    int save(SysSmsLogWithBLOBs smsLog);

    /**
     * 根据主键更新实体
     *
     * @param smsLog 实体
     * @return 更新条数
     */
    //int update(SysSmsLog smsLog);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    //int delete(String id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    //int deleteBatch(String[] ids);

    /**
     * 发送短信
     *
     * @param smsLog
     * @return
     */
    SysSmsLogWithBLOBs sendSms(SysSmsLogWithBLOBs smsLog,Long userId);
}
