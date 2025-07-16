package practices.huawei.excel;

import java.util.Scanner;

/**
 * 计算Excel中指定区域的数字总和
 *
 * @since 2025/7/16
 */
public class AreaSum {

  static String[][] table; // 给定表格区域
  static int rows; // 给定表格区域的行数
  static int cols; // 给定表格区域的列数
  static String start; // 选中区域的左上角位置
  static String end; // 选中区域的右下角位置

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    rows = scanner.nextInt();
    cols = scanner.nextInt();

    table = new String[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        table[i][j] = scanner.next();
      }
    }

    String[] range = scanner.next().split(":");
    start = range[0];
    end = range[1];

    System.out.println(getResult());
  }

  public static int getResult() {
    int[] startCell = getPos(start);
    int[] endCell = getPos(end);

    int row1 = startCell[0], col1 = startCell[1];
    int row2 = endCell[0], col2 = endCell[1];

    int totalSum = 0;
    for (int i = row1; i <= row2; i++) {
      for (int j = col1; j <= col2; j++) {
        totalSum += getCellVal(table[i][j]);
      }
    }

    return totalSum;
  }

  // 获取单元格的值
  public static int getCellVal(String cell) {
    /*
     * 单元格内容cell如果以'='开头，则必然是公式
     * */
    if (cell.charAt(0) == '=') {
      return computeFormulaVal(cell);
    }
    /*
     * 单元格内容cell如果不以'='开头，则必然是数字，且是非负正数
     * */
    else {
      return Integer.parseInt(cell);
    }
  }

  private static int computeFormulaVal(String cell) {
    // fn是公式内容
    String fn = cell.substring(1);

    // 如果公式内容fn包含“+”，则可能是两个单元格的双目运算，也可能是单元格和数字的双目运算
    if (fn.contains("+")) {
      return operate(fn.split("\\+"), true);
    }
    // 如果公式内容fn包含“-”，则可能是两个单元格的双目运算，也可能是单元格和数字的双目运算
    else if (fn.contains("-")) {
      return operate(fn.split("-"), false);
    }
    // 如果公式内容fn不包含“+”和“-”，则必然等于某单元格的值，例如=B12
    else {
      return getPosVal(getPos(fn));
    }
  }

  // 双目运算
  public static int operate(String[] ops, boolean isAdd) {
    int op1, op2;

    if (isPos(ops[0])) {
      // 如果操作数1是单元格
      op1 = getPosVal(getPos(ops[0]));
    } else {
      // 如果操作数1是数字
      op1 = Integer.parseInt(ops[0]);
    }

    if (isPos(ops[1])) {
      // 如果操作数2是单元格
      op2 = getPosVal(getPos(ops[1]));
    } else {
      // 如果操作数2是数字
      op2 = Integer.parseInt(ops[1]);
    }

    if (isAdd) {
      // 加法运算
      return op1 + op2;
    } else {
      // 减法运算
      return op1 - op2;
    }
  }

  // 解析单元格坐标  为  矩阵坐标
  public static int[] getPos(String pos) {
    int col = pos.charAt(0) - 65; // A=65
    int row = Integer.parseInt(pos.substring(1)) - 1;
    return new int[]{row, col};
  }

  // 获取矩阵对应坐标的值，并且更新矩阵对应单元格的内容
  public static int getPosVal(int[] pos) {
    int r = pos[0], c = pos[1];
    int val = getCellVal(table[r][c]);
    table[r][c] = val + "";
    return val;
  }

  // 判断一个内容是否为单元格坐标
  public static boolean isPos(String str) {
    char c = str.charAt(0);
    return c <= 'Z' && c >= 'A';
  }

}
