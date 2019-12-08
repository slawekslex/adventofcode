package y2018;
import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class C5 {

	Scanner scan = new Scanner(System.in);
	String readAll() {
		String res ="";
		while(scan.hasNext()) {
			String s = scan.nextLine();
			res+=s;
		}
		return res;
	}
	
	int doit(String s) {
		while(true) {
			StringBuffer sb = new StringBuffer();
			boolean ch=false;
			for(int i=0;i<s.length();i++) {
				if(i+1<s.length() && abs(s.charAt(i)-s.charAt(i+1))==32) {
					i++;
					ch=true;
				}else {
					sb.append(s.charAt(i));
				}
			}
			s = sb.toString();
			if(!ch)break;
		}
		return s.length();
	}
	
	void solve() {
		String s = readAll();
		System.out.println(s.length());
		System.out.println('A'-'a');
		int b = 1000000;
		for(int i=0;i<26;i++) {
			char c1 = (char)('a'+i);
			char c2 = (char)('A'+i);
			String r = "["+c1+c2+"]";
			System.out.println(r);
			String z = s.replaceAll(r, "");
			int x = doit(z);
			if(x<b)b=x;
		}
		System.out.println(b);
	}
	
	public static void main(String[] args) {
		C5 me = new C5();
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
