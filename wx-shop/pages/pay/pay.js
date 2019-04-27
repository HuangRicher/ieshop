var app = getApp();
var util = require('../../utils/util.js');
var api = require('../../config/api.js');

Page({
  data: {
    orderId: 0,
    actualPrice: 0.00,
    payType:2
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    this.setData({
      orderId: options.orderId,
      actualPrice: options.actualPrice,
      payType:2
    })
  },
  onReady: function () {

  },
  onShow: function () {
    // 页面显示

  },
  onHide: function () {
    // 页面隐藏

  },
  onUnload: function () {
    // 页面关闭

  },
  checkedItem: function (e) {
    let that = this;
    that.setData({
      payType: e.currentTarget.dataset.paytype
    });
  },
  //向服务请求支付参数
  requestPayParam() {
    let that = this;
    util.request(api.PayPrepayId, { orderId: that.data.orderId, payType: 1 }).then(function (res) {
      if (res.errno === 0) {
        let payParam = res.data;
        wx.requestPayment({
          'timeStamp': payParam.timeStamp,
          'nonceStr': payParam.timeStamp,
          'package': payParam.nonceStr,
          'signType': payParam.signType,
          'paySign': payParam.paySign,
          'success': function (res) {
            wx.redirectTo({
              url: '/pages/payResult/payResult?status=true',
            })
          },
          'fail': function (res) {
            wx.redirectTo({
              url: '/pages/payResult/payResult?status=false',
            })
          }
        })
      }
    });
  },
  startPay() {
    let that=this;
    if (this.data.payType === 1) {
      this.requestPayParam();
    };
    if (this.data.payType === 2) {
      util.request(api.OrderPayByWalletOpen, { orderId: that.data.orderId, payType: 2 }).then(res => {
        if (res.errno === 0) {
          wx.redirectTo({
            url: '/pages/payResult/payResult?status=1',
          });
        } else {
          util.showErrorToast('钱包支付失败');
          wx.redirectTo({
            url: '/pages/payResult/payResult?status=0'
          });
        }
      });
    };
  }
})