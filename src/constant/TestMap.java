package constant;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TestMap {
	/**
	 * ��һ������ȡ��ͼ������������
	 */
	public static ArrayList<String> lines = new ArrayList<String>();
	static BufferedReader br = null;

	public static void readPainting() throws FileNotFoundException {
		try {
			FileReader reader = new FileReader("map.txt");
			br = new BufferedReader(reader);
			String line;
			try {
				while ((line = br.readLine()) != null) {
					lines.add(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				br = null;
			}
		}
		/**
		 * �ڶ�������ȡ���У���ȡ
		 */
		int row = lines.size();
		int colum = lines.get(0).length();
		for (int i = 0; i < row; i++) {
			String line = lines.get(i);
			for (int j = 0; j < colum; j++) {
				char chAt = line.charAt(j);
				int x = j * 30;
				int y = i * 30;
				switch (chAt) {
				case '3':
					System.out.println("ˮ��");
					break;
				}
			}
		}
	}

}
