package com.seamwhole.weberpadmin.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seamwhole.serviceerpcore.constants.ExceptionConstants;
import com.seamwhole.serviceerpcore.exception.BusinessRunTimeException;
import com.seamwhole.serviceerpcore.mapper.vo.TreeNode;
import com.seamwhole.serviceerpcore.model.Organization;
import com.seamwhole.serviceerpcore.service.OrganizationService;
import com.seamwhole.serviceerpcore.utils.BaseResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;


@RestController
@RequestMapping(value = "/organization")
public class OrganizationController {
    private Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Resource
    private OrganizationService organizationService;
    /**
     * 根据id来查询机构信息
     * @param id
     * @param request
     * @return
     */
    @GetMapping(value = "/findById")
    public BaseResponseInfo findById(@RequestParam("id") Long id, HttpServletRequest request) throws Exception {
        BaseResponseInfo res = new BaseResponseInfo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<Organization> dataList = organizationService.findById(id);
            JSONObject outer = new JSONObject();
            if (null != dataList) {
                for (Organization org : dataList) {
                    outer.put("id", org.getId());
                    outer.put("orgFullName", org.getOrgFullName());
                    outer.put("orgAbr", org.getOrgAbr());
                    outer.put("orgParentNo", org.getOrgParentNo());
                    List<Organization> dataParentList = organizationService.findByOrgNo(org.getOrgParentNo());
                    if(dataParentList!=null&&dataParentList.size()>0){
                        //父级机构名称显示简称
                        outer.put("orgParentName", dataParentList.get(0).getOrgAbr());
                    }
                    outer.put("orgTpcd", org.getOrgTpcd());
                    outer.put("orgStcd", org.getOrgStcd());
                    outer.put("orgNo", org.getOrgNo());
                    outer.put("sort", org.getSort());
                    /**
                     * 判断时间不为空再做转化
                     * */
                    if(org.getOrgCreateTime()!=null){
                        outer.put("orgCreateTime", sdf.format(org.getOrgCreateTime()));
                    }
                    if(org.getOrgStopTime()!=null){
                        outer.put("orgStopTime", sdf.format(org.getOrgStopTime()));
                    }
                    outer.put("remark", org.getRemark());
                }
            }
            res.code = 200;
            res.data = outer;
        } catch(Exception e){
            e.printStackTrace();
            res.code = 500;
            res.data = "获取数据失败";
        }
        return res;
    }

    /**
     * 获取机构树数据
     */
    @GetMapping(value = "/getOrganizationTree")
    public JSONArray getOrganizationTree(@RequestParam("id") Long id) throws Exception{
       JSONArray arr=new JSONArray();
       List<TreeNode> organizationTree= organizationService.getOrganizationTree(id);
       if(organizationTree!=null&&organizationTree.size()>0){
           for(TreeNode node:organizationTree){
               String str= JSON.toJSONString(node);
               JSONObject obj= JSON.parseObject(str);
               arr.add(obj) ;
           }
       }
        return arr;
    }


    /**
     *  新增机构信息
     */
    @PostMapping(value = "/addOrganization")
    public Object addOrganization(@RequestParam("info") String beanJson) throws Exception {
        JSONObject result = ExceptionConstants.standardSuccess();
        Organization org= JSON.parseObject(beanJson, Organization.class);
        int i= organizationService.addOrganization(org);
        if(i<1){
            throw new BusinessRunTimeException(ExceptionConstants.ORGANIZATION_ADD_FAILED_CODE,
                    ExceptionConstants.ORGANIZATION_ADD_FAILED_MSG);
        }
        return result;
    }


    /**
     *  修改机构信息
     */
    @PostMapping(value = "/editOrganization")
    public Object editOrganization(@RequestParam("info") String beanJson) throws Exception {
        JSONObject result = ExceptionConstants.standardSuccess();
        Organization org= JSON.parseObject(beanJson, Organization.class);
        int i= organizationService.editOrganization(org);
        if(i<1){
            throw new BusinessRunTimeException(ExceptionConstants.ORGANIZATION_EDIT_FAILED_CODE,
                    ExceptionConstants.ORGANIZATION_EDIT_FAILED_MSG);
        }
        return result;
    }


    /**
     *  批量删除机构信息
     */
    @PostMapping(value = "/batchDeleteOrganization")
    public Object batchDeleteOrganization(@RequestParam("ids") String ids) throws Exception {
        JSONObject result = ExceptionConstants.standardSuccess();
        int i= organizationService.batchDeleteOrganizationByIds(ids);
        if(i<1){
            throw new BusinessRunTimeException(ExceptionConstants.ORGANIZATION_DELETE_FAILED_CODE,
                    ExceptionConstants.ORGANIZATION_DELETE_FAILED_MSG);
        }
        return result;
    }
}
