package com.hackday;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import lombok.Synchronized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author i531869
 * @Date 2022/3/25 13:25
 * @Version 1.0
 */
public class NewTest2 {

  private int count = 0;
  Set<String> set = new HashSet<>();

  public int largestTimeFromDigits(int[] A) {
    permutate(A, 0);
    return count;
  }

  protected void permutate(int[] array, int start) {
    if (start == array.length) {
      if(build_time(array) && !set.contains(Arrays.toString(array))){
        System.out.println(Arrays.toString(array));
        set.add(Arrays.toString(array));
        count++;
      }
      return;
    }
    for (int i = start; i < array.length; ++i) {
      this.swap(array, i, start);
      this.permutate(array, start + 1);
      this.swap(array, i, start);
    }
  }

  protected boolean build_time(int[] perm) {
    int hour = perm[0] * 10 + perm[1];
    int minute = perm[2] * 10 + perm[3];
    if (hour < 24 && minute < 60){
      return true;
    }
    return false;
  }

  protected void swap(int[] array, int i, int j) {
    if (i != j) {
      int temp = array[i];
      array[i] = array[j];
      array[j] = temp;
    }
  }

  //a,b,c
  public static void main(String[] args) {
    //2->1,3->2,3->1
    // int[][] arr = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
    // Arrays.sort(arr,(sub1,sub2)->{
    //   if(sub1[0] != sub2[0]){
    //     return sub2[0] - sub1[0];
    //   }else{
    //     return sub1[1] - sub2[1];
    //   }
    // });
    // List<int[]> res = new ArrayList<>();
    // for(int[] arr1 : arr){
    //   res.add(arr1[1],arr1);
    // }
    // res.toArray(new int[res.size()][2]);
    int res = new NewTest2().largestTimeFromDigits(new int[]{2, 3, 3, 2});
    System.out.println(res);
  }
  static class Solution {

    int count=0;

    public int solution(int A, int B, int C, int D) {
      // write your code in Java SE 8
      int[] arr = new int[]{A,B,C,D};
      permutate(arr,new ArrayList<>());
      return count;
    }

    private void permutate(int[] arr, List<Integer> list){
      if(list.size() == 4){
        if(validateTime(list)){
          count++;
        }
        return;
      }
      for(int i=0;i<arr.length;i++){
        if(list.contains(arr[i])) continue;
        list.add(arr[i]);
        permutate(arr,list);
        list.remove(list.size()-1);
      }
    }

    private boolean validateTime(List<Integer> list){
      int hour = list.get(0)*10 + list.get(1);
      int min = list.get(2)*10 + list.get(3);
      if(hour < 24 && min < 60){
        return true;
      }
      return false;
    }


  }

}
