package com.hackday;

import lombok.SneakyThrows;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * @Author i531869
 * @Date 2022/3/22 20:29
 * @Version 1.0
 */
public class TestFuture {
  public static void main(String[] args) throws Exception {
    // System.out.println("回家吃饭");
    // ExecutorService pool = Executors.newFixedThreadPool(2);
    // Future future = pool.submit(() ->{
    //   try {
    //     Thread.sleep(3000);
    //   } catch (InterruptedException e) {
    //     e.printStackTrace();
    //   }
    //   return "火腿炒蛋";
    // });
    // pool.shutdown();
    // System.out.println("等待....");
    // System.out.println(future.get());
    // System.out.println("吃饭");
    // System.out.println("等待发工资....");
    System.out.println(ForkJoinPool.getCommonPoolParallelism());
    System.out.println(ForkJoinPool.commonPool().getPoolSize());
    System.out.println(Runtime.getRuntime().availableProcessors());
    CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
      System.out.println(Thread.currentThread().getId());
      sleep(1000);
      return 2000;
    }).thenApply(salary -> {
          System.out.println(Thread.currentThread().getId() + " 税前:" + salary);
          TestFuture.sleep(2000);
          return "扣除五险一金: " + String.valueOf(Integer.valueOf(salary) * 0.82);
        });
      System.out.println(Thread.currentThread().getId()+": "+completableFuture1.join());
      // CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
      //   TestFuture.sleep(2000);
      //   return "基本工资";
      // }).thenCombine(CompletableFuture.supplyAsync(() -> {
      //   TestFuture.sleep(1000);
      //   return "绩效工资";
      // }), (a, b) -> a + "和" + b);
      // System.out.println(completableFuture.join());
    CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
      sleep(3000);
      System.out.println(Thread.currentThread().getId() + " 700路公交正在赶来");
      return "700路公交";
    }).applyToEitherAsync(CompletableFuture.supplyAsync(() -> {
      sleep(2000);
      System.out.println(Thread.currentThread().getId() + " 800路公交正在赶来");
      if (true) {
        throw new RuntimeException();
      }
      return "800路公交";
    }), a -> {
      System.out.println(a+"来了");
      if (true) {
        throw new RuntimeException();
      }
      return a;
    }).exceptionally(e -> {
      System.out.println(e.getMessage());
      System.out.println("公安出问题了,叫出租车");
      return "出租车";
    });
    System.out.println(completableFuture.join());
  }

  private static void sleep(int i) {
    try {
      Thread.sleep(i);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
