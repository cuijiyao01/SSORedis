package com.hackday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author i531869
 * @Date 11/27/21 5:59 PM
 * @Version 1.0
 */
@SpringBootApplication
@PropertySource("classpath:resource.properties")
public class ServiceAApplication {
  public static void main(String[] args) {
    SpringApplication.run(ServiceAApplication.class, args);
  }
}
