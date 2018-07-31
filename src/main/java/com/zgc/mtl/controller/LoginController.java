package com.zgc.mtl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zgc.mtl.base.model.Json;
import com.zgc.mtl.model.SysUser;
import com.zgc.mtl.service.ISysUserService;
import com.zgc.mtl.util.MD5;
/**
 * 
 *description:登录模块 
 *Author:laoyangtou
 *2018年7月31日 下午5:34:34
 */
@Controller
public class LoginController {
	 @Autowired
	  ISysUserService loginService;

	    @RequestMapping("login")
	    @ResponseBody
	    public Json login(String loginName, String password,HttpServletRequest request)throws Exception{
	        SysUser sysUser = loginService.login(loginName.trim(),MD5.MD5Encrypt(password));
	        if (sysUser != null && sysUser.getIsActive() == 1){
	            HttpSession session = request.getSession();
	            if (session.getAttribute("loginUser") != null)
	                session.removeAttribute("loginUser");
	            session.setAttribute("loginUser",sysUser);
	            Json json = new Json(true,sysUser);
	            return json;
	        }
	        else{
	            Json json = new Json(false,"用户信息错误，请重试");
	            return json;
	        }
	    }

	    @RequestMapping("index")
	    public ModelAndView loginIndex() throws Exception{
	        return new ModelAndView("views/main");
	    }

	    @RequestMapping("logout")
	    public String logout(HttpServletRequest request){
	        HttpSession session = request.getSession();
	        if (session.getAttribute("loginUser") != null){
	            session.removeAttribute("loginUser");
	            session.invalidate();
	        }
	        return "login";
	    }
}
