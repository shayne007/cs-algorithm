题目描述
Excel工作表中对选定区域的数值进行统计的功能非常实用。
仿照Excel的这个功能，请对给定表格中选中区域中的单元格进行求和统计，并输出统计结果。
为简化计算，假设当前输入中每个单元格内容仅为数字或公式两种。
如果为数字，则是一个非负整数，形如3、77
如果为公式，则固定以=开头，且仅包含下面三种情况：
等于某单元格的值，例如=B12
两个单元格的双目运算（仅为+或-），形如=C1-C2、C3+B2
单元格和数字的双目运算（仅为+或-），形如=B1+1、100-B2

注意：
公式内容都是合法的，例如不存在，=C+1、=C1-C2+B3,=5、=3+5
不存在循环引用，例如A1=B1+C1、C1=A1+B2
内容中不存在空格、括号

输入描述
第一行两个整数rows cols，表示给定表格区域的行数和列数，1<=rows<=20，1<=cols<=26。
接下来rows行，每行cols个以空格分隔的字符串，表示给定表格values的单元格内容。
最后一行输入的字符串，表示给定的选中区域，形如A1:C2。

输出描述
一个整数，表示给定选中区域各单元格中数字的累加总和，范围-2,147,483,648 ~ 2,147,483,647

备注
表格的行号1~20，列号A~Z，例如单元格B3对应values[2][1]。
输入的单元格内容（含公式）中数字均为十进制，值范围[0, 100]。
选中区域：冒号左侧单元格表示选中区域的左上角，右侧表示右下角，如可以为B2:C10、B2:B5、B2:Y2、B2:B2,无类似C2:B2、C2:A1的输入。

用例
输入	
1 3
1 =A1+C1 3
A1:C1

输出	8

输入	
5 3
10 12 =C5
15 5 6
7 8 =3+C2
6 =B2-A1 =C2
7 5 3
B2:C4

输出	29





题目解析
本题逻辑不难，但是实现起来比较麻烦。

我的解题思路如下：

首先，要搞清楚Excel表格坐标和matrix输入矩阵的索引的对应关系，比如上面用例中，输入的matrix矩阵为：[ ["1", "=A1+C1", "3"] ]

其中“1”值，对应矩阵 martix[0][0]，而对应的Excel表格坐标是A1，其中A代表列号，1代表行号。

因此，我们容易得到Excel表格坐标和matrix输入矩阵的索引的对应关系：

列对应关系：String('A').charCodeAt() - 65 = 0  （PS：'A'的ASCII码值为65）
行对应关系：1 - 1 = 0
解下来，我们需要弄清楚，如何将Excel坐标，如A1，B2，C3中的列号和行号解析出来，因为只有解析出来，才能方便处理，之后才能对应到matrix的索引。

这里我们使用了正则表达式的捕获组，正则为：/^(A-Z)(\d+)$/



接下来，我们就可以实现根据Excel坐标，获取到matrix矩阵元素的逻辑了，我们定义一个方法getCell，入参Excel坐标，然后通过上面的正则解析出来对应列号、行号，然后再根据Excel列号、行号转化求得matrix矩阵的行索引、列索引，进而求得matrix矩阵对应索引的值。

此时，取得的值有两类：

1、非公式的值，比如1

2、公式，以=开头

对于非公式的值，直接将其转为数值后返回；

对于公式，又分为三种情况：

=A1+B1，即Excel坐标之间的运算
=A1-2，即Excel坐标和数值之间的运算
=A1，即Excel坐标
我们可以通过getCell方法获取到Excel坐标对应的值，然后再来运算