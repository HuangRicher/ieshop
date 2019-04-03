package com.seamwhole.servicetradecore.resource;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.model.CommentPicture;
import com.seamwhole.servicetradecore.service.CommentPictureService;
import com.seamwhole.util.PagesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commentPicture")
public class CommentPictureResource {

    @Autowired
    private CommentPictureService commentPictureService;


    @PostMapping("/queryByPage")
    public PagesInfo<CommentPicture> queryByPage(@RequestBody Map<String, Object> params){
        PageInfo<CommentPicture> pageInfo=commentPictureService.queryByPage(params,Integer.valueOf((String)params.get("page")),Integer.valueOf((String)params.get("limit")));
        return new PagesInfo<CommentPicture>(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(), pageInfo.getPages(),pageInfo.getList());
    }

    @PostMapping("/queryObject/{id}")
    public CommentPicture queryObject(@PathVariable("id") Integer id){
        return commentPictureService.getById(id);
    }

    @PostMapping("/save")
    public void save(@RequestBody CommentPicture commentPicture){
        commentPictureService.save(commentPicture);
    }

    @PostMapping("/update")
    public void update(@RequestBody CommentPicture commentPicture){
        commentPictureService.updateById(commentPicture);
    }

    @PostMapping("/deleteBatch")
    public void deleteBatch(@RequestBody Integer[] ids){
        commentPictureService.deleteBatch(ids);
    }

    @PostMapping("/queryList")
    public List<CommentPicture> queryList(@RequestBody Map<String, Object> params){
        return commentPictureService.queryList(params);
    }
}
