import java.util.Random;


public class QuickSortExample
{
	/**
	 * Worst case: O(n^2)
	 * Average case: O(n lg n)
	 * 
	 * @param arr
	 * @param low
	 * @param high
	 * @return
	 */
	public static int[] quickSort(int[] arr, int low, int high)
	{
		if (low < high)
		{
			int piv = partition(arr, low, high);
			quickSort(arr, low, piv - 1);
			quickSort(arr, piv + 1, high);
		}
		return arr;
	}

	public static void main(String[] args)
	{
//    	int[] arr = {3, 1, 2, 4, 5, 2};
		
	// ===== CONTROL ===== //
	    int amountOfIterations = 1;
	    int maxArrayLength = 11;
	 // ================= //
	 
	 long sumOfTime = 0;
	    
	 for(int i = 0; i < amountOfIterations; i++)
	 {
	     Random rg = new Random();
	     int r = rg.nextInt(maxArrayLength);
	     int[] a = randomArrayGenerator(r + 1); // add 1 so it's never 0
	     
	     long startTime = System.nanoTime();
	     int highIndex = a.length - 1;
	     a = quickSort(a, 0, highIndex);
	     long endTime = System.nanoTime();

	     for(int k : a)
	     {
	    	 System.out.print(k + " ");
	     }
	     
	     sumOfTime += endTime - startTime;
	     
//	     System.out.print("\n" + "It took " + (endTime - startTime) + " nanoseconds");
//	     System.out.print("\n");
	 }
	 
	 long averageTime = sumOfTime/amountOfIterations;
	 System.out.println("AVERAGE TIME: " + averageTime);

	}

 // ============================ UTILITIES ======================== //
	
	
	private static int partition(int[] arr, int low, int high)
	{
		int pivot = arr[high];
		int i = low - 1;
		
		for (int j = low; j < high; j++)
		{
			if(arr[j] <= pivot)
			{
				i++;
				swap(arr, i, j);
			}
		}
		
		swap(arr, i + 1, high);		
		return i + 1;
	}
	
 	private static void swap(int[] arr, int i, int j)
 	{
		int swap = arr[i];
 		arr[i] = arr[j];
 		arr[j] = swap;
 		
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
