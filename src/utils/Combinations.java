package utils;
import java.util.Arrays;

public class Combinations {
	static long[][] npk(int n, long mod){
		long[][]R = new long[n+1][];
		R[0]=new long[] {1};
		
		for(int i=1;i<=n;i++) {
			R[i]=new long[i+1];
			R[i][0]=R[i][i]=1L;
			for(int j=1;j<i;j++) {
				R[i][j]=(R[i-1][j-1]+R[i-1][j])%mod;
			}
		}
		return R;
	}
	public static class CombI {
		int[] St;
		int n; int k;
		public CombI( int n, int k){
			St = new int[k];
			this.k = k;
			this.n=n;
			for( int i = 0; i < k; i++){
				St[i] = i;
			}
		}
		public int[] next(){
			int[]t = (int[])St.clone();
			int i=k-1;
			while( i >= 0 && St[i] == n -(k-i) ) i--;
			if( i >= 0 ){
				St[i]++;
				for( int j = i+1; j < k; j++) St[j] = St[i] + ( j-i);
			}
			else{
				St = null;
			}
			return t;
		}
		public boolean hasNext(){return St!=null;}
	}

	
	public static void main(String[] args) {
		long[][]R = npk(600,10000000000L);
		System.out.println(R[500][3]);
		
		int n = 10,k=4;
		CombI cmb = new CombI(n, k);
		int count=0;
		while(cmb.hasNext()) {
			count++;
			System.out.println(Arrays.toString(cmb.next()));
		};
		assert count == R[n][k];
	}
}
