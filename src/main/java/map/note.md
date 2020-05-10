图的表示：如何存储微博、微信等社交网络中的好友关系？

图是一种比树更加复杂的非线性表结构。掌握了图的基础概念和存储方法，我们就能解决复杂社交网络存储问题。

1.如何理解“图”？

 Basic conception:顶点（vertex），边（edge），度（degree）

无向图、有向图，入度（In-degree）和出度（Out-degree），带权图（weighted graph）

稀疏图（Sparse Matrix）

2.邻接矩阵存储方法

二维数组，存储方式简单，获取顶点关系高效，矩阵计算方便，适合数据量小、不限制存储空间情况

![img](https://static001.geekbang.org/resource/image/62/d2/625e7493b5470e774b5aa91fb4fdb9d2.jpg)

3.邻接表存储方法

每个顶点对应存储一个链表，链表中存储与该顶点有关系的其他所有顶点

节省内存空间，缓存不友好，查找效率不高，可使用红黑树等数据结构优化

![img](https://static001.geekbang.org/resource/image/03/94/039bc254b97bd11670cdc4bf2a8e1394.jpg)