package javaProject;

import java.awt.*;

public class Background {
	
	//�ؿ������ӵ�һ�ؿ�ʼ��һ���Ĺ�
	public static int level = 1;
	
	//Ŀ�������ÿ�ط������Źؿ��仯���仯��Ϊ�ؿ�������ʮ��
	public static int targetScore = 0;
	
    //�ܷ֣���ӡ����Ϸ�����ϣ��ִܷ���Ŀ��ּ�������һ���ؿ���
	public static int myScore = 0;
	
	//ҩˮ������ʹ��һ�ξͻ���١�
	public static int numOfWater = 3;
	
	//ҩˮ״̬��false����ʹ�ã������������а��Ҽ�ʹ��ҩˮ
	public static boolean waterFlag = false;
	
	//����������ʹ��һ�ξͻ����
	public static int numOfFire = 3;
	
	//����״̬��false����ʹ�ã������������а�����м�ʹ��ҩˮ
	public static boolean fireFlag = false;
	
	//�̵���Ʒ��λ�����꣬����ʵ�ָı�
	public 	static int x1 = 300;
	public static int y1 = 300;
	public static int x2 = 400;
	public static int y2 = 300;
	
	//����boolean�������������̵깦��
	public static boolean bookStoneFlag =  false;
	public static boolean DiamondFlag = false;
	
	//�̵깺��һ��,״̬ȷ��
	public static boolean Buying1 = true;
	public static boolean Buying2 = true;
	
	//ʹ��ҩˮ�����ڣ���ֻ֤ʹ��һ��
	public static boolean Usingwater = true;
	public static boolean Usingfire = true;
	
	//��Ϸ��ʼʱ��
	public static long startTime; //��̬����
	
	//��Ϸ����ʱ��
	public static long endTime;
	
	//��Ҫ�õ������ڻ��Ƶ�ͼƬ
	Image bg = Toolkit.getDefaultToolkit().getImage("imgs/background.png");
	Image sky = Toolkit.getDefaultToolkit().getImage("imgs/bg1.jpg");
	Image people = Toolkit.getDefaultToolkit().getImage("imgs/peo.png");
	Image water = Toolkit.getDefaultToolkit().getImage("imgs/water.png");
	Image fc = Toolkit.getDefaultToolkit().getImage("imgs/firecraker.png");
	Image rockbook = Toolkit.getDefaultToolkit().getImage("imgs/rockbook1.png");
	Image dw = Toolkit.getDefaultToolkit().getImage("imgs/diamondWater.png");
	Image title = Toolkit.getDefaultToolkit().getImage("imgs/title.png");
	Image land = Toolkit.getDefaultToolkit().getImage("imgs/land.png");
	
	//���Ƹ�������ı�Ҫ�����ֻ���ͼƬ��Ϣ��
	void paintself(Graphics g) {
	
		switch(Screens.state) {
		
		//0������Ϸ��ʼ���棬�������Ҽ���ʼ��Ϸ
		case 0:
			g.drawImage(bg, 0, 200, null);
			g.drawImage(sky,0,0,null);
			printWordOnGui(g,80,"׼����ʼ��Ϸ",120,350,Color.green);
			printWordOnGui(g,40,"�����������ʼ",200,450,Color.blue);
			printWordOnGui(g,30,"��Ϸ�淨:S�Ź��� Wʹ��ҩˮ Dʹ�ñ���",100,550,Color.green);
			g.drawImage(title,265,75,null);
			g.drawImage(land,0,808,null);
			break;
			
		//����Ҽ���state+1=1��������Ϸ����
		case 1:
			
		g.drawImage(sky,0,0,null);
		g.drawImage(bg, 0, 200, null);
		g.drawImage(people, 310, 50 ,null);
		g.drawImage(land,0,808,null);
		//������ǰ��ȡ�Ļ������
		printWordOnGui(g,30,"��ǰ����:"+myScore,30,150,Color.black);
		
		//ҩˮ���������ҩˮͼƬ�Ļ��ƣ�ҩˮʣ���������
		g.drawImage(water, 450, 30, null);
		printWordOnGui(g,25,"ҩˮ����:"+numOfWater,550,93,Color.black);
		
		//�����������������ͼƬ�Ļ��ƣ�����ʣ���������
		g.drawImage(fc, 475, 105, null);
		printWordOnGui(g,25,"��������:"+numOfFire,550,147,Color.black);
		
		//�ؿ����Ļ���
		printWordOnGui(g,30,"��"+level+"��",30,60,Color.black);
		
		//Ŀ����ֵĻ���
		targetScore = level * 3000;
		printWordOnGui(g,30,"Ŀ�����:"+targetScore,30,110,Color.black);
		
		//ʱ�������ÿ���ؿ���Ϊ30�����Ϸʱ�䡣
		endTime = System.currentTimeMillis();
		long time  = 0;
		time = 30 - (endTime - startTime)/1000;
		printWordOnGui(g,25,"ʣ��ʱ��:"+(time>0?time:0),550,190,Color.black);
		break;
		
		case 2:
			
			//�̵����˵��
			g.drawImage(sky,0,0,null);
			g.drawImage(bg, 0, 200, null);
			printWordOnGui(g,30,"������Ҽ�����ʯͷ�ղ���",200,450,Color.green);
			printWordOnGui(g,30,"��������������ʯ������",200,550,Color.green);
			printWordOnGui(g,30,"�� �ո� ������һ��",200,650,Color.red);
			printWordOnGui(g,20,"100����",x1,400,Color.blue);
			printWordOnGui(g,20,"300����",x2,400,Color.blue);
			//�̵���Ʒ����
			g.drawImage(rockbook,x1,y1,null);
			g.drawImage(dw,x2,y2,null);
			g.drawImage(title,265,75,null);
			g.drawImage(land,0,808,null);
			break;
		
		case 3:
			g.drawImage(sky,0,0,null);
			g.drawImage(bg, 0, 200, null);
			
			//Ŀ��ֲ���ʱ����ӡ�����壬��ʾʧ�ܽ���
			printWordOnGui(g,40,"���ź�����δ��ͨ��",200,350,Color.red);
			printWordOnGui(g,40,"��Ļ�����:"+myScore,200,450,Color.red);
			printWordOnGui(g,40,"�������������¿�ʼ��Ϸ",200,550,Color.red);
		    g.drawImage(title,265,75,null);
		    g.drawImage(land,0,808,null);
			break;
			
		case 4:
			g.drawImage(bg, 0, 200, null);
			g.drawImage(sky,0,0,null);
			//��Ϸͨ��֮�󣬻��Ƴɹ�����
			//printWordOnGui(g,50,"�ƽ��",300,100,Color.yellow);
			printWordOnGui(g,40,"��ϲ�㣬��Ϸ˳��ͨ��",200,350,Color.green);
			printWordOnGui(g,40,"�ܻ�����:"+myScore,200,450,Color.green);
			printWordOnGui(g,40,"�������������¿�ʼ��Ϸ",200,550,Color.green);
	        g.drawImage(land,0,808,null);
	        g.drawImage(title,265,75,null);
			break;
		default:
			
		}
		
	}
	
	//�ܹ���Ϸʱ��
	static boolean gameTimeEnd() {
		long time = (endTime - startTime)/1000;
		if(time > 30) {
			return true;
		}
		return false;
	}
	//������Ϸ
	void reStart() {
		//�ؿ���
		level = 1;
		
		//Ŀ�����
		targetScore = 0;
		
	    //�ܷ�
		myScore = 0;
		
		//ҩˮ����
		numOfWater = 3;
		
		//ҩˮ״̬
		waterFlag = false;
		
		//��������
		numOfFire = 3;
		
		//����״̬
		fireFlag  = false;
		
		//����֮ǰ���̵�õ�������Ч��
    	bookStoneFlag =  false;
    	DiamondFlag = false;
    	
    	//���ù���״̬
    	Buying1 = true;
    	Buying2 = true;
    	
    	//���ñ��ڣ�ҩˮ��ʹ��״̬
    	Usingwater = true;
    	Usingfire = true;
	}
	
	//�����ַ����ĺ��������ô˺�����ʵ����GUI����������Ĵ�ӡ
	public static void printWordOnGui(
			Graphics g,int size,
			String str,int x,int y,Color color) {
		g.setColor(color);
		g.setFont(new Font("����",Font.CENTER_BASELINE,size));
		g.drawString(str, x, y);
		
	}
}
