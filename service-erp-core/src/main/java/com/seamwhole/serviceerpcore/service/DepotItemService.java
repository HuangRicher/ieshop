package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.mapper.vo.DepotItemVo4DetailByTypeAndMId;
import com.seamwhole.serviceerpcore.mapper.vo.DepotItemVo4HeaderId;
import com.seamwhole.serviceerpcore.mapper.vo.DepotItemVo4Material;
import com.seamwhole.serviceerpcore.mapper.vo.DepotItemVo4WithInfoEx;
import com.seamwhole.serviceerpcore.model.DepotItem;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface DepotItemService {


    DepotItem getDepotItem(long id);

    List<DepotItem> getDepotItem();

    List<DepotItem> select(String name, Integer type, String remark, int offset, int rows);

    Long countDepotItem(String name, Integer type, String remark);

    int insertDepotItem(String beanJson, HttpServletRequest request);

    int updateDepotItem(String beanJson, Long id);

    int deleteDepotItem(Long id);


    int batchDeleteDepotItem(String ids);

    int checkIsNameExist(Long id, String name);

    List<DepotItemVo4HeaderId> getHeaderIdByMaterial(String materialParam, String depotIds);

    List<DepotItemVo4DetailByTypeAndMId> findDetailByTypeAndMaterialIdList(Map<String, String> map);

    Long findDetailByTypeAndMaterialIdCounts(Map<String, String> map) ;

    List<DepotItemVo4Material> findStockNumByMaterialIdList(Map<String, String> map) ;

    Long findStockNumByMaterialIdCounts(Map<String, String> map);

    int insertDepotItemWithObj(DepotItem depotItem);

    int updateDepotItemWithObj(DepotItem depotItem);

    int findByTypeAndMaterialId(String type, Long mId);

    List<DepotItemVo4WithInfoEx> getDetailList(Long headerId) ;

    List<DepotItemVo4WithInfoEx> findByAll(String headIds, String materialIds, Integer offset, Integer rows);

    int findByAllCount(String headIds, String materialIds);

    BigDecimal findByType(String type, Integer ProjectId, Long MId, String MonthTime, Boolean isPrev);

    BigDecimal findPriceByType(String type, Integer ProjectId, Long MId, String MonthTime, Boolean isPrev);

    BigDecimal findAssembleByType(String subType, String mType, Integer ProjectId, Long MId, String MonthTime, Boolean isPrev);

    BigDecimal buyOrSale(String type, String subType, Long MId, String MonthTime, String sumType);

    /**
     * BasicNumber=OperNumber*ratio
     * */
    String saveDetials(String inserted, String deleted, String updated, Long headerId) throws Exception;


    /**
     * 查询计量单位信息
     * @return
     */
    String findUnitName(Long mId);


    /**
     * 查询商品当前库存数量是否充足，
     * */
    int getCurrentInStock(Long materialId);

    int batchDeleteDepotItemByIds(String ids);
}
