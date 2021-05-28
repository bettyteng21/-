# 109學年下學期 演算法作業
## alghw10 - sorting
```
Your program will take an integer array as input. 
Please return the sorted ascending array in the given array.
Input: [-1,2,5,9,8,7,1,3,2]
Output: [-1,1,2,2,3,5,7,8,9]
```

就是最最最基本的sorting (不提供側資特徵)


## alghw09 - longest shortest path (LSP)
題目:
```diff
Your program will take a two-dimensional integer array as input. 
Each element in the array represents nodes lined together
Please return the longest shortest-path in the given graph.
-Given a graph G = (V, E), find maxu,v d(u, v), where d(u,v) denotes the shortest path length between node u and node v, for all possible u, v
(In this home work, You may use java.util.collection、java.util.ArrayList、java.util.Stack, but  java.util.* is not allowed.)
Input: [[0,1][0,2][0,4][1,3][1,4][2,5][6,7]]
Output: 4
```
一些附加條件: 要在最多node的block中找LSP

簡單來說，就是從所有點中任取兩點(前提是他們有相連)，記錄他們的shortest path，然後從這些長度裡找**最大**的，就是答案

## alghw08 - buy phone version2
題目:
```
Your program will take a six-dimensional integer array as input. 
Each element represents a smartphone model which contains two features (e.g., screen size and performance ranking).
Please return an sorted array containing a list of unbeatable smartphone models (In other words, filter out the phone models that can be beaten by some model).
The return array should be sorted by the first element of each array in the output. 
Input: [[8,7,7,4,2,1],[2,4,4,6,2,1],[4,0,5,1,3,2],[5,2,4,3,7,3],[7,5,6,9,8,9]]
Output: [[7,5,6,9,8,9],[8,7,7,4,2,1]]
```
上次作業的延伸，只是變成多維版本

```diff
@@這次助教給的側資都很漂亮，所以才不用使用到暴力解，例外測資(晚點附上)跑我的code會直接爆掉喔喔喔@@
```

## alghw07 - buy phone
## ....剩下等有空再更新
