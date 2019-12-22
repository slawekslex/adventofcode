import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class D22 {

	Scanner scan = new Scanner(System.in);

	//int[]A;
	long N = 119315717514047L;
	/*
	void deal_into() {
		long n_at_pos=-1;
		int a = 0;int b = A.length-1;
		while(a<b) {
			if(at_pos==b)n_at_pos=a;
			if(at_pos==a)n_at_pos=b;
			int t = A[a];A[a]=A[b];A[b]=t;
			a++;b--;
		}
		at_pos = n_at_pos;
	}
	
    void cut(int n) {
    	long n_at_pos=-1;
   
    	if(n<0) {
    		n=A.length+n;
    	}
    	int[]B = new int[A.length];
    	int st = A.length-n;
    	for(int i=0;i<n;i++) {
    		if(i==at_pos)n_at_pos=st;
    		B[st++]=A[i];
    		
    	}
    	st=0;
    	for(int i=n;i<A.length;i++) {
    		if(i==at_pos)n_at_pos=st;
    		B[st++]=A[i];
    	}
    	A=B;
    	at_pos = n_at_pos;
    }
    void increment(int n) {
    	long n_at_pos=-1;
    	int[]B = new int[A.length];
    	int p=0;
    	for(int i=0;i<A.length;i++) {
    		if(i==at_pos)n_at_pos=p;
    		B[p]=A[i];
    		p = (p+n)%A.length;
    	}
    	A=B;
    	at_pos = n_at_pos;
    }*/
	void deal_into() {
		at_pos = N-at_pos-1;
	}
	
    void cut(long x) {
    	if(x<0) {
    		x=N+x;
    	}
    	long st = N-x;
    	
    	if(at_pos<x)
    		at_pos =st+at_pos; 
    	else {
    		at_pos = at_pos-x;
    	}
    }
    void increment(int n) {
    	at_pos=(at_pos*n)%N;
    }
   
	long at_pos;

	void solve() {
	//	A = A = new int[10007];
	//	for(int i=0;i<A.length;i++)A[i]=i;
		String[]com = readAll(scan,"\n").split("\n");
			/*{
					"cut 6",
					"deal with increment 7",
					"deal into new stack",	
			};*/
		at_pos=2020;
		HashSet<Long>vis = new HashSet<>();
		for (long it = 0;; it++) {
			for (String s : com) {
				if (s.contains("into")) {
					deal_into();

				}
				if (s.contains("with")) {
					String[] p = s.split(" ");
					int x = Integer.parseInt(p[p.length - 1]);
					increment(x);

				}
				if (s.contains("cut")) {
					String[] p = s.split(" ");
					int x = Integer.parseInt(p[p.length - 1]);
					cut(x);
				}

			}
			if (vis.contains(at_pos)) {
				System.out.println("Cycle at " + (it + 1));
				break;
			}
			vis.add(at_pos);
		}
		System.out.println(at_pos);
	}
	
	public static void main(String[] args) {
		D22 me = new D22();
		try{
			String sample =me.getClass().getName()+".txt";
			me.scan = new Scanner(System.in);
			if(new File(sample).isFile()) {
				me.scan = new Scanner(new FileInputStream(sample));
			//	System.err.println(new Scanner(new File(sample)).useDelimiter("\\Z").next());
			} else {
				System.err.println("INPUT FILE "+sample+" DOES NOT EXIST");
			}					
		}catch (Exception e) {
			System.err.println(e);
		}
		long time = System.currentTimeMillis();
		me.solve();
		time = System.currentTimeMillis() - time;
		System.err.format("time: %.2fs\n",time/1000.0);
	}
}
