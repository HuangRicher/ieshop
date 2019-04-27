var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');
var user = require('../../../services/user.js');
var app = getApp();

Page({
    data: {
        userInfo: {},
        hasMobile: ''
    },
    onLoad: function (options) {
        // 页面初始化 options为页面跳转所带来的参数
    },
    onReady: function () {

    },
    onShow: function () {

        let userInfo = wx.getStorageSync('userInfo');
        let token = wx.getStorageSync('token');

        // 页面显示
        if (userInfo && token) {
            app.globalData.userInfo = userInfo;
            app.globalData.token = token;
        }

        this.setData({
            userInfo: app.globalData.userInfo,
        });

    },
    onHide: function () {
        // 页面隐藏

    },
    onUnload: function () {
        // 页面关闭
    },
    bindGetUserInfo(e){
      let that=this;
      let userInfo = wx.getStorageSync('userInfo');
      let token = wx.getStorageSync('token');
      if (userInfo && token) {
        return;
      }
      let code=null;
      wx.login({
        withCredentials:true,
        success(res) {
          if (res.code) {
            code=res.code;
            // 必须是在用户已经授权的情况下调用
            wx.getUserInfo({
              success(res) {
                //登录远程服务器
                let user={
                  code:code,
                  encryptedData: res.encryptedData,
                  errMsg:res.errMsg,
                  iv:res.iv,
                  rawData:res.rawData,
                  signature:res.signature,
                  userInfo:res.userInfo
                };
                util.request(api.AuthLoginByWeixin, user, 'POST', 'application/json').then(res => {
                  if (res.errno === 0) {
                    //存储用户信息
                    wx.setStorageSync('userInfo', res.data.userInfo);
                    wx.setStorageSync('token', res.data.token);
                    that.setData({
                      userInfo: res.data.userInfo
                    });
                  } else {
                    util.showErrorToast(res.errmsg)
                  }
                }).catch((err) => {
                  console.log(err);
                });
              }
            });
          } else {
            console.log('登录失败！' + res.errMsg)
          }
        }
      });
    },
    // bindGetUserInfo(e) {
    //   let userInfo = wx.getStorageSync('userInfo');
    //   let token = wx.getStorageSync('token');
    //   if (userInfo && token) {
    //     return;
    //   }
    //     if (e.detail.userInfo){
    //         //用户按了允许授权按钮
    //         user.loginByWeixin(e.detail).then(res => {
    //             this.setData({
    //                 userInfo: res.data.userInfo
    //             });
    //             app.globalData.userInfo = res.data.userInfo;
    //             app.globalData.token = res.data.token;
    //         }).catch((err) => {
    //             console.log(err)
    //         });
    //     } else {
    //         //用户按了拒绝按钮
    //         wx.showModal({
    //             title: '警告通知',
    //             content: '您点击了拒绝授权,将无法正常显示个人信息,点击确定重新获取授权。',
    //             success: function (res) {
    //                 if (res.confirm) {
    //                     wx.openSetting({
    //                         success: (res) => {
    //                             if (res.authSetting["scope.userInfo"]) {////如果用户重新同意了授权登录
    //                                 user.loginByWeixin(e.detail).then(res => {
    //                                     this.setData({
    //                                         userInfo: res.data.userInfo
    //                                     });
    //                                     app.globalData.userInfo = res.data.userInfo;
    //                                     app.globalData.token = res.data.token;
    //                                 }).catch((err) => {
    //                                     console.log(err)
    //                                 });
    //                             }
    //                         }
    //                     })
    //                 }
    //             }
    //         });
    //     }
    // },
    exitLogin: function () {
        wx.showModal({
            title: '',
            confirmColor: '#b4282d',
            content: '退出登录？',
            success: function (res) {
                if (res.confirm) {
                    wx.removeStorageSync('token');
                    wx.removeStorageSync('userInfo');
                    wx.switchTab({
                        url: '/pages/index/index'
                    });
                }
            }
        })

    }
})