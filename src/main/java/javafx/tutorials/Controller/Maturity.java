package javafx.tutorials.Controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class Maturity {
    public static void main(String[] args) throws IOException {
        try (InputStreamReader in = new InputStreamReader(new FileInputStream("article.csv"), StandardCharsets.UTF_8);) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);

            
            Map<String, Integer> wordFrequency = new HashMap<>();

            for (CSVRecord record : records) {
                String line = record.get(4);
                if (!line.equals("null")) {

                    String[] words = line.split(" ");

                    for (String word : words) {
                        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                    }
                }
            }

            for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                System.out.println(entry.getKey() +  " " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
