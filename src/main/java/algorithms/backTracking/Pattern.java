package algorithms.backTracking;

/**
 * TODO
 *
 * @since 2025/7/20
 */
public class Pattern {

  private boolean matched = false;
  private char[] pattern; // 正则表达式
  private int patterjLen; // 正则表达式长度

  public Pattern(char[] pattern, int patterjLen) {
    this.pattern = pattern;
    this.patterjLen = patterjLen;
  }

  public static void main(String[] args) {
    char[] patternArr = "a*".toCharArray();
    char[] charArray = "abc".toCharArray();
    boolean match = new Pattern(patternArr, 2).match(charArray, 3);
    System.out.println(match);
  }

  public boolean match(char[] text, int tlen) { // 文本串及长度
    matched = false;
    rmatch(0, 0, text, tlen);
    return matched;
  }

  private void rmatch(int textIndex, int patternIndex, char[] text, int textLen) {
    if (matched) {
      return; // 如果已经匹配了，就不要继续递归了
    }
    if (patternIndex == patterjLen) { // 正则表达式到结尾了
      if (textIndex == textLen) {
        matched = true; // 文本串也到结尾了
      }
      return;
    }
    if (pattern[patternIndex] == '*') { // *匹配任意个字符
      for (int k = 0; k <= textLen - textIndex; ++k) {
        rmatch(textIndex + k, patternIndex + 1, text, textLen);
      }
    } else if (pattern[patternIndex] == '?') { // ?匹配0个或者1个字符
      rmatch(textIndex, patternIndex + 1, text, textLen);
      rmatch(textIndex + 1, patternIndex + 1, text, textLen);
    } else if (textIndex < textLen && pattern[patternIndex] == text[textIndex]) { // 纯字符匹配才行
      rmatch(textIndex + 1, patternIndex + 1, text, textLen);
    }
  }
}
