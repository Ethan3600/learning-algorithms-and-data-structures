import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class BreadthFirstSearchExample
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
	
	public static void Bfs(Node root)
	{
		Queue<Node> queue = new LinkedList<Node>(); 
		root.visited = true;
		queue.add(root);
		
		while(!queue.isEmpty())
		{
			Node n = queue.remove();
			n.visited = true;
			
			for(Node r : n.adjacent)
			{
				if(r.visited == false)
				{
					r.visited = true;
					r.parent = n; 
					queue.add(r);
				}
			}
		}
	}
}
