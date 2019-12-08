import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;


public class D3 {

	Scanner scan = new Scanner(System.in);
	class P {
		int x,y;
		P(int a,int b){x=a;y=b;}
		@Override
		public boolean equals(Object obj) {
			P b = (P)obj;
			return x==b.x && y==b.y;
		}
		@Override
		public int hashCode() {
			return (x+" "+y).hashCode();
		}
	}
	
	HashMap<P, Integer> run(String[]s){
		int steps=0;
		int x =0;int y =0;
		HashMap<P,Integer>H = new HashMap<P,Integer>();
		
		for(String w:s) {
			int dx=0,dy=0;
			char c = w.charAt(0);
			int r = Integer.parseInt(w.substring(1));
			if(c=='D') dy=-1;
			if(c=='U') dy=1;
			if(c=='L') dx=-1;
			if(c=='R') dx=1;
			for(int i=0;i<r;i++) {
				x+=dx;y+=dy;
				steps++;
				P k = new P(x,y);
				if(!H.containsKey(k))H.put(k,steps);
				
			}
		}
		
		return H;
	}
	
	void solve() {
		String[]s1 = scan.nextLine().split(",");
		String[]s2 = scan.nextLine().split(",");
		HashMap<P,Integer> h1 = run(s1);
		HashMap<P,Integer> h2 = run(s2);
		int best = Integer.MAX_VALUE;
		for(P x:h1.keySet()) {
			if(h2.containsKey(x)) {
				best = min(best, h1.get(x)+h2.get(x));
			}
		}
		System.out.println(best);
	}
	
	public static void main(String[] args) {

		D3 me = new D3();
		try{
			
			String sample =me.getClass().getName()+".txt";
			me.scan = new Scanner(new FileInputStream(sample));
			//me.scan = new Scanner(System.in);
		}catch (Exception e) {
			System.err.println(e);
		}
		me.solve();
	}
}
