package practices.hsbc.kidsTeam;

/**
 * TODO
 *
 * @since 2026/5/25
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {

  static int[] parent;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // Read number of kids
    int N = Integer.parseInt(br.readLine().trim());

    // Read strengths
    long[] strengths = new long[N];
    String[] strengthTokens = br.readLine().trim().split(" ");
    for (int i = 0; i < N; i++) {
      strengths[i] = Long.parseLong(strengthTokens[i]);
    }

    // Read numCards and pairCount
    String[] cardInfo = br.readLine().trim().split(" ");
    int numCards = Integer.parseInt(cardInfo[0]);

    // Initialize Union-Find
    parent = new int[N];
    for (int i = 0; i < N; i++) {
      parent[i] = i;
    }

    // Process each card (edge)
    for (int i = 0; i < numCards; i++) {
      String[] pair = br.readLine().trim().split(" ");
      int pos1 = Integer.parseInt(pair[0]) - 1;
      int pos2 = Integer.parseInt(pair[1]) - 1;
      union(pos1, pos2);
    }

    // Use HashMap to sum strengths by root
    Map<Integer, Long> teamSum = new HashMap<>();
    for (int i = 0; i < N; i++) {
      int root = find(i);
      teamSum.put(root, teamSum.getOrDefault(root, 0L) + strengths[i]);
    }

    // Find maximum team strength
    long maxPower = 0;
    for (long sum : teamSum.values()) {
      if (sum > maxPower) {
        maxPower = sum;
      }
    }

    System.out.println(maxPower);
  }

  static int find(int x) {
    if (parent[x] != x) {
      parent[x] = find(parent[x]);
    }
    return parent[x];
  }

  static void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);
    if (rootX != rootY) {
      parent[rootY] = rootX;
    }
  }
}