package javaProject;

import java.awt.*;

public class HookAndLine {
     //钩子的起点
	static int x = 380;
	static int y = 180;
     
     //钩子的终点
     static int endX = 500;
     static int endY = 500;
     
     //线长度
     static double length = 100;
     
     //线长最大值最小值
     static double minLength = 100;
     static double maxLength = 750;
     
     //钩子方向
     static double n = 0;
     static int direction = 1;
     
     //钩子状态 0摇摆 1抓取 2收回 3抓取返回
     static int status = 0;
     
     //解决无钩子情况
     static boolean judge = true;
     
     
     //钩爪图片
     Image hook = Toolkit.getDefaultToolkit().getImage("imgs/hook2.png");
     
     //保证在同一个GUI上面绘出
     Screens frame;
     HookAndLine(Screens frame){
    	 
    	 this.frame = frame;
    	 
     }
     
     //碰撞检测
     void collisionDete() {
    	 
    	 for(int i = 0;i < this.frame.objectList.size();i++) {
    		 
    		 Object obj = this.frame.objectList.get(i);
    		 int count = 0;
    		 
    		 if( endX > obj.x - 2
        			 && endX < obj.x + obj.width
        			 && endY > obj.y - 2
        			 && endY < obj.y + obj.length
        			 ) {
        		
        	     //满足上述条件则说明抓到了金块或者石头
        		 //将status设置为3将其抓回
        			 status = 3;
        		 
        		 //flag为true代表该物体被抓到了，即检测被抓
                 obj.flag = true;
                 
                 //count+1,跳出循环
                 count++;
                                                     
        		 }
    		 
    		 if(count != 0) {
    			 
    			 break;
    			 
    		 }
    		 
    	 }
    	 
     }
     
     //绘制钩子和线的运动轨迹
     void drawLineAndHook(Graphics g) {
    	 endX = (int) (x + length * Math.cos(n * Math.PI));
    	 endY = (int) (y + length * Math.sin(n * Math.PI));
    	 g.setColor(Color.green);
    	 //画四条线来增加线的粗细
    	 g.drawLine(x , y, endX , endY);
    	 g.drawLine(x - 1 , y, endX - 1 , endY);
    	 g.drawLine(x + 1 , y, endX + 1 , endY);
    	 g.drawLine(x + 2 , y, endX + 2 , endY);
    	 g.drawImage(hook, endX - 20, endY - 2, null);
    	 
     }
     
     
     
     void paint(Graphics g) {
    	 
    	 if(status == 1) {
    	 //进行碰撞检测，同时，抓到东西就不检测了
    		 collisionDete();
    	 }
    	 
    	if(Background.level >= 1 && judge) {
    		
    		status = 0;
    		judge = false;
    		
    	}
    	
    	
    	 do{
    
    		 if(status == 0) {
    		
    		 //实现钩子放出去之前的左右摇摆
    		 if(n < 0.1) direction = 1;
    		 else if(n > 0.9) direction = -1;
    		 
    		 n = n + 0.005 * direction;
    		 
    		 //实时画出钩子的左右摇摆状态
    		 drawLineAndHook(g);
    		 
    		 break;
    		 }
    	 
    		 if(status == 1) {
    		 //钩子以某状态射出，线实时画出
    			 if(length < maxLength) {
    				 length = length + 5;
    				 drawLineAndHook(g);
    
    			 }
    		 
    			 else status = 2;
    		 
    		 break;
    		 
    		 }
    	
    	if(status == 2) {
    		 //没抓到东西的时候将钩子收回，并且把线实时画出来
    		 if(length > minLength) {
    			 length = length - 5;
    			 drawLineAndHook(g);
    		 }
    		 else status = 0;
    		 
    		 break;
    		 
    	}
    	
    	if(status == 3) {
    		 
    		 //这是抓到东西的情况
    		 int weight = 0;

    		 
    		 if(length > minLength) {
    			 //抓到后收回
    			 length = length - 10;
    			 drawLineAndHook(g);
    			 
    			 for(Object object : this.frame.objectList) {
    				 if(object.flag) {
    					 
    				 //得到质量
    					 weight = object.weight;
    				 
    				 //改变被抓物体的坐标：随着钩子移动而移动
    				 object.x = endX - object.getWidth()/2;
    				 object.y = endY + 10;
    			 if(length <= 100) {
    				 
    				 //抓到人物（people图片）这里时，把抓到的物品图片设置在窗口外面
    				 object.x = -150;
    				 object.y = -150;
    				 
    				 //设为未抓取状态
    				 object.flag = false;
    				 
    				 //不使用药水
    				 Background.waterFlag = false;
    				 
    				 //抓到后加分
    				 if(object.type == 2 && Background.bookStoneFlag) {
    					 
    					 Background.myScore = Background.myScore + object.score + 100;
    				 
    				 }
    				 else if(object.type == 3 && Background.DiamondFlag) {
    					 
    					 Background.myScore = Background.myScore + object.score + 200;
    					 
    				 }
    				 else if(object.type == 4) {
    					 
    					 Background.numOfWater++;
    					 
    				 }
    				 else if(object.type == 5) {
    					 
    					 double random = Math.random();
    					 //使用随机数实现问号袋子功能
    					 if(random <= 0.25)Background.numOfWater++;
    					 else if(random > 0.25 && random < 0.55) Background.numOfFire++;
    					 else Background.myScore += object.score;
    					 
    				 }
    				 else
    					 Background.myScore += object.score;
    				 
    				 //status为0，重新甩动钩子
    				 
    				 status = 0;
    				 
    				 }
    			 
    				if(Background.waterFlag) { 
    					if(object.type == 1) {
    						
    						//让金块质量变为1，提升抓上来的速率
    						weight = 8;
    					}
    					
    					if(object.type == 2) {
    						
    						//让石头质量变为35，提升抓上来的速率
    						weight = 35;
    					}
    					if(object.type == 3) {
    						
    						//让钻石质量变为1，提升抓上来的速率
    						weight = 1;
    					}
    					if(object.type == 4) {
    						
    						//让生力药水质量变为10，提升抓上来的速率
    						weight = 10;
    					}
    					if(object.type == 5) {
    						
    						//让袋子质量变为10，提升抓上来的速率
    						weight = 10;
    					}
    				}
    				
    				if(Background.fireFlag) {
    					
    					object.x = -150;
    					object.y = -150;
    					object.flag = false;
	    				 Background.fireFlag = false;
	    				 status = 2;
    					
    				}
    				
    			 }
    			 }	 	 
    		}
    		 try {
    			 
    			 //m（质量）决定拉上来的时间
				Thread.sleep(weight);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	 break;
    	}
    		 
    		 
    		 
    		 
    		 //default:
    	 }while(true);
    	
     }
     //重置游戏，重新开始
     void reStart() {
    	 
    	 //变量全部恢复初值
    	 x = 380;
    	 y = 180;  	     
    	 endX = 500;
    	 endY = 500;
    	 minLength = 100;
    	 maxLength = 750;
    	 direction = 1;
    	 n = 0;
    	 length = 100;
    	 status = 0;
    	 judge = true;

    	 
     }
}


