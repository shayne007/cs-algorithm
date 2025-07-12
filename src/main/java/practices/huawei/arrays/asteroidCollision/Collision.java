package practices.huawei.arrays.asteroidCollision;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://leetcode.cn/problems/asteroid-collision/
 *
 * @since 2025/7/12
 */
public class Collision {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // 读取一行输入，并使用空格分隔字符串
    String[] input = scanner.nextLine().split(" ");

    // 创建一个整数列表，用于存储输入的整数
    List<Integer> people = new ArrayList<>();

    // 将输入的字符串数组转换为整数，并添加到people列表中
    for (String s : input) {
      people.add(Integer.parseInt(s));
    }

    // 如果people列表的大小大于30000，则输出-1
    if (people.size() > 30000) {
      System.out.println(-1);
    } else {
      // 调用asteroidCollision方法，并将结果输出
      int result = asteroidCollision(people);
      System.out.println(result);
    }
  }

  // 定义一个名为asteroidCollision的方法，接受一个整数列表作为参数
  public static int asteroidCollision(List<Integer> people) {
    // 创建一个新的ArrayList，用于存储幸存者
    List<Integer> survivors = new ArrayList<>();

    // 遍历输入的整数列表
    for (int person : people) {
      // 如果person等于0，则返回-1
      if (person == 0) {
        return -1;
      }

      // 初始化一个布尔变量alive为true
      boolean alive = true;

      // 当alive为true且person小于0，且survivors不为空，且survivors列表中最后一个元素大于0时，执行循环
      while (alive && person < 0 && !survivors.isEmpty()
          && survivors.get(survivors.size() - 1) > 0) {
        // 更新alive的值，判断当前person是否比survivors列表中最后一个元素的相反数大
        alive = survivors.get(survivors.size() - 1) < -person;

        // 如果survivors列表中最后一个元素小于等于person的相反数，则移除该元素
        if (survivors.get(survivors.size() - 1) <= -person) {
          person = person + survivors.get(survivors.size() - 1);
          survivors.remove(survivors.size() - 1);
        }
      }

      // 如果alive为true，则将person添加到survivors列表中
      if (alive) {
        survivors.add(person);
      }
    }

    // 返回survivors列表的大小
    return survivors.size();
  }
}
