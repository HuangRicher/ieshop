package com.seamwhole.serviceerpcore.mapper.ext;

import com.seamwhole.serviceerpcore.mapper.vo.AccountItemVo4List;
import com.seamwhole.serviceerpcore.model.AccountItem;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AccountItemExtMapper {

    List<AccountItem> selectByConditionAccountItem(
            @Param("name") String name,
            @Param("type") Integer type,
            @Param("remark") String remark,
            @Param("offset") Integer offset,
            @Param("rows") Integer rows);

    Long countsByAccountItem(
            @Param("name") String name,
            @Param("type") Integer type,
            @Param("remark") String remark);

    List<AccountItemVo4List> getDetailList(
            @Param("headerId") Long headerId);

    int batchDeleteAccountItemByIds(@Param("updateTime") Date updateTime, @Param("updater") Long updater, @Param("ids") String ids[]);
}