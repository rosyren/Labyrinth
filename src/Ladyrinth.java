/**
 * 
 * @author Rosy Ren 
 * CS2210A 2020
 * Assignment 5
 * class that represents the Ladyrinth 
 *
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

public class Ladyrinth {
	private Graph Ladyrinth;
	private int width;
	private int length;
	private int[] keys;
	private Node entrance, exit;
	
	/**
	 * constructor that reads the file and builds the graph representing the ladyrinth 
	 * throws an exception if file doesn't exist or format is wrong 
	 * @param inputFile
	 * @throws GraphException
	 */
	
	public Ladyrinth(String inputFile) throws GraphException {
		
		try{
			File file = new File(inputFile);			
			BufferedReader reader = new BufferedReader(new FileReader(file));
			reader.readLine(); // skip first value for SCALE
			
			width = Integer.parseInt(reader.readLine());
			length = Integer.parseInt(reader.readLine());
			
			keys = new int[10];
			String strLine;
			
			strLine = reader.readLine();
	
			String split[] = strLine.split(" ");
			
			// reads in each of the key values into an array 
			for (int i = 0; i < 10; i++) {
				keys[i] = Integer.parseInt(split[i]);
			}	
			
			Ladyrinth = new Graph(length * width);
		
			// Strings to iterate through path text
			String curr = reader.readLine();
			String next = reader.readLine();
			String nextnext = reader.readLine();
			
			int numNodes = -1;		// initialize to -1 for empty stack structure
			int edgeT; 				// assigned value through edgeType()
			
			// Nodes to iterate through path text 
			Node currNode;
			Node prevNode;
			Node nextNode;
			
			char ch;
			
			// while there's still characters within the line 
			while (curr != null) {
				for(int i=0; i<curr.length(); i++) {
					ch = curr.charAt(i);
					char pre_ch;
					char post_ch;
					
					// if it's an entrance
					if (ch == 's') {
						numNodes++;
						currNode = Ladyrinth.getNode(numNodes);
						try {				
							if(next.charAt(i) != 'w' && nextnext.charAt(i) == 'i') {
								edgeT = edgeType(next.charAt(i));
								nextNode = Ladyrinth.getNode(numNodes + width);
								String EntLabel = "entrance";
								entrance = currNode;
								Ladyrinth.insertEdge(currNode, nextNode, edgeT, EntLabel);
							}
						}
						catch(Exception e) {
							continue;	// go to next row for iteration
						}	
					}
					
					// if it's an exit 
					else if (ch == 'x') {
						numNodes++;
						currNode = Ladyrinth.getNode(numNodes);
						try {				
							if(next.charAt(i) != 'w' && nextnext.charAt(i) == 'i') {
								edgeT = edgeType(next.charAt(i));
								nextNode = Ladyrinth.getNode(numNodes + width);
								String EntLabel = "exit";
								exit = currNode;
								Ladyrinth.insertEdge(currNode, nextNode, edgeT, EntLabel);
							}
						}
						catch(Exception e) {
							continue;	// go to next row for iteration
						}	
					}
					
					// if it's a room 
					else if(ch == 'i') {		// dead end or another room, add to node count 
						numNodes++;
						currNode = Ladyrinth.getNode(numNodes);
						try {				
							if(next.charAt(i) != 'w' && nextnext.charAt(i) == 'i') {
								edgeT = edgeType(next.charAt(i));
								nextNode = Ladyrinth.getNode(numNodes + width);
								Ladyrinth.insertEdge(currNode, nextNode, edgeT);
							}
						}
						catch(Exception e) {
							continue;	// go to next row for iteration
						}	
					}
					// char analysis 
					else {
						try {
							pre_ch = curr.charAt(i-1);
						}catch (IndexOutOfBoundsException e){
							pre_ch = ' ';
						}
					
						try {
							post_ch = curr.charAt(i+1);
						}catch(IndexOutOfBoundsException e) {
							post_ch = ' ';
						}
						
						edgeT = edgeType(ch);
						if(edgeT == 0)	continue;
						
						if(pre_ch == 'i') {
							prevNode = Ladyrinth.getNode(numNodes);
						}else {
							continue;
						}
						
						if(post_ch == 'i') {
							nextNode = Ladyrinth.getNode(numNodes + 1);
						}else {
							continue;
						}
						
						Ladyrinth.insertEdge(prevNode, nextNode, edgeT);
					}
				}
				// iterate for next line 
				curr = next;
				next = nextnext;
				nextnext = reader.readLine();
			}
			reader.close();
		}
		
		catch(IOException e) {
			throw new GraphException("error");
		}
	}
	
		

/**
 * helper method to return the edgeType (especially in relation to the type of key)
 * @param ch
 * @return int
 */
	private int edgeType(char ch) {
		if(ch == 'c')
			return 0;
		
		else{
			int type = Character.getNumericValue(ch);
		
			return type;
		}
	
	}
	
	/**
	 * returns a reference to the Graph object representing the Ladyrinth 
	 * @return Graph
	 */
	
	public Graph getGraph() {
		return Ladyrinth; 
	}
	/**
	 * returns a java iterator containing the nodes of the path of the entrance to the exit of the Ladyrinth 
	 * if no path exists, the method retuns null
	 * @return Iterator 
	 * @throws GraphException
	 */
	public Iterator solve() throws GraphException {
		
		// Initialize stack to store path elements
				Stack<Node> path = new Stack<Node>();
				Node currNode = getGraph().getNode(0);
				path.push(currNode);
				currNode.setMark(true);
				
				if (getEntrance() == getExit()) return path.iterator();		// base case
				
				// All edges for the current node
				Iterator<Edge> allEdges = Ladyrinth.incidentEdges(currNode);
				
				while(allEdges.hasNext()) {
					Edge nextEdge = allEdges.next();
					Node nextNode = nextEdge.secondEndpoint();
					if(nextNode.equals(currNode)) {
						nextNode = nextEdge.firstEndpoint();
					}
					
					// checks for the key/door type
					int door = nextEdge.getType();
					
						int keyNum = door;
						boolean found = false;
	
						for (int i = door; i < keys.length; i++) {
							if (keys[i] != 0) {
								found = true;
								keyNum++;
								break;
							}
						}
						
						// if there is a key 
						if (found == true) {
							keys[keyNum] = keys[keyNum] -1;
							Iterator<Edge> edgePath = solve();
							currNode.setMark(true);
							if (path != null) {
								return edgePath;
							}
							else {
								keys[keyNum] = keys[keyNum] +1;
							}
						}
		
				// unset the mark 
				currNode.setMark(false);
				path.pop();
				return null;

		
				}
				return allEdges;
	}
	
	/**
	 * helper method to retrieve the entrance
	 * @return Node
	 */
	private Node getEntrance() {
		return entrance; 
		
	}
	
	/**
	 * helper method to retrieve the exit 
	 * @return Node
	 */
	private Node getExit() {
		return exit;
	}
	
		
}
