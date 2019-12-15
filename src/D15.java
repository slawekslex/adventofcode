import java.util.*;

import utils.Prog;

import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class D15 {

	Scanner scan = new Scanner(System.in);

	Prog prog;
	char[][]M= new char[44][44];
	
	int[][]D = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
	int[]RD = new int[] {1,0,3,2};
	int cx,cy;
	int sx,sy;
	long move(int d) {
		prog.inputs.add(d+1L);
		
		long out = prog.run();
		if(out>0) {
			cx+=D[d][0];
			cy+=D[d][1];
		}
		
		return out;
	}
	void find(int x, int y, boolean[][]V) {
		V[x][y]=true;
		assert x==cx && y==cy;
		for(int d=0;d<4;d++) {
			int i = D[d][0];int j=D[d][1];
			if(i+x<0||j+y<0||i+x==V.length || j+y==V[0].length) {
				System.err.println("out of bound");
				continue;
			}
			if(V[i+x][j+y])continue;
			long out = move(d);
			if(out==0)M[i+x][j+y]='#';
			if(out==1) {
				M[i+x][j+y]='.';
				find(x+i,y+j,V);
				long out2 = move(RD[d]);
				if(out2!=1) {
					System.err.println("error "+out2);
				}
			}
			if(out==2) {
				M[i+x][j+y]='S';
				find(x+i,y+j,V);
				long out2 = move(RD[d]);
				System.err.println("found at "+(x+i)+" "+(y+j));
				sx=x+1;sy=y+j;
			}

		}
		
		
	}
	
	int doit(int x, int y, boolean[][]V) {
		V[x][y]=true;
		int res = 1000000000;
		for(int d=0;d<4;d++) {
			int i = D[d][0];int j=D[d][1];
			if(i+x<0||j+y<0||i+x==V.length || j+y==V[0].length) {
				continue;
			}
			if(V[i+x][j+y])continue;
			if(M[i+x][j+y]=='#')continue;
			else if(M[i+x][j+y]=='S')return 1;
			else res = min(res, 1+doit(i+x,j+y,V));
		}
		
		return res;
	}
	void solve() {
		
		for(char[]m:M)fill(m,'?');
		prog = new Prog(readAll(scan));
		prog.exitOnOutput = true;
		cx = M.length/2; cy = M[0].length/2;
		find(cx,cy, new boolean[M.length][M[0].length]);

		System.out.println(doit(cx,cy, new boolean[M.length][M[0].length]));
		boolean[][]V = new boolean[M.length][M[0].length];
		
		LinkedList<int[]>Q = new LinkedList<>();
		Q.add(new int[] {sx,sy,0});
		int maxg=0;
		while(!Q.isEmpty()) {
			int[]q = Q.removeFirst();
			int x =q[0];int y = q[1];int g = q[2];
			maxg = max(g,maxg);
			V[x][y]=true;
			for(int d=0;d<4;d++) {
				int i = D[d][0];int j=D[d][1];
				if(i+x<0||j+y<0||i+x==V.length || j+y==V[0].length) {
					System.err.println("out of bound");
					continue;
				}
				if(V[i+x][j+y])continue;
				
				if(M[i+x][j+y]=='.'  ||M[i+x][j+y]=='S'){
					Q.add(new int[] {x+i,y+j,g+1});	
				}
			}
		}
		
		for(char[]m:M)System.out.println(new String(m));
		System.out.println(maxg);
	}
	
	public static void main(String[] args) {
		D15 me = new D15();
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
