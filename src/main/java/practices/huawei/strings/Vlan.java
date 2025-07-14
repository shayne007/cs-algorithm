package practices.huawei.strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * TODO
 *
 * @since 2025/7/13
 */
public class Vlan {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    // 输入VLAN资源池
    String input = sc.nextLine();
    // 输入业务要申请的VLAN
    Integer destVlan = Integer.parseInt(sc.nextLine());

    // 解析VLAN资源池
    List<Integer> vlanPool = parseVlanPool(input);

    // 对VLAN资源池进行升序排序
    Collections.sort(vlanPool);

    // 从VLAN资源池中移除申请的VLAN
    vlanPool.remove(destVlan);

    // 格式化VLAN资源池
    String result = formatVlanPool(vlanPool);
    System.out.println(result);
  }

  // 解析VLAN资源池
  private static List<Integer> parseVlanPool(String input) {
    List<Integer> vlanPool = new ArrayList<Integer>();
    // 根据逗号分割VLAN资源池中的VLAN
    String[] vlanGroup = input.split(",");
    for (String vlanItem : vlanGroup) {
      if (vlanItem.contains("-")) {
        // 如果VLAN是连续的，根据连字符分割开始VLAN和结束VLAN
        String[] vlanItems = vlanItem.split("-");
        Integer start = Integer.parseInt(vlanItems[0]);
        Integer end = Integer.parseInt(vlanItems[1]);
        // 将连续的VLAN添加到VLAN资源池中
        for (int j = start; j <= end; j++) {
          vlanPool.add(j);
        }
      } else {
        // 如果VLAN是单个的，直接添加到VLAN资源池中
        vlanPool.add(Integer.parseInt(vlanItem));
      }
    }
    return vlanPool;
  }

  // 格式化VLAN资源池
  private static String formatVlanPool(List<Integer> vlanPool) {
    StringBuilder result = new StringBuilder();
    Integer last = null;
    for (int index = 0; index < vlanPool.size(); index++) {
      if (last == null) {
        // 如果是第一个VLAN，直接添加到结果中
        result.append(vlanPool.get(index));
        last = vlanPool.get(index);
      } else {
        if (vlanPool.get(index) - last == 1) {
          // 如果与上一个VLAN相差1，表示是连续的VLAN
          if (result.toString().endsWith("-" + last)) {
            // 如果结果中最后一个VLAN已经是连续的VLAN的结束VLAN，替换为当前VLAN
            result.replace(result.lastIndexOf(last.toString()), result.length(),
                vlanPool.get(index).toString());
          } else {
            // 否则添加连字符和当前VLAN
            result.append("-").append(vlanPool.get(index));
          }
        } else {
          // 如果与上一个VLAN不连续，直接添加逗号和当前VLAN
          result.append(",").append(vlanPool.get(index));
        }
        last = vlanPool.get(index);
      }
    }
    return result.toString();
  }
}
