package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.MaterialProperty;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface MaterialPropertyService {


    MaterialProperty getMaterialProperty(long id)throws Exception;

    List<MaterialProperty> getMaterialProperty()throws Exception ;

    List<MaterialProperty> select(String name, int offset, int rows)throws Exception;

    Long countMaterialProperty(String name)throws Exception;

    int insertMaterialProperty(String beanJson, HttpServletRequest request)throws Exception;

    int updateMaterialProperty(String beanJson, Long id)throws Exception;

    int deleteMaterialProperty(Long id)throws Exception;

    int batchDeleteMaterialProperty(String ids)throws Exception;

    int checkIsNameExist(Long id, String name)throws Exception;

    int batchDeleteMaterialPropertyByIds(String ids)throws Exception;
}
