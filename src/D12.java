import java.util.*;

import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Array;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class D12 {

	Scanner scan = new Scanner(System.in);
	long gcd(long a, long b) {
		if(b==0)return a;
		return gcd(b, a%b);
	}
	HashSet <String>St1= new HashSet<>();
	
	long nww(long[]x) {
		long res =1;
		for(long y:x)res = res *y /gcd(res,y);
		return res;
	}
	
	void sim(long[]x, long[]vx) {
		long maxP=0;
		long maxV=0;
		long[] xi = (long[])x.clone();long[] vi = (long[])vx.clone();
		for(long s =0;;s++) {
			if(s%100000000 == 99999999)System.out.println(s+1);
			for(int i=0;i<4;i++)for(int j=i+1;j<4;j++) {
				if(x[i]<x[j]) {
					vx[i]++;vx[j]--;
				}
				if(x[i]>x[j]) {
					vx[i]--;vx[j]++;
				}
				
			}
			for (int i = 0; i < 4; i++) {
				x[i]+=vx[i];
				maxP = max(maxP, abs(x[i]));
				maxV = max(maxV, abs(vx[i]));
			}
			if(Arrays.equals(x, xi) && Arrays.equals(vx, vi)) {
				System.out.println("ended after "+(s+1));
				return;
			}
		}
		
	}
	
	void solve() {
		System.out.println(nww(new long[] {56344,231614, 193052}));
		long[]x  =new long[4];
		long[]y  =new long[4];
		long[]z  =new long[4];
		long[]vx  =new long[4];
		long[]vy  =new long[4];
		long[]vz  =new long[4];
		int p=0;
		String all = readAll(scan,"#");
		for(String s:all.split("#")){
			s= s.replaceAll("[<xyz\\=,>]", "");
			String[]ss = s.split(" ");
			x[p] = Integer.parseInt(ss[0]);
			y[p] = Integer.parseInt(ss[1]);
			z[p] = Integer.parseInt(ss[2]);
			p++;
		}
		long res =0;
		sim(x, vx);
		sim(y, vy);
		sim(z, vz);
		//100587
		for (int i = 0; i < 4; i++) {
			long pe =0;
			long ke =0;
			pe+=abs(x[i])+abs(y[i])+abs(z[i]);
			ke+=abs(vx[i])+abs(vy[i])+abs(vz[i]);
			res+=pe*ke;
		}
		System.out.println(res);
	}
	
	public static void main(String[] args) {
		D12 me = new D12();
		try{
			String sample =me.getClass().getName()+".txt";
			String examp = "D12_samp.txt";
			me.scan = new Scanner(System.in);
			if(new File(sample).isFile()) {
				me.scan = new Scanner(new FileInputStream(sample));
				System.err.println(new Scanner(new File(examp)).useDelimiter("\\Z").next());
			} else {
				System.err.println("INPUT FILE "+sample+" DOES NOT EXIST");
			}					
		}catch (Exception e) {
			System.err.println(e);
		}
		me.solve();
	}
}
