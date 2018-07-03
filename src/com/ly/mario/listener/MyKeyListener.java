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
		// ��
		if (e.getKeyCode() == 65) {
			mario.isLeft = true;

		}
		// ��
		else if (e.getKeyCode() == 68) {
			mario.isRight = true;

		}
		// ��
		if (e.getKeyCode() == 87) {
			mario.isUp = true;
		}
		// ��
		if (e.getKeyCode() == 83) {
			mario.isDown = true;
		}
		// �ո� ��ͣ
		if (e.getKeyCode() == 32) {
			ConstantParam.Game_Is_Pause = !ConstantParam.Game_Is_Pause;
		}

	}

	public void keyReleased(KeyEvent e) {
		// ��
		if (e.getKeyCode() == 65) {
			mario.isLeft = false;
			mario.currentImg = mario.img_LStop;
		}
		// ��
		if (e.getKeyCode() == 68) {
			mario.isRight = false;
			mario.currentImg = mario.img_RStop;
		}
		// ��
		if (e.getKeyCode() == 87) {
			mario.isUp = false;
		}
		// ��
		if (e.getKeyCode() == 83) {
			mario.isDown = false;
		}
	}

}
