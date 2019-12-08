import java.util.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;

public class D7IntComp {

	Scanner scan = new Scanner(System.in);
	String readAll() {
		String res ="";
		while(scan.hasNext()) {
			String s = scan.nextLine();
			res+=s;
		}
		return res;
	}
	static class Prog {
		static int HALT = Integer.MIN_VALUE;
		ArrayList<Long> A;
		int p=0;
		ArrayList<Long> inputs=new ArrayList<>();

		Prog(ArrayList<Long> A) {
			this.A = (ArrayList<Long>)A.clone();
		}
		
		Prog(String s){
			A = new ArrayList<>();
			for (String ss : s.split(","))
				A.add(Long.parseLong(ss));
		}
		
		long input() {
			long x = inputs.get(0);
			inputs.remove(0);
			return x;
		}

		long get(boolean imm) {
			long x = A.get(p++);
			if(!imm)x = A.get((int)x);
			return x;
		}
		
		long run() {
			while (true) {
				long cd = A.get(p++);
				long op = cd % 100;
				boolean am = (cd / 100) % 2 == 1;
				boolean bm = (cd / 1000) % 2 == 1;
				boolean cm = (cd / 10000) % 2 == 1;
				if (op == 99)
					return HALT;
				if (op == 1) {
					long a = get(am);
					long b = get(bm);
					long c = get(true);
					A.set((int)c, a + b);
				}
				if (op == 2) {
					long a = get(am);
					long b = get(bm);
					long c = get(true);
					A.set((int)c, a * b);
				}
				if (op == 3) {
					long a = get(true);
					long x = input();
					System.out.println("setting " + x);
					A.set((int)a, x);
				}
				if (op == 4) {
					long a = get(am);
					// System.out.println("prining "+a);
					return a;
				}
				if (op == 5) {
					long a = get(am);
					long b = get(bm);
					if (a != 0)
						p = (int)b;
				}
				if (op == 6) {
					long a = get(am);
					long b = get(bm);
					if (a == 0)
						p = (int)b;
				}
				if (op == 7) {
					long a = get(am);
					long b = get(bm);
					long c = get(true);
					A.set((int)c, a < b ? 1L : 0L);
				}
				if (op == 8) {
					long a = get(am);
					long b = get(bm);
					long c = get(true);
					A.set((int)c, a == b ? 1L : 0);
				}
			}
		}
	}

	ArrayList<int[]> P = new ArrayList<>();

	void permut(int p, int[] a, boolean[] u) {
		if (p == a.length) {
			P.add((int[]) a.clone());
			return;
		}

		for (int i = 0; i < 5; i++) {
			if (!u[i]) {
				a[p] = i + 5;
				u[i] = true;
				permut(p + 1, a, u);
				u[i] = false;
			}
		}
	}

	void solve() {
		ArrayList<Long> A = new ArrayList<>();
		String[] s = readAll().split(",");
		for (String ss : s)
			A.add(Long.parseLong(ss));
		permut(0, new int[5], new boolean[5]);
		long res = 0;

		for (int[] p : P) {
			long out = 0;
			long lout = 0;
			Prog[] progs = new Prog[5];
			System.out.println(Arrays.toString(p));
			for (int i = 0; i < 5; i++) {
				progs[i] = new Prog(A);
				progs[i].inputs.add(p[i]+0L);
			}
			boolean fin = false;
			while (!fin) {
				for (int i = 0; i < p.length; i++) {
					progs[i].inputs.add(out);
					long r = progs[i].run();
					if (r == Prog.HALT) {
						fin = true;
						break;
					}
					out = r;
					if (i == 4)
						lout = r;
				}
			}
			res = max(res, lout);
		}
		System.out.println(res);

	}

	// 54163586
	public static void main(String[] args) {
		D7IntComp me = new D7IntComp();
		try {

			String sample = "D7.txt";
			me.scan = new Scanner(new FileInputStream(sample));
			//me.scan = new Scanner(System.in);
		} catch (Exception e) {
			System.err.println(e);
		}
		me.solve();
	}
}
