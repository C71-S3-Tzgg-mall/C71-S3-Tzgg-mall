package com.yc.C71S3Tzggmall.biz;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.C71S3Tzggmall.bean.Chat;
import com.yc.C71S3Tzggmall.bean.ChatExample;
import com.yc.C71S3Tzggmall.dao.ChatMapper;

@Service
public class ChatBiz {
	
	@Resource
	private ChatMapper cm;
	
	/**
	 * 保存聊天数据
	 * @param chat
	 * @return
	 */
	public int insert(Chat chat){
		Date d=new Date();      
		Timestamp t = new Timestamp(d.getTime());
		chat.setStatus("未读");
		chat.setTime(t);
		return cm.insert(chat);
	}
	
	/**
	 * 查看聊天记录
	 * @param name
	 * @return
	 */
	public List<Chat> findChat(String name){
		ChatExample example=new ChatExample();
		example.setOrderByClause("time asc");
		example.createCriteria().andCfromEqualTo(name)
		.andCtoEqualTo("客服");
		example.or().andCfromEqualTo("客服").andCtoEqualTo(name);
		List<Chat> list=cm.selectByExample(example);
		return list;
	}
	
	/**
	 * 修改消息状态
	 * @param chat
	 * @return
	 */
	public int update(Chat chat){
		ChatExample example=new ChatExample();
		example.createCriteria().andCfromEqualTo(chat.getCfrom());
		return cm.updateByExampleSelective(chat, example);
	}
	
	/**
	 * 未读消息条数
	 * @return
	 */
	public int selectCount(String name){
		ChatExample example=new ChatExample();
		example.createCriteria().andCfromEqualTo("客服")
		.andCtoEqualTo(name).andStatusEqualTo("未读");
		return (int) cm.countByExample(example);
	}
}
