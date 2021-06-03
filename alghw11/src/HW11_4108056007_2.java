public class HW11_4108056007_2 extends GroupCounting{
    int[] id, size;
    int island;
    public static void main(String[] args){
        HW11_4108056007_2 test= new HW11_4108056007_2();
        String[] A={"1","3","5","7","9","11","13","2"};
        String[] B={"2","4","6","8","10","12","14","3"};

        System.out.println(test.count(A, B));
    }

    public void union(int p, int q)
    {
        int i = find_root(p);
        int j = find_root(q);
        if (i == j) return;

        if (size[i] < size[j]) { id[i] = j; size[j] += size[i]; }
        else { id[j] = i; size[i] += size[j]; }
        island--;
    }

    public int find_root(int i)
    {
        while (i!=id[i]) {
            id[i] = id[id[i]];
            i=id[i];
        }
        return i;
    }

    public int count(String[] A, String[] B){
        int n1, n2, i;
        id= new int[A.length*3];
        size= new int[A.length*3];
        for (i=0; i< id.length; ++i){
            size[i]=1; id[i]=-1;
        }

        for (i=0; i<A.length; ++i){
            n1=Integer.parseInt(A[i]); n2=Integer.parseInt(B[i]);
            if (id[n1]!=(-1) && id[n2]!=(-1)){
                union(n1, n2);
            }
            else if (id[n1]!=(-1)){
                id[n2]= n2; island++;
                union(n1, n2);
            }
            else if (id[n2]!=(-1)){
                id[n1]=n1; island++;
                union(n1, n2);
            }
            else{
                id[n1]=n1; island++;
                id[n2]= n2; island++;
                union(n1, n2);
            }

        }
        return island;
    }
}
