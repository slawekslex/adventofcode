import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class D18 {

	Scanner scan = new Scanner(System.in);

	HashMap<Character, int[]>H = new HashMap<>();
	char[][]M;
	
	HashMap<String,ArrayList<int[]>>HC = new HashMap<>();
	
	private  ArrayList<int[]> cand(char cur, int keys) {
		String st = cur+" "+keys;
		if(HC.containsKey(st))return HC.get(st);
		ArrayList<int[]>res = new ArrayList<>();
		LinkedList<int[]> Q = new LinkedList<>();
		LinkedList<Integer> S = new LinkedList<>();
		Q.add(H.get(cur));
		
		S.add(0);
		boolean[][]vis = new boolean[81][81];
		if(!H.containsKey(cur)) {
			System.err.println("WTF");
		}
		vis[H.get(cur)[0]][H.get(cur)[1]]=true;
		while(!Q.isEmpty()) {
			int x = Q.getFirst()[0];int y = Q.getFirst()[1];
			Q.removeFirst();
			int s = S.removeFirst();
			
			for(int i=-1;i<2;i++)for(int j=-1;j<2;j++) {
				if(abs(i)+abs(j)==1) {
					int xx = x+i;int yy=y+j;
					if(vis[xx][yy] || M[xx][yy]=='#')continue;
					if(M[xx][yy]>='a' && M[xx][yy]<='z') {
						int k =M[xx][yy]-'a';
						if( (keys &(1<<k)) ==0 ) {
							res.add(new int[] {M[xx][yy], s+1});	
						}
					}
					else if(M[xx][yy]>='A' && M[xx][yy]<='Z') {
						int need =M[xx][yy]-'A';
						if( (keys &(1<<need)) ==0 ) {
							//System.out.println("HIT "+M[xx][yy]);
							continue;
						}
					}
					Q.add(new int[] {xx,yy});
					S.add(s+1);
					vis[xx][yy]=true;
				}
				
			}
		}
		HC.put(st, res);
		return res;
	}

	HashMap<String,Integer >D=new HashMap<>();
	
	static class St implements Comparable<St>{
		int step;
		char cur;
		int keys;
		public St(char c, int k, int s) {
			cur=c;
			keys=k;
			step=s;
		}
		@Override
		public int compareTo(St o) {
			return step-o.step; 
		}
		@Override
		public boolean equals(Object obj) {
			St o = (St)obj;
			return cur==o.cur && keys == o.keys;
		}
		
		int hash() {
			return keys*30 +(cur-'a');
		}
		
		@Override
		public String toString() {
			String ks ="";
			for(char c = 'a';c<='z';c++) {
				if((keys&1<<(c-'a'))!=0)ks+=c;
			}
			return "("+ks+") @ "+cur +":  "+step;
		}
	}
	
	static class St4 implements Comparable<St4>{
		
		St[] R;
		
		public int step() {
			int r =0;
			for(St s:R)r+=s.step;
			return r;
		}
		
		int keys() {
			int r=0;
			for(St s:R)r|=s.keys;
			return r;
		}
		
		long hash() {
			long r = keys();
			for(St s:R) {
				char c = s.cur;
				if(c=='0')c='z'+1;
				if(c=='1')c='z'+1;
				if(c=='2')c='z'+1;
				if(c=='3')c='z'+1;
				r = r*30 + s.cur-'a';
			}
			return r;
		}
		
		@Override
		public int compareTo(St4 o) {
			return step()-o.step(); 
		}
		@Override
		public String toString() {
			String r = "[";
			for(St s:R)r+=s.toString()+" | ";
			return r+"]";
		}
	}
	
	int doit2() {
		
		HashMap<Long, Integer>best=new HashMap<>();
		PriorityQueue<St4> Q=new PriorityQueue<>();
		St4 start = new St4();
		start.R = new St[] {new St('0',0,0),new St('1',0,0),new St('2',0,0),new St('3',0,0)};
		Q.add(start);
		HashSet<Long>vis = new HashSet<>();
		while(!Q.isEmpty()) {
			St4 cur = Q.remove();
			if(cur.keys() == (1<<keyCount)-1)return cur.step();
			vis.add(cur.hash());
			for (int i=0;i<4;i++) {
				St s = cur.R[i];
				ArrayList<int[]> r = cand(s.cur, cur.keys());
				for(int[]rr:r) {
					char p =(char)rr[0];
					int step =rr[1] +s.step;
					int nk = s.keys | (1<<(p-'a'));
					St ns = new St(p, nk,step);
					St4 ns4 = new St4();
					ns4.R=cur.R.clone();
					ns4.R[i]=ns;
					if(vis.contains(ns4.hash()))continue;
					if(best.containsKey(ns4.hash()) && best.get(ns4.hash())<=step)continue;
					best.put(ns4.hash(), step);
					Q.add(ns4);
				}
			}
		}
		
		return -1;
	}
	
int doit(char cur, int keys) {
		
		HashMap<Integer, Integer>best=new HashMap<>();
		PriorityQueue<St> Q=new PriorityQueue<>();
		Q.add(new St(cur, keys,0));
		HashSet<Integer>vis = new HashSet<>();
		while(!Q.isEmpty()) {
			St s = Q.remove();
			if(s.keys == (1<<keyCount)-1)return s.step;
			vis.add(s.hash());
			ArrayList<int[]> r = cand(s.cur, s.keys);
			for(int[]rr:r) {
				char p =(char)rr[0];
				int st =rr[1] +s.step;
				int nk = s.keys | (1<<(p-'a'));
				St ns = new St(p, nk,st);
				if(vis.contains(ns.hash()))continue;
				if(best.containsKey(ns.hash()) && best.get(ns.hash())<=st)continue;
				best.put(ns.hash(), st);
				Q.add(ns);
			}
		}
		
		return -1;
	}
	
	
	int keyCount = 26;
	void solve() {
		
		M = chars(scan);
		
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[0].length; j++) {
				if(M[i][j]!='.') {H.put(M[i][j], new int[] {i,j});}
			}
		}
		System.out.println(H.size());
		System.out.println(M.length+" "+M[0].length);
	
		System.out.println(doit2());
		//System.out.println(doit('@',0));
	
	}
	
	public static void main(String[] args) {
		D18 me = new D18();
		try{
			String sample ="D18_2.txt";//me.getClass().getName()+".txt";
			me.scan = new Scanner(System.in);
			if(new File(sample).isFile()) {
				me.scan = new Scanner(new FileInputStream(sample));
				System.err.println(new Scanner(new File(sample)).useDelimiter("\\Z").next());
			} else {
				System.err.println("INPUT FILE "+sample+" DOES NOT EXIST");
			}					
		}catch (Exception e) {
			System.err.println(e);
		}
		long time = System.currentTimeMillis();
		me.solve();
		time = System.currentTimeMillis() - time;
		System.err.format("time: %.2fs\n",time/1000.0);
		
	}
}
