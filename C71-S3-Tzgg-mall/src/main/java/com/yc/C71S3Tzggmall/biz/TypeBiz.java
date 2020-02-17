package com.yc.C71S3Tzggmall.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.yc.C71S3Tzggmall.bean.Type;
import com.yc.C71S3Tzggmall.dao.TypeMapper;

@Service
public class TypeBiz {
	
	@Resource
	private TypeMapper tm;
	
	public List<Type> selectType(){
		List<Type> typeList=tm.selectByExample(null);
		return typeList;

	}
}
