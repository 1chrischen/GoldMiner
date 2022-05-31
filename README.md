# GoldMiner
黄金矿工

SCU 《面向对象程序设计导论》课程作业。

队员：陈嘉伟，康庄。

项目介绍： 
Title: 黄金矿工2.0


我们小组的项目是游戏《黄金矿工》，我们的想法是通过不同的界面来完成整个游戏的进程，一共分为5个界面，分别为：游戏开始界面、不同关卡界面（一共为五关）、游戏胜利界面、商店界面、游戏失败（游戏结束）界面。
每个界面都是游戏的一部分，在每一个界面玩家都通过键盘的不同按键来进行每一步的操作。当然，游戏是否可以继续与玩家游戏水平的高低有很大关系，但最直接的决定因素是玩家的每一关积分是否达到目标要求积分。

第一个界面：游戏开始界面。
（1）	
绘制游戏开始界面图：包括场景的描绘、基本游戏道具的分布、以及“开始游戏”四个字，用户通过按鼠标左键，来开始游戏。
（2）	
当用户点击鼠标左键之后，随即跳入下一个界面：第一个关卡界面。然后立即开始计时，一共30秒时间来完成第一个关卡。

第二个界面：玩游戏界面（随机分布物品）
（1） 
绘制不同关卡的物品：一共有五个不同的游戏画面，全部通过即获胜，反之游戏失败。
所有的关卡总共有这些物品 ：
1.黄金（分为大中小三类），并且大小不同，抓取之后的分数也不同；
2.石块
3.钻石
4.神秘袋子（可能有鞭炮、分数等，后面有讲述其作用）；
5.升力药水；
显然每一个关卡不可能同时出现所有的物品，这五个关卡各不相同，过关条件（达到相应分数）也不尽相同。
每关所涉及的物品暂未完全设计好，因为要考虑游戏过关的难易度
其中：钻石加700分、大黄金加580，中黄金加350，小黄金加180、石块加120、神秘袋子加200-900或者一个鞭炮或者一个药水、药水药水数量加一
各个物品被抓上来的时间和钩子与它们之间的距离成正比，并且各个物品被抓一秒钟所行进的距离各不相同。
其中：大黄金1秒行进1个单位，中黄金1秒行进2个单位，小黄金1秒行进3个单位，石块一秒行进1个单位，其余的物品一秒行进3个单位。
（2） 
绘制关卡的背景 ：该部分包括背景的绘制、游戏人物（一个矿工）的绘制、目标分数的绘制、已经获取的分数的绘制、人物旁边显示炸药数量（之后讲述其功能）和最重要的绳子与钩子（钩子可以在一定范围内左右摇摆，绳子具有最大拉伸距离，玩家自由选择什么时候放出钩子）的绘制。
（3） 
用户将会在已经绘制好的游戏关卡界面进行游戏，每一关一共有30秒的时间进行游戏，按“S”键来释放钩子，此时钩子将以转到的位置直线放出，若抓到东西，则将其抓回并获得相应的分数，若超出游戏界面，则自动收回钩子。若抓到自己不满意的物品，在有鞭炮的情况之下按“D”键，将自动放弃抓到的物品并立即将钩子收回，当然也不会获得其分数。按“W”键使用生力药水。
特殊的物品有神秘袋子（它可以获得分数或者鞭炮或者药水）。除去第一关之外，每一关都可以用分数来购买商店里面的功能性道具，来增加游戏的体验，购买的方式则是用先前获得的总分数（后面会详细讲解这部分）。
待时间结束（30s）之后，这一关的功能性道具（除鞭炮）将不会带入下一关，所有从商店获取的功能将被清除。系统会将你的总分数和目标分数相对比，如果超过目标分数则进入游戏胜利界面，继续之后的一系列操作，反之则进入游戏结束界面。

第三个界面 ： 游戏胜利界面
（1）绘制游戏胜利的场景：包括背景和“继续游戏”四个字，总分数的显示。
（2）用户点击鼠标左键重新开始游戏。



第四个界面 ： 商店界面
（1）
绘制商店界面 ： 包括商店的背景，还有商店卖的2个物品石头收藏书、钻石收藏书。
其中：按 鼠标左键买钻石收藏书，右键买石头收藏书
（2）
     购买石头收藏书之后，抓到的石头分数将会加100。
     购买钻石收藏书之后，抓到的钻石将从700变成900。
购买方式是使用之前获取的总分数作为金钱购买商店里面的道具，并且所有的道具（除未使用的鞭炮）仅在下一关生效。
其中石头收藏书100、钻石收藏书300。购买之后，新的总分数就等于原来的总分数减去购买所用到的分数
（3）
用户点击空格键进入下一关游戏的界面开始游戏

第五个界面：游戏结束界面
（1）
绘制游戏结束画面：包括游戏结束的背景和“很遗憾，你的分数未能达标”、“确定”的字样
 （2）
     用户点击鼠标左键可重新开始游戏。

制作计划： 按部就班，每周完成一部分
