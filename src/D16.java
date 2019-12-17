import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class D16 {
	
	Scanner scan = new Scanner(System.in);

	int sum(int a, int b, int[]A) {
		int res=0;
		for(int i=a;i<b;i++)res+=A[i];
		return res;
	}

	void solve() {
		int[] b = new int[] { 0, 1, 0, -1 };
		int[] a = ints(scan.nextLine(), "");
		int[]A = new int[10000*a.length];
		for(int i=0;i<10000;i++)for(int j=0;j<a.length;j++) {
			A[i*a.length+j]=a[j];
		}
		a=A;
		// int[]a = ints("12345678","");
		for (int ph = 0; ph < 100; ph++) {
			int[]sa = new int[a.length];
			for(int i=0;i<a.length;i++) {
				if(i>0)sa[i]+=sa[i-1];
				sa[i]+=a[i];
			}
			int[] aa = new int[a.length];

			for (int i = 0; i < a.length; i++) {
				int p = 0;

				while (p < a.length) {
					int sk = i + 1;
					if (p == 0)
						sk = i;
					p += sk;
					if(p>=a.length)break;
					int pe = min(a.length, p + i + 1);
					aa[i] += sa[pe-1];//sum(p, pe, a);
				    if(p>0)aa[i]-=sa[p-1];
					p += i + 1;
					p += i + 1;
					if(p>=a.length)break;
					pe = min(a.length, p + i + 1);
					aa[i] -= sa[pe-1]-sa[p-1];;//sum(p, pe, a);
					p += i + 1;


				}
				if (aa[i] < 0)
					aa[i] = -aa[i];
				aa[i] %= 10;
			}
			a = aa;
			System.out.println("Phase "+ph);
		}
		String r = "";
		for (int i = 5974057; i < 5974057+8; i++)
			r += a[i];
		System.out.println(r);
	}
//50053207
	public static void main(String[] args) {
		D16 me = new D16();
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
		me.solve();
	}
}
