package utils;

import static java.lang.Math.max;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Images {
	public static int white = argb(255, 255, 255);
	public static int black = argb(0, 0, 0);
	public static int[][] bwToARGB(int[][] pixels){
		int[][]r = new int[pixels.length][pixels[0].length];
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[0].length; j++) {
				r[i][j]=argb(pixels[i][j], pixels[i][j], pixels[i][j]);
			}
		}
		return r;
	}
	
	public static int argb(int r, int g, int b) {
		int col = 0xff000000;
		col |= b | (g<<8) | (r<<16);
		return col;
	}
	
	public static void showImage(int[][]pixels) {
		BufferedImage bi = new BufferedImage(pixels[0].length, pixels.length, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[0].length; j++) {
				bi.setRGB(j, i, pixels[i][j]);
			}
		}
		JFrame frame = new JFrame();
		frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                e.getWindow().dispose();
            }
        });
		frame.setSize(1024, 1024);
		JPanel jp = new JPanel() {
				public void paint(Graphics g) {
					int m = max(bi.getWidth(), bi.getHeight());
					int scale = 1024/m;
					g.drawImage(bi.getScaledInstance(bi.getWidth()*scale, bi.getHeight()*scale,Image.SCALE_SMOOTH),0,0,Color.WHITE,null);
				}
		};
		jp.setSize(512,512);
		frame.add(jp);
		frame.setVisible(true);
	}
}
