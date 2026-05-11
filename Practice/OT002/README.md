# OT002 - Search in Infinite Sorted Array

---

## Problem

Given a sorted array (potentially infinite or of unknown size) and a target value, return the index of the target. If the target is not found, return -1.

---

## Approach

Standard binary search requires knowing the bounds of the array upfront. Since the array may be infinite, we use **Exponential Search** to first find a valid range, then binary search within it.

**Phase 1 — Exponential range expansion:**
Start with the window `[0, 1]`. If `arr[end] < target`, the target lies further right. We move `start` to `end + 1` and double the window size each iteration. This gives us a range `[start, end]` that is guaranteed to contain the target (or we'd have exited the loop).

**Phase 2 — Binary search within the range:**
Once we have a bounded range where `arr[end] >= target`, run a standard binary search to find the exact index.

The exponential doubling means we reach the right range in O(log i) steps, where i is the target's index.

---

## Complexity

| | |
|---|---|
| Time  | O(log i) — where i is the index of the target |
| Space | O(1) |

---

## Code

```java
public class Solution {
  static int answer(int[] arr, int target) {
    int start = 0;
    int end = 1;

    // Phase 1: Expand range exponentially until arr[end] >= target
    while (target > arr[end]) {
      int newStart = end + 1;
      end = end + (end - start + 1) * 2;
      start = newStart;
    }

    // Phase 2: Binary search within the found range
    while (start <= end) {
      int mid = start + (end - start) / 2;

      if (arr[mid] > target) {
        end = mid - 1;
      } else if (arr[mid] < target) {
        start = mid + 1;
      } else {
        return mid;
      }
    }
    return -1;
  }
}
```

---

## Edge Cases

- Target is at index 0 — the while loop condition `target > arr[end]` is false immediately (since `end = 1`), so we binary search `[0, 1]` directly.
- Target is not in the array — binary search exhausts the range and returns -1.
- Target is very far right — exponential doubling ensures we reach it in O(log i) steps without scanning linearly.
- Duplicate values — the algorithm still works; it returns *an* index where the value equals the target (not necessarily the first or last).

---

## Notes

**Brute force:** Linear scan from index 0 — O(i) time, fine for small i but degrades badly for large indices.

**Why double the window size?**
Each expansion doubles: window sizes go 2, 4, 8, 16, … so after k expansions the window covers up to index 2^k. This means we reach index i in at most log₂(i) expansions.

**Window size formula:**
`end = end + (end - start + 1) * 2` keeps doubling the window rather than just doubling `end`, which correctly handles the case where `start` is non-zero after the first expansion.

**Variant:** If the array is guaranteed finite with a known size, standard binary search over `[0, n-1]` is simpler and sufficient — exponential search is most useful for unbounded/infinite arrays.
