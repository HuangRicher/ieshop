package com.seamwhole.serviceerpcore.service;

import com.seamwhole.serviceerpcore.model.Supplier;
import com.seamwhole.serviceerpcore.utils.BaseResponseInfo;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;


public interface SupplierService {


    Supplier getSupplier(long id);

    List<Supplier> getSupplier();

    List<Supplier> select(String supplier, String type, String phonenum, String telephone, String description, int offset, int rows);

    Long countSupplier(String supplier, String type, String phonenum, String telephone, String description);

    int insertSupplier(String beanJson, HttpServletRequest request);

    int updateSupplier(String beanJson, Long id);

    int deleteSupplier(Long id);

    int batchDeleteSupplier(String ids);

    int checkIsNameExist(Long id, String name);

    int updateAdvanceIn(Long supplierId, BigDecimal advanceIn);

    List<Supplier> findBySelectCus();

    List<Supplier> findBySelectSup();

    List<Supplier> findBySelectRetail() ;

    List<Supplier> findById(Long supplierId) ;

    int batchSetEnable(Boolean enabled, String supplierIDs);

    List<Supplier> findUserCustomer();

    List<Supplier> findByAll(String supplier, String type, String phonenum, String telephone, String description);
    
    BaseResponseInfo importExcel(List<Supplier> mList) throws Exception;
    
    int batchDeleteSupplierByIds(String ids);
    
    /**
     *正常删除，要考虑数据完整性，进行完整性校验
     */
    int batchDeleteSupplierByIdsNormal(String ids) throws Exception;
}
