package javaProject;

import java.awt.Toolkit;

public class Rock extends Object{
	
	Rock() {
		this.x = (int)(Math.random() * 700);
		this.y = (int)(Math.random() * 460 + 320);
		this.width = 71;
		this.length = 71;
		this.flag = false;
		this.weight = 150;
		this.score = 120;
		this.type = 2;
		this.image = Toolkit.getDefaultToolkit().getImage("imgs/rock1.png");
	}
}