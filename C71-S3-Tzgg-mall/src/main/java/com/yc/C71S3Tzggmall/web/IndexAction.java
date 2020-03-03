package com.yc.C71S3Tzggmall.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.C71S3Tzggmall.bean.Cart;
import com.yc.C71S3Tzggmall.bean.Chat;
import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.Comment;
import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.biz.CartBiz;
import com.yc.C71S3Tzggmall.biz.ChatBiz;
import com.yc.C71S3Tzggmall.biz.ClothBiz;
import com.yc.C71S3Tzggmall.biz.CommentBiz;

@Controller
public class IndexAction {
	
	@Resource
	private ClothBiz cBiz;
	
	@Resource
	private CartBiz cartBiz;
	
	@Autowired
    private ServletContext servletContext;
	
	@Resource
	private CommentBiz cmBiz;
	
	@Resource
	private ChatBiz chatBiz;
	
	@RequestMapping("index")
	public String index(Model m,Cloth cloth,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		if(user!=null){
			m.addAttribute("userId", user.getName());
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
			
			int chatCount=chatBiz.selectCount(user.getName());
			m.addAttribute("chatCount",chatCount);
		}
		List<Cloth> newList=cBiz.findClothByTime();
		List<Comment> cmlist=null;
		int stars=0;
		for(int i=0;i<newList.size();i++){
			cmlist=cmBiz.findComment(newList.get(i).getCid());
			for(int j=0;j<cmlist.size();j++){
				stars+=cmlist.get(j).getStars();
			}
			if(cmlist.size()==0){
				newList.get(i).setStars(stars);
			}else{
				stars=stars/cmlist.size();
				newList.get(i).setStars(stars);
				stars=0;
			}
		}
		List<Cloth> bestList=cBiz.selectBestCloth();
		for(int i=0;i<bestList.size();i++){
			cmlist=cmBiz.findComment(bestList.get(i).getCid());
			for(int j=0;j<cmlist.size();j++){
				stars+=cmlist.get(j).getStars();
			}
			if(cmlist.size()==0){
				bestList.get(i).setStars(stars);
			}else{
				stars=stars/cmlist.size();
				bestList.get(i).setStars(stars);
				stars=0;
			}
		}
		List<Cloth> specialList=cBiz.findSpecialByTime();
		for(int i=0;i<specialList.size();i++){
			cmlist=cmBiz.findComment(specialList.get(i).getCid());
			for(int j=0;j<cmlist.size();j++){
				stars+=cmlist.get(j).getStars();
			}
			if(cmlist.size()==0){
				specialList.get(i).setStars(stars);
			}else{
				stars=stars/cmlist.size();
				specialList.get(i).setStars(stars);
				stars=0;
			}
		}
		m.addAttribute("newList",newList);
		m.addAttribute("bestList",bestList);
		m.addAttribute("sList",specialList);
		Integer num=(Integer) servletContext.getAttribute("num");
		if(null==num){
			num=1;
		}else{
			num++;
		}
		servletContext.setAttribute("num", num);
		
		return "index";
	}
	
	@RequestMapping("exit")
	public String exit(HttpServletRequest request){
		request.getSession().removeAttribute("user");
		return "login";
	}
}
