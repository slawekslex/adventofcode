import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class SlexTemplate {

	Scanner scan = new Scanner(System.in);

	
	void solve() {
		
	}
	
	public static void main(String[] args) {
		SlexTemplate me = new SlexTemplate();
		try{
			String sample =me.getClass().getName()+".txt";
			me.scan = new Scanner(System.in);
			if(new File(sample).isFile()) {
				me.scan = new Scanner(new FileInputStream(sample));
				System.err.println(new Scanner(new File(sample)).useDelimiter("\\Z").next());
			} else {
				System.err.println("INPUT FILE "+sample+" DOES NOT EXIST");
			}					
		}catch (Exception e) {
			System.err.println(e);
		}
		long time = System.currentTimeMillis();
		me.solve();
		time = System.currentTimeMillis() - time;
		System.err.format("time: %.2fs\n",time/1000.0);
	}
}
