package com.yc.C71S3Tzggmall.web;



import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yc.C71S3Tzggmall.bean.Admin;
import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.biz.AdminBiz;
import com.yc.C71S3Tzggmall.biz.BizException;
import com.yc.C71S3Tzggmall.biz.ClothBiz;
import com.yc.C71S3Tzggmall.biz.UserBiz;
import com.yc.C71S3Tzggmall.vo.Result;





@Controller
@RequestMapping("back")
//@SessionAttributes("admin")
public class BackUserAction {
	
	@Resource
	private AdminBiz aBiz;
	
	@Resource
	private UserBiz uBiz;
	
	@Resource
	private ClothBiz cBiz;
	
	//读取配置
	@Value("${spring.servlet.multipart.location}")
	private String uploadDir;
		
	@RequestMapping("login")
	public String signIn(){
		return "/back/login";
	}
	
	@RequestMapping("index")
	public String index(Model m){
		int count=uBiz.selectPNum();
		m.addAttribute("count",count);
		List<Cloth> list=cBiz.findClothByTime();
		m.addAttribute("newProduct", (list.size()+1));
		return "/back/index";
	}
	
	@RequestMapping("register")
	public String signOn(){
		return "/back/register";
	}
	
	
	@RequestMapping("user_profile")
	public String profile(Model m,HttpServletRequest request){
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		m.addAttribute("admin",admin);
		return "/back/user_profile";
	}
	
	
	@ResponseBody
	@PostMapping("login.do")
	/**
	 * 登录
	 * @param admin
	 * @param errors
	 * @param m
	 * @return
	 */
	public Result login(@Valid Admin admin,Errors errors, Model m,HttpSession sess,HttpServletRequest request){
		
		try {
			if(errors.hasErrors()){
				return new Result(2,"表单验证错误",errors.getFieldErrors());
			}
			admin=aBiz.login(admin);
			sess.setAttribute("admin",admin);
			System.out.println(admin.getImage());
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
	/**
	 * 注册
	 * @param admin
	 * @param errors
	 * @return
	 */
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
	
	/**
	 * 上传管理员头像
	 * @param admin
	 * @param file
	 * @param m
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@PostMapping("adminImg.do")
	public String adminImg(@RequestParam("adminFile") MultipartFile file ,HttpServletRequest req, Model m) throws IllegalStateException, IOException{
		 String fileName = System.currentTimeMillis()+file.getOriginalFilename();
         String destFileName=uploadDir+"/"+fileName;
         File destFile = new File(destFileName);
         file.transferTo(destFile);//写入图片
         //获取session
         Admin admin=(Admin)req.getSession().getAttribute("admin");
         admin.setImage("/images/"+fileName);
         aBiz.updateInfo(admin);
         return "redirect:user_profile";
	}
	
	@ResponseBody
	@PostMapping("save.do")
	public Result updateInfo(Admin admin){
		int i=aBiz.updateInfo(admin);
		if(i==1){
			return new Result(1,"修改成功");
		}else{
			return new Result(0,"修改失败");
		}
		
	}
	
}
