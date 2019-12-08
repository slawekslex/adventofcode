import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;


public class D1 {

	Scanner scan = new Scanner(System.in);
	
	
	void solve() {
		int res =0;
		while(scan.hasNext()) {
			int x = scan.nextInt();
			res += x/3-2;
		}
		System.out.println(res);
	}
	
	public static void main(String[] args) {
		D1 me = new D1();
		try{
			
			String sample =me.getClass().getName()+".txt";
			me.scan = new Scanner(new FileInputStream(sample));
		}catch (Exception e) {
			System.err.println(e);
		}
		me.solve();
	}
}
