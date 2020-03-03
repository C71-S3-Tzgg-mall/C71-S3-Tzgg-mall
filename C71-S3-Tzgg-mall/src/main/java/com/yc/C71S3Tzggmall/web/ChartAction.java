package com.yc.C71S3Tzggmall.web;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.C71S3Tzggmall.bean.Cloth;
import com.yc.C71S3Tzggmall.bean.Type;
import com.yc.C71S3Tzggmall.biz.ClothBiz;
import com.yc.C71S3Tzggmall.biz.OrderItemBiz;
import com.yc.C71S3Tzggmall.biz.TypeBiz;
import com.yc.C71S3Tzggmall.biz.UserBiz;
import com.yc.C71S3Tzggmall.vo.Bill;
import com.yc.C71S3Tzggmall.vo.Result;

@Controller
@RequestMapping("back")
public class ChartAction {
	
	@Resource
	private OrderItemBiz oBiz;
	
	@Resource
	private UserBiz uBiz;
	
	@Resource
	private ClothBiz cBiz;
	
	@Resource
	private TypeBiz tBiz;
	
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
	@ResponseBody
	@RequestMapping("scp.do")
	public List<Result> soleCount(){
		List<Type> tList=tBiz.selectType();
		List<Cloth> cList=null;
		List<Result> list=new ArrayList<Result>();
		Result result=null;
		for(int i=0;i<tList.size();i++){
			int total=0;
			cList=cBiz.findSoleCount(tList.get(i).getTid());
			result=new Result();
			for(int j=0;j<cList.size();j++){
				total+=cList.get(j).getSolecount();
			}
			result.setCode(total);
			result.setMsg(tList.get(i).getType());
			list.add(result);
		}
		return list;
	}
}
