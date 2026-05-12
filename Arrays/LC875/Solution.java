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
