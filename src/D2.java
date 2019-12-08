import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;


public class D2 {

	Scanner scan = new Scanner(System.in);
	ArrayList<Integer>A;
	
	
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
	
	int test(int x, int y) {
		ArrayList<Integer>B = (ArrayList)A.clone();
		B.set(1, x);B.set(2, y);
		run(B);
		return B.get(0);
	}
	void solve() {
		A = new ArrayList<>();
		String[]s = scan.nextLine().split(",");
		for(String ss:s)A.add(Integer.parseInt(ss));
		
		for(int i=0;i<100;i++)for(int j=0;j<100;j++) {
			int y = test(i,j);
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
