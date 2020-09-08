package Astar; 
import java.util.ArrayList;
import java.util.List;

	public class Graph {
		private List nodes;
		private List edges;
		
		public Graph(List nodes, List edges) {
			this.nodes = nodes;
			this.edges = edges;
		}
		
		public List getEdges() {
			return edges;
		}
		
		public List getNodes() {
			return nodes;
		}
		
		public static ArrayList getNeighbors(List edges,Node n) {
			ArrayList neighbors = new ArrayList();
			for(Edge edge : edges) {
				if(edge.getStart().equals(n)) {
					neighbors.add(edge.getEnd());
				}
			}
			return neighbors;
		}
		
		public static Double getDistanceFrom(List edges, Node start, Node end) {
			for(Edge e : edges) {
				if(e.getStart().equals(start) && e.getEnd().equals(end)) {
					return e.getLength();
				}
			}
			return null;
		}
		
	}
