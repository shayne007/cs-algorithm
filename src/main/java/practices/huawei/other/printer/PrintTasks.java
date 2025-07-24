package practices.huawei.other.printer;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * TODO
 *
 * @since 2025/7/12
 */
public class PrintTasks {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = Integer.parseInt(sc.nextLine());
    String[][] tasks = new String[n][];

    for (int i = 0; i < n; i++) {
      String[] s = sc.nextLine().split(" ");
      tasks[i] = s;
    }

    getResult(tasks);
  }

  public static void getResult(String[][] tasks) {
    // print中存放每台打印机的等待队列
    HashMap<String, PriorityQueue<int[]>> print = new HashMap<>();

    // 文件的编号定义为”IN P NUM”事件发生第 x 次，此处待打印文件的编号为x。编号从1开始。
    int x = 1;
    for (int i = 0; i < tasks.length; i++) {
      String[] task = tasks[i];
      // IN,OUT都有type和printId
      String type = task[0];
      String printId = task[1];

      if ("IN".equals(type)) {
        // IN还有priority
        String priority = task[2];
        // arr是打印任务
        int[] arr = {x, Integer.parseInt(priority), i}; // i代表先来后到的顺序
        // 为打印机printId设置打印优先级，打印任务的priority越大，优先级越高
        print.putIfAbsent(printId, new PriorityQueue<>(
            (a, b) -> a[1] != b[1] ? b[1] - a[1] : a[2] - b[2])); // 优先按priority，如果priority相同，按先来后到i
        // 将打印任务加入对应打印机
        print.get(printId).offer(arr);
        x++;
      } else {
        // 打印机等待队列中取出优先级最高的打印任务arr
        if (!print.containsKey(printId) || print.get(printId).isEmpty()) {
          // 如果此时没有文件可以打印，请输出”NULL“。
          System.out.println("NULL");
        } else {
          int[] arr = print.get(printId).poll();
          if (arr != null) {
            System.out.println(arr[0]); // arr[0]是x
          } else {
            System.out.println("NULL");
          }
        }
      }
    }
  }
}
