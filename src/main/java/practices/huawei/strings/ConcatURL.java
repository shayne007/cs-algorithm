package practices.huawei.strings;

import java.util.Scanner;

/**
 * TODO
 *
 * @since 2025/7/12
 */
public class ConcatURL {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    String s = sc.nextLine();

    String[] arr = s.split(",");

    String prefix = arr.length > 0 && arr[0].length() > 0 ? arr[0] : "/";
    String suffix = arr.length > 1 && arr[1].length() > 0 ? arr[1] : "/";

    System.out.println(getResult(prefix, suffix));
  }

  public static String getResult(String prefix, String suffix) {
    prefix = prefix.replaceAll("/+$", "");
    suffix = suffix.replaceAll("^/+", "");
    return prefix + "/" + suffix;
  }
}
