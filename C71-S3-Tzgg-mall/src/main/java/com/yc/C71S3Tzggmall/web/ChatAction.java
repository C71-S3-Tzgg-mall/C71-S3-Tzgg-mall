package com.yc.C71S3Tzggmall.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.C71S3Tzggmall.bean.Chat;
import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.biz.ChatBiz;

@Controller
public class ChatAction {
	
	@Resource
	private ChatBiz cBiz;
	
	@ResponseBody
	@RequestMapping("saveChat2")
	public void save(Chat chat){
		cBiz.insert(chat);
	}
	
	
	@RequestMapping("findChats")
	public String findChat(Model m,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		System.out.println(user.getName());
		List<Chat> list=cBiz.findChat(user.getName());
		Chat chat=new Chat();
		for(int i=0;i<list.size();i++){
			chat.setCfrom(list.get(i).getCfrom());
			chat.setStatus("已读");
			cBiz.update(chat);
		}
		
		m.addAttribute("chat", list);
		return "index::chat";
	}
	
	/**
	 * 修改未读消息的数量
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping("updChat")
	public String updChat(Model m,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		int chatCount=cBiz.selectCount(user.getName());
		m.addAttribute("chatCount",chatCount);
		return "index::updateChat";
	}
	
	
}
