import java.util.Random;
/**
 * Merge sort recursively sorts a given set of elements
 * 
 * 			[4,3,6,78,65,9,1,5]
 * 			/		\
 * 		[4,3,6,78]		[65,9,1,5]
 * 		/	\		/	\
 * 	    [4,3]	[6,78]	     [65,9]	[1,5]
 * 	    /	\	/    \	     /	  \	/   \
 * 	   [4]  [3]    [6]   [78]   [65]  [9]  [1]   [5]
 * 	    \   /       \    /        \    /     \   /
 * 	    [3,4]	[6,78]	      [9,65]     [1,5]
 * 		\       /		   \     /
 * 		[3,4,6,78]		  [1,5,9,65]
 * 			 \                /
 * 			  \              /
 * 			   \            /
 * 			 [1,3,4,5,6,9,65,78]
 * 
 * O(n lg n)
 * 
 * @author Ethan
 *
 */
public class MergesortExample 
{

    public static int[] mergeSort(int[] a)
    {
	if(a.length <= 1)
	{
	    return a;
	}
	
	int arrLength = a.length; 
	int mid = (arrLength/2);
	
	int[] left = new int[mid];
	int[] right = new int[arrLength - mid];
	
	for (int q = 0; q < mid; q++)
	{
	    left[q] = a[q];
	}
	
	int r = 0;
	for (int p = mid; p < arrLength; p++)
	{
	    if(r < right.length)
	    {
		right[r] = a[p];
		r++;
	    }    
	}
	
	int[] mergedArray = merge(mergeSort(left), mergeSort(right), arrLength);
	
	return mergedArray;
    }
    
    public static void main(String[] args) {
	//int a[] = {23, 35, 23, 34234, 1, 34, 4, 25, 12, 2342, 4353, 34, 31, 2, 3, 234, 46, 75};
	
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
	     
	     long startTime = System.nanoTime();
	     int result[] = mergeSort(a);
	     long endTime = System.nanoTime();

	     for(int k : result)
	     {
//		 System.out.print(k + " ");
	     }
	     
	     sumOfTime += endTime - startTime;
	     
//	     System.out.print("\n" + "It took " + (endTime - startTime) + " nanoseconds");
//	     System.out.print("\n");
	 }
	 
	 long averageTime = sumOfTime/amountOfIterations;
	 System.out.println("AVERAGE TIME: " + averageTime);
	
    }
    
    // ========================= UTILITIES ========================= //
    
    public static int[] merge(int[] left, int[] right, int arrLength)
    {
	int i = 0; // left array pointer
	int j = 0; // right array pointer
	
	int mergedArray[] = new int[arrLength];
	
	for (int k = 0; k <= arrLength; k++)
	{
	    if(i < left.length)
	    {
		if(j >= right.length)
		{
		    mergedArray[k] = left[i];
		    i++;
		}
		else if(left[i] <= right[j])
		{
		    mergedArray[k] = left[i];
		    i++;
		}
		else
		{
		    mergedArray[k] = right[j];
		    j++;
		}
		
	    }
	    else if(j < right.length)
	    {
		if (i >= left.length)
		{
		    mergedArray[k] = right[j];
		    j++;
		}
		else if(right[j] <= left[i])
		{
		    mergedArray[k] = right[j];
		    j++;
		}
		else
		{
		    mergedArray[k] = left[i];
		    i++;
		}
		
	    }
	}
	return mergedArray;
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
