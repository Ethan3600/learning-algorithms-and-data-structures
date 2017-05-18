import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * O(n lg n)
 * 
 * A heap sort is an array visualized as a binary tree
 * This algorithm will be in terms of a "Max Heap" which means
 * given a root node, the two child nodes will have a value less than it's parent
 * 
 * @author Ethan
 *
 */
public class HeapsortExample 
{
    private List<Integer> A;
    private ArrayList<Integer> sortedArray;
    
    public HeapsortExample(List<Integer> arr)
    {
	this.A = arr;
	this.sortedArray = new ArrayList<Integer>(arr.size());
    }
    
    /**
     * O(n lg n)
     * @return
     */
    public ArrayList<Integer> heapSort()
    {
	buildMaxHeap();
	for(int i = this.A.size() - 1; i > 0; i--)
	{
	    exchange(0, i);
	    popOffHeapAppendToSortedArray(A.get(i));
	    maxHeapify(0);
	}
	maxHeapify(0);
	popOffHeapAppendToSortedArray(A.get(0));
	return sortedArray;
    }
    
    /**
     * O(n)
     */
    public void buildMaxHeap()
    {
	for(int i = (int) Math.floor(this.A.size()/2); i >= 0; i--)
	{
	    maxHeapify(i);
	}
	return;
    }
    
    /**
     * O(lg n)
     * @param rootIndex
     */
    private void maxHeapify(int rootIndex)
    {
	int leftNode;
	int rightNode;
	
	int largerValuesIndex = rootIndex;
	
	try
	{
	    leftNode	= rootIndex*2 + 1;
	    rightNode	= leftNode + 1;
	    
	    if(indexExistsInArray(leftNode))
	    {
		if(this.A.get(rootIndex) < this.A.get(leftNode))
		{
		    largerValuesIndex = leftNode;
		}
	    }
	    if(indexExistsInArray(rightNode))
	    {
		if(this.A.get(rightNode) > this.A.get(largerValuesIndex))
		{
		    largerValuesIndex = rightNode; 
		}
	    }
	    
	    if(largerValuesIndex != rootIndex)
	    {
    	    	exchange(rootIndex, largerValuesIndex);
    	    	maxHeapify(largerValuesIndex);
	    }
	}
	catch(Exception e)
	{
	    return;
	}
	return;
    }
    
    
    public static void main(String[] args) 
    {
	//ArrayList<Integer> arr = {1, 2, 3, 4, 5, 6, 7};
	
	// ===== CONTROL ===== //
	    int amountOfIterations = 1000000;
	    int maxArrayLength = 10000;
	 // ================= //
	 
	 long sumOfTime = 0;
	    
	 for(int i = 0; i < amountOfIterations; i++)
	 {
	     Random rg = new Random();
	     int r = rg.nextInt(maxArrayLength);
	     int[] a = randomArrayGenerator(r + 1); // add 1 so it's never 0
	     
	     List<Integer> arr = new ArrayList<Integer>();
	     for (int index = 0; index < a.length; index++)
	     {
		 arr.add(a[index]);
	     }
	     // Construct
	     HeapsortExample hs = new HeapsortExample(arr);
	     
	     long startTime = System.nanoTime();
	     ArrayList<Integer> result = hs.heapSort();
	     long endTime = System.nanoTime();

	     for(int k : result)
	     {
		 //System.out.print(k + " ");
	     }
	     
	     sumOfTime += endTime - startTime;
	     
//	     System.out.print("\n" + "It took " + (endTime - startTime) + " nanoseconds");
//	     System.out.print("\n");
	 }
	 long averageTime = sumOfTime/amountOfIterations;
	 System.out.println("AVERAGE TIME: " + averageTime);
    }
    // ============================ UTILITIES ======================== //
    
    private void exchange(int e1, int e2)
    {
	int valueOfE1 = this.A.get(e1);
	this.A.set(e1, this.A.get(e2));
	this.A.set(e2, valueOfE1);
    }
    
    private boolean indexExistsInArray(int index)
    {
	return index >= 0 && index < this.A.size();
    }
    
    private void popOffHeapAppendToSortedArray(int val)
    {
	this.sortedArray.add(val);
	this.A.remove(this.A.size() - 1);
    }
    
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

}
