package com.ly.mario.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.ly.mario.entity.Mario;

import constant.ConstantParam;

/**
 * @author Administrator
 *
 */
public class MyKeyListener implements KeyListener {
	Mario mario = new Mario();

	public MyKeyListener(Mario mario) {
		this.mario = mario;
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		// System.out.println(e.getKeyCode());
		// 左
		if (e.getKeyCode() == 65) {
			mario.isLeft = true;

		}
		// 右
		else if (e.getKeyCode() == 68) {
			mario.isRight = true;

		}
		// 上
		if (e.getKeyCode() == 87) {
			mario.isUp = true;
		}
		// 下
		if (e.getKeyCode() == 83) {
			mario.isDown = true;
		}
		// 空格 暂停
		if (e.getKeyCode() == 32) {
			ConstantParam.Game_Is_Pause = !ConstantParam.Game_Is_Pause;
		}

	}

	public void keyReleased(KeyEvent e) {
		// 左
		if (e.getKeyCode() == 65) {
			mario.isLeft = false;
			mario.currentImg = mario.img_LStop;
		}
		// 右
		if (e.getKeyCode() == 68) {
			mario.isRight = false;
			mario.currentImg = mario.img_RStop;
		}
		// 上
		if (e.getKeyCode() == 87) {
			mario.isUp = false;
		}
		// 下
		if (e.getKeyCode() == 83) {
			mario.isDown = false;
		}
	}

}
