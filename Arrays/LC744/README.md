# LC744 - Next Greatest Letter

🔗 [LeetCode Problem](https://leetcode.com/problems/find-smallest-letter-greater-than-target/)
**Difficulty:** Easy
**Topics:** Array, Binary Search

---

## Problem

Given a sorted array of characters `letters` and a character `target`,
return the smallest character in the array that is **strictly greater** than target.

The array is circular — if no greater letter exists, wrap around and return the first letter.

Example:
- letters = ['c', 'f', 'j'], target = 'a' → return 'c'
- letters = ['c', 'f', 'j'], target = 'f' → return 'j'
- letters = ['c', 'f', 'j'], target = 'j' → return 'c' (wrap around)

---

## Approach

Use Binary Search to find the smallest letter strictly greater than target.

- If `letters[mid] > target` → answer might be here or left, move `end` to `mid - 1`
- If `letters[mid] <= target` → answer is in right half, move `start` to `mid + 1`

When the loop ends, `start` points to the next greatest letter.
Using `start % letters.length` handles the wrap around case —
if `start` goes out of bounds, it wraps back to index `0`.

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
  public char nextGreatestLetter(char[] letters, char target) {
    int start = 0;
    int end = letters.length - 1;

    while (start <= end) {
      int mid = start + (end - start) / 2;

      if (letters[mid] > target) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return letters[start % letters.length];
  }
}
```

---

## Edge Cases

- Target is greater than all letters → wraps around, returns first letter
- Target is smaller than all letters → returns first letter
- Target exists in array → returns next letter after it (strictly greater)

---

## Notes

The wrap around is elegantly handled by `start % letters.length`.
When `start == letters.length` (target >= all elements),
`start % letters.length` returns `0` — the first element.
No extra if-check needed.
