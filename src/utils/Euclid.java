package utils;
import java.util.Random;

public class Euclid {
	
	int gcd(int a, int b) {
		if(b==0)return a;
		return gcd(b, a%b);
	}
	
	 //  return array [g, a, b] such that g = gcd(x, y), ax + by = g
	int[] extended(int x, int y) {
	   if (y == 0) {
	         return new int[] { x, 1, 0 };
	   }
	   int[] vals = extended(y, x % y);
	   int d = vals[0];
	   int a = vals[2];
	   int b = vals[1] - (x / y) * vals[2];
	   return new int[] { d, a, b };
	}
   
	
	int invert(int x, int mod) {
		int[]r = extended(x,mod);
		if(r[0]!=1)throw new RuntimeException("Not coprime: "+r[0]);
		int res = r[1];
		if(res<0)res+=mod;
		return res;
	}
	
	
	//-------------- Long 
	
	long gcd(long a, long b) {
		if(b==0)return a;
		return gcd(b, a%b);
	}
	
	//  return array [g, a, b] such that g = gcd(x, y), ax + by = g
    long[] extended(long x, long y) {
		   if (y == 0) {
		         return new long[] { x, 1, 0 };
		   }
		   long[] vals = extended(y, x % y);
		   long d = vals[0];
		   long a = vals[2];
		   long b = vals[1] - (x / y) * vals[2];
		   return new long[] { d, a, b };
		}
	   
		
 	long invert(long x, long mod) {
 		long[]r = extended(x,mod);
			if(r[0]!=1)throw new RuntimeException("Not coprime: "+r[0]);
			long res = r[1];
			if(res<0)res+=mod;
			return res;
	}
	
 	// ------------- Test
 	
	public static void main(String[] args) {
		
		int x = 107; int y = 52;
		Euclid E = new Euclid();
		//System.out.println(E.invert(1L, 912750790581630L));
		int[] r = E.extended(x, y);
		System.out.format("%d*%d + %d*%d = %d\n", r[1],x, r[2],y,r[0] );
		System.out.println(r[1]*x + r[2]*y);
		
		int inv = E.invert(y, x);
		System.out.format("%d * %d = %d mod %d", y, inv, (y*inv)%x, x);
		
		//test big
		int mod = 1000000007;
		Random R = new Random();
		for(int i=0;i<10000000;i++) {
			x = R.nextInt(mod);
			inv = E.invert(x, mod);
			if((1L*x*inv)%mod !=1)System.err.println("Error "+x);
		}
    }
}
