import java.util.*;
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
	
	
	static void solve(char[][]M) {
		int res=0;
foo:	for ( int i = 0; i < M.length; i++) for (int j = 0; j < M[0].length; j++) {
			if(M[i][j]=='.')continue;
			System.out.println("Counting "+i+" "+j);
			int cnt = 0;
			for(int a=0;a<M.length;a++) for(int b=0;b<M[0].length;b++){
				if(M[a][b]=='.')continue;
				if(a==i &&b==j)continue;
				boolean vis=true;
				for(int c=0;c<M.length;c++) for(int d=0;d<M[0].length;d++){
					if(M[c][d]=='.')continue;
					if(c==i &&d==j)continue;
					if(c==a &&d==b)continue;
					int dx = c-i;int dy=d-j;
					int ddx = a-i;int ddy=b-j;
					if(abs(dx)>abs(ddx) || abs(dy)>abs(ddy))continue;
					if(signum(ddy)!=signum(dy) || signum(ddx)!=signum(dx))continue;
					if(dy==0) {
						if(ddy==0) {
							vis=false;
							System.out.format("(%d,%d) is blocking (%d, %d)\n", dx,dy, ddx,ddy);
						}
							
					}
					else if(dx==0) {
						if(ddx==0) {
							vis=false;
							System.out.format("(%d,%d) is blocking (%d, %d)\n", dx,dy, ddx,ddy);
						}
					}
					else if(ddx*dy ==ddy*dx) {
						System.out.format("(%d,%d) is blocking (%d, %d)\n", dx,dy, ddx,ddy);
						vis=false;
					}
						
				}
				if(vis) {
					cnt++;
					System.out.println("adding "+a+" "+b);
				}
			}
			res = max(res,cnt);
			M[i][j] = (char)('0'+cnt);
			System.out.println("----------");
			//break foo;
		}
		for(char[]c:M)System.out.println(new String(c));
		System.out.println(res);
	}
	void solve() {
		char[][]M =chars(scan);
		solve(M);
	}
	
	public static void main(String[] args) {
		D10 me = new D10();
		String[]s = new String[] {
				".#..#",
				".....",
				"#####",
				"....#",
				"...##",
		};
		//solve(chars(s));
		try{
			String sample =me.getClass().getName()+".txt";
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
