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
    	int[] arr = {3, 1, 2, 4, 5, 6};
    	arr = insertionSort(arr);
    	System.out.println("done");
    }
}
