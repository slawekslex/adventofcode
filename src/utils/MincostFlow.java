package utils;
import static java.util.Arrays.fill;


public class MincostFlow {
    int mincostMaxFlow(boolean[][]M, int[][]C,int sink, int source){
        int res =0;
        int n = M.length;
        int flow =0;
        while(true){
            int[]D = new int[n];
            int []P = new int[n];
            fill(D,Integer.MAX_VALUE/2);
            D[source]=0;
            while(true){
                boolean changed =false;
                for(int i=0;i<n;i++){
                    for(int j=0;j<n;j++){
                        if(!M[j][i])continue;
                        if(D[i]>D[j]+C[j][i]){
                            D[i]=D[j]+C[j][i];
                            P[i]=j;
                            changed=true;
                        }
                    }
                }
                if(!changed)break;
            }
            if(D[sink]==Integer.MAX_VALUE/2)break;
            res+=D[sink];
            int x = sink;
            flow++;
            while(x!=source){
                int p = P[x];
                C[x][p]=-C[p][x];
                M[x][p]=true;
                M[p][x]=false;
                x=p;
            }
        }
        return res;
    }
}
