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
