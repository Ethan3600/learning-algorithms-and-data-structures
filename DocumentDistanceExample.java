import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.util.*;

/**
 * Document Distance algorithm
 * 
 * @author Ethan
 *
 */
public class DocumentDistanceExample 
{
    
    public static Hashtable<String, Integer> doc1Freq;
    public static Hashtable<String, Integer> doc2Freq;
    
    
    public static double documentDistance(List<String> document1, List<String> document2)
    {
	// append document lines into one string
	String doc1 = appendDocument(document1);
	String doc2 = appendDocument(document2);
	
	// Split documents into words
	ArrayList<String> doc1Split = splitDocument(doc1);
	ArrayList<String> doc2Split = splitDocument(doc2);
	
	// Compute word frequencies
	computeFrequencies(doc1Split, doc2Split);
	
	// Compute dot product
	int dotProduct = computeDotProduct();
	
	// Divide dot product by the length(magnitude) of the two vectors
	double lengthOfBothVectors = computeLengthOfBothVectors(doc1Freq, doc2Freq);
	
	double result = ((double) dotProduct / lengthOfBothVectors);
	
	result =  Math.acos(result);
	
	return result;
    }

    public static void main(String[] args) 
    {
	try
	{
	    String filePath = new File("").getAbsolutePath();
	    String doc1path = new String(filePath+"/src/miscellaneous/document_distance_inputs/doc1.txt");
	    String doc2path = new String(filePath+"/src/miscellaneous/document_distance_inputs/doc2.txt");
	
	    List<String> document1 = new ArrayList<String>(Files.readAllLines(Paths.get(doc1path)));
	    List<String> document2 = new ArrayList<String>(Files.readAllLines(Paths.get(doc2path)));

	    double result = documentDistance(document1, document2);
	    System.out.println(result);
	}
	catch (Exception e)
	{
	    System.out.println(e.getMessage());
	}

    }
    
    // ========================= UTILITIES ========================= //
    
    /**
     * O(n)
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
    /**
     * O(n)
     * Regex is not reliable and can grow exponentially or linearly
     * 
     * @param doc
     * @return
     */
    public static ArrayList<String> splitDocument(String doc)
    {
	ArrayList<String> docSplit = new ArrayList<String>();
	
	for(String word : doc.split("\\s+"))
	{
	    word = word.replaceAll("[^A-Za-z0-9]", "");
	    docSplit.add(word.toLowerCase());
	}
	return docSplit;
    }
    /**
     * O(doc1 * doc2)
     * 
     * @param doc1
     * @param doc2
     */
    public static void computeFrequencies(ArrayList<String> doc1, ArrayList<String> doc2)
    {
	doc1Freq = new Hashtable<String, Integer>();
	doc2Freq = new Hashtable<String, Integer>();
	
	for(String D1 : doc1)
	{
	    if(doc1Freq.containsKey(D1))
	    {
		doc1Freq.put(D1, doc1Freq.get(D1) + 1);
	    }
	    else
	    {
		doc1Freq.put(D1, 1);
	    }
	}
	for (String D2: doc2)
	{
	    if(doc2Freq.containsKey(D2))
	    {
		doc2Freq.put(D2, doc2Freq.get(D2) + 1);
	    }
	    else
	    {
		doc2Freq.put(D2, 1);
	    }
	}
	
    }
    
    /**
     * O(n^2)
     * 
     * @return
     */
    public static int computeDotProduct()
    {	
	int sum = 0;
	
	Set<String> keysOfDoc1 = doc1Freq.keySet();
	Set<String> keysOfDoc2 = doc2Freq.keySet();
	
	for(String word1 : keysOfDoc1)
	{
	    for(String word2 : keysOfDoc2)
	    {
		if(word1.equals(word2))
		{
		    sum += doc1Freq.get(word1) * doc2Freq.get(word2);
		}
	    }
	}
	
	return sum;
    }
    
    /**
     * O(n)
     * 
     * @param doc1Freq
     * @param doc2Freq
     * @return
     */
    public static double computeLengthOfBothVectors(Hashtable<String, Integer> doc1Freq, Hashtable<String, Integer> doc2Freq)
    {
	double lengthOfVector1 = magnitudeOfVector(doc1Freq);
	double lengthOfVector2 = magnitudeOfVector(doc2Freq);
	
	return lengthOfVector1 * lengthOfVector2;
    }
    
    // ========================= SUB UTILITIES ========================= //
    /**
     * O(n)
     * 
     * @param docFreq
     * @return
     */
    public static double magnitudeOfVector(Hashtable<String, Integer> docFreq)
    {
	
	double sumOfSqrs = 0;
	
	Set<String> keysOfDoc = docFreq.keySet();
	for(String key : keysOfDoc)
	{
	    sumOfSqrs = Math.pow(docFreq.get(key), 2) + sumOfSqrs;
	}
	
	return Math.sqrt(sumOfSqrs);
	
    }

}
