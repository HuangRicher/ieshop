package com.seamwhole.servicetradecore.controller;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.annotation.LoginUser;
import com.seamwhole.servicetradecore.controller.model.FootPrintModel;
import com.seamwhole.servicetradecore.mapper.model.FootPrintDO;
import com.seamwhole.servicetradecore.model.FootPrint;
import com.seamwhole.servicetradecore.model.ShopUser;
import com.seamwhole.servicetradecore.service.FootPrintService;
import com.seamwhole.util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Api(tags = "足迹")
@RestController
@RequestMapping("/api/footprint")
public class FootprintController extends BaseController {
    @Autowired
    private FootPrintService footPrintService;

    /**
     */
    @ApiOperation(value = "删除足迹")
    @ApiImplicitParams({@ApiImplicitParam(name = "footprintId", value = "足迹id", paramType = "path", required = true)})
    @PostMapping("delete")
    public Object delete(@RequestBody FootPrintModel footPrintModel) {
        if (footPrintModel.getFootprintId() == null) {
            return toResponsFail("删除出错");
        }
        //删除当天的同一个商品的足迹
        FootPrint footprintEntity = footPrintService.queryObject(footPrintModel.getFootprintId());
        //
        if (footPrintModel.getUserId() == null || footprintEntity == null || footprintEntity.getGoodsId() == null) {
            return toResponsFail("删除出错");
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", footPrintModel.getUserId());
        param.put("goodsId", footprintEntity.getGoodsId());
        footPrintService.deleteByParam(param);

        return toResponsMsgSuccess("删除成功");
    }

    /**
     */
    @ApiOperation(value = "获取足迹列表")
    @PostMapping("list")
    public Object list(@LoginUser ShopUser user,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Map<String, Object> resultObj = new HashMap<String, Object>();

        //查询列表数据
        PageInfo<FootPrintDO> pageInfo=footPrintService.queryByPage(user.getId(),page,size);
        List<FootPrintDO> footprintVos = pageInfo.getList();
        //
        Map<String, List<FootPrintDO>> footPrintMap = new TreeMap<String, List<FootPrintDO>>(new Comparator<String>() {
            /*
             * int compare(Object o1, Object o2) 返回一个基本类型的整型，
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             */
            @Override
            public int compare(String o1, String o2) {
                //指定排序器按照降序排列
                return o2.compareTo(o1);
            }
        });

        if (null != footprintVos && footprintVos.size() > 0) {
            for (FootPrintDO footprintVo : footprintVos) {
                String addTime = DateUtils.timeToStr(footprintVo.getAdd_time(), DateUtils.DATE_PATTERN);
                List<FootPrintDO> tmpList = footPrintMap.get(addTime);
                if (null == footPrintMap.get(addTime)) {
                    tmpList = new ArrayList<>();
                }
                tmpList.add(footprintVo);
                footPrintMap.put(addTime, tmpList);
            }
            List<List<FootPrintDO>> footprintVoList = new ArrayList<>();
            for (Map.Entry<String, List<FootPrintDO>> entry : footPrintMap.entrySet()) {
                footprintVoList.add(entry.getValue());
            }
        }

        return this.toResponsSuccess(pageInfo);
    }


    /**
     */
    @ApiOperation(value = "分享足迹")
    @PostMapping("sharelist")
    public Object sharelist(@RequestBody FootPrintModel footPrintModel) {
        Map<String, List<FootPrintDO>> resultObj = new HashMap<>();

        //查询列表数据
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sidx", "f.id");
        params.put("order", "desc");
        params.put("referrer", footPrintModel.getUserId());
        List<FootPrintDO> footprintVos = footPrintService.shareList(params);
        //
        resultObj.put("data", footprintVos);
        return this.toResponsSuccess(resultObj);
    }
}