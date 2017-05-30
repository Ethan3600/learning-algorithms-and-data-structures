import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RabinKarpAlgorithmExample 
{
    
    static final int PRIME = 101;
    
    public static ArrayList<Integer> rabinKarp(String needle, String document) 
    {
	ArrayList<Integer> matchedIndexes = new ArrayList<Integer>(); 
	int key = hash(needle);
	String rollingComparison;
	
	for(int i = 0; i < document.length(); i++)
	{
	    if(i + needle.length() < document.length())
	    {
		rollingComparison = document.substring(i, needle.length() + i);
	    }
	    else return matchedIndexes; 
	    if(key == hash(rollingComparison)) 
	    {
		matchedIndexes.add(i);
	    }
	}
	return matchedIndexes;
    }
    
    /**
     * O(m*n)
     * 
     * Where m is the length of the pattern (needle) and n is the length of the document
     * 
     * @param needle
     * @param document
     * @param returnFirstMatch
     * @return
     * @throws Exception
     */
    public static int rabinKarp(String needle, String document, boolean returnFirstMatch) throws Exception 
    {
	if(!returnFirstMatch)
	{
	    throw new Exception("Must be true, otherwise do not include third parameter");
	}
	int matchedIndexes = -1;
	int key = hash(needle);
	String rollingComparison;
	
	for(int i = 0; i < document.length(); i++)
	{
	    if(i + needle.length() < document.length())
	    {
		rollingComparison = document.substring(i, needle.length() + i);
	    }
	    else return matchedIndexes; 
	    if(key == hash(rollingComparison)) 
	    {
		matchedIndexes = i;
		return matchedIndexes; 
	    }
	}
	return matchedIndexes;
    }

    public static void main(String[] args) 
    {
	try
	{
	    String filePath = new File("").getAbsolutePath();
	    String doc1path = new String(filePath+"/src/miscellaneous/Rabin_Karp_Input/doc1.txt");
	
	    List<String> doc = new ArrayList<String>(Files.readAllLines(Paths.get(doc1path)));
	    String document = appendDocument(doc);
	    int results = rabinKarp("test", document, true);
	    System.out.println(results);
	}
	catch (Exception e)
	{
	    System.out.println(e.getMessage());
	}

    }

    // ============================ UTILITIES ======================== //
    
    /**
     * Returns one long string of a given document
     * 
     * @param document
     * @return
     */
    public static String appendDocument(List<String> document)
    {
	String doc = "";
	for(String line : document)
	{
	    doc = doc.concat(line+" ");
	}
	return doc;
    }
   
    public static int hash(String key)
    {
	int value = 0;
	for (int i = 0; i < key.length(); i++)
	{
	    char c = key.charAt(i);      
	    value += (int) c * Math.pow(getPrime(), i);
	}
	return value;
    }
    
    public static int getPrime()
    {
	return RabinKarpAlgorithmExample.PRIME;
    }
    
}
