package algorithms.binarySearch;

/**
 * TODO
 *
 * @since 2025/3/5
 */
public class BinarySearch {

  public static void main(String[] args) {
    int[] arr = {1, 3, 5, 7, 9};
    int key = 5;
    int index = binarySearch(arr, key);
    if (index == -1) {
      System.out.println("Element not found");
    } else {
      System.out.println("Element found at index " + index);
    }
  }

  public static int binarySearch(int[] arr, int key) {
    int left = 0;
    int right = arr.length - 1;
    while (left <= right) {
      int mid = left + (right - left) >> 1;
      if (arr[mid] == key) {
        return mid;
      } else if (arr[mid] < key) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return -1;
  }

}
