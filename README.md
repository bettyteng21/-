# 109學年下學期 演算法作業
## alghw09 - longest shortest path (LSP)
題目:
```
Your program will take a two-dimensional integer array as input. 
Each element in the array represents nodes lined together
Please return the longest shortest-path in the given graph.
Given a graph G = (V, E), find maxu,v d(u, v), where d(u,v) denotes the shortest path length between node u and node v, for all possible u, v
(In this home work, You may use java.util.collection、java.util.ArrayList、java.util.Stack, but  java.util.* is not allowed.)
Input: [[0,1][0,2][0,4][1,3][1,4][2,5][6,7]]
Output: 4
```
一些附加條件: 要在最多node的block中找LSP

簡單來說，就是從所有點中任取兩點(前提是他們有相連)，記錄他們的shortest path，然後從這些長度裡找**最大**的，就是答案


## alghw08 - buy phone version2
## alghw07 - buy phone
## ....剩下等有空再更新
