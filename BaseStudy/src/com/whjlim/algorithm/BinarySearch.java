package com.whjlim.algorithm;

public class BinarySearch {

    //找到就返回一个下标，没有就返回-1
    public int binarySearch(int[] nums, int target){
        if(null == nums || nums.length == 0)return -1;
        int l = 0, r = nums.length-1;
        while (l <= r){
            int mid = l + (r-l)/2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[mid] < target){
                l = mid+1;
            }else{
                r = mid-1;
            }
        }
        return -1;
    }
    /*
    * 二分查找，有就返回下标，没有就返回插入位置的下标
    * */
    public int binaryInsertIndex(int[] nums, int target){
        if(nums == null || nums.length == 0)return 0;
        int l = 0, r = nums.length-1;
        while (l <= r){
            int mid = l + (r - l) / 2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[mid] > target){
                r = mid-1;
            }else{
                l = mid+1;
            }
        }
        return l;
    }
    /*
    * 二分查找，存在的话，返回第一个数的下标
    * 其实阔以这样，相等的时候，咱们就把
    * */
    public int binaryFirstIndex(int[] nums, int target){
        if(null == nums || nums.length == 0)return -1;
        int l = 0, r = nums.length-1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        //存在就是L
        if(l >= nums.length || nums[l] != target)return -1;
        return l;
    }

    public int binaryIndexLast(int[] nums, int target){
        if(nums == null || nums.length == 0)return -1;
        int l = 0, r = nums.length-1;
        while (l <= r){
            int mid = l + (r - l) / 2;
            if(nums[mid] <= target) {
                l = mid + 1;
            }else{
                r = mid-1;
            }
        }
        //跟第一相识，存在的话，一定是在r，r可能会爆下限
        if(r < 0 || nums[r] != target)return -1;
        return r;

    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        int[] nums = new int[]{1,1,1,2,4,4,5,6,7,8,9};
        System.out.println(bs.binaryIndexLast(nums, 100));
    }

}
