package javaProject;

import java.awt.*;
public class Object {
    //����
	public int x;
	public int y;
	
	//����
	public int length;
	public int width;
	
	//ͼƬ
	Image image;
	
	//��ǣ���¼�Ƿ�ͨ����ײ���
	public boolean flag = false;
	
	//����
	public int weight;
	
	//����
	public int score;
	
	//���� 1��ʾ��� 2��ʯ�� 3����ʯ 4��ҩˮ 5���ʺŴ���
	public int type;
	
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