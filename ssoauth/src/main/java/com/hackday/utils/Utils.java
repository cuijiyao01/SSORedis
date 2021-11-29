package com.hackday.utils;

import com.hackday.domain.User;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

/**
 * 工具类
 * @author itdragon
 *
 */
public class Utils {

	/**
	 * 加盐加密的策略非常多,根据实际业务来
	 */
	public static void entryptPassword(User user) {
		String salt = UUID.randomUUID().toString();
		String temPassword = salt + user.getPlainPassword();
		String md5Password = DigestUtils.md5DigestAsHex(temPassword.getBytes());
		user.setSalt(salt);
		user.setPassword(md5Password);
	}

	public static boolean decryptPassword(User user, String plainPassword) {
		String temPassword = user.getSalt() + plainPassword;
		String md5Password = DigestUtils.md5DigestAsHex(temPassword.getBytes());
		return user.getPassword().equals(md5Password);
	}

	public static ImmutablePair<HttpServletRequest, HttpServletResponse> getReqAndRes(){
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes)requestAttributes).getResponse();
		return new ImmutablePair(request, response);
	}

}
