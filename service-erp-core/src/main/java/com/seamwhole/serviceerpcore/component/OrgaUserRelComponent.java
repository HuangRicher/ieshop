package com.seamwhole.serviceerpcore.component;

import com.seamwhole.serviceerpcore.service.ICommonQuery;
import com.seamwhole.serviceerpcore.service.OrgUserRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Service(value = "orgaUserRel_component")
@OrgaUserRelResource
public class OrgaUserRelComponent implements ICommonQuery {
    @Resource
    private OrgUserRelService orgUserRelService;
    @Override
    public Object selectOne(String condition)throws Exception {
        return null;
    }

    @Override
    public List<?> select(Map<String, String> parameterMap)throws Exception {
        return getOrgaUserRelList(parameterMap);
    }
    private List<?> getOrgaUserRelList(Map<String, String> map)throws Exception {
        return null;
    }
    @Override
    public Long counts(Map<String, String> parameterMap)throws Exception {
        return null;
    }

    @Override
    public int insert(String beanJson, HttpServletRequest request)throws Exception {
        return orgUserRelService.insertOrgUserRel(beanJson,request);
    }

    @Override
    public int update(String beanJson, Long id)throws Exception {
        return orgUserRelService.updateOrgUserRel(beanJson,id);
    }

    @Override
    public int delete(Long id)throws Exception {
        return orgUserRelService.deleteOrgUserRel(id);
    }

    @Override
    public int batchDelete(String ids)throws Exception {
        return orgUserRelService.batchDeleteOrgUserRel(ids);
    }

    @Override
    public int checkIsNameExist(Long id, String name)throws Exception {
        return 0;
    }
}
