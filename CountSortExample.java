import java.util.Arrays;
import java.util.Random;

public class CountSortExample 
{
    /**
     * O(n)
     * @param a
     * @return int[] result
     */
    public static int[] countSort(int[] a) 
    {
	int max = Arrays.stream(a).max().getAsInt(); // grabs max number from array
	
	int[] aux = new int[max + 1];
	
	for(int i = 0; i < a.length; i++)
	{
	    aux[a[i]] = aux[a[i]] + 1;
	}
	
	for(int j = 1; j < aux.length; j++)
	{
	    aux[j] = aux[j] + aux[j-1];
	}
	
	int[] result = new int[a.length];
	for(int k = 0; k < a.length; k++)
	{
	    result[aux[a[k]]-1] = a[k];
	    aux[a[k]]--;
	}
	
	return result;
    }
    
    public static int[] countSort(int[] a, int dig) 
    {
	int max = getMaxNthDigit(a, dig);
	
	int[] aux = new int[max + 1];
	
	for(int i = 0; i < a.length; i++)
	{
	    aux[(a[i] / dig) % 10] = aux[(a[i] / dig) % 10] + 1;
	}
	
	for(int j = 1; j < aux.length; j++)
	{
	    aux[j] = aux[j] + aux[j-1];
	}
	
	int[] result = new int[a.length];
	for(int k = a.length - 1; k >= 0; k--)
	{
	    result[aux[(a[k] / dig) % 10]-1] = a[k];
	    aux[(a[k] / dig) % 10]--;
	}
	
	return result;
    }

    public static void main(String[] args) 
    {
    	//int[] a = {3, 1, 2, 4, 5, 6};
	
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
	     a = countSort(a);
	     long endTime = System.nanoTime();

	     for(int k : a)
	     {
		 System.out.print(k + " \n");
	     }
	     
	     sumOfTime += endTime - startTime;
	     
//	     System.out.print("\n" + "It took " + (endTime - startTime) + " nanoseconds");
//	     System.out.print("\n");
	 }
	 
	 long averageTime = sumOfTime/amountOfIterations;
	 System.out.println("AVERAGE TIME: " + averageTime);

    }
    
    // ============================ UTILITIES ======================== //

    private static int getMaxNthDigit(int[] a, int dig) 
    {
	int max = -1;
	for(int i = 0; i < a.length; i++)
	{
	    if(max < (a[i] / dig) % 10)
	    {
		max = (a[i] / dig) % 10; 
	    }
	}
	return max;
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
