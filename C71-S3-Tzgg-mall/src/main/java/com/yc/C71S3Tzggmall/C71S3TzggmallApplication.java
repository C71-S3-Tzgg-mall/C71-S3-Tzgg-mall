package com.yc.C71S3Tzggmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


@SpringBootApplication
@ServletComponentScan
@MapperScan("com.yc.C71S3TZggmall")
public class C71S3TzggmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(C71S3TzggmallApplication.class, args);
	}
	
	@Bean
	public ServerEndpointExporter serverEndpointExporter(){
		return new ServerEndpointExporter();
	}
}
