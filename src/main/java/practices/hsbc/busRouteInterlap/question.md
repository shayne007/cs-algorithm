Given a route in a straight line. N buses operate between various bus stations. There is a workstation at the start of the route. The distances of the bus stations from the workstation are calculated. The transportation authority wishes to decrease the number of buses that it operates in the city. If any buses are found to have overlapping routes, then these buses will be replaced by a single bus. The authority wishes to determine how many buses will remain after the buses with overlapping routes have been eliminated.

Write an algorithm to find how many buses will remain after the buses with overlapping routes have been eliminated.

Input:
The first line of the input consists of two space-separated integers - busStation_row and busStation_col, representing the number of buses running on the route (N) and number of bus stations for each bus showing the starting and ending bus stations (M).

The next N lines consist of M space-separated integers representing the distance of the starting and ending bus stations of N buses from the workstation.

Output:
Print an integer representing how many buses will remain after the buses with overlapping routes have been eliminated.

Constraints:
0 ≤ busStation_row ≤ 10^5
0 ≤ busStations[i][0] < busStations[i][1] ≤ 10^6
busStations are the starting and ending bus stations of a bus.
0 ≤ i < busStation_row

Example Input:
4 2
2 8
6 10
12 14
12 20

Example Output:
2