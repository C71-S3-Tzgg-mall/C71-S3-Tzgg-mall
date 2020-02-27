package com.yc.C71S3Tzggmall.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.C71S3Tzggmall.bean.Cart;
import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.Comment;
import com.yc.C71S3Tzggmall.bean.Tag;
import com.yc.C71S3Tzggmall.bean.Type;
import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.biz.CartBiz;
import com.yc.C71S3Tzggmall.biz.ClothBiz;
import com.yc.C71S3Tzggmall.biz.CommentBiz;
import com.yc.C71S3Tzggmall.biz.TagBiz;
import com.yc.C71S3Tzggmall.biz.TypeBiz;


@Controller
public class SpecialShopAction {
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
	
	@Resource
	private CommentBiz cmBiz;
	
	@RequestMapping("specialShop")
	/**
	 * 加载shop页面
	 * @param m
	 * @return
	 */
	public String type(Model m,HttpServletRequest request){
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
		}
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
		List<Cloth> list=cBiz.findSpecialByTime();
		List<Comment> cmlist=null;
		int stars=0;
		for(int i=0;i<list.size();i++){
			cmlist=cmBiz.findComment(list.get(i).getCid());
			for(int j=0;j<cmlist.size();j++){
				stars+=cmlist.get(j).getStars();
			}
			if(cmlist.size()==0){
				list.get(i).setStars(stars);
			}else{
				stars=stars/cmlist.size();
				list.get(i).setStars(stars);
				stars=0;
			}
		}
		m.addAttribute("fCList", list);
		return "specialShop";
	}
	
	
	@RequestMapping("sfCByTime.do")
	/**
	 * 根据时间查询新品
	 * @param m
	 * @return
	 */
	public String findClothByTime(Model m){
		List<Cloth> list=cBiz.findClothByTime();
		List<Comment> cmlist=null;
		int stars=0;
		for(int i=0;i<list.size();i++){
			cmlist=cmBiz.findComment(list.get(i).getCid());
			for(int j=0;j<cmlist.size();j++){
				stars+=cmlist.get(j).getStars();
			}
			if(cmlist.size()==0){
				list.get(i).setStars(stars);
			}else{
				stars=stars/cmlist.size();
				list.get(i).setStars(stars);
				stars=0;
			}
		}
		m.addAttribute("fCList",list);
		return "specialShop::fbd";
	}
	
	@RequestMapping("sfCByCon.do")
	/**
	 * 条件查询商品
	 * @param m
	 * @param cloth
	 * @return
	 */
	public String findClothByCon(Model m,Cloth cloth){
		List<Cloth> list=cBiz.findClothByCon(cloth);
		List<Comment> cmlist=null;
		int stars=0;
		for(int i=0;i<list.size();i++){
			cmlist=cmBiz.findComment(list.get(i).getCid());
			for(int j=0;j<cmlist.size();j++){
				stars+=cmlist.get(j).getStars();
			}
			if(cmlist.size()==0){
				list.get(i).setStars(stars);
			}else{
				stars=stars/cmlist.size();
				list.get(i).setStars(stars);
				stars=0;
			}
		}
		m.addAttribute("fCList",list);
		return "specialShop::fbd";
	}	
	
}
