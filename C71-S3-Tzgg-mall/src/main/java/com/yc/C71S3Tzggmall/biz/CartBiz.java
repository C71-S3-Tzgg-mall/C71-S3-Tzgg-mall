package com.yc.C71S3Tzggmall.biz;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.C71S3Tzggmall.bean.Cart;
import com.yc.C71S3Tzggmall.bean.CartExample;
import com.yc.C71S3Tzggmall.bean.Cloth;
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
	 * 根据商品id和用户id删除购物车中的商品
	 * @param cart
	 * @return
	 */
	public int delete(Cart cart){
		CartExample example =new CartExample();
		example.createCriteria().andCidEqualTo(cart.getCid()).andUidEqualTo(cart.getUid());
		return cm.deleteByExample(example);
	}
	
	/**
	 * 
	 * @param cart
	 * @return
	 */
	public int deleteAll(int uid){
		CartExample example =new CartExample();
		example.createCriteria().andUidEqualTo(uid);
		return cm.deleteByExample(example);
	}
	
	/**
	 * 范围查找
	 * @param list
	 * @return
	 */
	public List<Cart> findCartByCid(List<Integer> list){
		CartExample example=new CartExample();
		example.createCriteria().andCidIn(list);
		//System.out.println(list);
		return cm.selectByExample(example);
	}
	
	/**
	 * 查询购物车的具体内容
	 * @param request
	 * @return
	 */
	public Cloth showCart(int uid){
		Cart cart=new Cart();
		cart.setUid(uid);
		List<Cart> cartList=cBiz.findCartByUid(cart);
		List<Cloth> list=null;
		Cloth cloth=new Cloth();
		for(int i=0;i<cartList.size();i++){
			list=new ArrayList<Cloth>();
			for(int j=0;j<cartList.size();j++){
				cloth=clBiz.selectCloth(cartList.get(j).getCid());
				cloth.setCount(cartList.get(j).getCount());
				//System.out.println(cloth);
				list.add(cloth);
			}
			
		}
		cloth.setList(list);
		return cloth;
	}
	
	/**
	 * 加入购物车
	 * @param c
	 * @return
	 */
	public int addCart(Cart c){
		return cm.insertSelective(c);
	}
	
	/**
	 * 查看购物车
	 * @param cart
	 * @return
	 */
	public List<Cart> findByCid(Cart cart){
		CartExample example=new CartExample();
		example.createCriteria().andCidEqualTo(cart.getCid()).andUidEqualTo(cart.getUid());
		List<Cart> list= cm.selectByExample(example);
		return list;
	}
	
	/**
	 * 修改购物车的数量
	 * @param cart
	 * @return
	 */
	public int update(Cart cart){
		CartExample example=new CartExample();
		example.createCriteria().andCidEqualTo(cart.getCid()).andUidEqualTo(cart.getUid());
		return cm.updateByExampleSelective(cart, example);
	}
	
}
