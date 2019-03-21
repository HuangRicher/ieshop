package com.seamwhole.servicetradecore.mapper;

import com.seamwhole.servicetradecore.model.GoodsGallery;
import com.seamwhole.servicetradecore.model.GoodsGalleryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsGalleryMapper {
    int countByExample(GoodsGalleryExample example);

    int deleteByExample(GoodsGalleryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsGallery record);

    int insertSelective(GoodsGallery record);

    List<GoodsGallery> selectByExampleWithBLOBs(GoodsGalleryExample example);

    List<GoodsGallery> selectByExample(GoodsGalleryExample example);

    GoodsGallery selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsGallery record, @Param("example") GoodsGalleryExample example);

    int updateByExampleWithBLOBs(@Param("record") GoodsGallery record, @Param("example") GoodsGalleryExample example);

    int updateByExample(@Param("record") GoodsGallery record, @Param("example") GoodsGalleryExample example);

    int updateByPrimaryKeySelective(GoodsGallery record);

    int updateByPrimaryKeyWithBLOBs(GoodsGallery record);

    int updateByPrimaryKey(GoodsGallery record);
}