package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("commentpicture")
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
        Query query = new Query(params);

        List<CommentPictureEntity> commentPictureList = commentPictureService.queryList(query);
        int total = commentPictureService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(commentPictureList, total, query.getLimit(), query.getPage());

        return ResponseObject.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("commentpicture:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        CommentPictureEntity commentPicture = commentPictureService.queryObject(id);

        return ResponseObject.ok().put("commentPicture", commentPicture);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("commentpicture:save")
    public ResponseObject save(@RequestBody CommentPictureEntity commentPicture) {
        commentPictureService.save(commentPicture);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("commentpicture:update")
    public ResponseObject update(@RequestBody CommentPictureEntity commentPicture) {
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

        List<CommentPictureEntity> list = commentPictureService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }
}
