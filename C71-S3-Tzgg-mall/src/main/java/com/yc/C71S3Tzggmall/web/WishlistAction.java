package com.yc.C71S3Tzggmall.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.C71S3Tzggmall.bean.Wishlist;
import com.yc.C71S3Tzggmall.biz.WishlistBiz;
import com.yc.C71S3Tzggmall.vo.Result;

@Controller
public class WishlistAction {
	
	@Resource
	private WishlistBiz wBiz;
	
	@ResponseBody
	@PostMapping("addWishlist.do")
	public Result addWishlist(Wishlist wl){
		try{
			wBiz.addWishlist(wl);
			return new Result(1,"已加入收藏夹,在收藏夹等亲~");
		}catch(RuntimeException e){
			e.printStackTrace();
			wBiz.deleteWishlist(wl);
			return new Result(0,"已取消收藏");
		}
		
	}
}
