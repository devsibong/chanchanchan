package com.site.chanchanchan.controller;

import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.chanchanchan.service.OrderListService;

@RequestMapping("/chart")
@Controller
public class ChartController {	
	
	@Autowired
	OrderListService olservice;
	
	String dir ="chart/";

	@RequestMapping("/order")
	public String order(Model model) {
		model.addAttribute("center",dir+"chart1");
		return "main";
	}
	
	@ResponseBody
	@RequestMapping("/chart1")
	public Object chart1(int sm, int em) {
		// [1,2,3,4,5]
		// [{},{}]
		// {'m':[1,2,3,4,5], 'data':[{'name':'Female','data':[]},{'name':'Male','data':[]}]}
		
		JSONObject jo = new JSONObject();
		
		JSONArray ja1 = new JSONArray();
		JSONArray ja2 = new JSONArray();
		
		for(int i = sm; i <= em;i++) {
			int a=0;
			ja1.add(i);
			Random r = new Random();
			try {
				a= olservice.monthsales(i);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			ja2.add(Math.round(a/10000));
		}
		
		jo.put("m", ja1);
		
		JSONArray ja = new JSONArray();
		JSONObject jo1 = new JSONObject();
		jo1.put("name", "매출액");
		jo1.put("data", ja2);
		ja.add(jo1);
		
		jo.put("data", ja);
		
		return jo;
	}
}
