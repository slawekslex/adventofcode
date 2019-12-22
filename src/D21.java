import java.util.*;

import utils.Prog;

import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;

import static java.lang.Character.*;
import static java.lang.Double.*;
import static utils.U.*;

public class D21 {

	Scanner scan = new Scanner(System.in);

	
	void solve() {
		Prog p = new Prog(readAll(scan));/*
		p.feedASCII("NOT A J\n");
		p.feedASCII("NOT B T\n");
		p.feedASCII("OR T J\n"); //!A || !B
		
		p.feedASCII("NOT C T\n");
		p.feedASCII("OR T J\n"); //!A || !B || !C
		
		p.feedASCII("AND D J\n");// !A || !B || !C && D
		
		p.feedASCII("NOT E T\n"); //!E
		p.feedASCII("NOT T T\n");// T
		p.feedASCII("OR H T\n");//I || E
		
		p.feedASCII("AND T J\n");
		*/
		
		p.feedASCII("OR E J\n");
		p.feedASCII("OR I J\n");
		p.feedASCII("AND D J\n");
		p.feedASCII("RUN\n");
		p.run();
		p.printASCII();
	}
	
	public static void main(String[] args) {
		D21 me = new D21();
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
