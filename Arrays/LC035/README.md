# LC035 - Search Insert Position

🔗 [LeetCode Problem](https://leetcode.com/problems/search-insert-position/)
**Difficulty:** Easy
**Topics:** Array, Binary Search

---

## Problem

Given a **sorted array** of distinct integers and a `target`, return the index of `target` if it exists. If it doesn't, return the index where it **would be inserted** to keep the array sorted.

Must run in **O(log n)** time.

---

## Approach

Standard binary search with one key observation: if the target is never found, `start` naturally lands at the correct insertion point by the time the loop ends.

1. Set `start = 0`, `end = nums.length - 1`.
2. At each `mid`, three cases:
   - `nums[mid] == target` → found it, return `mid`.
   - `nums[mid] > target` → target is in the left half → `end = mid - 1`.
   - `nums[mid] < target` → target is in the right half → `start = mid + 1`.
3. If the loop exits without finding the target, `start` points to the first element greater than `target` — exactly where it should be inserted.

---

## Complexity

| | |
|---|---|
| Time  | O(log n) |
| Space | O(1) |

---

## Code

```java
class Solution {
  public static int searchInsert(int[] nums, int target) {
    int start = 0;
    int end = nums.length - 1;

    while (start <= end) {
      int mid = start + (end - start) / 2;

      if (nums[mid] > target) {
        end = mid - 1;
      } else if (nums[mid] < target) {
        start = mid + 1;
      } else {
        return mid;
      }
    }
    return start;
  }
}
```

---

## Edge Cases

- Target smaller than all elements → `start` never moves, returns `0`.
- Target larger than all elements → `start` ends up at `nums.length`, which is the correct append position.
- Target already exists → returned immediately on the `==` branch.
- Single-element array → works correctly for all three cases above.

---

## Notes

**Why `start` is the insertion point:** When the loop exits, `end < start`. At this moment, `nums[end] < target < nums[start]` — so `start` is the first position where inserting `target` keeps the array sorted.

**Brute force:** Linear scan from left, return the first index where `nums[i] >= target`. O(n) time — straightforward but doesn't meet the O(log n) requirement.

**Relation to `lower_bound`:** This is essentially the same as C++ STL's `lower_bound` — the leftmost position where `target` can be inserted while maintaining sorted order.
