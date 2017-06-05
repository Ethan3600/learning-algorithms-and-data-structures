package Binary_Search_Tree;

import java.util.Random;
import Binary_Search_Tree.Node;

public class Bst implements BstInterface
{
    private Node tree = null;
    
    public void insert(int value) 
    {
	Node node = new Node(value);
	if(tree == null) tree = node;
	else
	{
	    tree.insertSubNode(node);
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
	Bst bst = new Bst();

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
	     
	     for(int j = 0; j < a.length; j++)
	     {
		 bst.insert(a[j]);
	     }
	     
	     long startTime = System.nanoTime();
	     bst.delete(a.length/2);
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

}
