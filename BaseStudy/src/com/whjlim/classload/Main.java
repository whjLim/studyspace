package com.whjlim.classload;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;


public class Main {

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root2 == null)return false;
        return subtree(root1, root2);
    }
    public boolean subtree(TreeNode root1,TreeNode root2) {
        if(root1 == null && root2 != null)return false;
        if(root2 == null)return true;
        if(root1.val == root2.val){
            return (HasSubtree(root1.left, root2.left) && HasSubtree(root1.right, root2.right)) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
        }else{
            return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
        }
    }

    public void test(){
        TreeNode root = new TreeNode(8);
        TreeNode n1 = new TreeNode(8);
        TreeNode n2 = new TreeNode(7);
        TreeNode n3 = new TreeNode(9);
        TreeNode n4 = new TreeNode(2);
        TreeNode n5 = new TreeNode(4);
        TreeNode n6 = new TreeNode(7);
        root.left = n1;root.right=n2;
        n1.left=n3;n1.right = n4;
        n4.left = n5; n5.right=n6;
        TreeNode bb = new TreeNode(8);
        bb.left = new TreeNode(9);
        bb.right = new TreeNode(2);
        System.out.println(HasSubtree(root,bb));
    }
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int down = matrix.length;
        int right = matrix[0].length;
        int left = -1;
        int up = -1;
        int x = 0, y = 0;
        int nums = down*right;
        while(nums > 0){
            up++;
            while(nums >0 && x == up && y < right){
                list.add(matrix[x][y]);
                y++;
                nums--;
            }
            right--;x++;y--;
            while(nums >0 && y == right && x < down){
                list.add(matrix[x][y]);
                x++;
                nums--;
            }
            down--;x--;y--;
            while(nums >0 && x == down && y > left){
                list.add(matrix[x][y]);
                y--;
                nums--;
            }
            left++;y++;x--;
            while(nums > 0 && y == left && x > up) {
                list.add(matrix[x][y]);
                x--;
                nums--;
            }
            x++;y++;
        }
        return list;
    }

    public static void main(String[] args) {

        Main main = new Main();
        main.printMatrix(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}});

    }

    public int NumberOf1(int n) {
        int res = 0;
        while(n != 0){
            n = n & (n-1);
            res = res+1;
        }
        return res;
    }

    public List<String> restoreIpAddresses(String s) {
        if(null == s || s.length() <= 3 || s.length() > 12)
            return new ArrayList<String>();
        List<String> res = new ArrayList<String>();
        int[] ipnums = new int[4];
        for(int i = 0, leni = 3, len = s.length(); i < leni && i < len; i++){
            //1:0--i
            if(i > 0 && s.charAt(0) == '0')break;
            ipnums[0] = ipnums[0]*10+(s.charAt(i)-'0');
            if(ipnums[0] > 255)break;
            for(int j = i+1, lenj = i+4; j < lenj && j < len; j++){
                //2:i+1--j
                if(j > i+1 && s.charAt(i+1) == '0')break;
                ipnums[1] = ipnums[1]*10+(s.charAt(j)-'0');
                if(ipnums[1] > 255){
                    ipnums[1] = 0;
                    ipnums[2] = 0;
                    break;
                }
                //
                for(int k = j+1, lenk = j+4; k < lenk && k+1 < len; k++){
                    //3:j+1--k
                    if(k > j+1 && s.charAt(j+1) == '0')break;
                    ipnums[2] = ipnums[2]*10+(s.charAt(k)-'0');
                    if(ipnums[2] > 255){
                        ipnums[1] = 0;
                        ipnums[2] = 0;
                        break;
                    }
                    //4:k+1 -len
                    if(s.charAt(k+1) == '0' && ((k+2) < len))continue;
                    ipnums[3] = Integer.valueOf(s.substring(k+1));
                    if(ipnums[3] > 255){
                        continue;
                    }
                    StringBuilder sb = new StringBuilder(s);
                    System.out.println(ipnums[0] + "." + ipnums[1] + "." + ipnums[2] + "." + ipnums[3]);
                    System.out.println(i + "," + j + "," + k);
                    sb.insert(i+1,'.');
                    sb.insert(j+2,'.');
                    sb.insert(k+3,'.');
                    res.add(sb.toString());
                }
                ipnums[2] = 0;
            }
            ipnums[1] = 0;
        }
        return res;
    }

    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return false;
        }
        int left = 0, right = nums.length-1;
        while(left <= right){
            int mid = left + ((right-left)>>1);
            if(nums[mid] == target)return true;
            while(left < mid && nums[left] == nums[mid]){
                left++;
            }
            while(right > mid && nums[right] == nums[mid]){
                right--;
            }
            if(nums[left] >= nums[right]){
                if(nums[mid] >= nums[left]){//mid在左边大
                    if((target >= nums[left] && target < nums[mid])){
                        right = mid-1;
                    }else{
                        left = mid+1;
                    }
                }else{//mid在右边小区
                    if(target <= nums[right] && target > nums[mid]){
                        left = mid+1;
                    }else{
                        right = mid-1;
                    }
                }
            }else{
                if(target > nums[mid]){
                    left = mid+1;
                }else{
                    right = mid-1;
                }
            }
        }

        return false;
    }

    public int minNumberInRotateArray(int [] array) {
        if(null == array || array.length == 0)return 0;
        int left = 0, right = array.length-1;
        while(left <= right){
            while(left < right && array[left+1] == array[left]){
                left++;
            }
            while(right > left && array[right-1] == array[right]){
                right--;
            }
            if(left != right && array[left] == array[right]){
                right--;
            }
            int mid = left + (right-left)/2;
            if((left == right) || (mid == left && array[mid] < array[mid+1]) || (mid == right && array[mid] < array[mid-1]) || (mid > left && array[mid] < array[mid-1] && mid < right && array[mid] < array[mid+1])){
                return array[mid];
            }else if(array[mid] < array[right]){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return array[left];
    }


}
