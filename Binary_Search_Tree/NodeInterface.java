package Binary_Search_Tree;

public interface NodeInterface 
{  
    public void insertSubNode(Node node);
    
    public Node getLeftNode();
    
    public Node getRightNode();
    
    public Node getParentNode();
    
    public Node search(int value);
    
    public int aggregateTotalSubTrees(Node node);
    
    public Node remove(int value);
}
