package practices.huawei.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * TODO
 *
 * @since 2025/7/21
 */
public class WolfSheep {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int m = sc.nextInt();
    int n = sc.nextInt();
    int x = sc.nextInt();

    System.out.println(getResult(m, n, x));
  }

  /**
   * @param sheep 本岸羊数量
   * @param wolf  本岸狼数量
   * @param boat  船负载
   * @return 最少运送次数
   */
  public static int getResult(int sheep, int wolf, int boat) {
    ArrayList<Integer> ans = new ArrayList<>();
    dfs(sheep, wolf, boat, 0, 0, 0, ans);

    if (ans.size() > 0) {
      return Collections.min(ans);
    } else {
      return 0;
    }
  }

  public static void dfs(
      int sheep,
      int wolf,
      int boat,
      int oppo_sheep,
      int oppo_wolf,
      int count,
      ArrayList<Integer> ans) {
    if (sheep == 0 && wolf == 0) {
      ans.add(count);
      return;
    }

    if (sheep + wolf <= boat) {
      ans.add(count + 1);
      return;
    }

    // i 代表船上羊数量，最多Math.min(boat, sheep)
    for (int i = 0; i <= Math.min(boat, sheep); i++) {
      // j 代表船上狼数量，最多Math.min(boat, wolf)
      for (int j = 0; j <= Math.min(boat, wolf); j++) {
        // 空运
        if (i + j == 0) {
          continue;
        }
        // 超载
        if (i + j > boat) {
          break;
        }

        // 本岸羊 <= 本岸狼，说明狼运少了
        if (sheep - i <= wolf - j && sheep - i != 0) {
          continue;
        }

        // 对岸羊 <= 对岸狼，说明狼运多了
        if (oppo_sheep + i <= oppo_wolf + j && oppo_sheep + i != 0) {
          break;
        }

        // 对岸没羊，但是对岸狼已经超过船载量，即下次即使整船都运羊，也无法保证对岸羊 > 对岸狼
        if (oppo_sheep + i == 0 && oppo_wolf + j >= boat) {
          break;
        }

        dfs(sheep - i, wolf - j, boat, oppo_sheep + i, oppo_wolf + j, count + 1, ans);
      }
    }
  }

}
