package javaProject;

import java.awt.*;
public class Object {
    //坐标
	public int x;
	public int y;
	
	//长宽
	public int length;
	public int width;
	
	//图片
	Image image;
	
	//标记，记录是否通过碰撞检测
	public boolean flag = false;
	
	//质量
	public int weight;
	
	//积分
	public int score;
	
	//类型 1表示金块 2是石块 3是钻石 4是药水 5是问号袋子
	public int type;
	
	void paintSelf(Graphics g) {
		g.drawImage(image, x, y, null);
		
	}
	public int getWidth() {
		return width;
	}
	//获取矩形，保证物品位置不重合
	public Rectangle getRec() {
		return new Rectangle(x,y,width,length);
	}
}