package com.seamwhole.weberpadmin.controller;

import com.sun.tools.corba.se.idl.StringGen;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathController {

    @RequestMapping("/login")
    public String toLogin(){
        return "/login";
    }

    @RequestMapping("/register")
    public String toRegister(){
        return "/register";
    }

    @RequestMapping("/index")
    public String toIndex(){
        return "/index";
    }

    @RequestMapping("/common/main")
    public String toMain(){
        return "/common/main";
    }

    @RequestMapping("/common/menu")
    public String toMenu(String appID,Integer id,Model model){
        model.addAttribute("appId",appID);
        model.addAttribute("id",id);
        return "/common/menu";
    }

    @RequestMapping("/user/password")
    public String toPassword(){
        return "/user/password";
    }

    @RequestMapping("/user/userCustomer")
    public String toUserCustomer(Integer id,Model model){
        model.addAttribute("id",id);
        return "/user/userCustomer";
    }

    @RequestMapping("/user/userDepot")
    public String toUserDepot(Integer id,Model model){
        model.addAttribute("id",id);
        return "/user/userDepot";
    }

    @RequestMapping("/user/userRole")
    public String toUserRole(Integer id,Model model){
        model.addAttribute("id",id);
        return "/user/userRole";
    }

    @RequestMapping("/manage/app")
    public String toApp(){
        return "/manage/app";
    }

    @RequestMapping("/manage/role")
    public String toRole(){
        return "/manage/role";
    }

    @RequestMapping("/manage/user")
    public String toUser(){
        return "/manage/user";
    }

    @RequestMapping("/manage/log")
    public String toLog(){
        return "/manage/log";
    }

    @RequestMapping("/manage/functions")
    public String toFunctions(){
        return "/manage/functions";
    }

    @RequestMapping("/manage/user_forselect")
    public String toUserForSelect(){
        return "/manage/user_forselect";
    }

    @RequestMapping("/asset/asset")
    public String toAsset(){
        return "/asset/asset";
    }

    @RequestMapping("/manage/systemConfig")
    public String toSystemConfig(){
        return "/manage/systemConfig";
    }

    @RequestMapping("/materials/materialProperty")
    public String toMaterialProperty(){
        return "/materials/materialProperty";
    }

    @RequestMapping("/manage/organization")
    public String toOrganization(){
        return "/manage/organization";
    }

    @RequestMapping("/materials/materialcategory")
    public String toMaterialcategory(){
        return "/materials/materialcategory";
    }

    @RequestMapping("/materials/materialcategory_forselect")
    public String toMaterialCategoryForSelect(){
        return "/materials/materialcategory_forselect";
    }

    @RequestMapping("/materials/material_forselect")
    public String toMaterialForSelect(){
        return "/materials/material_forselect";
    }

    @RequestMapping("/materials/material")
    public String toMaterial(){
        return "/materials/material";
    }

    @RequestMapping("/manage/unit")
    public String toUnit(){
        return "/manage/unit";
    }

    @RequestMapping("/manage/serialNumber")
    public String toSerialNumber(){
        return "/manage/serialNumber";
    }

    @RequestMapping("/manage/vendor")
    public String toVendor(){
        return "/manage/vendor";
    }

    @RequestMapping("/manage/depot")
    public String toDepot(){
        return "/manage/depot";
    }

    @RequestMapping("/materials/person")
    public String toPerson(){
        return "/materials/person";
    }

    @RequestMapping("/manage/inOutItem")
    public String toInOutItem(){
        return "/manage/inOutItem";
    }

    @RequestMapping("/manage/roleFunctions")
    public String toRoleFunctions(Integer id,Model model){
        model.addAttribute("id",id);
        return "/manage/roleFunctions";
    }

    @RequestMapping("/manage/rolePushBtn")
    public String toRolePushBtn(Integer id,Model model){
        model.addAttribute("id",id);
        return "/manage/rolePushBtn";
    }

    @RequestMapping("/manage/account")
    public String toAccount(){
        return "/manage/account";
    }

    @RequestMapping("/manage/organization_forselect")
    public String toOrganizationForSelect(){
        return "/manage/organization_forselect";
    }

    @RequestMapping("/manage/customer")
    public String toCustomer(){
        return "/manage/customer";
    }

    @RequestMapping("/manage/member")
    public String toMember(){
        return "/manage/member";
    }

    @RequestMapping("/reports/in_out_stock_report")
    public String toInOutStockReport(){
        return "reports/in_out_stock_report";
    }

    @RequestMapping("/materials/bill_detail")
    public String toBillDetail(String n, String type,Model model){
        model.addAttribute("n",n);
        model.addAttribute("type",type);
        return "/materials/bill_detail";
    }

    @RequestMapping("/reports/account_report")
    public String toAccountReport(){
        return "/reports/account_report";
    }

    @RequestMapping("/reports/buy_in_report")
    public String toBuyInReport(){
        return "/reports/buy_in_report";
    }

    @RequestMapping("/reports/sale_out_report")
    public String toSaleOutReport(){
        return "/reports/sale_out_report";
    }

    @RequestMapping("/reports/in_detail")
    public String toInDetail(){
        return "/reports/in_detail";
    }

    @RequestMapping("/reports/out_detail")
    public String toOutDetail(){
        return "/reports/out_detail";
    }

    @RequestMapping("/reports/in_material_count")
    public String toInMaterialCount(){
        return "/reports/in_material_count";
    }

    @RequestMapping("/reports/out_material_count")
    public String toOutMaterialCount(){
        return "/reports/out_material_count";
    }

    @RequestMapping("/reports/customer_account")
    public String toCustomerAccount(){
        return "/reports/customer_account";
    }

    @RequestMapping("/reports/vendor_account")
    public String toVendorAccount(){
        return "/reports/vendor_account";
    }

    @RequestMapping("/reports/stock_warning_report")
    public String toStockWarningReport(){
        return "/reports/stock_warning_report";
    }

    @RequestMapping("/materials/retail_out_list")
    public String toRetailOutList(){
        return "/materials/retail_out_list";
    }

    @RequestMapping("/materials/retail_back_list")
    public String toRetailBackList(){
        return "/materials/retail_back_list";
    }

    @RequestMapping("/materials/purchase_in_list")
    public String toPurchaseInList(){
        return "/materials/purchase_in_list";
    }

    @RequestMapping("/materials/purchase_back_list")
    public String toPurchaseBackList(){
        return "/materials/purchase_back_list";
    }

    @RequestMapping("/materials/purchase_orders_list")
    public String toPurchaseOrdersList(){
        return "/materials/purchase_orders_list";
    }

    @RequestMapping("/materials/sale_out_list")
    public String toSaleOutList(){
        return "/materials/sale_out_list";
    }

    @RequestMapping("/materials/sale_back_list")
    public String toSaleBackList(){
        return "/materials/sale_back_list";
    }

    @RequestMapping("/materials/sale_orders_list")
    public String toSaleOrdersList(){
        return "/materials/sale_orders_list";
    }

    @RequestMapping("/financial/item_in")
    public String toItemIn(){
        return "/financial/item_in";
    }

    @RequestMapping("/financial/item_out")
    public String toItemOut(){
        return "/financial/item_out";
    }

    @RequestMapping("/financial/money_in")
    public String toMoneyIn(){
        return "/financial/money_in";
    }

    @RequestMapping("/financial/money_out")
    public String toMoneyOut(){
        return "/financial/money_out";
    }

    @RequestMapping("/financial/giro")
    public String toGiro(){
        return "/financial/giro";
    }

    @RequestMapping("/financial/advance_in")
    public String toAdvanceIn(){
        return "/financial/advance_in";
    }

    @RequestMapping("/materials/allocation_out_list")
    public String toAllocationOutList(){
        return "/materials/allocation_out_list";
    }

    @RequestMapping("/materials/other_in_list")
    public String toOtherInList(){
        return "/materials/other_in_list";
    }

    @RequestMapping("/materials/other_out_list")
    public String toOtherOutList(){
        return "/materials/other_out_list";
    }

    @RequestMapping("/materials/assemble_list")
    public String toAssembleList(){
        return "/materials/assemble_list";
    }

    @RequestMapping("/materials/disassemble_list")
    public String toDisassembleList(){
        return "/materials/disassemble_list";
    }
}
