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
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;
import com.yc.C71S3Tzggmall.bean.Cart;
import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.Orderitem;
import com.yc.C71S3Tzggmall.bean.User;
import com.yc.C71S3Tzggmall.biz.CartBiz;
import com.yc.C71S3Tzggmall.biz.ClothBiz;
import com.yc.C71S3Tzggmall.biz.OrderItemBiz;
import com.yc.C71S3Tzggmall.biz.UserBiz;
import com.yc.C71S3Tzggmall.vo.Result;


@Controller
public class CheckoutAction {
	
	@Resource
	private OrderItemBiz oBiz;
	
	@Resource
	private ClothBiz cBiz;
	
	@Resource
	private UserBiz uBiz;
	
	@Resource
	private CartBiz cartBiz;
	
	private String oid;
	
	
	
	/**
	 * 查询订单
	 * @param m
	 * @param request
	 */
	public void checkout(Model m){
		Cloth cloth=new Cloth();
		List<Cloth> clothList=null;
		List<Orderitem> list=oBiz.showOrder(oid);
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
	
	@ResponseBody
	@RequestMapping("settle")
	public Result order(HttpServletRequest request,Model m){
		User user=(User) request.getSession().getAttribute("user");
		String cids = request.getParameter("cids");
		oid=request.getParameter("oid");
		if(cids!=null){
			// 分割字符串
			String[] infos = cids.split(",");
			List<Integer> cidsList = new ArrayList<Integer>();
			for (String cid : infos) {
				if (StringUtil.isNotEmpty(cids)) {
					cidsList.add(Integer.parseInt(cid));
				}
			}
			
			int uid=user.getUid();
			oBiz.settle(cidsList, uid,oid);
			Cart c=new Cart();
			for (int i=0;i<cidsList.size();i++) {
				c.setUid(user.getUid());
				c.setCid(cidsList.get(i));
				cartBiz.delete(c);
			}
		}
		checkout(m);
		return new Result(1,"checkout");
	}
	
	@RequestMapping("checkout")
	public String showOrder(Model m,HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		oid=request.getParameter("oid");
		if(user!=null){
			Cloth cloth=cartBiz.showCart(user.getUid());
			Cart cart=new Cart();
			cart.setUid(user.getUid());
			List<Cart> cartList=cartBiz.findCartByUid(cart);
			int total=0;
			for (Cart c : cartList) {
				total += c.getCount()*c.getPrice();
			}
			m.addAttribute("total",total);
			m.addAttribute("cartSize", cartList.size());
			m.addAttribute("cartList",cloth);
			checkout(m);
			m.addAttribute("user", user);
			if(user.getAddress()!=null){
				getAddr(m,user.getAddress());
			}
		}
		
		return "checkout";
	}
	
	
	@RequestMapping("delOrder")
	public String delOrder(Orderitem item,Model m,HttpServletRequest request){
		User user=(User)request.getSession().getAttribute("user");
		oBiz.del(item);
		m.addAttribute("user", user);
		checkout(m);
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
		if(u!=null){
			user.setUid(u.getUid());
			uBiz.updata(user);
			User user02=uBiz.selectUserByUid(u);
			String addr=user02.getAddress();
			//System.out.println(addr);
			getAddr(m,addr);
			m.addAttribute("user", user02);
		}
		
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
		oBiz.UpdStatus(item);
		checkout(m);
		return "checkout::payment";
	}
	
	@PostMapping("updateShip")
	public String updateShip(Model m,HttpServletRequest request){
		User user=(User)request.getSession().getAttribute("user");
		checkout(m);
		return "checkout::shipping";
	}
	@PostMapping("updatePay")
	public String updatePay(Model m,HttpServletRequest request){
		User user=(User)request.getSession().getAttribute("user");
		checkout(m);
		return "checkout::payment";
	}
}
