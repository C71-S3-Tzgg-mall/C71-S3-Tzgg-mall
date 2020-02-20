package com.yc.C71S3Tzggmall.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.biz.BizException;
import com.yc.C71S3Tzggmall.biz.UserBiz;
import com.yc.C71S3Tzggmall.vo.Result;

@Controller
//@SessionAttributes("loginedUser")
public class UserAction {
	@Resource
	private UserBiz ubiz;

	@RequestMapping("login")
	public String login(){
		return "login";
	}
	
	
	/**
	 * 登录操作
	 * @param user
	 * @param errors
	 * @param m
	 * @return
	 */
	@ResponseBody
	@PostMapping("login")
	public Result login(@Valid User user, Errors errors, Model m,HttpSession sess) {
		try {

			if (errors.hasErrors()) {
				return new Result(2, "表单验证错误", errors.getFieldErrors());
			}
			user = ubiz.login(user);
			sess.setAttribute("user", user);
			return new Result(1, "index", user);
		} catch (BizException e) {
			e.printStackTrace();
			return new Result(0, e.getMessage());
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new Result(0, "业务繁忙，请稍后再试");
		}

	}

	@ResponseBody
	@PostMapping("register")
	/**
	 * 注册
	 * @param user
	 * @param errors
	 * @return
	 */
	public Result register(@Valid User user,Errors errors){
		try {
			if(errors.hasErrors()){
				return new Result(2,"表单验证错误",errors.getFieldErrors());
			}
			ubiz.register(user);
			return new Result(1);
		}catch(RuntimeException e){
			e.printStackTrace();
			return new Result(0,"用户名已被注册，请换一个");
		}
	}
	
}
