package AVL_Tree;

public class Node
{
    public int data;
    
    private static Node root 	= null;
    private Node parent		= null;
    private Node left 		= null;
    private Node right 		= null;
    
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
    
    public void setRoot(Node node)
    {
	if(Node.root == null)
	{
	    Node.root = node;
	}
	return;
    }
    
    public Node insertSubNode(Node node) 
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
	balance(this);
	return Node.root;
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
	    balance(this);
	}
	catch (NullPointerException e)
	{
	    System.out.println("Couldn't delete value: " + value + " in the Tree");
	}
	return this;
    }
    
    /**
     * Given a node with two subtrees
     * 
     * If the height of the left tree - the height of the right tree > 1 || < -1
     * we need to rotate the Node to balance the tree out
     * 
     * @param node
     */
    public void balance(Node node)
    {
	if(node == null) return;
	int diff = balanceFactor(node);
	if(diff > 1 || diff < -1)
	{
	    // Left Left Case
	    if (diff > 1 && balanceFactor(node.left) > 0) rightRotate(node);
	 
	    // Right Right Case
	    if (diff < -1 && balanceFactor(node.right) < 0) leftRotate(node);
	 
	    // Left Right Case
	    if (diff > 1 && balanceFactor(node.left) < 0) 
	    {
		leftRotate(node.left);
		rightRotate(node);
	    }
	 
	    // Right Left Case
	    if (diff < -1 && balanceFactor(node.right) > 0) 
	    {
		rightRotate(node.right);
		leftRotate(node);
	    }
	}
    }
    
    
    // ============================ UTILITIES ======================== //
    
    /**
     * In a left rotation we assume x is the parent node and y is the right node
     * 
     * 		X
     * 		 \
     * 		  Y
     * 
     * We want to change this structure to:
     * 
     * 		Y
     * 	       /
     * 	      X
     * 
     * Taking into account all subtrees where y.left (in the original structure) needs to be in x.right
     * in the new structure
     * 
     * O(1)
     * @param Node x
     */
    private Node leftRotate(Node x)
    {
	Node y = x.right;
	x.right = y.left;
	
	if(y.left != null)
	{
	    y.left.parent = x;
	}
	y.parent = x.parent;
	
	if(x.parent == null)
	{
	    Node.root = y;
	}
	else if(x == x.parent.left)
	{
	    x.parent.left = y;
	}
	else x.parent.right = y;
	y.left = x;
	x.parent = y;
	return this;
    }
    
    private Node rightRotate(Node x)
    {
	Node y = x.left;
	x.left = y.right;
	
	if(y.right != null)
	{
	    y.right.parent = x;
	}
	y.parent = x.parent;
	
	if(x.parent == null)
	{
	    Node.root = y;
	}
	else if(x == x.parent.left)
	{
	    x.parent.left = y;
	}
	else x.parent.right = y;
	y.right = x;
	x.parent = y;
	return this;
    }
    
    private int calulateHeightOfNode(Node node)
    {
	if(node == null) return 0;
	
	Integer leftNodeHeight = 1;
	Integer rightNodeHeight = 1;
	
	if(node.getLeftNode() != null)
	{
	    leftNodeHeight += node.calulateHeightOfNode(node.getLeftNode());	   
	}
	if(node.getRightNode() != null)
	{
	    rightNodeHeight += node.calulateHeightOfNode(node.getRightNode());
	}
	
	if(leftNodeHeight >= rightNodeHeight) return leftNodeHeight;
	else return rightNodeHeight;
    }
    
    private int balanceFactor(Node node)
    {
	if(node == null) return 0;
	int leftHeight 	= node.left != null ? calulateHeightOfNode(node.left) : 0;
	int rightHeight = node.right != null ? calulateHeightOfNode(node.right) : 0;
	return leftHeight - rightHeight;
    }

}
