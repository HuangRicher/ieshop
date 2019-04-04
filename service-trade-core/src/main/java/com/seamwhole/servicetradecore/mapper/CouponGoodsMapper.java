package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.CouponGoods;
import com.seamwhole.servicetradecore.model.CouponGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CouponGoodsMapper {
    int countByExample(CouponGoodsExample example);

    int deleteByExample(CouponGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CouponGoods record);

    int insertSelective(CouponGoods record);

    List<CouponGoods> selectByExample(CouponGoodsExample example);

    CouponGoods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CouponGoods record, @Param("example") CouponGoodsExample example);

    int updateByExample(@Param("record") CouponGoods record, @Param("example") CouponGoodsExample example);

    int updateByPrimaryKeySelective(CouponGoods record);

    int updateByPrimaryKey(CouponGoods record);
}