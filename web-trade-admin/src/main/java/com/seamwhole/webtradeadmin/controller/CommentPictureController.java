package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.CommentPicture;
import com.seamwhole.webtradeadmin.service.CommentPictureService;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/commentpicture")
public class CommentPictureController {
    @Autowired
    private CommentPictureService commentPictureService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("commentpicture:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PagesInfo<CommentPicture> page=commentPictureService.queryByPage(params);

        return ResponseObject.ok().put("page", page);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("commentpicture:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        CommentPicture commentPicture = commentPictureService.queryObject(id);

        return ResponseObject.ok().put("commentPicture", commentPicture);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("commentpicture:save")
    public ResponseObject save(@RequestBody CommentPicture commentPicture) {
        commentPictureService.save(commentPicture);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("commentpicture:update")
    public ResponseObject update(@RequestBody CommentPicture commentPicture) {
        commentPictureService.update(commentPicture);

        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("commentpicture:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        commentPictureService.deleteBatch(ids);

        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {

        List<CommentPicture> list = commentPictureService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }
}
