package com.yc.C71S3Tzggmall.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.C71S3Tzggmall.bean.Cart;
import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.Tag;
import com.yc.C71S3Tzggmall.bean.Type;
import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.biz.CartBiz;
import com.yc.C71S3Tzggmall.biz.ClothBiz;
import com.yc.C71S3Tzggmall.biz.TagBiz;
import com.yc.C71S3Tzggmall.biz.TypeBiz;


@Controller
public class NewShopAction {
	//首页分页每页行数
	private final static int PAGE_SIZE=5;
	
	@Resource
	private ClothBiz cBiz;
	
	@Resource
	private TypeBiz tBiz;
	
	@Resource
	private TagBiz tagBiz;
	
	@Resource
	private CartBiz cartBiz;
	
	
	@RequestMapping("newShop")
	/**
	 * 加载shop页面
	 * @param m
	 * @return
	 */
	public String type(Model m,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			return "login";
		}
		Cart cart=new Cart();
		cart.setUid(user.getUid());
		List<Cart> cartList=cartBiz.findCartByUid(cart);
		int total=0;
		for (Cart c : cartList) {
			total += c.getCount()*c.getType();
		}
		m.addAttribute("total",total);
		m.addAttribute("cartSize", cartList.size());
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
		List<Cloth> list=cBiz.findClothByTime();
		m.addAttribute("fCList", list);
		Cloth cloth=cartBiz.showCart(request);
		m.addAttribute("cartList", cloth);
		return "newShop";
	}
	
	
	@RequestMapping("nfCByTime.do")
	/**
	 * 根据时间查询新品
	 * @param m
	 * @return
	 */
	public String findClothByTime(Model m){
		List<Cloth> list=cBiz.findClothByTime();
		m.addAttribute("fCList",list);
		return "newShop::fbd";
	}
	
	@RequestMapping("nfCByCon.do")
	/**
	 * 条件查询商品
	 * @param m
	 * @param cloth
	 * @return
	 */
	public String findClothByCon(Model m,Cloth cloth){
		List<Cloth> list=cBiz.findClothByCon(cloth);
		m.addAttribute("fCList",list);
		return "newShop::fbd";
	}	
	
	@RequestMapping("shop01")
	/**
	 * 分页查询
	 * @param m
	 * @param start
	 * @param size
	 * @return
	 */
	public String findClothByCon(Model m,@RequestParam(value = "start",defaultValue = "0")int start,
            @RequestParam(value = "size",defaultValue = "5")int size){
		PageHelper.startPage(start,PAGE_SIZE);
		List<Cloth> list=cBiz.selectCloth();
		PageInfo<Cloth> pageInfo = new PageInfo<Cloth>(list);
		System.out.println(pageInfo);
		m.addAttribute("page",pageInfo);
		return "shop01";
	}
	
	
}
