import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordGeneration {
	
	public String genaratedWord() throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new File(".\\Team_4_Hangman_Project\\src\\Words.txt"));
		
		List<String> words = new ArrayList<>();
		while (scanner.hasNext())
		{
			words.add((scanner.nextLine()));
		}
		
		Random random = new Random();
		
		String word = words.get(random.nextInt(words.size()));
		
		//System.out.println(word);
		return word;
		
	}
}