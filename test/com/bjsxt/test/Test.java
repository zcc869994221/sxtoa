package com.bjsxt.test;

import java.awt.Color;
import java.awt.Graphics;

public class Test {
	public void paint(Graphics g) {
		g.setColor(Color.pink);   //设定g的颜色为粉红色
		g.fillRect(10,10,20,20);  //画一个填充为粉红色的矩形
	}
}
