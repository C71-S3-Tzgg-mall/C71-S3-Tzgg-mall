package com.yc.C71S3Tzggmall.biz;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.C71S3Tzggmall.bean.Cart;
import com.yc.C71S3Tzggmall.bean.Cloth;
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
	
	
	@Resource
	private OrderItemBiz obiz;
	
	@Resource
	private ClothBiz cbiz;
	
	@Resource
	private ClothBiz clBiz;
	
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
			System.out.println(c.getCount());
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
	 * 查询已发货的订单数
	 * @return
	 */
	public int findCountByP(){
		OrderitemExample example=new OrderitemExample();
		example.createCriteria().andStatusEqualTo("已发货");
		return (int) om.countByExample(example);
	}
	
	
	/**
	 * 查询取消的订单数
	 * @return
	 */
	public int findCountByQ(){
		OrderitemExample example=new OrderitemExample();
		example.createCriteria().andStatusEqualTo("取消");
		return (int) om.countByExample(example);
	}
	/**
	 * 查询订单数
	 * @return
	 */
	public List<Bill> findMonthCount(){
		List<Bill> list=new ArrayList<>();
		Bill bill=null;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,24);
		calendar.set(Calendar.MINUTE,00);
		calendar.set(Calendar.SECOND,00);
		Date d=new Date(); 
		d=calendar.getTime();
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
			time01=t01+"";
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
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,24);
		calendar.set(Calendar.MINUTE,00);
		calendar.set(Calendar.SECOND,00);
		Date d=new Date(); 
		d=calendar.getTime();
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
			//System.out.println(t);
			example.createCriteria().andTimeBetween(t01, t).andStatusEqualTo("已完成");
			oList=om.selectByExample(example);
			for(int i=0;i<oList.size();i++){
				total+=oList.get(i).getTotalprice();
			}
			//System.out.println(t01+"====="+t+"======"+total);
			bill.setCount(total);
			total=0;
			time01=t01+"";
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
			//System.out.println(t);
			calendar.add(Calendar.YEAR, +1);
			d=calendar.getTime();
			
			t01=new Timestamp(d.getTime());
			example.createCriteria().andTimeBetween(t, t01).andStatusEqualTo("已完成");
			oList=om.selectByExample(example);
			for(int i=0;i<oList.size();i++){
				total+=oList.get(i).getTotalprice();
			}
			bill.setCount(total);
			total=0;
			time01=t+"";
			time = (Integer.parseInt(time01.substring(0, 4)));
			bill.setTime(time);
			list.add(bill);
		}
		
		return list;
	}
	
	/**
	 * 通过uid查询
	 */
	public List<Orderitem> selectOrderByuid(Orderitem orderitem){
		OrderitemExample example=new OrderitemExample();
		example.createCriteria().andUidEqualTo(orderitem.getUid());
		
		return om.selectByExample(example);

		
	}
	/**
	 * 查询订单
	 * @return
	 */
	public List<Orderitem> selectOrderitem(){
		List<Orderitem> list=om.selectByExample(null);
		return list;
	}

	/**
	 * 删除
	 * @param cloth
	 */             
	public int delete(Orderitem orderitem){
		OrderitemExample example =new OrderitemExample();
        //example.createCriteria().andCidEqualTo(orderitem.getCid()).andUidEqualTo(orderitem.getUid());
		example.createCriteria().andCidEqualTo(orderitem.getCid());
		return om.deleteByExample(example);
	}
	
	/**
	 * 显示后台订单信息
	 * @return
	 */
	public Cloth showbackOrder(){
		List<Orderitem> olist=om.selectByExample(null);
		List<Cloth> list=null;
		
		Cloth cloth=new Cloth();
		
		for (int i = 0; i < olist.size(); i++) {
			list=new ArrayList<Cloth>();
			for (int j = 0; j < olist.size(); j++) {
				cloth=clBiz.selectCloth(olist.get(j).getCid());
				cloth.setDetail(olist.get(j).getOid());
				cloth.setRealprice(olist.get(j).getTotalprice());
				cloth.setStatus(olist.get(j).getStatus());
				list.add(cloth);
			}
		}
		cloth.setList(list);
		return cloth;
	}
	
	/**
	 * 修改后台状态
	 * @param item
	 * @return
	 */
	public int reStatus(Orderitem item){
		OrderitemExample example=new OrderitemExample();
		example.createCriteria().andOidEqualTo(item.getOid());
		return om.updateByExampleSelective(item, example);
	}
	
	/**
	 * 修改状态
	 * @param item
	 * @return
	 */
	public int UpdStatus(Orderitem item){
		OrderitemExample example=new OrderitemExample();
		example.createCriteria().andOiidEqualTo(item.getOiid());
		return om.updateByExampleSelective(item, example);
	}
	
	/**
	 *根据oiid查询
	 * @param oiid
	 * @return
	 */
	public Orderitem selectByOid(int oiid){
		return om.selectByPrimaryKey(oiid);
	}
	
}
