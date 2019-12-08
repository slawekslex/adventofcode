import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;


public class D5 {

	Scanner scan = new Scanner(System.in);

	static class Prog {
		ArrayList<Integer> A;
		int p=0;
		ArrayList<Integer> inputs=new ArrayList<>();

		Prog(ArrayList A) {
			this.A = (ArrayList<Integer>)A.clone();
		}
		
		Prog(String s){
			A = new ArrayList<>();
			for (String ss : s.split(","))
				A.add(Integer.parseInt(ss));
		}
		
		int input() {
			int x = inputs.get(0);
			inputs.remove(0);
			return x;
		}

		int get(boolean imm) {
			int x = A.get(p++);
			if(!imm)x = A.get(x);
			return x;
		}
		
		int run() {
			while (true) {
				int cd = A.get(p++);
				int op = cd % 100;
				boolean am = (cd / 100) % 2 == 1;
				boolean bm = (cd / 1000) % 2 == 1;
				boolean cm = (cd / 10000) % 2 == 1;
				if (op == 99)
					return -1;
				if (op == 1) {
					int a = get(am);
					int b = get(bm);
					int c = get(true);
					A.set(c, a + b);
				}
				if (op == 2) {
					int a = get(am);
					int b = get(bm);
					int c = get(true);
					A.set(c, a * b);
				}
				if (op == 3) {
					int a = get(true);
					int x = input();
					System.out.println("setting " + x);
					A.set(a, x);
				}
				if (op == 4) {
					int a = get(am);
					// System.out.println("prining "+a);
					return a;
				}
				if (op == 5) {
					int a = get(am);
					int b = get(bm);
					if (a != 0)
						p = b;

				}
				if (op == 6) {
					int a = get(am);
					int b = get(bm);
					if (a == 0)
						p = b;
				}
				if (op == 7) {
					int a = get(am);
					int b = get(bm);
					int c = get(true);
					A.set(c, a < b ? 1 : 0);
				}
				if (op == 8) {
					int a = get(am);
					int b = get(bm);
					int c = get(true);
					A.set(c, a == b ? 1 : 0);
				}
			}
		}
	}

	//15724522
	void solve() {
		Prog p = new Prog(scan.nextLine());
		p.inputs.add(5);
		System.out.println(p.run());
	}
	
	public static void main(String[] args) {
		D5 me = new D5();
		try{
			
			String sample ="D5.txt";
			me.scan = new Scanner(new FileInputStream(sample));
			//me.scan = new Scanner(System.in);
		}catch (Exception e) {
			System.err.println(e);
		}
		me.solve();
	}
}
