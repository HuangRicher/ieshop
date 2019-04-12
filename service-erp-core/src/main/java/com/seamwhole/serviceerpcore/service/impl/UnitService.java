package com.seamwhole.serviceerpcore.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.seamwhole.serviceerpcore.constants.BusinessConstants;
import com.seamwhole.serviceerpcore.constants.ExceptionConstants;
import com.seamwhole.serviceerpcore.exception.BusinessRunTimeException;
import com.seamwhole.serviceerpcore.mapper.UnitMapper;
import com.seamwhole.serviceerpcore.mapper.ext.MaterialExtMapper;
import com.seamwhole.serviceerpcore.mapper.ext.UnitExtMapper;
import com.seamwhole.serviceerpcore.model.Material;
import com.seamwhole.serviceerpcore.model.Unit;
import com.seamwhole.serviceerpcore.model.UnitExample;
import com.seamwhole.serviceerpcore.model.User;
import com.seamwhole.serviceerpcore.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
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
public class UnitService {
    private Logger logger = LoggerFactory.getLogger(UnitService.class);

    @Resource
    private UnitMapper unitMapper;

    @Resource
    private UnitExtMapper unitExtMapper;
    @Resource
    private UserServiceImpl userService;
    @Resource
    private LogService logService;
    @Resource
    private MaterialExtMapper materialExtMapper;

    public Unit getUnit(long id) {
        return unitMapper.selectByPrimaryKey(id);
    }

    public List<Unit> getUnit() {
        UnitExample example = new UnitExample();
        return unitMapper.selectByExample(example);
    }

    public List<Unit> select(String name, int offset, int rows) {
        return unitExtMapper.selectByConditionUnit(name, offset, rows);
    }

    public Long countUnit(String name) {
        return unitExtMapper.countsByUnit(name);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int insertUnit(String beanJson, HttpServletRequest request) {
        Unit unit = JSONObject.parseObject(beanJson, Unit.class);
        return unitMapper.insertSelective(unit);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int updateUnit(String beanJson, Long id) {
        Unit unit = JSONObject.parseObject(beanJson, Unit.class);
        unit.setId(id);
        return unitMapper.updateByPrimaryKeySelective(unit);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int deleteUnit(Long id) {
        return unitMapper.deleteByPrimaryKey(id);
    }

    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteUnit(String ids) {
        List<Long> idList = StringUtil.strToLongList(ids);
        UnitExample example = new UnitExample();
        example.createCriteria().andIdIn(idList);
        return unitMapper.deleteByExample(example);
    }

    public int checkIsNameExist(Long id, String name) {
        UnitExample example = new UnitExample();
        example.createCriteria().andIdNotEqualTo(id).andUnameEqualTo(name).andDeleteFlagNotEqualTo(BusinessConstants.DELETE_FLAG_DELETED);
        List<Unit> list = unitMapper.selectByExample(example);
        return list.size();
    }
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteUnitByIds(String ids) {
        logService.insertLog(BusinessConstants.LOG_INTERFACE_NAME_UNIT,
                new StringBuffer(BusinessConstants.LOG_OPERATION_TYPE_DELETE).append(ids).toString(),
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
        User userInfo=userService.getCurrentUser();
        String [] idArray=ids.split(",");
        return unitExtMapper.batchDeleteUnitByIds(new Date(),userInfo==null?null:userInfo.getId(),idArray);
    }

    /**
     * create by: qiankunpingtai
     * website：https://qiankunpingtai.cn
     * description:
     *  正常删除，要考虑数据完整性，进行完整性校验
     * create time: 2019/4/11 10:20
     * @Param: ids
     * @return int
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public int batchDeleteUnitByIdsNormal(String ids) throws Exception {
        /**
         * 校验
         * 1、产品表	jsh_material
         * 是否有相关数据
         * */
        int deleteTotal=0;
        if(StringUtils.isEmpty(ids)){
            return deleteTotal;
        }
        String [] idArray=ids.split(",");
        /**
         * 校验产品表	jsh_material
         * */
        List<Material> materialList=materialExtMapper.getMaterialListByUnitIds(idArray);
        if(materialList!=null&&materialList.size()>0){
            logger.error("异常码[{}],异常提示[{}],参数,UnitIds[{}]",
                    ExceptionConstants.DELETE_FORCE_CONFIRM_CODE,ExceptionConstants.DELETE_FORCE_CONFIRM_MSG,ids);
            throw new BusinessRunTimeException(ExceptionConstants.DELETE_FORCE_CONFIRM_CODE,
                    ExceptionConstants.DELETE_FORCE_CONFIRM_MSG);
        }
        /**
         * 校验通过执行删除操作
         * */
        deleteTotal= batchDeleteUnitByIds(ids);
        return deleteTotal;
    }
}
