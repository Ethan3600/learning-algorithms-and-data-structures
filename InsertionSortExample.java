import java.util.Random;

public class InsertionSortExample 
{
	/**
	 * O(n^2)
	 * 
	 * Sorts the array in accenting order
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] insertionSort(int[] arr)
	{
		for (int j = 1; j < arr.length; j++)
		{
			int key = arr[j];
			// insert arr[j] into sorted sequence
			int i = j - 1;
			while(i >= 0 && arr[i] > key)
			{
				arr[i+1] = arr[i];
				i = i - 1;
			}
			arr[i+1] = key;
		}
		return arr;
	}
	
    public static void main(String[] args) 
    {
//    	int[] arr = {3, 1, 2, 4, 5, 6};
	
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
	     a = insertionSort(a);
	     long endTime = System.nanoTime();

	     for(int k : a)
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
}
