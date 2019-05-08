package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.Supplier;
import com.seamwhole.serviceerpcore.utils.BaseResponseInfo;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;


public interface SupplierService {


    Supplier getSupplier(long id)throws Exception;

    List<Supplier> getSupplier()throws Exception;

    List<Supplier> select(String supplier, String type, String phonenum, String telephone, String description, int offset, int rows)throws Exception;

    Long countSupplier(String supplier, String type, String phonenum, String telephone, String description)throws Exception;

    int insertSupplier(String beanJson, HttpServletRequest request)throws Exception;

    int updateSupplier(String beanJson, Long id)throws Exception;

    int deleteSupplier(Long id)throws Exception;

    int batchDeleteSupplier(String ids)throws Exception;

    int checkIsNameExist(Long id, String name)throws Exception;

    int updateAdvanceIn(Long supplierId, BigDecimal advanceIn)throws Exception;

    List<Supplier> findBySelectCus()throws Exception;

    List<Supplier> findBySelectSup()throws Exception;

    List<Supplier> findBySelectRetail() throws Exception;

    List<Supplier> findById(Long supplierId) throws Exception;

    int batchSetEnable(Boolean enabled, String supplierIDs)throws Exception;

    List<Supplier> findUserCustomer()throws Exception;

    List<Supplier> findByAll(String supplier, String type, String phonenum, String telephone, String description)throws Exception;
    
    BaseResponseInfo importExcel(List<Supplier> mList) throws Exception;
    
    int batchDeleteSupplierByIds(String ids)throws Exception;
    
    /**
     *正常删除，要考虑数据完整性，进行完整性校验
     */
    int batchDeleteSupplierByIdsNormal(String ids) throws Exception;
}
