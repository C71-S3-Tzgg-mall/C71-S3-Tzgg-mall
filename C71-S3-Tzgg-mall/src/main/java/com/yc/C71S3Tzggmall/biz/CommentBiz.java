package com.yc.C71S3Tzggmall.biz;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.C71S3Tzggmall.bean.Comment;
import com.yc.C71S3Tzggmall.bean.CommentExample;
import com.yc.C71S3Tzggmall.dao.CommentMapper;

@Service
public class CommentBiz {
	
	@Resource
	private CommentMapper cm;
	
	/**
	 * 评论
	 * @return
	 */
	public int addComment(Comment c){
		Date d=new Date();      
		Timestamp t = new Timestamp(d.getTime());
		c.setTime(t);
		return cm.insert(c);
	}
	
	/**
	 * 根据商品id查看评论
	 * @param cid
	 * @return
	 */
	public List<Comment> findComment(int cid){
		CommentExample example=new CommentExample();
		example.createCriteria().andCidEqualTo(cid);
		List<Comment> list=cm.selectByExample(example);
		return list;
	}
	
	/**
	 * 查看好评数
	 * @return
	 */
	public int gNum(){
		CommentExample example=new CommentExample();
		example.createCriteria().andStarsEqualTo(5);
		return (int) cm.countByExample(example);
	}
	
	/**
	 * 查看最新评论
	 * @return
	 */
	public int newNum(){
		Date d=new Date();      
		Timestamp t = new Timestamp(d.getTime()- 7 * 24 * 60 * 60 * 1000);
		CommentExample example=new CommentExample();
		example.createCriteria().andTimeGreaterThanOrEqualTo(t);
		return (int)cm.countByExample(example);
	}
	
	/**
	 * 差评数
	 * @return
	 */
	public int badNum(){
		CommentExample example=new CommentExample();
		example.createCriteria().andStarsLessThanOrEqualTo(3);
		return (int) cm.countByExample(example);
	}
}
