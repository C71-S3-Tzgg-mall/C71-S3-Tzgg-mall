package com.yc.C71S3Tzggmall.biz;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yc.C71S3Tzggmall.bean.Cart;
import com.yc.C71S3Tzggmall.bean.CartExample;
import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.dao.CartMapper;

@Service
public class CartBiz {
	
	@Resource
	private CartMapper cm;
	
	@Resource
	private CartBiz cBiz;
	
	@Resource
	private ClothBiz clBiz;
	/**
	 * 根据用户id查看购物车
	 * @param cart
	 * @return
	 */
	public List<Cart> findCartByUid(Cart cart){
		CartExample example=new CartExample();
		example.createCriteria().andUidEqualTo(cart.getUid());
		return cm.selectByExample(example);
	}
	
	/**
	 * 根据商品id删除购物车中的商品
	 * @param cart
	 * @return
	 */
	public int delete(Cart cart){
		CartExample example =new CartExample();
		example.createCriteria().andCidEqualTo(cart.getCid());
		return cm.deleteByExample(example);
	}
	
	
	
	public List<Cart> findCartByCid(List<Integer> list){
		CartExample example=new CartExample();
		example.createCriteria().andCidIn(list);
		return cm.selectByExample(example);
	}
	
	/**
	 * 查询购物车的具体内容
	 * @param request
	 * @return
	 */
	public Cloth showCart(HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		//System.out.println(user);
		Cart cart=new Cart();
		cart.setUid(user.getUid());
		List<Cart> cartList=cBiz.findCartByUid(cart);
		List<Cloth> list=null;
		Cloth cloth=new Cloth();
		for(int i=0;i<cartList.size();i++){
			list=new ArrayList<Cloth>();
			for(int j=0;j<cartList.size();j++){
				cloth=clBiz.selectCloth(cartList.get(j).getCid());
				cloth.setCount(cartList.get(j).getCount());
				list.add(cloth);
			}
			
		}
		cloth.setList(list);
		return cloth;
	}
	
}
