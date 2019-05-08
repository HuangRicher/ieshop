package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.mapper.vo.MaterialVo4Unit;
import com.seamwhole.serviceerpcore.model.Material;
import com.seamwhole.serviceerpcore.utils.BaseResponseInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public interface MaterialService {
    

    Material getMaterial(long id)throws Exception;

    List<Material> getMaterial()throws Exception;

    List<MaterialVo4Unit> select(String name, String model, Long categoryId, String categoryIds, String mpList, int offset, int rows)throws Exception;

    Long countMaterial(String name, String model,Long categoryId, String categoryIds,String mpList)throws Exception;

    int insertMaterial(String beanJson, HttpServletRequest request) throws Exception;

    int updateMaterial(String beanJson, Long id)throws Exception;

    int deleteMaterial(Long id)throws Exception;

    int batchDeleteMaterial(String ids)throws Exception;

    int checkIsNameExist(Long id, String name)throws Exception;

    int checkIsExist(Long id, String name, String model, String color, String standard, String mfrs,
                            String otherField1, String otherField2, String otherField3, String unit, Long unitId)throws Exception;

    int batchSetEnable(Boolean enabled, String materialIDs)throws Exception;

    String findUnitName(Long mId)throws Exception;

    List<MaterialVo4Unit> findById(Long id)throws Exception;

    List<MaterialVo4Unit> findBySelect()throws Exception;

    List<Material> findByOrder()throws Exception;

    List<MaterialVo4Unit> findByAll(String name, String model, Long categoryId, String categoryIds)throws Exception;
    
    BaseResponseInfo importExcel(List<Material> mList) throws Exception;

    List<Material> getMaterialEnableSerialNumberList(Map<String, Object> parameterMap)throws Exception;
    
    int batchDeleteMaterialByIds(String ids)throws Exception;
    
    /**
     *  正常删除，要考虑数据完整性，进行完整性校验
     */
    int batchDeleteMaterialByIdsNormal(String ids) throws Exception;
}
