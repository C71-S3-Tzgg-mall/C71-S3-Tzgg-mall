package com.yc.C71S3Tzggmall.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.C71S3Tzggmall.bean.Cart;
import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.Comment;
import com.yc.C71S3Tzggmall.bean.Orderitem;
import com.yc.C71S3Tzggmall.bean.Type;
import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.biz.CartBiz;
import com.yc.C71S3Tzggmall.biz.ClothBiz;
import com.yc.C71S3Tzggmall.biz.CommentBiz;
import com.yc.C71S3Tzggmall.biz.OrderItemBiz;
import com.yc.C71S3Tzggmall.biz.TypeBiz;
import com.yc.C71S3Tzggmall.vo.Result;

@Controller
public class SingleProductAction {
	
	@Resource
	private ClothBiz cBiz;
	
	@Resource
	private TypeBiz tBiz;
	
	@Resource
	private CartBiz cartBiz;
	
	@Resource
	private CommentBiz cmBiz;
	
	@Resource
	private OrderItemBiz oBiz;
	
	/**
	 * 局部刷新
	 * @param m
	 * @param cloth
	 * @return
	 */
	@RequestMapping("single.do")
	public String single(Model m,Cloth cloth){
		List<Cloth> c=cBiz.findClothByCon(cloth);
		m.addAttribute("singleP",c );
		List<Comment> list=cmBiz.findComment(cloth.getCid());
		try{
			int stars=0;
			for(int i=0;i<list.size();i++){
				stars+=list.get(i).getStars();
			}
			stars=stars/list.size();
			m.addAttribute("st", stars);
		}catch(ArithmeticException e){
			e.printStackTrace();
		}
		return "single-product::single";
	}
	
	@RequestMapping("single-product")
	public String single(Model m,HttpServletRequest request){
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
		}
		List<Type> typeList=tBiz.selectType();
		m.addAttribute("type",typeList);
		
		return "single-product";
	}
	
	
	@ResponseBody
	@RequestMapping("addComment")
	public Result addComment(Comment c,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		try{
			List<Orderitem> list=oBiz.showByUid(user.getUid());
			for(int i=0;i<list.size();i++){
				if(c.getCid()==list.get(i).getCid() && list.get(i).getStatus().equals("已完成")){
					c.setUid(user.getUid());
					c.setName(user.getName());
					cmBiz.addComment(c);
					return new Result(0,"redirect:single-product?cid="+c.getCid());
				}
			}
			return new Result(2,"请购买该商品或确认您已收货");
		}catch(NullPointerException e){
			e.printStackTrace();
			return new Result(1,"请先登录");
		}catch(RuntimeException e){
			e.printStackTrace();
			return new Result(1,"评论失败");
		}
		
	}
	
	@RequestMapping("findComment")
	public String findComment(Comment c,Model m){
		List<Comment> clist=cmBiz.findComment(c.getCid());
		m.addAttribute("comment",clist);
		return "single-product::data";
	}
	
	
	/**
	 * 商品详情
	 * @param cloth
	 * @param m
	 * @return
	 */
	@RequestMapping("detail")
	public String detail(Cloth cloth,Model m){
		List<Cloth> c=cBiz.findClothByCon(cloth);
		System.out.println(c);
		m.addAttribute("det",c);
		return "single-product::info";
	}
}
