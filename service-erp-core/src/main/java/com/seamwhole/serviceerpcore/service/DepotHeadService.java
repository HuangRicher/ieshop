package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.mapper.vo.DepotHeadVo4InDetail;
import com.seamwhole.serviceerpcore.mapper.vo.DepotHeadVo4InOutMCount;
import com.seamwhole.serviceerpcore.mapper.vo.DepotHeadVo4List;
import com.seamwhole.serviceerpcore.mapper.vo.DepotHeadVo4StatementAccount;
import com.seamwhole.serviceerpcore.model.DepotHead;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;


public interface DepotHeadService {


    DepotHead getDepotHead(long id)throws Exception;

    List<DepotHead> getDepotHead()throws Exception;

    List<DepotHeadVo4List> select(String type, String subType, String number, String beginTime, String endTime, String dhIds, int offset, int rows)throws Exception;

    Long countDepotHead(String type, String subType, String number, String beginTime, String endTime, String dhIds)throws Exception ;

    int insertDepotHead(String beanJson, HttpServletRequest request)throws Exception;

    int updateDepotHead(String beanJson, Long id)throws Exception ;

    int deleteDepotHead(Long id)throws Exception;

    int batchDeleteDepotHead(String ids)throws Exception;

    int checkIsNameExist(Long id, String name)throws Exception;

    int batchSetStatus(String status, String depotHeadIDs)throws Exception;
    /**
     * 创建一个唯一的序列号
     * */
    String buildOnlyNumber()throws Exception;

    Long getMaxId()throws Exception;

    String findMaterialsListByHeaderId(Long id)throws Exception ;

    List<DepotHead> findByMonth(String monthTime)throws Exception;

    List<DepotHead> getDepotHeadGiftOut(String projectId)throws Exception;

    List<DepotHeadVo4InDetail> findByAll(String beginTime, String endTime, String type, Integer pid, String dids,
                                         Integer oId, Integer offset, Integer rows)throws Exception;

    int findByAllCount(String beginTime, String endTime, String type, Integer pid, String dids, Integer oId)throws Exception;


    List<DepotHeadVo4InOutMCount> findInOutMaterialCount(String beginTime, String endTime, String type,
                                                                Integer pid, String dids, Integer oId, Integer offset, Integer rows)throws Exception ;


    int findInOutMaterialCountTotal(String beginTime, String endTime, String type, Integer pid, String dids, Integer oId)throws Exception;


    List<DepotHeadVo4StatementAccount> findStatementAccount(String beginTime, String endTime, Integer organId,
                                                            String supType, Integer offset, Integer rows)throws Exception;


    int findStatementAccountCount(String beginTime, String endTime, Integer organId, String supType)throws Exception;

    BigDecimal findAllMoney(Integer supplierId, String type, String subType, String mode, String endTime)throws Exception;

    List<DepotHeadVo4List> getDetailByNumber(String number)throws Exception;

    /**
     * description:
     *  新增单据主表及单据子表信息
     */
    void addDepotHeadAndDetail(String beanJson, String inserted, String deleted, String updated) throws Exception;


    /**
     * description:
     * 更新单据主表及单据子表信息
     * @Param: id
     */
    void updateDepotHeadAndDetail(Long id, String beanJson, String inserted, String deleted,
                                  String updated, BigDecimal preTotalPrice)throws Exception;

    /**
     * description:
     *  删除单据主表及子表信息
     * @Param: id
     * @return java.lang.Object
     */
    void deleteDepotHeadAndDetail(Long id) throws Exception;


    /**
     * description:
     *  批量删除单据主表及子表信息
     * @Param: id
     * @return java.lang.Object
     */
    void batchDeleteDepotHeadAndDetail(String ids) throws Exception;

    int batchDeleteDepotHeadByIds(String ids)throws Exception;
}
