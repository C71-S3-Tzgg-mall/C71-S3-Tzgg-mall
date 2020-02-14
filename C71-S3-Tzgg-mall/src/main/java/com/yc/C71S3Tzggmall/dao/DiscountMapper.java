package com.yc.C71S3Tzggmall.dao;

import com.yc.C71S3Tzggmall.bean.Discount;
import com.yc.C71S3Tzggmall.bean.DiscountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DiscountMapper {
    long countByExample(DiscountExample example);

    int deleteByExample(DiscountExample example);

    int deleteByPrimaryKey(Integer did);

    int insert(Discount record);

    int insertSelective(Discount record);

    List<Discount> selectByExample(DiscountExample example);

    Discount selectByPrimaryKey(Integer did);

    int updateByExampleSelective(@Param("record") Discount record, @Param("example") DiscountExample example);

    int updateByExample(@Param("record") Discount record, @Param("example") DiscountExample example);

    int updateByPrimaryKeySelective(Discount record);

    int updateByPrimaryKey(Discount record);
}