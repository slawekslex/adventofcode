package utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;

public class Primes {
	
	ArrayList<Integer> sieve(int maxVal) {
		BitSet marked = new BitSet(maxVal+1);
		ArrayList<Integer>res = new ArrayList<>();
		for(int p=2;p*p<=maxVal;p++) {
			if(!marked.get(p)) {
				for(int j=p;j*p<=maxVal;j++) {
					marked.set(j*p);
				}
			}
		}
		for(int p=2;p<=maxVal;p++) {
			if(!marked.get(p))res.add(p);
		}
		return res;
	}
	
	
	/** 
	 * res[x] = 1 iff x prime
	 * res[x] = p - smallest prime factor of x
	 */
	int[]sieveMap(int maxVal){
		int[] res = new int[maxVal+1];
		Arrays.fill(res,1);
		for(int p=2;p*p<=maxVal;p++) {
			if(res[p]==1) {
				for(int j=p;j*p<=maxVal;j++) {
					if(res[j*p]==1)res[j*p]=p;
				}
			}
		}
		return res;
	}
	
	// returns {{p1,k1},{p2,k2}...} where x = p1^k1 * p2^k2...
	// O(log(N)) time O(N) memory
	ArrayList<int[]>factor(int x, int[]map){
		ArrayList<int[]>res = new ArrayList<>();
		while(x>1) {
			int p = map[x];
			if(p==1) {
				res.add(new int[]{x,1});
				break;
			}
			int cnt=0;
			while(x%p==0) {
				cnt++;
				x/=p;
			}
			res.add(new int[]{p,cnt});
		}
		return res;
	}
	
	// returns {{p1,k1},{p2,k2}...} where x = p1^k1 * p2^k2...
	// O(sqrt(N)) time O(sqrt(N)) memory
	ArrayList<int[]>factor(int x, ArrayList<Integer>Primes){
		ArrayList<int[]>res = new ArrayList<>();
		for(int p:Primes) {
			if(p*p>x)break;
			int cnt=0;
			while(x%p==0) {
				x/=p;
				cnt++;
			}
			if(cnt>0)res.add(new int[] {p,cnt});
		}
		if(x>1)res.add(new int[] {x,1});
		return res;
	}
	
	
	void populate(int x, int p, ArrayList<int[]>factors, ArrayList<Integer>res) {
		if(p==factors.size()) {
			res.add(x);
		}else {
			int y =1;
			for(int i=0;i<=factors.get(p)[1];i++) {
				populate(x*y,p+1,factors,res);
				y*=factors.get(p)[0];
			}
		}
	}
	ArrayList<Integer> divisors(ArrayList<int[]>factors){
		ArrayList<Integer>res = new ArrayList<>();
		populate(1,0,factors,res);
		return res;
	}
	
	
	public static void main(String[] args) {
		System.out.println(new Primes().sieve(23));
		ArrayList<Integer> P = new Primes().sieve(1000000);
		int[]M = new Primes().sieveMap(1000000);
		ArrayList<int[]> f48 = new Primes().factor(240, M);
		System.out.println(new Primes().divisors(f48));
		Random R = new Random();
		for(int i=0;i<10;i++) {
			int x = R.nextInt(1000000);
			//ArrayList<int[]>f = new Primes().factor(x, P);
			ArrayList<int[]>f = new Primes().factor(x, M);
			System.out.print(x+" = ");
			int y =1;
			for(int j=0;j<f.size();j++) {
				System.out.format("%d^%d * ", f.get(j)[0],f.get(j)[1]);
				for(int k=0;k<f.get(j)[1];k++)y*=f.get(j)[0];
			}
			if(x!=y) throw new RuntimeException();
			System.out.println();
		}
		
	}
}
