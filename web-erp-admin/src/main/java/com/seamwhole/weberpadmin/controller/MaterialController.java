package com.seamwhole.weberpadmin.controller;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.MaterialClient;
import com.seamwhole.weberpadmin.constants.BusinessConstants;
import com.seamwhole.weberpadmin.constants.Constants;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import com.seamwhole.weberpadmin.domain.MaterialInfo;
import com.seamwhole.weberpadmin.domain.MaterialVo4Unit;
import com.seamwhole.weberpadmin.utils.ExcelUtils;
import com.seamwhole.weberpadmin.utils.ExportExecUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/material")
public class MaterialController {
    private Logger logger = LoggerFactory.getLogger(MaterialController.class);

    @Resource
    private MaterialClient materialClient;


    @GetMapping(value = "/checkIsExist")
    public String checkIsExist(MaterialInfo materialInfo)throws Exception {

        return materialClient.checkIsExist(materialInfo);
    }

    /**
     * 批量设置状态-启用或者禁用
     */
    @PostMapping(value = "/batchSetEnable")
    public String batchSetEnable(@RequestParam("enabled") Boolean enabled,
                                 @RequestParam("materialIDs") String materialIDs)throws Exception {

        return materialClient.batchSetEnable(enabled,materialIDs);
    }


    /**
     * 根据id来查询商品名称
     */
    @GetMapping(value = "/findById")
    public BaseResponseInfo findById(@RequestParam("id") Long id) throws Exception{
        BaseResponseInfo res =materialClient.findById(id);
        return res;
    }

    /**
     * 查找商品信息-下拉框
     */
    @GetMapping(value = "/findBySelect")
    public JSONArray findBySelect(@RequestParam("mpList") String mpList) throws Exception{
        JSONArray dataArray = materialClient.findBySelect(mpList);
        return dataArray;
    }


    /**
     * 查找商品信息-统计排序
     */
    @GetMapping(value = "/findByOrder")
    public BaseResponseInfo findByOrder()throws Exception {
        BaseResponseInfo res = materialClient.findByOrder();
        return res;
    }

    /**
     * 生成excel表格
     */
    @GetMapping(value = "/exportExcel")
    public BaseResponseInfo exportExcel(@RequestParam("name") String name,
                                        @RequestParam("model") String model,
                                        @RequestParam("categoryId") Long categoryId,
                                        @RequestParam("categoryIds") String categoryIds,
                                        HttpServletRequest request, HttpServletResponse response)throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        String message = "成功";
        try {
            List<MaterialVo4Unit> dataList = materialClient.findByAll(name, model, categoryId, categoryIds);
            String[] names = {"品名", "类型", "型号", "安全存量", "单位", "零售价", "最低售价", "预计采购价", "批发价", "备注", "状态"};
            String title = "商品信息";
            List<String[]> objects = new ArrayList<String[]>();
            if (null != dataList) {
                for (MaterialVo4Unit m : dataList) {
                    String[] objs = new String[11];
                    objs[0] = m.getName();
                    objs[1] = m.getCategoryName();
                    objs[2] = m.getModel();
                    objs[3] = m.getSafetystock() == null? "" : m.getSafetystock().toString();
                    objs[4] = m.getUnit();
                    objs[5] = m.getRetailprice() == null ? "" : m.getRetailprice().toString();
                    objs[6] = m.getLowprice() == null ? "" : m.getLowprice().toString();
                    objs[7] = m.getPresetpriceone() == null ? "" : m.getPresetpriceone().toString();
                    objs[8] = m.getPresetpricetwo() == null ? "" : m.getPresetpricetwo().toString();
                    objs[9] = m.getRemark();
                    objs[10] = m.getEnabled() ? "启用" : "禁用";
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
     * excel表格导入
     */
    @PostMapping(value = "/importExcel")
    public void importExcel(@RequestParam("file") MultipartFile materialFile) throws Exception{
        materialClient.importExcel(materialFile);
    }


    @RequestMapping(value = "/getMaterialEnableSerialNumberList")
    public String getMaterialEnableSerialNumberList(@RequestParam(value = Constants.PAGE_SIZE, required = false) Integer pageSize,
                                                    @RequestParam(value = Constants.CURRENT_PAGE, required = false) Integer currentPage,
                                                    @RequestParam(value = Constants.SEARCH, required = false) String search)throws Exception {


        return materialClient.getMaterialEnableSerialNumberList(pageSize,currentPage,search);
    }


    /**
     *  批量删除商品信息
     */
    @RequestMapping(value = "/batchDeleteMaterialByIds")
    public Object batchDeleteMaterialByIds(@RequestParam("ids") String ids,
                                           @RequestParam(value="deleteType", required =false,defaultValue= BusinessConstants.DELETE_TYPE_NORMAL)
                                                   String deleteType) throws Exception {

        return materialClient.batchDeleteMaterialByIds(ids,deleteType);
    }
}
