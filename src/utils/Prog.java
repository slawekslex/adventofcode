package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Prog {
	public static int HALT = Integer.MIN_VALUE;
	public ArrayList<Long> A;
	int p=0;
	long base =0;
	public ArrayList<Long> inputs=new ArrayList<>();
	public ArrayList<Long> outputs=new ArrayList<>();
	public boolean exitOnOutput = false;

	public Prog(ArrayList<Long> A) {
		this.A = (ArrayList<Long>)A.clone();
		for(int i=0;i<1000;i++)this.A.add(0L);
	}
	
	public Prog(String s){
		A = new ArrayList<>();
		for (String ss : s.split(","))
			A.add(Long.parseLong(ss));
		for(int i=0;i<1000;i++)this.A.add(0L);
	}
	
	public long input() {
		long x = inputs.get(0);
		inputs.remove(0);
		return x;
	}

	public long read(long mode) {
		long x = A.get(p++);
		if(mode==2) {
			x = A.get((int)(x+base));
		}
		if(mode==0) {
			x = A.get((int)x);
		}
		return x;
	}
	
	public void write(long mode, long x) {
		if(mode==1)throw new RuntimeException("Writing wit mode 1");
		long a = A.get(p++);
		if(mode==0) {
			A.set((int)a, x);
		}
		if(mode==2) {
			A.set((int)(a+base), x);
		}
	}
	
	public long run() {
		while (true) {
			long cd = A.get(p++);
			long op = cd % 100;
			long am = (cd / 100) % 10;
			long bm = (cd / 1000) % 10;
			long cm = (cd / 10000) % 10;
			
			if (op == 99)
				return HALT;
			if (op == 1) {
				long a = read(am);
				long b = read(bm);
				write(cm, a+b);
			}
			if (op == 2) {
				long a = read(am);
				long b = read(bm);

				write(cm, a*b);
			}
			if (op == 3) {
				long x = input();
				write(am,x);
			}
			if (op == 4) {
				long a = read(am);
				//System.out.println("prining "+a);
				outputs.add(a);
				if(exitOnOutput) return a;
			}
			if (op == 5) {
				long a = read(am);
				long b = read(bm);
				if (a != 0)
					p = (int)b;
			}
			if (op == 6) {
				long a = read(am);
				long b = read(bm);
				if (a == 0)
					p = (int)b;
			}
			if (op == 7) {
				long a = read(am);
				long b = read(bm);
				write(cm, a < b ? 1L : 0L );
			}
			if (op == 8) {
				long a = read(am);
				long b = read(bm);
				write(cm, a == b ? 1L : 0);
			}
			if (op == 9) {
				long a = read(am);
				base +=a;
			}
		}
	}

	
	static void test_d2_ex() {
		Prog p= new Prog("1,9,10,3,2,3,11,0,99,30,40,50");
		p.run();
		List<Long> exp = Arrays.asList(3500L,9L,10L,70L,2L,3L,11L,0L,99L,30L,40L,50L);
		List A =p.A.subList(0, exp.size());	
	    assert A.equals(exp);
	}
	
	static void test_d2() throws FileNotFoundException {
		String s = new Scanner(new FileInputStream("D2.txt")).nextLine();
		Prog p = new Prog(s);
		p.A.set(1, 52L);p.A.set(2, 8L);
		p.run();
		assert p.A.get(0) == 19690720;
	}
	
	static void test_d5_p1() throws FileNotFoundException{
		String s = new Scanner(new FileInputStream("D5.txt")).nextLine();
		Prog p = new Prog(s);
		p.inputs.add(1L);
		p.run();
		for(int i=0;i<p.outputs.size();i++) {
			if(i+1<p.outputs.size())assert p.outputs.get(i)==0;
			else assert p.outputs.get(i)==7286649;
		}
	}
	
	static void test_d5_p2() throws FileNotFoundException{
		String s = new Scanner(new FileInputStream("D5.txt")).nextLine();
		Prog p = new Prog(s);
		p.inputs.add(5L);
		p.run();
		assert p.outputs.get(0)==15724522;
	}
	
	static void test_d9_ex1() {
		String s ="109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99";
		Prog p = new Prog(s);
		p.run();
		String r = p.outputs.toString().replace(" ","");
		r = r.substring(1, r.length()-1);
		assert r.equals(s);
	}
	
	static void test_d9_ex2() {
		String s ="1102,34915192,34915192,7,4,7,99,0";
		Prog p = new Prog(s);
		p.run();
		assert p.outputs.get(0) == 1219070632396864L;
	}
	
	static void test_d9_ex3() {
		String s ="104,1125899906842624,99";
		Prog p = new Prog(s);
		p.run();
		assert p.outputs.get(0) == 1125899906842624L;
	}
	
	static void test_d9_p1() throws FileNotFoundException{
		String s = new Scanner(new FileInputStream("D9.txt")).nextLine();
		Prog p = new Prog(s);
		p.inputs.add(1L);
		p.run();
		assert p.outputs.get(0) == 2738720997L;
	}
	
	static void test_d9_p2() throws FileNotFoundException{
		String s = new Scanner(new FileInputStream("D9.txt")).nextLine();
		Prog p = new Prog(s);
		p.inputs.add(2L);
		p.run();
		assert p.outputs.get(0) == 50894;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		test_d2_ex();
		test_d2();
		test_d5_p1();
		test_d5_p2() ;
		test_d9_ex1();
		test_d9_ex2();
		test_d9_ex3();
		test_d9_p1();
		test_d9_p2();
	}
}
