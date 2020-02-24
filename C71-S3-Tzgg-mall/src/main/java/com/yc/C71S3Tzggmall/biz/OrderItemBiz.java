package com.yc.C71S3Tzggmall.biz;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.yc.C71S3Tzggmall.vo.Bill;


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
		//System.out.println(cidsList);
		List<Cart> list = cBiz.findCartByCid(cidsList);
		double totalPrice=0;
		for(Cart c:list){
			//System.out.println(c.getCartid());
			totalPrice=c.getCount()*c.getPrice();
			orderitem.setTotalprice(totalPrice);
			orderitem.setCid(c.getCid());
			orderitem.setUid(uid);
			orderitem.setCount(c.getCount());
			//System.out.println(c.getCount());
			orderitem.setOid(oid);
			orderitem.setStatus("未支付");
			Date d=new Date();      
			Timestamp t = new Timestamp(d.getTime());
			orderitem.setTime(t);
			om.insert(orderitem);
		}
		
	}
	
	/**
	 * 根据订单号查询订单
	 * @return
	 */
	public List<Orderitem> showOrder(String oid){
		OrderitemExample example=new OrderitemExample();
		example.createCriteria().andOidEqualTo(oid);
		List<Orderitem> list=om.selectByExample(example);
		return list;
	}
	
	public List<Orderitem> showByUid(int uid){
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
	
	/**
	 * 查询所有的订单数
	 * @return
	 */
	public int findCount(){
		return (int) om.countByExample(null);
	}
	
	/**
	 * 查询已完成的订单数
	 * @return
	 */
	public int findCountByStatus(){
		OrderitemExample example=new OrderitemExample();
		example.createCriteria().andStatusEqualTo("已完成");
		return (int) om.countByExample(example);
	}
	
	/**
	 * 查询配送中的订单数
	 * @return
	 */
	public int findCountByP(){
		OrderitemExample example=new OrderitemExample();
		example.createCriteria().andStatusEqualTo("配送中");
		return (int) om.countByExample(example);
	}
	
	/**
	 * 查询订单数
	 * @return
	 */
	public List<Bill> findMonthCount(){
		List<Bill> list=new ArrayList<>();
		Bill bill=null;
		Date d=new Date(); 
		Timestamp t01= null;
		Timestamp t =null;
		OrderitemExample example=null;
		int count=0;
		int time=0;
		String time01;
		for(int n=6;n>=0;n--){
			example=new OrderitemExample();
			t= new Timestamp(d.getTime()- n * 24 * 60 * 60 * 1000);
			t01=new Timestamp(d.getTime()- (n+1) * 24 * 60 * 60 * 1000);
			example.createCriteria().andTimeBetween(t01, t);
			count=(int) om.countByExample(example);
			bill=new Bill();
			bill.setCount(count);
			time01=t+"";
			time = (Integer.parseInt(time01.substring(8, 10)));
			bill.setTime(time);
			list.add(bill);
		}
		
		return list;
	}
	
	/**
	 * 最近7天的账单
	 * @return
	 */
	public List<Bill> findPriceW(){
		List<Bill> list=new ArrayList<>();
		List<Orderitem> oList=null;
		Bill bill=null;
		
		Date d=new Date(); 
		Timestamp t01= null;
		Timestamp t =null;
		OrderitemExample example=null;
		int total =0;
		int time=0;
		String time01;
		for(int n=6;n>=0;n--){
			bill=new Bill();
			example=new OrderitemExample();
			t= new Timestamp(d.getTime()- n * 24 * 60 * 60 * 1000);
			t01=new Timestamp(d.getTime()- (n+1) * 24 * 60 * 60 * 1000);
			example.createCriteria().andTimeBetween(t01, t).andStatusEqualTo("已完成");
			oList=om.selectByExample(example);
			for(int i=0;i<oList.size();i++){
				total+=oList.get(i).getTotalprice();
			}
			bill.setCount(total);
			time01=t+"";
			time = (Integer.parseInt(time01.substring(8, 10)));
			bill.setTime(time);
			list.add(bill);
		}
		
		return list;
	}
	
	/**
	 * 年账单
	 * @return
	 */
	public List<Bill> findPriceM(){
		List<Bill> list=new ArrayList<>();
		List<Orderitem> oList=null;
		
		Date d=new Date();;  
		Calendar calendar = Calendar.getInstance();
		// 获取当前月
		int year = calendar.get(Calendar.YEAR); 
		calendar.set(year, 0, 1);
		calendar.add(Calendar.YEAR, -5);
		d=calendar.getTime();
		Bill bill=null; 
		Timestamp t01= null;
		Timestamp t =null;
		OrderitemExample example=null;
		int total =0;
		int time=0;
		String time01;
		for(int n=5;n>=0;n--){
			bill=new Bill();
			example=new OrderitemExample();
		
			t= new Timestamp(d.getTime());
			System.out.println(t);
			calendar.add(Calendar.YEAR, +1);
			d=calendar.getTime();
			
			t01=new Timestamp(d.getTime());
			example.createCriteria().andTimeBetween(t, t01).andStatusEqualTo("已完成");
			oList=om.selectByExample(example);
			for(int i=0;i<oList.size();i++){
				total+=oList.get(i).getTotalprice();
			}
			bill.setCount(total);
			time01=t+"";
			time = (Integer.parseInt(time01.substring(0, 4)));
			bill.setTime(time);
			list.add(bill);
		}
		
		return list;
	}
	
}
