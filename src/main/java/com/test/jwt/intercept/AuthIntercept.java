package com.test.jwt.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.test.jwt.util.JwtUtil;

import io.jsonwebtoken.Claims;
public class AuthIntercept implements HandlerInterceptor{
	
	//请求前执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
			//System.out.println("请求前执行："+request.getRequestURI());
		
		//4.前端将token放入header中
		//5.后端验证token
		//6.根据token的正确性执行不同逻辑
		String token = request.getHeader("token");
		System.out.println(token);
		
		try {
			//如果jwt过期了会抛出异常
			Claims parseJWT = JwtUtil.parseJWT(token);
			
			System.out.println("没有过期");
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("过期了");
			e1.printStackTrace();
		}
			return true;
	}

	//请求后执行
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
//			System.out.println("请求后执行："+request.getRequestURI());
	}

	//最终执行
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		System.out.println("最终执行："+request.getRequestURI());
		
	}

}
