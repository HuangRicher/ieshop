package com.seamwhole.serviceerpcore.controller;

import com.alibaba.fastjson.JSONObject;
import com.seamwhole.serviceerpcore.constants.BusinessConstants;
import com.seamwhole.serviceerpcore.constants.ExceptionConstants;
import com.seamwhole.serviceerpcore.exception.BusinessRunTimeException;
import com.seamwhole.serviceerpcore.mapper.vo.AccountHeadVo4ListEx;
import com.seamwhole.serviceerpcore.service.AccountHeadService;
import com.seamwhole.serviceerpcore.utils.BaseResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/accountHead")
public class AccountHeadController {
    private Logger logger = LoggerFactory.getLogger(AccountHeadController.class);

    @Resource
    private AccountHeadService accountHeadService;

    /**
     * 获取最大的id
     * @param request
     * @return
     */
    @GetMapping(value = "/getMaxId")
    public BaseResponseInfo getMaxId(HttpServletRequest request) {
        BaseResponseInfo res = new BaseResponseInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Long maxId = accountHeadService.getMaxId();
            map.put("maxId", maxId);
            res.code = 200;
            res.data = map;
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }

    /**
     * 查询单位的累计应收和累计应付，收预付款不计入此处
     * @param supplierId
     * @param endTime
     * @param supType
     * @param request
     * @return
     */
    @GetMapping(value = "/findTotalPay")
    public BaseResponseInfo findTotalPay(@RequestParam("supplierId") Integer supplierId,
                                         @RequestParam("endTime") String endTime,
                                         @RequestParam("supType") String supType,
                                         HttpServletRequest request) {
        BaseResponseInfo res = new BaseResponseInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject outer = new JSONObject();
            BigDecimal sum = BigDecimal.ZERO;
            String getS = supplierId.toString();
            int i = 1;
            if (supType.equals("customer")) { //客户
                i = 1;
            } else if (supType.equals("vendor")) { //供应商
                i = -1;
            }
            //收付款部分
            sum = sum.add((allMoney(getS, "付款", "合计",endTime).add(allMoney(getS, "付款", "实际",endTime))).multiply(new BigDecimal(i)));
            sum = sum.subtract((allMoney(getS, "收款", "合计",endTime).add(allMoney(getS, "收款", "实际",endTime))).multiply(new BigDecimal(i)));
            sum = sum.add((allMoney(getS, "收入", "合计",endTime).subtract(allMoney(getS, "收入", "实际",endTime))).multiply(new BigDecimal(i)));
            sum = sum.subtract((allMoney(getS, "支出", "合计",endTime).subtract(allMoney(getS, "支出", "实际",endTime))).multiply(new BigDecimal(i)));
            outer.put("getAllMoney", sum);
            map.put("rows", outer);
            res.code = 200;
            res.data = map;
        } catch (Exception e) {
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }

    /**
     * 根据编号查询单据信息
     * @param billNo
     * @param request
     * @return
     */
    @GetMapping(value = "/getDetailByNumber")
    public BaseResponseInfo getDetailByNumber(@RequestParam("billNo") String billNo,
                                              HttpServletRequest request) {
        BaseResponseInfo res = new BaseResponseInfo();
        AccountHeadVo4ListEx ahl = new AccountHeadVo4ListEx();
        try {
            List<AccountHeadVo4ListEx> list = accountHeadService.getDetailByNumber(billNo);
            if(list.size() == 1) {
                ahl = list.get(0);
            }
            res.code = 200;
            res.data = ahl;
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }

    /**
     * 统计总金额
     * @param getS
     * @param type
     * @param mode 合计或者金额
     * @param endTime
     * @return
     */
    public BigDecimal allMoney(String getS, String type, String mode, String endTime) {
        BigDecimal allMoney = BigDecimal.ZERO;
        try {
            Integer supplierId = Integer.valueOf(getS);
            BigDecimal sum = accountHeadService.findAllMoney(supplierId, type, mode, endTime);
            if(sum != null) {
                allMoney = sum;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回正数，如果负数也转为正数
        if ((allMoney.compareTo(BigDecimal.ZERO))==-1) {
            allMoney = allMoney.abs();
        }
        return allMoney;
    }
    /**
     * description:
     *  批量删除账户信息
     * @Param: ids
     * @return java.lang.Object
     */
    @RequestMapping(value = "/batchDeleteAccountHeadByIds")
    public Object batchDeleteAccountHeadByIds(@RequestParam("ids") String ids, @RequestParam(value="deleteType",
            required =false,defaultValue= BusinessConstants.DELETE_TYPE_NORMAL)String deleteType) throws Exception {

        JSONObject result = ExceptionConstants.standardSuccess();
        int i=0;
        if(BusinessConstants.DELETE_TYPE_NORMAL.equals(deleteType)){
            i= accountHeadService.batchDeleteAccountHeadByIdsNormal(ids);
        }else if(BusinessConstants.DELETE_TYPE_FORCE.equals(deleteType)){
            i= accountHeadService.batchDeleteAccountHeadByIds(ids);
        }else{
            logger.error("异常码[{}],异常提示[{}],参数,ids[{}],deleteType[{}]",
                    ExceptionConstants.DELETE_REFUSED_CODE,ExceptionConstants.DELETE_REFUSED_MSG,ids,deleteType);
            throw new BusinessRunTimeException(ExceptionConstants.DELETE_REFUSED_CODE,
                    ExceptionConstants.DELETE_REFUSED_MSG);
        }
        if(i<1){
            logger.error("异常码[{}],异常提示[{}],参数,ids[{}]",
                    ExceptionConstants.ACCOUNT_HEAD_DELETE_FAILED_CODE,ExceptionConstants.ACCOUNT_HEAD_DELETE_FAILED_MSG,ids);
            throw new BusinessRunTimeException(ExceptionConstants.ACCOUNT_HEAD_DELETE_FAILED_CODE,
                    ExceptionConstants.ACCOUNT_HEAD_DELETE_FAILED_MSG);
        }
        return result;
    }

}
