package com.seamwhole.servicetradecore.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShopBrandExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopBrandExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andListPicUrlIsNull() {
            addCriterion("list_pic_url is null");
            return (Criteria) this;
        }

        public Criteria andListPicUrlIsNotNull() {
            addCriterion("list_pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andListPicUrlEqualTo(String value) {
            addCriterion("list_pic_url =", value, "listPicUrl");
            return (Criteria) this;
        }

        public Criteria andListPicUrlNotEqualTo(String value) {
            addCriterion("list_pic_url <>", value, "listPicUrl");
            return (Criteria) this;
        }

        public Criteria andListPicUrlGreaterThan(String value) {
            addCriterion("list_pic_url >", value, "listPicUrl");
            return (Criteria) this;
        }

        public Criteria andListPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("list_pic_url >=", value, "listPicUrl");
            return (Criteria) this;
        }

        public Criteria andListPicUrlLessThan(String value) {
            addCriterion("list_pic_url <", value, "listPicUrl");
            return (Criteria) this;
        }

        public Criteria andListPicUrlLessThanOrEqualTo(String value) {
            addCriterion("list_pic_url <=", value, "listPicUrl");
            return (Criteria) this;
        }

        public Criteria andListPicUrlLike(String value) {
            addCriterion("list_pic_url like", value, "listPicUrl");
            return (Criteria) this;
        }

        public Criteria andListPicUrlNotLike(String value) {
            addCriterion("list_pic_url not like", value, "listPicUrl");
            return (Criteria) this;
        }

        public Criteria andListPicUrlIn(List<String> values) {
            addCriterion("list_pic_url in", values, "listPicUrl");
            return (Criteria) this;
        }

        public Criteria andListPicUrlNotIn(List<String> values) {
            addCriterion("list_pic_url not in", values, "listPicUrl");
            return (Criteria) this;
        }

        public Criteria andListPicUrlBetween(String value1, String value2) {
            addCriterion("list_pic_url between", value1, value2, "listPicUrl");
            return (Criteria) this;
        }

        public Criteria andListPicUrlNotBetween(String value1, String value2) {
            addCriterion("list_pic_url not between", value1, value2, "listPicUrl");
            return (Criteria) this;
        }

        public Criteria andSimpleDescIsNull() {
            addCriterion("simple_desc is null");
            return (Criteria) this;
        }

        public Criteria andSimpleDescIsNotNull() {
            addCriterion("simple_desc is not null");
            return (Criteria) this;
        }

        public Criteria andSimpleDescEqualTo(String value) {
            addCriterion("simple_desc =", value, "simpleDesc");
            return (Criteria) this;
        }

        public Criteria andSimpleDescNotEqualTo(String value) {
            addCriterion("simple_desc <>", value, "simpleDesc");
            return (Criteria) this;
        }

        public Criteria andSimpleDescGreaterThan(String value) {
            addCriterion("simple_desc >", value, "simpleDesc");
            return (Criteria) this;
        }

        public Criteria andSimpleDescGreaterThanOrEqualTo(String value) {
            addCriterion("simple_desc >=", value, "simpleDesc");
            return (Criteria) this;
        }

        public Criteria andSimpleDescLessThan(String value) {
            addCriterion("simple_desc <", value, "simpleDesc");
            return (Criteria) this;
        }

        public Criteria andSimpleDescLessThanOrEqualTo(String value) {
            addCriterion("simple_desc <=", value, "simpleDesc");
            return (Criteria) this;
        }

        public Criteria andSimpleDescLike(String value) {
            addCriterion("simple_desc like", value, "simpleDesc");
            return (Criteria) this;
        }

        public Criteria andSimpleDescNotLike(String value) {
            addCriterion("simple_desc not like", value, "simpleDesc");
            return (Criteria) this;
        }

        public Criteria andSimpleDescIn(List<String> values) {
            addCriterion("simple_desc in", values, "simpleDesc");
            return (Criteria) this;
        }

        public Criteria andSimpleDescNotIn(List<String> values) {
            addCriterion("simple_desc not in", values, "simpleDesc");
            return (Criteria) this;
        }

        public Criteria andSimpleDescBetween(String value1, String value2) {
            addCriterion("simple_desc between", value1, value2, "simpleDesc");
            return (Criteria) this;
        }

        public Criteria andSimpleDescNotBetween(String value1, String value2) {
            addCriterion("simple_desc not between", value1, value2, "simpleDesc");
            return (Criteria) this;
        }

        public Criteria andPicUrlIsNull() {
            addCriterion("pic_url is null");
            return (Criteria) this;
        }

        public Criteria andPicUrlIsNotNull() {
            addCriterion("pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andPicUrlEqualTo(String value) {
            addCriterion("pic_url =", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotEqualTo(String value) {
            addCriterion("pic_url <>", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlGreaterThan(String value) {
            addCriterion("pic_url >", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("pic_url >=", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLessThan(String value) {
            addCriterion("pic_url <", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLessThanOrEqualTo(String value) {
            addCriterion("pic_url <=", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLike(String value) {
            addCriterion("pic_url like", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotLike(String value) {
            addCriterion("pic_url not like", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlIn(List<String> values) {
            addCriterion("pic_url in", values, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotIn(List<String> values) {
            addCriterion("pic_url not in", values, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlBetween(String value1, String value2) {
            addCriterion("pic_url between", value1, value2, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotBetween(String value1, String value2) {
            addCriterion("pic_url not between", value1, value2, "picUrl");
            return (Criteria) this;
        }

        public Criteria andSortOrderIsNull() {
            addCriterion("sort_order is null");
            return (Criteria) this;
        }

        public Criteria andSortOrderIsNotNull() {
            addCriterion("sort_order is not null");
            return (Criteria) this;
        }

        public Criteria andSortOrderEqualTo(Byte value) {
            addCriterion("sort_order =", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderNotEqualTo(Byte value) {
            addCriterion("sort_order <>", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderGreaterThan(Byte value) {
            addCriterion("sort_order >", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderGreaterThanOrEqualTo(Byte value) {
            addCriterion("sort_order >=", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderLessThan(Byte value) {
            addCriterion("sort_order <", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderLessThanOrEqualTo(Byte value) {
            addCriterion("sort_order <=", value, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderIn(List<Byte> values) {
            addCriterion("sort_order in", values, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderNotIn(List<Byte> values) {
            addCriterion("sort_order not in", values, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderBetween(Byte value1, Byte value2) {
            addCriterion("sort_order between", value1, value2, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andSortOrderNotBetween(Byte value1, Byte value2) {
            addCriterion("sort_order not between", value1, value2, "sortOrder");
            return (Criteria) this;
        }

        public Criteria andIsShowIsNull() {
            addCriterion("is_show is null");
            return (Criteria) this;
        }

        public Criteria andIsShowIsNotNull() {
            addCriterion("is_show is not null");
            return (Criteria) this;
        }

        public Criteria andIsShowEqualTo(Boolean value) {
            addCriterion("is_show =", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotEqualTo(Boolean value) {
            addCriterion("is_show <>", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowGreaterThan(Boolean value) {
            addCriterion("is_show >", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_show >=", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLessThan(Boolean value) {
            addCriterion("is_show <", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLessThanOrEqualTo(Boolean value) {
            addCriterion("is_show <=", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowIn(List<Boolean> values) {
            addCriterion("is_show in", values, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotIn(List<Boolean> values) {
            addCriterion("is_show not in", values, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowBetween(Boolean value1, Boolean value2) {
            addCriterion("is_show between", value1, value2, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_show not between", value1, value2, "isShow");
            return (Criteria) this;
        }

        public Criteria andFloorPriceIsNull() {
            addCriterion("floor_price is null");
            return (Criteria) this;
        }

        public Criteria andFloorPriceIsNotNull() {
            addCriterion("floor_price is not null");
            return (Criteria) this;
        }

        public Criteria andFloorPriceEqualTo(BigDecimal value) {
            addCriterion("floor_price =", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceNotEqualTo(BigDecimal value) {
            addCriterion("floor_price <>", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceGreaterThan(BigDecimal value) {
            addCriterion("floor_price >", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_price >=", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceLessThan(BigDecimal value) {
            addCriterion("floor_price <", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_price <=", value, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceIn(List<BigDecimal> values) {
            addCriterion("floor_price in", values, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceNotIn(List<BigDecimal> values) {
            addCriterion("floor_price not in", values, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_price between", value1, value2, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andFloorPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_price not between", value1, value2, "floorPrice");
            return (Criteria) this;
        }

        public Criteria andAppListPicUrlIsNull() {
            addCriterion("app_list_pic_url is null");
            return (Criteria) this;
        }

        public Criteria andAppListPicUrlIsNotNull() {
            addCriterion("app_list_pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andAppListPicUrlEqualTo(String value) {
            addCriterion("app_list_pic_url =", value, "appListPicUrl");
            return (Criteria) this;
        }

        public Criteria andAppListPicUrlNotEqualTo(String value) {
            addCriterion("app_list_pic_url <>", value, "appListPicUrl");
            return (Criteria) this;
        }

        public Criteria andAppListPicUrlGreaterThan(String value) {
            addCriterion("app_list_pic_url >", value, "appListPicUrl");
            return (Criteria) this;
        }

        public Criteria andAppListPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("app_list_pic_url >=", value, "appListPicUrl");
            return (Criteria) this;
        }

        public Criteria andAppListPicUrlLessThan(String value) {
            addCriterion("app_list_pic_url <", value, "appListPicUrl");
            return (Criteria) this;
        }

        public Criteria andAppListPicUrlLessThanOrEqualTo(String value) {
            addCriterion("app_list_pic_url <=", value, "appListPicUrl");
            return (Criteria) this;
        }

        public Criteria andAppListPicUrlLike(String value) {
            addCriterion("app_list_pic_url like", value, "appListPicUrl");
            return (Criteria) this;
        }

        public Criteria andAppListPicUrlNotLike(String value) {
            addCriterion("app_list_pic_url not like", value, "appListPicUrl");
            return (Criteria) this;
        }

        public Criteria andAppListPicUrlIn(List<String> values) {
            addCriterion("app_list_pic_url in", values, "appListPicUrl");
            return (Criteria) this;
        }

        public Criteria andAppListPicUrlNotIn(List<String> values) {
            addCriterion("app_list_pic_url not in", values, "appListPicUrl");
            return (Criteria) this;
        }

        public Criteria andAppListPicUrlBetween(String value1, String value2) {
            addCriterion("app_list_pic_url between", value1, value2, "appListPicUrl");
            return (Criteria) this;
        }

        public Criteria andAppListPicUrlNotBetween(String value1, String value2) {
            addCriterion("app_list_pic_url not between", value1, value2, "appListPicUrl");
            return (Criteria) this;
        }

        public Criteria andIsNewIsNull() {
            addCriterion("is_new is null");
            return (Criteria) this;
        }

        public Criteria andIsNewIsNotNull() {
            addCriterion("is_new is not null");
            return (Criteria) this;
        }

        public Criteria andIsNewEqualTo(Boolean value) {
            addCriterion("is_new =", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotEqualTo(Boolean value) {
            addCriterion("is_new <>", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewGreaterThan(Boolean value) {
            addCriterion("is_new >", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_new >=", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewLessThan(Boolean value) {
            addCriterion("is_new <", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewLessThanOrEqualTo(Boolean value) {
            addCriterion("is_new <=", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewIn(List<Boolean> values) {
            addCriterion("is_new in", values, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotIn(List<Boolean> values) {
            addCriterion("is_new not in", values, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewBetween(Boolean value1, Boolean value2) {
            addCriterion("is_new between", value1, value2, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_new not between", value1, value2, "isNew");
            return (Criteria) this;
        }

        public Criteria andNewPicUrlIsNull() {
            addCriterion("new_pic_url is null");
            return (Criteria) this;
        }

        public Criteria andNewPicUrlIsNotNull() {
            addCriterion("new_pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andNewPicUrlEqualTo(String value) {
            addCriterion("new_pic_url =", value, "newPicUrl");
            return (Criteria) this;
        }

        public Criteria andNewPicUrlNotEqualTo(String value) {
            addCriterion("new_pic_url <>", value, "newPicUrl");
            return (Criteria) this;
        }

        public Criteria andNewPicUrlGreaterThan(String value) {
            addCriterion("new_pic_url >", value, "newPicUrl");
            return (Criteria) this;
        }

        public Criteria andNewPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("new_pic_url >=", value, "newPicUrl");
            return (Criteria) this;
        }

        public Criteria andNewPicUrlLessThan(String value) {
            addCriterion("new_pic_url <", value, "newPicUrl");
            return (Criteria) this;
        }

        public Criteria andNewPicUrlLessThanOrEqualTo(String value) {
            addCriterion("new_pic_url <=", value, "newPicUrl");
            return (Criteria) this;
        }

        public Criteria andNewPicUrlLike(String value) {
            addCriterion("new_pic_url like", value, "newPicUrl");
            return (Criteria) this;
        }

        public Criteria andNewPicUrlNotLike(String value) {
            addCriterion("new_pic_url not like", value, "newPicUrl");
            return (Criteria) this;
        }

        public Criteria andNewPicUrlIn(List<String> values) {
            addCriterion("new_pic_url in", values, "newPicUrl");
            return (Criteria) this;
        }

        public Criteria andNewPicUrlNotIn(List<String> values) {
            addCriterion("new_pic_url not in", values, "newPicUrl");
            return (Criteria) this;
        }

        public Criteria andNewPicUrlBetween(String value1, String value2) {
            addCriterion("new_pic_url between", value1, value2, "newPicUrl");
            return (Criteria) this;
        }

        public Criteria andNewPicUrlNotBetween(String value1, String value2) {
            addCriterion("new_pic_url not between", value1, value2, "newPicUrl");
            return (Criteria) this;
        }

        public Criteria andNewSortOrderIsNull() {
            addCriterion("new_sort_order is null");
            return (Criteria) this;
        }

        public Criteria andNewSortOrderIsNotNull() {
            addCriterion("new_sort_order is not null");
            return (Criteria) this;
        }

        public Criteria andNewSortOrderEqualTo(Byte value) {
            addCriterion("new_sort_order =", value, "newSortOrder");
            return (Criteria) this;
        }

        public Criteria andNewSortOrderNotEqualTo(Byte value) {
            addCriterion("new_sort_order <>", value, "newSortOrder");
            return (Criteria) this;
        }

        public Criteria andNewSortOrderGreaterThan(Byte value) {
            addCriterion("new_sort_order >", value, "newSortOrder");
            return (Criteria) this;
        }

        public Criteria andNewSortOrderGreaterThanOrEqualTo(Byte value) {
            addCriterion("new_sort_order >=", value, "newSortOrder");
            return (Criteria) this;
        }

        public Criteria andNewSortOrderLessThan(Byte value) {
            addCriterion("new_sort_order <", value, "newSortOrder");
            return (Criteria) this;
        }

        public Criteria andNewSortOrderLessThanOrEqualTo(Byte value) {
            addCriterion("new_sort_order <=", value, "newSortOrder");
            return (Criteria) this;
        }

        public Criteria andNewSortOrderIn(List<Byte> values) {
            addCriterion("new_sort_order in", values, "newSortOrder");
            return (Criteria) this;
        }

        public Criteria andNewSortOrderNotIn(List<Byte> values) {
            addCriterion("new_sort_order not in", values, "newSortOrder");
            return (Criteria) this;
        }

        public Criteria andNewSortOrderBetween(Byte value1, Byte value2) {
            addCriterion("new_sort_order between", value1, value2, "newSortOrder");
            return (Criteria) this;
        }

        public Criteria andNewSortOrderNotBetween(Byte value1, Byte value2) {
            addCriterion("new_sort_order not between", value1, value2, "newSortOrder");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}