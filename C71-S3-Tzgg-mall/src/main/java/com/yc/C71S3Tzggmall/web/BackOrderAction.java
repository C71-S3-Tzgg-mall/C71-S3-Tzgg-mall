package com.yc.C71S3Tzggmall.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.C71S3Tzggmall.bean.Admin;
import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.Orderitem;
import com.yc.C71S3Tzggmall.biz.OrderItemBiz;

@Controller
@RequestMapping("back")
public class BackOrderAction {
	
	@Resource
	private OrderItemBiz oBiz;
	
	@RequestMapping("order")
	public String bill(Model m,HttpServletRequest request){
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		m.addAttribute("admin",admin);
		Cloth cloth=oBiz.showbackOrder();
		System.out.println(cloth.getList().get(0).getDetail());
		m.addAttribute("olist",cloth);
		return "/back/order";
	}
	
	@PostMapping("reStatu")
	public String reStatus(Orderitem item,Model m){
		item.setStatus("已发货");
		oBiz.reStatus(item);
		return "/back/order::move";
	}
}

