package com.hackday;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Author i531869
 * @Date 2022/3/8 21:15
 * @Version 1.0
 */
public class Test2 {

  class Solution {
    public int[] searchRange(int[] nums, int target) {
      int first = getFirst(nums, target);
      int last = getLast(nums, target);
      if (first > last)
        return new int[]{-1, -1};
      return new int[]{first, last};
    }

    private int getFirst(int[] nums, int target) {
      int lo = 0, hi = nums.length - 1;
      while (lo < hi) {
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] <= target) {
          lo = mid;
        } else {
          hi = mid - 1;
        }
      }
      return lo;
    }

    private int getLast(int[] nums, int target) {
      int lo = 0, hi = nums.length - 1;
      while (lo < hi) {
        int mid = lo + (hi - lo) / 2;
        if (nums[mid] < target) {
          lo = mid + 1;
        } else {
          hi = mid;
        }
      }
      return lo;
    }
  }

  public static void main(String[] args) throws InterruptedException {
    int[] arr = new int[]{5, 41, 2, 12, 31, 31, 9, 7, 0, 12};
    quickSort(arr,0,arr.length-1);
    System.out.println(Arrays.toString(arr));
  }

  public static void quickSort(int[] nums, int lo,int hi){
    if(lo<hi){
      int j = partition(nums,lo,hi);
      quickSort(nums,lo,j-1);
      quickSort(nums,j+1,hi);
    }
  }

  private static int partition(int[] nums, int lo, int hi) {
    int pivot = nums[lo];
    while(lo<hi){
      while(lo<hi && nums[hi]>pivot) hi--;
      nums[lo] = nums[hi];
      while(lo<hi && nums[lo]<=pivot) lo++;
      nums[hi] = nums[lo];
    }
    nums[lo] = pivot;
    return lo;
  }

  public int subarraySum(int[] nums, int k) {
    // BigInteger.valueOf()
    Map<Integer,Integer> map = new HashMap<>();
    map.put(0,1);
    int count=0;
    int preSum=0;
    for(int i=0;i<nums[i];i++){
      preSum+=nums[i];
      if(map.containsKey(preSum-k)){
        count+=map.get(preSum-k);
      }
      map.put(preSum, map.getOrDefault(preSum,0)+1);
    }
    return count;
  }
  public int numIslands(char[][] grid) {
    int row = grid.length;
    int col = grid[0].length;
    int count = 0;
    for(int i=0;i<row;i++){
      for(int j=0;j<col;j++){
        if(grid[i][j] == '1'){
          dfs(grid,i,j);
          count++;
        }
      }
    }
    return count;
  }

  private void dfs(char[][] grid,int i,int j){
    if(i<0 || j<0 || i>=grid.length || j>=grid[0].length){
      return;
    }
    if(grid[i][j] == '1'){
      grid[i][j] = '0';
    }
    dfs(grid,i-1,j);
    dfs(grid,i+1,j);
    dfs(grid,i,j-1);
    dfs(grid,i,j+1);
  }
}
