package utils;

class Permut {
	Object[] C;
	int[] St;
	public Permut( Object[] obj){
		C = obj;
		St = new int[C.length];
		for( int i = 0; i < St.length; i++ ) St[i] = i;
	}
	public Object[] next(){
		Object[] T = new Object[ C.length ];
		for (int i = 0; i < T.length; i++)T[i] = C[St[i]];		
		int j = St.length - 1;
		while( j > 0 && St[j] < St[j-1] )j--;
		if( j > 0 ){
			int k = j - 1;
			int x = St[k];
			j = St.length - 1;
			while( St[j] < x) j--;
			St[k] = St[j];
			St[j] = x;
			for( j = 0; j < (St.length-k)/2;j++ ){
				x = St[k+1+j];
				St[k+1+j] = St[St.length-1 -j];
				St[St.length-j-1] = x;
			}
		}
		else St = null;
		return T;	
	}
	public static final long siln( long n ){
		int x = 1;
		while( n > 1){ x *= n; n--;}
		return x;
	}
	public boolean hasNext(){
		return St != null;
	}
}

class PerInt {
	int[] St;
	public PerInt( int n){
		St = new int[n];
		for( int i = 0; i < St.length; i++ ) St[i] = i;
	}
	public int[] next(){		
		int j = St.length - 1;
		while( j > 0 && St[j] < St[j-1] )j--;
		if( j > 0 ){
			int k = j - 1;
			int x = St[k];
			j = St.length - 1;
			while( St[j] < x) j--;
			St[k] = St[j];
			St[j] = x;
			for( j = 0; j < (St.length-k)/2;j++ ){
				x = St[k+1+j];
				St[k+1+j] = St[St.length-1 -j];
				St[St.length-j-1] = x;
			}
		}
		else St = null;
		return St;	
	}
	public boolean hasNext(){
		return St != null;
	}
}

class Test{
	public static void main(String[] args) {
		int r=0;
		System.out.println(Permut.siln(10));
		Integer[] a = new Integer[11];
		for (int i = 0; i < a.length; i++) {
			a[i]= new Integer(i);
		}
		Permut p = new Permut(a);
		PerInt pi = new PerInt(a.length);
		long t1 = System.currentTimeMillis();
		while(p.hasNext()){
			Object[] b = p.next();
		}
		System.out.println(System.currentTimeMillis()-t1);
		t1 = System.currentTimeMillis();
		while(pi.hasNext()){
			int[] b = pi.next();
		}
		System.out.println(System.currentTimeMillis()-t1);
	}
}