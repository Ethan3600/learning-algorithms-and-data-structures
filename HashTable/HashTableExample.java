package HashTable;

/**
 * This implementation of a hashtable uses open addressing to handle collisions
 * which probes the array for an empty slot (null)
 *
 * This implementation also includes logic for table doubling and halving
 * 		The table will double if the largest hash is >= array.length - 2
 * 		and the table will halve when the largest hash is < array.length / 4
 * 			The reason for this is so we don't constantly resize the array
 * 
 * The hash algorithm uses a simple division method h(k) = k % m -> array.lenth
 * Other (better) methods include:
 * 		Multiplication method 		h(k) = ( (a*k) % 2^w ) >> (w-r)
 * 		Universal hashing method 	h(k) = ( (ak+b) % PRIME_NUM ) % m
 * 
 * Worst case for get, put, remove methods are: O(n)
 * Average (theta) case for get, put, remove methods are θ(1)
 * 		Which is why hashtables are very good
 */
public class HashTableExample
{
	/**
	 * Container structure for values in the array
	 */
	private class Node
	{
		public String key;
		public String value;
		
		Node(String key, String value)
		{
			this.key = key;
			this.value = value;
		}
	}
	
	private Node[] arr;
	
	HashTableExample()
	{
		arr = new Node[8]; // initialize to size 8
	}
	
	/**
	 * Retrieves value from key 
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key)
	{
		for(int i = 0; i < arr.length; i++)
		{
			if(hash(key, i) > size())
				return null;
			Node n = arr[hash(key, i)];
			if(n != null)
			{
				if(n.key != key)
					continue;
				else
					return n.value;
			}
			else
			{
				continue;
			}
		}
		return null;
	}
	
	/**
	 * Inserts key value pair
	 * 
	 * @param key
	 * @param val
	 * @throws Exception
	 */
	public void put(String key, String val) throws Exception
	{
		if(this.contains(key))
			throw new Exception("Key is already present in hash table");
		Node n = new Node(key, val);
		int iterations = 0;
		while(arr[hash(key, iterations)] != null)
		{
			iterations++;
		}
		arr[hash(key, iterations)] = n;
		if(hash(key, iterations) >= arr.length - 2)
			this.tableDouble();
	}
	
	public void remove(String key) throws Exception
	{
		if(!this.contains(key))
			throw new Exception("key doesn't exist in hashtable");
		
		for(int i = 0; i < arr.length; i++)
		{
			Node n = arr[hash(key, i)];
			if(n != null)
			{
				if(n.key != key)
					continue;
				else
					arr[hash(key, i)] = null;
					if(size() <= arr.length / 4)
						this.tableHalf();
					return;
			}
			else
			{
				continue;
			}
		}
	}
	
	/**
	 * Checks hashtable to see if key exists
	 * 
	 * @param key
	 * @return
	 */
	public boolean contains(String key)
	{
		if(arr.length > hash(key) 
				&& this.get(key) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Division method hashing for simplicity
	 * 
	 * @param val
	 * @param iter used for probing
	 * @return
	 */
	private int hash(int val, int iter)
	{
		int hash = (val % arr.length) + iter;
		return hash;
	}
	
	/**
	 * Converts string into int and hashes
	 * @param val
	 * @return
	 */
	private int hash(String val, int iter)
	{
		int stringNumRepresentation = 0;
		
		for(char i : val.toCharArray())
		{
			stringNumRepresentation += (int) i;
		}
		return hash(stringNumRepresentation, iter);
	}
	
	private int hash(String val)
	{
		int stringNumRepresentation = 0;
		
		for(char i : val.toCharArray())
		{
			stringNumRepresentation += (int) i;
		}
		return hash(stringNumRepresentation, 0);
	}
	
	/**
	 * Doubles table size, copy values, rehashes keys into
	 * a new array
	 */
	private void tableDouble()
	{
		Node[] oldArr = arr;
		arr = new Node[arr.length * 2];
		for(Node s : oldArr)
		{
			if(s != null)
				try
				{
					put(s.key, s.value);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * divides table size, copy values, and rehashes keys into
	 * a new array
	 */
	private void tableHalf()
	{
		Node[] oldArr = arr;
		arr = new Node[arr.length / 2];
		int index = 0;
		for(Node s : oldArr)
		{
			if(index > arr.length)
				return;
			if(s != null)
				try
				{
					put(s.key, s.value);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			index++;
		}
	}
	
	/**
	 * returns the highest index that doesn't
	 * have a null value
	 * 
	 * @return
	 */
	private int size()
	{
		for (int i = arr.length - 1; i > 0; i--)
		{
			if(arr[i] != null)
				return i;
		}
		return 0;
	}
	
	
	public static void main(String[] args)
	{
		try
		{
			HashTableExample ht = new HashTableExample(); 
			ht.put("one", "some value");
			ht.put("neo", "some other value");
			ht.put("eno", "another collision avoided");
			ht.put("somethiasdfgn", "asdf");
			ht.put("somethigna", "asdf");
			ht.put("sasdfomethign", "asdf");
			ht.put("sometasdfahign", "asdf");
			ht.put("somethigasdfn", "asdf");
			ht.put("sasdfasdomethign", "asdf");
			
			ht.remove("sasdfasdomethign");
			ht.remove("somethiasdfgn");
			ht.remove("somethigna");
			ht.remove("sasdfomethign");
			ht.remove("sometasdfahign");
			ht.remove("somethigasdfn");
			String test = ht.get("eno");
			System.out.println(test);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
}
