# LC852 - Peak Index in a Mountain Array

🔗 [LeetCode Problem](https://leetcode.com/problems/peak-index-in-a-mountain-array/)
**Difficulty:** Medium
**Topics:** Array, Binary Search

---

## Problem

Given a mountain array (values strictly increase then strictly decrease), find and return the index of the peak element — the largest value in the array. The solution must run in O(log n) time.

---

## Approach

Since the array is a mountain, we can use **Binary Search** to locate the peak efficiently.

At every `mid`, we compare `arr[mid]` with `arr[mid + 1]`:
- If `arr[mid] < arr[mid + 1]` → we're on the **ascending slope**, so the peak must be to the right → move `left = mid + 1`
- Otherwise → we're on the **descending slope** (or at peak), so peak is to the left or at `mid` → move `right = mid - 1`

We also track `peakIndex` throughout by updating it whenever we find a value greater than the current peak.

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
  public static int peakIndexInMountainArray(int[] arr) {
    int left = 0;
    int right = arr.length - 1;

    int peakIndex = 0;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (arr[mid] > arr[peakIndex]){
        peakIndex = mid;
      }

      if (arr[mid] < arr[mid + 1]) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return peakIndex;
  }
}
```

---

## Edge Cases

- Minimum valid mountain array has 3 elements (guaranteed by problem constraints)
- Peak at index 1 (e.g., `[0, 5, 1]`)
- Peak near the end (e.g., `[0, 1, 2, 5, 2]`)

---

## Notes

**Brute Force:** Linear scan O(n) — just iterate and find `arr[i] > arr[i-1] && arr[i] > arr[i+1]`.

**This approach:** Binary search cuts the search space in half each time → O(log n). The key insight is that comparing `arr[mid]` with `arr[mid + 1]` tells us which slope we're on, letting us discard half the array every iteration.

> ⚠️ Note: `arr[mid + 1]` is safe here because the problem guarantees a mountain shape, so `mid` will never be the last index when we're still searching.
