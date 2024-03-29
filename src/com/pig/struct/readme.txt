

2019年9月8日22:38:53
6、删除任意节点；
8、求一个数（不一定存在该节点）的floor和ceil


successor、predecessor：前驱、后继

rank： 给出一个元素，求它的排名
select：排名第10名的元素，取出它的元素
维护每个节点的size，来实现rank和select

维护深度值 depth；

支持重复元素的二分搜索树；  左子树<= 父节点；   右子树 > 父节点；
或者说，记录一个count变量给每个节点；

====================================================================

集合和映射，主要是二分搜索树实现的数据结构；

集合，二分搜索树，
treeset 红黑树，自平衡的二叉树，log2n 时间复杂度

映射，Map
本质和集合并没有太大差别，只不过每个节点存储两个数据，K，V

基于集合来实现映射；或者基于映射来实现集合； 比如HashMap和HashSet

一般是基于映射的底层来实现集合；比如将映射的value设为空，只考虑key，那就变成集合了；

=========================
树的拓展：堆、线段树、字典树(Trie)、并查集
=========================
堆和优先队列：

堆来实现优先队列

堆本身也是一棵树：
堆也有很多种，比较主流的叫做 二叉堆，满足一些特殊性质的二叉树；

二叉堆是一棵完全二叉树

完全二叉树在数组中的索引，求第一个非叶子节点在数组中的索引，利用最后一个元素的索引来求；

二叉堆，父节点>=其子节点 ，这叫做最大堆；
注意
1、最大堆只是最上面的那个数是最大的，但是并不是层级越上面的数字越大
2、

最小堆：。。。。

优先队列，堆的应用

java 自带的优先队列，可以接一个Comparator，来自定义元素的比较方式，覆盖自己的比较方式，比如覆盖String自己的比较方式；

Comparable 和 Comparator
其中 Comparator 往往可以直接用匿名内部类直接初始化；
=========================================================================
线段树（区间树）：

线段树                  数组
创建O(4n)               O(n)
查询O(logN)             O(n)
更新O(logN)             o(n)

非常重要：
    线段树的应用场景：

    用于统计一个区间，并且该区间中的元素需要经常进行更新，可以应用线段树；

    但是一定要注意，线段树这种数据结构不考虑改区间本身的大小发生变化； 区间本身大小是不变的，举个例子，一个数组长度是不变的，
    虽然里头每个元素可能经常变化；

    如果区间内的数据是静态的，那么可以用另外一种经典的方式进行查询,预处理后，查询的时间复杂度为O(1)，看题目；但是更新操作还是需要应用线段树，即使是静态的，更新操作的
    时间复杂度在数组中是O(n)


segment tree

区间染色：

基于区间的统计查询：  统计查询，假设要非常频繁的调用查询或者修改，如果每次都用数组，那么效率： 次数*log(N)
查找一个区间[i,j]的最大值，最小值，或者区间数字和；

也就是区间的
更新:更新区间中一个元素或者一个区间的值

查询：查询或者更新一个区间[i,j]的最大值，最小值，或者区间数字和；
数组：O(n）
线段树：O(logN）

数组实现：都是O(n)

使用线段树：都是O(logN)

做完创建、查询、修改以后，再来看时间和空间的复杂度，和数组对比的优势；

https://cloud.tencent.com/developer/article/1149069
https://www.cnblogs.com/iris001999/articles/9058603.html
https://www.jianshu.com/p/5769dcb06221

线段树不是完全二叉树

线段树是平衡二叉树：叶子节点高度不会相差1；

完全二叉树一定是平衡二叉树；

满二叉树：h-1层，有2^(h-1) 个元素   0~h-1层，一共有2^h - 1个节点，
         下一层是上一层的两倍节点数量； 最后一层的节点数量大概是总数的1/2，也就是等于前面所有层的和;

第n层(从0层算起)的个数是2^n ，前0~n-1层的总数是2^n - 1 一共的数量是 2^(n+1) - 1个；

创建线段树，如果有n个元素，那么数组需要开辟4n的空间；

int mid = (left+right) / 2;  可以计算出left和right的中间数； 但是有可能left+right会出现整型溢出，
所以，写成left+ (right - left) / 2比较好，这样也是中间数;

=========================================================================
Trie
字典树
专门处理字符串

时间复杂度为O(w)  w为字符串的长度；查询每个条目，和字段中一共有多少个条目无关；

操作：
1、添加单词
2、查询单词
3、前缀查询
4、简单的字符串模式匹配，递归   WordDictionary 不懂什么意思，需要做个例子打印一下看看

拓展：
1、字典树删除
2、压缩字典树
3、三叉字典树

=========================================================
统计一个字母出现的频次：  字母作为key，转化为索引，频次作为value

Hash表  思想 ：空间换时间；但是哈希是时间和空间之间的平衡；索引分布越均匀越好

把相关内容(key) 根据hash函数转化为数组索引，最后在索引的时候，O(1)的时间复杂度；

把键转化为索引，然后O(1)索引到相关键以后，对value进行操作；

比如哈希函数 index = ch - 'a'

f(ch) = ch - 'a';

---- 哈希函数的设计：
键 通过哈希函数得到的 索引 分布越均匀越好

基础、通用、一般的方法：

key通过哈希函数后的整数值作为数组下标来索引；
该位置设置的value既可以是key本身的原始值，也可以根据业务需求赋予该位置指定的值；

键的种类：

一 、整型；
1、小范围内的正整数；
2、小范围内负整数进行偏移；
3、大整数,比如身份证号，取模，比如取后四位，那就是 %10000，身份证取模，因为日期最大的问题，可能分布不均匀；
    并且没有好好利用全部信息；

    没有什么规律的随机大整数，随意模一个数；

    规律比较强的大整数，一个简单的方法：模一个素数，数论理论范畴；

    怎么样取素数？

    http://planetmath.org/goodhashtableprimes

二、浮点型 因为占用32位或者64位，所以可以比较容易转成整型处理

三、字符串 转化为整型
比如，参照166
166 = 1 * 10^2 + 6 * 10^1 + 6 * 10^0

那么 code ：
code = c * 26^3 +o*26^2+d*26^1+e*26^0

code = c * B^3 +o*B^2+d*B^1+e*B^0

hash(code) = (c * B^3 +o*B^2+d*B^1+e*B^0) %M(就是选择的素数，根据那个网站) 素数就是hash数组的大小
因为可能长度很长，所以往往要转化一下,等价方式，速度会快一些：
hash(code) = ((((c * B)+o)*B+d)*B+e)%M

为了防止大整形移除，转为下面的方式：

hash(code) = ((((c % M) * B) + o ) % M * B + d ) % M * B + e ) % M

但是整型溢出不会抛出异常，计算机底层会到头循环处理；

java hashcode方法只是将数据类型和整型对应起来，整型可正可负，因为还不知道M素数的大小，具体怎么对应哈希表，那要自己实现

也就是：
hashCode(k1) % M;

int
(hashCode(k1) & 0x7fffffff) % M, 最高位符号位；

M既是数组的大小；

复合类型：
    复写hashcode：

HashSet：哈希表作为底层实现的集合；
HashMap：哈希表映射

在java的集合中，真正的集合只有数组和链表两种实现，HashMap是通过两者组合实现的，
而HashSet内部是通过HashMap实现的，丢弃了HashMap中的value部分，使用了一个垃圾值（dummy）进行填充实现的。

java 默认hashcode方法：把内存地址转化为整型

java
hashcode：

equals：

练习：
书写字符串的映射函数

四、复合类型，转成整型
比如Date year month day

hash(Date) =  (( (year % M) * B + month ) % M * B + day ) % M;


-------------
设计的原则：
1、一致性  如果a==b 那么hash(a) = hash(b)，但是反过来不一定成立；
2、高效性  计算高效简便
3、均匀性  哈希值分布均匀

但是转成整型，不是唯一方法；

====================================================
映射：
既可以treemap：红黑树

又可以hash表

哈希表：得到了时间复杂度O(1)（均摊时间复杂度），牺牲了顺序，空间；

有序集合、有序映射：平衡树

无序集合、无序映射：哈希表

这一章实现的哈希表有Bug，不能传入一个不可比较的Key类型，因为是Treemap实现的，也就是Comparable，但是如果底层是链表，就
不需要传入可比较的Key；

java1.8，链表哈希冲突后达到一定程度后，会转成红黑树的前提是传入的Key是可比较类型的，实现Comparable接口；

===========接口================
好好理解这个概念： 创建一个接口，由外面实现这个接口，接口中可以定义规则等；由内部调用这个规则；

观察者模式：  被观察者将观察者注册到自身中，观察者实现相关接口，需要的时候，触发观察者的方法；

-----------------------------------
概念补充：
满二叉树
完全二叉树
堆和树的区别（不是指的二叉树）

d叉堆d-ary heap,有d个分支的； O(LogdN), 到底d是多少，效率最高，需要实际上算一算；

索引堆（算法与数据结构）

二项堆

斐波那契堆

广义队列：
普通队列、优先队列、
栈，也可以理解成是一个队列；

----------------------------------练习题目-------------------------------------
递归!!!!
链表和二叉树
多练习链表和二叉树
二叉搜索树题目，再看看那一章的最后一节布置的一些拓展题目；
二叉搜索树：删除任意节点；
二叉搜索树：求一个数（不一定存在该节点）的floor和ceil

排序：
冒泡排序：两两对比，一轮一轮浮上去，选择排序

寻找树的最短公节点

集合和映射：
LeetCode：摩斯密码题目等，具体看那一节最后布置了哪些题目；
leetcode：求一个数组的交集等，具体看，集合，treeset

优先队列，堆：

N个元素选出前M大的

排序： NLogN

使用优先队列：
NLogM

频率前K高的元素；

用java标准库PriorityQueue解决以上这个问题


-----------------
求区间元素和，总是经常会调用sum函数；并且数组静态不变；
方法1：线段树
方法2：预处理

线段树更新操作：

哈希表：

书写字符串的映射函数

java中的hashSet和hashMap区别和实现

SparseArray HashMap 区别；

哈希表动态扩容；

java 中的hashcode和equals
https://www.cnblogs.com/kexianting/p/8508207.html

看下最后一章，线性表和集合分别有哪些

===============================================================
后续：
一、图论基础部分
二、五大算法，这里空的时候看看











