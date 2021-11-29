package com.hackday.service;

import com.hackday.dao.UserMapper;
import com.hackday.domain.Result;
import com.hackday.domain.User;
import com.hackday.manage.JedisClient;
import com.hackday.utils.CookieUtils;
import com.hackday.utils.JsonUtils;
import com.hackday.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author i531869
 * @Date 11/26/21 6:04 PM
 * @Version 1.0
 */
@Service
public class UserService {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private JedisClient jedisClient;

  @Value("${REDIS_USER_SESSION_KEY}")
  private String REDIS_USER_SESSION_KEY;

  @Value("${SSO_SESSION_EXPIRE}")
  private Integer SSO_SESSION_EXPIRE;

  public void save(User user) {
    userMapper.insert(user);
  }

  public User findById(Long id) {
    return userMapper.selectById(id);
  }

  public Result login(String account, String password) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("account", account);
    User user = userMapper.selectByMap(map).get(0);

    if (!Utils.decryptPassword(user, password)) {
      return Result.build(400, "账号名或密码错误");
    }
    // 生成token
    String token = UUID.randomUUID().toString();
    // 清空密码和盐避免泄漏
    String userPassword = user.getPassword();
    String userSalt = user.getSalt();
    user.setPassword(null);
    user.setSalt(null);
    // 把用户信息写入 redis
    jedisClient.set(REDIS_USER_SESSION_KEY + ":" + token, JsonUtils.objectToJson(user));
    // user 已经是持久化对象了，被保存在了session缓存当中，若user又重新修改了属性值，那么在提交事务时，此时 hibernate对象就会拿当前这个user对象和保存在session缓存中的user对象进行比较，如果两个对象相同，则不会发送update语句，否则，如果两个对象不同，则会发出update语句。
    user.setPassword(userPassword);
    user.setSalt(userSalt);
    // 设置 session 的过期时间
    jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);

    // 添加写 cookie 的逻辑，cookie 的有效期是关闭浏览器就失效。
    CookieUtils.setCookie(Utils.getReqAndRes().getLeft(), Utils.getReqAndRes().getRight(), "USER_TOKEN", token);
    // 返回token
    return Result.ok(token);
  }

  public Result queryUserByToken(String token) {
    // 根据token从redis中查询用户信息
    String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
    // 判断是否为空
    if (StringUtils.isEmpty(json)) {
      return Result.build(400, "此session已经过期，请重新登录");
    }
    // 更新过期时间
    jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
    // 返回用户信息
    return Result.ok(JsonUtils.jsonToPojo(json, User.class));
  }

  public void logout(String token) {
    jedisClient.del(REDIS_USER_SESSION_KEY + ":" + token);
  }

}
