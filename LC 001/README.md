# LC001 - Two Sum

🔗 [LeetCode Problem](https://leetcode.com/problems/two-sum/)
**Difficulty:** Easy
**Topics:** Array, Brute Force

---

## Problem

Given an array of integers `nums` and an integer `target`, return the
indices of the two numbers that add up to `target`.
You may assume exactly one solution exists and you cannot use the same element twice.

---

## Approach

Use two nested loops to check every possible pair of numbers in the array.
For each pair `(i, j)`, calculate their sum and check if it equals the target.
If a match is found, return both indices immediately.
If no pair is found after all iterations, return `[-1, -1]`.

---

## Complexity

| | |
|---|---|
| Time  | O(n²) |
| Space | O(1)  |

---

## Code

```java
class Solution {
  public int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        int sum = nums[i] + nums[j];
        if (sum == target) {
          return new int[] { i, j };
        }
      }
    }
    return new int[] { -1, -1 };
  }
}
```

---

## Edge Cases

- Empty array
- No valid pair exists → returns [-1, -1]
- Array with only two elements

---

## Notes

This is the brute force approach — simple but not optimal for large inputs.
A HashMap solution can solve this in O(n) time at the cost of O(n) space.
