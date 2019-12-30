import java.util.*;

import utils.Prog;

import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class D23 {

	Scanner scan = new Scanner(System.in);

	
	void solve() {
		String code = readAll(scan);
		Prog[]P = new Prog[50];
		for(int i=0;i<P.length;i++) {
			P[i]= new Prog(code);
			P[i].inputs.add(i+0L);
			P[i].inputs.add(-1L);
			P[i].run();
		}
		long natx=-1,naty=-1;
		long lasty=0;
		while(true) {
			
			for(int i=0;i<P.length;i++) {
				for(int j=0;j<P[i].outputs.size();j+=3) {
					int id = P[i].outputs.get(j).intValue();
					long x = P[i].outputs.get(j+1);
					long y = P[i].outputs.get(j+2);
					//System.out.format("%d -> %d %d %d\n", i, id, x, y);
					if(id==255) {
						System.out.println("sending NAT "+" "+x+" "+y);
						natx =x; naty=y;
					} else {
					P[id].inputs.add(x);
					P[id].inputs.add(y);
					}
				}
				P[i].outputs.clear();
			}
			boolean allEmpty = true;
			for(int i=0;i<P.length;i++) {
				

				if(P[i].inputs.isEmpty()) {
					P[i].inputs.add(-1L);
				}else {
					allEmpty=false;
				}
				P[i].run();
			}
			if(allEmpty) {
				System.out.println("IDLE resume with "+natx+" "+naty);
				P[0].inputs.add(natx);
				P[0].inputs.add(naty);
				if(naty==lasty) {
					System.out.println("TWICE "+lasty);
					return;
				}
				lasty=naty;
				P[0].run();
				
			}
		}
	}
	
	public static void main(String[] args) {
		D23 me = new D23();
		try{
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
		long time = System.currentTimeMillis();
		me.solve();
		time = System.currentTimeMillis() - time;
		System.err.format("time: %.2fs\n",time/1000.0);
	}
}
