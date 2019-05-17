package com.seamwhole.weberpadmin.controller;

import com.seamwhole.weberpadmin.client.DepotHeadClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import com.seamwhole.weberpadmin.domain.DepotHeadInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping(value = "/depotHead")
public class DepotHeadController {
    private Logger logger = LoggerFactory.getLogger(DepotHeadController.class);


    @Autowired
    private DepotHeadClient depotHeadClient;


    /**
     * 批量设置状态-审核或者反审核
     */
    @PostMapping(value = "/batchSetStatus")
    public String batchSetStatus(@RequestParam("status") String status,
                                 @RequestParam("depotHeadIDs") String depotHeadIDs) throws Exception{

        return depotHeadClient.batchSetStatus(status,depotHeadIDs);
    }

    /**
     * 单据编号生成接口
     * @return
     */
    @GetMapping(value = "/buildNumber")
    public BaseResponseInfo buildNumber()throws Exception {
        BaseResponseInfo res = depotHeadClient.buildNumber();
        return res;
    }

    /**
     * 获取最大的id
     * @return
     */
    @GetMapping(value = "/getMaxId")
    public BaseResponseInfo getMaxId()throws Exception {
        return depotHeadClient.getMaxId();
    }

    /**
     * 查找单据_根据月份(报表)
     */
    @GetMapping(value = "/findByMonth")
    public BaseResponseInfo findByMonth(@RequestParam("monthTime") String monthTime)throws Exception {
        BaseResponseInfo res = depotHeadClient.findByMonth(monthTime);
        return res;
    }

    /**
     * 入库出库明细接口
     */
    @GetMapping(value = "/findInDetail")
    public BaseResponseInfo findInDetail(DepotHeadInfo depotHeadInfo)throws Exception {
        BaseResponseInfo res = depotHeadClient.findInDetail(depotHeadInfo);
        return res;
    }

    /**
     * 入库出库统计接口
     */
    @GetMapping(value = "/findInOutMaterialCount")
    public BaseResponseInfo findInOutMaterialCount(DepotHeadInfo depotHeadInfo)throws Exception {
        BaseResponseInfo res = depotHeadClient.findInOutMaterialCount(depotHeadInfo);
        return res;
    }


    /**
     * 对账单接口
     */
    @GetMapping(value = "/findStatementAccount")
    public BaseResponseInfo findStatementAccount(DepotHeadInfo info) throws Exception{

        BaseResponseInfo res = depotHeadClient.findStatementAccount(info);
        return res;
    }

    /**
     * 查询单位的累计应收和累计应付，零售不能计入
     */
    @GetMapping(value = "/findTotalPay")
    public BaseResponseInfo findTotalPay(@RequestParam("supplierId") Integer supplierId,
                                         @RequestParam("endTime") String endTime,
                                         @RequestParam("supType") String supType)throws Exception {
        BaseResponseInfo res = depotHeadClient.findTotalPay(supplierId,endTime,supType);
        return res;
    }

    /**
     * 根据编号查询单据信息
     */
    @GetMapping(value = "/getDetailByNumber")
    public BaseResponseInfo getDetailByNumber(@RequestParam("number") String number)throws Exception {
        BaseResponseInfo res = depotHeadClient.getDetailByNumber(number);
        return res;
    }



    /**
     *  新增单据主表及单据子表信息
     */
    @RequestMapping(value = "/addDepotHeadAndDetail")
    public Object addDepotHeadAndDetail(@RequestParam("info") String beanJson,
                                        @RequestParam("inserted") String inserted,
                                        @RequestParam("deleted") String deleted,
                                        @RequestParam("updated") String updated) throws  Exception{

        return depotHeadClient.addDepotHeadAndDetail(beanJson,inserted,deleted,updated);
    }


    /**
     * 更新单据主表及单据子表信息
     */
    @RequestMapping(value = "/updateDepotHeadAndDetail")
    public Object updateDepotHeadAndDetail(@RequestParam("id") Long id,
                                           @RequestParam("info") String beanJson,
                                           @RequestParam("inserted") String inserted,
                                           @RequestParam("deleted") String deleted,
                                           @RequestParam("updated") String updated,
                                           @RequestParam("preTotalPrice") BigDecimal preTotalPrice) throws  Exception{

        return depotHeadClient.updateDepotHeadAndDetail(id,beanJson,inserted,deleted,updated,preTotalPrice);
    }


    /**
     *  删除单据主表及子表信息
     */
    @RequestMapping(value = "/deleteDepotHeadAndDetail")
    public Object deleteDepotHeadAndDetail(@RequestParam("id") Long id) throws  Exception{

        return depotHeadClient.deleteDepotHeadAndDetail(id);
    }


    /**
     *  删除单据主表及子表信息
     */
    @RequestMapping(value = "/batchDeleteDepotHeadAndDetail")
    public Object batchDeleteDepotHeadAndDetail(@RequestParam("ids") String ids) throws  Exception{

        return depotHeadClient.batchDeleteDepotHeadAndDetail(ids);
    }
}
