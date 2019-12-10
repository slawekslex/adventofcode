import java.util.*;

import utils.Geom;

import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class D10 {

	Scanner scan = new Scanner(System.in);
	String readAll() {
		String res ="";
		while(scan.hasNext()) {
			String s = scan.nextLine();
			res+=s;
		}
		return res;
	}
	
	
	static ArrayList<int[]>vis(ArrayList<int[]>A, int[]a){
		ArrayList<int[]>res = new ArrayList<>();
		for(int[]b:A) {
			if(a==b)continue;
			boolean vis = true;
			for(int[]c:A) {
				if(c==b || c==a)continue;
				int[]ab = Geom.vect(a,b);
				int[]ac = Geom.vect(a, c);
				int crs = Geom.cross(ac, ab);
				if(Geom.len(ac) < Geom.len(ab) && crs==0 &&signum(ab[0])==signum(ac[0]) && signum(ab[1])==signum(ac[1])) {
			//		System.out.println(b[0]+", "+b[1]+" is blocked by " +c[0]+","+c[1]);
					
					vis=false;
				}
			}
			if(vis) {
				res.add(b);
			}
		}
		
		return res;
	}
	
	static double angle(int[]st, int[]a, int[]b) {
		int[]v1 = Geom.vect(st, b);
		int[]v2 = Geom.vect(st, a);
		double foo = Geom.dot(v1, v2)/Geom.len(v1)/Geom.len(v2);
		double res =Math.acos(foo)*180/Math.PI;
		if(Geom.cross(v1, v2)<0)res = 360-res;
		return res; 
	}
	
	static void solve(char[][]M) {
		int res=0;
		ArrayList<int[]>A = new ArrayList<>();;
		for ( int i = 0; i < M.length; i++) for (int j = 0; j < M[0].length; j++) {
			if(M[i][j]=='#')A.add(new int[] {i,j});
		}
		int[]st=null;
		for(int[]a:A) {
			int cnt =0;
			cnt = vis(A,a).size();
			if(cnt>res) {
				res =  cnt;
				st = a;
			}
		}
		ArrayList<int[]> V =vis(A,st); 
		System.out.println(res);
		final int[]o = st.clone();
		Collections.sort(V, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				double d1 = angle(o, new int[] {-1000,0}, o1);
				double d2 = angle(o, new int[] {-1000,0}, o2);
				if(d1<d2)return -1;
				if(d1>d2)return 1;
				return 0;
			}
		});
		int[] toPrint = {1,2,3,10,20,50,100,199,200,201};
		for(int i:toPrint) {
			System.out.println(V.get(i-1)[1]+" "+V.get(i-1)[0]);
		}
	}
	void solve() {
		char[][]M =chars(scan);
		solve(M);
	}
	
	public static void main(String[] args) {
		D10 me = new D10();
		
		try{
			String input =me.getClass().getName()+".txt";
			String sample = "D10_example.txt";
			String sample_small = "D10_example_small.txt";
			me.scan = new Scanner(System.in);
			if(new File(input).isFile()) {
				me.scan = new Scanner(new FileInputStream(input));
				//System.err.println(new Scanner(new File(sample)).useDelimiter("\\Z").next());
			} else {
				System.err.println("INPUT FILE "+input+" DOES NOT EXIST");
			}					
		}catch (Exception e) {
			System.err.println(e);
		}
		me.solve();
	}
}
