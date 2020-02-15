package com.yc.C71S3Tzggmall.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.C71S3Tzggmall.bean.Admin;
import com.yc.C71S3Tzggmall.bean.AdminExample;
import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.dao.ClothMapper;

@Service
public class ClothBiz {
	
	@Resource
	private ClothMapper cm;
	
	public List<Cloth> selectCloth(){
		List<Cloth> list=cm.selectByExample(null);
		return list;

	}
}
