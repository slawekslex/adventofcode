import java.util.*;

import org.xml.sax.HandlerBase;

import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class D14 {

	Scanner scan = new Scanner(System.in);

	String[][] G;
	HashMap<String,Integer>H = new HashMap<>();
	int ore=0;
	int[][]gm;
	int[]gc;
	int orid;
	
	int get(int x, int h, int[] left) {
	
		if (h == orid)
			return x;
		int u = min(x, left[h]);
		x -= u;
		left[h] = left[h] - u;

		int r = 0;
		int g = gc[h];
		int num = (x + g - 1) / g;
		int le = num * g - x;
		left[h] += le;

		for (int j = 0; j < gm[h].length; j += 2) {

				r += get(gm[h][j]*num, gm[h][j + 1], left);

		}
		

		return r;
	}
	
	void solve() {
		HashMap<String,Integer>ID = new HashMap();
		 G = new String[58][];//[58][];
		int p=0;
		int n=0;
		while(scan.hasNext()) {
			String s = scan.nextLine();
			s = s.replaceAll("[,=>]", "");
			G[p++]=s.split(" +");
		}
		for(int i=0;i<G.length;i++)for(int j=0;j<G[i].length;j+=2) {
			if(!ID.containsKey(G[i][j+1]))ID.put(G[i][j+1], n++);
		}
		gm = new int[n][];
		gc = new int[n];
		for(int i=0;i<G.length;i++) {
			int[]c = new int[G[i].length-2];
			String h =G[i][G[i].length-1];
			int g = Integer.parseInt(G[i][G[i].length-2]);
			gc[ID.get(h)]=g;
			for(int j=0;j+2<G[i].length;j+=2) {
				c[j]=Integer.parseInt(G[i][j]);
				c[j+1]=ID.get(G[i][j+1]);
			}
			gm[ID.get(h)]=c;
		}
		orid=ID.get("ORE");
		int[]left =new int[n];
		long ore = 1000000000000L;
		int f=0;
		int s=0;
		while(true) {
			s++;
			int o = get(1, ID.get("FUEL"), left);
			
			if(o>ore)break;
			ore-=o;
			f++;
			if(s%10000 == 0)System.out.println("--"+ore);
		}
		//System.out.println(get(1, ID.get("FUEL"), left));
		System.out.println(f);
	}
	
	public static void main(String[] args) {
		long o = 1000000000000L;
		//System.out.println(o/2210736  );
		D14 me = new D14();
		try{
			//String sample="D14_samp.txt";
			String sample =me.getClass().getName()+".txt";
			me.scan = new Scanner(System.in);
			if(new File(sample).isFile()) {
				me.scan = new Scanner(new FileInputStream(sample));
			//	System.err.println(new Scanner(new File(sample)).useDelimiter("\\Z").next());
			} else {
				System.err.println("INPUT FILE "+sample+" DOES NOT EXIST");
			}					
		}catch (Exception e) {
			System.err.println(e);
		}
		me.solve();
	}
}
