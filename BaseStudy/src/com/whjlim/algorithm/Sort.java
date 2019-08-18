package com.whjlim.algorithm;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] a = new int[]{9,8,7,6,5,4,3,2,1,9,8,7,6,5,4,3,2,1,9,8,7,6,5,4,3,2,1,
                9,8,7,6,5,4,3,2,1,9,8,7,6,5,4,3,2,1,9,8,7,6,5,4,3,2,1,
                9,8,7,6,5,4,3,2,1,9,8,7,6,5,4,3,2,1,9,8,7,6,5,4,3,2,1};
        Sort.sort(a, 0, a.length-1, true);
    }

    private static void sort(int[] a, int left, int right, boolean leftmost) {
    }
}
