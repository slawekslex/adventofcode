package utils;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class FastReader {

	final private int BUFFER_SIZE = 1 << 20;
	private DataInputStream din;
	private byte[] buffer;
	private int bufferPointer, bytesRead;

	public FastReader() {
		din = new DataInputStream(System.in);
		buffer = new byte[BUFFER_SIZE];
		bufferPointer = bytesRead = 0;
	}

	public FastReader(String file_name) {
		try {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String readLine() {
		StringBuffer sb = new StringBuffer();
		int c;
		while ((c = read()) != -1) {
			if (c == '\n')
				break;
			if(c=='\r') {
				read();
				break;
			}
			sb.append( (char) c);
		}
		return sb.toString();
	}
	
	public String next() {
		StringBuffer sb = new StringBuffer();
		int  c;
		while ((c = read()) != -1) {
			if (Character.isWhitespace(c))
				break;
			sb.append( (char) c);
		}
		return sb.toString();
	}

	public int nextInt() {
		int ret = 0;
		byte c = read();
		while (c <= ' ')
			c = read();
		boolean neg = (c == '-');
		if (neg)
			c = read();
		do {
			ret = ret * 10 + c - '0';
		} while ((c = read()) >= '0' && c <= '9');

		if (neg)
			return -ret;
		return ret;
	}

	public long nextLong() {
		long ret = 0;
		byte c = read();
		while (c <= ' ')
			c = read();
		boolean neg = (c == '-');
		if (neg)
			c = read();
		do {
			ret = ret * 10 + c - '0';
		} while ((c = read()) >= '0' && c <= '9');
		if (neg)
			return -ret;
		return ret;
	}

	public double nextDouble() {
		double ret = 0, div = 1;
		byte c = read();
		while (c <= ' ')
			c = read();
		boolean neg = (c == '-');
		if (neg)
			c = read();

		do {
			ret = ret * 10 + c - '0';
		} while ((c = read()) >= '0' && c <= '9');

		if (c == '.') {
			while ((c = read()) >= '0' && c <= '9') {
				ret += (c - '0') / (div *= 10);
			}
		}
		if(c=='E' || c=='e') {
			int exp = nextInt();
			ret = ret * Math.pow(10,exp);
		}
		if (neg)
			return -ret;
		return ret;
	}

	private void fillBuffer() {
		try {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if (bytesRead == -1)
			buffer[0] = -1;
	}

	private byte read() {
		if (bufferPointer == bytesRead)
			fillBuffer();
		return buffer[bufferPointer++];
	}

	// ---------------- Testing

	int[] readScan(InputStream is, int n) {
		Scanner s = new Scanner(is);
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = s.nextInt();
			// System.out.println("read "+i);
		}
		return A;
	}

	int[] readFast(int n) throws IOException {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = nextInt();

		}
		return A;
	}

	double[] readScanD(InputStream is, int n) {
		Scanner s = new Scanner(is);
		double[] A = new double[n];
		for (int i = 0; i < n; i++) {
			A[i] = s.nextDouble();
		}
		return A;
	}

	double[] readFastD(int n) throws IOException {
		double[] A = new double[n];
		for (int i = 0; i < n; i++) {
			A[i] = nextDouble();

		}
		return A;
	}
	public static void main(String[] args) throws IOException {
		
	/*	  FileWriter f = new FileWriter("milliondoubles.txt"); BufferedWriter bw = new
		  BufferedWriter(f); 
		  Random r = new Random(); 
		  for(int i=0;i<1000000;i++) { 
			  double x = r.nextDouble(); 
			  bw.write(x+" "); 
			  } 
		  	bw.close();
		 
		long t = System.currentTimeMillis();
		double[] A = new FastReader().readScanD(new FileInputStream("milliondoubles.txt"), 1000000);
		long t2 = System.currentTimeMillis();
		System.out.println("read in " + (t2 - t));

		t = System.currentTimeMillis();
		double[] A2 = new FastReader("milliondoubles.txt").readFastD(1000000);
		t2 = System.currentTimeMillis();
		System.out.println("read in " + (t2 - t));

		for(int i=0;i<A.length;i++) {
			if(Math.abs(A[i]-A2[i])>1e-10) {
				System.out.println(A[i]+" "+A2[i]+" "+Math.abs(A[i]-A2[i]));
				break;
			}
		}
		*/
		FastReader fr = new FastReader();
	
		String lin = fr.readLine();
		String lin2 = fr.readLine();
		String s = fr.next();
		int x = fr.nextInt();
		System.out.print(s+" "+x+" |"+lin+"|" +" |"+lin2+"|" );
	}
}
