import java.util.*;

import static utils.U.*;

import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;

public class D8 {

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
		int[][][]M = viewAs(ints(readAll(), ""),-1,6,25);
		long res = 0;
		long b = 100000;
		char[][]R = new char[6][25];
		for(char[]r:R)fill(r,'2');
		for(int[][]layer:M) {
			int[]fl = flatten(layer);
			long zeros =stream(fl).filter(x->x==0).count();
			long ones = stream(fl).filter(x->x==1).count();
			long twos = stream(fl).filter(x->x==2).count();
			if(zeros<b) {
				b=zeros;
				res=ones*twos;
			}
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 25; j++) {
					if(R[i][j]=='2'&&layer[i][j]==0)R[i][j]=' ';
					if(R[i][j]=='2'&&layer[i][j]==1)R[i][j]='#';
				}
			}
		}
		System.out.println(res);
		for(char[]c:R)System.out.println(new String(c));
	   
	}
	
	public static void main(String[] args) {
		D8 me = new D8();
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
		me.solve();
	}
}
