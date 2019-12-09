import java.util.*;

import utils.Prog;

import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;


public class D2 {

	Scanner scan = new Scanner(System.in);
	String program;
	
	
	void run(ArrayList<Integer>A) {
		int p=0;
		while(true) {
			int op = A.get(p);
			if(op ==99)return;
			int a = A.get(A.get(p+1));
			int b = A.get(A.get(p+2));
			int c = A.get(p+3);
			if(op==1)A.set(c, a+b);
			if(op==2)A.set(c, a*b);
			p+=4;
		}
	}
	
	long test(long x, long y) {
		Prog p = new Prog(program);
		p.A.set(1, x);p.A.set(2, y);
		p.run();
		return p.A.get(0);
	}
	void solve() {
		program = scan.nextLine();
		
		for(int i=0;i<100;i++)for(int j=0;j<100;j++) {
			long y = test(i,j);
			if(y==19690720)
				System.out.println(100*i+j);
		}
		
	}
	
	public static void main(String[] args) {
		D2 me = new D2();
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
