package com.hackday.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author i531869
 * @Date 11/27/21 1:35 PM
 * @Version 1.0
 */
@Configuration
public class MyConfiguration extends WebMvcConfigurationSupport {
  @Override
  protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    // 解决controller返回字符串中文乱码问题
    for (HttpMessageConverter<?> converter : converters) {
      if (converter instanceof StringHttpMessageConverter) {
        ((StringHttpMessageConverter)converter).setDefaultCharset(StandardCharsets.UTF_8);
      }
    }
  }
}
