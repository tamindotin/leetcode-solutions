# LC34 - Find First and Last Position of Element in Sorted Array

🔗 [LeetCode Problem](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)
**Difficulty:** Medium
**Topics:** Array, Binary Search

---

## Problem

Given a sorted array of integers `nums` and a target value `target`, find the starting and ending position of the target value in the array.

If the target is not found in the array, return `[-1, -1]`.

The solution must have **O(log n)** time complexity.

---

## Approach

The problem requires O(log n) time, indicating **binary search** as the natural choice. However, finding both the first and last occurrence needs a modified binary search:

- Perform binary search twice:
  1. **Find first occurrence** — when `nums[mid] == target`, instead of returning immediately, move the `end` pointer to `mid - 1` to keep searching left.
  2. **Find last occurrence** — when `nums[mid] == target`, move the `start` pointer to `mid + 1` to keep searching right.

- A helper function `search(nums, target, findStartIndex)` handles both cases with a boolean flag.

- If the target is not present, the helper returns `-1` for that bound.

---

## Complexity

| | |
|---|---|
| Time  | O(log n) — two binary searches, each O(log n) |
| Space | O(1) — only a few variables used |

---

## Code

```java
class Solution {
  public int[] searchRange(int[] nums, int target) {
    int ans[] = { -1, -1 };

    ans[0] = search(nums, target, true);
    ans[1] = search(nums, target, false);

    return ans;
  }

  int search(int[] nums, int target, boolean FindStartIndex) {
    int start = 0;
    int end = nums.length - 1;

    int ans = -1;

    while (start <= end) {
      int mid = start + (end - start) / 2;

      if (nums[mid] > target) {
        end = mid - 1;
      } else if (nums[mid] < target) {
        start = mid + 1;
      } else {
        ans = mid;
        if (FindStartIndex)
          end = mid - 1;
        else
          start = mid + 1;
      }
    }
    return ans;
  }
}
