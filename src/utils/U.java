package utils;
import java.util.*;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.plaf.FontUIResource;

import static java.lang.Math.*; 
import static java.util.Arrays.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

import static java.lang.Integer.*;
import static java.lang.Character.*;
import static java.lang.Double.*;

public class U {
	
	public static String readAll(Scanner scan) {
		String res ="";
		while(scan.hasNext()) {
			String s = scan.nextLine();
			res+=s;
		}
		return res;
	}
	public static String readAll(Scanner scan, String delim) {
		String res ="";
		while(scan.hasNext()) {
			String s = scan.nextLine();
			if(res.length()>0)res+=delim;
			res+=s;
		}
		return res;
	}
	public static int[] ints(String s, String delim) {
		String[]ss = s.split(delim);
		return stream(ss).mapToInt(x->parseInt(x)).toArray();
	}
	public static int[] ints(String s) {
		return ints(s, "[ ,]");
	}
	
	
	public static char[][] chars(String[]s){
		char[][]R = new char[s.length][];
		for (int i = 0; i < R.length; i++) {
			R[i]=s[i].toCharArray();
		}
		return R;
	}
	public static char[][] chars(Scanner s){
		ArrayList<String>A = new ArrayList<>();
		while(s.hasNext()) {
			A.add(s.nextLine());
		}
		return chars(A.toArray(new String[A.size()]));
		
	}
	
	public static int[][] viewAs(int[]A, int a, int b){
		int n = A.length;
		if(a==-1)a=n/b;
		if(b==-1)b=n/a;
		int[][]ret = new int[a][b];
		for (int i = 0; i < a; i++) for (int j = 0; j < b; j++) ret[i][j]=A[i*b+j];
		return ret;
	}
	
	public static int[][][] viewAs(int[]A, int a, int b, int c){
		int n = A.length;
		if(a==-1)a=n/b/c;
		if(b==-1)b=n/a/c;
		if(c==-1)c=n/a/b;
		if(a*b*c !=n)throw new RuntimeException(String.format("shape [%d, %d, %d] not compatible with %d", a,b,c,n));
		int[][][]ret = new int[a][b][c];
		for (int i = 0; i < a; i++) for (int j = 0; j < b; j++)for(int k=0;k<c;k++) ret[i][j][k]=A[i*b*c + j*c +k];
		return ret;
	}
	
	public static int[] flatten(int[][]A) {
		return stream(A).flatMapToInt(x->stream(x)).toArray();
	}
	
	
	public static void print(int[]A) {
		System.out.println(Arrays.toString(A));
	}
	public static void print(int[][]A) {
		for(int[]a:A)System.out.println(Arrays.toString(a));
	}
	public static void print(int[][][]A) {
		for(int[][]a:A) {
			print(a);
			System.out.println(",");
		}
	}
	

	
	public static void main(String[] args) {
		int[]A = ints("1 2 3 4 5 6 7 8");
		int[][]M = viewAs(A, 2,-1);
		print(M);
		print(flatten(M));
		              
	}
}
