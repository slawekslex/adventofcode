package utils;
import static java.util.Arrays.*;
import static java.lang.Math.*;

import java.util.Random;
/**
 * Queries for min and updates in rectangular matrix in log(n)*log(m)
 */
public class Interval2D {
	int n,m;
	int[][]M;
	public Interval2D(int[][]x){
		n=x.length;
		m=x[0].length;
		M = new int[2*n][2*m];
		for(int[]y:M)fill(y, Integer.MAX_VALUE);
		for(int i=0;i<n;i++)for(int j=0;j<m;j++)insert(i,j,x[i][j]);
	}
	
	public void insert(int x, int y, int val){
		M[x+n][y+m]=val;
		for(int a = x+n;a>=0;a/=2)for(int b = x+n;b>=0;b/=2){
			if(b<m){
				M[a][b]=min(M[a][b],M[a][b*2]);
				M[a][b]=min(M[a][b],M[a][b*2+1]);
			}
			if(a<n){
				M[a][b]=min(M[a][b],M[a*2][b]);
				M[a][b]=min(M[a][b],M[a*2+1][b]);
			}
		}
	}
	
	int get(int x, int y){
		return M[x+n][y+m];
	}
	
	/** query in rectangle (x1,y1)-(x2,y2), inclusive*/
	int getMin(int x1, int x2, int y1, int y2){
		int a = x1+n;
		int b = x2+n;
		int res =Integer.MAX_VALUE;
		while(a<=b){
			res = min(res, getMinY(a,y1,y2));
			res = min(res, getMinY(b,y1,y2));
			a = (a+1)/2;
			b = (b-1)/2;
		}
		
		return res;
	}
	
	int getMinY(int x, int y1, int y2){
		int a = y1+m;
		int b = y2+m;
		int res = Integer.MAX_VALUE;
		while(a<=b){
			res = min(res,M[x][a]);
			res = min(res,M[x][b]);
			a = (a+1)/2;
			b = (b-1)/2;
		}
		return res;
	}
	
	static int brut(int[][]M, int x1, int y1, int x2, int y2){
		int res = Integer.MAX_VALUE;
		for(int i=x1;i<=x2;i++)for(int j=y1;j<=y2;j++)res = min(res, M[i][j]);
		return res;
	}
	
	static void printM(int[][]M){
		for(int i=0;i<M.length;i++){
			for(int j=0;j<M[0].length;j++)System.out.format("%3d", M[i][j]);
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
	/*	int[][]M = {{5},{6},{4},{8}};
		Random R = new Random(5);
		//for(int i=0;i<M.length;i++)for(int j=0;j<M[0].length;j++)M[i][j]=R.nextInt(10);
		printM(M);
		Interval2D tree = new Interval2D(M);
		System.out.println(tree.getMin(0, 3, 0, 0));		
		*/
		int n = 149;int m =102;
		int[][]M = new int[n][m];
		Interval2D tree = new Interval2D(M);
		Random R = new Random();
		for(int t =0;t<10000000;t++){
			if(R.nextBoolean()){
				int v = R.nextInt(10000);
				int x =  R.nextInt(n);
				int y = R.nextInt(m);
				M[x][y]=v;
				tree.insert(x, y, v);
			}else{
				int x1 = R.nextInt(n);
				int x2 =  R.nextInt(n);
				int y1 = R.nextInt(m);
				int y2 = R.nextInt(m);
				if(x1>x2){int w=x1;x1=x2;x2=w;}
				if(y1>y2){int w=y1;y1=y2;y2=w;}
				int m1 = brut(M,x1,y1,x2,y2);
				int m2 = tree.getMin(x1, x2, y1, y2);
				if(m1!=m2){
					System.out.println("Error "+ m1 + " "+m2);
				}
			}
		}
	}
}
