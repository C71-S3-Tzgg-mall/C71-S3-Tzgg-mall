package com.yc.C71S3Tzggmall.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.C71S3Tzggmall.bean.Cart;
import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.biz.CartBiz;
import com.yc.C71S3Tzggmall.biz.ClothBiz;
import com.yc.C71S3Tzggmall.vo.Result;

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
	
	@ResponseBody
	@PostMapping("addCart.do")
	public Result addCart(Cart cart,HttpServletRequest request){
		try{
			User user=(User)request.getSession().getAttribute("user");
			Cloth cloth=clBiz.selectCloth(cart.getCid());
			List<Cart> list=cBiz.findByCid(cart);
			if(!list.isEmpty()){
				System.out.println(list.get(0));
				cart.setCount(list.get(0).getCount()+1);
				cBiz.update(cart);
			}else{
				cart.setCount(1);
				cart.setUid(user.getUid());	
				cBiz.addCart(cart);
			}
			return new Result(1,"成功加入购物车，请到购物车查看");
		}catch(Exception e){
			e.printStackTrace();				
			return new Result(0, "业务繁忙，请稍后再试");
		}
	}
	
	
	
}