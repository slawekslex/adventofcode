import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;



public class D20 {

	Scanner scan = new Scanner(System.in);
	 class Pos{
		int p;
		int level;
		Pos from;
		public Pos(int p, int l) {this.p=p;level=l;}
		@Override
		public int hashCode() {
			return level*128*128+p;
		}
		@Override
		public boolean equals(Object obj) {
			return hashCode()==obj.hashCode();
		}
		
		@Override
		public String toString() {
		
			return (keyx(p)+1)+","+(keyy(p)+1)+"  @"+level;
		}
	}
	
	
	int key(int x, int y) {
		return 128*x + y;
	}
	char[][]s;
	int keyx(int p) {return p/128;}
	int keyy(int p) {return p%128;}
	
	void addEdge(int x1, int y1, int x2, int y2) {
		int p1 = key(x1,y1);
		int p2 = key(x2,y2);
		E.get(p1).add(p2);
		E.get(p2).add(p1);
	}
	
	void addEdge(int p1, int p2) {
		E.get(p1).add(p2);
		E.get(p2).add(p1);
	}
	ArrayList<ArrayList<Integer>>E = new ArrayList<>();
	
	boolean isOuter(int p1, int p2) {
		int x1 = keyx(p1);int y1=keyy(p1);
		int x2 = keyx(p2);int y2=keyy(p2);
		if(abs(x1-x2)+abs(y1-y2)==1)return false;
		if(x1==2 || x1+3==s.length)return true;
		if(y1==2 || y1+3==s[0].length)return true;
		return false;
	}
	
	boolean isInner(int p1, int p2) {
		int x1 = keyx(p1);int y1=keyy(p1);
		int x2 = keyx(p2);int y2=keyy(p2);
		if(abs(x1-x2)+abs(y1-y2)==1)return false;
		if(x2==2 || x2+3==s.length)return true;
		if(y2==2 || y2+3==s[0].length)return true;
		return false;
	}
	
	void solve() {
		s = chars(scan);
		int[][] D = {{0,-1}, {0,1},{-1,0},{1,0}};
		System.out.println(s.length+" "+s[0].length);
		HashMap<String, ArrayList<Integer>>L = new HashMap<>();
		
		for(int i=0;i<128*128;i++)E.add(new ArrayList<>());
		for(int i=2;i<s.length-2;i++)for(int j=2;j<s[0].length-2;j++) {
			if(s[i][j]=='.') {
				for(int d=0;d<4;d++) {
					int x = i+D[d][0];int y=j+D[d][1];
					if(s[x][y]=='.')addEdge(i,j,x,y);
					if(Character.isAlphabetic(s[x][y])){
						String lab="";
						if(d==0)lab = ""+s[i][j-2]+s[i][j-1];
						if(d==1)lab = ""+s[i][j+1]+s[i][j+2];
						if(d==2)lab = ""+s[i-2][j]+s[i-1][j];
						if(d==3)lab = ""+s[i+1][j]+s[i+2][j];
						if(!L.containsKey(lab))L.put(lab, new ArrayList<>());
						L.get(lab).add(key(i,j));
					}
				}
			}
		}
		for(String l:L.keySet()) {
			ArrayList<Integer>A = L.get(l);
			if(A.size()==1)System.out.println("Skip "+l);
			else if(A.size()==2) {
				addEdge(A.get(0), A.get(1));
			}else System.out.println("ERROR");
		}
		Pos start = new Pos(L.get("AA").get(0),0);
		Pos end = new Pos(L.get("ZZ").get(0),0);
		LinkedList<Pos>Q = new LinkedList<>();
		boolean[]V = new boolean[128*128*128];
		int[]K = new int[128*128*128];
		Q.add(start);
		K[start.hashCode()]=0;
		while(!Q.isEmpty()) {
			Pos pPos = Q.removeFirst();
			
			if(pPos.equals(end)) {
				System.out.println(K[end.hashCode()]);
				while(pPos!=null) {
				//	System.out.println(pPos);
					pPos=pPos.from;
				}
				break;
			}
			V[pPos.hashCode()]=true;
			for(int q:E.get(pPos.p)) {
				int ql = pPos.level;
				if(isOuter(pPos.p, q))ql--;
				if(isInner(pPos.p, q))ql++;
				if(ql<0)continue;
				Pos qPos = new Pos(q,ql);
				qPos.from=pPos;
				if(V[qPos.hashCode()])continue;
				
				V[qPos.hashCode()]=true;
				K[qPos.hashCode()]=K[pPos.hashCode()]+1;
				Q.add(qPos);
			}
		}
	}
	
	public static void main(String[] args) {
		D20 me = new D20();
		try{
			String sample =me.getClass().getName()+".txt";
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
