package javafx.tutorials.Controller;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class Maturity {

    public List<Map.Entry<String, Integer>> getWordFrequency() {
        try (InputStreamReader in = new InputStreamReader(new FileInputStream("article.csv"), StandardCharsets.UTF_8);) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);

            Map<String, Integer> wordFrequency = new HashMap<>();

            for (CSVRecord record : records) {
                if (record.size() > 4){
                String line = record.get(4);
                if (!line.equals("null")) {

                    String[] words = line.split(" ");

                    for (String word : words) {
                        String lowerCaseWord = word.toLowerCase();
                        wordFrequency.put(lowerCaseWord, wordFrequency.getOrDefault(lowerCaseWord, 0) + 1);
                    }
                }
            }
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(wordFrequency.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue()); // Đảo ngược so sánh
            }
        });
        
        return entryList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
