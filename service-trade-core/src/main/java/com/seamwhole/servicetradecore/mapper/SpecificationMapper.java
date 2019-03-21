package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.Specification;
import com.seamwhole.servicetradecore.model.SpecificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpecificationMapper {
    int countByExample(SpecificationExample example);

    int deleteByExample(SpecificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Specification record);

    int insertSelective(Specification record);

    List<Specification> selectByExample(SpecificationExample example);

    Specification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Specification record, @Param("example") SpecificationExample example);

    int updateByExample(@Param("record") Specification record, @Param("example") SpecificationExample example);

    int updateByPrimaryKeySelective(Specification record);

    int updateByPrimaryKey(Specification record);
}