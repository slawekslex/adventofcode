package utils;

public class Matrix {
    long[][] clone(long[][]A){
        long[][] r = new long[A.length][];
        for(int i=0;i<A.length;i++)r[i]=A[i].clone();
        return r;
    }
    
    long[][] sum(long[][] a, long[][]b ){
		long[][]c=new long[a.length][a[0].length];
		for( int i=0;i<a.length;i++)for(int j=0;j<a[0].length;j++){
			c[i][j]=a[i][j]+b[i][j];
		}
		return c;
	}
	long[][] mult(long[][]a, long[][]b){
		long[][]c = new long[a.length][b[0].length];
		for(int i=0;i<a.length;i++)for(int j=0;j<b[0].length;j++){
			for(int k=0;k<a[0].length;k++){
				c[i][j]+=a[i][k]*b[k][j];
			}
		}
		return c;
	}
    
	long[][] pow(long[][]A, long k){
		if( k== 1)return clone(A);
		long[][] b = pow(A, k/2);
        b = mult(b,b);
		if( k%2==1) b = mult(b,A);
		return b;
	}
}

	
