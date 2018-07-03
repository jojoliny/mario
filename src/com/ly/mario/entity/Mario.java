package com.ly.mario.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.ly.mario.utils.ImageUtil;
import com.ly.mario.utils.ThreadUtil;

import constant.ConstantParam;
import constant.EntitySingleton;

public class Mario implements Runnable {
	public Mario() {
		img_Left = ImageUtil.getImage("image/mari_left.gif");
		img_Right = ImageUtil.getImage("image/mari_right.gif");
		img_RStop = ImageUtil.getImage("image/mari1.png");
		img_LStop = ImageUtil.getImage("image/mari_left1.png");
		currentImg = img_RStop;
		// 开启自己
		new Thread(this).start();
		gravity();

	}

	public void drawSelf(Graphics g) {
		g.drawImage(currentImg, x, y, null);
	}

	// 位置
	public int x = ConstantParam.Mario_X;
	public int y = ConstantParam.Mario_Y;
	// 速度
	public int speedX = 1;
	public int speedY = 1;
	// 当前图片
	public Image currentImg;
	public Image img_Left;
	public Image img_Right;
	public Image img_LStop;
	public Image img_RStop;

	ArrayList<Entity> entity = new ArrayList<Entity>();

	/**
	 * 开启引力线程
	 * 
	 * @param g
	 */
	public boolean isWork = false;

	public void gravity() {
		new Thread() {
			public void run() {
				while (true) {
					ThreadUtil.sleep(10);
					if (y <= ConstantParam.Mario_Y) {// jump
						if (!isJump) {// 没跳就下落 从水管下落？
							if (!isHit("down")) {// 下面没有障碍物
								y += speedY;
								isWork = true;
								continue;
							}
						}
					}
					isWork = false;
				}
			}
		}.start();
	}

	// 通知方向
	public boolean isLeft = false;
	public boolean isRight = false;
	public boolean isUp = false;
	public boolean isDown = false;
	public static boolean isJump = false;
	private int width = 28;
	private int height = 34;

	// 背景图的偏移量
	public static int backX = 0;
	public static int backY = 0;

	public void run() {
		while (true) {
			ThreadUtil.sleep(10);
			if (isLeft) {
				currentImg = img_Left;
				if (!isHit("left")) {
					if (x > 0)
						x -= speedX;
				}
			} else if (isRight) {
				currentImg = img_Right;
				if (!isHit("right")) {

					if (x <= 300)
						x += speedX;
					else
						backX -= speedX;
				}
			}
			if (isUp) {
				if (!isHit("up")) {
					// y -= speedY;
					if (!isWork) {

						if (!isJump) {
							isJump = true;
							jump();

						}
					}
				}

			}

		}
	}

	/***
	 * 
	 * @param direction
	 * @return
	 */
	public boolean isHit(String direction) {
		Rectangle rectangle = null;
		if ("right".equals(direction)) {
			rectangle = new Rectangle(x + speedX - backX, y, width, height);
		} else if ("left".equals(direction)) {
			rectangle = new Rectangle(x - speedX - backX, y, width, height);
		} else if ("up".equals(direction)) {
			rectangle = new Rectangle(x - backX, y - speedY, width, height);
		} else if ("down".equals(direction)) {
			rectangle = new Rectangle(x - backX, y + speedY, width, height);
		}
		for (int i = 0; i < EntitySingleton.getList().size(); i++) {
			Entity entity = EntitySingleton.getList().get(i);
			if (rectangle.intersects(entity)) {
				if (entity instanceof Coin) {
					EntitySingleton.getList().remove(i);
					return true;
				}
				if (entity instanceof Blank) {
					die();
					return true;
				}
			}
		}
		return false;
	}

	public void jump() {
		new Thread() {
			public void run() {
				// 向上
				for (int i = 0; i < 130; i++) {
					if (!isHit("up")) {
						y -= speedY;
						ThreadUtil.sleep(10);// 1秒钟100下
					} else
						break;
				}

				// 向下
				for (int i = 0; i < 130; i++) {
					if (!isHit("down")) {
						y += speedY;
						ThreadUtil.sleep(10);
					} else
						break;
				}
				isJump = false;
			}
		}.start();

	}

	public void die() {
		// 暂停所有线程
		ConstantParam.Game_Is_Die = true;
		ThreadUtil.sleep(500);

		// 暂停音乐

	}

}
