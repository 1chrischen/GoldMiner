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

	//�ý׶�����ʾ��Ϸ�Ľ��̣�ͬʱʵ�ֽ������ת
	//����0��ʾδ��ʼ 1������ 2�̵� 3ʧ�� 4ʤ��
	public static int state = 0;
	
	
	//Gold���Rock����̳���Object�࣬�����洢������ ʯ��
	List<Object> objectList = new ArrayList<>();
	
	//����background���Line��ĺ������໥��ϵ����
	Background background = new Background();
	HookAndLine operation = new HookAndLine(this);
    
	
	//Screens screens = new Screens();
    //screens.launch();
	//�����д��뱻ִ�У��ͽ������棬�������µĹؿ����µ���Ʒ
    {
    	boolean ifPlaced = true;
    	
    	//����8����С��ͬ�Ľ�飬�ֱ��д���С��������
    	//ʹ������������ɽ��
    	for(int i = 0;i < 8; i++) {
    		//�õ������
    		double random = Math.random();
    		
    		//��ŵ�ǰ���ɵĽ��
    		Gold gold = null ;
    		
    		//�����<=0.3�õ�С���
    		if(random <= 0.3)  
    			gold = new GoldMini();
    		
    		//�����>0.3,<0.7�õ��н��
    		else if(random > 0.3 && random < 0.7) 
    			gold = new Gold();
    		
    		//�����>=0.7�õ�����
    		else if(random >= 0.7) 
    			gold = new GoldPlus();
    		
    		//ʹ��for-each����ʵ����Ʒλ�ò��غ� 
    		for(Object object : objectList) {
    			
    			//��֤��Ʒλ�ò��غ� 
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
    	
    	
    	//����5����ͬ��ʯͷ
    	//ʹ��for-each����ʵ��ʯͷ�������
    	for(int i = 0;i < 5; i++) {
    		Rock rock = new Rock();
    		for(Object object : objectList) {
    			
    			//��֤��Ʒλ�ò��غ� 
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
    	
    	
    	//�����ĸ���ʯ
    	//ʹ��for-each����ʵ����ʯ�������
    	for(int i = 0;i < 4;i++) {
    		
    		Diamond dia = new Diamond();
    		for(Object object : objectList) {
    			
    			//��֤��Ʒλ�ò��غ� 
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
    			
    				//��֤��Ʒλ�ò��غ� 
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
    			
    			//��֤��Ʒλ�ò��غ�   			
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
	//������Ҫ�Ľ�������
	public void paint(Graphics g) {
		
		offScreenImage = this.createImage(768, 1000);
		Graphics Image = offScreenImage.getGraphics();
		
		background.paintself(Image);
		
		
		
		if(state == 1) {
			
		
			
			//���ƽ�顢ʯͷ����ʯ����Object������ʵ��
			for(Object object:objectList) {
				
				object.paintSelf(Image);
			
			}
			
			//�ؿ�������ƹ��Ӻ���
			operation.paint(Image);

		
		}
		
		
		g.drawImage(offScreenImage, 0, 0,null);
	}
    
    //������Ϸ����
	void setScreen() {
		
		this.setVisible(true);
		this.setSize(768, 1000);
		this.setLocationRelativeTo(null);
		this.setTitle("�ƽ��");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);


		//���Ӽ��̼�����ʵ����Ϸ����
		addKeyListener(new KeyAdapter(){  
            public void keyPressed(KeyEvent e){  
                
                switch(state) {
                
                case 1:
                	
                	//ͨ������Line��ʵ�ֹ�������ҡ��
    				if(HookAndLine.status == 0 && e.getKeyCode() == KeyEvent.VK_S ) {
    					
    					HookAndLine.status = 1;
    					
    					//�����ջ�֮��ʵ�����¿���ҩˮ�ͱ��ڵĹ���
    					Background.Usingwater = true;
    					Background.Usingfire = true;
    					
    					
    					
    				}
    				
    				//ͨ������Line������ץȡ������ʯͷ,����ʹ��ҩˮ
    				//������W����ʹ��ҩˮ
    				if(e.getKeyCode() == KeyEvent.VK_W && HookAndLine.status == 3 
    						&& Background.numOfWater > 0 && Background.Usingwater) {
    					
    					//false��Ϊtrue���Դ�ʵ��ʹ��ҩˮ�Ĺ���
    					//ʹ��֮��ҩˮ��������һ
    					Background.waterFlag = true;
    					Background.numOfWater--;
    					
    					//һ��ץȡֻ����һ���������û���������ҩˮ�������
    					Background.Usingwater = false;
    				}
    				
    				//������м�ʹ�ñ���
    				if(e.getKeyCode() == KeyEvent.VK_D && HookAndLine.status == 3 
    						&& Background.numOfFire > 0 && Background.Usingfire) {
    					
    					//false��Ϊtrue���Դ�ʵ��ʹ�ñ��ڵĹ���
    					//ʹ��֮�󣬱��ڵ�������һ
    					Background.fireFlag = true;
    					Background.numOfFire--;
    					
    					//һ��ץȡֻ����һ���������û��������±����������
    					Background.Usingfire = false;
    				}
    				break;
                
    				
                case 2:
                	
                	if(e.getKeyCode() == KeyEvent.VK_SPACE) {
    					
    					
    					//����������Ϊҡ��״̬,�����߳���,�����Ӹ�Ϊҡ��״̬
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
    					
    					//������Ϸʱ��
    					javaProject.Background.startTime = System.currentTimeMillis();
    					
    				
    				}
                	
                
                	
                	break;
    				
                	
                default:
                	
                }
            }  
        });
		
		
		//�������������Դ���ʵ����Ϸ�Ĺ���
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e)  {
				super.mouseClicked(e);
			switch(state) {
			case 0:
				//��������������ؿ����棬ͬʱ��ϵͳ�����趨��Ϸ��ʼʱ��
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
				
				//������Ҽ�����ʯͷ�ղ���
				if(e.getButton() == 3 && Background.Buying1) {
					
					//��1��������ʯͷ�ղ���
					Background.myScore -= 100 ;
					Background.x1 = -150;
					Background.y1 = -150;
					Background.bookStoneFlag  = true;
					
					//����ֻ����һ��
					Background.Buying1 = false;
				}
				
				//��������������ʯ������
				if(e.getButton() == 1 && Background.Buying2) {
					
					//��3��������ʯ������
					Background.myScore -= 300;
					Background.x2 = -150;
					Background.y2 = -150;
					Background.DiamondFlag = true;
					
					//����ֻ����һ��
					Background.Buying2 = false;
				}
				
				//������Ҽ�ʵ���̵���ת���ؿ�����
			
				break;
			
			case 3:
				
				//ʧ�ܽ���������֮���������������¿�ʼ��Ϸ
				if(e.getButton() == 1) {
					state = 0;
					background.reStart();
					operation.reStart();
					
				}
				break;
				
			case 4:
				
				//ʤ������������֮���������������¿�ʼ��Ϸ
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
		
		
		
		//��������ĺ���˼�룬ͨ��ѭ�������ϻ����棬����ʵ��������Ϸ�Ĺ���
		do {
			
			//���»��ƽ��棬���ҽ�����һ�ص��ж�
			repaint();
			
			//�����ж���Ϸ�Ľ���
			judgeWhichScreens();
			 try {
				 
				 //������ҵ���Ϸ���飬�ù��ӵİڶ�����������Ʒ��ץȡ����н���
				Thread.sleep(18);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 
		} while(true);
		
	}
	
	
	//������һ��
	public void judgeWhichScreens(){
		
        if(state == 1 && javaProject.Background.gameTimeEnd()){
        	
        	//��Ϸʱ�����֮�󣬵õ��ķ�������Ŀ������������if����ʽ��
            if(Background.myScore >= Background.targetScore)
            {
            	//�ؿ�������5ʱ������Ϸʤ�����棬��ζ��һ����5��
                if(Background.level == 5) {
                	
                	        
                	Background.bookStoneFlag =  false;
                    Background.DiamondFlag = false;
                    Background.Buying1 = true;
                    Background.Buying2 = true;
                	HookAndLine.judge = true;
                	state = 4; 
                	 //����GUIͼ��
                    dispose();
                    
                    //�����¹ؿ�����
                    Screens screens = new Screens();
                    screens.setScreen();
                	}
                
                //�ؿ���<=4�ͽ�����Ϸ���棬ͬʱ�趨�µ���Ϸ�Ŀ�ʼʱ��
                else {
                	
                	//����֮��,��ȥ֮ǰ���̵�õ�������Ч��
                	Background.bookStoneFlag =  false;
                	Background.DiamondFlag = false;
                	
                	//�ؿ�����һ
                	Background.level++;   
                	
                	//���½����̵���棬��������Ϊ������һ����Ʒ
                	Background.Buying1 = true;
                	Background.Buying2 = true;
                	state = 2;
                	HookAndLine.judge = true;
                	HookAndLine.status = 0;
                	 //����GUIͼ��
                    dispose();
                    
                    //�����¹ؿ�����
                    Screens screens = new Screens();
                    screens.setScreen();
                    
                
                }
                
            }
            
            //����õ��ķ���С��Ŀ�������stateΪ3������ʧ�ܽ��档         
            else 
            {
            	
            	state = 3;
            	
            //ʧ��֮��,��ȥ֮ǰ���̵�õ�������Ч��
            Background.bookStoneFlag =  false;
            Background.DiamondFlag = false;
            Background.Buying1 = true;
            Background.Buying2 = true;
            HookAndLine.judge = true;
            
            //����GUIͼ��
            dispose();
            
            //�����¹ؿ�����
            Screens screens = new Screens();
            screens.setScreen();
            
            }
            
      
            //��Ϸʱ�����������ı乳��״̬
            HookAndLine.status = 0;
        }

    }

	
	public static void main(String[] args){
		
		//������ʵ����Ϸ��ʼ����
		  Screens screens = new Screens();
          screens.setScreen();
	}
}