import java.util.*;

import utils.Prog;

import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class D19 {

	Scanner scan = new Scanner(System.in);

	
	void solve() {
		String s = readAll(scan);
		int res=0;
		char[][]C = new char[1500][1500];
		int[]F = new int[1500];
		int[]L = new int[1500];


		for(int i=0;i<C.length;i++) {
			 F[i]=-1; L[i] =0;
			for (int j = 0; j < C[0].length; j++) {
		
				Prog p = new Prog(s);
				p.exitOnOutput=true;
				p.inputs.add(j+0L);p.inputs.add(i+0L);
				long o= p.run();
				if(o==1) {
					L[i] = j;
					if(F[i]==-1)F[i]=j;
				}else if(F[i]>0)break;
				
			}
			System.out.println(i+": "+F[i]+" "+L[i]);
		}
		for(int i=10;i+100<F.length;i++) {
			int ii=i+99;
			int e = F[ii]+99;
			if(e<=L[i]) {
				System.out.println("Found at" + i +" "+F[ii]);
			}
		}

	}
	
	public static void main(String[] args) {
		D19 me = new D19();
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
		long time = System.currentTimeMillis();
		me.solve();
		time = System.currentTimeMillis() - time;
		System.err.format("time: %.2fs\n",time/1000.0);
	}
}
