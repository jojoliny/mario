package com.ly.mario.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import com.ly.mario.entity.Entity;
import com.ly.mario.entity.Mario;
import com.ly.mario.listener.MyKeyListener;
import com.ly.mario.utils.ImageUtil;
import com.ly.mario.utils.ThreadUtil;

import constant.EntitySingleton;

public class MainFrame extends ParentFrame {
	Mario mario;

	public MainFrame(String title, int x, int y, LayoutManager manager) {
		super(title, x, y, manager);

		new Thread() {
			public void run() {
				while (true) {
					ThreadUtil.sleep(100);
					repaint();
				}
			}
		}.start();
	}

	public void initView() {
		mario = new Mario();

	}

	public void initListen() {
		addKeyListener(new MyKeyListener(mario));
	}

	/*
	 * @see com.ly.mario.ui.ParentFrame#initDate()
	 */
	public void initDate() {

	}

	public void paint(Graphics g) {
		// super.paint(g);
		// 创建一张图片
		Image img = createImage(getWidth(), getHeight());
		// 拿到该图片的画笔
		Graphics img_g = img.getGraphics();
		// 在上面粘贴图片
		// ImageIcon ii = new ImageIcon("image/startBack.jpg");
		// img_g.drawImage(ii.getImage(), 0, 0, null);
		Image img_bj = ImageUtil.getImage("image/startBack.jpg");
		img_g.drawImage(img_bj, mario.backX, mario.backY, null);
		mario.drawSelf(img_g);
		// try {
		// TestMap.readPainting();
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
		for (int i = 0; i < EntitySingleton.getList().size(); i++) {
			Entity entity = EntitySingleton.getList().get(i);
			entity.drawSelf(img_g);
			// TestMap.lines.get(i).drawSelf();
			// System.out.println(entity);
		}

		g.drawImage(img, 0, 0, null);
	}

}
