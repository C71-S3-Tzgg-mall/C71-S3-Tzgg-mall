package com.yc.C71S3Tzggmall.biz;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.C71S3Tzggmall.bean.ClothExample;
import com.yc.C71S3Tzggmall.bean.Wishlist;
import com.yc.C71S3Tzggmall.bean.WishlistExample;
import com.yc.C71S3Tzggmall.dao.WishlistMapper;

@Service
public class WishlistBiz {
	
	@Resource
	private WishlistMapper wm;
	
	
	//加入收藏夹
	public int addWishlist(Wishlist wl){
		Date d=new Date();      
		Timestamp t = new Timestamp(d.getTime());
		wl.setTime(t);
		return wm.insert(wl);
	}
	
	
	//从收藏夹中删除
	public int deleteWishlist(Wishlist wl){
		WishlistExample example=new WishlistExample();
		example.createCriteria().andCidEqualTo(wl.getCid());
		return wm.deleteByExample(example);
	}
}
