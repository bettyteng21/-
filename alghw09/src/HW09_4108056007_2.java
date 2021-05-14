import java.util.Collections;
import java.util.ArrayList;
import java.util.Stack;
public class HW09_4108056007_2 extends LSD{
    int max_degree=0, LS=0;
    Stack<Integer> pushStack= new Stack<>();
    Stack<Integer> popStack= new Stack<>();
    Stack<Integer> endPoints= new Stack<>();
    public static void main(String[] args){
        HW09_4108056007_2 test= new HW09_4108056007_2();
        int[][] array={{0,1},{0,2},{0,4},{1,3},{1,4},{2,5},{6,7}};
        System.out.println(test.Distance(array));
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

        public void push_queue(int x) {
            while (!popStack.isEmpty()) {
                pushStack.push(popStack.pop());
            }
            pushStack.push(x);
        }

        public int pop_queue() {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
            return popStack.pop();
        }

        public boolean empty_queue() {
            return pushStack.isEmpty() && popStack.isEmpty();
        }

        public void BFS(){ //start= max_degree
            boolean[] visited= new boolean[V];
            push_queue(max_degree);
            visited[max_degree]= true;

            int vis, curr_index, flag, i;
            while (!empty_queue()){
                flag=0;
                vis= pop_queue();

                for (i=0; i<node.get(vis).size(); ++i){
                    curr_index=node.get(vis).get(i);
                    if(!visited[curr_index]){
                        push_queue(curr_index);
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
            pushStack.clear();
            popStack.clear();
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
                    dist[i] = Integer.MAX_VALUE;
                    pred[i] = -1;
                }

                push_queue(curr_node);
                visited[curr_node]= true;
                dist[curr_node]=0;

                while (!empty_queue()){
                    vis= pop_queue();

                    for (i=0; i<node.get(vis).size(); ++i){
                        curr_index=node.get(vis).get(i);
                        if(!visited[curr_index]){
                            visited[curr_index]=true;
                            dist[curr_index]=dist[vis]+1;
                            pred[curr_index]=vis;
                            push_queue(curr_index);

                        }
                    }
                }
                for (i=0; i<V; ++i){
                    if(dist[i]>LS && dist[i]!=Integer.MAX_VALUE) LS=dist[i];
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
        for (i=0; i<array.length; ++i){
            graph.addEdge(array[i][0], array[i][1]);
        }

        graph.find_max_degree();
        graph.BFS();
        graph.longest_shortest();
        return LS;
    }
}