package practices.huawei.strings;

import java.util.Scanner;

/**
 * TODO
 *
 * @since 2025/7/24
 */
public class BitOperation {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    int n = Integer.parseInt(in.nextLine());// 6
    String A = in.nextLine();// 011011
    String B = in.nextLine(); // 110110
    char[] charsToCheck = A.toCharArray();
    String standard;
    for (int i = 0; i < n; i++) {
      if (charsToCheck[i] == '1' || B.charAt(i) == '1') {
        charsToCheck[i] = '1';
      }
    }
    standard = String.valueOf(charsToCheck);

    int count = doCheck(standard, A, B);

    System.out.println(count);

  }

  private static int doCheck(String standard, String A, String B) {
    int count = 0;
    int n = A.length();
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (A.charAt(i) == A.charAt(j)) {
          continue;
        }
        char[] tempChars = A.toCharArray();
        char temp = tempChars[j];
        tempChars[j] = tempChars[i];
        tempChars[i] = temp;

        if (standard.charAt(i) == '1' && B.charAt(i) == '0' && tempChars[i] == '0' || (
            standard.charAt(i) == '0' && tempChars[i] == '1')) {
          count++;
        } else if (standard.charAt(j) == '1' && B.charAt(j) == '0' && tempChars[j] == '0' || (
            standard.charAt(j) == '0' && tempChars[j] == '1')) {
          count++;
        }
      }
    }
    return count;
  }
}