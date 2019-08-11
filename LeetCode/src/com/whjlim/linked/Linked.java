package com.whjlim.linked;

public class Linked {

    static class ListNode{
        int val;
        ListNode next = null;
        ListNode(int val){
            this.val = val;
        }
    }

    /*/**
    *@Author: whjLim
    *@Date: 12:53 2019/8/10
    * 反转链表--非递归
    */
    public ListNode retationList(ListNode head){
        if(null == head) return head;
        ListNode pre = null;
        ListNode next = head.next;
        while (next != null){
            head.next = pre;
            pre = head;
            head = next;
            next = next.next;
        }
        head.next = pre;
        return head;
    }
    /*/**
    *@Author: whjLim
    *@Date: 13:13 2019/8/10
    * 反转链表递归
    */
    public ListNode retationByRecursion(ListNode node){
        if(node == null || node.next == null) return node;
        ListNode next = retationByRecursion(node.next);
        node.next.next = node;
        node.next = null;
        return next;
    }
    public void Print(ListNode head){
        while (head != null){
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println();
    }
    /**
    *@Author: whjLim
    *@Date: 15:21 2019/8/10
    * 两两反转链表
    */
    public ListNode swapPairs(ListNode head) {
        ListNode rehead = new ListNode(-1);
        rehead.next = head;
        ListNode pre = rehead;
        while(null != head && head.next != null){
            ListNode next = head.next.next;
            pre.next = head.next;
            head.next.next = head;
            head.next = next;
            pre = head;
            head = next;
        }
        return rehead.next;
    }

    public static void main(String[] args) {

        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(2);
        ListNode p3 = new ListNode(3);
        ListNode p5 = new ListNode(5);
        ListNode p6 = new ListNode(6);
        ListNode p7 = new ListNode(7);
        p1.next = p2;
        p2.next = p3;
        p3.next = p5;
        p5.next = p6;
        p6.next = p7;
        Linked linked = new Linked();
        //linked.Print(linked.retationList(p1));
        linked.Print(linked.retationByRecursion(p1));
    }
}
