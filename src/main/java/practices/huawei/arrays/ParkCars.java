package practices.huawei.arrays;

import java.util.Scanner;

/**
 * TODO
 *
 * @since 2025/7/13
 */
public class ParkCars {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String str =
        sc.nextLine()
            .replaceAll(",", "")
            .replaceAll("111", "x")
            .replaceAll("11", "x")
            .replaceAll("1", "x");

    int ans = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == 'x') {
        ans++;
      }
    }

    System.out.println(ans);
  }
}
