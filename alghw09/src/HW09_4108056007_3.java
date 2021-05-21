import java.util.Collections;
import java.util.ArrayList;
import java.util.Stack;
public class HW09_4108056007_3 extends LSD{
    int max_degree=0, LS=0;
    final int capacity=100000;
    ArrayList<ArrayList<Integer>> node;
    Stack<Integer> endPoints= new Stack<>();
    public static void main(String[] args){
        HW09_4108056007_3 test= new HW09_4108056007_3();
        int[][] array={{0,1},{0,2},{0,4},{1,3},{1,4},{2,5},{6,7}};
        System.out.println(test.Distance(array));
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

    class Graph{
        final int V;
        public Graph(int V){
            this.V=V;
        }

        public void BFS(){ //start= max_degree
            boolean[] visited= new boolean[V];
            Queue q= new Queue();
            q.enqueue(max_degree);
            visited[max_degree]= true;

            int vis, curr_index, flag, i;
            while (!q.isEmpty(q)){
                flag=0;
                vis= q.dequeue();

                for (i=0; i<node.get(vis).size(); ++i){
                    curr_index=node.get(vis).get(i);
                    if(!visited[curr_index]){
                        q.enqueue(curr_index);
                        visited[curr_index]=true;
                    }
                    else flag++;
                }
                if(flag==node.get(vis).size()) {
                    endPoints.push(vis);
                }
            }
        }

        public void longest_shortest(){
            Queue q= new Queue();
            boolean[] visited;
            int[] pred;
            int[] dist;
            int curr_node, i, vis, curr_index;

            while (!endPoints.empty()){
                curr_node= endPoints.pop();
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

                    for (i=0; i<node.get(vis).size(); ++i){
                        curr_index=node.get(vis).get(i);
                        if(!visited[curr_index]){
                            visited[curr_index]=true;
                            dist[curr_index]=dist[vis]+1;
                            pred[curr_index]=vis;
                            q.enqueue(curr_index);
                        }
                    }
                }
                for (i=0; i<V; ++i){
                    if(dist[i]>LS && dist[i]!=2147483647) {
                        LS=dist[i];
                    }
                }
            }
        }
    }

    public int Distance(int[][] array){
        int V=0, i;
        for (i=0; i<array.length; ++i){
            if(array[i][0]>V) V=array[i][0];
            if (array[i][1]>V) V=array[i][1];
        }
        V+=1;

        Graph graph= new Graph(V);
        node= new ArrayList<>(V);
        for (i=0; i<V; ++i){
            node.add(new ArrayList<>());
        }
        for (i=0; i<array.length; ++i){
            node.get(array[i][0]).add(array[i][1]);
            node.get(array[i][1]).add(array[i][0]);
        }

        int degree_size=0;
        for (i=0; i<V; ++i){
            if (node.get(i).size()>degree_size) {
                max_degree=i; degree_size=node.get(i).size();
            }
        }

        graph.BFS();
        graph.longest_shortest();
        return LS;
    }
}