package practices.hsbc.maxApps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * TODO
 *
 * @since 2026/5/25
 */

public class MaxApplication {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int num = scanner.nextInt();
    int constX = scanner.nextInt(); // always 3

    List<Application> applications = new ArrayList<>();
    Map<Integer, List<Application>> appsByStartTime = new HashMap<>();

    for (int i = 0; i < num; i++) {
      String startTimeStr = scanner.next();
      String endTimeStr = scanner.next();
      int resourceId = scanner.nextInt();

      int startTime = convertToSeconds(startTimeStr);
      int endTime = convertToSeconds(endTimeStr);

      Application app = new Application(startTime, endTime, resourceId, i);
      applications.add(app);

      appsByStartTime.computeIfAbsent(startTime, k -> new ArrayList<>()).add(app);
    }

    // Build conflict graph
    // Two applications conflict if:
    // 1. Same start time, OR
    // 2. Same resource and overlapping times

    boolean[][] conflicts = new boolean[num][num];

    for (int i = 0; i < num; i++) {
      for (int j = i + 1; j < num; j++) {
        Application a = applications.get(i);
        Application b = applications.get(j);

        if (a.startTime == b.startTime) {
          conflicts[i][j] = conflicts[j][i] = true;
        } else if (a.resourceId == b.resourceId) {
          if (!(a.endTime <= b.startTime || b.endTime <= a.startTime)) {
            conflicts[i][j] = conflicts[j][i] = true;
          }
        }
      }
    }

    // Maximum Independent Set in a graph with N <= 20? No, N <= 1000
    // So we need a greedy/approximation or specialized solution

    // Sort applications by start time
    applications.sort(Comparator.comparingInt(a -> a.startTime));

    // DP approach: dp[i] = max apps from first i apps
    int[] dp = new int[num + 1];
    int[] lastCompatible = new int[num];

    for (int i = 0; i < num; i++) {
      // Find last app that doesn't conflict with app i
      int last = -1;
      for (int j = i - 1; j >= 0; j--) {
        if (!conflicts[applications.get(i).index][applications.get(j).index]) {
          last = j;
          break;
        }
      }
      lastCompatible[i] = last;
    }

    for (int i = 0; i < num; i++) {
      // Skip app i
      dp[i + 1] = dp[i];

      // Take app i
      int take = 1;
      if (lastCompatible[i] >= 0) {
        take += dp[lastCompatible[i] + 1];
      }
      dp[i + 1] = Math.max(dp[i + 1], take);
    }

    System.out.println(dp[num]);
  }

  private static int convertToSeconds(String time) {
    int minutes = Integer.parseInt(time.substring(0, 2));
    int seconds = Integer.parseInt(time.substring(2, 4));
    return minutes * 60 + seconds;
  }

  static class Application {

    int startTime;
    int endTime;
    int resourceId;
    int index;

    Application(int startTime, int endTime, int resourceId, int index) {
      this.startTime = startTime;
      this.endTime = endTime;
      this.resourceId = resourceId;
      this.index = index;
    }
  }
}