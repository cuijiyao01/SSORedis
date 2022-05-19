package com.hackday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author i531869
 * @Date 2022/3/28 13:13
 * @Version 1.0
 */
public class NewTest3 {

  private static AtomicInteger a = new AtomicInteger(0);
  private static volatile int b = 1;

  public static String getShiftedString(String s, int leftShifts, int rightShifts) {
    // Write your code here
    int n = s.length();
    if(n==1) return s;
    leftShifts = leftShifts%n;
    rightShifts = rightShifts%n;
    char[] chs = s.toCharArray();
    StringBuffer sb = new StringBuffer();

    sb.append(chs, leftShifts, n);
    sb.append(chs, 0,leftShifts);
    char[] chs2 = sb.toString().toCharArray();
    StringBuffer sb2 = new StringBuffer();
    sb2.append(chs2, n-rightShifts, n);
    sb2.append(chs2, 0, n-rightShifts);
    return sb2.toString();
  }

  public static void main(String[] args) throws InterruptedException {
    List<String> list = new ArrayList<>();
    list.add("jason");
    list.add("james");
    list.add("jackson");
    String.join(",", list);
    System.out.println(list);
    Map<Integer, String> map = new HashMap<>();
    // getShiftedString("abcdefg", 2, 4);
  }

  static class Singleton {
    private static Singleton singleton;

    public static Singleton getSingleton() {
      if (singleton == null) {
        synchronized (Singleton.class) {
          if (singleton == null) {
            return new Singleton();
          }
          return singleton;
        }
      }
      return singleton;
    }
  }
}
