package com.site.chanchanchan.controller;

import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/chart")
@Controller
public class ChartController {	
	
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
		JSONArray ja3 = new JSONArray();
		
		for(int i = sm; i <= em;i++) {
			ja1.add(i);
			Random r = new Random();
			int num1 = r.nextInt(100)+1;
			int num2 = r.nextInt(100)+1;
			ja2.add(num1);
			ja3.add(num2);
		}
		
		jo.put("m", ja1);
		
		JSONArray ja = new JSONArray();
		JSONObject jo1 = new JSONObject();
		jo1.put("name", "Female");
		jo1.put("data", ja2);
		ja.add(jo1);
		JSONObject jo2 = new JSONObject();
		jo2.put("name", "Male");
		jo2.put("data", ja3);
		ja.add(jo2);
		
		jo.put("data", ja);
		
		return jo;
	}
}
