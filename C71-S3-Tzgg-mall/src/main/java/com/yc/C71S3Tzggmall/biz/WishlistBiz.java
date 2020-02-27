package com.yc.C71S3Tzggmall.biz;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.Wishlist;
import com.yc.C71S3Tzggmall.bean.WishlistExample;
import com.yc.C71S3Tzggmall.dao.WishlistMapper;

@Service
public class WishlistBiz {
	
	@Resource
	private WishlistMapper wm;
	
	@Resource
	private WishlistBiz Wbiz;
	
	@Resource
	private ClothBiz cBiz;
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
	
	
	
	
	/**
	 * 查看
	 * @param wishlist
	 * @return
	 */
	public List<Wishlist> selectWishlistByuid(Wishlist wishlist){
		WishlistExample example=new WishlistExample();
		example.createCriteria().andUidEqualTo(wishlist.getUid());
		return wm.selectByExample(example);
		
	}
	
	//从收藏夹中删除
	public int delete(int cid){
			WishlistExample example=new WishlistExample();
			example.createCriteria().andCidEqualTo(cid);
			return wm.deleteByExample(example);
	}
	/**
	 * 删除所有
	 * @param uid
	 * @return
	 */
	public int deleteAllwish(int uid){
		WishlistExample example =new WishlistExample();
		example.createCriteria().andUidEqualTo(uid);
		return wm.deleteByExample(example);
	}
}
