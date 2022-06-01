package javaProject;

import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class Screens extends JFrame{
	
	
	private static final long serialVersionUID = 1L;

	//用阶段来表示游戏的进程，同时实现界面的跳转
	//其中0表示未开始 1运行中 2商店 3失败 4胜利
	public static int state = 0;
	
	
	//Gold类和Rock类均继承于Object类，用来存储多个金块 石块
	List<Object> objectList = new ArrayList<>();
	
	//调用background类和Line类的函数，相互联系起来
	Background background = new Background();
	HookAndLine operation = new HookAndLine(this);
    
	
	//Screens screens = new Screens();
    //screens.launch();
	//这两行代码被执行，就进入下面，即生成新的关卡的新的物品
    {
    	boolean ifPlaced = true;
    	
    	//生成8个大小不同的金块，分别有大中小三个类型
    	//使用随机数来生成金块
    	for(int i = 0;i < 8; i++) {
    		//得到随机数
    		double random = Math.random();
    		
    		//存放当前生成的金块
    		Gold gold = null ;
    		
    		//随机数<=0.3得到小金块
    		if(random <= 0.3)  
    			gold = new GoldMini();
    		
    		//随机数>0.3,<0.7得到中金块
    		else if(random > 0.3 && random < 0.7) 
    			gold = new Gold();
    		
    		//随机数>=0.7得到大金块
    		else if(random >= 0.7) 
    			gold = new GoldPlus();
    		
    		//使用for-each函数实现物品位置不重合 
    		for(Object object : objectList) {
    			
    			//保证物品位置不重合 
    			if(gold.getRec().intersects(object.getRec())) {
    				
    				ifPlaced = false;
    				
    			}
    			
    		}
    		
    		if(ifPlaced) {
    			objectList.add(gold); 			
    		}
    		else {
    			ifPlaced = true;
    			i--;
    		}
    	}
    	
    	
    	//生成5个相同的石头
    	//使用for-each函数实现石头存进数组
    	for(int i = 0;i < 5; i++) {
    		Rock rock = new Rock();
    		for(Object object : objectList) {
    			
    			//保证物品位置不重合 
    			if(rock.getRec().intersects(object.getRec())) {
    				ifPlaced = false;
    				
    			}
    		}
    		if(ifPlaced) {
    			objectList.add(rock); 			
    		}
    		else {
    			ifPlaced = true;
    			i--;
    		}
    	}
    	
    	
    	//生成四个钻石
    	//使用for-each函数实现钻石存进数组
    	for(int i = 0;i < 4;i++) {
    		
    		Diamond dia = new Diamond();
    		for(Object object : objectList) {
    			
    			//保证物品位置不重合 
    			if(dia.getRec().intersects(object.getRec())) {
    				ifPlaced = false;
    			}
    		}
    		if(ifPlaced) {
    			objectList.add(dia);
    		}
    		else {
    			ifPlaced = true;
    			i--;
    		}
    		
    	}

    		
    		for(int i = 0;i < 1;i++) {
    		
    			Water water = new Water();
    			for(Object object : objectList) {
    			
    				//保证物品位置不重合 
    				if(water.getRec().intersects(object.getRec())) {
    				
    					ifPlaced = false;
				
    				}
    			}
    			if(ifPlaced) {
    			
    				objectList.add(water);
			
    			}
    			else {
    			
    				ifPlaced = true;
    				i--;
			
    			}
    	
    		}
    	
    	
    	for(int i = 0;i < 1;i++) {
    		
    		Bag bag = new Bag();
    		for(Object object : objectList) {
    			
    			//保证物品位置不重合   			
    			if(bag.getRec().intersects(object.getRec())) {
    				
    				ifPlaced = false;
				
    			}
    		}
    		if(ifPlaced) {
    			
			objectList.add(bag);
			
    		}
    		else {
    			
    			ifPlaced = true;
    			i--;
			
    		}
    	
    	}
    
    }
    
    Image offScreenImage;
    
	@Override
	//绘制需要的界面内容
	public void paint(Graphics g) {
		
		offScreenImage = this.createImage(768, 1000);
		Graphics Image = offScreenImage.getGraphics();
		
		background.paintself(Image);
		
		
		
		if(state == 1) {
			
		
			
			//绘制金块、石头和钻石，在Object类里面实现
			for(Object object:objectList) {
				
				object.paintSelf(Image);
			
			}
			
			//关卡界面绘制钩子和线
			operation.paint(Image);

		
		}
		
		
		g.drawImage(offScreenImage, 0, 0,null);
	}
    
    //绘制游戏界面
	void setScreen() {
		
		this.setVisible(true);
		this.setSize(768, 1000);
		this.setLocationRelativeTo(null);
		this.setTitle("黄金矿工");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);


		//增加键盘监听，实现游戏功能
		addKeyListener(new KeyAdapter(){  
            public void keyPressed(KeyEvent e){  
                
                switch(state) {
                
                case 1:
                	
                	//通过调用Line类实现钩子左右摇摆
    				if(HookAndLine.status == 0 && e.getKeyCode() == KeyEvent.VK_S ) {
    					
    					HookAndLine.status = 1;
    					
    					//钩子收回之后，实现重新可用药水和鞭炮的功能
    					Background.Usingwater = true;
    					Background.Usingfire = true;
    					
    					
    					
    				}
    				
    				//通过调用Line类往回抓取金块或者石头,并且使用药水
    				//按键盘W键来使用药水
    				if(e.getKeyCode() == KeyEvent.VK_W && HookAndLine.status == 3 
    						&& Background.numOfWater > 0 && Background.Usingwater) {
    					
    					//false改为true，以此实现使用药水的功能
    					//使用之后，药水的数量减一
    					Background.waterFlag = true;
    					Background.numOfWater--;
    					
    					//一次抓取只能用一个，避免用户多点而导致药水数量骤减
    					Background.Usingwater = false;
    				}
    				
    				//按鼠标中键使用鞭炮
    				if(e.getKeyCode() == KeyEvent.VK_D && HookAndLine.status == 3 
    						&& Background.numOfFire > 0 && Background.Usingfire) {
    					
    					//false改为true，以此实现使用鞭炮的功能
    					//使用之后，鞭炮的数量减一
    					Background.fireFlag = true;
    					Background.numOfFire--;
    					
    					//一次抓取只能用一个，避免用户多点而导致鞭炮数量骤减
    					Background.Usingfire = false;
    				}
    				break;
                
    				
                case 2:
                	
                	if(e.getKeyCode() == KeyEvent.VK_SPACE) {
    					
    					
    					//将钩子设置为摇摆状态,重置线长度,将钩子改为摇摆状态
    					HookAndLine.length = 100;
    					HookAndLine.status = 0;
    					HookAndLine.judge = true;
    					
    				
    					Background.x1 = 300;
    					Background.y1 = 300;
    					Background.x2 = 400;
    					Background.y2 = 300;
    					
    					for(Object obj : objectList) {
    						
    						obj.flag = false;
    						
    					}
    					
    					
    					state = 1;
    					
    					//设置游戏时间
    					javaProject.Background.startTime = System.currentTimeMillis();
    					
    				
    				}
                	
                
                	
                	break;
    				
                	
                default:
                	
                }
            }  
        });
		
		
		//增加鼠标监听，以此来实现游戏的功能
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)  {
				super.mouseClicked(e);
			switch(state) {
			case 0:
				//按鼠标左键，进入关卡界面，同时用系统函数设定游戏开始时间
				if(e.getButton() == 1) {
					HookAndLine.status = 0;
					HookAndLine.judge = true;
					for(Object obj : objectList) {
						
						obj.flag = false;
						
					}
					state = 1;
					javaProject.Background.startTime = System.currentTimeMillis();
					
			
				}
				break;
				
				
			case 2:
				
				//按鼠标右键购买石头收藏书
				if(e.getButton() == 3 && Background.Buying1) {
					
					//用1积分来买石头收藏书
					Background.myScore -= 100 ;
					Background.x1 = -150;
					Background.y1 = -150;
					Background.bookStoneFlag  = true;
					
					//单次只购买一次
					Background.Buying1 = false;
				}
				
				//按鼠标左键购买钻石增幅剂
				if(e.getButton() == 1 && Background.Buying2) {
					
					//用3积分买钻石增幅剂
					Background.myScore -= 300;
					Background.x2 = -150;
					Background.y2 = -150;
					Background.DiamondFlag = true;
					
					//单次只购买一次
					Background.Buying2 = false;
				}
				
				//按鼠标右键实现商店跳转到关卡界面
			
				break;
			
			case 3:
				
				//失败界面跳出来之后，允许按鼠标左键重新开始游戏
				if(e.getButton() == 1) {
					state = 0;
					background.reStart();
					operation.reStart();
					
				}
				break;
				
			case 4:
				
				//胜利界面跳出来之后，允许按鼠标左键重新开始游戏
				if(e.getButton() == 1) {
					state = 0;
					background.reStart();
					operation.reStart();
					
				}
				break;
				
				default:
			}
			
		}
				
		});
		
		
		
		//整个代码的核心思想，通过循环来不断画界面，进而实现整个游戏的功能
		do {
			
			//重新绘制界面，并且进入下一关的判定
			repaint();
			
			//不断判定游戏的进程
			judgeWhichScreens();
			 try {
				 
				 //增加玩家的游戏体验，让钩子的摆动，伸缩，物品的抓取变得有节奏
				Thread.sleep(18);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 
		} while(true);
		
	}
	
	
	//进入下一关
	public void judgeWhichScreens(){
		
        if(state == 1 && javaProject.Background.gameTimeEnd()){
        	
        	//游戏时间结束之后，得到的分数大于目标分数，则进入if条件式子
            if(Background.myScore >= Background.targetScore)
            {
            	//关卡数等于5时进入游戏胜利界面，意味着一共有5关
                if(Background.level == 5) {
                	
                	        
                	Background.bookStoneFlag =  false;
                    Background.DiamondFlag = false;
                    Background.Buying1 = true;
                    Background.Buying2 = true;
                	HookAndLine.judge = true;
                	state = 4; 
                	 //消除GUI图像
                    dispose();
                    
                    //绘制新关卡界面
                    Screens screens = new Screens();
                    screens.setScreen();
                	}
                
                //关卡数<=4就进入游戏界面，同时设定新的游戏的开始时间
                else {
                	
                	//过关之后,消去之前在商店得到的增幅效果
                	Background.bookStoneFlag =  false;
                	Background.DiamondFlag = false;
                	
                	//关卡数加一
                	Background.level++;   
                	
                	//重新进入商店界面，并且设置为可以买一次物品
                	Background.Buying1 = true;
                	Background.Buying2 = true;
                	state = 2;
                	HookAndLine.judge = true;
                	HookAndLine.status = 0;
                	 //消除GUI图像
                    dispose();
                    
                    //绘制新关卡界面
                    Screens screens = new Screens();
                    screens.setScreen();
                    
                
                }
                
            }
            
            //代表得到的分数小于目标分数，state为3，进入失败界面。         
            else 
            {
            	
            	state = 3;
            	
            //失败之后,消去之前在商店得到的增幅效果
            Background.bookStoneFlag =  false;
            Background.DiamondFlag = false;
            Background.Buying1 = true;
            Background.Buying2 = true;
            HookAndLine.judge = true;
            
            //消除GUI图像
            dispose();
            
            //绘制新关卡界面
            Screens screens = new Screens();
            screens.setScreen();
            
            }
            
      
            //游戏时间结束，立马改变钩子状态
            HookAndLine.status = 0;
        }

    }

	
	public static void main(String[] args){
		
		//主函数实现游戏开始功能
		  Screens screens = new Screens();
          screens.setScreen();
	}
}