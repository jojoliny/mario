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
		// �����Լ�
		new Thread(this).start();
		gravity();

	}

	public void drawSelf(Graphics g) {
		g.drawImage(currentImg, x, y, null);
	}

	// λ��
	public int x = ConstantParam.Mario_X;
	public int y = ConstantParam.Mario_Y;
	// �ٶ�
	public int speedX = 1;
	public int speedY = 1;
	// ��ǰͼƬ
	public Image currentImg;
	public Image img_Left;
	public Image img_Right;
	public Image img_LStop;
	public Image img_RStop;

	ArrayList<Entity> entity = new ArrayList<Entity>();

	/**
	 * ���������߳�
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
						if (!isJump) {// û�������� ��ˮ�����䣿
							if (!isHit("down")) {// ����û���ϰ���
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

	// ֪ͨ����
	public boolean isLeft = false;
	public boolean isRight = false;
	public boolean isUp = false;
	public boolean isDown = false;
	public static boolean isJump = false;
	private int width = 28;
	private int height = 34;

	// ����ͼ��ƫ����
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
				// ����
				for (int i = 0; i < 130; i++) {
					if (!isHit("up")) {
						y -= speedY;
						ThreadUtil.sleep(10);// 1����100��
					} else
						break;
				}

				// ����
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
		// ��ͣ�����߳�
		ConstantParam.Game_Is_Die = true;
		ThreadUtil.sleep(500);

		// ��ͣ����

	}

}
