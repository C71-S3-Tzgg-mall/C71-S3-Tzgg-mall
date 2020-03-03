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

import com.yc.C71S3Tzggmall.bean.Cart;
import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.Orderitem;
import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.biz.CartBiz;
import com.yc.C71S3Tzggmall.biz.ClothBiz;
import com.yc.C71S3Tzggmall.biz.OrderItemBiz;
import com.yc.C71S3Tzggmall.vo.Result;

@Controller
public class OrderAction {
	
	@Resource
	private ClothBiz cbiz;

	@Resource
	private OrderItemBiz obiz;
	
	@Resource
	private CartBiz cartBiz;
	/**
	 * 删除
	 * @param cloth
	 * @param m
	 * @return
	 */
	@PostMapping("del")
	public String del(Model m,Orderitem orderitem,HttpServletRequest request){
		/*User user=(User) request.getSession().getAttribute("user");
		  
		  orderitem.setUid(user.getUid());*/
		obiz.delete(orderitem);
		Cloth cloth=showOrderitem(m, request);
		  m.addAttribute("clist",cloth);
		return "order::ori";
	}
	
	@RequestMapping("order")
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
			Cloth cloth=showOrderitem(m, request);
			m.addAttribute("clist",cloth);
		}
		
		return "order";
	} 
	
	/**
	 * 显示订单
	 * @param totalprice 
	 * @param order
	 * @return
	 */
	public Cloth showOrderitem(Model m,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		int total=0;
		Orderitem orderitem=new Orderitem();
		orderitem.setUid(user.getUid());	
		List<Orderitem> orderList=obiz.selectOrderByuid(orderitem);	
		List<Cloth> list=null;	
		Cloth cloth=new Cloth();
		for(int i=0;i<orderList.size();i++){
			list=new ArrayList<>();	
			for(int j=0;j<orderList.size();j++){
				cloth=cbiz.selectCloth(orderList.get(j).getCid());
				cloth.setCount(orderList.get(j).getCount());
				cloth.setStatus(orderList.get(j).getStatus());
				cloth.setStars(orderList.get(j).getOiid());
				cloth.setDetail(orderList.get(j).getOid());
				list.add(cloth);	
			}	
			total+=orderList.get(i).getTotalprice();
		}
		cloth.setList(list);
	    m.addAttribute("Total",total);
	    return cloth;
	}
	
	@PostMapping("upd.do")
	public String update(Orderitem item,Model m,HttpServletRequest request){
		Orderitem order=obiz.selectByOid(item.getOiid());
		String status=order.getStatus();
		if(status.equals("已发货")){
			item.setStatus("已完成");
			obiz.UpdStatus(item);
			Cloth cloth=cbiz.selectCloth(order.getCid());
			cloth.setSolecount(cloth.getSolecount()+order.getCount());
			cloth.setRestcount(cloth.getRestcount()-order.getCount());
			cbiz.updateSoleCount(cloth);
		}
		Cloth cloth=showOrderitem(m, request);
		m.addAttribute("clist",cloth);
		return "order::ori";
	}
	
	@PostMapping("upd2.do")
	public String update2(Orderitem item,Model m,HttpServletRequest request){
		item.setStatus("取消");
		obiz.UpdStatus(item);
		Cloth cloth=showOrderitem(m, request);
		m.addAttribute("clist",cloth);
		return "order::ori";
	}
	
	@ResponseBody
	@PostMapping("upd1.do")
	public Result selectByOiid(Orderitem item){
		//System.out.println(item.getOiid());
		Orderitem order=obiz.selectByOid(item.getOiid());
		String oid=order.getOid();
		//System.out.println(oid);
		return new Result(1,oid);
	}
}
