package com.hackday.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * 用户实体类
 * @author itdragon
 *
 */
@Data
public class User {

	private Long id;						// 自增长主键

	private String account;					// 登录的账号

	private String userName;				// 注册的昵称

	@TableField(exist = false)
	private String plainPassword;      // 登录时的密码，不持久化到数据库

	private String password;				// 加密后的密码

	private String salt;					// 用于加密的盐

	private String iphone;					// 手机号

	private String email;					// 邮箱

	private String platform;				// 用户来自的平台

	private String createdDate;				// 用户注册时间

	private String updatedDate;				// 用户最后一次登录时间

}
