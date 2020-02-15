package com.yc.C71S3Tzggmall.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.C71S3Tzggmall.bean.Admin;
import com.yc.C71S3Tzggmall.bean.AdminExample;
import com.yc.C71S3Tzggmall.dao.AdminMapper;

@Service
public class AdminBiz {
	
	@Resource
	private AdminMapper am;
	
	
	/**
	 * 登录
	 * @param admin
	 * @return
	 * @throws BizException
	 */
	public Admin login(Admin admin) throws BizException {
		AdminExample example=new AdminExample();
		example.createCriteria()
		.andEmailEqualTo(admin.getEmail())
		.andPwdEqualTo(admin.getPwd());
		List<Admin> list=am.selectByExample(example);
		if(list.size()==0){
			throw new BizException("用户名或密码错误");
		}else{
			return list.get(0);
		}
	}
	
	/**
	 * 注册
	 * @param admin
	 * @return
	 */
	public int register(Admin admin){
		return am.insert(admin);
	}
	
	/**
	 * 修改个人信息
	 * @param admin
	 * @return
	 */
	public int updateInfo(Admin admin){
		AdminExample example=new AdminExample();
		example.createCriteria().andEmailEqualTo(admin.getEmail());
		return am.updateByExampleSelective(admin, example);
				
	}
	
}
