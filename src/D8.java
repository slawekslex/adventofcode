import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

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
		char[][]M = new char[6][25];
		for(char[]m:M)Arrays.fill(m,'2');
		String s = readAll();
		int res=0;
		int b = 100000;
		for(int l=0;l<s.length();l+=25*6) {
			int zer=0;int on=0;int tw=0;
			for(int r=0;r<6;r++)for(int c=0;c<25;c++) {
				int i = r*25+c;
				if(M[r][c]=='2')M[r][c]=s.charAt(l+i);
				if(s.charAt(l+i)=='0')zer++;
				if(s.charAt(l+i)=='1')on++;
				if(s.charAt(l+i)=='2')tw++;
			}
			if(zer<b) {
				b=zer;
				res = on*tw;
			}
		}
		for(int i=0;i<M.length;i++)for(int j=0;j<M[0].length;j++) {
			if(M[i][j]=='0')M[i][j]=' ';
			if(M[i][j]=='1')M[i][j]='#';
		}
		for(char[]c:M)System.out.println(new String(c));
		System.out.println(res);
		
	}
	
	public static void main(String[] args) {
		D8 me = new D8();
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
