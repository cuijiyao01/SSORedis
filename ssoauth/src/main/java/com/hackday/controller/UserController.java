package com.hackday.controller;

import com.hackday.domain.Result;
import com.hackday.domain.User;
import com.hackday.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author i531869
 * @Date 11/26/21 6:07 PM
 * @Version 1.0
 */
@Controller
@RequestMapping("user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("{id}")
  public User findById(@PathVariable Long id) {
    return userService.findById(id);
  }

  @PostMapping("login")
  public ModelAndView login(String username, String password, String redirect, ModelAndView modelAndView) {
    Result result = userService.login(username, password);
    if (result.getStatus().intValue() == 200) {
      //http://localhost:8081/tv
      modelAndView.setViewName("redirect:" + redirect);
      return modelAndView;
    }
    modelAndView.addObject("errMsg", "用户名或密码错误!");
    modelAndView.setViewName("loginIndex");
    return modelAndView;
  }

  @GetMapping("/token/{token}")
  @ResponseBody
  public Result getUserByToken(@PathVariable String token) {
    Result result = null;
    try {
      result = userService.queryUserByToken(token);
    } catch (Exception e) {
      e.printStackTrace();
      result = Result.build(500, "");
    }
    return result;
  }

  @GetMapping(value = "/logout/{token}")
  public String logout(@PathVariable String token) {
    userService.logout(token); // 思路是从Redis中删除key，实际情况请和业务逻辑结合
    return "index";
  }
}
