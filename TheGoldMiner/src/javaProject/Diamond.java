package javaProject;

import java.awt.Toolkit;

public class Diamond extends Object{
	
	Diamond() {
		this.x = (int)(Math.random() * 700);
		this.y = (int)(Math.random() * 460 + 320);
		this.width = 36;
		this.length = 36;
		this.flag = false;
		this.weight = 20;
		this.score = 700;
		this.type = 3;
		this.image = Toolkit.getDefaultToolkit().getImage("imgs/dia.png");
	}
} 