package com.seamwhole.serviceuser.model;

import java.util.ArrayList;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andDomainIdIsNull() {
            addCriterion("domain_id is null");
            return (Criteria) this;
        }

        public Criteria andDomainIdIsNotNull() {
            addCriterion("domain_id is not null");
            return (Criteria) this;
        }

        public Criteria andDomainIdEqualTo(String value) {
            addCriterion("domain_id =", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdNotEqualTo(String value) {
            addCriterion("domain_id <>", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdGreaterThan(String value) {
            addCriterion("domain_id >", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdGreaterThanOrEqualTo(String value) {
            addCriterion("domain_id >=", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdLessThan(String value) {
            addCriterion("domain_id <", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdLessThanOrEqualTo(String value) {
            addCriterion("domain_id <=", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdLike(String value) {
            addCriterion("domain_id like", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdNotLike(String value) {
            addCriterion("domain_id not like", value, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdIn(List<String> values) {
            addCriterion("domain_id in", values, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdNotIn(List<String> values) {
            addCriterion("domain_id not in", values, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdBetween(String value1, String value2) {
            addCriterion("domain_id between", value1, value2, "domainId");
            return (Criteria) this;
        }

        public Criteria andDomainIdNotBetween(String value1, String value2) {
            addCriterion("domain_id not between", value1, value2, "domainId");
            return (Criteria) this;
        }

        public Criteria andUserSerialIsNull() {
            addCriterion("user_serial is null");
            return (Criteria) this;
        }

        public Criteria andUserSerialIsNotNull() {
            addCriterion("user_serial is not null");
            return (Criteria) this;
        }

        public Criteria andUserSerialEqualTo(Integer value) {
            addCriterion("user_serial =", value, "userSerial");
            return (Criteria) this;
        }

        public Criteria andUserSerialNotEqualTo(Integer value) {
            addCriterion("user_serial <>", value, "userSerial");
            return (Criteria) this;
        }

        public Criteria andUserSerialGreaterThan(Integer value) {
            addCriterion("user_serial >", value, "userSerial");
            return (Criteria) this;
        }

        public Criteria andUserSerialGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_serial >=", value, "userSerial");
            return (Criteria) this;
        }

        public Criteria andUserSerialLessThan(Integer value) {
            addCriterion("user_serial <", value, "userSerial");
            return (Criteria) this;
        }

        public Criteria andUserSerialLessThanOrEqualTo(Integer value) {
            addCriterion("user_serial <=", value, "userSerial");
            return (Criteria) this;
        }

        public Criteria andUserSerialIn(List<Integer> values) {
            addCriterion("user_serial in", values, "userSerial");
            return (Criteria) this;
        }

        public Criteria andUserSerialNotIn(List<Integer> values) {
            addCriterion("user_serial not in", values, "userSerial");
            return (Criteria) this;
        }

        public Criteria andUserSerialBetween(Integer value1, Integer value2) {
            addCriterion("user_serial between", value1, value2, "userSerial");
            return (Criteria) this;
        }

        public Criteria andUserSerialNotBetween(Integer value1, Integer value2) {
            addCriterion("user_serial not between", value1, value2, "userSerial");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password_ is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password_ is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password_ =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password_ <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password_ >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password_ >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password_ <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password_ <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password_ like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password_ not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password_ in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password_ not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password_ between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password_ not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level_ is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level_ is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level_ =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level_ <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level_ >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level_ >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level_ <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level_ <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level_ in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level_ not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level_ between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level_ not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andNickNameIsNull() {
            addCriterion("nick_name is null");
            return (Criteria) this;
        }

        public Criteria andNickNameIsNotNull() {
            addCriterion("nick_name is not null");
            return (Criteria) this;
        }

        public Criteria andNickNameEqualTo(String value) {
            addCriterion("nick_name =", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotEqualTo(String value) {
            addCriterion("nick_name <>", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThan(String value) {
            addCriterion("nick_name >", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameGreaterThanOrEqualTo(String value) {
            addCriterion("nick_name >=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThan(String value) {
            addCriterion("nick_name <", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLessThanOrEqualTo(String value) {
            addCriterion("nick_name <=", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameLike(String value) {
            addCriterion("nick_name like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotLike(String value) {
            addCriterion("nick_name not like", value, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameIn(List<String> values) {
            addCriterion("nick_name in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotIn(List<String> values) {
            addCriterion("nick_name not in", values, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameBetween(String value1, String value2) {
            addCriterion("nick_name between", value1, value2, "nickName");
            return (Criteria) this;
        }

        public Criteria andNickNameNotBetween(String value1, String value2) {
            addCriterion("nick_name not between", value1, value2, "nickName");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNull() {
            addCriterion("phone_number is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIsNotNull() {
            addCriterion("phone_number is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberEqualTo(String value) {
            addCriterion("phone_number =", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotEqualTo(String value) {
            addCriterion("phone_number <>", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThan(String value) {
            addCriterion("phone_number >", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberGreaterThanOrEqualTo(String value) {
            addCriterion("phone_number >=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThan(String value) {
            addCriterion("phone_number <", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLessThanOrEqualTo(String value) {
            addCriterion("phone_number <=", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberLike(String value) {
            addCriterion("phone_number like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotLike(String value) {
            addCriterion("phone_number not like", value, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberIn(List<String> values) {
            addCriterion("phone_number in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotIn(List<String> values) {
            addCriterion("phone_number not in", values, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberBetween(String value1, String value2) {
            addCriterion("phone_number between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneNumberNotBetween(String value1, String value2) {
            addCriterion("phone_number not between", value1, value2, "phoneNumber");
            return (Criteria) this;
        }

        public Criteria andWeChatIsNull() {
            addCriterion("we_chat is null");
            return (Criteria) this;
        }

        public Criteria andWeChatIsNotNull() {
            addCriterion("we_chat is not null");
            return (Criteria) this;
        }

        public Criteria andWeChatEqualTo(String value) {
            addCriterion("we_chat =", value, "weChat");
            return (Criteria) this;
        }

        public Criteria andWeChatNotEqualTo(String value) {
            addCriterion("we_chat <>", value, "weChat");
            return (Criteria) this;
        }

        public Criteria andWeChatGreaterThan(String value) {
            addCriterion("we_chat >", value, "weChat");
            return (Criteria) this;
        }

        public Criteria andWeChatGreaterThanOrEqualTo(String value) {
            addCriterion("we_chat >=", value, "weChat");
            return (Criteria) this;
        }

        public Criteria andWeChatLessThan(String value) {
            addCriterion("we_chat <", value, "weChat");
            return (Criteria) this;
        }

        public Criteria andWeChatLessThanOrEqualTo(String value) {
            addCriterion("we_chat <=", value, "weChat");
            return (Criteria) this;
        }

        public Criteria andWeChatLike(String value) {
            addCriterion("we_chat like", value, "weChat");
            return (Criteria) this;
        }

        public Criteria andWeChatNotLike(String value) {
            addCriterion("we_chat not like", value, "weChat");
            return (Criteria) this;
        }

        public Criteria andWeChatIn(List<String> values) {
            addCriterion("we_chat in", values, "weChat");
            return (Criteria) this;
        }

        public Criteria andWeChatNotIn(List<String> values) {
            addCriterion("we_chat not in", values, "weChat");
            return (Criteria) this;
        }

        public Criteria andWeChatBetween(String value1, String value2) {
            addCriterion("we_chat between", value1, value2, "weChat");
            return (Criteria) this;
        }

        public Criteria andWeChatNotBetween(String value1, String value2) {
            addCriterion("we_chat not between", value1, value2, "weChat");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Integer value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Integer value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Integer value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Integer value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Integer value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Integer value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Integer> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Integer> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Integer value1, Integer value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Integer value1, Integer value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andHeaderIsNull() {
            addCriterion("header is null");
            return (Criteria) this;
        }

        public Criteria andHeaderIsNotNull() {
            addCriterion("header is not null");
            return (Criteria) this;
        }

        public Criteria andHeaderEqualTo(String value) {
            addCriterion("header =", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotEqualTo(String value) {
            addCriterion("header <>", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderGreaterThan(String value) {
            addCriterion("header >", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderGreaterThanOrEqualTo(String value) {
            addCriterion("header >=", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderLessThan(String value) {
            addCriterion("header <", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderLessThanOrEqualTo(String value) {
            addCriterion("header <=", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderLike(String value) {
            addCriterion("header like", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotLike(String value) {
            addCriterion("header not like", value, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderIn(List<String> values) {
            addCriterion("header in", values, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotIn(List<String> values) {
            addCriterion("header not in", values, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderBetween(String value1, String value2) {
            addCriterion("header between", value1, value2, "header");
            return (Criteria) this;
        }

        public Criteria andHeaderNotBetween(String value1, String value2) {
            addCriterion("header not between", value1, value2, "header");
            return (Criteria) this;
        }

        public Criteria andQqIsNull() {
            addCriterion("qq is null");
            return (Criteria) this;
        }

        public Criteria andQqIsNotNull() {
            addCriterion("qq is not null");
            return (Criteria) this;
        }

        public Criteria andQqEqualTo(String value) {
            addCriterion("qq =", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotEqualTo(String value) {
            addCriterion("qq <>", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThan(String value) {
            addCriterion("qq >", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThanOrEqualTo(String value) {
            addCriterion("qq >=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThan(String value) {
            addCriterion("qq <", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThanOrEqualTo(String value) {
            addCriterion("qq <=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLike(String value) {
            addCriterion("qq like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotLike(String value) {
            addCriterion("qq not like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqIn(List<String> values) {
            addCriterion("qq in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotIn(List<String> values) {
            addCriterion("qq not in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqBetween(String value1, String value2) {
            addCriterion("qq between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotBetween(String value1, String value2) {
            addCriterion("qq not between", value1, value2, "qq");
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