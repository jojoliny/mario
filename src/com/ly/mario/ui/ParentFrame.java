package com.ly.mario.ui;

import java.awt.LayoutManager;

import javax.swing.JFrame;

public abstract class ParentFrame extends JFrame {
	public ParentFrame(String title, int x, int y, LayoutManager manager) {
		setTitle(title);
		setSize(x, y);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setLayout(manager);
		initDate();
		initView();
		setVisible(true);
		initListen();
	}

	public abstract void initDate();

	public ParentFrame(int width, int height) {
		this(null, width, height, null);

	}

	public abstract void initView();

	// Ìí¼Ó×é¼þ
	public abstract void initListen();

}