package Binary_Search_Tree;

public class Node implements NodeInterface
{
    public int data;
    
    private Node parent = null;
    private Node left 	= null;
    private Node right 	= null;
    
    /**
     * used for caching results
     */
    private Integer subTreeSize;
    
    public Node(int data) 
    {
	this.data = data;
    }

    
    public Node getLeftNode() 
    {
	if(left != null) return left;
	return null;
    }

    
    public Node getRightNode() 
    {
	if(right != null) return right;
	return null;
    }

    
    public Node getParentNode() 
    {
	if(parent != null) return parent;
	return null;
    }
    
    public void insertSubNode(Node node) 
    {	
	if(this.data >= node.data)
	{
	   if(left == null)
	   {
	       left = node;
	       left.parent = this;
	   }
	   else left.insertSubNode(node);
	}
	else if(this.data < node.data)
	{
	    if(right == null)
	    {
		right = node;
		right.parent = this;
	    }
	    else right.insertSubNode(node);
	}
    }

    
    public Node search(int value) 
    {
	try
	{
	    if(this.data != value)
	    {
		if(this.data >= value) return left.search(value);
		else return right.search(value);
	    }
	    else
	    {
		return this;
	    }
	}
	catch (NullPointerException e)
	{
	    System.out.println("Couldn't find value: " + value + " in the Tree");
	    return null;
	}
    }
   
    public int aggregateTotalSubTrees(Node node)
    {
	if(this.subTreeSize != null)
	{
	    return this.subTreeSize.intValue();
	}
	
	int total = 1;
	
	if(node.getLeftNode() != null)
	{
	   total += this.aggregateTotalSubTrees(node.getLeftNode());
	}
	if(node.getRightNode() != null)
	{
	   total += this.aggregateTotalSubTrees(node.getRightNode());
	}
	
	node.subTreeSize = total;
	return total;
    }

    
    
    public Node remove(int value) 
    {
	try
	{
	    if(this.data != value)
	    {
		if(this.data >= value) left.remove(value);
		else right.remove(value);
	    }
	    else
	    {
		if(right != null)
		{
		    this.data = right.data;
		    this.right = right.right;
		}
		else if(left != null)
		{
		    this.data = left.data;
		    left = left.left;
		}
		else
		{
		    if(this.parent.data >= this.data)
		    {
			this.parent.left = null;
		    }
		    else
		    {
			this.parent.right = null;
		    }
		}
	    }
	}
	catch (NullPointerException e)
	{
	    System.out.println("Couldn't delete value: " + value + " in the Tree");
	}
	return this;
    }

}
