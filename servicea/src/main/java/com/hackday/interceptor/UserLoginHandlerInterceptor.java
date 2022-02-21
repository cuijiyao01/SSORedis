package com.hackday.interceptor;

import com.hackday.domain.User;
import com.hackday.service.UserService;
import com.hackday.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class UserLoginHandlerInterceptor implements HandlerInterceptor {

  public static final String COOKIE_NAME = "USER_TOKEN";

  @Autowired
  private UserService userService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String token = CookieUtils.getCookieValue(request, COOKIE_NAME);
    token = StringUtils.isNotEmpty(token) ? token : request.getParameter(COOKIE_NAME);
    if (StringUtils.isEmpty(token)) {
      // 跳转到登录页面，把用户请求的url作为参数传递给登录页面。
      response.sendRedirect("http://localhost:8080/loginIndex?redirect=" + request.getRequestURL());
      // 返回false
      return false;
    }
    User user = this.userService.getUserByToken(token);
    if (Objects.isNull(user)) {
      // 跳转到登录页面，把用户请求的url作为参数传递给登录页面。
      response.sendRedirect("http://localhost:8080/loginIndex?redirect=" + request.getRequestURL()); //requestURL: http://localhost:8081/tv
      // 返回false
      return false;
    }
    // 把用户信息放入Request
    request.setAttribute("user", user);
    // 返回值决定handler是否执行。true：执行，false：不执行。
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
      Exception ex) throws Exception {
  }

}