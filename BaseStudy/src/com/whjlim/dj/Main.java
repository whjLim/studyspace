package com.whjlim.dj;

import java.util.*;

public class Main {

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {

        ArrayList<Integer> res = new ArrayList<Integer>();
        int l = 0, r = input.length-1;
        while(l <= r){
            int mid = parament(input, l, r);
            if(mid+1 == k){
                for(int i = 0; i < k; i++){
                    res.add(input[i]);
                }
                break;
            }
            if(mid+1 > k){
                r = mid-1;
            }else{
                l = mid+1;
            }
        }

        return res;
    }
    public int parament(int[] input, int l, int r){

        int temp = input[l];
        while(l < r){
            while(r > l && input[r] >= input[l]) r--;
            input[l] = input[r];
            while(l < r && input[l] <= input[r]) l++;
            input[r] = input[l];
        }
        input[l] = temp;
        return l;

    }
    int rotation(int x){
        int res = 0;
        while(x > 0){
            res = res*10 + x % 10;
            x /= 10;
        }
        return res;
    }
    public int NumberOf1Between1AndN_Solution(int n) {
        int res = 0;
        int t = n;
        int after = 1;
        int chang = 0;
        while(t >= 10){
            int befor = t / 10;
            int mod = t % 10;
            if(mod == 0){
                res += befor * after;
            }else if(mod == 1){
                res += rotation(chang)+1;
                res += (befor)*after;
            }else{
                res += (befor + 1) * after;
            }
            t = befor;
            after *= 10;
            chang = chang*10+mod;
        }
        if(t == 1){
            res += n-after+1; //
        }else{
            res += after;
        }
        return res;
    }

    int cmd(String as, String bs){

        String ab = as + bs;
        String ba = bs + as;

        return ab.compareTo(ba); //0相等  < 0 小  >0 大于
    }
    public String PrintMinNumber(int [] numbers) {
        if(null == numbers || numbers.length == 0)return "";
        String[] strNums = new String[numbers.length];
        for(int i = 0, len = numbers.length; i < len; i++){
            strNums[i] = String.valueOf(numbers[i]);
        }
        quickSort(strNums, 0, strNums.length-1);
        StringBuilder sb = new StringBuilder();
        for(int i = 0, len = strNums.length; i < len; i++){
            sb.append(strNums[i]);
        }
        return sb.toString();
    }
    //手写个快排序吧，复习个
    public void quickSort(String[] strs, int l, int r){
        if(l < r){
            int mid = parament(strs, l, r);
            quickSort(strs, l, mid-1);
            quickSort(strs, mid+1, r);
        }
    }
    public int parament(String[] strs, int l, int r){

        String temp = strs[l];
        while(l < r){
            while(r > l && cmd(strs[r], strs[l]) >= 0)r--;
            strs[l] = strs[r];

            while(l < r && cmd(strs[l], strs[r]) <= 0)l++;
            strs[r] = strs[l];

        }
        strs[l] = temp;
        return l;
    }
    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode LoopNode(ListNode head){
        if(null == head)return null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null && slow != null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow){
                break;
            }
        }
        if(fast == null || fast.next == null)return null;
        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
            if(slow == fast)break;
        }
        return slow;
    }
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1 == null || pHead2 == null)return null;
        ListNode head = pHead1;
        while(head.next != null){
            head = head.next;
        }
        head.next = pHead2;
        ListNode com = LoopNode(pHead1);
        head = pHead1;
        while(head.next != pHead2){
            head = head.next;
        }
        head.next = null;
        return com;
    }

    public boolean isContinuous(int [] numbers) {
        if(null == numbers || numbers.length == 0)return false;
        Arrays.sort(numbers);
        int zeroIndex = 0;
        while(numbers[zeroIndex] == 0){
            zeroIndex++;
        }
        for(int i = zeroIndex+1, len = numbers.length; i < len; i++){
            if(numbers[i] - numbers[i-1] > 1){
                zeroIndex  = zeroIndex - (numbers[i] - numbers[i-1] - 1);
                if(zeroIndex < 0)
                    return false;
            }
        }
        return true;
    }

    public boolean isNumeric(char[] str) {
        if(null == str || str.length == 0)return false;
        int index = 0; //

        int len = str.length;
        boolean flage = false;
        int point = 0; //小数点

        if(str[index] == '+' || str[index] == '-')
            index++;
        for(int i = index; i < len; i++){
            if(str[i] == 'e' || str[i] == 'E'){
                if(flage || i == len-1)return false;
                flage = true;
            }else if(str[i] == '.'){
                if(!flage && point == 0){
                    point = 1;
                }else{
                    return false;
                }
            }else if(str[i] == '+' || str[i] == '-'){
                if(str[i-1] != 'e' && str[i-1] != 'E'){
                    return false;
                }
            }else if(str[i] < '0' || str[i] > '9'){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int a = 1;
        int b = 31;
        System.out.println(a<<b);

        Main main = new Main();
        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(2);
        ListNode p3 = new ListNode(3);
        ListNode p4 = new ListNode(4);
        ListNode p5 = new ListNode(5);
        ListNode p6 = new ListNode(6);
        ListNode p7 = new ListNode(7);
        ListNode p8 = new ListNode(9);
        p1.next = p2;
        p2.next = p3;

        p5.next = p6;
        p6.next = p7;


        System.out.println(main.maxInWindows(new int[]{2,3,4,2,6,2,5,1}, 0));
    }
    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        if(num == null)return null;
        ArrayDeque<Integer>  deque = new ArrayDeque<Integer>();
        int max = num[0];
        deque.offerLast(0);
        for(int i = 1; i < size; i++){

        while(!deque.isEmpty() && num[deque.peekLast()] < num[i])
            deque.pollLast();
        deque.offerLast(i);
        if(num[i] > max)max = num[i];
    }
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add(max);
        for(int i = size, len = num.length; i < len; i++){
            if(deque.peekFirst() == i-size){
                deque.pollFirst();
            }
            while(!deque.isEmpty() && num[deque.peekLast()] < num[i])
                deque.pollLast();
            deque.offerLast(i);
            res.add(num[deque.peekFirst()]);
        }
        return res;
    }
    TreeNode Deserialize(String str) {
        if(null == str || str.length() == 0)return null;
        char[] cs = str.toCharArray();
        int index = 0;
        TreeNode root = new TreeNode(cs[index++]-'0');
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root);
        while(!que.isEmpty()){
            TreeNode t = que.poll();
            if(index < cs.length && cs[index] != '#'){
                t.left = new TreeNode(cs[index++]-'0');
                que.offer(t.left);
            }
            if(index < cs.length && cs[index] != '#'){
                t.right = new TreeNode(cs[index++]-'0');
                que.offer(t.right);
            }
        }
        return root;
    }

    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {

        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        for(int i = 1, len = sum/2; i <= len; i++){
            int c = 2*sum+i*i-i;
            int sqn = 1+4*c;
            int c2 = (int)Math.sqrt(sqn);
            if(c2*c2 == sqn && (c2 & 1) == 1){
                c2 = (c2-1)>>1;
                ArrayList<Integer> list = new ArrayList<Integer>();
                for(int j = i; j <= c2; j++){
                    list.add(j);
                }
                res.add(list);
            }
        }
        return res;

    }

    public int GetNumberOfK(int [] array , int k) {
        int l = 0, r = array.length-1;
        int valueIndex = -1;
        while(l <= r){
            int mid = l + (r-l)/2;
            if(array[mid] == k){
                valueIndex = mid;
                break;
            }
            if(array[mid] < k){
                l = mid+1;
            }else{
                r = mid-1;
            }
        }
        int res = 0;
        if(valueIndex != -1){
            res = 1;
            int i = valueIndex-1;
            while(i >= 0 && array[i] == array[valueIndex])res++;
            i = valueIndex+1;
            while(i < array.length && array[i] == array[valueIndex])res++;
        }
        return res;
    }
}

/*
2 1 1
0 1 10
1
4 5 3
0 1 15
1 2 15
0 3 50
1 3 30
2 3 10
2
1
3
* */
