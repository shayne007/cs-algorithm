package practices.huawei.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 输入一行字符串，一行词库：ilovechina i,love,china,ch,na,ve,lo,this,is,the,word
 * <p>
 * 输出： 	i,love,china
 *
 * @since 2025/7/20
 */
public class Tokenizer {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String[] sentences = sc.nextLine().split("[,.;]");
    String[] words = sc.nextLine().split("[,.;]");

    System.out.println(getResult(sentences, words));
  }

  public static String getResult(String[] sentences, String[] words) {
    // wordSet 记录词库词汇
    HashSet<String> wordSet = new HashSet<>(Arrays.asList(words));

    // queue记录待分词语句
    LinkedList<String> queue = new LinkedList<>(Arrays.asList(sentences));

    // ans记录最终分词结果
    LinkedList<String> ans = new LinkedList<>();

    while (queue.size() > 0) {
      // 待分词的句子
      String sentence = queue.removeFirst();

      int r = sentence.length();
      for (; r > 0; r--) {
        // 截取句子 [0,r) 范围子串词汇, 这样的就能实现优先最长匹配，并且由于是必须从0索引开始截取，因此满足了分词顺序优先
        String fragment = sentence.substring(0, r);

        // 若词库中是否存在该子串词汇
        if (wordSet.contains(fragment)) {
          // 则将对应子串词汇纳入结果
          ans.addLast(fragment);
          wordSet.remove(fragment); // 我理解词库中每个词汇只能使用一次，因此这里将词库中使用过的词汇移除

          // 若子串词汇只是句子部分，则句子剩余部分还要继续去词库中查找
          if (r < sentence.length()) {
            queue.addFirst(sentence.substring(r));
          }

          break;
        }
      }

      // 没有在词库中找到对应子串词汇，则输出单个字母
      if (r == 0) {
        // 注意，这里只放一个字母到结果中，句子剩余部分继续去词库中查找
        ans.add(sentence.charAt(0) + "");

        if (sentence.length() > 1) {
          queue.addFirst(sentence.substring(1));
        }
      }
    }

    StringJoiner sj = new StringJoiner(",");
    ans.forEach(sj::add);

    return sj.toString();
  }
}
