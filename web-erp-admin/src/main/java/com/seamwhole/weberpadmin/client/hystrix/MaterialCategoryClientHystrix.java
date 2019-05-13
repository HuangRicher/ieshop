package com.seamwhole.weberpadmin.client.hystrix;

import com.alibaba.fastjson.JSONArray;
import com.seamwhole.weberpadmin.client.MaterialCategoryClient;
import com.seamwhole.weberpadmin.domain.BaseResponseInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MaterialCategoryClientHystrix  implements MaterialCategoryClient{
    @Override
    public BaseResponseInfo getAllList(Long parentId) {
        return null;
    }

    @Override
    public BaseResponseInfo findById(Long id) {
        return null;
    }

    @Override
    public JSONArray getMaterialCategoryTree(Long id) {
        return null;
    }

    @Override
    public Object addMaterialCategory(String beanJson) {
        return null;
    }

    @Override
    public Object editMaterialCategory(String beanJson) {
        return null;
    }

    @Override
    public Object batchDeleteMaterialCategory(String ids, String deleteType) {
        return null;
    }
}
