package bigO;

/**
 * @author fengsy
 * @date 4/14/20
 * @Description
 */
public class CalculatExecTimeBestAndWorst {

    // n表示数组array的长度
    static int find(int[] array, int n, int x) {
        int i = 0;
        int pos = -1;
        for (; i < n; ++i) {
            if (array[i] == x) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    int n = 10;
    // array表示一个长度为n的数组
    // 代码中的array.length就等于n
    int[] array = new int[n];
    int count = 0;

     void insert(int val) {
        if (count == array.length) {
            int sum = 0;
            for (int i = 0; i < array.length; ++i) {
                sum = sum + array[i];
            }
            array[0] = sum;
            count = 1;
        }

        array[count] = val;
        ++count;
    }


    int old_array[] = new int[10];
    int len = 10;
    int i = 0;

    void add(int element) {
        if (i >= len) {
            int new_array[] = new int[len*2];
            for (int j = 0; j < len; ++j) {
                new_array[j] = old_array[j];
            }
            old_array = new_array;
            len = 2 * len;
        }
        old_array[i] = element;
        ++i;
    }
    public static void main(String[] args) {
        int[] array = new int[10];
        int n = array.length;
        System.out.println(find(array,n,0));
    }
}
