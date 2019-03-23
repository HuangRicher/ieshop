package com.seamwhole.servicetradecore.domain;

import com.seamwhole.servicetradecore.model.CommentPicture;
import com.seamwhole.servicetradecore.model.ShopComment;
import com.seamwhole.servicetradecore.model.ShopUser;

import java.io.Serializable;
import java.util.List;

public class ShopCommentInfo extends ShopComment implements Serializable {

    //会员Id
    private ShopUser userInfo;
    private List<CommentPicture> picList;

    public ShopUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(ShopUser userInfo) {
        this.userInfo = userInfo;
    }

    public List<CommentPicture> getPicList() {
        return picList;
    }

    public void setPicList(List<CommentPicture> picList) {
        this.picList = picList;
    }
}
