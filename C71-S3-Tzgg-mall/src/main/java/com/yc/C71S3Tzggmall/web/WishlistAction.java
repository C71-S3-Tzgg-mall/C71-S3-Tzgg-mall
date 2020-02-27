package com.yc.C71S3Tzggmall.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;
import com.yc.C71S3Tzggmall.bean.Cart;
import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.Orderitem;
import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.bean.Wishlist;
import com.yc.C71S3Tzggmall.biz.CartBiz;
import com.yc.C71S3Tzggmall.biz.ClothBiz;
import com.yc.C71S3Tzggmall.biz.WishlistBiz;
import com.yc.C71S3Tzggmall.vo.Result;

@Controller
public class WishlistAction {
	
	@Resource
	private WishlistBiz wBiz;
	
	@Resource
	private ClothBiz cBiz;
	
	@Resource
	private CartBiz cartBiz;
	
	@Resource
	private ClothBiz clBiz;
	
	@ResponseBody
	@PostMapping("addWishlist.do")
	public Result addWishlist(Wishlist wl,HttpServletRequest request){
		User user=(User)request.getSession().getAttribute("user");
		try{
			wl.setUid(user.getUid());
			wBiz.addWishlist(wl);
			return new Result(1,"已加入收藏夹,在收藏夹等亲~");
		}catch(NullPointerException e){
			e.printStackTrace();
			return new Result(2,"请先登录");
		}catch(RuntimeException e){
			e.printStackTrace();
			wBiz.deleteWishlist(wl);
			return new Result(0,"已取消收藏");
		}
		
	}
	
	/**
	 * 显示
	 * @param m
	 * @param request
	 * @return
	 */
	public Cloth showWishlist(Model m,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");	
		Wishlist wishlist=new Wishlist();	
		wishlist.setUid(user.getUid());
		List<Wishlist> WishList=wBiz.selectWishlistByuid(wishlist);
		List<Cloth> list=null;	
		Cloth cloth=new Cloth();;	
		for(int i=0;i<WishList.size();i++){
			list=new ArrayList<>();	
			for(int j=0;j<WishList.size();j++){
				cloth=cBiz.selectCloth(WishList.get(j).getCid());
				cloth.setArrivetime(WishList.get(j).getTime());
				cloth.setCount(WishList.get(j).getWid());
				list.add(cloth);
			}	
		}
		cloth.setList(list);
		return cloth;
	}
	
	@RequestMapping("wishlist")
	public String order(Model m,Orderitem order, HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");	
		if(user!=null){
			Cart cart=new Cart();
			cart.setUid(user.getUid());
			List<Cart> cartList=cartBiz.findCartByUid(cart);
			int total=0;
			for (Cart c : cartList) {
				total += c.getCount()*c.getPrice();
			}
			m.addAttribute("total",total);
			m.addAttribute("cartSize", cartList.size());
			Cloth pro=cartBiz.showCart(user.getUid());
			m.addAttribute("cartList", pro);
			Cloth cloth=showWishlist(m, request);
			m.addAttribute("wlist",cloth);
		}
		return "wishlist";
	} 
	
	/**
	 * 删除
	 * @param cloth
	 * @param m
	 * @return
	 */
	@PostMapping("delWist")
	public String delwist(Model m,Wishlist wishlist,HttpServletRequest request){
		/*User user=(User) request.getSession().getAttribute("user");
		  
		  orderitem.setUid(user.getUid());*/
		wBiz.deleteWishlist(wishlist);
        Cloth cloth=showWishlist(m, request);
		m.addAttribute("wlist",cloth);
		return "wishlist::wis";
	}
	
	@PostMapping("delAllwish.do")
	public String deleteAll(Model m,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		int i=wBiz.deleteAllwish(user.getUid());
		 Cloth cloth=showWishlist(m, request);
			m.addAttribute("wlist",cloth);
			return "wishlist::wis";
	}
	
	@RequestMapping("addCarts")
	public String addCarts(HttpServletRequest request,Cart cart,Model m){
		User user=(User)request.getSession().getAttribute("user");
		String cids = request.getParameter("cids");
		//System.out.println(cids);
		List<Cart> list=null;
		if(cids!=null){
			// 分割字符串
			String[] infos = cids.split(",");
			for (String cid : infos) {
				if (StringUtil.isNotEmpty(cids)) {
					cart.setCid(Integer.parseInt(cid));
					cart.setUid(user.getUid());
					list=cartBiz.findByCid(cart);
					Cloth cloth=clBiz.selectCloth(Integer.parseInt(cid));
					cart.setPrice(cloth.getRealprice());
					if(!list.isEmpty()){
						//System.out.println(list.get(0).getCount()+1);
						cart.setCount(list.get(0).getCount()+1);
						//System.out.println(11111);
						cartBiz.update(cart);
					}
					if(list.isEmpty()){
						cart.setCount(1);
						cart.setUid(user.getUid());	
						cartBiz.addCart(cart);
					}
					wBiz.delete(Integer.parseInt(cid));
					
				}
			}
		}
		Cloth cl=showWishlist(m, request);
		m.addAttribute("wlist",cl);
		return "wishlist::wis";
	}
}
