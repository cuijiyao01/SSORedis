package com.hackday.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author i531869
 * @Date 12/1/21 10:45 AM
 * @Version 1.0
 */
@Configuration
public class MyBeanConfig {

  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }
}
