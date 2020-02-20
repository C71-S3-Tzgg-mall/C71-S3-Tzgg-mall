package com.yc.C71S3Tzggmall.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.Orderitem;
import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.biz.ClothBiz;
import com.yc.C71S3Tzggmall.biz.OrderItemBiz;
import com.yc.C71S3Tzggmall.biz.UserBiz;

@Controller
public class CheckoutAction {
	
	@Resource
	private OrderItemBiz oBiz;
	
	@Resource
	private ClothBiz cBiz;
	
	@Resource
	private UserBiz uBiz;
	
	/**
	 * 查询订单
	 * @param m
	 * @param request
	 */
	public void checkout(Model m,int uid){
		Cloth cloth=new Cloth();
		List<Cloth> clothList=null;
		List<Orderitem> list=oBiz.showOrder(uid);
		int total=0;
		for(int i=0;i<list.size();i++){
			clothList=new ArrayList<>();
			for(int j=0;j<list.size();j++){
				cloth=cBiz.selectCloth(list.get(j).getCid());
				cloth.setCount(list.get(j).getCount());
				//订单状态
				cloth.setDetail(list.get(j).getStatus());
				cloth.setTid(list.get(j).getOiid());
				clothList.add(cloth);
				list.get(j).setList(clothList);
			}
			total+=list.get(i).getTotalprice();
		}
		m.addAttribute("totalP", total);
		m.addAttribute("order",list );
	}
	
	@RequestMapping("checkout")
	public String showOrder(Model m,HttpServletRequest request){
		User user=(User)request.getSession().getAttribute("user");
		if(user==null){
			return "login";
		}
		String addr=user.getAddress();
		getAddr(m,addr);
		m.addAttribute("user", user);
		checkout(m,user.getUid());
		return "checkout";
	}
	
	
	@RequestMapping("delOrder")
	public String delOrder(Orderitem item,Model m,HttpServletRequest request){
		User user=(User)request.getSession().getAttribute("user");
		oBiz.del(item);
		m.addAttribute("user", user);
		checkout(m,user.getUid());
		return "checkout::summary";
	}
	
	/**
	 * 截取地址
	 * @param address
	 * @return
	 */
	public static List<Map<String,String>> addressResolution(String address){
        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m=Pattern.compile(regex).matcher(address);
        String province=null,city=null,county=null,town=null,village=null;
        List<Map<String,String>> table=new ArrayList<Map<String,String>>();
        Map<String,String> row=null;
        while(m.find()){
            row=new LinkedHashMap<String,String>();
            province=m.group("province");
            row.put("province", province==null?"":province.trim());
            city=m.group("city");
            row.put("city", city==null?"":city.trim());
            county=m.group("county");
            row.put("county", county==null?"":county.trim());
            town=m.group("town");
            row.put("town", town==null?"":town.trim());
            village=m.group("village");
            row.put("village", village==null?"":village.trim());
            table.add(row);
        }
        return  table;
    }
	
	
	/**
	 * 获取地址
	 * @param m
	 * @param addr
	 */
	public void getAddr(Model m,String addr){
		List<Map<String,String>> table = addressResolution(addr);
		String province=table.get(0).get("province");
		String city=table.get(0).get("city");
		String county=table.get(0).get("county");
		String town=table.get(0).get("town")+table.get(0).get("village");
		m.addAttribute("province",province);
		m.addAttribute("city", city);
		m.addAttribute("county", county);
		m.addAttribute("town", town);
	}
	
	/**
	 * 更新地址
	 * @param request
	 * @param m
	 * @param user
	 * @return
	 */
	@PostMapping("updateAddr")
	public String updateAddr(HttpServletRequest request,Model m,User user){
		User u=(User)request.getSession().getAttribute("user");
		user.setUid(u.getUid());
		uBiz.updata(user);
		User user02=uBiz.selectUserByUid(u);
		String addr=user02.getAddress();
		getAddr(m,addr);
		m.addAttribute("user", user02);
		return "checkout::address";
	}
	
	/**
	 * 更新订单状态
	 * @param item
	 * @param m
	 * @param request
	 * @return
	 */
	@PostMapping("updateStatu")
	public String updateStatus(Orderitem item,Model m,HttpServletRequest request){
		User user=(User)request.getSession().getAttribute("user");
		item.setStatus("已支付");
		oBiz.updateStatus(item);
		System.out.println(oBiz.updateStatus(item));
		checkout(m,user.getUid());
		return "checkout::payment";
	}
}
