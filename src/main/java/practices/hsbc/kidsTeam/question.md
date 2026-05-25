# HSBC

## Problem

There are \(N\) kids standing in a line. Kid \(i\) (1-indexed) has strength \(X_i\).

You draw \(M\) cards. Each card contains a pair \([a, b]\), meaning kid \(a\) and kid \(b\) must be on the same team.

Team membership is transitive: if \([1, 4]\) and \([4, 3]\) are drawn, then kids \(1, 4, 3\) form one team. Any kid that is not connected to others forms a one-person team.

The power of a team is the sum of strengths of all kids on that team. Output the power of the strongest (highest-power) team.

## Input

- The first line contains an integer \(N\), the number of kids.
- The second line contains \(N\) space-separated integers \(X_1, X_2, \dots, X_N\), the kids' strengths.
- The third line contains two integers \(M\) and \(C\), where \(M\) is the number of cards drawn and \(C\) is always \(2\) (each card gives one pair).
- The next \(M\) lines each contain two integers \(a\) and \(b\) (\(1 \le a, b \le N\)), indicating a pair that must be on the same team.

## Output

Print an integer representing the power of the winning team.

## Constraints

\[1 \leq N \leq 10^5\]

\[0 \leq M \leq 10^5\]

\[0 \leq X_i \leq 10^{12}\]

## Example

### Input:
```text
6
11 2 3 15 8 22
3 2
1 2
2 3
4 5
```

### Output:
```text
23
```
