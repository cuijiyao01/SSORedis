package com.hackday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@PropertySource("classpath:redis.properties")
public class SSOAuthApplication {
  public static void main(String[] args) {
    SpringApplication.run(SSOAuthApplication.class, args);
  }
}
