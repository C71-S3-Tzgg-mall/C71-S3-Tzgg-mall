package com.yc.C71S3Tzggmall.dao;

import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.ClothExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClothMapper {
    long countByExample(ClothExample example);

    int deleteByExample(ClothExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(Cloth record);

    int insertSelective(Cloth record);

    List<Cloth> selectByExample(ClothExample example);

    Cloth selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") Cloth record, @Param("example") ClothExample example);

    int updateByExample(@Param("record") Cloth record, @Param("example") ClothExample example);

    int updateByPrimaryKeySelective(Cloth record);

    int updateByPrimaryKey(Cloth record);
}