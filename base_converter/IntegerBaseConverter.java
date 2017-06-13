package base_converter;

import java.util.ArrayList;
import java.util.Hashtable;


public class IntegerBaseConverter
{
	public static Hashtable<Integer, String> hexConv = new Hashtable<Integer, String>();    
	static {
		hexConv.put(1, "1");
		hexConv.put(2, "2");
		hexConv.put(3, "3");
		hexConv.put(4, "4");
		hexConv.put(5, "5");
		hexConv.put(6, "6");
		hexConv.put(7, "7");
		hexConv.put(8, "8");
		hexConv.put(9, "9");
		hexConv.put(10,"A");
		hexConv.put(11, "B");
		hexConv.put(12, "C");
		hexConv.put(13, "D");
		hexConv.put(14, "E");
		hexConv.put(15, "F");
	}
	
	public static String convertIntToHexBase(int num)
	{
		ArrayList<String> convertedNum = new ArrayList<String>();
		
		String converter = String.valueOf(num);
		
		while(num > 16 - 1)
		{
			String remainder = (hexConv.get(Integer.valueOf(converter) % 16) != null) ? 
					(hexConv.get(Integer.valueOf(converter) % 16)) :
					(String.valueOf(Integer.valueOf(converter) % 16));
			
			converter = ((hexConv.get(Integer.valueOf(converter) / 16) != null) ? 
					hexConv.get(Integer.valueOf(converter) / 16) :
					String.valueOf((Integer.valueOf(converter)) / 16));
			
			num = num /16;
			
			convertedNum.add(remainder);
		}
		convertedNum.add(converter);
		
		String result = convArrToInt(convertedNum);
		
		return result;
	}

	private static String convArrToInt(ArrayList<String> convertedNum)
	{
		String result = "";
		for(int i = convertedNum.size() - 1; i >= 0; i--)
		{
			result += "" + convertedNum.get(i);
		}
		return result;
	}

	public static void main(String[] args)
	{
		String r = convertIntToHexBase(54315936);
		System.out.println(r);
	}

}
