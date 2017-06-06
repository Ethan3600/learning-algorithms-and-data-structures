import java.util.ArrayList;
import java.util.LinkedList;

public class DepthFirstSearchExample
{
	/**
	 * Generic class for structure that contains nodes
	 */
	private class Graph
	{
		public ArrayList<Node> list;
	}
	
	/**
	 * Generic class for node
	 */
	private class Node
	{
		public boolean visited = false;
		public char key;
		/**
		 * contains all adjacent Nodes in the graph
		 */
		public LinkedList<Node> adjacent;
		public Node parent; 
	}
	
	public static void Dfs(Node root)
	{
		if(root == null) return;
		root.visited = true;
		for(Node n : root.adjacent)
		{
			if(root.visited == false)
			{
				Dfs(n);
			}
		}
		
	}
	
}
