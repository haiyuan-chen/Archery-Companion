package ui;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    int[] nums = new int[] { 2, 7, 11, 15 };
    int target = 9;

    public int[] twoSum(int[] nums, int target) {
        int[] list = new int[] {};
        for (int targetIndex = 0; targetIndex < nums.length - 1; targetIndex++) {
            for (int i = 1; i < nums.length; i++) {
                int toSub = target;
                toSub -= nums[i];
                if (toSub + nums[i] == target) {
                    return list;
                }
            }
        }
        return list;
    }

    int[] result = twoSum(nums, target);
}