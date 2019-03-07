package com.test.jwt.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.test.jwt.common.Result;
import com.test.jwt.util.JwtUtil;

import io.jsonwebtoken.Claims;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("")
public class LoginController {

	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject login(String id,String username,String password){
		//1.前端发送数据
		//2.后端创建JWT
		//3.返回给前端
		String jwtJson;
		System.out.println("username:"+username+" === password:"+password);
		try{
			jwtJson = JwtUtil.createJWT("jwt",username,600000);
	        return Result.OK.makeResult(jwtJson);
		}catch(Exception e){
			return Result.FALSE.toJson();
		}
	}
	
	@RequestMapping(value="/check",method=RequestMethod.POST)
	@ResponseBody
	public void check(HttpServletRequest request,HttpServletResponse response,String username,String password){
		System.out.println("验证通过");
	}
	
	
}
