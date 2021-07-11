package com.fajar.employeedataapi.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomHandlerInterceptor implements HandlerInterceptor {
 
	@Autowired
	ObjectMapper objectMapper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("Pre handle");
//		System.out.println(getBody(request));
		return true;
	}@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("afterCompletion: {}", handler);
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	} 
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("postHandle: {}", handler);
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}
