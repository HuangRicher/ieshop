package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.mapper.vo.*;
import com.seamwhole.serviceerpcore.model.DepotItem;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface DepotItemService {


    DepotItem getDepotItem(long id)throws Exception;

    List<DepotItem> getDepotItem()throws Exception;

    List<DepotItem> select(String name, Integer type, String remark, int offset, int rows)throws Exception;

    Long countDepotItem(String name, Integer type, String remark)throws Exception;

    int insertDepotItem(String beanJson, HttpServletRequest request)throws Exception;

    int updateDepotItem(String beanJson, Long id)throws Exception;

    int deleteDepotItem(Long id)throws Exception;


    int batchDeleteDepotItem(String ids)throws Exception;

    int checkIsNameExist(Long id, String name)throws Exception;

    List<DepotItemVo4HeaderId> getHeaderIdByMaterial(String materialParam, String depotIds)throws Exception;

    List<DepotItemVo4DetailByTypeAndMId> findDetailByTypeAndMaterialIdList(Map<String, String> map)throws Exception;

    Long findDetailByTypeAndMaterialIdCounts(Map<String, String> map)throws Exception ;

    List<DepotItemVo4Material> findStockNumByMaterialIdList(Map<String, String> map)throws Exception ;

    Long findStockNumByMaterialIdCounts(Map<String, String> map)throws Exception;

    int insertDepotItemWithObj(DepotItem depotItem)throws Exception;

    int updateDepotItemWithObj(DepotItem depotItem)throws Exception;

    int findByTypeAndMaterialId(String type, Long mId)throws Exception;

    List<DepotItemVo4WithInfoEx> getDetailList(Long headerId) throws Exception;

    List<DepotItemVo4WithInfoEx> findByAll(String headIds, String materialIds, Integer offset, Integer rows)throws Exception;

    int findByAllCount(String headIds, String materialIds)throws Exception;

    BigDecimal findByType(String type, Integer ProjectId, Long MId, String MonthTime, Boolean isPrev)throws Exception;

    BigDecimal findPriceByType(String type, Integer ProjectId, Long MId, String MonthTime, Boolean isPrev)throws Exception;

    BigDecimal findAssembleByType(String subType, String mType, Integer ProjectId, Long MId, String MonthTime, Boolean isPrev)throws Exception;

    BigDecimal buyOrSale(String type, String subType, Long MId, String MonthTime, String sumType)throws Exception;

    /**
     * BasicNumber=OperNumber*ratio
     * */
    String saveDetials(String inserted, String deleted, String updated, Long headerId) throws Exception;


    /**
     * 查询计量单位信息
     * @return
     */
    String findUnitName(Long mId)throws Exception;


    /**
     * 查询商品当前库存数量是否充足，
     * */
    int getCurrentInStock(Long materialId)throws Exception;

    int batchDeleteDepotItemByIds(String ids)throws Exception;

    List<DepotItemStockWarningCount> findStockWarningCount(int offset, Integer rows, Integer pid);

    int findStockWarningCountTotal(Integer pid);
}
