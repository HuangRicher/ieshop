package com.seamwhole.webtradeadmin.controller;

import com.seamwhole.util.PagesInfo;
import com.seamwhole.webtradeadmin.info.ShopComment;
import com.seamwhole.webtradeadmin.info.ShopCommentDO;
import com.seamwhole.webtradeadmin.service.CommentService;
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
        PagesInfo<ShopCommentDO> page=commentService.queryByPage(params);

        return ResponseObject.ok().put("page", page);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("comment:info")
    public ResponseObject info(@PathVariable("id") Integer id) {
        ShopComment comment = commentService.queryObject(id);

        return ResponseObject.ok().put("comment", comment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("comment:save")
    public ResponseObject save(@RequestBody ShopComment comment) {
        commentService.save(comment);

        return ResponseObject.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("comment:update")
    public ResponseObject update(@RequestBody ShopComment comment) {
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

        List<ShopCommentDO> list = commentService.queryList(params);

        return ResponseObject.ok().put("list", list);
    }

    /**
     * 修改状态
     */
    @RequestMapping("/toggleStatus")
    @RequiresPermissions("comment:toggleStatus")
    public ResponseObject toggleStatus(@RequestBody ShopComment comment) {
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
