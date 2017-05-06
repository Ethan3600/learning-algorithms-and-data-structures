/**
 * A two dimensional peak if defined as follows:
 * Given a two dimensional array:
 * 
 * [0, a, 0]
 * [b, c, d]
 * [0, e, 0]
 * 
 * if c is greater than a,b,d,e:
 * 	then "c" is a two dimensional peak
 * 
 * @author Ethan
 *
 */
public class TwoDimensionalPeakFinderExample {

    /**
     * the complexity of this is O(y log x)
     * 
     * where y is the amount of columns
     * and x is the length of the rows
     * 
     * @param a
     * @return peak
     */
    public static int twoDimPeakFinder(int[][] a)
    {
	int colLength = a.length;
	// By dividing the column length in half, we are going to iterate only half of the
	// 2D array because we accounted for the highest value in the middle
	// column, making the algorithm O(y log x)
	int x = Math.round(colLength/2);
	// worst case scenario is iterate through the length of the entire column: y
	int y = getLargestIndexInColumn(x, a);
	// worst case scenario is iterate through the entire 2 dimensional array
	int peak = checkPeak(x, y, a);
	
	return peak;
    }
    
    public static void main(String[] args) 
    {
	// sample input
	int[][] a = {
		{23, 12, 356, 112, 23},
		{84, 2000, 1000, 48, 12},
		{9, 51, 76, 2, 2222},
	};
	
	int peak = twoDimPeakFinder(a);
	System.out.println(peak);

    }
    
    // ================ UTILITIES =================== //
    /**
     * O(n)
     * 
     * @param col
     * @param a
     * @return index
     */
    public static int getLargestIndexInColumn(int col, int[][] a)
    {
	int max = a[0][col];
	int index = -1;
	for (int i = 0; i < a.length; i++)
	{
	    if (max < a[i][col])
	    {
		max = a[i][col];
		index = i;
	    }
	}
	return index;
    }
    /**
     * O(x*y)
     * 
     * recursively iterates through the 2 dimensional array until
     * it fulfills the peak (hill) requirement
     * 
     * @param x
     * @param y
     * @param a
     * @return peak
     */
    public static int checkPeak(int x, int y, int[][] a)
    {
	int result;
	
	if(y+1 <= a[x].length-1 && a[x][y] < a[x][y+1])
	{
	    y++;
	   return result = checkPeak(x, y, a);
	}
	else if(y-1 >= 0 && a[x][y] < a[x][y-1])
	{
	    y--;
	    return result = checkPeak(x, y, a);
	}
	else if(x+1 <= a.length-1 && a[x][y] < a[x+1][y])
	{
	    x++;
	    return result = checkPeak(x, y, a);
	}
	else if(x-1 >= 0 && a[x][y] < a[x-1][y])
	{
	    x--;
	    return result = checkPeak(x, y, a);
	}
	else
	{
	    result = a[x][y]; // peak
	    return result;
	}
    }

}
