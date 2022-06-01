package javaProject;

import java.awt.Toolkit;

public class Gold extends Object{
    
	
	Gold() {
		this.x = (int)(Math.random() * 700);
		this.y = (int)(Math.random() * 460 + 320);
		this.width = 52;
		this.length = 52;
		this.flag = false;
		this.weight = 35;
		this.score = 350;
		this.type = 1;
		this.image = Toolkit.getDefaultToolkit().getImage("imgs/MediumGold.gif");
	}
}
class GoldMini extends Gold{
	GoldMini(){
		this.width = 36;
		this.length = 36;
		this.weight = 20;
		this.score = 180 ;
		this.image = Toolkit.getDefaultToolkit().getImage("imgs/TinyGold.gif");
	}
}
class GoldPlus extends Gold{
	GoldPlus(){
		this.width = 100;
		this.length = 100;
		this.weight = 90;
		this.score = 580;
		this.image = Toolkit.getDefaultToolkit().getImage("imgs/BigGold.gif");
	}
}
