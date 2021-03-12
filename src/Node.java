/**
 * 
 * @author Rosy Ren 
 * CS2210A 2020
 * Assignment 5
 * class that creates the nodes 
 *
 */


public class Node{
		
	private int name;
	private boolean mark;
	
	/**
	 * constructor 
	 * @param name
	 */
	
	public Node(int name) {
		
		this.name = name;
		this.mark = false;
	}
	
	/**
	 * marks the node with the specified value
	 * @param mark
	 */
	
	void setMark(boolean mark){
		this.mark = mark;
	}
	/**
	 * return the value the node has been marked with 
	 * @return boolean 
	 */
	
	boolean getMark() {
		return mark;
	}
	
	/**
	 * returns the name of the node 
	 * @return int 
	 */
	int getName() {
		return name;
	}
	
	
	
	
	
}
