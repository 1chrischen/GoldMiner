package javaProject;

import java.awt.Toolkit;

public class Bag extends Object{
	
	Bag() {
		
		double ran = Math.random();
		
		this.x = (int)(Math.random() * 700);
		this.y = (int)(Math.random() * 460 + 320);
		this.width = 50;
		this.length = 50;
		this.flag = false;
		this.weight = (int) (ran * 100 + 10);
		this.score = (int) (ran * 800) + 200;
		this.type = 5;
		this.image = Toolkit.getDefaultToolkit().getImage("imgs/part2_bag01.png");
	}
}