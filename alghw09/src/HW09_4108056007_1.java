import java.util.ArrayList;
public class HW09_4108056007_1 extends LSD{
    int max_degree=0, LS=0;
    int[] pred;
    int[] dist;

    public static void main(String[] args){
        HW09_4108056007_1 test= new HW09_4108056007_1();
        int[][] array={{0,1},{0,2},{0,4},{1,3},{1,4},{2,5},{6,7}};
        System.out.println(test.Distance(array));
    }

    class Queue{
        int front=0, size=0, capacity=8000000, rear=capacity-1;
        int[] array= new int[capacity];

        boolean isFull(Queue queue) {
            return (queue.size == queue.capacity);
        }
        boolean isEmpty(Queue queue){
            return (queue.size==0);
        }

        void enqueue(int item){
            if(isFull(this)) return;
            rear= ((rear+1)%capacity);
            array[rear]=item;
            size= size+1;
        }

        int dequeue(){
            if (isEmpty(this)) return Integer.MIN_VALUE;

            int item= array[front];
            front= ((front+1)%capacity);
            size= size-1;
            return item;
        }

    }

    class Graph{
        private final ArrayList<ArrayList<Integer>> node;
        int V;
        public Graph(int V){
            this.V=V;
            this.node= new ArrayList<>(V);
            for (int i=0; i<V; ++i){
                node.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v){
            this.node.get(u).add(v);
            this.node.get(v).add(u);
        }

        void find_max_degree(){
            int degree_size=0;
            for (int i=0; i<V; ++i){
                if (node.get(i).size()>degree_size) {
                    max_degree=i; degree_size=node.get(i).size();
                }
            }
        }

        public void BFS(){ //start= max_degree
            Queue q= new Queue();
            boolean[] visited= new boolean[V];
            pred= new int[V];
            dist= new int[V];
            int i;
            for (i=0; i<V; ++i){
                dist[i] = Integer.MAX_VALUE;
                pred[i] = -1;
            }

            q.enqueue(max_degree);
            visited[max_degree]= true;
            dist[max_degree]=0;

            int vis, curr_index;
            while (!q.isEmpty(q)){
                vis= q.dequeue();

                for (i=0; i<node.get(vis).size(); ++i){
                    curr_index=node.get(vis).get(i);
                    if(!visited[curr_index]){
                        visited[curr_index]=true;
                        dist[curr_index]=dist[vis]+1;
                        pred[curr_index]=vis;
                        q.enqueue(curr_index);
                    }
                }
                for (i=0; i<V; ++i){
                    if(dist[i]>LS && dist[i]!=Integer.MAX_VALUE) LS=dist[i];
                }
            }
        }

    }

    public int Distance(int[][] array){
        int V=0, i, curr;
        for (i=0; i<array.length; ++i){
            if(array[i][0]>V) V=array[i][0];
            if (array[i][1]>V) V=array[i][1];
        }
        V+=1;

        Graph graph= new Graph(V);
        for (i=0; i<array.length; ++i){
            graph.addEdge(array[i][0], array[i][1]);
        }

        graph.find_max_degree();
        graph.BFS();
        for (i=1, curr=dist[0]; i<V; ++i){
            if(dist[i]>curr && dist[i]!=Integer.MAX_VALUE){
                max_degree=i;
                curr=dist[i];
            }
        }
        graph.BFS();
        return LS;
    }
}