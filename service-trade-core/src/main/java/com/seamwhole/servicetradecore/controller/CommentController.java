package com.seamwhole.servicetradecore.controller;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.controller.model.CommentModel;
import com.seamwhole.servicetradecore.domain.ShopCommentInfo;
import com.seamwhole.servicetradecore.mapper.model.CouponDO;
import com.seamwhole.servicetradecore.model.CommentPicture;
import com.seamwhole.servicetradecore.model.ShopComment;
import com.seamwhole.servicetradecore.model.UserCoupon;
import com.seamwhole.servicetradecore.service.*;
import com.seamwhole.servicetradecore.util.CharUtil;
import com.seamwhole.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Api(tags = "评论")
@RestController
@RequestMapping("/api/comment")
public class CommentController extends BaseController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentPictureService commentPictureService;
    @Autowired
    private CouponService apiCouponService;
    @Autowired
    private UserCouponService apiUserCouponService;

    /**
     * 发表评论
     */
    @ApiOperation(value = "发表评论")
    @PostMapping("post")
    public Object post(@RequestBody CommentModel commentModel) {
        Map resultObj = new HashMap();
        //
        Integer typeId = commentModel.getTypeId();
        Integer valueId = commentModel.getValueId();
        String content = commentModel.getContent();
        List<String> imagesList = commentModel.getImagesList();
        ShopComment commentEntity = new ShopComment();
        commentEntity.setTypeId(typeId);
        commentEntity.setValueId(valueId);
        commentEntity.setContent(content);
        commentEntity.setStatus(0);
        //
        commentEntity.setAddTime(System.currentTimeMillis() / 1000);
        commentEntity.setUserId(commentModel.getUserId());
        commentEntity.setContent(Base64.getEncoder().encodeToString(commentEntity.getContent().getBytes()));
        Integer insertId = commentService.save(commentEntity);
        //
        if (insertId > 0 && null != imagesList && imagesList.size() > 0) {
            int i = 0;
            for (Object imgLink : imagesList) {
                i++;
                CommentPicture pictureVo = new CommentPicture();
                pictureVo.setCommentId(insertId);
                pictureVo.setPicUrl(imgLink.toString());
                pictureVo.setSortOrder(i);
                commentPictureService.save(pictureVo);
            }
        }
        // 是否领取优惠券
        if (insertId > 0 && typeId == 0) {
            // 当前评价的次数
            Map param = new HashMap();
            param.put("value_id", valueId);
            List<ShopComment> commentVos = commentService.queryList(param);
            boolean hasComment = false;
            for (ShopComment commentVo : commentVos) {
                if (commentVo.getUserId().equals(commentModel.getUserId())
                        && !commentVo.getId().equals(insertId)) {
                    hasComment = true;
                }
            }
            if (!hasComment) {
                Map couponParam = new HashMap();
                couponParam.put("send_type", 6);
                CouponDO newCouponConfig = apiCouponService.queryMaxUserEnableCoupon(couponParam);
                if (null != newCouponConfig
                        && newCouponConfig.getMin_transmit_num() >= commentVos.size()) {
                    UserCoupon userCouponVo = new UserCoupon();
                    userCouponVo.setAddTime(new Date());
                    userCouponVo.setCouponId(newCouponConfig.getId());
                    userCouponVo.setCouponNumber(CharUtil.getRandomString(12));
                    userCouponVo.setUserId(commentModel.getUserId());
                    apiUserCouponService.save(userCouponVo);
                    resultObj.put("coupon", newCouponConfig);
                }
            }
        }
        if (insertId > 0) {
            return toResponsObject(0, "评论添加成功", resultObj);
        } else {
            return toResponsFail("评论保存失败");
        }
    }

    /**
     */
    @ApiOperation(value = "评论数量")
    @PostMapping("count")
    public Object count(@RequestBody CommentModel commentModel) {
        Map<String, Object> resultObj = new HashMap();
        //
        Map param = new HashMap();
        param.put("type_id", commentModel.getTypeId());
        param.put("value_id", commentModel.getValueId());
        Integer allCount = commentService.queryTotal(param);
        Integer hasPicCount = commentService.queryHasPicTotal(param);
        //
        resultObj.put("allCount", allCount);
        resultObj.put("hasPicCount", hasPicCount);
        return toResponsSuccess(resultObj);
    }

    /**
     * 选择评论类型
     * @return
     */
    @ApiOperation(value = "选择评论类型")
    @PostMapping("list")
    public Object list(@RequestBody CommentModel commentModel) {
        List<ShopCommentInfo> commentList = new ArrayList();
        Map param = new HashMap();
        param.put("typeId", commentModel.getTypeId());
        param.put("valueId", commentModel.getValueId());
        String order="";
        if (StringUtils.isNullOrEmpty(commentModel.getOrder())) {
            order=" id";
        } else {
            order=" "+commentModel.getOrder();
        }
        if (StringUtils.isNullOrEmpty(commentModel.getSort())) {
            order+="  desc ";
        } else {
            order+=" "+commentModel.getSort();
        }
        if (null != commentModel.getShowType() && commentModel.getShowType() == 1) {
            param.put("hasPic", 1);
        }
        //查询列表数据

        PageInfo<ShopComment> pageInfo = commentService.queryByPage(param,commentModel.getPageNum(),commentModel.getPageSize(),order);

        for (ShopComment commentItem : pageInfo.getList()) {
            ShopCommentInfo commentInfo=new ShopCommentInfo();
            BeanUtils.copyProperties(commentItem,commentInfo);
            commentInfo.setContent(new String(Base64.getDecoder().decode(commentItem.getContent().getBytes())));
            commentInfo.setUserInfo(userService.queryObject(commentItem.getUserId()));

            Map paramPicture = new HashMap();
            paramPicture.put("commentId", commentItem.getId());
            List<CommentPicture> commentPictureEntities = commentPictureService.queryList(paramPicture);
            commentInfo.setPicList(commentPictureEntities);
        }
        PageInfo<ShopCommentInfo> page=new PageInfo<>(commentList);
        return toResponsSuccess(page);
    }
}