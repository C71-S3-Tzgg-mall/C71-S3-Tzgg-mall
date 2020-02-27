package com.yc.C71S3Tzggmall.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yc.C71S3Tzggmall.bean.Admin;
import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.Tag;
import com.yc.C71S3Tzggmall.bean.Type;
import com.yc.C71S3Tzggmall.biz.ClothBiz;
import com.yc.C71S3Tzggmall.biz.TagBiz;
import com.yc.C71S3Tzggmall.biz.TypeBiz;
import com.yc.C71S3Tzggmall.vo.Bill;
import com.yc.C71S3Tzggmall.vo.Result;

@Controller
@RequestMapping("back")
public class BackProductAction {
	@Resource
	private ClothBiz cBiz;

	@Resource
	private TypeBiz tBiz;

	@Resource
	private TagBiz taBiz;

	// 读取配置
	@Value("${spring.servlet.multipart.location}")
	private String uploadDir;

	/**
	 * 查询
	 * 
	 * @param m
	 * @return
	 */
	@RequestMapping("table")
	public String table(Model m,HttpServletRequest request){
		
		List<Cloth> list=cBiz.selectCloth();
		m.addAttribute("list",list);
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		m.addAttribute("admin",admin);
		return "/back/tables";

	}

	/**
	 * 后台添加
	 * 
	 * @param admin
	 * @param errors
	 * @return
	 */
	@ResponseBody
	@PostMapping("add.do")
	public Result add(Cloth cloth, Type type, Tag tag, @RequestParam("upimage") MultipartFile file,
			HttpServletRequest req, Model m) throws IllegalStateException, IOException {
		String fileName = System.currentTimeMillis() + file.getOriginalFilename();
		String destFileName = uploadDir + "/" + fileName;
		File destFile = new File(destFileName);
		file.transferTo(destFile);// 写入图片
		cloth.setImage("/images/" + fileName);
		//System.out.println(cloth.getArrivetime());
		cBiz.addTable(cloth);

		// tBiz.addType(type);
		// taBiz.addTag(tag);

		// return "redirect:/back/table";
		return new Result(1, "添加成功！");

	}

	/**
	 * 后台删除商品
	 * 
	 * @param cid
	 * @return
	 */
	@PostMapping("del.do")
	public String del(Cloth cloth, Model m) {
		cBiz.delete(cloth);
		List<Cloth> list = cBiz.selectCloth();
		m.addAttribute("list", list);
		return "/back/tables::tables";

	}

	/**
	 * 上传图片<后台>
	 * 
	 * @param file
	 * @param req
	 * @param m
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@PostMapping("img.do")
	public String adminImg(@RequestParam("adminFile") MultipartFile file, HttpServletRequest req, Model m)
			throws IllegalStateException, IOException {
		String fileName = System.currentTimeMillis() + file.getOriginalFilename();
		String destFileName = uploadDir + "/" + fileName;
		File destFile = new File(destFileName);
		file.transferTo(destFile);// 写入图片
		System.out.println(fileName);
		return "/back/tables";
	}
	
	@RequestMapping("shop")
	/**
	 * 分页查询
	 * @param m
	 * @param start
	 * @param size
	 * @return
	 */
	public String findClothByCon(Model m,@RequestParam(value = "start",defaultValue = "0")int start,
            @RequestParam(value = "size",defaultValue = "5")int size){
		PageHelper.startPage(start,size);
		List<Cloth> list=cBiz.selectCloth();
		PageInfo<Cloth> pageInfo = new PageInfo<Cloth>(list);
		System.out.println(pageInfo);
		m.addAttribute("page",pageInfo);
		return "table";
	}
	
	

}
