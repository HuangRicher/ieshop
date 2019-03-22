package com.seamwhole.servicetradecore.controller;

import com.seamwhole.servicetradecore.controller.model.CollectModel;
import com.seamwhole.servicetradecore.mapper.model.CollectDO;
import com.seamwhole.servicetradecore.model.ShopCollect;
import com.seamwhole.servicetradecore.service.CollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author Harmon <br>
 * 时间: 2017-08-11 08:32<br>
 * 描述: IndexController <br>
 */
@Api(tags = "用户收藏")
@RestController
@RequestMapping("/api/collect")
public class CollectController extends BaseController {

    @Autowired
    private CollectService collectService;

    /**
     * 获取用户收藏
     */
    @ApiOperation(value = "获取用户收藏")
    @PostMapping("list")
    public Object list(@RequestBody CollectModel collectModel) {

        Map param = new HashMap();
        param.put("user_id", collectModel.getUserId());
        param.put("type_id", collectModel.getValueId());
        List<CollectDO> collectEntities = collectService.queryList(param);

//        Query query = new Query(param);
//        int total = collectService.queryTotal(query);
//        ApiPageUtils pageUtil = new ApiPageUtils(collectEntities, total, query.getLimit(), query.getPage());
        return toResponsSuccess(collectEntities);
    }

    /**
     * 获取用户收藏
     */
    @ApiOperation(value = "添加取消收藏")
    @PostMapping("addordelete")
    public Object addordelete(@RequestBody CollectModel collectModel) {
        Integer typeId = collectModel.getTypeId();
        Integer valueId = collectModel.getValueId();

        Map param = new HashMap();
        param.put("user_id", collectModel.getUserId());
        param.put("type_id", typeId);
        param.put("value_id", valueId);
        List<CollectDO> collectEntities = collectService.queryList(param);
        //
        Integer collectRes = null;
        String handleType = "add";
        if (null == collectEntities || collectEntities.size() < 1) {
            ShopCollect collectEntity = new ShopCollect();
            collectEntity.setAddTime((int)(System.currentTimeMillis() / 1000));
            collectEntity.setTypeId(typeId);
            collectEntity.setValueId(valueId);
            collectEntity.setIsAttention(false);
            collectEntity.setUserId(collectModel.getUserId());
            //添加收藏
            collectRes = collectService.save(collectEntity);
        } else {
            //取消收藏
            collectRes = collectService.delete(collectEntities.get(0).getId());
            handleType = "delete";
        }

        if (collectRes > 0) {
            Map data = new HashMap();
            data.put("type", handleType);
            return toResponsSuccess(data);
        }
        return toResponsFail("操作失败");
    }
}