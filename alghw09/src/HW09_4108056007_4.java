public class HW09_4108056007_4 extends LSD{
    int max_degree=0, LS=0, V=0;
    final int capacity=100000;
    Queue second= new Queue();
    public static void main(String[] args){
        HW09_4108056007_4 test= new HW09_4108056007_4();
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

    class Queue{
        int front=0, size=0, rear=capacity-1;
        int[] array= new int[capacity];

        boolean isFull(Queue queue) {
            return (queue.size == capacity);
        }
        boolean isEmpty(Queue queue){
            return (queue.size==0);
        }

        void enqueue(int item){
            if(isFull(this)) return;
            this.rear= ((this.rear+1)%capacity);
            this.array[this.rear]=item;
            this.size= this.size+1;
        }

        int dequeue(){
            if (isEmpty(this)) return Integer.MIN_VALUE;
            int item= this.array[this.front];
            this.front= ((this.front+1)%capacity);
            this.size= this.size-1;
            return item;
        }
    }

        public void BFS(Node[] adj_list){ //start= max_degree
            boolean[] visited= new boolean[V];
            Queue q= new Queue();
            q.enqueue(max_degree);
            visited[max_degree]= true;

            Node temp;
            int vis, curr_index, flag, size;
            while (!q.isEmpty(q)){
                flag=0; size=0;
                vis= q.dequeue();

                temp=adj_list[vis];
                while(temp!=null){
                    curr_index=temp.key;
                    size++;
                    if(!visited[curr_index]){
                        visited[curr_index]=true;
                        q.enqueue(curr_index);
                    }
                    else flag++;
                    temp=temp.next;
                }
                if (flag==size) second.enqueue(vis);
            }
        }

        public void longest_shortest(Node[] adj_list){
            Queue q= new Queue();
            boolean[] visited;
            int[] pred;
            int[] dist;
            int curr_node, i, vis, curr_index;

            while (!second.isEmpty(second)){
                curr_node= second.dequeue();
                visited= new boolean[V];
                pred = new int[V];
                dist = new int[V];
                for (i=0; i<V; ++i){
                    dist[i] = 2147483647;
                    pred[i] = -1;
                }

                q.enqueue(curr_node);
                visited[curr_node]= true;
                dist[curr_node]=0;

                while (!q.isEmpty(q)){
                    vis= q.dequeue();

                    Node temp=adj_list[vis];
                    while(temp!=null){
                        curr_index=temp.key;
                        if(!visited[curr_index]){
                            visited[curr_index]=true;
                            dist[curr_index]=dist[vis]+1;
                            pred[curr_index]=vis;
                            q.enqueue(curr_index);
                        }
                        temp=temp.next;
                    }
                }
                for (i=0; i<V; ++i){
                    if(dist[i]>LS && dist[i]!=2147483647) {
                        LS=dist[i];
                    }
                }
            }
        }


    public int Distance(int[][] array){
        int i;
        final int len=array.length;
        for (i=0, V=0; i<len; ++i){
            if(array[i][0]>V) V=array[i][0];
            if (array[i][1]>V) V=array[i][1];
        }
        ++V;

        Node node;
        Node[] adj_list= new Node[V];
        for (i=0; i<len; ++i){
            node= new Node(array[i][0]);
            node.next=adj_list[array[i][1]];
            adj_list[array[i][1]]=node;
            node= new Node(array[i][1]);
            node.next=adj_list[array[i][0]];
            adj_list[array[i][0]]=node;
        }

        int size=0, max_size=0;
        Node temp;
        for (i=0; i<V; ++i, size=0){
            temp=adj_list[i];
            while (temp!=null){
                size++;
                temp=temp.next;
            }
            if (max_size<size){
                max_size=size; max_degree=i;
            }
        }

        BFS(adj_list);
        longest_shortest(adj_list);
        return LS;
    }
}