package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.webtradeadmin.util.ResponseObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("comment:list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<CommentEntity> commentList = commentService.queryList(query);
        int total = commentService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(commentList, total, query.getLimit(), query.getPage());

        return ResponseObject.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("comment:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        CommentEntity comment = commentService.queryObject(id);

        return ResponseObject.ok().put("comment", comment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("comment:save")
    public ResponseObject save(@RequestBody CommentEntity comment) {
        commentService.save(comment);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("comment:update")
    public ResponseObject update(@RequestBody CommentEntity comment) {
        commentService.update(comment);

        return ResponseObject.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("comment:delete")
    public ResponseObject delete(@RequestBody Integer[] ids) {
        commentService.deleteBatch(ids);

        return ResponseObject.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public ResponseObject queryAll(@RequestParam Map<String, Object> params) {

        List<CommentEntity> list = commentService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }

    /**
     * 修改状态
     */
    @RequestMapping("/toggleStatus")
    @RequiresPermissions("comment:toggleStatus")
    public ResponseObject toggleStatus(@RequestBody CommentEntity comment) {
        commentService.toggleStatus(comment);

        return ResponseObject.ok();
    }

    /**
     * 总计
     */
    @RequestMapping("/queryTotal")
    public ResponseObject queryTotal(@RequestParam Map<String, Object> params) {
        int sum = commentService.queryTotal(params);

        return ResponseObject.ok().put("sum", sum);
    }
}
