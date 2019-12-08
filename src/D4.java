import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;


public class D4 {

	Scanner scan = new Scanner(System.in);
	
	static boolean test(String s) {
		boolean dou=false;
		for(int i=1;i<s.length();i++) {
			if(s.charAt(i)<s.charAt(i-1))return false;
			if(s.charAt(i)==s.charAt(i-1)) {
				boolean lr = false;
				if(i+1<s.length() && s.charAt(i+1)==s.charAt(i))lr=true;
				if(i-2>=0 &&s.charAt(i-2)==s.charAt(i))lr=true;
				if(!lr)dou=true;
			}
			
		}
		return dou;
	}
	void solve() {
		int res =0;
		for( int i=146810;i<=612564;i++)
			if(test(""+i)) {
				res++;
				System.out.println(i);
			}
		System.out.println(res);
		
	}
	
	public static void main(String[] args) {
		D4 me = new D4();
		System.out.println(test("223455"));
		try{
			
			String sample =me.getClass().getName()+".txt";
			me.scan = new Scanner(new FileInputStream(sample));
			//me.scan = new Scanner(System.in);
		}catch (Exception e) {
			System.err.println(e);
		}
		me.solve();
	}
}
