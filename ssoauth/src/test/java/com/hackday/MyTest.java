package com.hackday;

import com.hackday.domain.User;
import com.hackday.service.UserService;
import com.hackday.utils.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDateTime;

/**
 * @Author i531869
 * @Date 11/28/21 4:32 PM
 * @Version 1.0
 */
@SpringBootTest
public class MyTest {

  @Autowired
  private UserService userService;

  @Autowired
  private StringRedisTemplate redisTemplate;

  @Test
  public void testSave() {
    User user = new User();
    user.setId(1l);
    user.setAccount("jason");
    user.setPlainPassword("123");
    user.setEmail("jason.cui01@sap.com");
    user.setIphone("13811802797");
    user.setUpdatedDate(LocalDateTime.now().toString());
    user.setCreatedDate(LocalDateTime.now().toString());
    Utils.entryptPassword(user);
    System.out.println(user);
    userService.save(user);
  }

  @Test
  public void testRedis() {
    redisTemplate.opsForValue().set("username", "Jason");
    String value = redisTemplate.opsForValue().get("username");
    System.out.println(value);

  }
}
