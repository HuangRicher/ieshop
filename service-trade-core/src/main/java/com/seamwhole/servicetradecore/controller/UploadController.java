package com.seamwhole.servicetradecore.controller;


import com.seamwhole.except.CheckException;
import com.seamwhole.servicetradecore.oss.OSSFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-09-08 13:20<br>
 * 描述: UploadController <br>
 */
@Api(tags = "上传")
@RestController
@RequestMapping("/api/upload")
public class UploadController extends BaseController {

    /**
     * 上传文件
     */
	@ApiOperation(value = "上传文件")
    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new CheckException("上传文件不能为空");
        }
        //上传文件
        String url = OSSFactory.build().upload(file);
        return toResponsSuccess(url);
    }
}