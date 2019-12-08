package y2018;
import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class C3 {

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
		int[][]A = new int[2000][2000];
		ArrayList<String>B = new ArrayList<>();
		while(scan.hasNext()) {
			String s = scan.nextLine();
			B.add(s);
			System.out.println(s);
			String p = s.split(" ")[2];
			String w = s.split(" ")[3];
			int x =Integer.parseInt(p.substring(0, p.indexOf(',')));
			int y =Integer.parseInt(p.substring(p.indexOf(',')+1, p.length()-1));
			int xl = Integer.parseInt(w.split("x")[0]);
			int yl = Integer.parseInt(w.split("x")[1]);
			for(int i=0;i<xl;i++)for(int j=0;j<yl;j++) {
				A[x+i][y+j]++;
			}
		}
		int res =0;
		for(int i=0;i<A.length;i++)for(int j=0;j<A.length;j++)if(A[i][j]>1)res++;
		for(String s:B) {
		String p = s.split(" ")[2];
		String w = s.split(" ")[3];
		int x =Integer.parseInt(p.substring(0, p.indexOf(',')));
		int y =Integer.parseInt(p.substring(p.indexOf(',')+1, p.length()-1));
		int xl = Integer.parseInt(w.split("x")[0]);
		int yl = Integer.parseInt(w.split("x")[1]);
		boolean ok =true;
		for(int i=0;i<xl;i++)for(int j=0;j<yl;j++) {
			if(A[x+i][y+j]!=1)ok=false;
		}
		if(ok)System.out.println(s);
		}
		System.out.println(res);
		
	}
	
	public static void main(String[] args) {
		C3 me = new C3();
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
