import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordGeneration {
	public enum Theme {
		NONE, ANIMALS, CARTOONS, MOVIES, SCIENCE, SPORTS
	};
	
	// Singleton variable
    private static WordGeneration instance = null;
	private List<String> words = new ArrayList<>();
	private EnumMap<Theme, String> themeDictionary = new EnumMap<>(Theme.class);
	private static String PHRASES = "Phrases";
	private Theme curTheme = Theme.NONE;
    
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
    	configThemes();
    	loadInWords(PHRASES);
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
	
	private void configThemes() 
    {
		themeDictionary.put(Theme.ANIMALS, "Animals");
		themeDictionary.put(Theme.CARTOONS, "Cartoons and Superheroes");
		themeDictionary.put(Theme.MOVIES, "Movies");
		themeDictionary.put(Theme.SCIENCE, "Science");
		themeDictionary.put(Theme.SPORTS, "Sports");
    }
	
	private void loadInWords(String wordFile) throws FileNotFoundException {
    	Scanner scanner = new Scanner(new File("..\\Team_4_Hangman_Project\\src\\" + wordFile + ".txt"));
    	
    	words.clear();
    	
		while (scanner.hasNext())
		{
			String phrase = scanner.nextLine();
			if (phrase.length() < 35){
				words.add(phrase);
			}
		}
	}
	
	public boolean loadTheme(Theme wordTheme) {
		boolean ret = true;
		if(wordTheme == curTheme) return ret;
		try {
			loadInWords(themeDictionary.get(wordTheme));
			curTheme = wordTheme;
    	} catch (FileNotFoundException ex) {
    		ret = false;
    		// Handle the exception
    	}
		return ret;
		
	}
	
	public WordGeneration.Theme getCurTheme() {
		return curTheme;
	}
	
	public void setCurTheme(WordGeneration.Theme newTheme) {
		curTheme = newTheme;
	}
	
}