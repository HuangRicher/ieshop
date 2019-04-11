package com.seamwhole.serviceerpcore.mapper;

import com.seamwhole.serviceerpcore.model.OrgUserRel;
import com.seamwhole.serviceerpcore.model.OrgUserRelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrgUserRelMapper {
    int countByExample(OrgUserRelExample example);

    int deleteByExample(OrgUserRelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrgUserRel record);

    int insertSelective(OrgUserRel record);

    List<OrgUserRel> selectByExample(OrgUserRelExample example);

    OrgUserRel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrgUserRel record, @Param("example") OrgUserRelExample example);

    int updateByExample(@Param("record") OrgUserRel record, @Param("example") OrgUserRelExample example);

    int updateByPrimaryKeySelective(OrgUserRel record);

    int updateByPrimaryKey(OrgUserRel record);
}