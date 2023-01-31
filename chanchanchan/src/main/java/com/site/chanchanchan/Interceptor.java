package com.site.chanchanchan;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.site.chanchanchan.dto.Category;
import com.site.chanchanchan.service.CategoryService;

public class Interceptor implements HandlerInterceptor {
	
	@Autowired
	CategoryService categoryservice;
	 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	List<Category> catelist = categoryservice.get();
    	request.setAttribute("catelist", catelist);
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {    	
        
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }    
    
}