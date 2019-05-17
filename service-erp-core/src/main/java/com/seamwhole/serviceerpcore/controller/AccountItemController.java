package com.seamwhole.serviceerpcore.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seamwhole.serviceerpcore.constants.ExceptionConstants;
import com.seamwhole.serviceerpcore.domain.AccountItemInfo;
import com.seamwhole.serviceerpcore.mapper.vo.AccountItemVo4List;
import com.seamwhole.serviceerpcore.exception.BusinessRunTimeException;
import com.seamwhole.serviceerpcore.service.AccountItemService;
import com.seamwhole.serviceerpcore.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.seamwhole.serviceerpcore.utils.ResponseJsonUtil.returnJson;


@RestController
@RequestMapping(value = "/accountItem")
public class AccountItemController {
    private Logger logger = LoggerFactory.getLogger(AccountItemController.class);

    @Resource
    private AccountItemService accountItemService;


    /**
     *  业务逻辑操作放在service层，controller只做参数解析和视图封装
     * @Param: inserted
     * @Param: deleted
     * @Param: updated
     * @Param: headerId
     * @Param: listType
     * @Param: request
     * @return java.lang.String
     */
    @PostMapping(value = "/saveDetails")
    public String saveDetails(@RequestBody AccountItemInfo info) throws Exception{

        Map<String, Object> objectMap = new HashMap<String, Object>();
        try {
            accountItemService.saveDetials(info.getInserted(),info.getDeleted(),info.getUpdated(),info.getHeaderId(),info.getListType());
            return returnJson(objectMap, ErpInfo.OK.name, ErpInfo.OK.code);
        } catch (DataAccessException e) {
            e.printStackTrace();
            logger.error(">>>>>>>>>>>>>>>>>>>保存明细信息异常", e);
            return returnJson(objectMap, ErpInfo.ERROR.name, ErpInfo.ERROR.code);
        }
    }

    @GetMapping(value = "/getDetailList")
    public BaseResponseInfo getDetailList(@RequestParam("headerId") Long headerId,
                                          HttpServletRequest request)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<AccountItemVo4List> dataList = new ArrayList<AccountItemVo4List>();
            if(headerId != 0) {
                dataList = accountItemService.getDetailList(headerId);
            }
            JSONObject outer = new JSONObject();
            outer.put("total", dataList.size());
            //存放数据json数组
            JSONArray dataArray = new JSONArray();
            if (null != dataList) {
                for (AccountItemVo4List ai : dataList) {
                    JSONObject item = new JSONObject();
                    item.put("Id", ai.getId());
                    item.put("AccountId", ai.getAccountid());
                    item.put("AccountName", ai.getAccountName());
                    item.put("InOutItemId", ai.getInoutitemid());
                    item.put("InOutItemName", ai.getInOutItemName());
                    BigDecimal eachAmount = ai.getEachamount();
                    item.put("EachAmount", (eachAmount.compareTo(BigDecimal.ZERO))==-1 ? BigDecimal.ZERO.subtract(eachAmount): eachAmount);
                    item.put("Remark", ai.getRemark());
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            res.code = 200;
            res.data = outer;
        } catch (Exception e) {
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }
    /**
     *  批量删除财务明细信息
     * @Param: ids
     * @return java.lang.Object
     */
    @PostMapping(value = "/batchDeleteAccountItemByIds")
    public Object batchDeleteAccountItemByIds(@RequestParam("ids") String ids) throws Exception {

        JSONObject result = ExceptionConstants.standardSuccess();
        int i= accountItemService.batchDeleteAccountItemByIds(ids);
        if(i<1){
            logger.error("异常码[{}],异常提示[{}],参数,ids[{}]",
                    ExceptionConstants.ACCOUNT_ITEM_DELETE_FAILED_CODE,ExceptionConstants.ACCOUNT_ITEM_DELETE_FAILED_MSG,ids);
            throw new BusinessRunTimeException(ExceptionConstants.ACCOUNT_ITEM_DELETE_FAILED_CODE,
                    ExceptionConstants.ACCOUNT_ITEM_DELETE_FAILED_MSG);
        }
        return result;
    }

}
