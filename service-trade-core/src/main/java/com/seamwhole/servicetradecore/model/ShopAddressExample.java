package com.seamwhole.servicetradecore.model;

import java.util.ArrayList;
import java.util.List;

public class ShopAddressExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopAddressExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andTelNumberIsNull() {
            addCriterion("tel_number is null");
            return (Criteria) this;
        }

        public Criteria andTelNumberIsNotNull() {
            addCriterion("tel_number is not null");
            return (Criteria) this;
        }

        public Criteria andTelNumberEqualTo(String value) {
            addCriterion("tel_number =", value, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberNotEqualTo(String value) {
            addCriterion("tel_number <>", value, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberGreaterThan(String value) {
            addCriterion("tel_number >", value, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberGreaterThanOrEqualTo(String value) {
            addCriterion("tel_number >=", value, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberLessThan(String value) {
            addCriterion("tel_number <", value, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberLessThanOrEqualTo(String value) {
            addCriterion("tel_number <=", value, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberLike(String value) {
            addCriterion("tel_number like", value, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberNotLike(String value) {
            addCriterion("tel_number not like", value, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberIn(List<String> values) {
            addCriterion("tel_number in", values, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberNotIn(List<String> values) {
            addCriterion("tel_number not in", values, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberBetween(String value1, String value2) {
            addCriterion("tel_number between", value1, value2, "telNumber");
            return (Criteria) this;
        }

        public Criteria andTelNumberNotBetween(String value1, String value2) {
            addCriterion("tel_number not between", value1, value2, "telNumber");
            return (Criteria) this;
        }

        public Criteria andPostalCodeIsNull() {
            addCriterion("postal_Code is null");
            return (Criteria) this;
        }

        public Criteria andPostalCodeIsNotNull() {
            addCriterion("postal_Code is not null");
            return (Criteria) this;
        }

        public Criteria andPostalCodeEqualTo(String value) {
            addCriterion("postal_Code =", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeNotEqualTo(String value) {
            addCriterion("postal_Code <>", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeGreaterThan(String value) {
            addCriterion("postal_Code >", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeGreaterThanOrEqualTo(String value) {
            addCriterion("postal_Code >=", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeLessThan(String value) {
            addCriterion("postal_Code <", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeLessThanOrEqualTo(String value) {
            addCriterion("postal_Code <=", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeLike(String value) {
            addCriterion("postal_Code like", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeNotLike(String value) {
            addCriterion("postal_Code not like", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeIn(List<String> values) {
            addCriterion("postal_Code in", values, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeNotIn(List<String> values) {
            addCriterion("postal_Code not in", values, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeBetween(String value1, String value2) {
            addCriterion("postal_Code between", value1, value2, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeNotBetween(String value1, String value2) {
            addCriterion("postal_Code not between", value1, value2, "postalCode");
            return (Criteria) this;
        }

        public Criteria andNationalCodeIsNull() {
            addCriterion("national_Code is null");
            return (Criteria) this;
        }

        public Criteria andNationalCodeIsNotNull() {
            addCriterion("national_Code is not null");
            return (Criteria) this;
        }

        public Criteria andNationalCodeEqualTo(String value) {
            addCriterion("national_Code =", value, "nationalCode");
            return (Criteria) this;
        }

        public Criteria andNationalCodeNotEqualTo(String value) {
            addCriterion("national_Code <>", value, "nationalCode");
            return (Criteria) this;
        }

        public Criteria andNationalCodeGreaterThan(String value) {
            addCriterion("national_Code >", value, "nationalCode");
            return (Criteria) this;
        }

        public Criteria andNationalCodeGreaterThanOrEqualTo(String value) {
            addCriterion("national_Code >=", value, "nationalCode");
            return (Criteria) this;
        }

        public Criteria andNationalCodeLessThan(String value) {
            addCriterion("national_Code <", value, "nationalCode");
            return (Criteria) this;
        }

        public Criteria andNationalCodeLessThanOrEqualTo(String value) {
            addCriterion("national_Code <=", value, "nationalCode");
            return (Criteria) this;
        }

        public Criteria andNationalCodeLike(String value) {
            addCriterion("national_Code like", value, "nationalCode");
            return (Criteria) this;
        }

        public Criteria andNationalCodeNotLike(String value) {
            addCriterion("national_Code not like", value, "nationalCode");
            return (Criteria) this;
        }

        public Criteria andNationalCodeIn(List<String> values) {
            addCriterion("national_Code in", values, "nationalCode");
            return (Criteria) this;
        }

        public Criteria andNationalCodeNotIn(List<String> values) {
            addCriterion("national_Code not in", values, "nationalCode");
            return (Criteria) this;
        }

        public Criteria andNationalCodeBetween(String value1, String value2) {
            addCriterion("national_Code between", value1, value2, "nationalCode");
            return (Criteria) this;
        }

        public Criteria andNationalCodeNotBetween(String value1, String value2) {
            addCriterion("national_Code not between", value1, value2, "nationalCode");
            return (Criteria) this;
        }

        public Criteria andProvinceNameIsNull() {
            addCriterion("province_Name is null");
            return (Criteria) this;
        }

        public Criteria andProvinceNameIsNotNull() {
            addCriterion("province_Name is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceNameEqualTo(String value) {
            addCriterion("province_Name =", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotEqualTo(String value) {
            addCriterion("province_Name <>", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameGreaterThan(String value) {
            addCriterion("province_Name >", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameGreaterThanOrEqualTo(String value) {
            addCriterion("province_Name >=", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameLessThan(String value) {
            addCriterion("province_Name <", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameLessThanOrEqualTo(String value) {
            addCriterion("province_Name <=", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameLike(String value) {
            addCriterion("province_Name like", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotLike(String value) {
            addCriterion("province_Name not like", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameIn(List<String> values) {
            addCriterion("province_Name in", values, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotIn(List<String> values) {
            addCriterion("province_Name not in", values, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameBetween(String value1, String value2) {
            addCriterion("province_Name between", value1, value2, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotBetween(String value1, String value2) {
            addCriterion("province_Name not between", value1, value2, "provinceName");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNull() {
            addCriterion("city_Name is null");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNotNull() {
            addCriterion("city_Name is not null");
            return (Criteria) this;
        }

        public Criteria andCityNameEqualTo(String value) {
            addCriterion("city_Name =", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotEqualTo(String value) {
            addCriterion("city_Name <>", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThan(String value) {
            addCriterion("city_Name >", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("city_Name >=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThan(String value) {
            addCriterion("city_Name <", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThanOrEqualTo(String value) {
            addCriterion("city_Name <=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLike(String value) {
            addCriterion("city_Name like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotLike(String value) {
            addCriterion("city_Name not like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameIn(List<String> values) {
            addCriterion("city_Name in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotIn(List<String> values) {
            addCriterion("city_Name not in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameBetween(String value1, String value2) {
            addCriterion("city_Name between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotBetween(String value1, String value2) {
            addCriterion("city_Name not between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andCountyNameIsNull() {
            addCriterion("county_Name is null");
            return (Criteria) this;
        }

        public Criteria andCountyNameIsNotNull() {
            addCriterion("county_Name is not null");
            return (Criteria) this;
        }

        public Criteria andCountyNameEqualTo(String value) {
            addCriterion("county_Name =", value, "countyName");
            return (Criteria) this;
        }

        public Criteria andCountyNameNotEqualTo(String value) {
            addCriterion("county_Name <>", value, "countyName");
            return (Criteria) this;
        }

        public Criteria andCountyNameGreaterThan(String value) {
            addCriterion("county_Name >", value, "countyName");
            return (Criteria) this;
        }

        public Criteria andCountyNameGreaterThanOrEqualTo(String value) {
            addCriterion("county_Name >=", value, "countyName");
            return (Criteria) this;
        }

        public Criteria andCountyNameLessThan(String value) {
            addCriterion("county_Name <", value, "countyName");
            return (Criteria) this;
        }

        public Criteria andCountyNameLessThanOrEqualTo(String value) {
            addCriterion("county_Name <=", value, "countyName");
            return (Criteria) this;
        }

        public Criteria andCountyNameLike(String value) {
            addCriterion("county_Name like", value, "countyName");
            return (Criteria) this;
        }

        public Criteria andCountyNameNotLike(String value) {
            addCriterion("county_Name not like", value, "countyName");
            return (Criteria) this;
        }

        public Criteria andCountyNameIn(List<String> values) {
            addCriterion("county_Name in", values, "countyName");
            return (Criteria) this;
        }

        public Criteria andCountyNameNotIn(List<String> values) {
            addCriterion("county_Name not in", values, "countyName");
            return (Criteria) this;
        }

        public Criteria andCountyNameBetween(String value1, String value2) {
            addCriterion("county_Name between", value1, value2, "countyName");
            return (Criteria) this;
        }

        public Criteria andCountyNameNotBetween(String value1, String value2) {
            addCriterion("county_Name not between", value1, value2, "countyName");
            return (Criteria) this;
        }

        public Criteria andDetailInfoIsNull() {
            addCriterion("detail_Info is null");
            return (Criteria) this;
        }

        public Criteria andDetailInfoIsNotNull() {
            addCriterion("detail_Info is not null");
            return (Criteria) this;
        }

        public Criteria andDetailInfoEqualTo(String value) {
            addCriterion("detail_Info =", value, "detailInfo");
            return (Criteria) this;
        }

        public Criteria andDetailInfoNotEqualTo(String value) {
            addCriterion("detail_Info <>", value, "detailInfo");
            return (Criteria) this;
        }

        public Criteria andDetailInfoGreaterThan(String value) {
            addCriterion("detail_Info >", value, "detailInfo");
            return (Criteria) this;
        }

        public Criteria andDetailInfoGreaterThanOrEqualTo(String value) {
            addCriterion("detail_Info >=", value, "detailInfo");
            return (Criteria) this;
        }

        public Criteria andDetailInfoLessThan(String value) {
            addCriterion("detail_Info <", value, "detailInfo");
            return (Criteria) this;
        }

        public Criteria andDetailInfoLessThanOrEqualTo(String value) {
            addCriterion("detail_Info <=", value, "detailInfo");
            return (Criteria) this;
        }

        public Criteria andDetailInfoLike(String value) {
            addCriterion("detail_Info like", value, "detailInfo");
            return (Criteria) this;
        }

        public Criteria andDetailInfoNotLike(String value) {
            addCriterion("detail_Info not like", value, "detailInfo");
            return (Criteria) this;
        }

        public Criteria andDetailInfoIn(List<String> values) {
            addCriterion("detail_Info in", values, "detailInfo");
            return (Criteria) this;
        }

        public Criteria andDetailInfoNotIn(List<String> values) {
            addCriterion("detail_Info not in", values, "detailInfo");
            return (Criteria) this;
        }

        public Criteria andDetailInfoBetween(String value1, String value2) {
            addCriterion("detail_Info between", value1, value2, "detailInfo");
            return (Criteria) this;
        }

        public Criteria andDetailInfoNotBetween(String value1, String value2) {
            addCriterion("detail_Info not between", value1, value2, "detailInfo");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIsNull() {
            addCriterion("is_default is null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIsNotNull() {
            addCriterion("is_default is not null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultEqualTo(Integer value) {
            addCriterion("is_default =", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotEqualTo(Integer value) {
            addCriterion("is_default <>", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThan(Integer value) {
            addCriterion("is_default >", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_default >=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThan(Integer value) {
            addCriterion("is_default <", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThanOrEqualTo(Integer value) {
            addCriterion("is_default <=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIn(List<Integer> values) {
            addCriterion("is_default in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotIn(List<Integer> values) {
            addCriterion("is_default not in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultBetween(Integer value1, Integer value2) {
            addCriterion("is_default between", value1, value2, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotBetween(Integer value1, Integer value2) {
            addCriterion("is_default not between", value1, value2, "isDefault");
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