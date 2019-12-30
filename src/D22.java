import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class D22 {

	Scanner scan = new Scanner(System.in);

	
	long N = 10;
	long K = 1;
	BigInteger BIN;
	
	long[] deal_into() {
		return new long[]{N-1, N-1};//-1 * pos + N-1;
	}
	
	long[] cut(long n) {
    	return new long[] {1, N-n};//pos+N-n;
    }
	long[] increment(long n) {
    	return new long[] {n, 0};//n*pos;
    }
	

	BigInteger[] shuf_coef(String[]com) {
		BigInteger a = BigInteger.ONE;BigInteger b =BigInteger.ZERO;
    	for (String s : com) {
    		long[]f= {0,0};
			if (s.contains("into")) {
				f = deal_into();

			}
			if (s.contains("with")) {
				String[] p = s.split(" ");
				int x = Integer.parseInt(p[p.length - 1]);
				f = increment(x);

			}
			if (s.contains("cut")) {
				String[] p = s.split(" ");
				int x = Integer.parseInt(p[p.length - 1]);
				f = cut(x);
			}
			a = a.multiply(BigInteger.valueOf(f[0])).mod(BIN);//(a*f[0])%N;
			b = b.multiply(BigInteger.valueOf(f[0])).add(BigInteger.valueOf(f[1])).mod(BIN);//(b*f[0]+f[1])%N;
    	}
    	return new BigInteger[] {a,b};
	}
	
	long shuffle(String[]com, long pos) {
		BigInteger[]f = shuf_coef(com);
		BigInteger a = f[0];BigInteger b = f[1];
		BigInteger P = BigInteger.valueOf(pos).multiply(a).add(b).mod(BIN);
    	return P.longValue();
    }
    
    void part1(String[]com, int card) {
    	N = 10007;
    	BIN = BigInteger.valueOf(N);
    	System.out.println(shuffle(com, card));
    }
    
    
    void p2_forward(String[]com, long pos) {
    	N = 119315717514047L;
    	K =101741582076661L;
    	BIN = BigInteger.valueOf(N);
    	BigInteger[]f = shuf_coef(com);
    	f = repeat(f[0], f[1], K);
    	BigInteger a = f[0];BigInteger b = f[1];
    	BigInteger P = BigInteger.valueOf(pos).add(BIN).add(b.multiply(BigInteger.valueOf(-1))).multiply(a.modInverse(BIN)).mod(BIN);
    	System.out.println(P);
    }
    
    BigInteger[] repeat(BigInteger a, BigInteger b, long k) {
    	BigInteger ra = a.modPow(BigInteger.valueOf(k), BIN);
    	BigInteger rb=BigInteger.ZERO;
    	rb = a.modPow(BigInteger.valueOf(k),BIN).add(BigInteger.valueOf(N-1)); // (r^N-1) / (r-1)
    	BigInteger denom = a.add(BigInteger.valueOf(N-1)).modInverse(BIN);
    	rb= rb.multiply(denom);
    	rb = rb.multiply(b).mod(BIN);
    	return new BigInteger[] {ra,rb};
    }
    
	void solve() {
		String[]com = readAll(scan,"\n").split("\n");
		part1(com,2019);
		p2_forward(com,2020);
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
