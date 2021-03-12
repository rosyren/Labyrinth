/**
 * 
 * @author Rosy Ren 
 * CS2210A 2020
 * Assignment 5
 * class that represents an undirected graph 
 * use an adjacency matrix for the graph 
 *
 */

import java.util.Iterator;
import java.util.LinkedList;

public class Graph implements GraphADT {
	
	private Edge matrix[][];
	private Node nodes[];
	

	/**
	 * creates a graph with n nodes and no edges
	 * constructor of the class
	 * @param n
	 */
	public Graph(int n) {
		// container for vertices 
		nodes = new Node[n];
		for (int i=0; i<n; i++) {
			nodes[i] = new Node(i);
		}
		// container for edges ; initialized to no edges
		matrix = new Edge[n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				matrix[i][j] = null;
			}
		}
	}


	/**
	 * adds an edge to the graph containing u and v along with the specified edgetype
	 * @param nodeu, nodev, edgeType
	 * 
	 */
	public void insertEdge(Node nodeu, Node nodev, int edgeType) throws GraphException {
		Edge edge = new Edge(nodeu,nodev,edgeType);
		// node is null and exists to add Edge to 
		try {
			matrix[nodeu.getName()][nodev.getName()] = edge;
			matrix[nodev.getName()][nodeu.getName()] = edge;
		}
		// throw a GraphException if either node does not exist or the edge is already in the graph
		catch (Exception e) {
			throw new GraphException("error");
		}
	}
	 
	/**
	 * adds an edge to the graph containing u and v along with the specified edgetype and string label
	 * @param nodeu, nodev, edgeType, label 
	 * 
	 */
	public void insertEdge(Node nodeu, Node nodev, int edgeType, String label) throws GraphException {
		Edge edge = new Edge(nodeu,nodev,edgeType, label);
		// node is null and exists to add Edge to 
		try {
			matrix[nodeu.getName()][nodev.getName()] = edge;
			matrix[nodev.getName()][nodeu.getName()] = edge;
		}
		// throw a GraphException if either node does not exist or the edge is already in the graph
		catch (Exception e) {
			throw new GraphException("error");
		}
	}
	 
	
	/**
	 * returns the node with the specified name
	 * if no name exists throw an exception 
	 * @param int name
	 * @return node 
	 */
	public Node getNode(int name) throws GraphException {
		try{
			return nodes[name];
		}
		// throw a GraphException if node does not exist
		catch (Exception e) {
			throw new GraphException("error");
		}
	}

	
	/**
	 *  returns a Java Iterator storing all the edges incident on node u 
	 * It returns null if u does not have any edges incident on it. 
	 * If u is not a node of the graph a GraphException is thrown.
	 * @param node U
	 * @return Interator<Edge> 
	 * 
	 */
	public Iterator<Edge> incidentEdges(Node u) throws GraphException {
		try {
			// list to store edges 
			LinkedList<Edge> list = new LinkedList<Edge>();
			// go through the row of u to find if any edges exist
			for (int i = 0; i< nodes.length; i++) {
				if (matrix[u.getName()][i] != null) list.add(matrix[u.getName()][i]);		
			}
			// return null if u does not have any edges incident on it
			if (list.isEmpty()) return null;
			// return iterator storing all the edges incident on node u
			else return list.iterator();
		}
		// throw a GraphException if node does not exist
		catch (Exception e) {
			throw new GraphException("error");
		}
	}

	
	/**
	 * 
	 * returns the edge connecting nodes u and v
	 * throws an exception if edge doesn't exist 
	 * @param node u, node v
	 * @return edge
	 * 
	 */
	public Edge getEdge(Node u, Node v) throws GraphException {
		try {
			return matrix[u.getName()][v.getName()];
		}
		catch (Exception e) {
			throw new GraphException("error");
		}
	}
                   
	/**
	 * checks if the nodes are adjacent 
	 * @param node u, node v
	 * @return boolean
	 */
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		try {
			// check that either two edges are not null 
			if (matrix[u.getName()][v.getName()] == null || matrix[v.getName()][u.getName()] == null) {
				return false;
			}
			return true;
		}
		catch (Exception e) {
			throw new GraphException("error");
		}
	}   
	
}
