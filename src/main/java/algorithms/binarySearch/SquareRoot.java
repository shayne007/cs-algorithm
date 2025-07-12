package algorithms.binarySearch;

/**
 * 求取一个数的平方根，使用二分法
 *
 * @since 2025/3/6
 */
public class SquareRoot {
  public static void main(String[] args) {
    System.out.println(sqrt(25));
    System.out.println(sqrt(6));
    System.out.println(sqrt(9));
  }
    public static double sqrt(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("x must be non-negative");
        }
        double left = 0;
        double right = x;
        double mid = 0;
        double precision = 0.00001;
        while (Math.abs(right - left) > precision) {
            mid = (left + right) / 2;
            if (mid * mid == x) {
                return mid;
            } else if (mid * mid < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return mid;
        }
}
