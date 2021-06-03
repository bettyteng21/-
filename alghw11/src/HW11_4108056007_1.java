public class HW11_4108056007_1 extends GroupCounting{
    int[] id, size;
    int island;
    public static void main(String[] args){
        HW11_4108056007_1 test= new HW11_4108056007_1();
        String[] A={"A","B","B","C","B","D","F","G"};
        String[] B={"E","E","C","D","D","E","H","H"};

        System.out.println(test.count(A, B));
    }

    public class node{
        public String key;
        public node next;
        int nodeNum;
        public node(String key, node next, int nodeNum){
            this.key=key; this.next=next; this.nodeNum= nodeNum;
        }
    }

    public class hashMap{
        int bucket;
        node[] list_node;
        public hashMap(int len){
            this.bucket=(1 << ((int) Math.ceil(Math.log10(len*2) / 0.3010)));
            this.list_node= new node[bucket];
        }

        public int is_in_map(String target){
            int index= (target.hashCode() & 0x7fffffff) % this.bucket;
            for (node curr= this.list_node[index]; curr!=null; curr= curr.next){
                if (curr.key.equals(target)) return curr.nodeNum;
            }
            return -1;
        }

        public void put_into_map(String input, int nodeNum){
            int index= (input.hashCode() & 0x7fffffff) % this.bucket;
            node new_node= new node(input, list_node[index], nodeNum);
            list_node[index]= new_node; island++;
        }
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
        int curr_index=0, n1, n2, i;
        id= new int[A.length*2];
        size= new int[A.length*2];
        for (i=0; i< id.length; ++i){
            size[i]=1; id[i]=i;
        }

        hashMap h= new hashMap(A.length);
        for (i=0; i<A.length; ++i){
            n1=h.is_in_map(A[i]); n2=h.is_in_map(B[i]);
            if (n1!=(-1) && n2!=(-1)){
                union(n1, n2);
            }
            else if (n1!=(-1)){
                h.put_into_map(B[i], curr_index);
                union(n1, curr_index++);
            }
            else if (n2!=(-1)){
                h.put_into_map(A[i], curr_index);
                union(curr_index++, n2);
            }
            else{
                h.put_into_map(A[i], curr_index++);
                h.put_into_map(B[i], curr_index++);
                union(h.is_in_map(A[i]), h.is_in_map(B[i]));
            }
        }
        return island;
    }
}
