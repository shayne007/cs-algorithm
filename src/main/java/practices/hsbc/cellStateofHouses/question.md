# Cell State of Houses

A colony of **8 houses** is arranged in a straight line. Each house is a cell with state:

- `1` = active
- `0` = inactive

Each day, every cell updates its state based on its two adjacent neighbors:

- If the two neighbors are in the **same** state (both `0` or both `1`), the cell becomes `0` (inactive) the next day.
- Otherwise, the cell becomes `1` (active) the next day.

Edge cells have only one neighbor; treat the missing neighbor as always `0` (inactive).

All cells must be updated **simultaneously** (use the previous day’s states to compute every new state).

## Input

- Line 1: integer `n` (number of cells), where `n = 8`
- Line 2: eight space-separated integers `cell1 cell2 ... cell8` (current states)
- Line 3: integer `days` (number of days to simulate)

## Output

Print eight space-separated integers representing the state of the cells after `days` days.

## Example
### input
8
1 0 0 0 0 1 0 0
2
### output
1 0 1 1 0 0 0 1


### explanation

