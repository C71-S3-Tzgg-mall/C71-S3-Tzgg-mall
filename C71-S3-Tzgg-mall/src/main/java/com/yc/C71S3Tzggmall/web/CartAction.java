package com.yc.C71S3Tzggmall.web;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;
import com.yc.C71S3Tzggmall.bean.Cart;
import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.biz.CartBiz;
import com.yc.C71S3Tzggmall.biz.ClothBiz;
import com.yc.C71S3Tzggmall.biz.WishlistBiz;
import com.yc.C71S3Tzggmall.vo.Result;

@Controller
public class CartAction {
	
	@Resource
	private CartBiz cBiz;
	
	@Resource
	private ClothBiz clBiz;
	
	@Resource
	private WishlistBiz wBiz;
	
	/**
	 * 
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping("cart")
	public String cart(Model m,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		if(user!=null){
			Cloth cloth=cBiz.showCart(user.getUid());
			Cart cart=new Cart();
			cart.setUid(user.getUid());
			List<Cart> cartList=cBiz.findCartByUid(cart);
			int total=0;
			for (Cart c : cartList) {
				total += c.getCount()*c.getPrice();
			}
			m.addAttribute("total",total);
			m.addAttribute("cartSize", cartList.size());
			m.addAttribute("cartList",cloth);
		}
		
		//System.out.println(cloth.getList().size());
		return "cart";
	}
	
	
	@PostMapping("delAll.do")
	public String deleteAll(Model m,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		int i=cBiz.deleteAll(user.getUid());
		Cloth cloth=cBiz.showCart(user.getUid());
		m.addAttribute("cartList",cloth);
		return "cart::cart";
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
		User user=(User) request.getSession().getAttribute("user");
		cart.setUid(user.getUid());
		int i=cBiz.delete(cart);
		Cloth cloth=cBiz.showCart(user.getUid());
		m.addAttribute("cartList",cloth);
		return "cart::cart";
	}
	
	/**
	 * 加入购物车
	 * @param cart
	 * @param request
	 * @return
	 */
	@ResponseBody
	@PostMapping("addCart.do")
	public Result addCart(Cart cart,HttpServletRequest request){
		try{
			User user=(User)request.getSession().getAttribute("user");
			cart.setUid(user.getUid());
			List<Cart> list=cBiz.findByCid(cart);
			if(!list.isEmpty()){
				if(cart.getCount()==null){
					//System.out.println(list.get(0).getCount()+1);
					cart.setCount(list.get(0).getCount()+1);
					//System.out.println(11111);
					cBiz.update(cart);
				}else{
					cart.setCount(list.get(0).getCount()+cart.getCount());
					cBiz.update(cart);
				}
				
			}
			if(list.isEmpty()){
				if(cart.getCount()==null){
					cart.setCount(1);
					cart.setUid(user.getUid());	
					cBiz.addCart(cart);
				}else{
					cart.setUid(user.getUid());	
					cBiz.addCart(cart);
				}		
			}
			return new Result(1,"成功加入购物车，请到购物车查看");
		}catch(NullPointerException e){
			e.printStackTrace();				
			return new Result(0, "请先登录");
		}
	}
	
	
	
}