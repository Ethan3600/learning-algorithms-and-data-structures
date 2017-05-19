package Binary_Search_Tree;

public interface BstInterface
{
    public void insert(int value);
    
    public Node find(int value);
    
    public void delete(int value);
    
    public int getSubTreeSize(int value);
    
//    public void balance();
}
