package constant;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ly.mario.entity.Blank;
import com.ly.mario.entity.Brick;
import com.ly.mario.entity.Coin;
import com.ly.mario.entity.Entity;
import com.ly.mario.entity.Pipe;

public class EntitySingleton {
	/**
	 * 单例模式：第一步，不能给别人new
	 */
	private EntitySingleton() {
	}

	/**
	 * 第一步：读取地图，保存行数据
	 */
	public static ArrayList<String> lines = new ArrayList<String>();
	static BufferedReader br = null;

	public static List<Entity> list;

	public static List<Entity> getList() {
		if (list == null) {
			list = new ArrayList<Entity>();
			// Pipe pipe = new Pipe(200, 272);
			// list.add(pipe);
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
			 * 第二步：获取行列，读取
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
					case '1':
						Brick brick = new Brick(x, y);
						list.add(brick);
						break;
					case '2':
						Coin coin = new Coin(x, y);
						list.add(coin);
						break;
					case '3':
						// System.out.println("水管");
						Pipe pipe = new Pipe(x, y);
						list.add(pipe);
						break;
					case '5':
						Blank blank = new Blank(x, y);
						list.add(blank);
						break;
					}
				}
			}

		}
		return list;
	}

	static EntitySingleton instance = new EntitySingleton();

	public static EntitySingleton getInstance() {
		return instance;
	}

	// static EntitySingleton instance;
	// public static EntitySingleton getInstance(){
	// if(instance==null){
	// synchronized(EntitySingleton.class){
	// if(instance==null)
	// instance=new EntitySingleton();
	// }
	// }
	// return instance;
	// }

}
