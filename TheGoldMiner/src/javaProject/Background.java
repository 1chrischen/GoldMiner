package javaProject;

import java.awt.*;

public class Background {
	
	//关卡数，从第一关开始，一共四关
	public static int level = 1;
	
	//目标分数，每关分数随着关卡变化而变化，为关卡数乘以十五
	public static int targetScore = 0;
	
    //总分，打印在游戏界面上，总分大于目标分即进入下一个关卡。
	public static int myScore = 0;
	
	//药水数量，使用一次就会减少。
	public static int numOfWater = 3;
	
	//药水状态，false代表不使用，鼠标监听器当中按右键使用药水
	public static boolean waterFlag = false;
	
	//鞭炮数量，使用一次就会减少
	public static int numOfFire = 3;
	
	//鞭炮状态，false代表不使用，鼠标监听器当中按鼠标中键使用药水
	public static boolean fireFlag = false;
	
	//商店物品的位置坐标，可以实现改变
	public 	static int x1 = 300;
	public static int y1 = 300;
	public static int x2 = 400;
	public static int y2 = 300;
	
	//设置boolean变量，来控制商店功能
	public static boolean bookStoneFlag =  false;
	public static boolean DiamondFlag = false;
	
	//商店购买一次,状态确定
	public static boolean Buying1 = true;
	public static boolean Buying2 = true;
	
	//使用药水，鞭炮，保证只使用一次
	public static boolean Usingwater = true;
	public static boolean Usingfire = true;
	
	//游戏开始时间
	public static long startTime; //静态变量
	
	//游戏结束时间
	public static long endTime;
	
	//需要用到的用于绘制的图片
	Image bg = Toolkit.getDefaultToolkit().getImage("imgs/background.png");
	Image sky = Toolkit.getDefaultToolkit().getImage("imgs/bg1.jpg");
	Image people = Toolkit.getDefaultToolkit().getImage("imgs/peo.png");
	Image water = Toolkit.getDefaultToolkit().getImage("imgs/water.png");
	Image fc = Toolkit.getDefaultToolkit().getImage("imgs/firecraker.png");
	Image rockbook = Toolkit.getDefaultToolkit().getImage("imgs/rockbook1.png");
	Image dw = Toolkit.getDefaultToolkit().getImage("imgs/diamondWater.png");
	Image title = Toolkit.getDefaultToolkit().getImage("imgs/title.png");
	Image land = Toolkit.getDefaultToolkit().getImage("imgs/land.png");
	
	//绘制各个界面的必要的文字或者图片信息。
	void paintself(Graphics g) {
	
		switch(Screens.state) {
		
		//0代表游戏开始界面，点击鼠标右键开始游戏
		case 0:
			g.drawImage(bg, 0, 200, null);
			g.drawImage(sky,0,0,null);
			printWordOnGui(g,80,"准备开始游戏",120,350,Color.green);
			printWordOnGui(g,40,"点击鼠标左键开始",200,450,Color.blue);
			printWordOnGui(g,30,"游戏玩法:S放钩子 W使用药水 D使用鞭炮",100,550,Color.green);
			g.drawImage(title,265,75,null);
			g.drawImage(land,0,808,null);
			break;
			
		//点击右键后，state+1=1，进入游戏界面
		case 1:
			
		g.drawImage(sky,0,0,null);
		g.drawImage(bg, 0, 200, null);
		g.drawImage(people, 310, 50 ,null);
		g.drawImage(land,0,808,null);
		//画出当前获取的积分情况
		printWordOnGui(g,30,"当前积分:"+myScore,30,150,Color.black);
		
		//药水组件，包括药水图片的绘制，药水剩余的数量。
		g.drawImage(water, 450, 30, null);
		printWordOnGui(g,25,"药水数量:"+numOfWater,550,93,Color.black);
		
		//鞭炮组件，包括鞭炮图片的绘制，鞭炮剩余的数量。
		g.drawImage(fc, 475, 105, null);
		printWordOnGui(g,25,"鞭炮数量:"+numOfFire,550,147,Color.black);
		
		//关卡数的绘制
		printWordOnGui(g,30,"第"+level+"关",30,60,Color.black);
		
		//目标积分的绘制
		targetScore = level * 3000;
		printWordOnGui(g,30,"目标积分:"+targetScore,30,110,Color.black);
		
		//时间组件，每个关卡均为30秒的游戏时间。
		endTime = System.currentTimeMillis();
		long time  = 0;
		time = 30 - (endTime - startTime)/1000;
		printWordOnGui(g,25,"剩余时间:"+(time>0?time:0),550,190,Color.black);
		break;
		
		case 2:
			
			//商店界面说明
			g.drawImage(sky,0,0,null);
			g.drawImage(bg, 0, 200, null);
			printWordOnGui(g,30,"按鼠标右键购买石头收藏书",200,450,Color.green);
			printWordOnGui(g,30,"按鼠标左键购买钻石增幅剂",200,550,Color.green);
			printWordOnGui(g,30,"按 空格 进入下一关",200,650,Color.red);
			printWordOnGui(g,20,"100积分",x1,400,Color.blue);
			printWordOnGui(g,20,"300积分",x2,400,Color.blue);
			//商店物品绘制
			g.drawImage(rockbook,x1,y1,null);
			g.drawImage(dw,x2,y2,null);
			g.drawImage(title,265,75,null);
			g.drawImage(land,0,808,null);
			break;
		
		case 3:
			g.drawImage(sky,0,0,null);
			g.drawImage(bg, 0, 200, null);
			
			//目标分不够时，打印该字体，显示失败界面
			printWordOnGui(g,40,"很遗憾，你未能通关",200,350,Color.red);
			printWordOnGui(g,40,"你的积分是:"+myScore,200,450,Color.red);
			printWordOnGui(g,40,"按鼠标左键可重新开始游戏",200,550,Color.red);
		    g.drawImage(title,265,75,null);
		    g.drawImage(land,0,808,null);
			break;
			
		case 4:
			g.drawImage(bg, 0, 200, null);
			g.drawImage(sky,0,0,null);
			//游戏通关之后，绘制成功界面
			//printWordOnGui(g,50,"黄金矿工",300,100,Color.yellow);
			printWordOnGui(g,40,"恭喜你，游戏顺利通关",200,350,Color.green);
			printWordOnGui(g,40,"总积分是:"+myScore,200,450,Color.green);
			printWordOnGui(g,40,"按鼠标左键可重新开始游戏",200,550,Color.green);
	        g.drawImage(land,0,808,null);
	        g.drawImage(title,265,75,null);
			break;
		default:
			
		}
		
	}
	
	//总共游戏时间
	static boolean gameTimeEnd() {
		long time = (endTime - startTime)/1000;
		if(time > 30) {
			return true;
		}
		return false;
	}
	//重置游戏
	void reStart() {
		//关卡数
		level = 1;
		
		//目标分数
		targetScore = 0;
		
	    //总分
		myScore = 0;
		
		//药水数量
		numOfWater = 3;
		
		//药水状态
		waterFlag = false;
		
		//鞭炮数量
		numOfFire = 3;
		
		//鞭炮状态
		fireFlag  = false;
		
		//重置之前在商店得到的增幅效果
    	bookStoneFlag =  false;
    	DiamondFlag = false;
    	
    	//重置购买状态
    	Buying1 = true;
    	Buying2 = true;
    	
    	//重置鞭炮，药水，使用状态
    	Usingwater = true;
    	Usingfire = true;
	}
	
	//绘制字符串的函数，调用此函数来实现在GUI界面上字体的打印
	public static void printWordOnGui(
			Graphics g,int size,
			String str,int x,int y,Color color) {
		g.setColor(color);
		g.setFont(new Font("仿宋",Font.CENTER_BASELINE,size));
		g.drawString(str, x, y);
		
	}
}
