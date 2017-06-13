import java.util.Hashtable;


public class FibonacciDynamicProgrammingExample
{
	/**
	 *  Memoization -- caches results of pre-calculated subproblems
	 */
	public static Hashtable<Integer, Integer> memo = new Hashtable<Integer, Integer>();

	/**
	 * computes nth fibonacci number using "careful recursion" aka memoization
	 */
	public static int fibonacci(int n)
	{	
		// if the fibonacci number was calculated before, return it 
		if(memo.containsKey(n))
			return memo.get(n);
		
		// the nth fib number to return
		int f;
		
		if(n <= 2)
			f = 1;
		else
			f = fibonacci(n-1) + fibonacci(n-2);
		
		memo.put(n, f);
		return f;
	}
	
	/**
	 * Although the fibonacci() function and this function 
	 * have the same complexity, this is considered more efficient
	 * because a recursive function will use progressively more memory on
	 * the stack
	 */
	public static int bottomUpFib(int n)
	{
		// the nth fib number to return
		int f = -999;
		
		for (int i = 1; i <= n; i++)
		{	
			if(i <= 2)
				f = 1;
			else
				f = memo.get(i-1) + memo.get(i-2);
			
			memo.put(i, f);
		}
		return f;
	}
	
	public static void main(String[] args)
	{
		int nthFib = 10;
		
		int result = fibonacci(nthFib);
		memo.clear(); // "clear cache"
		int bottomUpResult = bottomUpFib(nthFib);
		
		System.out.println(result);
		System.out.println(bottomUpResult);
	}

}
