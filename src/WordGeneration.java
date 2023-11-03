import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordGeneration {
	// Singleton variable
    private static WordGeneration instance = null; 
	private List<String> words = new ArrayList<>();
    
    private WordGeneration()
    {
    	try {
    		this.init();
    	} catch (FileNotFoundException ex) {
    		// Handle the exception
    	}
    }
    
    private void init() throws FileNotFoundException
    {
    	Scanner scanner = new Scanner(new File("..\\Team_4_Hangman_Project\\src\\Phrases.txt"));
		
		while (scanner.hasNext())
		{
			String phrase = scanner.nextLine();
			if (phrase.length() < 35){
				words.add(phrase);
			}
		}
    }
    
    // Call to get the instance of the singleton WordGeneration
    public static synchronized WordGeneration getInstance() 
    { 
        if (instance == null) 
        	instance = new WordGeneration(); 
  
        return instance; 
    } 
    
	public String genaratedWord()
	{	
		Random random = new Random();
		
		String phrase = words.get(random.nextInt(words.size()));

		//System.out.println(word);
		return phrase;
		
	}
}