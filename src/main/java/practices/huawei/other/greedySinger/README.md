题目描述
一个歌手准备从A城去B城参加演出。

按照合同，他必须在 T 天内赶到
歌手途经 N 座城市
歌手不能往回走
每两座城市之间需要的天数都可以提前获知。
歌手在每座城市都可以在路边卖唱赚钱。

经过调研，歌手提前获知了每座城市卖唱的收入预期：
如果在一座城市第一天卖唱可以赚M，后续每天的收入会减少D（第二天赚的钱是 M - D，第三天是 M - 2D ...）。如果收入减少到 0 就不会再少了。
歌手到达后的第二天才能开始卖唱。如果今天卖过唱，第二天才能出发。
贪心的歌手最多可以赚多少钱？

输入描述
第一行两个数字 T 和 N，中间用空格隔开。

T 代表总天数，0 < T < 1000
N 代表路上经过 N 座城市，0 < N < 100
第二行 N+1 个数字，中间用空格隔开。代表每两座城市之间耗费的时间。

其总和 ≤ T。
接下来 N 行，每行两个数字 M 和 D，中间用空格隔开。代表每个城市的输入预期。

0 < M < 1000
0 < D < 100
输出描述
一个数字。代表歌手最多可以赚多少钱。以回车结束。

用例
输入	
10 2
1 1 2
120 20
90 10

输出	
540

说明
总共10天，路上经过2座城市。
路上共花 1+1+2=4 天
剩余6天最好的计划是在第一座城市待3天，在第二座城市待3天。
在第一座城市赚的钱：120 + 100 + 80 = 300
在第二座城市赚的钱：90 + 80 + 70 = 240
一共 300 + 240 = 540。