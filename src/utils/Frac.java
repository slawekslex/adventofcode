package utils;

class Frac implements Comparable<Frac>{
    long a, b;
    long nwd( long a, long b ){return b==0?a:nwd( b, a % b);}
    long nww( long a, long b ){return a* b / nwd( a, b );}
    public Frac(long x, long y){
        a=x;b=y;
        if(b<0){b=-b;a=-a;}
        boolean minus = false;
        if(a<0){a=-a;minus=true;}
        long n = nwd(a, b);
        a/=n;b/=n;
        if(minus)a=-a;
    }
    public Frac(long x){this(x,1L);}
    public Frac add(Frac f){
        long den = nww(b, f.b);
        return new Frac(a*(den/b) + f.a*(den/f.b), den);
    }
    public Frac sub(Frac f){
        long den = nww(b, f.b);
        return new Frac(a*(den/b) - f.a*(den/f.b), den);
    }
    public Frac mul(Frac f){
        return new Frac(a*f.a, b*f.b);
    }
    public Frac div(Frac f){
        return new Frac(a*f.b, b*f.a);
    }
    public int compareTo(Frac o) {
        long x = a*o.b;
        long y = b*o.a;
        if(x<y)return -1;
        if(x>y)return 1;
        return 0;
    }
}
