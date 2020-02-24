package com.yc.C71S3Tzggmall.web;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yc.C71S3Tzggmall.biz.OrderItemBiz;
import com.yc.C71S3Tzggmall.biz.UserBiz;
import com.yc.C71S3Tzggmall.vo.Bill;

@Controller
@RequestMapping("back")
public class ChartAction {
	
	@Resource
	private OrderItemBiz oBiz;
	
	@Resource
	private UserBiz uBiz;
	
	@RequestMapping("orderCount.do")
    @ResponseBody
    public List<Bill> orderCount(){
        List<Bill> list=oBiz.findMonthCount();
       // System.out.println(list.get(0).getCount());
        return list;
	}
	
	
	@RequestMapping("userCount.do")
    @ResponseBody
    public List<Bill> userCount(){
        List<Bill> list=uBiz.findMonthCount();
        //System.out.println(list.get(0).getCount());
        return list;
	}
	
	@RequestMapping("opm.do")
    @ResponseBody
    public List<Bill> opm(){
        List<Bill> list=oBiz.findPriceW();
       // System.out.println(list.get(0).getCount());
        return list;
	}
	
	@RequestMapping("omb.do")
    @ResponseBody
    public List<Bill> omb(){
        List<Bill> list=oBiz.findPriceM();
       // System.out.println(list.get(0).getCount());
        return list;
	}
	
}
