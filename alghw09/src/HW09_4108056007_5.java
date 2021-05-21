public class HW09_4108056007_5 extends LSD{
    final int capacity=100000;
    int max_degree=0, LS=0, V=0, vis, front, size, rear=capacity-1;
    int[] array;

    public static void main(String[] args){
        HW09_4108056007_5 test= new HW09_4108056007_5();
        int[][] array={{0,1},{0,2},{0,4},{1,3},{1,4},{2,5},{6,7}};
        System.out.println(test.Distance(array));
    }

    class Node{
        int key;
        Node next;

        public Node(int key){
            this.key=key;
            this.next= null;
        }
    }

        boolean isFull() {
            return (size == capacity);
        }
        boolean isEmpty(){
            return (size==0);
        }

        void enqueue(int item){
            if(isFull()) return;
            rear= ((rear+1)%capacity);
            array[rear]=item;
            ++size;
        }

        int dequeue(){
            if (isEmpty()) return 2147483647;
            int item= array[front];
            front= ((front+1)%capacity);
            --size;
            return item;
        }

    public int Distance(int[][] input_arr){
        int i;
        final int len=input_arr.length;
        for (i=0, V=0; i<len; ++i){
            if(input_arr[i][0]>V) V=input_arr[i][0];
            if (input_arr[i][1]>V) V=input_arr[i][1];
        }
        ++V;

        Node node;
        Node[] adj_list= new Node[V];
        for (i=0; i<len; ++i){
            node= new Node(input_arr[i][0]);
            node.next=adj_list[input_arr[i][1]];
            adj_list[input_arr[i][1]]=node;
            node= new Node(input_arr[i][1]);
            node.next=adj_list[input_arr[i][0]];
            adj_list[input_arr[i][0]]=node;
        }

        int s=0, max_size=0;
        Node temp;
        for (i=0; i<V; ++i, s=0){
            temp=adj_list[i];
            while (temp!=null){
                s++;
                temp=temp.next;
            }
            if (max_size<s){
                max_size=s; max_degree=i;
            }
        }

        boolean[] visited= new boolean[V];
        front=0; size=0; rear=capacity-1;
        array= new int[capacity];
        enqueue(max_degree);
        visited[max_degree]= true;
        int  curr_index;
        while (!isEmpty()){
            vis= dequeue();

            temp=adj_list[vis];
            while(temp!=null){
                curr_index=temp.key;
                if(!visited[curr_index]){
                    visited[curr_index]=true;
                    enqueue(curr_index);
                }
                temp=temp.next;
            }
        }

        front=0; size=0; rear=capacity-1;
        array= new int[capacity];

        int[] dist;
        visited= new boolean[V];
        dist = new int[V];
        for (i=0; i<V; ++i){
            dist[i] = 2147483647;
        }
        enqueue(vis);
        visited[vis]= true;
        dist[vis]=0;
        while (!isEmpty()){
            vis= dequeue();
            temp=adj_list[vis];
            while(temp!=null){
                curr_index=temp.key;
                if(!visited[curr_index]){
                    visited[curr_index]=true;
                    dist[curr_index]=dist[vis]+1;
                    enqueue(curr_index);
                }
                temp=temp.next;
            }
        }
        for (i=0; i<V; ++i){
            if(dist[i]>LS && dist[i]!=2147483647) {
                LS=dist[i];
            }
        }
        return LS;
    }
}