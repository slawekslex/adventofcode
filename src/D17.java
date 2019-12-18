import java.util.*;

import utils.Prog;

import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class D17 {

	Scanner scan = new Scanner(System.in);

	

	
	
	void route(String[]ss) {
		char[][]s = new char[ss.length][];
		for (int i = 0; i < ss.length; i++) {
			s[i]=ss[i].toCharArray();
		}
		String route="";
		char[][]ps = new char[s.length][];
		for(int i=0;i<s.length;i++)ps[i]=Arrays.copyOf(s[i],s[i].length);
		boolean[][]vis = new boolean[s.length][s[0].length];
		int sx=0;int sy=0;
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[0].length; j++) {
				if(s[i][j]=='^') {sx=i;sy=j;}
			}
		}
		int[][]D = {{-1,0},{0,1},{1,0},{0,-1}};
		int d =0;
		int x = sx;int y=sy;
		
		while(true) {
			vis[x][y]=true;
			int xx=x+D[d][0];int yy=y+D[d][1];
			boolean end = true;
			if(s[xx][yy]=='#') {
				route+="+";
				x=xx;y=yy;
				end = false;
				ps[x][y]='+';
			}else {
				for(int i=1;i<=3;i++) {
					xx=x+D[(d+i)%4][0]; yy=y+D[(d+i)%4][1];
					if(s[xx][yy]=='#' && !vis[xx][yy]) {
						if(i==1)route+="R";
						if(i==2)route+="RR";
						if(i==3)route+="L";
						d = (d+i)%4;
						end = false;
						break;
					}
				}
			}
			if(end)break;
		}
		for(char[]c:ps)System.out.println(new String(c));
		System.out.println(route);
		String cmp="";
		int cnt=0;
		for(char c:(route+"E").toCharArray()) {
			if(c=='+')cnt++;
			else {
				if(cnt>0) {cmp+=cnt;
				cmp+=" ";}
				cmp+=c;

				cnt=0;
			}
		}
		System.out.println(cmp);
	}
	
	String[] guard(String[]ss) {
		String g = "";
		for(int i=0;i<ss[0].length()+2;i++)g+=".";
		String[] n = new String[ss.length+2];
		for(int i=0;i<ss.length;i++) {
			n[i+1]='.'+ss[i]+'.';
		}
		n[0]=n[n.length-1]=g;
		return n;
	}
	
	void solve() {
		Prog p = new Prog(scan.nextLine());
		p.run();
		
		String s = "";
		for(long x:p.outputs)s+=(char)x;
		String[]ss =s.split("\n");
		
		for(String x:ss)System.out.println(x);
		
		int res =0;
		for (int i = 0; i < ss.length; i++) {
			for (int j = 0; j < ss[i].length(); j++) {
				if(ss[i].charAt(j)!='#')continue;
				int c =0;
				for(int a=-1;a<2;a++)for(int b=-1;b<2;b++) {
					if(abs(a)+abs(b)!=1)continue;
					int x = a+i;int y = b+j;
					if(x<0||x>=ss.length ||y<0||y>=ss[0].length())continue;
					if(ss[x].charAt(y)=='#')c++;
				}
				if(c==4) {
					System.out.println("adding for "+i+" "+j);
					res+=(i)*(j);
				}
			}
		}
		System.out.println(res);
		
		
		
		route(guard(ss));
	}
	
	void solve2() {
		Prog p = new Prog(scan.nextLine());
	    p.A.set(0, 2L);
		p.feedASCII( "A,B,A,C,B,C,B,A,C,B\n");
		p.feedASCII( "L,10,L,6,R,10\n");
		p.feedASCII( "R,6,R,8,R,8,L,6,R,8\n");
		p.feedASCII( "L,10,R,8,R,8,L,10\n");
		p.feedASCII( "n\n");
		p.run();
		p.printASCII();
	
	}
	
	public static void main(String[] args) {
		D17 me = new D17();
		BigInteger bi=BigInteger.ONE;
		for(int i=2;i<27;i++) {
			bi=bi.multiply(BigInteger.valueOf(i));
			System.out.println(bi + " "+bi.toString().length());
		}
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
		me.solve2();
	}
}
