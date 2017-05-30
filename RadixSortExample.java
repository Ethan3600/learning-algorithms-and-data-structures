import java.util.Arrays;
import java.util.Random;

public class RadixSortExample 
{
 
    /**
     * O(nk)
     * 
     * Where k = the number of digits
     * 
     * @param a
     * @return
     */
    public static int[] radixSort(int[] a) 
    {
	int max = Arrays.stream(a).max().getAsInt(); // grabs max number from array
	
	for(int dig = 1; max/dig > 0; dig *= 10)
	{
	    a = CountSortExample.countSort(a, dig);
	}
	
 	return a;
     }


    public static void main(String[] args) 
    {
	// int[] a = {31, 131, 2, 14, 635, 96};
	
	// ===== CONTROL ===== //
	    int amountOfIterations = 1;
	    int maxArrayLength = 150;
	 // ================= //
	 
	 long sumOfTime = 0;
	    
	 for(int i = 0; i < amountOfIterations; i++)
	 {
	     Random rg = new Random();
	     int r = rg.nextInt(maxArrayLength);
	     int[] a = randomArrayGenerator(r + 1); // add 1 so it's never 0
	     
	     long startTime = System.nanoTime();
	     a = radixSort(a);
	     long endTime = System.nanoTime();
	     for(int k : a)
	     {
		 System.out.print(k + " \n");
	     }
	     
	     sumOfTime += endTime - startTime;
	     
//		     System.out.print("\n" + "It took " + (endTime - startTime) + " nanoseconds");
//		     System.out.print("\n");
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
