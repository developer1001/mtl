package com.zgc.mtl.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zgc.mtl.base.model.Json;
import com.zgc.mtl.common.util.MD5;
import com.zgc.mtl.model.SysUser;
import com.zgc.mtl.service.ISysUserService;
/**
 * 
 *description: 系统用户
 *Author:laoyangtou
 *2018年7月31日 下午3:53:04
 */
@Controller
public class SysUserController {
    @Autowired
    ISysUserService userService;

    /**
     * 根据用户id查询
     * @param id
     */
    @RequestMapping("getUserById")
    @ResponseBody
    public Json getUser(String id){
        List<SysUser> list =null;
		if (StringUtils.isNotBlank(id)){
            SysUser user = userService.findById(Integer.parseInt(id));
            Json json = new Json(true,1,user);
            return json;
        }
        else {
    		 list = userService.findAllObj();
        	 Json json = new Json(true,list.size(),list);
             return json;
        }
    }
    
    @RequestMapping("deleteUserById")
    @ResponseBody
    public Json deleteUserById(String id){
        if (StringUtils.isNotBlank(id)){
        int a =   userService.deleteById(Integer.parseInt(id));
          Json json = new Json();
          if (a == 1){
              json.setSuccess(true);
              json.setData(a);
              json.setMsg("id为 "+id+" 的用户删除成功");
          }
          else if (a == 0){
              json.setSuccess(false);
              json.setData(a);
              json.setMsg("id为 "+id+" 的用户不存在，无法删除");
          }
          return json;
        }
        return null;
    }

    @RequestMapping("addUser")
    @ResponseBody
    public Json addUser(SysUser user){
    	user.setPassword(MD5.MD5Encrypt(user.getPassword()));
        int a = userService.add(user);
        Json json = new Json();
        json.setSuccess(true);
        json.setTotal(1);
        json.setMsg("添加新用户成功,主键：" + user.getUserId());
        return json;
    }

    @RequestMapping("updateUser")
    @ResponseBody
    public Json updateUser(SysUser user){
        user.setPassword(MD5.MD5Encrypt(user.getPassword()));
        int a = userService.update(user);
        int userId = user.getUserId().intValue();
        if (a == 1){
            Json json = new Json();
            json.setSuccess(true);
            json.setMsg("id为"+userId+"的用户信息修改成功");
            return json;
        }
        return null;
    }

    @RequestMapping("toNewPage")
    public String toNewPage(){
        //加上前缀和后缀，组成新的url地址，页面跳转
        return "test";
    }
    
    @PostMapping("getUser")
    @ResponseBody
    public SysUser getUser(@RequestBody Map<String, String> param) {
    	SysUser login = userService.login(param.get("loginName"), param.get("password"));
		return login;
    }
}
