package practices.hsbc.busRouteInterlap;

import java.util.*;

public class IntervalMerge {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int busStation_row = sc.nextInt();
    int busStation_col = sc.nextInt();

    // Handle edge case: no buses
    if (busStation_row == 0) {
      System.out.println(0);
      return;
    }

    int[][] intervals = new int[busStation_row][2];

    for (int i = 0; i < busStation_row; i++) {
      intervals[i][0] = sc.nextInt();
      intervals[i][1] = sc.nextInt();
    }

    // Sort intervals by start time
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

    int count = 1;
    int currentEnd = intervals[0][1];

    for (int i = 1; i < busStation_row; i++) {
      if (intervals[i][0] <= currentEnd) {
        // Overlapping, merge
        currentEnd = Math.max(currentEnd, intervals[i][1]);
      } else {
        // No overlap, count this as a new bus
        count++;
        currentEnd = intervals[i][1];
      }
    }

    System.out.println(count);
  }
}