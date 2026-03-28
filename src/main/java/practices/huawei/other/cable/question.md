# Cable Connection Problem

## Problem Statement

A company has N computer systems arranged in a row. Due to a power supply fault, some systems are OFF while others remain ON. Employees with OFF systems cannot work, and the company wants to minimize downtime.

Adam, the technical head, needs to connect all OFF systems to the nearest powered ON system using the shortest possible cable length. Each system's distance from the first system is known.

**Your task:** Write an algorithm to find the minimum cable length required to power all OFF systems.

---

## Input Format

| Line | Description | Constraints |
|------|-------------|-------------|
| 1 | Number of systems (N) | 1 ≤ N ≤ 10⁵ |
| 2 | System states (ON=1, OFF=0) | N space-separated integers |
| 3 | Distances from first system | 1 ≤ dist[i] < dist[i+1] ≤ 10⁹ |

### Sample Input
```
3
1 0 0
1 5 6
```

---

## Output

- Print the minimum total cable length required
- If all systems are already ON, print 0

### Sample Output
```
5
```

---

## Constraints

- At least one system is guaranteed to be ON
- All systems are functional (no hardware faults)
- Distances are strictly increasing: dist[i] < dist[i+1]

---

## Example Walkthrough

### Input:
```
3        ← 3 systems
1 0 0    ← System 1: ON, System 2: OFF, System 3: OFF
1 5 6    ← Distances from first system
```

### Solution:
1. **System 2 (OFF)** → Connect to nearest ON system (System 1)
   Cable length = |5 - 1| = 4

2. **System 3 (OFF)** → Can connect to System 2 (now powered)
   Cable length = |6 - 5| = 1

3. **Total cable length** = 4 + 1 = **5**

---

## Algorithm Insight

The optimal strategy connects each OFF system to its nearest powered neighbor, either to the left or right. Since distances are strictly increasing, we can efficiently find the nearest ON systems using binary search.