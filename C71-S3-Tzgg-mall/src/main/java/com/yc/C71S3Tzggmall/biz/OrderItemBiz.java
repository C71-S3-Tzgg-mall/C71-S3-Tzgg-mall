package com.yc.C71S3Tzggmall.biz;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;
import com.yc.C71S3Tzggmall.bean.Cart;
import com.yc.C71S3Tzggmall.bean.Order;
import com.yc.C71S3Tzggmall.bean.Orderitem;
import com.yc.C71S3Tzggmall.bean.OrderitemExample;
import com.yc.C71S3Tzggmall.dao.OrderitemMapper;


@Service
public class OrderItemBiz {
	
	@Resource
	private OrderitemMapper om;
	
	@Resource
	private CartBiz cBiz;
	
	@Resource
	private OrderBiz oBiz;
	
	@Resource
	private UserBiz uBiz;
	
	/**
	 * 生成订单
	 * @return
	 */
	public  void  settle(List<Integer> cidsList,int uid,String oid){
		Orderitem orderitem=new Orderitem();
		List<Cart> list = cBiz.findCartByCid(cidsList);
		double totalPrice=0;
		for(Cart c:list){
			System.out.println(c.getCartid());
			totalPrice=c.getCount()*c.getPrice();
			orderitem.setTotalprice(totalPrice);
			orderitem.setCid(c.getCid());
			orderitem.setUid(uid);
			orderitem.setCount(c.getCount());
			orderitem.setOid(oid);
			orderitem.setStatus("未支付");
			Date d=new Date();      
			Timestamp t = new Timestamp(d.getTime());
			orderitem.setTime(t);
			om.insert(orderitem);
		}
		
	}
	
	/**
	 * 根据用户id查询订单
	 * @return
	 */
	public List<Orderitem> showOrder(int uid){
		OrderitemExample example=new OrderitemExample();
		example.createCriteria().andUidEqualTo(uid);
		List<Orderitem> list=om.selectByExample(example);
		return list;
	}
	
	/**
	 * 根据订单编号删除订单
	 * @return
	 */
	public int del(Orderitem item){
		return om.deleteByPrimaryKey(item.getOiid());
	}
	
	/**
	 * 修改订单状态
	 * @param item
	 * @return
	 */
	public int updateStatus(Orderitem item){
		OrderitemExample example=new OrderitemExample();
		example.createCriteria().andCidEqualTo(item.getCid());
		return om.updateByExampleSelective(item, example);
	}
	
	/**
	 * 根据订单号查询订单
	 * @param oid
	 * @return
	 */
	public List<Orderitem> findOrderByOid(String oid){
		OrderitemExample example=new OrderitemExample();
		example.createCriteria().andOidEqualTo(oid);
		List<Orderitem> list=om.selectByExample(example);
		return list;
	}
}
