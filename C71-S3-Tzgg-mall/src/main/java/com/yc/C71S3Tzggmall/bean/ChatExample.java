package com.yc.C71S3Tzggmall.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ChatExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChatExample() {
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

        public Criteria andChatidIsNull() {
            addCriterion("chatid is null");
            return (Criteria) this;
        }

        public Criteria andChatidIsNotNull() {
            addCriterion("chatid is not null");
            return (Criteria) this;
        }

        public Criteria andChatidEqualTo(Integer value) {
            addCriterion("chatid =", value, "chatid");
            return (Criteria) this;
        }

        public Criteria andChatidNotEqualTo(Integer value) {
            addCriterion("chatid <>", value, "chatid");
            return (Criteria) this;
        }

        public Criteria andChatidGreaterThan(Integer value) {
            addCriterion("chatid >", value, "chatid");
            return (Criteria) this;
        }

        public Criteria andChatidGreaterThanOrEqualTo(Integer value) {
            addCriterion("chatid >=", value, "chatid");
            return (Criteria) this;
        }

        public Criteria andChatidLessThan(Integer value) {
            addCriterion("chatid <", value, "chatid");
            return (Criteria) this;
        }

        public Criteria andChatidLessThanOrEqualTo(Integer value) {
            addCriterion("chatid <=", value, "chatid");
            return (Criteria) this;
        }

        public Criteria andChatidIn(List<Integer> values) {
            addCriterion("chatid in", values, "chatid");
            return (Criteria) this;
        }

        public Criteria andChatidNotIn(List<Integer> values) {
            addCriterion("chatid not in", values, "chatid");
            return (Criteria) this;
        }

        public Criteria andChatidBetween(Integer value1, Integer value2) {
            addCriterion("chatid between", value1, value2, "chatid");
            return (Criteria) this;
        }

        public Criteria andChatidNotBetween(Integer value1, Integer value2) {
            addCriterion("chatid not between", value1, value2, "chatid");
            return (Criteria) this;
        }

        public Criteria andCfromIsNull() {
            addCriterion("cFrom is null");
            return (Criteria) this;
        }

        public Criteria andCfromIsNotNull() {
            addCriterion("cFrom is not null");
            return (Criteria) this;
        }

        public Criteria andCfromEqualTo(String value) {
            addCriterion("cFrom =", value, "cfrom");
            return (Criteria) this;
        }

        public Criteria andCfromNotEqualTo(String value) {
            addCriterion("cFrom <>", value, "cfrom");
            return (Criteria) this;
        }

        public Criteria andCfromGreaterThan(String value) {
            addCriterion("cFrom >", value, "cfrom");
            return (Criteria) this;
        }

        public Criteria andCfromGreaterThanOrEqualTo(String value) {
            addCriterion("cFrom >=", value, "cfrom");
            return (Criteria) this;
        }

        public Criteria andCfromLessThan(String value) {
            addCriterion("cFrom <", value, "cfrom");
            return (Criteria) this;
        }

        public Criteria andCfromLessThanOrEqualTo(String value) {
            addCriterion("cFrom <=", value, "cfrom");
            return (Criteria) this;
        }

        public Criteria andCfromLike(String value) {
            addCriterion("cFrom like", value, "cfrom");
            return (Criteria) this;
        }

        public Criteria andCfromNotLike(String value) {
            addCriterion("cFrom not like", value, "cfrom");
            return (Criteria) this;
        }

        public Criteria andCfromIn(List<String> values) {
            addCriterion("cFrom in", values, "cfrom");
            return (Criteria) this;
        }

        public Criteria andCfromNotIn(List<String> values) {
            addCriterion("cFrom not in", values, "cfrom");
            return (Criteria) this;
        }

        public Criteria andCfromBetween(String value1, String value2) {
            addCriterion("cFrom between", value1, value2, "cfrom");
            return (Criteria) this;
        }

        public Criteria andCfromNotBetween(String value1, String value2) {
            addCriterion("cFrom not between", value1, value2, "cfrom");
            return (Criteria) this;
        }

        public Criteria andCtoIsNull() {
            addCriterion("cTo is null");
            return (Criteria) this;
        }

        public Criteria andCtoIsNotNull() {
            addCriterion("cTo is not null");
            return (Criteria) this;
        }

        public Criteria andCtoEqualTo(String value) {
            addCriterion("cTo =", value, "cto");
            return (Criteria) this;
        }

        public Criteria andCtoNotEqualTo(String value) {
            addCriterion("cTo <>", value, "cto");
            return (Criteria) this;
        }

        public Criteria andCtoGreaterThan(String value) {
            addCriterion("cTo >", value, "cto");
            return (Criteria) this;
        }

        public Criteria andCtoGreaterThanOrEqualTo(String value) {
            addCriterion("cTo >=", value, "cto");
            return (Criteria) this;
        }

        public Criteria andCtoLessThan(String value) {
            addCriterion("cTo <", value, "cto");
            return (Criteria) this;
        }

        public Criteria andCtoLessThanOrEqualTo(String value) {
            addCriterion("cTo <=", value, "cto");
            return (Criteria) this;
        }

        public Criteria andCtoLike(String value) {
            addCriterion("cTo like", value, "cto");
            return (Criteria) this;
        }

        public Criteria andCtoNotLike(String value) {
            addCriterion("cTo not like", value, "cto");
            return (Criteria) this;
        }

        public Criteria andCtoIn(List<String> values) {
            addCriterion("cTo in", values, "cto");
            return (Criteria) this;
        }

        public Criteria andCtoNotIn(List<String> values) {
            addCriterion("cTo not in", values, "cto");
            return (Criteria) this;
        }

        public Criteria andCtoBetween(String value1, String value2) {
            addCriterion("cTo between", value1, value2, "cto");
            return (Criteria) this;
        }

        public Criteria andCtoNotBetween(String value1, String value2) {
            addCriterion("cTo not between", value1, value2, "cto");
            return (Criteria) this;
        }

        public Criteria andChatcontentIsNull() {
            addCriterion("chatContent is null");
            return (Criteria) this;
        }

        public Criteria andChatcontentIsNotNull() {
            addCriterion("chatContent is not null");
            return (Criteria) this;
        }

        public Criteria andChatcontentEqualTo(String value) {
            addCriterion("chatContent =", value, "chatcontent");
            return (Criteria) this;
        }

        public Criteria andChatcontentNotEqualTo(String value) {
            addCriterion("chatContent <>", value, "chatcontent");
            return (Criteria) this;
        }

        public Criteria andChatcontentGreaterThan(String value) {
            addCriterion("chatContent >", value, "chatcontent");
            return (Criteria) this;
        }

        public Criteria andChatcontentGreaterThanOrEqualTo(String value) {
            addCriterion("chatContent >=", value, "chatcontent");
            return (Criteria) this;
        }

        public Criteria andChatcontentLessThan(String value) {
            addCriterion("chatContent <", value, "chatcontent");
            return (Criteria) this;
        }

        public Criteria andChatcontentLessThanOrEqualTo(String value) {
            addCriterion("chatContent <=", value, "chatcontent");
            return (Criteria) this;
        }

        public Criteria andChatcontentLike(String value) {
            addCriterion("chatContent like", value, "chatcontent");
            return (Criteria) this;
        }

        public Criteria andChatcontentNotLike(String value) {
            addCriterion("chatContent not like", value, "chatcontent");
            return (Criteria) this;
        }

        public Criteria andChatcontentIn(List<String> values) {
            addCriterion("chatContent in", values, "chatcontent");
            return (Criteria) this;
        }

        public Criteria andChatcontentNotIn(List<String> values) {
            addCriterion("chatContent not in", values, "chatcontent");
            return (Criteria) this;
        }

        public Criteria andChatcontentBetween(String value1, String value2) {
            addCriterion("chatContent between", value1, value2, "chatcontent");
            return (Criteria) this;
        }

        public Criteria andChatcontentNotBetween(String value1, String value2) {
            addCriterion("chatContent not between", value1, value2, "chatcontent");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Timestamp value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Timestamp value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Timestamp value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Timestamp value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Timestamp> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Timestamp> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("time not between", value1, value2, "time");
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

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
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