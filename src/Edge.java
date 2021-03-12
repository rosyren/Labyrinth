/**
 * 
 * @author Rosy Ren 
 * CS2210A 2020
 * Assignment 5
 * class that represents the edges of the graph
 *
 */
public class Edge {

	private Node u, v;
	private int type;
	private String label;

	/**
	 * constructor of the class that takes in u and v endpoints and take in the type of the edge
	 * @param u
	 * @param v
	 * @param type
	 */
	public Edge(Node u, Node v, int type)   {
		this.u = u;
		this.v = v;
		this.type = type;
	}
	
	/**
	 * constructor of the class that takes in u and v endpoints and takes in type and label 
	 * @param u
	 * @param v
	 * @param type
	 * @param label
	 */
	
	public Edge(Node u, Node v, int type, String label) {
		this.u = u;
		this.v = v;
		this.type = type;
		this.label = label;
	}

	/**
	 * returns the first end point of the edge
	 * @return Node
	 */
	public Node firstEndpoint() {
		return u;
	}
	/**
	 * returns the second en point of the edge 
	 * @return Node
	 */

	public Node secondEndpoint()  {
		return v;
	}

	/**
	 * returns the type of the edge
	 * @return int
	 */
	public int getType()    {
		return type;
	}
	/**
	 * sets the type of the edge to the specified value 
	 * @param newType
	 */
	
	void setType (int newType){
		this.type = newType;
	}
	/**
	 * returns the label of the edge 
	 * @return String
	 */
	
	String getLabel() {
		return label;
	}
	/**
	 * sets the label of the edge into the specified value 
	 * @param newLabel
	 */
	void setLabel(String newLabel) {
		this.label = newLabel;
	}
	
}	
	
