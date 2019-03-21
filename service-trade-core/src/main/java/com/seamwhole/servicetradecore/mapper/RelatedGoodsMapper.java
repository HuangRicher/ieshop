package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.RelatedGoods;
import com.seamwhole.servicetradecore.model.RelatedGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RelatedGoodsMapper {
    int countByExample(RelatedGoodsExample example);

    int deleteByExample(RelatedGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RelatedGoods record);

    int insertSelective(RelatedGoods record);

    List<RelatedGoods> selectByExample(RelatedGoodsExample example);

    RelatedGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RelatedGoods record, @Param("example") RelatedGoodsExample example);

    int updateByExample(@Param("record") RelatedGoods record, @Param("example") RelatedGoodsExample example);

    int updateByPrimaryKeySelective(RelatedGoods record);

    int updateByPrimaryKey(RelatedGoods record);
}