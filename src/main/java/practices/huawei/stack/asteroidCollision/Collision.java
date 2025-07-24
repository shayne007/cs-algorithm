package practices.huawei.stack.asteroidCollision;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 荒岛求生 https://leetcode.cn/problems/asteroid-collision/
 *
 * @since 2025/7/12
 */
public class Collision {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    // 创建一个整数列表，用于存储输入的整数
    // 输入 5 10 8 -8 -5
    List<Integer> forces = Arrays.stream(scanner.nextLine().split(" "))
        .mapToInt(Integer::parseInt)
        .boxed()
        .collect(Collectors.toList());

    // 如果people列表的大小大于30000，则输出-1
    if (forces.size() > 30000) {
      System.out.println(-1);
      return;
    }
    // 调用asteroidCollision方法，并将结果输出
    System.out.println(asteroidCollision(forces));
  }

  /**
   * 计算碰撞结果
   *
   * @param forces 输入的整数列表
   * @return 碰撞结果
   */
  public static int asteroidCollision(List<Integer> forces) {
    // 使用ArrayDeque作为栈
    Deque<Integer> stack = new ArrayDeque<>();
    // 遍历输入的整数列表
    for (int force : forces) {
      // 初始化一个布尔变量alive为true
      boolean alive = true;

      // 当alive为true且force小于0，且stack不为空，且stack中最后一个元素大于0时，执行循环
      while (alive && force < 0 && !stack.isEmpty() && stack.peek() > 0) {
        // 更新alive的值，判断当前force是否比stack顶部元素的相反数大
        alive = stack.peek() < -force;

        // 如果stack中最后一个元素小于等于force的相反数，则移除该元素，同时更新force的值
        if (stack.peek() <= -force) {
          Integer pop = stack.pop();
          force = force + pop;
        }
      }

      // 如果alive为true，则将force入栈
      if (alive) {
        stack.push(force);
      }
    }
    System.out.println(stack);
    return stack.size();
  }
}
