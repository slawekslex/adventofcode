import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;


public class D6 {

	Scanner scan = new Scanner(System.in);
	
	HashMap<String, ArrayList<String>>H = new HashMap<>();
	HashSet<String>inOrb = new HashSet<>();
	
	int count(String x, int d) {
		int res = d;
		if(H.containsKey(x))
		for(String q:H.get(x)) {
			res += count(q,d+1);
		}
		return res;
	}
	
	
	int in(String p, String c) {
		if(p.equals(c))return 0;
		if(H.containsKey(p))
			for(String q:H.get(p)) {
				int d = in(q,c);
				if(d!=-1)return d+1;
			}
		return -1;
	}
	
	int dist(String p, String a, String b) {
		if(H.containsKey(p))
			for(String q:H.get(p)) {
				if(in(q,a)!=-1 &&in(q,b)!=-1)return dist(q,a,b);
			}
		
		int res = in(p,a)+in(p,b)-2;
		return res;
	}
	
	void solve() {
		while(scan.hasNext()) {
			String s = scan.next();
			if(s.length()==1)break;
			int p = s.indexOf(')');
			String a = s.substring(0,p);
			String b = s.substring(p+1);
			if(!H.containsKey(a)) {
				H.put(a, new ArrayList<>());
			}
			H.get(a).add(b);
			inOrb.add(b);
		}
		int res=0;
		for(String x:H.keySet()) {
			if(!inOrb.contains(x))System.out.println(dist(x, "YOU", "SAN"));
		}
		System.out.println(res);
	}
	
	public static void main(String[] args) {
		D6 me = new D6();
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
