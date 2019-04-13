package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.MaterialProperty;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface MaterialPropertyService {


    MaterialProperty getMaterialProperty(long id);

    List<MaterialProperty> getMaterialProperty() ;

    List<MaterialProperty> select(String name, int offset, int rows);

    Long countMaterialProperty(String name);

    int insertMaterialProperty(String beanJson, HttpServletRequest request);

    int updateMaterialProperty(String beanJson, Long id);

    int deleteMaterialProperty(Long id);

    int batchDeleteMaterialProperty(String ids);

    int checkIsNameExist(Long id, String name);

    int batchDeleteMaterialPropertyByIds(String ids);
}
