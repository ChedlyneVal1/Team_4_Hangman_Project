import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordGeneration {

    private static WordGeneration instance;
    private List<String> words;

    private WordGeneration() {
        words = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(".\\Team_4_Hangman_Project\\src\\Words.txt"));
            while (scanner.hasNext()) {
                words.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static WordGeneration getInstance() {
        if (instance == null) {
            instance = new WordGeneration();
        }
        return instance;
    }

    public String generateWord() {
        Random random = new Random();
        String word = words.get(random.nextInt(words.size()));
        return word;
    }
}
