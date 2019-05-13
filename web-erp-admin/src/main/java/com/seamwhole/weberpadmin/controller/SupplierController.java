package com.seamwhole.weberpadmin.controller;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.SupplierClient;
import com.seamwhole.weberpadmin.constants.BusinessConstants;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import com.seamwhole.weberpadmin.domain.Supplier;
import com.seamwhole.weberpadmin.utils.ExcelUtils;
import com.seamwhole.weberpadmin.utils.ExportExecUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {
    private Logger logger = LoggerFactory.getLogger(SupplierController.class);

    @Resource
    private SupplierClient supplierClient;


    /**
     * 更新供应商-只更新预付款，其余用原来的值
     */
    @PostMapping(value = "/updateAdvanceIn")
    public String updateAdvanceIn(@RequestParam("supplierId") Long supplierId,
                                  @RequestParam("advanceIn") BigDecimal advanceIn)throws Exception {

        return supplierClient.updateAdvanceIn(supplierId,advanceIn);
    }


    /**
     * 查找客户信息-下拉框
     */
    @PostMapping(value = "/findBySelect_cus")
    public JSONArray findBySelectCus()throws Exception {
        JSONArray arr = supplierClient.findBySelectCus();
        return arr;
    }


    /**
     * 查找供应商信息-下拉框
     */
    @PostMapping(value = "/findBySelect_sup")
    public JSONArray findBySelectSup() throws Exception{
        JSONArray arr = supplierClient.findBySelectCus();
        return arr;
    }


    /**
     * 查找会员信息-下拉框
     */
    @PostMapping(value = "/findBySelect_retail")
    public JSONArray findBySelectRetail()throws Exception {
        JSONArray arr = supplierClient.findBySelectRetail();
        return arr;
    }

    /**
     * 根据id查找信息
     */
    @GetMapping(value = "/findById")
    public BaseResponseInfo findById(@RequestParam("supplierId") Long supplierId)throws Exception {
        BaseResponseInfo res = supplierClient.findById(supplierId);
        return res;
    }


    /**
     * 批量设置状态-启用或者禁用
     */
    @PostMapping(value = "/batchSetEnable")
    public String batchSetEnable(@RequestParam("enabled") Boolean enabled,
                                 @RequestParam("supplierIDs") String supplierIDs)throws Exception {

        return supplierClient.batchSetEnable(enabled,supplierIDs);
    }

    /**
     * 用户对应客户显示
     */
    @PostMapping(value = "/findUserCustomer")
    public JSONArray findUserCustomer(@RequestParam("UBType") String type,
                                      @RequestParam("UBKeyId") String keyId) throws Exception{
        JSONArray arr = supplierClient.findUserCustomer(type,keyId);
        return arr;
    }

    /**
     * 生成excel表格
     */
    @GetMapping(value = "/exportExcel")
    public BaseResponseInfo exportExcel(@RequestParam("supplier") String supplier,
                                        @RequestParam("type") String type,
                                        @RequestParam("phonenum") String phonenum,
                                        @RequestParam("telephone") String telephone,
                                        @RequestParam("description") String description,
                                        HttpServletResponse response)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "成功";
        try {
            List<Supplier> dataList = supplierClient.findByAll(supplier, type, phonenum, telephone, description);
            String[] names = {"名称", "类型", "联系人", "电话", "电子邮箱", "预收款", "期初应收", "期初应付", "备注", "传真", "手机", "地址", "纳税人识别号", "开户行", "账号", "税率", "状态"};
            String title = "信息报表";
            List<String[]> objects = new ArrayList<String[]>();
            if (null != dataList) {
                for (Supplier s : dataList) {
                    String[] objs = new String[17];
                    objs[0] = s.getSupplier();
                    objs[1] = s.getType();
                    objs[2] = s.getContacts();
                    objs[3] = s.getPhonenum();
                    objs[4] = s.getEmail();
                    objs[5] = s.getAdvancein() == null? "" : s.getAdvancein().toString();
                    objs[6] = s.getBeginneedget() == null? "" : s.getBeginneedget().toString();
                    objs[7] = s.getBeginneedpay() == null? "" : s.getBeginneedpay().toString();
                    objs[8] = s.getDescription();
                    objs[9] = s.getFax();
                    objs[10] = s.getTelephone();
                    objs[11] = s.getAddress();
                    objs[12] = s.getTaxnum();
                    objs[13] = s.getBankname();
                    objs[14] = s.getAccountnumber();
                    objs[15] = s.getTaxrate() == null? "" : s.getTaxrate().toString();
                    objs[16] = s.getEnabled() ? "启用" : "禁用";
                    objects.add(objs);
                }
            }
            File file = ExcelUtils.exportObjectsWithoutTitle(title, names, title, objects);
            ExportExecUtil.showExec(file, file.getName(), response);
            res.code = 200;
        } catch (Exception e) {
            e.printStackTrace();
            message = "导出失败";
            res.code = 500;
        } finally {
            map.put("message", message);
            res.data = map;
        }
        return res;
    }

    /**
     * 导入excel表格-供应商
     */
    @PostMapping(value = "/importExcelVendor")
    public void importExcelVendor(@RequestParam("file") MultipartFile supplierFile) throws Exception{
        supplierClient.importExcelVendor(supplierFile);
    }

    /**
     * 导入excel表格-客户
     */
    @PostMapping(value = "/importExcelCustomer")
    public void importExcelCustomer(@RequestParam("file")MultipartFile supplierFile) throws Exception{
        supplierClient.importExcelCustomer(supplierFile);
    }

    /**
     * 导入excel表格-会员
     */
    @PostMapping(value = "/importExcelMember")
    public void importExcelMember(@RequestParam("file")MultipartFile supplierFile) throws Exception{
        supplierClient.importExcelMember(supplierFile);
    }


    /**
     *  批量删除供应商信息
     */
    @RequestMapping(value = "/batchDeleteSupplierByIds")
    public Object batchDeleteSupplierByIds(@RequestParam("ids") String ids,
                                           @RequestParam(value="deleteType", required =false,defaultValue= BusinessConstants.DELETE_TYPE_NORMAL)
                                                   String deleteType) throws Exception {

        return supplierClient.batchDeleteSupplierByIds(ids,deleteType);
    }

}
