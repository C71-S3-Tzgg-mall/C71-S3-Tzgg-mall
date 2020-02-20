package com.yc.C71S3Tzggmall.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.bean.UserExample;
import com.yc.C71S3Tzggmall.dao.UserMapper;





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
}
