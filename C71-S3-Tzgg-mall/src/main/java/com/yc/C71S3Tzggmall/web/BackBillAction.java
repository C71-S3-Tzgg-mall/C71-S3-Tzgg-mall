package com.yc.C71S3Tzggmall.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.C71S3Tzggmall.bean.Admin;

@Controller
@RequestMapping("back")
public class BackBillAction {
	
	@RequestMapping("bill")
	public String bill(Model m,HttpServletRequest request){
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		m.addAttribute("admin",admin);
		return "/back/bill";
	}
	
	
}
