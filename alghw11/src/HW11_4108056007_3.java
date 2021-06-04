public class HW11_4108056007_3 extends GroupCounting{
    int[] id, size;
    int island;
    public static void main(String[] args){
        HW11_4108056007_3 test= new HW11_4108056007_3();
        String[] A={"1","3","5","7","9","11","13"};
        String[] B={"2","4","6","8","10","12","14"};

        System.out.println(test.count(A, B));
    }

    public int find_root(int p) {
        int root = p, newp;
        while (root != id[root])
            root = id[root];
        while (p != root) {
            newp = id[p];
            id[p] = root;
            p = newp;
        }
        return root;
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

    public int count(String[] A, String[] B){
        int n1, n2, i, id_len=A.length*3;
        id= new int[id_len];
        size= new int[id_len];
        for (i=0; i< id_len; ++i){
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
                id[n1]=n1;
                id[n2]= n2; island+=2;
                union(n1, n2);
            }

        }
        return island;
    }
}
