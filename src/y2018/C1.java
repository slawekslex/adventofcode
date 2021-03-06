package y2018;
import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class C1 {

	Scanner scan = new Scanner(System.in);
	String readAll() {
		String res ="";
		while(scan.hasNext()) {
			String s = scan.nextLine();
			res+=s;
		}
		return res;
	}
	
	void solve() {
		int r =0;
		ArrayList<Integer>A = new ArrayList<>();
		while(scan.hasNext()) {
			int s = scan.nextInt();
			A.add(s);
		}
		HashSet<Integer>H = new HashSet<>();
		while(true) {
			for(int x:A) {r+=x;
				if(H.contains(r)) {System.out.println(r);return;}
				H.add(r);
			}
		}
	
	}
	
	public static void main(String[] args) {
		C1 me = new C1();
		try{
			String sample =me.getClass().getSimpleName()+".txt";
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
		me.solve();
	}
}
