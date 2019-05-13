package com.seamwhole.weberpadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathController {

    @RequestMapping("/login")
    public String toLogin(){
        return "/login.html";
    }

    @RequestMapping("/register")
    public String toRegister(){
        return "/register.html";
    }

    @RequestMapping("/index")
    public String toIndex(){
        return "/index.html";
    }

    @RequestMapping("/common/main")
    public String toMain(){
        return "/common/main.html";
    }

    @RequestMapping("/common/menu")
    public String toMenu(String appID,Integer id,Model model){
        model.addAttribute("appId",appID);
        model.addAttribute("id",id);
        return "/common/menu";
    }

    @RequestMapping("/user/password")
    public String toPassword(){
        return "/user/password.html";
    }

    @RequestMapping("/user/userCustomer")
    public String toUserCustomer(){
        return "/user/userCustomer.html";
    }

    @RequestMapping("/user/userDepot")
    public String toUserDepot(){
        return "/user/userDepot.html";
    }

    @RequestMapping("/user/userRole")
    public String toUserRole(){
        return "/user/userRole.html";
    }

    @RequestMapping("/manage/app")
    public String toApp(){
        return "/manage/app.html";
    }

    @RequestMapping("/manage/role")
    public String toRole(){
        return "/manage/role.html";
    }

    @RequestMapping("/manage/user")
    public String toUser(){
        return "/manage/user.html";
    }

    @RequestMapping("/manage/log")
    public String toLog(){
        return "/manage/log.html";
    }

    @RequestMapping("/manage/functions")
    public String toFunctions(){
        return "/manage/functions.html";
    }

    @RequestMapping("/asset/asset")
    public String toAsset(){
        return "/asset/asset.html";
    }

    @RequestMapping("/manage/systemConfig")
    public String toSystemConfig(){
        return "/manage/systemConfig.html";
    }

    @RequestMapping("/materials/materialProperty")
    public String toMaterialProperty(){
        return "/materials/materialProperty.html";
    }

    @RequestMapping("/manage/organization")
    public String toOrganization(){
        return "/manage/organization.html";
    }

    @RequestMapping("/materials/materialcategory")
    public String toMaterialcategory(){
        return "/materials/materialcategory.html";
    }

    @RequestMapping("/materials/material")
    public String toMaterial(){
        return "/materials/material.html";
    }

    @RequestMapping("/manage/unit")
    public String toUnit(){
        return "/manage/unit.html";
    }

    @RequestMapping("/manage/serialNumber")
    public String toSerialNumber(){
        return "/manage/serialNumber.html";
    }

    @RequestMapping("/manage/vendor")
    public String toVendor(){
        return "/manage/vendor.html";
    }

    @RequestMapping("/manage/depot")
    public String toDepot(){
        return "/manage/depot.html";
    }

    @RequestMapping("/materials/person")
    public String toPerson(){
        return "/materials/person.html";
    }

    @RequestMapping("/manage/inOutItem")
    public String toInOutItem(){
        return "/manage/inOutItem.html";
    }

    @RequestMapping("/manage/account")
    public String toAccount(){
        return "/manage/account.html";
    }

    @RequestMapping("/manage/customer")
    public String toCustomer(){
        return "/manage/customer.html";
    }

    @RequestMapping("/manage/member")
    public String toMember(){
        return "/manage/member.html";
    }

    @RequestMapping("/reports/in_out_stock_report")
    public String toInOutStockReport(){
        return "reports/in_out_stock_report";
    }

    @RequestMapping("/reports/account_report")
    public String toAccountReport(){
        return "/reports/account_report.html";
    }

    @RequestMapping("/reports/buy_in_report")
    public String toBuyInReport(){
        return "/reports/buy_in_report.html";
    }

    @RequestMapping("/reports/sale_out_report")
    public String toSaleOutReport(){
        return "/reports/sale_out_report.html";
    }

    @RequestMapping("/reports/in_detail")
    public String toInDetail(){
        return "/reports/in_detail.html";
    }

    @RequestMapping("/reports/out_detail")
    public String toOutDetail(){
        return "/reports/out_detail.html";
    }

    @RequestMapping("/reports/in_material_count")
    public String toInMaterialCount(){
        return "/reports/in_material_count.html";
    }

    @RequestMapping("/reports/out_material_count")
    public String toOutMaterialCount(){
        return "/reports/out_material_count.html";
    }

    @RequestMapping("/reports/customer_account")
    public String toCustomerAccount(){
        return "/reports/customer_account.html";
    }

    @RequestMapping("/reports/vendor_account")
    public String toVendorAccount(){
        return "/reports/vendor_account.html";
    }

    @RequestMapping("/reports/stock_warning_report")
    public String toStockWarningReport(){
        return "/reports/stock_warning_report.html";
    }

    @RequestMapping("/materials/retail_out_list")
    public String toRetailOutList(){
        return "/materials/retail_out_list.html";
    }

    @RequestMapping("/materials/retail_back_list")
    public String toRetailBackList(){
        return "/materials/retail_back_list.html";
    }

    @RequestMapping("/materials/purchase_in_list")
    public String toPurchaseInList(){
        return "/materials/purchase_in_list.html";
    }

    @RequestMapping("/materials/purchase_back_list")
    public String toPurchaseBackList(){
        return "/materials/purchase_back_list.html";
    }

    @RequestMapping("/materials/purchase_orders_list")
    public String toPurchaseOrdersList(){
        return "/materials/purchase_orders_list.html";
    }

    @RequestMapping("/materials/sale_out_list")
    public String toSaleOutList(){
        return "/materials/sale_out_list.html";
    }

    @RequestMapping("/materials/sale_back_list")
    public String toSaleBackList(){
        return "/materials/sale_back_list.html";
    }

    @RequestMapping("/materials/sale_orders_list")
    public String toSaleOrdersList(){
        return "/materials/sale_orders_list.html";
    }

    @RequestMapping("/financial/item_in")
    public String toItemIn(){
        return "/financial/item_in.html";
    }

    @RequestMapping("/financial/item_out")
    public String toItemOut(){
        return "/financial/item_out.html";
    }

    @RequestMapping("/financial/money_in")
    public String toMoneyIn(){
        return "/financial/money_in.html";
    }

    @RequestMapping("/financial/money_out")
    public String toMoneyOut(){
        return "/financial/money_out.html";
    }

    @RequestMapping("/financial/giro")
    public String toGiro(){
        return "/financial/giro.html";
    }

    @RequestMapping("/financial/advance_in")
    public String toAdvanceIn(){
        return "/financial/advance_in.html";
    }

    @RequestMapping("/materials/allocation_out_list")
    public String toAllocationOutList(){
        return "/materials/allocation_out_list.html";
    }

    @RequestMapping("/materials/other_in_list")
    public String toOtherInList(){
        return "/materials/other_in_list.html";
    }

    @RequestMapping("/materials/other_out_list")
    public String toOtherOutList(){
        return "/materials/other_out_list.html";
    }

    @RequestMapping("/materials/assemble_list")
    public String toAssembleList(){
        return "/materials/assemble_list.html";
    }

    @RequestMapping("/materials/disassemble_list")
    public String toDisassembleList(){
        return "/materials/disassemble_list.html";
    }
}
