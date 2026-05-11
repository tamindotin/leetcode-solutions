public class Solution {
  static int answer(int[] arr, int target) {
    int start = 0;
    int end = 1;

    while (target > arr[end]) {
      int newStart = end + 1;
      end = end + (end - start + 1) * 2;
      start = newStart;
    }

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
