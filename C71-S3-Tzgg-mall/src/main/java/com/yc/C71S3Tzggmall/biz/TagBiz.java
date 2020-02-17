package com.yc.C71S3Tzggmall.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.C71S3Tzggmall.bean.Tag;
import com.yc.C71S3Tzggmall.dao.TagMapper;

@Service
public class TagBiz {
	
	@Resource
	private TagMapper tm;
	
	public List<Tag> selectTag(){
		List<Tag> tagList=tm.selectByExample(null);
		return tagList;

	}
}