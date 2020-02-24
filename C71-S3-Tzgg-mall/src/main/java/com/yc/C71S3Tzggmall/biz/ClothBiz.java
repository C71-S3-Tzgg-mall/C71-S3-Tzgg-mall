package com.yc.C71S3Tzggmall.biz;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.C71S3Tzggmall.bean.Cart;
import com.yc.C71S3Tzggmall.bean.CartExample;
import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.ClothExample;
import com.yc.C71S3Tzggmall.dao.ClothMapper;

@Service
public class ClothBiz {
	
	@Resource
	private ClothMapper cm;
	
	public List<Cloth> selectCloth(){
		List<Cloth> list=cm.selectByExample(null);
		return list;

	}
	
	/**
	 * 根据商品类型查询
	 * @param cloth
	 * @return
	 */
	public List<Cloth> findClothByCon(Cloth cloth){
		ClothExample example=new ClothExample();
		if(cloth.getTypeid()!=null){
			example.createCriteria().andTypeidEqualTo(cloth.getTypeid());
		}else if(cloth.getTid()!=null){
			example.createCriteria().andTidEqualTo(cloth.getTid());
		}else if(cloth.getCid()!=null){
			example.createCriteria().andCidEqualTo(cloth.getCid());
		}else {
			example.createCriteria().andRestcountGreaterThan(0);
		}
		List<Cloth> list=cm.selectByExample(example);
		return list;

	}
	
	/**
	 * 查询某类型商品的数量
	 * @param typeId
	 * @return
	 */
	public int findClothCount(int typeId){
		ClothExample example=new ClothExample();
		example.createCriteria().andTypeidEqualTo(typeId);
		int count=(int) cm.countByExample(example);
		return count;
	}
	
	/**
	 * 根据时间查找新品
	 * @return
	 */
	public List<Cloth> findClothByTime(){
		Date d=new Date();      
		Timestamp t = new Timestamp(d.getTime()- 7 * 24 * 60 * 60 * 1000);
		ClothExample example=new ClothExample();
		example.createCriteria().andArrivetimeGreaterThanOrEqualTo(t);
		List<Cloth> list=cm.selectByExample(example);
		return list;	
	}
	
	/**
	 * 查询新品的数量
	 * @return
	 */
	public int findClothCountByTime(){
		Date d=new Date();      
		Timestamp t = new Timestamp(d.getTime()- 7 * 24 * 60 * 60 * 1000);
		ClothExample example=new ClothExample();
		example.createCriteria().andArrivetimeGreaterThanOrEqualTo(t);
		int count=(int) cm.countByExample(example);
		return count;	
	}
	
	/**
	 * 查询有库存商品的数量
	 * @return
	 */
	public int findClothCountByRes(){
		ClothExample example=new ClothExample();
		example.createCriteria().andRestcountGreaterThan(0);
		int count=(int) cm.countByExample(example);
		return count;	
	}
	
	/**
	 *	根据商品id查找
	 * @param cid
	 * @return
	 */
	public Cloth selectCloth(int cid){
		ClothExample example=new ClothExample();
		example.createCriteria().andCidEqualTo(cid);
		Cloth cloth=cm.selectByPrimaryKey(cid);
		return cloth;
	}
	
	/**
	 * 分类查询(婴儿)
	 * @return
	 */
	public List<Cloth> findCloth(){
		ClothExample example=new ClothExample();
		example.createCriteria().andTypeidEqualTo(5);
		List<Cloth>list=cm.selectByExample(example);
		return list;
		
	}
	
	/**
	 * 查询幼儿产品
	 * @return
	 */
	public List<Cloth> selectChildCloth() {
		ClothExample example=new ClothExample();
		example.createCriteria().andTypeidNotEqualTo(5);
		List<Cloth>list=cm.selectByExample(example);
		return list;
	}
	
	/**
	 * 查询特卖产品
	 * @return
	 */
	public List<Cloth> findSpecialByTime() {
		Date d=new Date();      
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(new Date());  
		calendar.add(Calendar.MONTH, -2);
		d=calendar.getTime();
		Timestamp t = new Timestamp(d.getTime());
		ClothExample example=new ClothExample();
		//System.out.println(t);
		example.createCriteria().andArrivetimeLessThanOrEqualTo(t);
		List<Cloth>list=cm.selectByExample(example);
		return list;
	}
	
	/**
	 * (后台)添加
	 * @param cloth
	 * @return
	 */
	public int addTable(Cloth cloth){
		return cm.insert(cloth);
	}

	/**
	 * 后台删除
	 * @param cid
	 */
	public void delete(Cloth cloth) {
	   cm.deleteByPrimaryKey(cloth.getCid());
	}
	
	public List<Cloth> selectBestCloth(){
		ClothExample example=new ClothExample();
		example.createCriteria().andSolecountGreaterThanOrEqualTo(100);
		List<Cloth>list=cm.selectByExample(example);
		return list;
	}
	
	
	
}
