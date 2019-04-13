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
    

    Material getMaterial(long id);

    List<Material> getMaterial();

    List<MaterialVo4Unit> select(String name, String model, Long categoryId, String categoryIds, String mpList, int offset, int rows);

    Long countMaterial(String name, String model,Long categoryId, String categoryIds,String mpList);

    int insertMaterial(String beanJson, HttpServletRequest request) ;

    int updateMaterial(String beanJson, Long id);

    int deleteMaterial(Long id);

    int batchDeleteMaterial(String ids);

    int checkIsNameExist(Long id, String name);

    int checkIsExist(Long id, String name, String model, String color, String standard, String mfrs,
                            String otherField1, String otherField2, String otherField3, String unit, Long unitId);

    int batchSetEnable(Boolean enabled, String materialIDs);

    String findUnitName(Long mId);

    List<MaterialVo4Unit> findById(Long id);

    List<MaterialVo4Unit> findBySelect();

    List<Material> findByOrder();

    List<MaterialVo4Unit> findByAll(String name, String model, Long categoryId, String categoryIds);
    
    BaseResponseInfo importExcel(List<Material> mList) throws Exception;

    List<Material> getMaterialEnableSerialNumberList(Map<String, Object> parameterMap);
    
    int batchDeleteMaterialByIds(String ids);
    
    /**
     *  正常删除，要考虑数据完整性，进行完整性校验
     */
    int batchDeleteMaterialByIdsNormal(String ids) throws Exception;
}
