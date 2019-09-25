package com.whjlim.algorithm;

import java.util.stream.Stream;

/**
 * Created by BlackWatch on 2019/9/20 9:27
 */
public class FirstHander {
    public int ip2Int(String ipStr){
        String[] ips = ipStr.trim().split("\\.");//哈哈，这里的参数是一个正则表达式，.需要转义
        int res = 0;
        for(int i = 0; i < ips.length; i++){
            int ipNums = Integer.valueOf(ips[i]) << 8*(3-i);
            res |= ipNums;
        }
        return res;
    }

    public String ip2String(int ips){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 4; i++){
            int ipNums = ips & (255 << 8*(3-i));
            sb.append(ipNums>>>8*(3-i));
            if(i != 3){
                sb.append(".");
            }
        }
        return sb.toString();
    }

    public class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode begin = head;
        ListNode newHead = null;
        ListNode end = head;
        int num = 1;
        boolean flag = false;
        while(head.next!=null){
            num++;
            if(num == k){
                ListNode nextBegin = head.next.next;
                if(!flag){
                    flag = true;
                    newHead = reverseList(begin, head.next);
                }else{
                    reverseList(begin, head.next);
                }
                begin = nextBegin;
                num = 1;
                head = begin;
            }else{
                head = head.next;
            }
        }
        return newHead;
    }
    public ListNode reverseList(ListNode head, ListNode tail){
        ListNode newHead = head;
        ListNode pre = head;
        ListNode next = head.next;
        head.next = tail.next;
        while(next != null && next != tail){
            ListNode nnext = next.next;
            next.next = pre;
            if(pre != head)
                pre.next = null;
            pre = next;
            next = nnext;
        }
        tail.next = pre;
        return tail;
    }

    public void test(){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        reverseKGroup(head,2);
    }

    public static void main(String[] args) {

        Stream.of("Apsara","2019","9.25 - 9.27","Digital Economy")
                .map(x -> 57 == x.charAt(0) ? "Here is the" :
                        50 == x.charAt(0) ? x.concat(":") : x)
                .reduce((x,y) -> x + " " + y)
                .ifPresent(System.out::print);

    }
}
