package com.yc.C71S3Tzggmall.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ClothExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClothExample() {
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

        public Criteria andCidIsNull() {
            addCriterion("cid is null");
            return (Criteria) this;
        }

        public Criteria andCidIsNotNull() {
            addCriterion("cid is not null");
            return (Criteria) this;
        }

        public Criteria andCidEqualTo(Integer value) {
            addCriterion("cid =", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotEqualTo(Integer value) {
            addCriterion("cid <>", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThan(Integer value) {
            addCriterion("cid >", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cid >=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThan(Integer value) {
            addCriterion("cid <", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThanOrEqualTo(Integer value) {
            addCriterion("cid <=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidIn(List<Integer> values) {
            addCriterion("cid in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotIn(List<Integer> values) {
            addCriterion("cid not in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidBetween(Integer value1, Integer value2) {
            addCriterion("cid between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotBetween(Integer value1, Integer value2) {
            addCriterion("cid not between", value1, value2, "cid");
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

        public Criteria andShoppriceIsNull() {
            addCriterion("shopPrice is null");
            return (Criteria) this;
        }

        public Criteria andShoppriceIsNotNull() {
            addCriterion("shopPrice is not null");
            return (Criteria) this;
        }

        public Criteria andShoppriceEqualTo(Double value) {
            addCriterion("shopPrice =", value, "shopprice");
            return (Criteria) this;
        }

        public Criteria andShoppriceNotEqualTo(Double value) {
            addCriterion("shopPrice <>", value, "shopprice");
            return (Criteria) this;
        }

        public Criteria andShoppriceGreaterThan(Double value) {
            addCriterion("shopPrice >", value, "shopprice");
            return (Criteria) this;
        }

        public Criteria andShoppriceGreaterThanOrEqualTo(Double value) {
            addCriterion("shopPrice >=", value, "shopprice");
            return (Criteria) this;
        }

        public Criteria andShoppriceLessThan(Double value) {
            addCriterion("shopPrice <", value, "shopprice");
            return (Criteria) this;
        }

        public Criteria andShoppriceLessThanOrEqualTo(Double value) {
            addCriterion("shopPrice <=", value, "shopprice");
            return (Criteria) this;
        }

        public Criteria andShoppriceIn(List<Double> values) {
            addCriterion("shopPrice in", values, "shopprice");
            return (Criteria) this;
        }

        public Criteria andShoppriceNotIn(List<Double> values) {
            addCriterion("shopPrice not in", values, "shopprice");
            return (Criteria) this;
        }

        public Criteria andShoppriceBetween(Double value1, Double value2) {
            addCriterion("shopPrice between", value1, value2, "shopprice");
            return (Criteria) this;
        }

        public Criteria andShoppriceNotBetween(Double value1, Double value2) {
            addCriterion("shopPrice not between", value1, value2, "shopprice");
            return (Criteria) this;
        }

        public Criteria andRealpriceIsNull() {
            addCriterion("realPrice is null");
            return (Criteria) this;
        }

        public Criteria andRealpriceIsNotNull() {
            addCriterion("realPrice is not null");
            return (Criteria) this;
        }

        public Criteria andRealpriceEqualTo(Double value) {
            addCriterion("realPrice =", value, "realprice");
            return (Criteria) this;
        }

        public Criteria andRealpriceNotEqualTo(Double value) {
            addCriterion("realPrice <>", value, "realprice");
            return (Criteria) this;
        }

        public Criteria andRealpriceGreaterThan(Double value) {
            addCriterion("realPrice >", value, "realprice");
            return (Criteria) this;
        }

        public Criteria andRealpriceGreaterThanOrEqualTo(Double value) {
            addCriterion("realPrice >=", value, "realprice");
            return (Criteria) this;
        }

        public Criteria andRealpriceLessThan(Double value) {
            addCriterion("realPrice <", value, "realprice");
            return (Criteria) this;
        }

        public Criteria andRealpriceLessThanOrEqualTo(Double value) {
            addCriterion("realPrice <=", value, "realprice");
            return (Criteria) this;
        }

        public Criteria andRealpriceIn(List<Double> values) {
            addCriterion("realPrice in", values, "realprice");
            return (Criteria) this;
        }

        public Criteria andRealpriceNotIn(List<Double> values) {
            addCriterion("realPrice not in", values, "realprice");
            return (Criteria) this;
        }

        public Criteria andRealpriceBetween(Double value1, Double value2) {
            addCriterion("realPrice between", value1, value2, "realprice");
            return (Criteria) this;
        }

        public Criteria andRealpriceNotBetween(Double value1, Double value2) {
            addCriterion("realPrice not between", value1, value2, "realprice");
            return (Criteria) this;
        }

        public Criteria andImageIsNull() {
            addCriterion("image is null");
            return (Criteria) this;
        }

        public Criteria andImageIsNotNull() {
            addCriterion("image is not null");
            return (Criteria) this;
        }

        public Criteria andImageEqualTo(String value) {
            addCriterion("image =", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotEqualTo(String value) {
            addCriterion("image <>", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThan(String value) {
            addCriterion("image >", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThanOrEqualTo(String value) {
            addCriterion("image >=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThan(String value) {
            addCriterion("image <", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThanOrEqualTo(String value) {
            addCriterion("image <=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLike(String value) {
            addCriterion("image like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotLike(String value) {
            addCriterion("image not like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageIn(List<String> values) {
            addCriterion("image in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotIn(List<String> values) {
            addCriterion("image not in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageBetween(String value1, String value2) {
            addCriterion("image between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotBetween(String value1, String value2) {
            addCriterion("image not between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andTidIsNull() {
            addCriterion("tid is null");
            return (Criteria) this;
        }

        public Criteria andTidIsNotNull() {
            addCriterion("tid is not null");
            return (Criteria) this;
        }

        public Criteria andTidEqualTo(Integer value) {
            addCriterion("tid =", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotEqualTo(Integer value) {
            addCriterion("tid <>", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThan(Integer value) {
            addCriterion("tid >", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThanOrEqualTo(Integer value) {
            addCriterion("tid >=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThan(Integer value) {
            addCriterion("tid <", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThanOrEqualTo(Integer value) {
            addCriterion("tid <=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidIn(List<Integer> values) {
            addCriterion("tid in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotIn(List<Integer> values) {
            addCriterion("tid not in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidBetween(Integer value1, Integer value2) {
            addCriterion("tid between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotBetween(Integer value1, Integer value2) {
            addCriterion("tid not between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andRestcountIsNull() {
            addCriterion("restCount is null");
            return (Criteria) this;
        }

        public Criteria andRestcountIsNotNull() {
            addCriterion("restCount is not null");
            return (Criteria) this;
        }

        public Criteria andRestcountEqualTo(Integer value) {
            addCriterion("restCount =", value, "restcount");
            return (Criteria) this;
        }

        public Criteria andRestcountNotEqualTo(Integer value) {
            addCriterion("restCount <>", value, "restcount");
            return (Criteria) this;
        }

        public Criteria andRestcountGreaterThan(Integer value) {
            addCriterion("restCount >", value, "restcount");
            return (Criteria) this;
        }

        public Criteria andRestcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("restCount >=", value, "restcount");
            return (Criteria) this;
        }

        public Criteria andRestcountLessThan(Integer value) {
            addCriterion("restCount <", value, "restcount");
            return (Criteria) this;
        }

        public Criteria andRestcountLessThanOrEqualTo(Integer value) {
            addCriterion("restCount <=", value, "restcount");
            return (Criteria) this;
        }

        public Criteria andRestcountIn(List<Integer> values) {
            addCriterion("restCount in", values, "restcount");
            return (Criteria) this;
        }

        public Criteria andRestcountNotIn(List<Integer> values) {
            addCriterion("restCount not in", values, "restcount");
            return (Criteria) this;
        }

        public Criteria andRestcountBetween(Integer value1, Integer value2) {
            addCriterion("restCount between", value1, value2, "restcount");
            return (Criteria) this;
        }

        public Criteria andRestcountNotBetween(Integer value1, Integer value2) {
            addCriterion("restCount not between", value1, value2, "restcount");
            return (Criteria) this;
        }

        public Criteria andSolecountIsNull() {
            addCriterion("soleCount is null");
            return (Criteria) this;
        }

        public Criteria andSolecountIsNotNull() {
            addCriterion("soleCount is not null");
            return (Criteria) this;
        }

        public Criteria andSolecountEqualTo(Integer value) {
            addCriterion("soleCount =", value, "solecount");
            return (Criteria) this;
        }

        public Criteria andSolecountNotEqualTo(Integer value) {
            addCriterion("soleCount <>", value, "solecount");
            return (Criteria) this;
        }

        public Criteria andSolecountGreaterThan(Integer value) {
            addCriterion("soleCount >", value, "solecount");
            return (Criteria) this;
        }

        public Criteria andSolecountGreaterThanOrEqualTo(Integer value) {
            addCriterion("soleCount >=", value, "solecount");
            return (Criteria) this;
        }

        public Criteria andSolecountLessThan(Integer value) {
            addCriterion("soleCount <", value, "solecount");
            return (Criteria) this;
        }

        public Criteria andSolecountLessThanOrEqualTo(Integer value) {
            addCriterion("soleCount <=", value, "solecount");
            return (Criteria) this;
        }

        public Criteria andSolecountIn(List<Integer> values) {
            addCriterion("soleCount in", values, "solecount");
            return (Criteria) this;
        }

        public Criteria andSolecountNotIn(List<Integer> values) {
            addCriterion("soleCount not in", values, "solecount");
            return (Criteria) this;
        }

        public Criteria andSolecountBetween(Integer value1, Integer value2) {
            addCriterion("soleCount between", value1, value2, "solecount");
            return (Criteria) this;
        }

        public Criteria andSolecountNotBetween(Integer value1, Integer value2) {
            addCriterion("soleCount not between", value1, value2, "solecount");
            return (Criteria) this;
        }

        public Criteria andArrivetimeIsNull() {
            addCriterion("arriveTime is null");
            return (Criteria) this;
        }

        public Criteria andArrivetimeIsNotNull() {
            addCriterion("arriveTime is not null");
            return (Criteria) this;
        }

        public Criteria andArrivetimeEqualTo(Timestamp value) {
            addCriterion("arriveTime =", value, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeNotEqualTo(Timestamp value) {
            addCriterion("arriveTime <>", value, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeGreaterThan(Timestamp value) {
            addCriterion("arriveTime >", value, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("arriveTime >=", value, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeLessThan(Timestamp value) {
            addCriterion("arriveTime <", value, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("arriveTime <=", value, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeIn(List<Timestamp> values) {
            addCriterion("arriveTime in", values, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeNotIn(List<Timestamp> values) {
            addCriterion("arriveTime not in", values, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("arriveTime between", value1, value2, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andArrivetimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("arriveTime not between", value1, value2, "arrivetime");
            return (Criteria) this;
        }

        public Criteria andDetailIsNull() {
            addCriterion("detail is null");
            return (Criteria) this;
        }

        public Criteria andDetailIsNotNull() {
            addCriterion("detail is not null");
            return (Criteria) this;
        }

        public Criteria andDetailEqualTo(String value) {
            addCriterion("detail =", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotEqualTo(String value) {
            addCriterion("detail <>", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThan(String value) {
            addCriterion("detail >", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThanOrEqualTo(String value) {
            addCriterion("detail >=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThan(String value) {
            addCriterion("detail <", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThanOrEqualTo(String value) {
            addCriterion("detail <=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLike(String value) {
            addCriterion("detail like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotLike(String value) {
            addCriterion("detail not like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailIn(List<String> values) {
            addCriterion("detail in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotIn(List<String> values) {
            addCriterion("detail not in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailBetween(String value1, String value2) {
            addCriterion("detail between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotBetween(String value1, String value2) {
            addCriterion("detail not between", value1, value2, "detail");
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