package com.yc.C71S3Tzggmall.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Value;


@WebListener
public class VisitCountListener implements ServletContextListener{
	
	//读取配置
	@Value("${spring.servlet.multipart.location}")
	private String uploadDir;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		Integer count=(Integer)arg0.getServletContext().getAttribute("num");
		if(null==count){
			count=0;
		}
		String path=arg0.getServletContext().getRealPath("/");
		//System.out.println(path);
		File file=new File(path);
		file=new File(file.getParent(),"num.txt");
		try {
			DataOutputStream out=new DataOutputStream(new FileOutputStream(file));
			out.writeInt(count);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		String path=arg0.getServletContext().getRealPath("/");
		//System.out.println(arg0.getServletContext().getRealPath("/"));
		File file=new File(path);
		file=new File(file.getParent(),"num.txt");
		int num=0;
		if(file.length()!=0){
			try {
				DataInputStream in=new DataInputStream(new FileInputStream(file));
				num=in.readInt();
				in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		arg0.getServletContext().setAttribute("num", num);
	}


}