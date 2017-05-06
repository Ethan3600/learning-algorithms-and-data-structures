import java.util.Random;

/**
 * A peak's definition is the following:
 * a[x] is a peak iff a[x] >= a[x-1] &&  a[x] >= a[x+1]
 * 
 * There can be more than one peak in an array and 
 * it doesn't need to be sorted
 * 
 * @author Ethan
 */
public class OneDimensionalPeakFinderExample 
{
    
    	/**
    	 * complexity of this algorithm is O(n/2)
    	 * because the worst case scenario would be that
    	 * we cut the problem set in half and iterate through the remaining elements
    	 * 
    	 * @param  a
    	 * @return result peak integer
    	 */
    	public static int oneDimPeakFinder(int[] a)
    	{
    	    int result = -1;
    	    int len = a.length - 1; // subtract one because array starts at 0
    	    int mid = Math.round(len/2);
    	    
    	    if(mid == 0 || mid == a.length - 1)
    	    {
    		result = a[mid];
    	    }
    	    else if (a[mid] <= a[mid - 1])
    	    {
    		// iterate to the left to find peak
    		result = a[mid];
    		for(int i = 0; i < mid; i++)
    		{
    		    result = (result >= a[i + 1]) ? result : a[i + 1];
    		}
    	    }
    	    else if (a[mid] <= a[mid + 1])
    	    {
    		// iterate to the right to find peak
    		result = a[mid];
    		for(int i = mid; i < len; i++)
    		{
    		    result = (result >= a[i + 1]) ? result : a[i + 1];
    		}
    	    }
    	    else 
    	    {
    		result = a[mid];
    	    }
    	    return result;
    	}
    	
    	/**
    	 * This implementation's complexity is O(log n) because
    	 * for every iteration we divide the problem set in half
    	 * 
    	 *   this is the more efficient algorithm, but may produce different
    	 *   results based on the problem set
    	 *   
    	 * @param a
    	 * @return Int result
    	 */
    	public static int oneDimPeakFinder(int[] a, boolean binarySearch)
    	{
    	    if(binarySearch == false)
    	    {
    		return oneDimPeakFinder(a);
    	    }
    	    int result = -1;
    	    int lowIndex = 0;
    	    int highIndex = a.length - 1; // subtract one because array starts at 0
    	    while (lowIndex <= highIndex)
    	    {
    		int mid = Math.round((highIndex + lowIndex)/2);
    		if(mid == 0 || mid == a.length - 1)
    		{
    		    result = a[mid];
    		    break;
    		}
    		else if(a[mid] <= a[mid-1])
    		{
    		    highIndex = mid -1;
    		}
    		else if(a[mid] <= a[mid+1])
    		{
    		    lowIndex = mid + 1;
    		}
    		else
    		{
    		    result = a[mid];
    		    break;
    		}
    	    }
    	    return result;    	   
    	}

    	/**
    	 * Results show that there is an inverse relation to the size of the input
    	 * and to the amount of matches between the two implementations
    	 * 
    	 * It also shows that for an array with a length of 20 elements,
    	 * between the two implementations (n/2 and log n) the results match ~61.8506% 
    	 * of the time
    	 * 
    	 * Decreasing the array length will result in a higher percentage
    	 * 
    	 * @param args
    	 */
	public static void main(String[] args) 
	{
	    //int[] a = {1,45,200,3,3,2,6,56,3,23,2,12,12,6}; example set
	    
	    // ===== CONTROL ITERATIONS HERE ===== //
	    int amountOfIterations = 1000000;
	    // =================================== //
	    int numOfMatches = 0;
	    
	    for(int i = 0; i < amountOfIterations; i++)
	    {
		Random rg = new Random();
		int r = rg.nextInt(20);
		int[] a = randomArrayGenerator(r + 1); // add 1 so it's never 0
		
		int result = oneDimPeakFinder(a);
		System.out.println("1st Implementation: "+result);
		int imp2result = oneDimPeakFinder(a, true);
	    	System.out.println("2nd Implementation: "+imp2result);
	    	System.out.println(" ");
	    	
	    	if(result == imp2result)
	    	{
	    	    numOfMatches++;
	    	}
	    }
	    float percentMatch = ((float) numOfMatches / amountOfIterations) * 100;
	    System.out.println("Percent matching answers: " + percentMatch +"%");
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
