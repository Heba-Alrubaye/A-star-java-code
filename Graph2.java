package Astar;
import java.util.*; 
import java.util.LinkedList; 
public class Graph2 { //Articulation Points

	// A Java program to find articulation points in an undirected graph 
	// This class represents an undirected graph using adjacency list 
	// representation 
	
	    private int N;   // No. of Node
	  
	    // Array  of lists for Adjacency List Representation 
	    private LinkedList<Integer> adj[]; 
	    int count = 0; 
	    static final int NIL = -1; 
	  
	    // Constructor 
	    Graph2(int n) 
	    { 
	        N = n; 
	        adj = new LinkedList[n]; 
	        for (int i=0; i<n; ++i) 
	            adj[i] = new LinkedList(); 
	    } 
	  
	    //Function to add an segment into the graph 
	    void addSegment(int n, int s) 
	    { 
	        adj[n].add(s);  // Add s to n's list. 
	        adj[s].add(n);    //Add n to s's list 
	    } 
	  
	    // A recursive function that find articulation points using DFS 
	    // u --> The node to be visited next 
	    // visited[] --> keeps tract of visited nodes 
	    // disc[] --> Stores discovery count of visited nodes 
	    // parent[] --> Stores parent nodes in DFS tree 
	    // ap[] --> Store articulation points 
	    void APUtil(int u, boolean visited[], int disc[], 
	    		int low[], int parent[], boolean ap[]) 
	    { 
	  
	        // Count of children in DFS Tree 
	        int children = 0; 
	  
	        // Mark the current node as visited 
	        visited[u] = true; 
	  
	        // Initialize discovery time and low value 
	        disc[u] = low[u] = ++count; 
	  
	        // Go through all nodes adjacent to this 
	        Iterator<Integer> i = adj[u].iterator(); 
	        while (i.hasNext()) 
	        { 
	            int n = i.next();  // n is current adjacent of u 
	  
	            // If n is not visited yet, then make it a child of u 
	            // in DFS tree and recur for it 
	            if (!visited[n]) 
	            { 
	                children++; 
	                parent[n] = u; 
	                APUtil(n, visited, disc, low, parent, ap); 
	             // Check if the subtree rooted with n has a connection to 
	                // one of the ancestors of u 
	                low[u]  = Math.min(low[u], low[n]); 
	  
	                // u is an articulation point in following cases 
	  
	                // (1) u is root of DFS tree and has two or more children. 
	                if (parent[u] == NIL && children > 1) 
	                    ap[u] = true; 
	  
	                // (2) If u is not root and low value of one of its child 
	                // is more than discovery value of u. 
	                if (parent[u] != NIL && low[n] >= disc[u]) 
	                    ap[u] = true; 
	            } 
	  
	            // Update low value of u for parent function calls. 
	            else if (n != parent[u]) 
	                low[u]  = Math.min(low[u], disc[n]); 
	        } 
	    } // The function to do DFS traversal. It uses recursive function APUtil() 
	    void AP() 
	    { 
	        // Mark all the vertices as not visited 
	        boolean visited[] = new boolean[N]; 
	        int disc[] = new int[N]; 
	        int low[] = new int[N]; 
	        int parent[] = new int[N]; 
	        boolean ap[] = new boolean[N]; // To store articulation points 
	  
	        // Initialize parent and visited, and ap(articulation point) 
	        // arrays 
	        for (int i = 0; i < N; i++) 
	        { 
	            parent[i] = NIL; 
	            visited[i] = false; 
	            ap[i] = false; 
	        } 
	     // Call the recursive helper function to find articulation 
	        // points in DFS tree rooted with node 'i' 
	        for (int i = 0; i < N; i++) 
	            if (visited[i] == false) 
	                APUtil(i, visited, disc, low, parent, ap); 
	  
	        // Now ap[] contains articulation points, print them 
	        for (int i = 0; i < N; i++) 
	            if (ap[i] == true) 
	                System.out.print(i+" "); 
	    } 
	 
	} 