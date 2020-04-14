package bigO;

/**
 * @author fengsy
 * @date 4/14/20
 * @Description
 */
public class CalculatExecTime {
    static int cal1(int n){
        int sum = 0;
        for (int i = 1; i < n; i++) {
            sum = sum + i;
        }
        return sum;
    }
    static int cal2(int n){
        int sum = 0;
        int i = 1;
        for (; i < n; ++i) {
            sum = sum + i;
        }
        return sum;
    }

    int cal3(int n) {
        int sum = 0;
        int i = 1;
        int j = 1;
        for (; i <= n; ++i) {
            j = 1;
            for (; j <= n; ++j) {
                sum = sum +  i * j;
            }
        }
        return  sum;
    }

    int cal4(int n) {
        int sum_1 = 0;
        int p = 1;
        for (; p < 100; ++p) {
            sum_1 = sum_1 + p;
        }

        int sum_2 = 0;
        int q = 1;
        for (; q < n; ++q) {
            sum_2 = sum_2 + q;
        }

        int sum_3 = 0;
        int i = 1;
        int j = 1;
        for (; i <= n; ++i) {
            j = 1;
            for (; j <= n; ++j) {
                sum_3 = sum_3 +  i * j;
            }
        }

        return sum_1 + sum_2 + sum_3;
    }
    public static void main(String[] args) {

        long before = System.currentTimeMillis();
        //cal2(1000000000);
        System.out.println(cal1(1000000000));
        System.out.println(cal2(1000000000));
        long after = System.currentTimeMillis();
        System.out.println(after - before);

    }
}
