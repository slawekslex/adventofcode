package y2018;
import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class C2 {

	Scanner scan = new Scanner(System.in);
	String readAll() {
		String res ="";
		while(scan.hasNext()) {
			String s = scan.nextLine();
			res+=s;
		}
		return res;
	}
	
	String get(String a, String b) {
		int p=-1;
		for(int i=0;i<a.length();i++) {
			if(a.charAt(i)!=b.charAt(i)) {
				if(p==-1)p=i;
				else return null;
			}
		}
		return a.substring(0,p)+a.substring(p+1);
	}
	
	void solve() {
		int tw=0;int th=0;
		ArrayList<String>A = new ArrayList<>();
		while(scan.hasNext()) {
			String s = scan.next();
			A.add(s);
			int[]cnts = new int[300];
			for(char c:s.toCharArray())cnts[c]++;
			boolean a=false;boolean b=false;
			for(int x:cnts)if(x==2)a=true;else if(x==3)b=true;
			if(a)tw++;if(b)th++;
		}
		for(int i=0;i<A.size();i++)for(int j=i+1;j<A.size();j++) {
			String s=get(A.get(i), A.get(j));
			if(s!=null)System.out.println(s);
		}
		System.out.println(tw*th);
		
	}
	
	public static void main(String[] args) {
		C2 me = new C2();
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
