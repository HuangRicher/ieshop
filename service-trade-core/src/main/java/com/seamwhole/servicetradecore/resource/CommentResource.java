package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.mapper.model.ShopCommentDO;
import com.seamwhole.servicetradecore.model.ShopComment;
import com.seamwhole.servicetradecore.service.CommentService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentResource {

    @Autowired
    private CommentService commentService;

    @PostMapping("/queryByPage")
    public PagesInfo<ShopCommentDO> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<ShopCommentDO> pageInfo=commentService.queryShopCommentByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<ShopCommentDO>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }

    @GetMapping("/queryObject/{id}")
    public ShopComment queryObject(@PathVariable("id") Integer id){
        return commentService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody ShopComment comment){
        commentService.save(comment);
    }

    @PostMapping("/update")
    public void update(@RequestBody ShopComment comment){
        commentService.updateById(comment);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        commentService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<ShopCommentDO> queryList(@RequestBody Map<String, Object> params){
        return commentService.queryShopCommentList(params);
    }

    @PostMapping("/toggleStatus")
    public void toggleStatus(@RequestBody ShopComment comment){
        commentService.toggleStatus(comment);
    }

    @PostMapping("/queryTotal")
    public int queryTotal(@RequestBody Map<String, Object> params){
        return commentService.queryShopTotal(params);
    }
}
