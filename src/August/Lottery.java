package August;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class Lottery {
	public int Amount = 0;// 抽奖名单人数
	private ArrayList<String> Num = new ArrayList<String>();

	public Lottery(String path) {// 从文件读入抽奖名单
		FileInputStream fis;
		
		try {
			fis = new FileInputStream(path);
			DataInputStream dis = new DataInputStream(fis);
			
			Amount = dis.readInt();
			for (int i = 1; i <= Amount; i++) {
				Num.add(dis.readUTF());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getRandomNum() {// 随机抽取一个号码
		int n = Math.abs(new Random().nextInt() % Amount);
		return Num.get(n);
	}
	
	public String getNum(int n) {// 获取一个号码
		return Num.get(n);
	}
	public static void saveData(String path){
		FileOutputStream fos;
		
		try {
			fos = new FileOutputStream(path);
			DataOutputStream dos = new DataOutputStream(fos);
			dos.writeInt(800);
			for (int i = 1; i <= 800; i++) {
				dos.writeUTF(String.valueOf(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 使图片白色部分透明 */
	public static Image makeColorTransparent(Image im, final Color color) {
		ImageFilter filter = new RGBImageFilter() {
			public int markerRGB = color.getRGB() | 0xFF000000;

			public final int filterRGB(int x, int y, int rgb) {
				if ((rgb | 0xFF000000) == markerRGB) {
					return 0x00FFFFFF & rgb;
				} else {
					return rgb;
				}
			}
		};
		ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}
}
