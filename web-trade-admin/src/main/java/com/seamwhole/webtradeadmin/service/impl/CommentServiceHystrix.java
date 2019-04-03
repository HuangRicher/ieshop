package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ShopComment;
import com.seamwhole.webtradeadmin.info.ShopCommentDO;
import com.seamwhole.webtradeadmin.service.CommentService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CommentServiceHystrix implements CommentService {
    @Override
    public PagesInfo<ShopCommentDO> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public ShopComment queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(ShopComment comment) {

    }

    @Override
    public void update(ShopComment comment) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<ShopCommentDO> queryList(Map<String, Object> params) {
        return null;
    }

    @Override
    public void toggleStatus(ShopComment comment) {

    }

    @Override
    public int queryTotal(Map<String, Object> params) {
        return 0;
    }
}
