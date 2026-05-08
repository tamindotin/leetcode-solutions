# LC704 - Binary Search

🔗 [LeetCode Problem](https://leetcode.com/problems/binary-search/)
**Difficulty:** Easy
**Topics:** Array, Binary Search

---

## Problem

Given a sorted array of integers `nums` and an integer `target`,
return the index of `target` if it exists, otherwise return `-1`.

---

## Approach

Use Binary Search to repeatedly narrow down the search range.
Start with two pointers `start` and `end` at both ends of the array.
At each step, calculate the middle index and compare `nums[mid]` with target:

- If `nums[mid] == target` → return `mid`
- If `nums[mid] > target` → target is in the left half, move `end` to `mid - 1`
- If `nums[mid] < target` → target is in the right half, move `start` to `mid + 1`

Repeat until `start > end`. If no match found, return `-1`.

> `mid = start + (end - start) / 2` is used instead of `(start + end) / 2`
> to avoid integer overflow for large arrays.

---

## Complexity

| | |
|---|---|
| Time  | O(log n) |
| Space | O(1)     |

---

## Code

```java
package Arrays.LC704;

class Solution {
  public int search(int[] nums, int target) {
    int start = 0;
    int end = nums.length - 1;

    while (start <= end) {
      int mid = start + (end - start) / 2;

      if (nums[mid] == target) {
        return mid;
      }

      if (nums[mid] > target) {
        end = mid - 1;
      }

      if (nums[mid] < target) {
        start = mid + 1;
      }
    }
    return -1;
  }
}
```

---

## Edge Cases

- Target not present in array → returns -1
- Single element array
- Target is the first or last element

---

## Notes

Binary Search only works on **sorted arrays**.
Each iteration halves the search space, making it far more
efficient than linear search O(n) for large inputs.
