package com.yc.C71S3Tzggmall.biz;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.C71S3Tzggmall.bean.OrderitemExample;
import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.bean.UserExample;
import com.yc.C71S3Tzggmall.dao.UserMapper;
import com.yc.C71S3Tzggmall.vo.Bill;





//业务层
@Service
public class UserBiz {
	@Resource
	private UserMapper um;
	
	public User login(User user) throws BizException{
		UserExample example=new UserExample();
		example.createCriteria()
		.andNameEqualTo(user.getName())
		.andPwdEqualTo(user.getPwd());
		
		List<User> list=um.selectByExample(example);
		
		if(list.size()==0){
			throw new BizException("用户名或密码错误");
		}else{
			return list.get(0);
		}
	}
	
	public User register(User user){
		Date d=new Date(); 
		Timestamp time= new Timestamp(d.getTime());
		user.setTime(time);
		um.insert(user);
		return user;
	}
	
	/**
	 * 查询用户个数
	 * @return
	 */
	public int selectPNum(){
		return (int) um.countByExample(null);
	}
	
	public int updata(User user){
		UserExample example=new UserExample();
		example.createCriteria().andUidEqualTo(user.getUid());
		return um.updateByExampleSelective(user, example);
	}
	
	public User selectUserByUid(User user){
		return um.selectByPrimaryKey(user.getUid());
	}
	
	/**
	 * 查询会员人数
	 * @return
	 */
	public int selectTNum(){
		UserExample example=new UserExample();
		example.createCriteria().andTypeEqualTo("会员");
		return (int) um.countByExample(example);
	}
	
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
		UserExample example=null;
		int count=0;
		int time=0;
		String time01;
		for(int n=6;n>=0;n--){
			example=new UserExample();
			t= new Timestamp(d.getTime()- n * 24 * 60 * 60 * 1000);
			t01=new Timestamp(d.getTime()- (n+1) * 24 * 60 * 60 * 1000);
			example.createCriteria().andTimeBetween(t01, t);
			count=(int) um.countByExample(example);
			bill=new Bill();
			bill.setCount(count);
			time01=t01+"";
			time = (Integer.parseInt(time01.substring(8, 10)));
			bill.setTime(time);
			list.add(bill);
		}
		
		return list;
	}
}
