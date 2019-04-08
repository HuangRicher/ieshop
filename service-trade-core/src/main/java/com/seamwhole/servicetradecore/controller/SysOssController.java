package com.seamwhole.servicetradecore.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.seamwhole.except.CheckException;
import com.seamwhole.servicetradecore.constant.Constant;
import com.seamwhole.servicetradecore.model.SysOss;
import com.seamwhole.servicetradecore.oss.CloudStorageConfig;
import com.seamwhole.servicetradecore.oss.OSSFactory;
import com.seamwhole.servicetradecore.service.SysConfigService;
import com.seamwhole.servicetradecore.service.SysOssService;
import com.seamwhole.servicetradecore.util.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 文件上传Controller
 */
@RestController
@RequestMapping("/old/sys/oss")
public class SysOssController {
    @Autowired
    private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    private final static String KEY = Constant.CLOUD_STORAGE_CONFIG_KEY;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseObject list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        PageInfo<SysOss> pageInfo=sysOssService.queryByPage(params,1,10);
        return ResponseObject.ok().put("page", pageInfo);
    }


    /**
     * 获取云存储配置信息
     */
    @RequestMapping("/config")
    public ResponseObject config() {
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);
        return ResponseObject.ok().put("config", config);
    }


    /**
     * 保存云存储配置信息
     */
    @RequestMapping("/saveConfig")
    public ResponseObject saveConfig(@RequestBody CloudStorageConfig config) {
        //校验类型
        //ValidatorUtils.validateEntity(config);

        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            //校验七牛数据
            //ValidatorUtils.validateEntity(config, QiniuGroup.class);
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            //校验阿里云数据
            //ValidatorUtils.validateEntity(config, AliyunGroup.class);
        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            //校验腾讯云数据
            //ValidatorUtils.validateEntity(config, QcloudGroup.class);
        } else if (config.getType() == Constant.CloudService.DISCK.getValue()) {
            //校验腾讯云数据
            //ValidatorUtils.validateEntity(config, DiskGroup.class);
        }

        sysConfigService.updateValueByKey(KEY, JSON.toJSONString(config));

        return ResponseObject.ok();
    }

    /**
     * 上传文件
     */
    @RequestMapping("/upload")
    public ResponseObject upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new CheckException("上传文件不能为空");
        }
        //上传文件
        String url = OSSFactory.build().upload(file);

        //保存文件信息
        SysOss ossEntity = new SysOss();
        ossEntity.setUrl(url);
        ossEntity.setCreateDate(new Date());
        sysOssService.save(ossEntity);

        ResponseObject r = new ResponseObject();
        r.put("url", url);
        r.put("link", url);
        return r;
    }


    /**
     * 删除图片
     */
    @RequestMapping("/delete")
    public ResponseObject delete(@RequestBody Long[] ids) {
        sysOssService.removeByIds(Arrays.asList(ids));

        return ResponseObject.ok();
    }
}
