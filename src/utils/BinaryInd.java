package utils;
    /* add only elements with ind > 0! */
    class BinaryInd {
        int MAX ;
        int P[];
        public BinaryInd(int size){
            MAX=size;
            P = new int[MAX+1];
        }
        public BinaryInd(){
            this(1000000);
        }
        
        public void add(int ind, int x){
            while(ind<=MAX){
                P[ind]+=x;
                ind += ind & (-ind);
            }
        }
        
        public int getSum( int ind){
            int res =0;
            while(ind>0){
                res += P[ind];
                ind -= ind & (-ind);
            }
            return res;
        }
        /** inclusive **/
        public int getSum(int a, int b){
            return getSum(b) - getSum(a-1);
        }
        
        public int get(int ind){
            return getSum(ind)-getSum(ind-1);
        }
    }
