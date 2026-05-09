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
