package com.seamwhole.webtradeadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class ShopPathRedirectController {

    @RequestMapping("/userlevel")
    public String userlevel() {
        return "page/shop/userlevel";
    }

    @RequestMapping("/usercoupon")
    public String usercoupon() {
        return "page/shop/usercoupon";
    }

    @RequestMapping("/topiccategory")
    public String topiccategory() {
        return "page/shop/topiccategory";
    }

    @RequestMapping("/topic")
    public String topic() {
        return "page/shop/topic";
    }

    @RequestMapping("/specification")
    public String specification() {
        return "page/shop/specification";
    }

    @RequestMapping("/shopuser")
    public String shopuser() {
        return "page/shop/shopuser";
    }

    @RequestMapping("/searchhistory")
    public String searchhistory() {
        return "page/shop/searchhistory";
    }

    @RequestMapping("/product")
    public String product() {
        return "page/shop/product";
    }

    @RequestMapping("/order")
    public String order() {
        return "page/shop/order";
    }
    @RequestMapping("/keywords")
    public String keywords() {
        return "page/shop/keywords";
    }

    @RequestMapping("/goodsspecification")
    public String goodsspecification() {
        return "page/shop/goodsspecification";
    }

    @RequestMapping("/goodsissue")
    public String goodsissue() {
        return "page/shop/goodsissue";
    }

    @RequestMapping("/goodshistory")
    public String goodshistory() {
        return "page/shop/goodshistory";
    }

    @RequestMapping("/goods")
    public String goods() {
        return "page/shop/goods";
    }

    @RequestMapping("/footprint")
    public String footprint() {
        return "page/shop/footprint";
    }

    @RequestMapping("/feedback")
    public String feedback() {
        return "page/shop/feedback";
    }

    @RequestMapping("/coupon")
    public String coupon() {
        return "page/shop/coupon";
    }

    @RequestMapping("/comment")
    public String comment() {
        return "page/shop/comment";
    }

    @RequestMapping("/collect")
    public String collect() {
        return "page/shop/collect";
    }

    @RequestMapping("/channel")
    public String channel() {
        return "page/shop/channel";
    }

    @RequestMapping("/category")
    public String category() {
        return "page/shop/category";
    }


    @RequestMapping("/cart")
    public String cart() {
        return "page/shop/cart";
    }

    @RequestMapping("/brand")
    public String brand() {
        return "page/shop/brand";
    }

    @RequestMapping("/attributecategory")
    public String attributecategory() {
        return "page/shop/attributecategory";
    }

    @RequestMapping("/adposition")
    public String adposition() {
        return "page/shop/adposition";
    }

    @RequestMapping("/address")
    public String address() {
        return "page/shop/address";
    }

    @RequestMapping("/ad")
    public String ad() {
        return "page/shop/ad";
    }

}
