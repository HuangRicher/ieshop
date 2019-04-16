package com.seamwhole.servicetradecore.controller;

import com.github.pagehelper.PageInfo;
import com.seamwhole.servicetradecore.controller.model.GoodsModel;
import com.seamwhole.servicetradecore.domain.CategoryInfo;
import com.seamwhole.servicetradecore.mapper.model.CouponDO;
import com.seamwhole.servicetradecore.mapper.model.GoodsDO;
import com.seamwhole.servicetradecore.mapper.model.GoodsSpecificationDO;
import com.seamwhole.servicetradecore.mapper.model.ShopCartDO;
import com.seamwhole.servicetradecore.model.*;
import com.seamwhole.servicetradecore.service.*;
import com.seamwhole.util.CharUtil;
import com.seamwhole.util.DateUtils;
import com.seamwhole.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Api(tags = "商品管理")
@RestController
@RequestMapping("/api/goods")
public class GoodsController extends BaseController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsSpecificationService goodsSpecificationService;
    @Autowired
    private ProductService productService;
    @Autowired
    private GoodsGalleryService goodsGalleryService;
    @Autowired
    private GoodsIssueService goodsIssueService;
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ShopUserService userService;
    @Autowired
    private CommentPictureService commentPictureService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private FootPrintService footPrintService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SearchHistoryService searchHistoryService;
    @Autowired
    private RelatedGoodsService relatedGoodsService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private UserCouponService userCouponService;
    @Autowired
    private CartService cartService;

    /**
     */
    @ApiOperation(value = "商品首页")
    @PostMapping(value = "index")
    public Object index() {
        //
        Map param = new HashMap();
        param.put("isDelete", 0);
        param.put("isOnSale", 1);
        List<GoodsDO> goodsList = goodsService.queryList(param);
        //
        return toResponsSuccess(goodsList);
    }

    /**
     * 获取商品规格信息，用于购物车编辑时选择规格
     */
    @ApiOperation(value = " 获取商品规格信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "商品id", paramType = "path", required = true)})
    @PostMapping(value = "sku")
    public Object sku(@RequestBody GoodsModel goodsModel) {
        Map<String, Object> resultObj = new HashMap();
        //
        Map param = new HashMap();
        param.put("goodsId", goodsModel.getGoodsId());
        List<GoodsSpecificationDO> goodsSpecificationEntityList = goodsSpecificationService.queryList(param);
        //
        List<Product> productEntityList = productService.queryList(param);
        //
        resultObj.put("specificationList", goodsSpecificationEntityList);
        resultObj.put("productList", productEntityList);
        return toResponsSuccess(resultObj);
    }

    /**
     * 商品详情页数据
     */
    @ApiOperation(value = " 商品详情页数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "商品id", paramType = "path", required = true),
            @ApiImplicitParam(name = "referrer", value = "商品referrer", paramType = "path", required = false)})
    @PostMapping(value = "detail")
    public Object detail(Integer id, Integer referrer) {
        Map<String, Object> resultObj = new HashMap();
        //
        Integer userId = null;
        Goods info = goodsService.queryObject(id);
        Map param = new HashMap();
        param.put("goodsId", id);
        //
        Map specificationParam = new HashMap();
        specificationParam.put("fields", "gs.*, s.name");
        specificationParam.put("goodsId", id);
        specificationParam.put("specification", true);
        specificationParam.put("sidx", "s.sort_order");
        specificationParam.put("order", "asc");
        List<GoodsSpecificationDO> goodsSpecificationEntityList = goodsSpecificationService.queryList(specificationParam);

        List<Map> specificationList = new ArrayList();
        //按规格名称分组
        for (int i = 0; i < goodsSpecificationEntityList.size(); i++) {
            GoodsSpecificationDO specItem = goodsSpecificationEntityList.get(i);
            //
            List<GoodsSpecificationDO> tempList = null;
            for (int j = 0; j < specificationList.size(); j++) {
                if (specificationList.get(j).get("specificationId").equals(specItem.getSpecification_id())) {
                    tempList = (List<GoodsSpecificationDO>) specificationList.get(j).get("valueList");
                    break;
                }
            }
            //
            if (null == tempList) {
                Map temp = new HashMap();
                temp.put("specificationId", specItem.getSpecification_id());
                temp.put("name", specItem.getName());
                tempList = new ArrayList();
                tempList.add(specItem);
                temp.put("valueList", tempList);
                specificationList.add(temp);
            } else {
                for (int j = 0; j < specificationList.size(); j++) {
                    if (specificationList.get(j).get("specificationId").equals(specItem.getSpecification_id())) {
                        tempList = (List<GoodsSpecificationDO>) specificationList.get(j).get("valueList");
                        tempList.add(specItem);
                        break;
                    }
                }
            }
        }
        //
        List<Product> productEntityList = productService.queryList(param);
        //
        List<GoodsGallery> gallery = goodsGalleryService.queryList(param);
        Map ngaParam = new HashMap();
        ngaParam.put("fields", "nga.value, na.name");
        ngaParam.put("sidx", "nga.id");
        ngaParam.put("order", "asc");
        ngaParam.put("goodsId", id);
        List<ShopAttribute> attribute = attributeService.queryList(ngaParam);
        //
        Map issueParam = new HashMap();
//        issueParam.put("goods_id", id);
        List<GoodsIssue> issue = goodsIssueService.queryList(issueParam);
        //
        ShopBrand brand = brandService.getById(info.getBrandId());
        //
        param.put("valueId", id);
        param.put("typeId", 0);
        Integer commentCount = commentService.queryTotal(param);
        List<ShopComment> hotComment = commentService.queryList(param);
        Map commentInfo = new HashMap();
        if (null != hotComment && hotComment.size() > 0) {
            ShopUser commentUser = userService.queryObject(hotComment.get(0).getUserId());
            commentInfo.put("content", Base64.getDecoder().decode(hotComment.get(0).getContent()));
            commentInfo.put("addTime", DateUtils.timeToStr(hotComment.get(0).getAddTime(), DateUtils.DATE_PATTERN));
            commentInfo.put("nickname", commentUser.getNickname());
            commentInfo.put("avatar", commentUser.getAvatar());
            Map paramPicture = new HashMap();
            paramPicture.put("commentId", hotComment.get(0).getId());
            List<CommentPicture> commentPictureEntities = commentPictureService.queryList(paramPicture);
            commentInfo.put("picList", commentPictureEntities);
        }
        Map comment = new HashMap();
        comment.put("count", commentCount);
        comment.put("data", commentInfo);
        //当前用户是否收藏
        Map collectParam = new HashMap();
        collectParam.put("userId", userId);
        collectParam.put("valueId", id);
        collectParam.put("typeId", 0);
        Integer userHasCollect = collectService.queryTotal(collectParam);
        if (userHasCollect > 0) {
            userHasCollect = 1;
        }
        //记录用户的足迹
        FootPrint footprintEntity = new FootPrint();
        footprintEntity.setAddTime((int)(System.currentTimeMillis() / 1000));
        //footprintEntity.setGoods_brief(info.getGoodsBrief());
        //footprintEntity.setList_pic_url(info.getListPicUrl());
        footprintEntity.setGoodsId(info.getId());
        //footprintEntity.setName(info.getName());
        //footprintEntity.setRetail_price(info.getRetailPrice());
        footprintEntity.setUserId(userId);
        if (null != referrer) {
            footprintEntity.setReferrer(referrer);
        } else {
            footprintEntity.setReferrer(0);
        }
        footPrintService.save(footprintEntity);
        //
        resultObj.put("info", info);
        resultObj.put("gallery", gallery);
        resultObj.put("attribute", attribute);
        resultObj.put("userHasCollect", userHasCollect);
        resultObj.put("issue", issue);
        resultObj.put("comment", comment);
        resultObj.put("brand", brand);
        resultObj.put("specificationList", specificationList);
        resultObj.put("productList", productEntityList);
        // 记录推荐人是否可以领取红包，用户登录时校验
        try {
            // 是否已经有可用的转发红包
            Map params = new HashMap();
            params.put("user_id", userId);
            params.put("send_type", 2);
            params.put("unUsed", true);
            List<CouponDO> enabledCouponVos = couponService.queryUserCoupons(params);
            if ((null == enabledCouponVos || enabledCouponVos.size() == 0)
                    && null != referrer && null != userId) {
                // 获取优惠信息提示
                Map couponParam = new HashMap();
                couponParam.put("enabled", true);
                Integer[] send_types = new Integer[]{2};
                couponParam.put("send_types", send_types);
                List<CouponDO> couponVos = couponService.queryList(couponParam);
                if (null != couponVos && couponVos.size() > 0) {
                    CouponDO couponVo = couponVos.get(0);
                    Map footprintParam = new HashMap();
                    footprintParam.put("goodsId", id);
                    footprintParam.put("referrer", referrer);
                    Integer footprintNum = footPrintService.queryTotal(footprintParam);
                    if (null != footprintNum && null != couponVo.getMin_transmit_num()
                            && footprintNum > couponVo.getMin_transmit_num()) {
                        UserCoupon userCouponVo = new UserCoupon();
                        userCouponVo.setAddTime(new Date());
                        userCouponVo.setCouponId(couponVo.getId());
                        userCouponVo.setCouponNumber(CharUtil.getRandomString(12));
                        userCouponVo.setUserId(userId);
                        userCouponService.save(userCouponVo);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toResponsSuccess(resultObj);
    }

    /**
     * 　获取分类下的商品
     */
    @ApiOperation(value = " 获取分类下的商品")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "分类id", paramType = "path", required = true)})
    @PostMapping(value = "category")
    public Object category(Integer id) {
        Map<String, Object> resultObj = new HashMap();
        //
        Category currentCategory = categoryService.queryObject(id);
        //
        Category parentCategory = categoryService.queryObject(currentCategory.getParentId());
        Map params = new HashMap();
        params.put("parentId", currentCategory.getParentId());
        List<Category> brotherCategory = categoryService.queryList(params);
        //
        resultObj.put("currentCategory", currentCategory);
        resultObj.put("parentCategory", parentCategory);
        resultObj.put("brotherCategory", brotherCategory);
        return toResponsSuccess(resultObj);
    }

    /**
     * 　　获取商品列表
     */
    @ApiOperation(value = "获取商品列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "categoryId", value = "分类id", paramType = "path", required = true),
            @ApiImplicitParam(name = "brandId", value = "品牌Id", paramType = "path", required = true),
            @ApiImplicitParam(name = "isNew", value = "新商品", paramType = "path", required = true),
            @ApiImplicitParam(name = "isHot", value = "热卖商品", paramType = "path", required = true)})
    @PostMapping(value = "list")
    public Object list(Integer categoryId,
                       Integer brandId, String keyword, Integer isNew, Integer isHot,
                       @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size,
                       String sort, String order) {
        Map params = new HashMap();
        params.put("isDelete", 0);
        params.put("isOnSale", 1);
        params.put("brandId", brandId);
        params.put("keyword", keyword);
        params.put("isNew", isNew);
        params.put("isHot", isHot);
        params.put("page", page);
        params.put("limit", size);
        params.put("order", order);
        params.put("sidx", sort);
        //
        if (null != sort && sort.equals("price")) {
            params.put("sidx", "retail_price");
            params.put("order", sort);
        } else {
            params.put("sidx", "id");
            params.put("order", "desc");
        }
        //添加到搜索历史
        if (!StringUtils.isNullOrEmpty(keyword)) {
            SearchHistory searchHistoryVo = new SearchHistory();
            searchHistoryVo.setAddTime((int)(System.currentTimeMillis() / 1000));
            searchHistoryVo.setKeyword(keyword);
            searchHistoryVo.setUserId("");
            searchHistoryVo.setFrom("");
            searchHistoryService.save(searchHistoryVo);

        }
        //筛选的分类
        List<CategoryInfo> filterCategory = new ArrayList();
        CategoryInfo rootCategory = new CategoryInfo();
        rootCategory.setId(0);
        rootCategory.setName("全部");
        rootCategory.setChecked(false);
        filterCategory.add(rootCategory);
        //
        params.put("fields", "category_id");
        List<GoodsDO> categoryEntityList = goodsService.queryList(params);
        params.remove("fields");
        if (null != categoryEntityList && categoryEntityList.size() > 0) {
            List<Integer> categoryIds = new ArrayList();
            for (GoodsDO goodsVo : categoryEntityList) {
                categoryIds.add(goodsVo.getCategoryId());
            }
            //查找二级分类的parent_id
            Map categoryParam = new HashMap();
            categoryParam.put("ids", categoryIds);
            categoryParam.put("fields", "parent_id");
            List<Category> parentCategoryList = categoryService.queryList(categoryParam);
            //
            List<Integer> parentIds = new ArrayList();
            for (Category categoryEntity : parentCategoryList) {
                parentIds.add(categoryEntity.getParentId());
            }
            //一级分类
            categoryParam = new HashMap();
            categoryParam.put("fields", "id,name");
            categoryParam.put("order", "asc");
            categoryParam.put("sidx", "sort_order");
            categoryParam.put("ids", parentIds);
            List<Category> parentCategory = categoryService.queryList(categoryParam);
            if (null != parentCategory) {
                for(Category category:parentCategory){
                    CategoryInfo info=new CategoryInfo();
                    BeanUtils.copyProperties(category,info);
                    filterCategory.add(info);
                }
            }
        }
        //加入分类条件
        if (null != categoryId && categoryId > 0) {
            List<Integer> categoryIds = new ArrayList();
            Map categoryParam = new HashMap();
            categoryParam.put("parentId", categoryId);
            categoryParam.put("fields", "id");
            List<Category> childIds = categoryService.queryList(categoryParam);
            for (Category categoryEntity : childIds) {
                categoryIds.add(categoryEntity.getId());
            }
            categoryIds.add(categoryId);
            params.put("categoryIds", categoryIds);
        }
        //查询列表数据
        params.put("fields", "id, name, list_pic_url, market_price, retail_price, goods_brief");

        PageInfo<GoodsDO> pageInfo=goodsService.queryByPage(params,page,size);
        List<GoodsDO> goodsList = pageInfo.getList();

        //搜索到的商品
        for (CategoryInfo categoryEntity : filterCategory) {
        	if (null != categoryId && (categoryEntity.getId() == 0 || categoryId.equals(categoryEntity.getId()))
        	   ||null == categoryId&&null == categoryEntity.getId()) {
                categoryEntity.setChecked(true);
            } else {
                categoryEntity.setChecked(false);
            }
        }
        Map<String,Object> result=new HashMap<>();
        result.put("currentPage",pageInfo.getPageNum());
        result.put("totalPages",pageInfo.getPages());
        result.put("filterCategory",filterCategory);
        result.put("goodsList",goodsList);
        return toResponsSuccess(result);
    }

    /**
     * 　　商品列表筛选的分类列表
     */
    @ApiOperation(value = "商品列表筛选的分类列表")
    @PostMapping(value = "filter")
    public Object filter(@RequestBody GoodsModel goodsModel) {
        Map params = new HashMap();
        params.put("isDelete", 0);
        params.put("isOnSale", 1);
        params.put("categoryId", goodsModel.getCategoryId());
        params.put("keyword", goodsModel.getKeyword());
        params.put("isNew", goodsModel.getIsNew());
        params.put("isHot", goodsModel.getIsHot());
        if (null != goodsModel.getCategoryId()) {
            Map categoryParams = new HashMap();
            categoryParams.put("categoryId", goodsModel.getCategoryId());
            List<Category> categoryEntityList = categoryService.queryList(categoryParams);
            List<Integer> category_ids = new ArrayList();
            for (Category categoryEntity : categoryEntityList) {
                category_ids.add(categoryEntity.getId());
            }
            params.put("category_id", category_ids);
        }
        //筛选的分类
        List<Category> filterCategory = new ArrayList();
        Category rootCategory = new Category();
        rootCategory.setId(0);
        rootCategory.setName("全部");
        // 二级分类id
        List<GoodsDO> goodsEntityList = goodsService.queryList(params);
        if (null != goodsEntityList && goodsEntityList.size() > 0) {
            List<Integer> categoryIds = new ArrayList();
            for (GoodsDO goodsEntity : goodsEntityList) {
                categoryIds.add(goodsEntity.getCategoryId());
            }
            //查找二级分类的parent_id
            Map categoryParam = new HashMap();
            categoryParam.put("categoryIds", categoryIds);
            List<Category> parentCategoryList = categoryService.queryList(categoryParam);
            //
            List<Integer> parentIds = new ArrayList();
            for (Category categoryEntity : parentCategoryList) {
                parentIds.add(categoryEntity.getId());
            }
            //一级分类
            categoryParam.put("categoryIds", parentIds);
            List<Category> parentCategory = categoryService.queryList(categoryParam);
            if (null != parentCategory) {
                filterCategory.addAll(parentCategory);
            }
        }
        return toResponsSuccess(filterCategory);
    }

    /**
     * 　　新品首发
     */
    @ApiOperation(value = "新品首发")
    @PostMapping(value = "new")
    public Object newAction() {
        Map<String, Object> resultObj = new HashMap();
        Map bannerInfo = new HashMap();
        bannerInfo.put("url", "");
        bannerInfo.put("name", "坚持初心，为你寻觅世间好物");
        bannerInfo.put("img_url", "https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/20180727/1504208321fef4.png");
        resultObj.put("bannerInfo", bannerInfo);
        return toResponsSuccess(resultObj);
    }

    /**
     * 　　人气推荐
     */
    @ApiOperation(value = "人气推荐")
    @PostMapping(value = "hot")
    public Object hot() {
        Map<String, Object> resultObj = new HashMap();
        Map bannerInfo = new HashMap();
        bannerInfo.put("url", "");
        bannerInfo.put("name", "大家都在买的严选好物");
        bannerInfo.put("img_url", "https://platform-wxmall.oss-cn-beijing.aliyuncs.com/upload/20180727/1504208321fef4.png");
        resultObj.put("bannerInfo", bannerInfo);
        return toResponsSuccess(resultObj);
    }

    /**
     * 　　商品详情页的大家都在看的商品
     */
    @ApiOperation(value = "商品详情页")
    @PostMapping(value = "related")
    public Object related(Integer id) {
        Map<String, Object> resultObj = new HashMap();
        Map param = new HashMap();
        param.put("goodsId", id);
        param.put("fields", "related_goods_id");
        List<RelatedGoods> relatedGoodsEntityList = relatedGoodsService.queryList(param);

        List<Integer> relatedGoodsIds = new ArrayList();
        for (RelatedGoods relatedGoodsEntity : relatedGoodsEntityList) {
            relatedGoodsIds.add(relatedGoodsEntity.getRelatedGoodsId());
        }

        List<Goods> relatedGoods = new ArrayList<>();

        if (null == relatedGoodsIds || relatedGoods.size() < 1) {
            //查找同分类下的商品
            Goods goodsCategory = goodsService.queryObject(id);
            Map paramRelated = new HashMap();
            paramRelated.put("fields", "id, name, list_pic_url, retail_price");
            paramRelated.put("categoryId", goodsCategory.getCategoryId());
            relatedGoods = goodsService.queryList(paramRelated);
        } else {
            Map paramRelated = new HashMap();
            paramRelated.put("goodsIds", relatedGoodsIds);
            paramRelated.put("fields", "id, name, list_pic_url, retail_price");
            relatedGoods = goodsService.queryList(paramRelated);
        }
        resultObj.put("goodsList", relatedGoods);
        return toResponsSuccess(resultObj);
    }

    /**
     * 　　在售的商品总数
     */
    @ApiOperation(value = "在售的商品总数")
    @PostMapping(value = "count")
    public Object count() {
        Map<String, Object> resultObj = new HashMap();
        Map param = new HashMap();
        param.put("is_delete", 0);
        param.put("is_on_sale", 1);
        Integer goodsCount = goodsService.queryTotal(param);
        resultObj.put("goodsCount", goodsCount);
        return toResponsSuccess(resultObj);
    }

    /**
     * 　　获取商品列表
     */
    @ApiOperation(value = "获取商品列表")
    @PostMapping(value = "productlist")
    public Object productlist(@RequestBody GoodsModel goodsModel) {
        Map params = new HashMap();
        params.put("isNew", goodsModel.getIsNew());
        params.put("order",goodsModel.getSort());
        params.put("sidx", goodsModel.getOrder());
        //
        if (null != goodsModel.getSort() && goodsModel.getSort().equals("price")) {
            params.put("sidx", "retail_price");
            params.put("order", goodsModel.getOrder());
        } else if (null != goodsModel.getSort() && goodsModel.getSort().equals("sell")) {
            params.put("sidx", "orderNum");
            params.put("order", goodsModel.getOrder());
        } else {
            params.put("sidx", "id");
            params.put("order", "desc");
        }
        // 0不限 1特价 2团购
        if (null != goodsModel.getDiscount() && goodsModel.getDiscount() == 1) {
            params.put("isHot", 1);
        } else if (null != goodsModel.getDiscount() && goodsModel.getDiscount() == 2) {
            params.put("isGroup", true);
        }
        //加入分类条件
        if (null != goodsModel.getCategoryId() && goodsModel.getCategoryId() > 0) {
            List<Integer> categoryIds = new ArrayList();
            Map categoryParam = new HashMap();
            categoryParam.put("parentId", goodsModel.getCategoryId());
            categoryParam.put("fields", "id");
            List<Category> childIds = categoryService.queryList(categoryParam);
            for (Category categoryEntity : childIds) {
                categoryIds.add(categoryEntity.getId());
            }
            categoryIds.add(goodsModel.getCategoryId());
            params.put("categoryIds", categoryIds);
        }
        //查询列表数据

        PageInfo<GoodsDO> pageInfo=goodsService.queryCatalogProductByPage(params,goodsModel.getPageNum(),goodsModel.getPageSize());
        List<GoodsDO> goodsList = pageInfo.getList();
        // 当前购物车中
        List<ShopCartDO> cartList = new ArrayList();
        if (null != goodsModel.getUserId()) {
            //查询列表数据
            Map cartParam = new HashMap();
            cartParam.put("userId", goodsModel.getUserId());
            cartList = cartService.queryList(goodsModel.getUserId(), "", null,null,null,"");
        }
        if (null != cartList && cartList.size() > 0 && null != goodsList && goodsList.size() > 0) {
            for (GoodsDO goodsVo : goodsList) {
                for (ShopCartDO cartVo : cartList) {
                    if (goodsVo.getId().equals(cartVo.getGoodsId())) {
                        goodsVo.setCartNum(cartVo.getNumber());
                    }
                }
            }
        }
        return toResponsSuccess(pageInfo);
    }
}