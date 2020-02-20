package com.yc.C71S3Tzggmall;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.yc.C71S3Tzggmall.bean.Order;
import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.biz.CartBiz;
import com.yc.C71S3Tzggmall.biz.OrderBiz;
import com.yc.C71S3Tzggmall.biz.UserBiz;

@SpringBootTest
class C71S3TzggMallApplicationTests {
	
	@Resource
	private CartBiz cBiz;
	
	@Resource
	private OrderBiz oBiz;
	
	@Resource
	private UserBiz bBiz;
	
	@Test
	void contextLoads() {
		/*List<Integer> list=new ArrayList<Integer>();
		list.add(2);
		list.add(4);
		System.out.println(cBiz.findCartByCid(list));*/
		/*User user=new User();
		user.setName("222");
		user.setPwd("1");
		bBiz.register(user);*/
		Order order=new Order();
		order.setOid("1");
		order.setTel("333");
		order.setDetail("fdgfg");
		order.setAddress("afsf");
		order.setUid("fdfd");
		oBiz.insert(order);
	}

}
