package com.hackday.service;

import com.hackday.domain.Result;
import com.hackday.domain.User;
import com.hackday.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

public class UserService {
	
	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	@Value("${SSO_DOMAIN_BASE_USRL}")
	public String SSO_DOMAIN_BASE_USRL;
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;


	// SSO_BASE_URL=http://127.0.0.1:8080
	// SSO_USER_TOKEN=/user/token/
	// SSO_PAGE_LOGIN=/login

	// http://127.0.0.1:8080/user/token/{token}
	public User getUserByToken(String token) {
		try {
			// 调用sso系统的服务，根据token取用户信息
			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
			System.out.println("json : " + json);
			// 把json转换成ItdragonResult
			Result result = Result.formatToPojo(json, User.class);
			if (null != result && result.getStatus() == 200) {
				User user = (User) result.getData();
				return user;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
