package javaProject;

import java.awt.*;

public class HookAndLine {
     //���ӵ����
	static int x = 380;
	static int y = 180;
     
     //���ӵ��յ�
     static int endX = 500;
     static int endY = 500;
     
     //�߳���
     static double length = 100;
     
     //�߳����ֵ��Сֵ
     static double minLength = 100;
     static double maxLength = 750;
     
     //���ӷ���
     static double n = 0;
     static int direction = 1;
     
     //����״̬ 0ҡ�� 1ץȡ 2�ջ� 3ץȡ����
     static int status = 0;
     
     //����޹������
     static boolean judge = true;
     
     
     //��צͼƬ
     Image hook = Toolkit.getDefaultToolkit().getImage("imgs/hook2.png");
     
     //��֤��ͬһ��GUI������
     Screens frame;
     HookAndLine(Screens frame){
    	 
    	 this.frame = frame;
    	 
     }
     
     //��ײ���
     void collisionDete() {
    	 
    	 for(int i = 0;i < this.frame.objectList.size();i++) {
    		 
    		 Object obj = this.frame.objectList.get(i);
    		 int count = 0;
    		 
    		 if( endX > obj.x - 2
        			 && endX < obj.x + obj.width
        			 && endY > obj.y - 2
        			 && endY < obj.y + obj.length
        			 ) {
        		
        	     //��������������˵��ץ���˽�����ʯͷ
        		 //��status����Ϊ3����ץ��
        			 status = 3;
        		 
        		 //flagΪtrue��������屻ץ���ˣ�����ⱻץ
                 obj.flag = true;
                 
                 //count+1,����ѭ��
                 count++;
                                                     
        		 }
    		 
    		 if(count != 0) {
    			 
    			 break;
    			 
    		 }
    		 
    	 }
    	 
     }
     
     //���ƹ��Ӻ��ߵ��˶��켣
     void drawLineAndHook(Graphics g) {
    	 endX = (int) (x + length * Math.cos(n * Math.PI));
    	 endY = (int) (y + length * Math.sin(n * Math.PI));
    	 g.setColor(Color.green);
    	 //���������������ߵĴ�ϸ
    	 g.drawLine(x , y, endX , endY);
    	 g.drawLine(x - 1 , y, endX - 1 , endY);
    	 g.drawLine(x + 1 , y, endX + 1 , endY);
    	 g.drawLine(x + 2 , y, endX + 2 , endY);
    	 g.drawImage(hook, endX - 20, endY - 2, null);
    	 
     }
     
     
     
     void paint(Graphics g) {
    	 
    	 if(status == 1) {
    	 //������ײ��⣬ͬʱ��ץ�������Ͳ������
    		 collisionDete();
    	 }
    	 
    	if(Background.level >= 1 && judge) {
    		
    		status = 0;
    		judge = false;
    		
    	}
    	
    	
    	 do{
    
    		 if(status == 0) {
    		
    		 //ʵ�ֹ��ӷų�ȥ֮ǰ������ҡ��
    		 if(n < 0.1) direction = 1;
    		 else if(n > 0.9) direction = -1;
    		 
    		 n = n + 0.005 * direction;
    		 
    		 //ʵʱ�������ӵ�����ҡ��״̬
    		 drawLineAndHook(g);
    		 
    		 break;
    		 }
    	 
    		 if(status == 1) {
    		 //������ĳ״̬�������ʵʱ����
    			 if(length < maxLength) {
    				 length = length + 5;
    				 drawLineAndHook(g);
    
    			 }
    		 
    			 else status = 2;
    		 
    		 break;
    		 
    		 }
    	
    	if(status == 2) {
    		 //ûץ��������ʱ�򽫹����ջأ����Ұ���ʵʱ������
    		 if(length > minLength) {
    			 length = length - 5;
    			 drawLineAndHook(g);
    		 }
    		 else status = 0;
    		 
    		 break;
    		 
    	}
    	
    	if(status == 3) {
    		 
    		 //����ץ�����������
    		 int weight = 0;

    		 
    		 if(length > minLength) {
    			 //ץ�����ջ�
    			 length = length - 10;
    			 drawLineAndHook(g);
    			 
    			 for(Object object : this.frame.objectList) {
    				 if(object.flag) {
    					 
    				 //�õ�����
    					 weight = object.weight;
    				 
    				 //�ı䱻ץ��������꣺���Ź����ƶ����ƶ�
    				 object.x = endX - object.getWidth()/2;
    				 object.y = endY + 10;
    			 if(length <= 100) {
    				 
    				 //ץ�����peopleͼƬ������ʱ����ץ������ƷͼƬ�����ڴ�������
    				 object.x = -150;
    				 object.y = -150;
    				 
    				 //��Ϊδץȡ״̬
    				 object.flag = false;
    				 
    				 //��ʹ��ҩˮ
    				 Background.waterFlag = false;
    				 
    				 //ץ����ӷ�
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
    					 //ʹ�������ʵ���ʺŴ��ӹ���
    					 if(random <= 0.25)Background.numOfWater++;
    					 else if(random > 0.25 && random < 0.55) Background.numOfFire++;
    					 else Background.myScore += object.score;
    					 
    				 }
    				 else
    					 Background.myScore += object.score;
    				 
    				 //statusΪ0������˦������
    				 
    				 status = 0;
    				 
    				 }
    			 
    				if(Background.waterFlag) { 
    					if(object.type == 1) {
    						
    						//�ý��������Ϊ1������ץ����������
    						weight = 8;
    					}
    					
    					if(object.type == 2) {
    						
    						//��ʯͷ������Ϊ35������ץ����������
    						weight = 35;
    					}
    					if(object.type == 3) {
    						
    						//����ʯ������Ϊ1������ץ����������
    						weight = 1;
    					}
    					if(object.type == 4) {
    						
    						//������ҩˮ������Ϊ10������ץ����������
    						weight = 10;
    					}
    					if(object.type == 5) {
    						
    						//�ô���������Ϊ10������ץ����������
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
    			 
    			 //m��������������������ʱ��
				Thread.sleep(weight);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	 break;
    	}
    		 
    		 
    		 
    		 
    		 //default:
    	 }while(true);
    	
     }
     //������Ϸ�����¿�ʼ
     void reStart() {
    	 
    	 //����ȫ���ָ���ֵ
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


