package com.ly.mario.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;

public class IOUtil {
	public static void in_Close(BufferedInputStream bis) {
		try {
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bis = null;
		}
	}

	public static void out_Close(BufferedOutputStream bos) {
		try {
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bos = null;
		}
	}

	public static void buffReader_Close(BufferedReader br) {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br = null;
		}
	}
}
