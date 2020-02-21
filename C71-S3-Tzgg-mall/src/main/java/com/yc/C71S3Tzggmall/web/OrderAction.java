package com.yc.C71S3Tzggmall.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;
import com.yc.C71S3Tzggmall.bean.Cart;
import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.biz.CartBiz;
import com.yc.C71S3Tzggmall.biz.OrderItemBiz;
import com.yc.C71S3Tzggmall.vo.Result;

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
