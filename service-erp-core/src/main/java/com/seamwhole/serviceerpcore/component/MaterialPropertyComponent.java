package com.seamwhole.serviceerpcore.component;

import com.seamwhole.serviceerpcore.service.ICommonQuery;
import com.seamwhole.serviceerpcore.service.MaterialPropertyService;
import com.seamwhole.serviceerpcore.utils.Constants;
import com.seamwhole.serviceerpcore.utils.QueryUtils;
import com.seamwhole.serviceerpcore.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service(value = "materialProperty_component")
@MaterialPropertyResource
public class MaterialPropertyComponent implements ICommonQuery {

    @Resource
    private MaterialPropertyService materialPropertyService;

    @Override
    public Object selectOne(String condition)throws Exception {
        return null;
    }

    @Override
    public List<?> select(Map<String, String> map)throws Exception {
        return getMaterialPropertyList(map);
    }

    private List<?> getMaterialPropertyList(Map<String, String> map)throws Exception {
        String search = map.get(Constants.SEARCH);
        String name = StringUtil.getInfo(search, "name");
        String order = QueryUtils.order(map);
        return materialPropertyService.select(name, QueryUtils.offset(map), QueryUtils.rows(map));
    }

    @Override
    public Long counts(Map<String, String> map)throws Exception {
        String search = map.get(Constants.SEARCH);
        String name = StringUtil.getInfo(search, "name");
        return materialPropertyService.countMaterialProperty(name);
    }

    @Override
    public int insert(String beanJson, HttpServletRequest request)throws Exception {
        return materialPropertyService.insertMaterialProperty(beanJson, request);
    }

    @Override
    public int update(String beanJson, Long id)throws Exception {
        return materialPropertyService.updateMaterialProperty(beanJson, id);
    }

    @Override
    public int delete(Long id)throws Exception {
        return materialPropertyService.deleteMaterialProperty(id);
    }

    @Override
    public int batchDelete(String ids)throws Exception {
        return materialPropertyService.batchDeleteMaterialProperty(ids);
    }

    @Override
    public int checkIsNameExist(Long id, String name)throws Exception {
        return materialPropertyService.checkIsNameExist(id, name);
    }

}
