package com.hackday;

/**
 * @Author i531869
 * @Date 2022/3/25 13:11
 * @Version 1.0
 */
public class NewTest {

  static class ListNode {
    int val;
    ListNode next;
    public ListNode(int val) {
      this.val = val;
    }
  }

  public static void main(String[] args) {
    int[] arr = new int[]{1};
    ListNode dummy = new ListNode(-1);
    ListNode cur = dummy;
    for (int i = 0; i < arr.length; i++) {
      ListNode node = new ListNode(arr[i]);
      cur.next = node;
      cur = cur.next;
    }
    cur = dummy.next;
    while(cur!=null){
      System.out.println(cur.val);
      cur = cur.next;
    }
    System.out.println("=======");
    ListNode root= reverse(dummy.next);
    while(root!=null){
      System.out.println(root.val);
      root = root.next;
    }
  }

  // 1-2-3-4 -> 4-3-2-1;
  public static ListNode reverse(ListNode root){
    ListNode prev = null;
    ListNode cur = root;
    while(cur!=null){
      ListNode next = cur.next;
      cur.next = prev;
      prev = cur;
      cur = next;
    }
    return prev;
  }
}
