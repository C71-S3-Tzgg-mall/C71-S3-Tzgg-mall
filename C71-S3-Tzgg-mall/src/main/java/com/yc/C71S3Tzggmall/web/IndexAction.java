package com.yc.C71S3Tzggmall.web;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class IndexAction {
	
	@RequestMapping(path={"/","index"})
	public String index(){
		return "index";
	}
}
