package com.seamwhole.webtradeadmin.service.impl;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.CommentPicture;
import com.seamwhole.webtradeadmin.service.CommentPictureService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CommentPictureServiceHystrix implements CommentPictureService {
    @Override
    public PagesInfo<CommentPicture> queryByPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public CommentPicture queryObject(Integer id) {
        return null;
    }

    @Override
    public void save(CommentPicture commentPicture) {

    }

    @Override
    public void update(CommentPicture commentPicture) {

    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    @Override
    public List<CommentPicture> queryList(Map<String, Object> params) {
        return null;
    }
}
