package com.seamwhole.serviceerpcore.component;

import com.seamwhole.serviceerpcore.service.ICommonQuery;
import com.seamwhole.serviceerpcore.service.OrganizationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Service(value = "organization_component")
@OrganizationResource
public class OrganizationComponent implements ICommonQuery {
    @Resource
    private OrganizationService organizationService;
    @Override
    public Object selectOne(String condition)throws Exception {
        return null;
    }

    @Override
    public List<?> select(Map<String, String> parameterMap)throws Exception {
        return getOrganizationList(parameterMap);
    }
    private List<?> getOrganizationList(Map<String, String> map)throws Exception {
        return null;
    }
    @Override
    public Long counts(Map<String, String> parameterMap)throws Exception {
        return null;
    }

    @Override
    public int insert(String beanJson, HttpServletRequest request)throws Exception {
        return organizationService.insertOrganization(beanJson,request);
    }

    @Override
    public int update(String beanJson, Long id)throws Exception {
        return organizationService.updateOrganization(beanJson,id);
    }

    @Override
    public int delete(Long id)throws Exception {
        return organizationService.deleteOrganization(id);
    }

    @Override
    public int batchDelete(String ids)throws Exception {
        return organizationService.batchDeleteOrganization(ids);
    }

    @Override
    public int checkIsNameExist(Long id, String name)throws Exception {
        return 0;
    }
}
