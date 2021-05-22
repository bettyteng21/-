public class HW09_4108056007_5 extends LSD{
    final int capacity=100000;
    int max_degree, LS=0, vis, front, size, rear=capacity-1;
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

    void enqueue(int item){
        if(size == capacity) return;
        rear= ((rear+1)%capacity);
        array[rear]=item;
        ++size;
    }

    int dequeue(){
        if (size==0) return 2147483647;
        int item= array[front];
        front= ((front+1)%capacity);
        --size;
        return item;
    }

    public int Distance(int[][] input_arr){
        int i, V, s=0, max_size=0, curr_index;
        final int len=input_arr.length;
        Node temp;
        for (i=0, V=0; i<len; ++i){
            if(input_arr[i][0]>V) V=input_arr[i][0];
            if (input_arr[i][1]>V) V=input_arr[i][1];
        }
        ++V;

        Node[] adj_list= new Node[V];
        for (i=0; i<len; ++i){
            temp= new Node(input_arr[i][0]);
            temp.next=adj_list[input_arr[i][1]];
            adj_list[input_arr[i][1]]=temp;
            temp= new Node(input_arr[i][1]);
            temp.next=adj_list[input_arr[i][0]];
            adj_list[input_arr[i][0]]=temp;
        }

        for (i=0; i<V; ++i, s=0){
            temp=adj_list[i];
            while (temp!=null){
                ++s;
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
        while (size!=0){
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
        int[] dist =new int[V];
        visited= new boolean[V];
        for (i=0; i<V; ++i){
            dist[i] = 2147483647;
        }
        enqueue(vis);
        visited[vis]= true;
        dist[vis]=0;
        while (size!=0){
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
            if(dist[i]>LS && dist[i]!=2147483647) LS=dist[i];
        }
        return LS;
    }
}