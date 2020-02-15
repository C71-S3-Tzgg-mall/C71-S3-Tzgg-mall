package com.yc.C71S3Tzggmall.web;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.C71S3Tzggmall.bean.Admin;
import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.biz.BizException;
import com.yc.C71S3Tzggmall.biz.ClothBiz;


@Controller
public class ClothAction {
	
	@Resource
	private ClothBiz cBiz;
	
	@RequestMapping("index")
	public String index(Model m){
		List<Cloth> list=cBiz.selectCloth();
		m.addAttribute("list",list);
		System.out.println(list);
		return "index";
	}
	
	
	
}
