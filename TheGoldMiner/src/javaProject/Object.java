package javaProject;

import java.awt.*;
public class Object {
    //����
	int x;
	int y;
	
	//����
	int length;
	int width;
	
	//ͼƬ
	Image image;
	
	//��ǣ���¼�Ƿ�ͨ����ײ���
	boolean flag = false;
	
	//����
	int weight;
	
	//����
	int score;
	
	//���� 1��ʾ��� 2��ʯ�� 3����ʯ 4��ҩˮ 5���ʺŴ���
	int type;
	
	void paintSelf(Graphics g) {
		g.drawImage(image, x, y, null);
		
	}
	public int getWidth() {
		return width;
	}
	//��ȡ���Σ���֤��Ʒλ�ò��غ�
	public Rectangle getRec() {
		return new Rectangle(x,y,width,length);
	}
}