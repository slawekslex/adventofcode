import java.util.*;
import java.util.stream.IntStream;

import utils.Geom;

import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class D10 {

	static class AngleComp implements Comparator<int[]>{
		int[]o;
		
		public AngleComp(int[]origin) {
			o=origin;
		}
		@Override
		public int compare(int[] o1, int[] o2) {
			double d1 = angle(o, new int[] {-1000,0}, o1);
			double d2 = angle(o, new int[] {-1000,0}, o2);
			if(d1<d2)return -1;
			if(d1>d2)return 1;
			return 0;
		}
		
	}
	Scanner scan = new Scanner(System.in);
	String readAll() {
		String res ="";
		while(scan.hasNext()) {
			String s = scan.nextLine();
			res+=s;
		}
		return res;
	}
	
	
	static ArrayList<Integer>blockedBy(ArrayList<int[]>A, int[]a, int[]c){
		ArrayList<Integer>res = new ArrayList<>();
		for(int i=0;i<A.size();i++) {
			int[]b = A.get(i);
			if(a==b ||b==c)continue;
			int[]ab = Geom.vect(a,b);
			int[]ac = Geom.vect(a, c);
			int crs = Geom.cross(ac, ab);
			if(Geom.len(ac) < Geom.len(ab) && crs==0 &&signum(ab[0])==signum(ac[0]) && signum(ab[1])==signum(ac[1])) {
				res.add(i);
			}
		}
		return res;
	}
	
	static int[] blocked(ArrayList<int[]>A, int[]a){
		int[]blocked = new int[A.size()];
		for(int[]c:A) {
			if(c==a)continue;
			ArrayList<Integer> B = blockedBy(A,a,c);
			for(int x:B)blocked[x]++;
		}
		return blocked;
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
		System.out.println("ALL: "+A.size());
		int[]st=null;
		for(int[]a:A) {
			
			int[]blo = blocked(A,a);
			int cnt = (int)Arrays.stream(blo).filter(x->x==0).count() -1;
			//M[a[0]][a[1]]=(char)('0'+cnt);
			if(cnt>res) {
				res =  cnt;
				st = a;
			}
		}
		//for(char[]c:M)System.out.println(new String(c));
		
		System.out.println(res);
		
		// PART 2
		int p=0;
		while(A.size()>1) {
			int[] blo = blocked(A,st);
			ArrayList<int[]> V =new ArrayList<>();
			for(int i=0;i<blo.length;i++)if(A.get(i)!=st && blo[i]==0)V.add(A.get(i));
			TreeSet<int[]>T = new TreeSet<>(new AngleComp(st));
			T.addAll(V);
			while(!T.isEmpty()) {
				int[] x = T.pollFirst();
				p++;
				System.out.println(p+": "+x[1]+" "+x[0]);
			}
			for(int[]x:V)A.remove(x);
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
			String sample_tiny = "D10_example_tiny.txt";
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
