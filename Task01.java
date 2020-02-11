
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.Arrays; 
import java.util.Collections;
public class Task01 {
    //define colors
    public static int WHITE = 1;
    public static int GRAY = 2;
    public static int BLACK = 3;
   
    //define resources
    public static int[] parent;
    public static  int[] color ;
    public static int[] d;
    public static int[] f;
    public static int time;
    public static String discovered = "";
    public static String processed = "";
    public static Stack stack = new Stack();
    
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner sc = new Scanner(file);
            
        //size
        int numberOfVerteces = sc.nextInt();
        int[][] g = new int[numberOfVerteces][numberOfVerteces];

        while (sc.hasNextInt()) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            g[src][dest] = 1;
        }

//        for (int i = 0; i < numberOfVerteces; i++) {
//            for (int j = 0; j < numberOfVerteces; j++) {
//                System.out.print(g[i][j] + " ");
//            }
//            System.out.println("");
//        }
        color = new int[numberOfVerteces];
        d = new int[numberOfVerteces];
        f = new int[numberOfVerteces];
        parent = new int[numberOfVerteces];

        //write DFS code here
        //follow the pseudocode
        DFS(g);
        
        
        //output
        //print discovered nodes
        System.out.println("\nDiscovered nodes: "+discovered);
        
        
        //print processed nodes
        //
        System.out.println("\nProcessed Nodes: "+ processed);
        
        //print topologically sorted nodes (inverse order of processed nodes
        System.out.print("\nTopological Sort: ");
        
        while(!stack.isEmpty()){
            
            System.out.print(stack.pop());
            if(!stack.isEmpty())System.out.print("-->");
        }
        
              System.out.println();

        Arrays.sort(f);
        
        System.out.print("\nFinished times: ");
        for(int i = f.length-1; i>=0;i--){
            
            System.out.print(f[i]+", ");
            
        }
        //end of code
    }

    public static void DFS(int[][] graph) {
        // follow pseudocode
        for(int u = 0;u<graph[0].length;u++){
            color[u] = WHITE;
            parent[u] = -1;           
        }
       
         for(int u = 0;u<graph[0].length;u++){
            if (color[u] == WHITE) DFS_visit(graph,u);         
        }
    }
    
    public static void DFS_visit(int [][] graph, int u){
        //follow pseudocode
        color[u] = GRAY;
        discovered+= u +" ,";
        time++;
        d[u]=time;
       for(int v = u; v<graph[1].length;v++){
          if(graph[u][v] == 1){
          
           if(color[v] == WHITE) {
               parent[v] = u;
               DFS_visit(graph,v);
           }
         
          }
           
          
                                                                            
        }
    color[u] = BLACK;
    stack.push(u);
    processed += u + " ,";
    time = time+1;
    f[u] = time;
    }
    
}
