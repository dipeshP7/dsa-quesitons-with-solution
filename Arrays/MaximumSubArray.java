package com.example.demo.problems.MaximumSubArray;

import java.util.Arrays;

public class MaximumSubArray {
//    Given an integer array nums, find the with the largest sum, and return its sum.
//    Example 1:
//    Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//    Output: 6
//    Explanation: The subarray [4,-1,2,1] has the largest sum 6.
//
//    Example 2:
//    Input: nums = [1]
//    Output: 1
//    Explanation: The subarray [1] has the largest sum 1.
//
//    Example 3:
//    Input: nums = [5,4,-1,7,8]
//    Output: 23
//    Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
//
//    Constraints:
//            1 <= nums.length <= 105
//            -104 <= nums[i] <= 104
//    Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
//
     //core logic
//    Kadane’s Algorithm basically does:
//    currentSum=max(nums[i], currentSum+nums[i])
//    Then:
//    maxSum=max(maxSum, currentSum)

    public static void main(String[] args){
     int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
     System.out.println(MaximumSubArray.findMaximumSubArray(arr));
    }

    public static int findMaximumSubArray(int[] arr){
          int currentSum = arr[0];
          int maxSum = arr[0];
          for(int i= 1; i < arr.length; i++){
             currentSum = Math.max(arr[i], (currentSum + arr[i]));
             maxSum = Math.max(maxSum, currentSum);
          }
          return maxSum;
    }


}

