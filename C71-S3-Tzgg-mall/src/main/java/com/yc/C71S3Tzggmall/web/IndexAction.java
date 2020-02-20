package com.yc.C71S3Tzggmall.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.C71S3Tzggmall.bean.Cart;
import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.biz.CartBiz;
import com.yc.C71S3Tzggmall.biz.ClothBiz;

@Controller
public class IndexAction {
	
	@Resource
	private ClothBiz cBiz;
	
	@Resource
	private CartBiz cartBiz;
	
	@RequestMapping("index")
	public String index(Model m,Cloth cloth,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			return "login";
		}
		Cart cart=new Cart();
		cart.setUid(user.getUid());
		List<Cart> cartList=cartBiz.findCartByUid(cart);
		int total=0;
		for (Cart c : cartList) {
			total += c.getCount()*c.getType();
		}
		m.addAttribute("total",total);
		m.addAttribute("cartSize", cartList.size());
		List<Cloth> newList=cBiz.findClothByTime();
		List<Cloth> bestList=cBiz.findClothByCon(cloth);
		List<Cloth> specialList=cBiz.findSpecialByTime();
		m.addAttribute("newList",newList);
		m.addAttribute("bestList",bestList);
		m.addAttribute("sList",specialList);
		Cloth pro=cartBiz.showCart(request);
		m.addAttribute("cartList", pro);
		//System.out.println(specialList.size());
		//System.out.println(list);
		return "index";
	}
}
