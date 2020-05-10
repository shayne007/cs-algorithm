package sort;

/**
 * @author fengsy
 * @date 4/22/20
 * @Description
 */
public class BubbleSort {
    
    static int[] sort(int[] data){
        for (int i = 0; i < data.length; i++) {
            boolean  flag = false;
            for (int j = 0; j < data.length-i-1; j++) {
                if (data[j] >data[j+1]) {
                    int tmp = data[j+1];
                    data[j+1] = data[j];
                    data[j] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        return data;
        
    }

    public static void main(String[] args) {
        int[] sorted = sort(new int[]{24,56,7,11});
        for (int i = 0; i < sorted.length; i++) {
            System.out.println(sorted[i]);
        }
    }

}
