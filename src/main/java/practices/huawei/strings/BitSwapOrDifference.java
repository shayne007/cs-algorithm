package practices.huawei.strings;

import java.util.Scanner;

/**
 * TODO
 *
 * @since 2025/7/25
 */
public class BitSwapOrDifference {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    int n = Integer.parseInt(in.nextLine());// 6
    String a = in.nextLine();// 011011
    String b = in.nextLine();// 110110

    System.out.println(countSwapsWithDifferentOr(a, b));
  }

  public static int countSwapsWithDifferentOr(String A, String B) {
    int n = A.length();
    int originalOr = Integer.parseInt(A, 2) | Integer.parseInt(B, 2);
    int count = 0;

    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (A.charAt(i) == A.charAt(j)) {
          continue;
        }

        // Swap bits at positions i and j in A
        char[] aChars = A.toCharArray();
        char temp = aChars[i];
        aChars[i] = aChars[j];
        aChars[j] = temp;
        String swappedA = new String(aChars);

        // Compute OR after swap
        int swappedOr = Integer.parseInt(swappedA, 2) | Integer.parseInt(B, 2);

        if (swappedOr != originalOr) {
          count++;
        }
      }
    }

    return count;
  }
}
