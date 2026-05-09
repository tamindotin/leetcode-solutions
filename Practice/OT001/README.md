# Ceiling Number in a Sorted Array

---

## Problem

Given a sorted array and a target, return the **ceiling** of the target —
the smallest element in the array that is greater than or equal to the target.
If no ceiling exists, return `-1`.

Example:
- array = [2, 4, 6, 8, 10], target = 5  → return 6
- array = [2, 4, 6, 8, 10], target = 4  → return 4
- array = [2, 4, 6, 8, 10], target = 11 → return -1

---

## Approach

First check if target is greater than the largest element — if so, return `-1`
since no ceiling exists.

Then use Binary Search to find the target or narrow down to where it would be.
At each step, calculate `mid` and compare `array[mid]` with target:

- If `array[mid] == target` → exact match, return `array[mid]`
- If `array[mid] > target` → ceiling might be here or left, move `end` to `mid - 1`
- If `array[mid] < target` → ceiling is in right half, move `start` to `mid + 1`

When the loop ends, `start` points to the smallest element
greater than target — which is the ceiling.

---

## Complexity

| | |
|---|---|
| Time  | O(log n) |
| Space | O(1)     |

---

## Code

```java
public class Solution {
  public int CeilingNumber(int[] array, int target) {
    int start = 0;
    int end = array.length - 1;

    if (target > array[end]) {
      return -1;
    }

    while (start <= end) {
      int mid = start + (end - start) / 2;

      if (array[mid] == target) {
        return array[mid];
      }

      if (array[mid] > target) {
        end = mid - 1;
      }

      if (array[mid] < target) {
        start = mid + 1;
      }
    }
    return array[start];
  }
}
```

---

## Edge Cases

- Target already exists in array → returns the element itself
- Target smaller than all elements → returns first element
- Target larger than all elements → returns -1

---

## Notes

This is a variation of Binary Search.
The key insight is that when the loop ends, `start` always lands
on the index where target would be inserted — making it the ceiling.
The early return `-1` check prevents ArrayIndexOutOfBoundsException.
