package com.seamwhole.serviceerpcore.component;

import com.seamwhole.serviceerpcore.service.ICommonQuery;
import com.seamwhole.serviceerpcore.service.MaterialService;
import com.seamwhole.serviceerpcore.utils.Constants;
import com.seamwhole.serviceerpcore.utils.QueryUtils;
import com.seamwhole.serviceerpcore.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service(value = "material_component")
@MaterialResource
public class MaterialComponent implements ICommonQuery {

    @Resource
    private MaterialService materialService;

    @Override
    public Object selectOne(String condition)throws Exception {
        return null;
    }

    @Override
    public List<?> select(Map<String, String> map)throws Exception {
        return getMaterialList(map);
    }

    private List<?> getMaterialList(Map<String, String> map) throws Exception{
        String search = map.get(Constants.SEARCH);
        String name = StringUtil.getInfo(search, "name");
        String model = StringUtil.getInfo(search, "model");
        Long categoryId = Long.parseLong(StringUtil.getInfo(search, "categoryId"));
        String categoryIds = StringUtil.getInfo(search, "categoryIds");
        String mpList = StringUtil.getInfo(search, "mpList");
        String order = QueryUtils.order(map);
        return materialService.select(name, model,categoryId,categoryIds,mpList, QueryUtils.offset(map), QueryUtils.rows(map));
    }

    @Override
    public Long counts(Map<String, String> map)throws Exception {
        String search = map.get(Constants.SEARCH);
        String name = StringUtil.getInfo(search, "name");
        String model = StringUtil.getInfo(search, "model");
        Long categoryId = Long.parseLong(StringUtil.getInfo(search, "categoryId"));
        String categoryIds = StringUtil.getInfo(search, "categoryIds");
        String mpList = StringUtil.getInfo(search, "mpList");
        return materialService.countMaterial(name, model,categoryId,categoryIds,mpList);
    }

    @Override
    public int insert(String beanJson, HttpServletRequest request) throws Exception{
        return materialService.insertMaterial(beanJson, request);
    }

    @Override
    public int update(String beanJson, Long id)throws Exception {
        return materialService.updateMaterial(beanJson, id);
    }

    @Override
    public int delete(Long id)throws Exception {
        return materialService.deleteMaterial(id);
    }

    @Override
    public int batchDelete(String ids)throws Exception {
        return materialService.batchDeleteMaterial(ids);
    }

    @Override
    public int checkIsNameExist(Long id, String name)throws Exception {
        return materialService.checkIsNameExist(id, name);
    }

}
