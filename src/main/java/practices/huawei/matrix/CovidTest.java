package practices.huawei.matrix;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * TODO
 *
 * @since 2025/7/13
 */
public class CovidTest {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = Integer.parseInt(sc.nextLine());

    int[] confirmed = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

    int[][] matrix = new int[n][n];
    for (int i = 0; i < n; i++) {
      matrix[i] = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
    }

    System.out.println(getResult(n, confirmed, matrix));
  }

  public static int getResult(int n, int[] confirmed, int[][] matrix) {
    UnionFindSet ufs = new UnionFindSet(n);

    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        if (matrix[i][j] == 1) {
          // 有过接触的人进行合并
          ufs.union(i, j);
        }
      }
    }

    // 统计每个接触群体（连通分量）中的人数
    int[] cnts = new int[n];
    for (int i = 0; i < n; i++) {
      int fa = ufs.find(i);
      cnts[fa]++;
    }

    // 记录已统计过的感染群体
    HashSet<Integer> confirmed_fa = new HashSet<>();

    // 将有感染者的接触群体的人数统计出来
    int ans = 0;
    for (int i : confirmed) {
      int fa = ufs.find(i);

      // 如果该感染群体已统计过，则不再统计
      if (confirmed_fa.contains(fa)) {
        continue;
      }
      confirmed_fa.add(fa);

      ans += cnts[fa];
    }

    // 最终需要做核酸的人数，不包括已感染的人
    return ans - confirmed.length;
  }
}

// 并查集实现
class UnionFindSet {

  int[] fa;

  public UnionFindSet(int n) {
    this.fa = new int[n];
    for (int i = 0; i < n; i++) {
      fa[i] = i;
    }
  }

  public int find(int x) {
    if (x != this.fa[x]) {
      this.fa[x] = this.find(this.fa[x]);
      return this.fa[x];
    }
    return x;
  }

  public void union(int x, int y) {
    int x_fa = this.find(x);
    int y_fa = this.find(y);

    if (x_fa != y_fa) {
      this.fa[y_fa] = x_fa;
    }
  }
}
