package com.seamwhole.serviceerpcore.mapper.ext;


import com.seamwhole.serviceerpcore.mapper.vo.TreeNode;
import com.seamwhole.serviceerpcore.model.MaterialCategory;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description
 *
 * @Author: cjl
 * @Date: 2019/2/18 17:23
 */
public interface MaterialCategoryExtMapper {
    List<MaterialCategory> selectByConditionMaterialCategory(
            @Param("name") String name,
            @Param("parentId") Integer parentId,
            @Param("offset") Integer offset,
            @Param("rows") Integer rows);

    Long countsByMaterialCategory(
            @Param("name") String name,
            @Param("parentId") Integer parentId);

    List<TreeNode> getNodeTree(@Param("currentId") Long currentId);
    List<TreeNode> getNextNodeTree(Map<String, Object> parameterMap);

    int addMaterialCategory(MaterialCategory mc);

    int batchDeleteMaterialCategoryByIds(@Param("updateTime") Date updateTime, @Param("updater") Long updater, @Param("ids") String ids[]);

    int editMaterialCategory(MaterialCategory mc);

    List<MaterialCategory> getMaterialCategoryBySerialNo(@Param("serialNo") String serialNo);

    List<MaterialCategory> getMaterialCategoryListByCategoryIds(@Param("parentIds") String[] categoryIds);
}
