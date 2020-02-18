package com.yc.C71S3Tzggmall.web;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.Tag;
import com.yc.C71S3Tzggmall.bean.Type;
import com.yc.C71S3Tzggmall.biz.ClothBiz;
import com.yc.C71S3Tzggmall.biz.TagBiz;
import com.yc.C71S3Tzggmall.biz.TypeBiz;


@Controller
public class ClothAction {
	
	@Resource
	private ClothBiz cBiz;
	
	@Resource
	private TypeBiz tBiz;
	
	@Resource
	private TagBiz tagBiz;
	
	@RequestMapping("single-product")
	public String single(Model m){
		return "single-Product";
	}
	
	
	@RequestMapping("singleProduct")
	public String singleProduct(Model m,Cloth cloth){
		List<Cloth> list=cBiz.findClothByCon(cloth);
		m.addAttribute("singleProduct",list);
		return "redirect:single-product?cid="+cloth.getCid();
	}
	
	@RequestMapping("index")
	public String index(Model m){
		List<Cloth> list=cBiz.selectCloth();
		m.addAttribute("list",list);
		//System.out.println(list);
		return "index";
	}
	
	@RequestMapping("shop")
	/**
	 * 加载shop页面
	 * @param m
	 * @return
	 */
	public String type(Model m){
		List<Type> typeList=tBiz.selectType();
		List<Tag> tagList=tagBiz.selectTag();
		@SuppressWarnings("unused")
		int count;
		for(int i=0;i<typeList.size();i++){
			count=cBiz.findClothCount(typeList.get(i).getTid());
			typeList.get(i).setCount(count);
		}
		int clothCount=cBiz.findClothCountByTime();
		int restCount=cBiz.findClothCountByRes();
		m.addAttribute("restCount", restCount);
		m.addAttribute("count",clothCount);
		m.addAttribute("typeList",typeList);
		m.addAttribute("tagList",tagList);
		return "shop";
	}
	
	
	@RequestMapping("findClothByTime.do")
	/**
	 * 根据时间查询新品
	 * @param m
	 * @return
	 */
	public String findClothByTime(Model m){
		List<Cloth> list=cBiz.findClothByTime();
		m.addAttribute("fCList",list);
		return "shop::fbd";
	}
	
	@RequestMapping("findClothByCon.do")
	/**
	 * 条件查询商品
	 * @param m
	 * @param cloth
	 * @return
	 */
	public String findClothByCon(Model m,Cloth cloth){
		List<Cloth> list=cBiz.findClothByCon(cloth);
		m.addAttribute("fCList",list);
		return "shop::fbd";
	}	
	
	@RequestMapping("single.do")
	public String single(Model m,Cloth cloth){
		List<Cloth> c=cBiz.findClothByCon(cloth);
		System.out.println(c);
		m.addAttribute("singleP",c );
		return "single-Product::single";
	}
	
	
}
