package com.yc.C71S3Tzggmall.web;



import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yc.C71S3Tzggmall.bean.Admin;
import com.yc.C71S3Tzggmall.biz.AdminBiz;
import com.yc.C71S3Tzggmall.biz.BizException;
import com.yc.C71S3Tzggmall.vo.Result;





@Controller
@RequestMapping("back")
//@SessionAttributes("admin")
public class AdminAction {
	
	@Resource
	private AdminBiz aBiz;
	
	@RequestMapping("login")
	public String signIn(){
		return "/back/login";
	}
	
	@RequestMapping("index")
	public String index(){
		return "/back/index";
	}
	
	@RequestMapping("register")
	public String signOn(){
		return "/back/register";
	}
	
	@RequestMapping("user_profile")
	public String profile(Model m){
		Admin admin=new Admin();
		admin.setEmail("2085173885@qq.com");
		admin.setPwd("b");
		try {
			admin=aBiz.login(admin);
			if(admin.getImage()==null){
				admin.setImage("http://placehold.it/230x230&amp;text=Photo");
			}
		} catch (BizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.addAttribute("admin",admin);
		return "/back/user_profile";
	}
	
	/*@ResponseBody
	@RequestMapping("user_profile.do")
	public Result profile(Model m,@SessionAttribute("admin") Admin admin){
		
		return new result();
	}*/
	@ResponseBody
	@PostMapping("login.do")
	public Result login(@Valid Admin admin,Errors errors, Model m){
		
		try {
			if(errors.hasErrors()){
				return new Result(2,"表单验证错误",errors.getFieldErrors());
			}
			admin=aBiz.login(admin);
			m.addAttribute("admin",admin);
			return new Result(1,"index",admin);
		} catch (BizException e) {
			e.printStackTrace();
			return new Result(0,e.getMessage());
		}catch(RuntimeException e){
			e.printStackTrace();
			return new Result(0,"业务繁忙，稍后再试");
		}
	}
	
	@ResponseBody
	@PostMapping("register.do")
	public Result register(@Valid Admin admin,Errors errors){
		try {
			if(errors.hasErrors()){
				return new Result(2,"表单验证错误",errors.getFieldErrors());
			}
			aBiz.register(admin);
			return new Result(1);
		}catch(RuntimeException e){
			e.printStackTrace();
			return new Result(0,"请输入正确的邮箱名！");
		}
		
	}
	
	@ResponseBody
	@PostMapping("save.do")
	public Result updateInfo(/*@RequestParam("adminFile") MultipartFile file ,*/Admin admin) throws IllegalStateException, IOException{
		/*String fn=file.getOriginalFilename();//获取文件名1.png
		if(fn.isEmpty()==false){
			file.getName();//获取字段名  name="file"
			//file.getInputStream();//获取输入流
			
			String path="adminImg/"+fn;//web 路径转成磁盘路径
			
			System.out.println(path);
			File adminImg=new File(path);
			file.transferTo(adminImg);
		}else{
			
		}*/
		int i=aBiz.updateInfo(admin);
		if(i==1){
			return new Result(1,"修改成功");
		}else{
			return new Result(0,"修改失败");
		}
		
	}
	
}
