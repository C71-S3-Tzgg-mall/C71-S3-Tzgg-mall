package com.yc.C71S3Tzggmall.dao;

import com.yc.C71S3Tzggmall.bean.Wishlist;
import com.yc.C71S3Tzggmall.bean.WishlistExample;
import com.yc.C71S3Tzggmall.bean.WishlistKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WishlistMapper {
    long countByExample(WishlistExample example);

    int deleteByExample(WishlistExample example);

    int deleteByPrimaryKey(WishlistKey key);

    int insert(Wishlist record);

    int insertSelective(Wishlist record);

    List<Wishlist> selectByExample(WishlistExample example);

    Wishlist selectByPrimaryKey(WishlistKey key);

    int updateByExampleSelective(@Param("record") Wishlist record, @Param("example") WishlistExample example);

    int updateByExample(@Param("record") Wishlist record, @Param("example") WishlistExample example);

    int updateByPrimaryKeySelective(Wishlist record);

    int updateByPrimaryKey(Wishlist record);
}