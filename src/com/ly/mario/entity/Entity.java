package com.ly.mario.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.ly.mario.utils.ImageUtil;

public class Entity extends Rectangle {

	public Image img;

	public Entity(int x, int y, int width, int height, String path) {
		super(x, y, width, height);
		img = ImageUtil.getImage(path);
	}

	public void drawSelf(Graphics g) {
		g.drawImage(img, x + Mario.backX, y + Mario.backY, null);
	}

}
