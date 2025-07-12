package practices.huawei;

import java.util.Scanner;

/**
 * 题目描述 祖国西北部有一片大片荒地，其中零星的分布着一些湖泊，保护区，矿区; 整体上常年光照良好，但是也有一些地区光照不太好。
 * <p>
 * 某电力公司希望在这里建设多个光伏电站，生产清洁能源对每平方公里的土地进行了发电评估， 其中不能建设的区域发电量为0kw，可以发电的区域根据光照，地形等给出了每平方公里年发电量x千瓦。
 * 我们希望能够找到其中集中的矩形区域建设电站，能够获得良好的收益。
 * <p>
 * 输入描述: 第一行输入为调研的地区长，宽，以及准备建设的电站【长宽相等，为正方形】的边长，最低要求的发电量。 之后每行为调研区域每平方公里的发电量。
 * <p>
 * 例如，输入为： 2 5 2 6 1 3 4 5 8 2 3 6 7 1 表示调研的区域大小为长 2 宽 5 的矩形，我们要建设的电站的边长为 2，建设电站最低发电量为 6
 * <p>
 * 输出描述: 输出为这样的区域有多少个？ 上述输入长度为 2 的正方形满足发电量大于等于 6 的区域有 4 个。则输出为： 4
 * <p>
 * 用例一： 输入： 2 5 2 6 1 3 4 5 8 2 3 6 7 1 输出： 4
 * <p>
 * 用例二： 输入： 2 5 1 6 1 3 4 5 8 2 3 6 7 1 输出： 3
 *
 * @since 2025/7/12
 */
public class StationConstructionPlanning {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    // 输入地区长r，宽c，电站边长s，最低发电量min
    int row = scanner.nextInt();
    int col = scanner.nextInt();
    int size = scanner.nextInt();
    int min = scanner.nextInt();

    // 输入每个区域每平方公里的发电量，存入矩阵matrix中
    int[][] matrix = new int[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        matrix[i][j] = scanner.nextInt();
      }
    }

    // 输出结果
    System.out.println(countValidAreas(row, col, size, min, matrix));
  }

  /**
   * 统计满足条件的子矩形数量，this method recompute the repeated numbers.
   *
   * @param rows   网格的行数
   * @param cols   网格的列数
   * @param size   子矩形的边长
   * @param minSum 子矩形的最小总和
   * @param matrix 输入网格
   * @return 满足条件的子矩形数量
   */
  private static int countValidAreas(int rows, int cols, int size, int minSum, int[][] matrix) {
    // 遍历所有可能的电站位置，计算该位置的矩形区域发电量
    int ans = 0;
    for (int i = size; i <= rows; i++) {
      for (int j = size; j <= cols; j++) {
        int square = 0;
        for (int x = i - size; x < i; x++) {
          for (int y = j - size; y < j; y++) {
            square += matrix[x][y];
          }
        }
        if (square >= minSum) {
          ans++;
        }
      }
    }
    return ans;
  }

  /**
   * 统计满足条件的子矩形数量
   *
   * @param rows   网格的行数
   * @param cols   网格的列数
   * @param size   子矩形的边长
   * @param minSum 子矩形的最小总和
   * @param matrix 输入网格
   * @return 符合条件的子矩形数量
   */
  public static int countValidAreasOptimal(int rows, int cols, int size, int minSum,
      int[][] matrix) {
    // 1. 构建二维前缀和数组
    int[][] prefixSum = new int[rows + 1][cols + 1]; // 多加一行和一列，用于简化计算
    for (int i = 1; i <= rows; i++) {
      for (int j = 1; j <= cols; j++) {
        // 计算前缀和：当前单元格的值 + 上方 + 左方 - 左上角重叠部分
        prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1]
            - prefixSum[i - 1][j - 1] + matrix[i - 1][j - 1];
      }
    }
    // 2. 遍历所有可能的子矩形，计算其总和并判断是否满足条件
    int count = 0; // 计数器
    for (int i = size; i <= rows; i++) { // 确保子矩形不会超出网格范围
      for (int j = size; j <= cols; j++) {
        // 计算子矩形的总和，公式：
        // 当前矩形总和 = 右下角 - 上方矩形 - 左方矩形 + 左上角重叠部分
        int sum = prefixSum[i][j]
            - prefixSum[i - size][j]
            - prefixSum[i][j - size]
            + prefixSum[i - size][j - size];
        // 判断是否满足最小总和条件
        if (sum >= minSum) {
          count++;
        }
      }
    }
    return count; // 返回符合条件的子矩形数量
  }
}
