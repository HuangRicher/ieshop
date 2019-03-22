package com.seamwhole.servicetradecore.mapper.ext;

import com.seamwhole.servicetradecore.model.ShopComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCommentExtMapper {


    List<ShopComment> queryList(@Param("userId") Integer userId,
                                @Param("typeId") Integer typeId,
                                @Param("valueId") Integer valueId,
                                @Param("goodsId") Integer goodsId,
                                @Param("order") String order);

    int queryTotal(@Param("typeId") Integer typeId,
                   @Param("valueId") Integer valueId,
                   @Param("goodsId") Integer goodsId);

    int queryHasPicTotal(@Param("typeId") Integer typeId,
                         @Param("valueId") Integer valueId);

}
