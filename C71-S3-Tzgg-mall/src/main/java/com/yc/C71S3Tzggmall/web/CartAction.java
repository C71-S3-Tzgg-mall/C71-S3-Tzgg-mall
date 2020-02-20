package com.yc.C71S3Tzggmall.web;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.C71S3Tzggmall.bean.Cart;
import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.biz.CartBiz;
import com.yc.C71S3Tzggmall.biz.ClothBiz;

@Controller
public class CartAction {
	
	@Resource
	private CartBiz cBiz;
	
	@Resource
	private ClothBiz clBiz;
	
	/**
	 * 
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping("cart")
	public String cart(Model m,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			return "login";
		}
		Cloth cloth=cBiz.showCart(request);
		m.addAttribute("cartList",cloth);
		//System.out.println(cloth.getList().size());
		return "cart";
	}
	
	
	
	
	/**
	 * 根据cid删除购物车
	 * @param m
	 * @param cart
	 * @param request
	 * @return
	 */
	@PostMapping("delCart.do")
	public String deleteProduct(Model m,Cart cart,HttpServletRequest request){
		int i=cBiz.delete(cart);
		Cloth cloth=cBiz.showCart(request);
		m.addAttribute("cartList",cloth);
		return "cart::cart";
	}
	
}