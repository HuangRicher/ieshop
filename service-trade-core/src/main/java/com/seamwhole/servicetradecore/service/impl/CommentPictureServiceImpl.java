package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.CommentPictureMapper;
import com.seamwhole.servicetradecore.model.CommentPicture;
import com.seamwhole.servicetradecore.model.CommentPictureExample;
import com.seamwhole.servicetradecore.service.CommentPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class CommentPictureServiceImpl implements CommentPictureService {
    @Autowired
    private CommentPictureMapper commentPictureMapper;


    @Override
    public List<CommentPicture> queryList(Map<String, Object> map) {
        CommentPictureExample example=new CommentPictureExample();
        CommentPictureExample.Criteria criteria=example.createCriteria();
        if(map.get("commentId")!=null)
            criteria.andCommentIdEqualTo((int)map.get("commentId"));
        return commentPictureMapper.selectByExample(example);
    }

    @Override
    public int save(CommentPicture comment) {
        return commentPictureMapper.insertSelective(comment);
    }

    @Override
    public PageInfo<CommentPicture> queryByPage(Map<String, Object> params, Integer pageNum, Integer pageSize) {
        CommentPictureExample example=new CommentPictureExample();
        CommentPictureExample.Criteria criteria=example.createCriteria();
        if(params.get("commentId")!=null)
            criteria.andCommentIdEqualTo((int)params.get("commentId"));
        Page<CommentPicture> page= PageHelper.startPage(pageNum,pageSize);
        commentPictureMapper.selectByExample(example);
        return page.toPageInfo();
    }

    @Override
    public CommentPicture getById(Integer id) {
        return commentPictureMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateById(CommentPicture commentPicture) {
        commentPictureMapper.updateByPrimaryKeySelective(commentPicture);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        CommentPictureExample example=new CommentPictureExample();
        example.createCriteria().andIdIn(Arrays.asList(ids));
        commentPictureMapper.deleteByExample(example);
    }
}
