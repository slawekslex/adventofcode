import java.util.*;

import utils.Prog;

import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;


public class D13 {

	Scanner scan = new Scanner(System.in);

	void show_board(Prog p) {
		
	}
	
	void solve()throws Exception {
		Prog p = new Prog(readAll(scan));
		p.A.set(0, 2L);
		char[] []C = new char[26][42];
		for(char[]c:C)fill(c,' ');
		int score=0;
		while(true) {
			long ret = p.run();
			int ox =0;
			int _x=0;
			
			for(int i=0;i<p.outputs.size();i+=3) {
				int x = (int)(long)p.outputs.get(i);
				int y = (int)(long)p.outputs.get(i+1);
				int t = (int)(long)p.outputs.get(i+2);
				if(x==-1) {
					score=t;
					continue;
				}
				if(t==0)C[y][x]=' ';
				if(t==1)C[y][x]='#';
				if(t==2)C[y][x]='X';
				if(t==3) {C[y][x]='-';_x=x;}
				if(t==4) {C[y][x]='o';ox=x;}
			}
			p.outputs.clear();
			for(char[]c:C)System.out.println(new String(c));
			System.out.println("score "+score);
			if(ret==Prog.HALT)break;
			if(_x<ox)p.inputs.add(1L);
			else if(_x>ox)p.inputs.add(-1L);
			else p.inputs.add(0L);			
		}

		
	}
	
	public static void main(String[] args) throws Exception {
		D13 me = new D13();
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
