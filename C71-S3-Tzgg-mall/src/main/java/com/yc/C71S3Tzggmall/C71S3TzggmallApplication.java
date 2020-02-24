package com.yc.C71S3Tzggmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan
@MapperScan("com.yc.C71S3TZggmall")
public class C71S3TzggmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(C71S3TzggmallApplication.class, args);
	}
	
	
}
