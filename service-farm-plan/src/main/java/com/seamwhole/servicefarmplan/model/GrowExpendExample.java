package com.seamwhole.servicefarmplan.model;

import java.util.ArrayList;
import java.util.List;

public class GrowExpendExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GrowExpendExample() {
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

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Integer value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Integer value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Integer value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Integer value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Integer> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Integer> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Integer value1, Integer value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andDayCountIsNull() {
            addCriterion("day_count is null");
            return (Criteria) this;
        }

        public Criteria andDayCountIsNotNull() {
            addCriterion("day_count is not null");
            return (Criteria) this;
        }

        public Criteria andDayCountEqualTo(Integer value) {
            addCriterion("day_count =", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountNotEqualTo(Integer value) {
            addCriterion("day_count <>", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountGreaterThan(Integer value) {
            addCriterion("day_count >", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("day_count >=", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountLessThan(Integer value) {
            addCriterion("day_count <", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountLessThanOrEqualTo(Integer value) {
            addCriterion("day_count <=", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountIn(List<Integer> values) {
            addCriterion("day_count in", values, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountNotIn(List<Integer> values) {
            addCriterion("day_count not in", values, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountBetween(Integer value1, Integer value2) {
            addCriterion("day_count between", value1, value2, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountNotBetween(Integer value1, Integer value2) {
            addCriterion("day_count not between", value1, value2, "dayCount");
            return (Criteria) this;
        }

        public Criteria andExpendDetailIsNull() {
            addCriterion("expend_detail is null");
            return (Criteria) this;
        }

        public Criteria andExpendDetailIsNotNull() {
            addCriterion("expend_detail is not null");
            return (Criteria) this;
        }

        public Criteria andExpendDetailEqualTo(String value) {
            addCriterion("expend_detail =", value, "expendDetail");
            return (Criteria) this;
        }

        public Criteria andExpendDetailNotEqualTo(String value) {
            addCriterion("expend_detail <>", value, "expendDetail");
            return (Criteria) this;
        }

        public Criteria andExpendDetailGreaterThan(String value) {
            addCriterion("expend_detail >", value, "expendDetail");
            return (Criteria) this;
        }

        public Criteria andExpendDetailGreaterThanOrEqualTo(String value) {
            addCriterion("expend_detail >=", value, "expendDetail");
            return (Criteria) this;
        }

        public Criteria andExpendDetailLessThan(String value) {
            addCriterion("expend_detail <", value, "expendDetail");
            return (Criteria) this;
        }

        public Criteria andExpendDetailLessThanOrEqualTo(String value) {
            addCriterion("expend_detail <=", value, "expendDetail");
            return (Criteria) this;
        }

        public Criteria andExpendDetailLike(String value) {
            addCriterion("expend_detail like", value, "expendDetail");
            return (Criteria) this;
        }

        public Criteria andExpendDetailNotLike(String value) {
            addCriterion("expend_detail not like", value, "expendDetail");
            return (Criteria) this;
        }

        public Criteria andExpendDetailIn(List<String> values) {
            addCriterion("expend_detail in", values, "expendDetail");
            return (Criteria) this;
        }

        public Criteria andExpendDetailNotIn(List<String> values) {
            addCriterion("expend_detail not in", values, "expendDetail");
            return (Criteria) this;
        }

        public Criteria andExpendDetailBetween(String value1, String value2) {
            addCriterion("expend_detail between", value1, value2, "expendDetail");
            return (Criteria) this;
        }

        public Criteria andExpendDetailNotBetween(String value1, String value2) {
            addCriterion("expend_detail not between", value1, value2, "expendDetail");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight_ is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight_ is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(Float value) {
            addCriterion("weight_ =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Float value) {
            addCriterion("weight_ <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Float value) {
            addCriterion("weight_ >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("weight_ >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Float value) {
            addCriterion("weight_ <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Float value) {
            addCriterion("weight_ <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Float> values) {
            addCriterion("weight_ in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Float> values) {
            addCriterion("weight_ not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Float value1, Float value2) {
            addCriterion("weight_ between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Float value1, Float value2) {
            addCriterion("weight_ not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andAddWeightIsNull() {
            addCriterion("add_weight is null");
            return (Criteria) this;
        }

        public Criteria andAddWeightIsNotNull() {
            addCriterion("add_weight is not null");
            return (Criteria) this;
        }

        public Criteria andAddWeightEqualTo(Float value) {
            addCriterion("add_weight =", value, "addWeight");
            return (Criteria) this;
        }

        public Criteria andAddWeightNotEqualTo(Float value) {
            addCriterion("add_weight <>", value, "addWeight");
            return (Criteria) this;
        }

        public Criteria andAddWeightGreaterThan(Float value) {
            addCriterion("add_weight >", value, "addWeight");
            return (Criteria) this;
        }

        public Criteria andAddWeightGreaterThanOrEqualTo(Float value) {
            addCriterion("add_weight >=", value, "addWeight");
            return (Criteria) this;
        }

        public Criteria andAddWeightLessThan(Float value) {
            addCriterion("add_weight <", value, "addWeight");
            return (Criteria) this;
        }

        public Criteria andAddWeightLessThanOrEqualTo(Float value) {
            addCriterion("add_weight <=", value, "addWeight");
            return (Criteria) this;
        }

        public Criteria andAddWeightIn(List<Float> values) {
            addCriterion("add_weight in", values, "addWeight");
            return (Criteria) this;
        }

        public Criteria andAddWeightNotIn(List<Float> values) {
            addCriterion("add_weight not in", values, "addWeight");
            return (Criteria) this;
        }

        public Criteria andAddWeightBetween(Float value1, Float value2) {
            addCriterion("add_weight between", value1, value2, "addWeight");
            return (Criteria) this;
        }

        public Criteria andAddWeightNotBetween(Float value1, Float value2) {
            addCriterion("add_weight not between", value1, value2, "addWeight");
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