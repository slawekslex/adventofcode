package y2018;
import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class C4 {

	Scanner scan = new Scanner(System.in);
	String readAll() {
		String res ="";
		while(scan.hasNext()) {
			String s = scan.nextLine();
			res+=s;
		}
		return res;
	}
	
	void solve() {
		HashMap<String, Integer>H = new HashMap<>();
		HashMap<String,int[]>M = new HashMap<>();
		String id = null;
		int st=0;
		ArrayList<String>A = new ArrayList<>();
		while(scan.hasNext()) {
			String d = scan.nextLine();
			A.add(d);
		}
		Collections.sort(A);
		for(String d:A) {
			System.err.println(d);
			String a = d.substring(19);
			int t = Integer.parseInt(d.substring(15,17));
			int th = Integer.parseInt(d.substring(12,14));
			if(th==0)t+=60;
			if(a.startsWith("G")) {
				id = a.split(" ")[1];
	
			}
			if(a.startsWith("fa")) {
				st =t;
				
			}
			if(a.startsWith("wa")) {
				int tt = t-st;
				if(!H.containsKey(id))H.put(id,0);
				H.put(id, H.get(id)+tt);
			}
			
		}
		int mx =0;
		String mid=null;
	
		for(String d:H.keySet()) {
			System.err.println(d+"-"+H.get(d));
			if(H.get(d)>mx) {
				mx = H.get(d);
				mid=d;
			}
		}
		
		for(String d:A) {
			
			String a = d.substring(19);
			int t = Integer.parseInt(d.substring(15,17));
			int th = Integer.parseInt(d.substring(12,14));
			if(th==0)t+=60;
			if(a.startsWith("G")) {
				id = a.split(" ")[1];
			}
			if(a.startsWith("fa")) {
				st = t;
				
				
			}
			if(a.startsWith("wa")) {
				if(!M.containsKey(id))M.put(id,new int[300]);
				int[]as = M.get(id);
				
				for(int i=st;i<t;i++)as[i]++;
				
			}
			
		}
		int best = 0;
		for(String d:M.keySet()) {
			int[]as = M.get(d);
			for(int i=0;i<as.length;i++) {
				if(as[i]>best) {
					best = as[i];
					System.out.println(best+":  "+d+" "+(i-60));
				}
			}
		}
		System.out.println(mid+" "+mx);
	}
	
	public static void main(String[] args) {
		C4 me = new C4();
		try{
			String sample =me.getClass().getSimpleName()+".txt";
			me.scan = new Scanner(System.in);
			if(new File(sample).isFile()) {
				me.scan = new Scanner(new FileInputStream(sample));
				//System.err.println(new Scanner(new File(sample)).useDelimiter("\\Z").next());
			} else {
				System.err.println("INPUT FILE "+sample+" DOES NOT EXIST");
			}					
		}catch (Exception e) {
			System.err.println(e);
		}
		me.solve();
	}
}
