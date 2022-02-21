package com.hackday.controller;

import com.hackday.domain.Result;
import com.hackday.domain.User;
import com.hackday.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * @author i531869
 */
@Controller
public class PageController {

	@Autowired
	private UserService userService;

	@GetMapping("/loginIndex")
	public ModelAndView showLogin(HttpServletRequest request, String redirect) {
		ModelAndView modelAndView = new ModelAndView();
		Cookie[] cookies = request.getCookies();
    if (Objects.nonNull(cookies)) {
      Arrays.stream(cookies).forEach(cookie ->{
        if (cookie.getName().equals("USER_TOKEN")) {
          Result result = userService.queryUserByToken(cookie.getValue());
          User user = (User)result.getData();
          if (Objects.nonNull(user)) {
            //http://localhost:8081/tv
            String url = redirect + "?USER_TOKEN=" + cookie.getValue();
            modelAndView.setViewName("redirect:" + url);
          }
        }
      });
      if(StringUtils.isNotEmpty(modelAndView.getViewName())){
        return modelAndView;
      }
    }
		modelAndView.addObject("redirect", redirect);
		modelAndView.setViewName("loginIndex");
		return modelAndView;
	}
	
}
