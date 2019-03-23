package com.seamwhole.servicetradecore.service.impl;

import com.seamwhole.servicetradecore.mapper.CommentPictureMapper;
import com.seamwhole.servicetradecore.model.CommentPicture;
import com.seamwhole.servicetradecore.model.CommentPictureExample;
import com.seamwhole.servicetradecore.service.CommentPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CommentPictureServiceImpl implements CommentPictureService {
    @Autowired
    private CommentPictureMapper commentPictureDao;


    public List<CommentPicture> queryList(Map<String, Object> map) {
        CommentPictureExample example=new CommentPictureExample();
        CommentPictureExample.Criteria criteria=example.createCriteria();
        if(map.get("commentId")!=null)
            criteria.andCommentIdEqualTo((int)map.get("commentId"));
        return commentPictureDao.selectByExample(example);
    }

    public int save(CommentPicture comment) {
        return commentPictureDao.insertSelective(comment);
    }


}
