package utils;
import static java.lang.Math.*;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
public class Geom {

	/**
	 * creates a vector from two points
	 */
	public static int[] vect(int x1, int y1, int x2, int y2){
		return new int[]{x2-x1,y2-y1};
	}
	
	/**
	 * creates a vector from two points
	 */
	public static int[] vect(int[]p1, int[]p2){
		return new int[]{p2[0]-p1[0],p2[1]-p1[1]};
	}
	
	/**
	 * x1*x2 + y1*y2
	 * v1 * v2 = |v1| * |v2| * cos(alpha)
	 * dot / (|v1| * |v2|) == 0 <===> perpendicular 
	 * dot / (|v1| * |v2|) == 1 <===> parallel
	 */
	public static int dot(int[]v1, int[]v2){
		return v1[0]*v2[0]+v1[1]*v2[1];
	}
	
	/**
	 * x1*y2 - y1*x2 
	 * v1 x v2 = |v1| * |v2| * sin(alpha)
	 * |v1 x v2 / 2| = area of trinagle
	 * if v1 is less than 180 degrees clockwise from v2, the value is positive
	 */
	public static int cross(int[]v1, int[]v2){
		return v1[0]*v2[1]-v1[1]*v2[0];
	}
	
	public static double len(int[] v){
		return sqrt(v[0]*v[0]+v[1]*v[1]);
	}
	
    
    /**
     * rotate a point (x1, y1) counter clockwise around another point cent(x, y) 
     * x' = x + (x1 - x) * cos(ang) - (y1 - y) * sin(ang)
     * y' = y + (x1 - x) * sin(ang) + (y1 - y) * cos(ang)
     */
	public static double[] rotate(double[] p1, double[]cent, double angle){
        double[] res = new double[2];
        res[0] = cent[0]+(p1[0]-cent[0])*cos(angle) - (p1[1]-cent[1])*sin(angle);
        res[1] = cent[1]+(p1[0]-cent[0])*sin(angle) + (p1[1]-cent[1])*cos(angle);
        return res;
    }
    
    /**
     * Returns point at distance r, from p in the direction ang
     */
	public static double[] translat(double[] p, double r, double ang){
        double[]res = new double[2];
        res[0]=p[0]+r*cos(ang);
        res[1]=p[1]+r*sin(ang);
        return res;
    }
    
	public static double linePoint(int[]A, int[]B, int[]C){
		Point2D pa = new Point2D.Double(A[0],A[1]);
		Point2D pb = new Point2D.Double(B[0],B[1]);
		Point2D pc = new Point2D.Double(C[0],C[1]);
		Line2D l = new Line2D.Double(pa,pb);
		return l.ptLineDist(pc);
	}
	
	public static double segmentPoint(int[]A, int[]B, int[]C){
		Point2D pa = new Point2D.Double(A[0],A[1]);
		Point2D pb = new Point2D.Double(B[0],B[1]);
		Point2D pc = new Point2D.Double(C[0],C[1]);
		Line2D l = new Line2D.Double(pa,pb);
		return l.ptSegDist(pc);
	}
	
	public static Point2D.Double intersect(Line2D l1, Line2D l2){
		double A1 =l1.getY2()-l1.getY1();
		double B1 =l1.getX1()-l1.getX2();
		double C1 = A1*l1.getX1()+B1*l1.getY1();
		double A2 =l2.getY2()-l2.getY1();
		double B2 =l2.getX1()-l2.getX2();
		double C2 = A2*l2.getX1()+B2*l2.getY1();
		double det = A1*B1-A2*B2;
		if(abs(det)<1e-9){
			throw new RuntimeException("parallel");
		}
		double x = (B2*C1-B1*C2)/det;
		double y = (A1*C2-A2*C1)/det;
		Point2D.Double res = new Point2D.Double(x,y);
		return res;
	}
	
	public static double area(int[][] P){
		int r =0;
		for(int i=1;i+1<P.length;i++){
			int[]v1=vect(P[0],P[i]);
			int[]v2=vect(P[0],P[i+1]);
			r+=cross(v1,v2);
		}
		return abs(r)/2.0;
	}
	
	public static int[][] hull( int[][]P, boolean onEdge ){
		ArrayList<int[]>A = new ArrayList<int[]>();
		int p = 0;
		int N =P.length;
		for(int i=1;i<N;i++){
			if(P[i][0]<P[p][0]){
				p=i;
			}else{
				if(P[i][0]==P[p][0]&&P[i][1]>P[p][1]){
					p=i;
				}
			}
		}
		int start = p;
		boolean[]used = new boolean[N];
		do{
			int next=-1;
			int dist=onEdge?Integer.MAX_VALUE:0;
			A.add(P[p]);
			for(int i=0;i<N;i++){
				if(used[i]||i==p)continue;
				if(next==-1)next=i;
				int[]pi=vect(P[p],P[i]);
				int cross = cross(pi,vect(P[p],P[next]));
				int d=dot(pi,pi);
				if(cross<0){
					next=i;
					dist=d;
				}else if(cross==0){
					if(onEdge && d < dist){
                        dist = d;
                        next = i;
                    }else if(!onEdge && d > dist){
                        dist = d;
                        next = i;
                    }
				}
			}
			p=next;
			used[p]=true;
		}while(start!=p);

		return A.toArray(new int[0][0]);
	}
	
	public static void main(String[] args) {
		int[] v1 = vect(3,2,2,1);
		int[] v2= vect(3,2,3,1);
		System.out.println(abs(cross(v1,v2))/2.0);
		int[][]P={{3,2},{2,1},{3,1}};
		System.out.println(area(P));
	}

}
