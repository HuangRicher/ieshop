package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.mapper.vo.SerialNumberEx;
import com.seamwhole.serviceerpcore.model.DepotItem;
import com.seamwhole.serviceerpcore.model.SerialNumber;
import com.seamwhole.serviceerpcore.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public interface SerialNumberService {



    SerialNumber getSerialNumber(long id) throws Exception;

    List<SerialNumber> getSerialNumber()throws Exception;

    List<SerialNumberEx> select(String serialNumber, String materialName, Integer offset, Integer rows) throws Exception;

    Long countSerialNumber(String serialNumber,String materialName)throws Exception;

    int insertSerialNumber(String beanJson, HttpServletRequest request)throws Exception;

    int updateSerialNumber(String beanJson, Long id)throws Exception;

    int deleteSerialNumber(Long id)throws Exception;

    int batchDeleteSerialNumber(String ids)throws Exception;

    int checkIsNameExist(Long id, String serialNumber)throws Exception;
    
    int batchSetEnable(Boolean enabled, String materialIDs)throws Exception;
    
    List<SerialNumberEx> findById(Long id)throws Exception;
    
    void checkIsExist(Long id, String materialName, String serialNumber)throws Exception;

    /**
     * 新增序列号信息
     * */
    SerialNumberEx addSerialNumber(SerialNumberEx serialNumberEx)throws Exception;
    
    
    SerialNumberEx updateSerialNumber(SerialNumberEx serialNumberEx)throws Exception;
    
    /**
     *  根据商品名称判断商品名称是否有效
     * @Param: materialName
     * @return Long 满足使用条件的商品的id
     */
    Long checkMaterialName(String materialName)throws Exception;
    
    
    /**
     *  根据商品名称判断给商品添加序列号是否可行
     *  1、根据商品名称必须查询到唯一的商品
     *  2、该商品必须已经启用序列号
     *  3、该商品已绑定序列号数量小于商品现有库存
     *  用商品的库存去限制序列号的添加有点不合乎道理，去掉此限制
     * @Param: materialName
     * @return Long 满足使用条件的商品的id
     */
    Long getSerialNumberMaterialIdByMaterialName(String materialName)throws Exception;

    /**
     * 出库时判断序列号库存是否足够，
     * 同时将对应的序列号绑定单据
     */
    void checkAndUpdateSerialNumber(DepotItem depotItem, User userInfo) throws Exception;
    
    /**
     * 卖出序列号
     * * @Param: depotheadId
     * @Param: isSell 卖出'1'
     * @Param: Count 卖出或者赎回的数量
     */
    int sellSerialNumber(Long materialId, Long depotheadId,int count,User user) throws Exception;
    

    /**
     * 赎回序列号
     * @Param: isSell 赎回'0'
     * @Param: Count 卖出或者赎回的数量
     */
    int cancelSerialNumber(Long materialId, Long depotheadId,int count,User user) throws Exception;

    /**
     * 批量添加序列号
     */
    void batAddSerialNumber(String materialName, String serialNumberPrefix, Integer batAddTotal, String remark)throws Exception;
    
    
    /**
     *  逻辑删除序列号信息
     */
    int batchDeleteSerialNumberByIds(String ids)throws Exception;
}
