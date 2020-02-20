package com.yc.C71S3Tzggmall.biz;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.C71S3Tzggmall.bean.Order;
import com.yc.C71S3Tzggmall.dao.OrderMapper;

@Service
public class OrderBiz {
	
	@Resource
	private OrderMapper om;
	
	public int  insert(Order order){
		return  om.insertSelective(order);
	}
}
