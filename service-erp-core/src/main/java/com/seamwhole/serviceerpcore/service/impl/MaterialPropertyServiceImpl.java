package com.seamwhole.serviceerpcore.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.seamwhole.serviceerpcore.constants.BusinessConstants;
import com.seamwhole.serviceerpcore.mapper.MaterialPropertyMapper;
import com.seamwhole.serviceerpcore.mapper.ext.MaterialPropertyExtMapper;
import com.seamwhole.serviceerpcore.model.MaterialProperty;
import com.seamwhole.serviceerpcore.model.MaterialPropertyExample;
import com.seamwhole.serviceerpcore.model.User;
import com.seamwhole.serviceerpcore.service.MaterialPropertyService;
import com.seamwhole.serviceerpcore.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class MaterialPropertyServiceImpl implements MaterialPropertyService {
    private Logger logger = LoggerFactory.getLogger(MaterialPropertyServiceImpl.class);

    @Resource
    private MaterialPropertyMapper materialPropertyMapper;

    @Resource
    private MaterialPropertyExtMapper materialPropertyExtMapper;
    @Resource
    private UserServiceImpl userService;
    @Resource
    private LogServiceImpl logService;

    public MaterialProperty getMaterialProperty(long id) {
        return materialPropertyMapper.selectByPrimaryKey(id);
    }

    public List<MaterialProperty> getMaterialProperty() {
        MaterialPropertyExample example = new MaterialPropertyExample();
        example.createCriteria().andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        return materialPropertyMapper.selectByExample(example);
    }
    public List<MaterialProperty> select(String name, int offset, int rows) {
        return materialPropertyExtMapper.selectByConditionMaterialProperty(name, offset, rows);
    }

    public Long countMaterialProperty(String name) {
        return materialPropertyExtMapper.countsByMaterialProperty(name);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int insertMaterialProperty(String beanJson, HttpServletRequest request) {
        MaterialProperty materialProperty = JSONObject.parseObject(beanJson, MaterialProperty.class);
        return materialPropertyMapper.insertSelective(materialProperty);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateMaterialProperty(String beanJson, Long id) {
        MaterialProperty materialProperty = JSONObject.parseObject(beanJson, MaterialProperty.class);
        materialProperty.setId(id);
        return materialPropertyMapper.updateByPrimaryKeySelective(materialProperty);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int deleteMaterialProperty(Long id) {
        return materialPropertyMapper.deleteByPrimaryKey(id);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteMaterialProperty(String ids) {
        List<Long> idList = StringUtil.strToLongList(ids);
        MaterialPropertyExample example = new MaterialPropertyExample();
        example.createCriteria().andIdIn(idList);
        return materialPropertyMapper.deleteByExample(example);
    }

    public int checkIsNameExist(Long id, String name) {
        return 0;
    }
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteMaterialPropertyByIds(String ids) {
        logService.insertLog(BusinessConstants.LOG_INTERFACE_NAME_MATERIAL_PROPERTY,
                new StringBuffer(BusinessConstants.LOG_OPERATION_TYPE_DELETE).append(ids).toString(),
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
        User userInfo=userService.getCurrentUser();
        String [] idArray=ids.split(",");
        return materialPropertyExtMapper.batchDeleteMaterialPropertyByIds(new Date(),userInfo==null?null:userInfo.getId(),idArray);

    }
}
