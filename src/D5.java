import java.util.*;
import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import utils.Prog;

public class D5 {

	Scanner scan = new Scanner(System.in);

	
	//15724522
	void solve() {
		String s  =scan.nextLine();
		Prog p = new Prog(s);
		p.inputs.add(1L);
		p.run();
		System.out.println(p.outputs);
		p = new Prog(s);
		p.inputs.add(5L);
		p.run();
		System.out.println(p.outputs);
	}
	
	public static void main(String[] args) {
		D5 me = new D5();
		try{
			
			String sample ="D5.txt";
			me.scan = new Scanner(new FileInputStream(sample));
			//me.scan = new Scanner(System.in);
		}catch (Exception e) {
			System.err.println(e);
		}
		me.solve();
	}
}
