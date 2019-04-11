package com.seamwhole.serviceerpcore.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AssetExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAssetnameidIsNull() {
            addCriterion("assetnameID is null");
            return (Criteria) this;
        }

        public Criteria andAssetnameidIsNotNull() {
            addCriterion("assetnameID is not null");
            return (Criteria) this;
        }

        public Criteria andAssetnameidEqualTo(Long value) {
            addCriterion("assetnameID =", value, "assetnameid");
            return (Criteria) this;
        }

        public Criteria andAssetnameidNotEqualTo(Long value) {
            addCriterion("assetnameID <>", value, "assetnameid");
            return (Criteria) this;
        }

        public Criteria andAssetnameidGreaterThan(Long value) {
            addCriterion("assetnameID >", value, "assetnameid");
            return (Criteria) this;
        }

        public Criteria andAssetnameidGreaterThanOrEqualTo(Long value) {
            addCriterion("assetnameID >=", value, "assetnameid");
            return (Criteria) this;
        }

        public Criteria andAssetnameidLessThan(Long value) {
            addCriterion("assetnameID <", value, "assetnameid");
            return (Criteria) this;
        }

        public Criteria andAssetnameidLessThanOrEqualTo(Long value) {
            addCriterion("assetnameID <=", value, "assetnameid");
            return (Criteria) this;
        }

        public Criteria andAssetnameidIn(List<Long> values) {
            addCriterion("assetnameID in", values, "assetnameid");
            return (Criteria) this;
        }

        public Criteria andAssetnameidNotIn(List<Long> values) {
            addCriterion("assetnameID not in", values, "assetnameid");
            return (Criteria) this;
        }

        public Criteria andAssetnameidBetween(Long value1, Long value2) {
            addCriterion("assetnameID between", value1, value2, "assetnameid");
            return (Criteria) this;
        }

        public Criteria andAssetnameidNotBetween(Long value1, Long value2) {
            addCriterion("assetnameID not between", value1, value2, "assetnameid");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLabelsIsNull() {
            addCriterion("labels is null");
            return (Criteria) this;
        }

        public Criteria andLabelsIsNotNull() {
            addCriterion("labels is not null");
            return (Criteria) this;
        }

        public Criteria andLabelsEqualTo(String value) {
            addCriterion("labels =", value, "labels");
            return (Criteria) this;
        }

        public Criteria andLabelsNotEqualTo(String value) {
            addCriterion("labels <>", value, "labels");
            return (Criteria) this;
        }

        public Criteria andLabelsGreaterThan(String value) {
            addCriterion("labels >", value, "labels");
            return (Criteria) this;
        }

        public Criteria andLabelsGreaterThanOrEqualTo(String value) {
            addCriterion("labels >=", value, "labels");
            return (Criteria) this;
        }

        public Criteria andLabelsLessThan(String value) {
            addCriterion("labels <", value, "labels");
            return (Criteria) this;
        }

        public Criteria andLabelsLessThanOrEqualTo(String value) {
            addCriterion("labels <=", value, "labels");
            return (Criteria) this;
        }

        public Criteria andLabelsLike(String value) {
            addCriterion("labels like", value, "labels");
            return (Criteria) this;
        }

        public Criteria andLabelsNotLike(String value) {
            addCriterion("labels not like", value, "labels");
            return (Criteria) this;
        }

        public Criteria andLabelsIn(List<String> values) {
            addCriterion("labels in", values, "labels");
            return (Criteria) this;
        }

        public Criteria andLabelsNotIn(List<String> values) {
            addCriterion("labels not in", values, "labels");
            return (Criteria) this;
        }

        public Criteria andLabelsBetween(String value1, String value2) {
            addCriterion("labels between", value1, value2, "labels");
            return (Criteria) this;
        }

        public Criteria andLabelsNotBetween(String value1, String value2) {
            addCriterion("labels not between", value1, value2, "labels");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Short value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Short value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Short value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Short value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Short value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Short> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Short> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Short value1, Short value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Short value1, Short value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userID is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userID is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Long value) {
            addCriterion("userID =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Long value) {
            addCriterion("userID <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Long value) {
            addCriterion("userID >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Long value) {
            addCriterion("userID >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Long value) {
            addCriterion("userID <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Long value) {
            addCriterion("userID <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Long> values) {
            addCriterion("userID in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Long> values) {
            addCriterion("userID not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Long value1, Long value2) {
            addCriterion("userID between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Long value1, Long value2) {
            addCriterion("userID not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPurchasedateIsNull() {
            addCriterion("purchasedate is null");
            return (Criteria) this;
        }

        public Criteria andPurchasedateIsNotNull() {
            addCriterion("purchasedate is not null");
            return (Criteria) this;
        }

        public Criteria andPurchasedateEqualTo(Date value) {
            addCriterion("purchasedate =", value, "purchasedate");
            return (Criteria) this;
        }

        public Criteria andPurchasedateNotEqualTo(Date value) {
            addCriterion("purchasedate <>", value, "purchasedate");
            return (Criteria) this;
        }

        public Criteria andPurchasedateGreaterThan(Date value) {
            addCriterion("purchasedate >", value, "purchasedate");
            return (Criteria) this;
        }

        public Criteria andPurchasedateGreaterThanOrEqualTo(Date value) {
            addCriterion("purchasedate >=", value, "purchasedate");
            return (Criteria) this;
        }

        public Criteria andPurchasedateLessThan(Date value) {
            addCriterion("purchasedate <", value, "purchasedate");
            return (Criteria) this;
        }

        public Criteria andPurchasedateLessThanOrEqualTo(Date value) {
            addCriterion("purchasedate <=", value, "purchasedate");
            return (Criteria) this;
        }

        public Criteria andPurchasedateIn(List<Date> values) {
            addCriterion("purchasedate in", values, "purchasedate");
            return (Criteria) this;
        }

        public Criteria andPurchasedateNotIn(List<Date> values) {
            addCriterion("purchasedate not in", values, "purchasedate");
            return (Criteria) this;
        }

        public Criteria andPurchasedateBetween(Date value1, Date value2) {
            addCriterion("purchasedate between", value1, value2, "purchasedate");
            return (Criteria) this;
        }

        public Criteria andPurchasedateNotBetween(Date value1, Date value2) {
            addCriterion("purchasedate not between", value1, value2, "purchasedate");
            return (Criteria) this;
        }

        public Criteria andPeriodofvalidityIsNull() {
            addCriterion("periodofvalidity is null");
            return (Criteria) this;
        }

        public Criteria andPeriodofvalidityIsNotNull() {
            addCriterion("periodofvalidity is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodofvalidityEqualTo(Date value) {
            addCriterion("periodofvalidity =", value, "periodofvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodofvalidityNotEqualTo(Date value) {
            addCriterion("periodofvalidity <>", value, "periodofvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodofvalidityGreaterThan(Date value) {
            addCriterion("periodofvalidity >", value, "periodofvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodofvalidityGreaterThanOrEqualTo(Date value) {
            addCriterion("periodofvalidity >=", value, "periodofvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodofvalidityLessThan(Date value) {
            addCriterion("periodofvalidity <", value, "periodofvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodofvalidityLessThanOrEqualTo(Date value) {
            addCriterion("periodofvalidity <=", value, "periodofvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodofvalidityIn(List<Date> values) {
            addCriterion("periodofvalidity in", values, "periodofvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodofvalidityNotIn(List<Date> values) {
            addCriterion("periodofvalidity not in", values, "periodofvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodofvalidityBetween(Date value1, Date value2) {
            addCriterion("periodofvalidity between", value1, value2, "periodofvalidity");
            return (Criteria) this;
        }

        public Criteria andPeriodofvalidityNotBetween(Date value1, Date value2) {
            addCriterion("periodofvalidity not between", value1, value2, "periodofvalidity");
            return (Criteria) this;
        }

        public Criteria andWarrantydateIsNull() {
            addCriterion("warrantydate is null");
            return (Criteria) this;
        }

        public Criteria andWarrantydateIsNotNull() {
            addCriterion("warrantydate is not null");
            return (Criteria) this;
        }

        public Criteria andWarrantydateEqualTo(Date value) {
            addCriterion("warrantydate =", value, "warrantydate");
            return (Criteria) this;
        }

        public Criteria andWarrantydateNotEqualTo(Date value) {
            addCriterion("warrantydate <>", value, "warrantydate");
            return (Criteria) this;
        }

        public Criteria andWarrantydateGreaterThan(Date value) {
            addCriterion("warrantydate >", value, "warrantydate");
            return (Criteria) this;
        }

        public Criteria andWarrantydateGreaterThanOrEqualTo(Date value) {
            addCriterion("warrantydate >=", value, "warrantydate");
            return (Criteria) this;
        }

        public Criteria andWarrantydateLessThan(Date value) {
            addCriterion("warrantydate <", value, "warrantydate");
            return (Criteria) this;
        }

        public Criteria andWarrantydateLessThanOrEqualTo(Date value) {
            addCriterion("warrantydate <=", value, "warrantydate");
            return (Criteria) this;
        }

        public Criteria andWarrantydateIn(List<Date> values) {
            addCriterion("warrantydate in", values, "warrantydate");
            return (Criteria) this;
        }

        public Criteria andWarrantydateNotIn(List<Date> values) {
            addCriterion("warrantydate not in", values, "warrantydate");
            return (Criteria) this;
        }

        public Criteria andWarrantydateBetween(Date value1, Date value2) {
            addCriterion("warrantydate between", value1, value2, "warrantydate");
            return (Criteria) this;
        }

        public Criteria andWarrantydateNotBetween(Date value1, Date value2) {
            addCriterion("warrantydate not between", value1, value2, "warrantydate");
            return (Criteria) this;
        }

        public Criteria andAssetnumIsNull() {
            addCriterion("assetnum is null");
            return (Criteria) this;
        }

        public Criteria andAssetnumIsNotNull() {
            addCriterion("assetnum is not null");
            return (Criteria) this;
        }

        public Criteria andAssetnumEqualTo(String value) {
            addCriterion("assetnum =", value, "assetnum");
            return (Criteria) this;
        }

        public Criteria andAssetnumNotEqualTo(String value) {
            addCriterion("assetnum <>", value, "assetnum");
            return (Criteria) this;
        }

        public Criteria andAssetnumGreaterThan(String value) {
            addCriterion("assetnum >", value, "assetnum");
            return (Criteria) this;
        }

        public Criteria andAssetnumGreaterThanOrEqualTo(String value) {
            addCriterion("assetnum >=", value, "assetnum");
            return (Criteria) this;
        }

        public Criteria andAssetnumLessThan(String value) {
            addCriterion("assetnum <", value, "assetnum");
            return (Criteria) this;
        }

        public Criteria andAssetnumLessThanOrEqualTo(String value) {
            addCriterion("assetnum <=", value, "assetnum");
            return (Criteria) this;
        }

        public Criteria andAssetnumLike(String value) {
            addCriterion("assetnum like", value, "assetnum");
            return (Criteria) this;
        }

        public Criteria andAssetnumNotLike(String value) {
            addCriterion("assetnum not like", value, "assetnum");
            return (Criteria) this;
        }

        public Criteria andAssetnumIn(List<String> values) {
            addCriterion("assetnum in", values, "assetnum");
            return (Criteria) this;
        }

        public Criteria andAssetnumNotIn(List<String> values) {
            addCriterion("assetnum not in", values, "assetnum");
            return (Criteria) this;
        }

        public Criteria andAssetnumBetween(String value1, String value2) {
            addCriterion("assetnum between", value1, value2, "assetnum");
            return (Criteria) this;
        }

        public Criteria andAssetnumNotBetween(String value1, String value2) {
            addCriterion("assetnum not between", value1, value2, "assetnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumIsNull() {
            addCriterion("serialnum is null");
            return (Criteria) this;
        }

        public Criteria andSerialnumIsNotNull() {
            addCriterion("serialnum is not null");
            return (Criteria) this;
        }

        public Criteria andSerialnumEqualTo(String value) {
            addCriterion("serialnum =", value, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumNotEqualTo(String value) {
            addCriterion("serialnum <>", value, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumGreaterThan(String value) {
            addCriterion("serialnum >", value, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumGreaterThanOrEqualTo(String value) {
            addCriterion("serialnum >=", value, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumLessThan(String value) {
            addCriterion("serialnum <", value, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumLessThanOrEqualTo(String value) {
            addCriterion("serialnum <=", value, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumLike(String value) {
            addCriterion("serialnum like", value, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumNotLike(String value) {
            addCriterion("serialnum not like", value, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumIn(List<String> values) {
            addCriterion("serialnum in", values, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumNotIn(List<String> values) {
            addCriterion("serialnum not in", values, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumBetween(String value1, String value2) {
            addCriterion("serialnum between", value1, value2, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSerialnumNotBetween(String value1, String value2) {
            addCriterion("serialnum not between", value1, value2, "serialnum");
            return (Criteria) this;
        }

        public Criteria andSupplierIsNull() {
            addCriterion("supplier is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIsNotNull() {
            addCriterion("supplier is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierEqualTo(Long value) {
            addCriterion("supplier =", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotEqualTo(Long value) {
            addCriterion("supplier <>", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierGreaterThan(Long value) {
            addCriterion("supplier >", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierGreaterThanOrEqualTo(Long value) {
            addCriterion("supplier >=", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLessThan(Long value) {
            addCriterion("supplier <", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierLessThanOrEqualTo(Long value) {
            addCriterion("supplier <=", value, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierIn(List<Long> values) {
            addCriterion("supplier in", values, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotIn(List<Long> values) {
            addCriterion("supplier not in", values, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierBetween(Long value1, Long value2) {
            addCriterion("supplier between", value1, value2, "supplier");
            return (Criteria) this;
        }

        public Criteria andSupplierNotBetween(Long value1, Long value2) {
            addCriterion("supplier not between", value1, value2, "supplier");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(Long value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(Long value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(Long value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(Long value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(Long value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(Long value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<Long> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<Long> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(Long value1, Long value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(Long value1, Long value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatorIsNull() {
            addCriterion("updator is null");
            return (Criteria) this;
        }

        public Criteria andUpdatorIsNotNull() {
            addCriterion("updator is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatorEqualTo(Long value) {
            addCriterion("updator =", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorNotEqualTo(Long value) {
            addCriterion("updator <>", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorGreaterThan(Long value) {
            addCriterion("updator >", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorGreaterThanOrEqualTo(Long value) {
            addCriterion("updator >=", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorLessThan(Long value) {
            addCriterion("updator <", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorLessThanOrEqualTo(Long value) {
            addCriterion("updator <=", value, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorIn(List<Long> values) {
            addCriterion("updator in", values, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorNotIn(List<Long> values) {
            addCriterion("updator not in", values, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorBetween(Long value1, Long value2) {
            addCriterion("updator between", value1, value2, "updator");
            return (Criteria) this;
        }

        public Criteria andUpdatorNotBetween(Long value1, Long value2) {
            addCriterion("updator not between", value1, value2, "updator");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNull() {
            addCriterion("tenant_id is null");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNotNull() {
            addCriterion("tenant_id is not null");
            return (Criteria) this;
        }

        public Criteria andTenantIdEqualTo(Long value) {
            addCriterion("tenant_id =", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotEqualTo(Long value) {
            addCriterion("tenant_id <>", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThan(Long value) {
            addCriterion("tenant_id >", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThanOrEqualTo(Long value) {
            addCriterion("tenant_id >=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThan(Long value) {
            addCriterion("tenant_id <", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThanOrEqualTo(Long value) {
            addCriterion("tenant_id <=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIn(List<Long> values) {
            addCriterion("tenant_id in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotIn(List<Long> values) {
            addCriterion("tenant_id not in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdBetween(Long value1, Long value2) {
            addCriterion("tenant_id between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotBetween(Long value1, Long value2) {
            addCriterion("tenant_id not between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNull() {
            addCriterion("delete_Flag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIsNotNull() {
            addCriterion("delete_Flag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagEqualTo(String value) {
            addCriterion("delete_Flag =", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotEqualTo(String value) {
            addCriterion("delete_Flag <>", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThan(String value) {
            addCriterion("delete_Flag >", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagGreaterThanOrEqualTo(String value) {
            addCriterion("delete_Flag >=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThan(String value) {
            addCriterion("delete_Flag <", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLessThanOrEqualTo(String value) {
            addCriterion("delete_Flag <=", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagLike(String value) {
            addCriterion("delete_Flag like", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotLike(String value) {
            addCriterion("delete_Flag not like", value, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagIn(List<String> values) {
            addCriterion("delete_Flag in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotIn(List<String> values) {
            addCriterion("delete_Flag not in", values, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagBetween(String value1, String value2) {
            addCriterion("delete_Flag between", value1, value2, "deleteFlag");
            return (Criteria) this;
        }

        public Criteria andDeleteFlagNotBetween(String value1, String value2) {
            addCriterion("delete_Flag not between", value1, value2, "deleteFlag");
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