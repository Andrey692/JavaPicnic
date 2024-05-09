import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JavaPiсnic {
    public static void main(String[] args) {
        String fileName = "input.txt";

        totalNumberOfWords(fileName);
        findLongestWord(fileName);
        frequencyOfWords(fileName);
    }

    // Метод подсчета слов в файле
    private static void totalNumberOfWords(String fileName) {
        int wordCount = 0;

        try {
            Scanner scanner = new Scanner(new File(fileName));

            scanner.useDelimiter("\\W+");

            while (scanner.hasNext()) {
                scanner.next();
                wordCount++;
            }
            scanner.close();

            System.out.println("Количество слов в файле: " + wordCount);
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getMessage());
        }
    }

    // Метод для нахождения самого длинного слова в файле
    public static void findLongestWord(String fileName) {
        String longestWord = ""; // Переменная для хранения самого о слова
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line; // Переменная для хранения текущей строки из файла
            // Чтение файла построчно
            while ((line = reader.readLine()) != null) {
                // Разделение строки на слова по пробелу
                String[] words = line.split("\\s+");
                // Перебор всех слов в текущей строке
                for (String word : words) {
                    // Если текущее слово длиннее, чем самое длинное найденное ранее слово
                    if (word.length() > longestWord.length()) {
                        longestWord = word; // Заменяем самое длинное слово на текущее
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
        System.out.println("Самое длинное слово в файле: " + longestWord);
    }

    // Метод для подсчета, сколько раз каждое слово встречается в файле
    public static void frequencyOfWords(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));

            Map<String, Integer> wordFrequencyMap = new HashMap<>();

            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase(); // Преобразуем слово в нижний регистр
                wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
            }

            scanner.close();

            System.out.println("Частота повторения слов в файле:");
            for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getMessage());
        }

    }

}
