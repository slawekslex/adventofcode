import java.util.*;

import utils.Prog;

import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class D25 {

	Scanner scan = new Scanner(System.in);

	
	void solve() {
		Prog p = new Prog(readAll(scan));
		Scanner comScan = new Scanner(System.in);
		while(true) {
			p.run();
			p.printASCII();
			String s = comScan.nextLine();
			p.feedASCII(s+"\n");
		}
			
	}
	
	public static void main(String[] args) {
		D25 me = new D25();
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
