package AVL_Tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import AVL_Tree.Node;

public class Avl
{
    public Node tree = null;
    
    
    public void insert(int value) 
    {
	Node node = new Node(value);
	if(tree == null)
	{
	    tree = node;
	    tree.setRoot(node);
	}
	else 
	{
	    tree = tree.insertSubNode(node);
	}
    }

    
    public Node find(int value) 
    {
	if(tree.data != value)
	{
	    return tree.search(value);
	}
	else return tree;
    }

    
    public void delete(int value) 
    {
	this.tree = tree.remove(value);
    }
    
    public int getSubTreeSize(int value) 
    {
	Node node;
	
	if (tree.data != value)
	{
	    node = tree.search(value);
	}
	else
	{
	    node = tree;
	}
	
	int total = node.aggregateTotalSubTrees(node);
	
	return total;
    }
    
    public static void main(String[] args) 
    {
	Avl avl = new Avl();

	// ===== CONTROL ===== //
	    int amountOfIterations = 1;
	    int maxArrayLength = 30;
	 // ================= //
	 
	 long sumOfTime = 0;
	    
	 for(int i = 0; i < amountOfIterations; i++)
	 {
	     Random rg = new Random();
	     int r = rg.nextInt(maxArrayLength);
	     int[] a = randomArrayGenerator(r + 1); // add 1 so it's never 0
	     
//	     int[] a = {1, 2, 4, 3, 6, 5,};
	     
	     for(int j = 0; j < a.length; j++)
	     {
		 System.out.print(a[j] + ", ");
		 avl.insert(a[j]);
	     }
	     System.out.println("\n");
	     
	     long startTime = System.nanoTime();
	     avl.printTree(avl.tree);
	     System.out.println("\n");
	     System.out.println("DELETING: " + a[a.length/2]);
	     avl.delete(a[a.length/2]);
	     avl.printTree(avl.tree);
	     if(avl.isBalanced(avl.tree)) System.out.println("TREE IS BALANCED");
	     System.out.println("======================= \n");
	     long endTime = System.nanoTime();
	     
	     sumOfTime += endTime - startTime;
	     
//	     System.out.print("\n" + "It took " + (endTime - startTime) + " nanoseconds");
//	     System.out.print("\n");
	 }
	 
	 long averageTime = sumOfTime/amountOfIterations;
	 System.out.println("AVERAGE TIME: " + averageTime);
	
    }
    
    // ============================ UTILITIES ======================== //
    
    public static int[] randomArrayGenerator(int length)
    {
	int[] a = new int[length];
	for (int i = 1; i <= length; i++)
	{
	    a[i-1] = i;
	}
	Random rg = new Random();
	int tmp;
	for (int i = length-1; i > 0; i--)
	{
	    int r = rg.nextInt(i+1);
	    tmp = a[r];
	    a[r] = a[i];
	    a[i] = tmp;
	}
	    return a;
    }
    
    public String toString() 
    {
	StringBuilder string = new StringBuilder("[");
	helpToString(this.tree, string);
	string.append("]");
	return string.toString();
    }
    
    private void helpToString(Node node, StringBuilder string) 
    {
	if (node == null)  return; // Tree is empty, so leave.	
	if (node.getLeftNode() != null) 
	{
	    helpToString(node.getLeftNode(), string);
	    string.append(", ");
	}
	string.append(node.data);
	if (node.getRightNode() != null) 
	{
	    string.append(", ");
	    helpToString(node.getRightNode(), string);
	}
    }
    
    public void printTree(Node tmpRoot) 
    {
        Queue<Node> currentLevel = new LinkedList<Node>();
        Queue<Node> nextLevel = new LinkedList<Node>();

        currentLevel.add(tmpRoot);

        while (!currentLevel.isEmpty()) 
        {
            Iterator<Node> iter = currentLevel.iterator();
            while (iter.hasNext()) 
            {
        	Node currentNode = iter.next();
                if (currentNode.getLeftNode() != null) 
                {
                    nextLevel.add(currentNode.getLeftNode());
                }
                if (currentNode.getRightNode() != null) 
                {
                    nextLevel.add(currentNode.getRightNode());
                }
                System.out.print(currentNode.data + ", ");
            }
            System.out.println();
            currentLevel = nextLevel;
            nextLevel = new LinkedList<Node>();
        }

    }
    
    public boolean isBalanced(Node node) 
    {
        int lh; /* for height of left subtree */
  
        int rh; /* for height of right subtree */
  
        /* If tree is empty then return true */
        if (node == null)
            return true;
  
        /* Get the height of left and right sub trees */
        lh = height(node.getLeftNode());
        rh = height(node.getRightNode());
  
        if (Math.abs(lh - rh) <= 1
                && isBalanced(node.getLeftNode())
                && isBalanced(node.getRightNode())) 
            return true;
  
        /* If we reach here then tree is not height-balanced */
        return false;
    }
    
    public int height(Node node) 
    {
        /* base case tree is empty */
        if (node == null)
            return 0;
  
        /* If tree is not empty then height = 1 + max of left
         height and right heights */
        return 1 + Math.max(height(node.getLeftNode()), height(node.getRightNode()));
    }

}
