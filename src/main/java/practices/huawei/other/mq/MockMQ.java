package practices.huawei.other.mq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 输入为两行。
 * <p>
 * 第一行为2N个正整数，代表发布者发送的N个消息的时刻和内容（为方便解折，消息内容也用正整数表示）。第一个数字是第一个消息的发送时刻，第二个数字是第一个消息的内容，以此类推。用例保证发送时刻不会重复，但注意消息并没有按照发送时刻排列。
 * <p>
 * 第二行为2M个正整数，代表M个消费者订阅和取消订阅的时刻。第一个数字是第一个消费者订阅的时刻，第二个数字是第一个消费者取消订阅的时刻，以此类推。用例保证每个消费者的取消订阅时刻大于订阅时刻，消费者按优先级升序排列。
 * <p>
 * 两行的数字都由空格分隔。N不超过100，M不超过10，每行的长度不超过1000字符
 * <p>
 * 输出为M行，依次为M个消费者收到的消息内容，消息内容按收到的顺序排列，且由空格分隔；
 * <p>
 * 若某个消费者没有收到任何消息，则对应的行输出-1
 * <p>
 * 用例
 * <p>
 * 输入line1： 2 22 1 11 4 44 5 55 3 33
 * <p>
 * 输入line2： 1 7 2 3
 * <p>
 * 输出line1： 11 33 44 55
 * <p>
 * 输出line2： 22
 * <p>
 * 说明 消息11在1时刻到达，此时只有第一个消费者订阅，消息发送给它；
 * <p>
 * 消息22在2时刻到达，此时两个消费者都订阅了，消息发送给优先级最高的第二个消费者；
 * <p>
 * 消息33在时刻3到达，此时只有第一个消费者订阅，消息发送给它；
 * <p>
 * 余下的消息按规则也是发送给第一个消费者
 *
 * @since 2025/7/12
 */
public class MockMQ {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    // 读取并解析消息发布者的数据
    int[] msgArr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt)
        .toArray();
    // 读取并解析订阅者的数据
    int[] consArr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt)
        .toArray();
    // 调用处理消息的函数
    processMessages(msgArr, consArr);
  }

  // 处理消息发布和订阅的函数
  public static void processMessages(int[] msgArr, int[] consArr) {
    int msgLen = msgArr.length;  // 消息发布者数组的长度
    int consLen = consArr.length;  // 订阅者数组的长度
    // 将消息发布者的数据按时间和内容分组，存储为二维数组
    int[][] msgs = new int[msgLen / 2][];
    int i = 0;
    int mIdx = 0;
    while (i < msgLen) {
      msgs[mIdx++] = new int[]{msgArr[i], msgArr[i + 1]};  // 每个发布者的时间和内容
      i += 2;  // 步进2个元素
    }
    // 将订阅者的数据按订阅时间和退订时间分组，存储为二维数组
    int[][] consumers = new int[consLen / 2][];
    int j = 0;
    int cIdx = 0;
    while (j < consLen) {
      consumers[cIdx++] = new int[]{consArr[j], consArr[j + 1]};  // 每个订阅者的订阅时间和退订时间
      j += 2;  // 步进2个元素
    }
    // 按发布的时间对消息进行升序排序
    Arrays.sort(msgs, (x, y) -> x[0] - y[0]);
    // 创建一个 ArrayList 用于存储每个订阅者接收到的消息
    ArrayList<ArrayList<Integer>> receivedMsgs = new ArrayList<>();
    for (int k = 0; k < consumers.length; k++) {
      receivedMsgs.add(new ArrayList<>());  // 每个订阅者的消息列表
    }
    // 遍历所有发布的消息
    int pubIdx = 0;
    while (pubIdx < msgs.length) {
      int msgTime = msgs[pubIdx][0];  // 获取当前消息的发布时间
      int msgContent = msgs[pubIdx][1];  // 获取当前消息的内容
      // 遍历所有订阅者（从后往前遍历，优先分配给有效的订阅者）
      int subIdx = consumers.length - 1;
      while (subIdx >= 0) {
        int subTime = consumers[subIdx][0];  // 订阅者的订阅时间
        int unsubTime = consumers[subIdx][1];  // 订阅者的退订时间
        // 如果消息发布时，订阅者处于有效订阅期
        if (msgTime >= subTime && msgTime < unsubTime) {
          receivedMsgs.get(subIdx).add(msgContent);  // 该订阅者接收到消息
          break;  // 找到一个有效的订阅者后就停止继续查找
        }
        subIdx--;  // 检查下一个订阅者
      }
      pubIdx++;  // 处理下一个发布的消息
    }
    // 输出每个订阅者接收到的消息内容
    int idx = 0;
    while (idx < receivedMsgs.size()) {
      ArrayList<Integer> contentList = receivedMsgs.get(idx);
      if (contentList.isEmpty()) {
        System.out.println("-1");  // 如果没有接收到任何消息，输出 -1
      } else {
        // 使用 StringJoiner 格式化输出接收到的消息内容
        StringJoiner sj = new StringJoiner(" ");
        for (int content : contentList) {
          sj.add(Integer.toString(content));  // 添加每条消息
        }
        System.out.println(sj.toString());  // 输出消息内容
      }
      idx++;  // 处理下一个订阅者
    }
  }
}
