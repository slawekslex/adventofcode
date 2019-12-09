import java.util.*;

import utils.Prog;

import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;

public class D7 {

	Scanner scan = new Scanner(System.in);
	String readAll() {
		String res ="";
		while(scan.hasNext()) {
			String s = scan.nextLine();
			res+=s;
		}
		return res;
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
				progs[i].exitOnOutput=true;
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
		D7 me = new D7();
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
