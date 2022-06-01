package javaProject;

import java.awt.Toolkit;

public class Water extends Object{
	
	Water() {
		this.x = (int)(Math.random() * 700);
		this.y = (int)(Math.random() * 460 + 320);
		this.width = 52;
		this.length = 52;
		this.flag = false;
		this.weight = 30;
		this.type = 4;
		this.image = Toolkit.getDefaultToolkit().getImage("imgs/waterObj.png");
	}
}