package com.yc.C71S3Tzggmall.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.biz.ClothBiz;

@Controller
public class SingleProductAction {
	
	@Resource
	private ClothBiz cBiz;
	
	/**
	 * 局部刷新
	 * @param m
	 * @param cloth
	 * @return
	 */
	@RequestMapping("single.do")
	public String single(Model m,Cloth cloth){
		List<Cloth> c=cBiz.findClothByCon(cloth);
		System.out.println();
		m.addAttribute("singleP",c );
		return "single-Product::single";
	}
	
	@RequestMapping("single-product")
	public String single(Model m){
		return "single-Product";
	}
	
	/**
	 * 商品详情页
	 * @param m
	 * @param cloth
	 * @return
	 */
	@RequestMapping("singleProduct")
	public String singleProduct(Model m,Cloth cloth){
		List<Cloth> list=cBiz.findClothByCon(cloth);
		m.addAttribute("singleProduct",list);
		return "redirect:single-product?cid="+cloth.getCid();
	}
}
