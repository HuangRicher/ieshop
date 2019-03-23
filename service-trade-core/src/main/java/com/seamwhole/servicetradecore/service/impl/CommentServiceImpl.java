package com.seamwhole.servicetradecore.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.ShopCommentMapper;
import com.seamwhole.servicetradecore.mapper.ext.ShopCommentExtMapper;
import com.seamwhole.servicetradecore.model.ShopComment;
import com.seamwhole.servicetradecore.model.ShopCommentExample;
import com.seamwhole.servicetradecore.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ShopCommentMapper shopCommentMapper;

    @Autowired
    private ShopCommentExtMapper shopCommentExtMapper;


    public List<ShopComment> queryList(Map<String, Object> map) {
        Integer typeId=null;
        Integer valueId=null;
        Integer goodsId=null;
        Integer userId=null;
        Integer hasPic=null;
        String order="";
        if(map.get("typeId")!=null)
            typeId=(Integer)map.get("typeId");
        if(map.get("valueId")!=null)
            valueId=(Integer)map.get("valueId");
        if(map.get("goodsId")!=null)
            goodsId=(Integer)map.get("goodsId");
        if(map.get("userId")!=null)
            userId=(Integer)map.get("userId");
        if(map.get("hasPic")!=null)
            hasPic=(Integer)map.get("hasPic");
        if(map.get("order")!=null)
            order=(String) map.get("order");
        return shopCommentExtMapper.queryList(userId,typeId,valueId,goodsId,hasPic,order);
    }

    @Override
    public PageInfo<ShopComment> queryByPage(Map<String, Object> map, Integer pageNum, Integer pageSize, String order) {
        Integer typeId=null;
        Integer valueId=null;
        Integer goodsId=null;
        Integer userId=null;
        Integer hasPic=null;
        if(map.get("typeId")!=null)
            typeId=(Integer)map.get("typeId");
        if(map.get("valueId")!=null)
            valueId=(Integer)map.get("valueId");
        if(map.get("goodsId")!=null)
            goodsId=(Integer)map.get("goodsId");
        if(map.get("userId")!=null)
            userId=(Integer)map.get("userId");
        if(map.get("hasPic")!=null)
            hasPic=(Integer)map.get("hasPic");
        if(map.get("order")!=null)
            order=(String) map.get("order");
        Page<ShopComment> pageInfo=PageHelper.startPage(pageNum,pageSize);
        shopCommentExtMapper.queryList(userId,typeId,valueId,goodsId,hasPic,order);
        return pageInfo.toPageInfo();
    }

    public int queryTotal(Map<String, Object> map) {
        Integer typeId=null;
        Integer valueId=null;
        Integer goodsId=null;
        if(map.get("typeId")!=null)
            typeId=(Integer)map.get("typeId");
        if(map.get("valueId")!=null)
            valueId=(Integer)map.get("valueId");
        if(map.get("goodsId")!=null)
            goodsId=(Integer)map.get("goodsId");
        return shopCommentExtMapper.queryTotal(typeId,valueId,goodsId);
    }

    public int queryHasPicTotal(Map<String, Object> map) {
        Integer typeId=null;
        Integer valueId=null;
        if(map.get("typeId")!=null)
            typeId=(Integer)map.get("typeId");
        if(map.get("valueId")!=null)
            valueId=(Integer)map.get("valueId");
        return shopCommentExtMapper.queryHasPicTotal(typeId,valueId);
    }

    public int save(ShopComment comment) {
        return shopCommentMapper.insertSelective(comment);
    }


}
