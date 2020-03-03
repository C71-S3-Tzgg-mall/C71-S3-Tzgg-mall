package com.yc.C71S3Tzggmall.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.C71S3Tzggmall.bean.Admin;
import com.yc.C71S3Tzggmall.bean.Chat;
import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.biz.ChatBiz;
import com.yc.C71S3Tzggmall.biz.UserBiz;
import com.yc.C71S3Tzggmall.vo.Result;

@Controller
@RequestMapping("back")
public class BackChatAction {
	
	@Resource
	private ChatBiz cBiz;
	
	@Resource
	private UserBiz uBiz;
	
	@RequestMapping("chat")
	public String chat(HttpServletRequest request,Model m){
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		m.addAttribute("admin",admin);
		return "/back/chat";
	}
	
	@ResponseBody
	@RequestMapping("findChatId")
	public Result findChatId(){
		List<User> list=uBiz.findUser();
		System.out.println(list);
		return new Result(list);
	}
	
	@ResponseBody
	@RequestMapping("saveChat1")
	public void save(Chat chat){
		cBiz.insert(chat);
	}
	
	@RequestMapping("findChatContent")
	public String findContent(Chat chat,Model m){
		List<Chat> list=cBiz.findChat(chat.getCto());
		m.addAttribute("chat", list);
		//System.out.println(list);
		return "/back/chat::chat";
	}
	
}
