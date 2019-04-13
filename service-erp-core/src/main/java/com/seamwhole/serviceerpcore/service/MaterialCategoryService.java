package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.mapper.vo.TreeNode;
import com.seamwhole.serviceerpcore.model.MaterialCategory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface MaterialCategoryService {


    MaterialCategory getMaterialCategory(long id);

    List<MaterialCategory> getMaterialCategory();

    List<MaterialCategory> getAllList(Long parentId) ;

    List<MaterialCategory> select(String name, Integer parentId, int offset, int rows);

    Long countMaterialCategory(String name, Integer parentId);

    int insertMaterialCategory(String beanJson, HttpServletRequest request) ;

    int updateMaterialCategory(String beanJson, Long id);

    int deleteMaterialCategory(Long id);

    int batchDeleteMaterialCategory(String ids);

    int checkIsNameExist(Long id, String name);

    List<MaterialCategory> findById(Long id) ;


    /**
     *获取商品类别树数据
     */
    List<TreeNode> getMaterialCategoryTree(Long id) throws Exception;

    /**
     *  新增商品类别信息
     */
    int addMaterialCategory(MaterialCategory mc) throws Exception ;


    int batchDeleteMaterialCategoryByIds(String ids) throws Exception;

    int editMaterialCategory(MaterialCategory mc) ;

    /**
     * 根据商品类别编号判断商品类别是否已存在
     * */
    void  checkMaterialCategorySerialNo(MaterialCategory mc);

    /**
     *  正常删除，要考虑数据完整性，进行完整性校验
     */
    int batchDeleteMaterialCategoryByIdsNormal(String ids) throws Exception;
}
