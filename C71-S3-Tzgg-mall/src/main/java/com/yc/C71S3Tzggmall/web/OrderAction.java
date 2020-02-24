package com.yc.C71S3Tzggmall.web;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.C71S3Tzggmall.biz.CartBiz;
import com.yc.C71S3Tzggmall.biz.OrderItemBiz;

@Controller
public class OrderAction {
	
	@Resource
	private OrderItemBiz oBiz;
	
	@Resource
	private CartBiz cartBiz;
	
	@RequestMapping("order")
	public String order(){
		return "order";
	}
	
	
	
}
