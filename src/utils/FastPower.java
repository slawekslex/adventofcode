package utils;
import java.util.Random;

public class FastPower {
	
	long power(long x, long k, long mod) {
		long res = 1;
		while(k >0) {
			if(k%2==1) {
				res = (res * x) %mod;
			}
			x = (x * x)%mod;
			k/=2;
		}
		return res;
	}
	
	long brut(long x, long k, long mod) {
		long res =1;
		for(int i=0;i<k;i++) {
			res = (res*x)%mod;
		}
		return res;
	}
	
	
	public static void main(String[] args) {
		FastPower fp = new FastPower();
		int mod = 1000000007;
		Random R = new Random();
		for(int i=0;i<100000;i++) {
			long x = R.nextInt(mod);
			int k = R.nextInt(100000);
			if(fp.power(x,k,mod)!=fp.brut(x,k,mod)) {
				System.out.println("error "+x+" "+k);
			}
		}
	}
	
}
