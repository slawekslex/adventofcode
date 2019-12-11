import java.util.*;

import utils.Images;
import utils.Prog;

import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class D11 {

	Scanner scan = new Scanner(System.in);

	
	void solve() {
		String s = readAll(scan);
		Prog prog = new Prog(s);
		prog.exitOnOutput=true;
		
		int x=64;int y=64;
		int[][]D = {{-1,0},{0,1},{1,0},{0,-1}};
		int cd =0;
		
		int[][]C = new int[128][128];
		int[][]R = new int[128][128];
		C[x][y]=1;
		while(true) {
			prog.inputs.add(C[x][y]+0L);
			int c = (int)prog.run();
			if(c==Prog.HALT)break;
			long t =prog.run();
			System.out.println(c+" "+t);
			C[x][y]=c;
			R[x][y]=1;
			if(t==0)cd = (cd+3)%4;
			if(t==1)cd = (cd+1)%4;
			x+=D[cd][0];
			y+=D[cd][1];
		}
		for (int i = 0; i < C.length; i++) {
			for(int j=0;j<C.length;j++) {
				if(C[i][j]==1)C[i][j]=Images.argb(255, 255, 255);
				if(C[i][j]==0)C[i][j]=Images.argb(0, 0, 0);
			}
		}
		Images.showImage(C);
		long res = Arrays.stream(flatten(R)).filter(u->u==1).count();
		System.out.println(res);
		
	}
	
	public static void main(String[] args) {
		D11 me = new D11();
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
