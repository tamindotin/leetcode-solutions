# LC875 - Koko Eating Bananas

🔗 [LeetCode Problem](https://leetcode.com/problems/koko-eating-bananas/)
**Difficulty:** Medium
**Topics:** Array, Binary Search

---

## Problem

Koko loves bananas. There are `n` piles of bananas, and she has `h` hours to eat them all before the guards return. She can choose an eating speed `k` (bananas per hour). Each hour she picks one pile and eats up to `k` bananas from it — if the pile has fewer than `k`, she eats the rest and doesn't move to another pile that hour.

Return the **minimum integer** `k` such that she can finish all piles within `h` hours.

---

## Approach

The eating speed `k` has a clear search space: anywhere from `1` (slowest possible) to `max(piles)` (eating the biggest pile in one hour — going faster never helps). Within this range, the relationship is **monotonic**: if speed `k` is fast enough, any speed greater than `k` is also fast enough. That makes this a classic binary search on the answer.

1. Set `start = 0`, `end = max(piles)`.
2. At each midpoint `mid`, compute the total hours needed to finish all piles at speed `mid` using `ceil(pile / mid)` per pile.
3. If total time exceeds `h`, the speed is too slow → search right (`start = mid + 1`).
4. Otherwise, the speed might work but we want the minimum → search left (`end = mid - 1`).
5. When the loop ends, `start` holds the minimum valid speed.

---

## Complexity

| | |
|---|---|
| Time  | O(n log m) — where `n` is number of piles and `m` is `max(piles)` |
| Space | O(1) |

---

## Code

```java
class Solution {
  public static int minEatingSpeed(int[] piles, int h) {
    int start = 0;
    int end = getMax(piles);

    while (start <= end) {
      int mid = start + (end - start) / 2;

      double reqTime = getTotalTime(piles, mid);

      if (reqTime > h) {
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    return start;
  }

  // Function to get the end for Binary Search
  static int getMax(int[] arr) {
    int max = arr[0];

    for (int i = 1; i < arr.length; i++) {
      if (arr[i] > max) {
        max = arr[i];
      }
    }

    return max;
  }

  // Function to get total time to finish whole set of piles
  static double getTotalTime(int[] arr, int k) {

    double totalTime = 0;

    for (int i : arr) {
      totalTime += Math.ceil((double) i / (double) k);
    }
    return totalTime;
  }
}
```

---

## Edge Cases

- `h == piles.length` → Koko must eat at full speed; answer is `max(piles)`.
- Single pile (`piles = [n]`) → answer is `ceil(n / h)`.
- `k = 0` guard: `start` begins at `0`, but `getTotalTime` with `k = 0` would cause division by zero — the first valid `mid` checked will always be ≥ 1 as long as `end ≥ 1`, which is guaranteed since pile sizes are ≥ 1.
- All piles of size 1 → minimum speed is 1 regardless of `h`.

---

## Notes

**Brute force:** Iterate `k` from `1` to `max(piles)` and return the first `k` where total time ≤ `h`. This is O(n·m) — too slow for large inputs.

**Why `start` is the answer:** The binary search narrows down to the leftmost `k` that satisfies the condition. When `start > end`, `start` is the smallest index that was never ruled out, making it the minimum valid speed.

**Alternative:** `Arrays.stream(piles).max().getAsInt()` can replace the manual `getMax()`, and integer arithmetic `(pile + k - 1) / k` can replace `Math.ceil((double) pile / k)` to avoid floating point altogether.
