package com.brightdata;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) throws IOException {
        String baseUrl = "https://quotes.toscrape.com";
        List<Quote> quotes = new ArrayList<>();
        Document doc = Jsoup
                .connect(baseUrl)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();

        Elements nextElements = doc.select(".next");

        while (!nextElements.isEmpty()) {
            Element nextElement = nextElements.first();
            String relativeUrl = nextElement.getElementsByTag("a").first().attr("href");
            String completeUrl = baseUrl + relativeUrl;

            doc = Jsoup
                    .connect(completeUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                    .get();
            Elements quoteElements = doc.select(".quote");
            for (Element quoteElement : quoteElements) {
                Quote quote = new Quote();
                String text = quoteElement.select(".text").first().text();
                String author = quoteElement.select(".author").first().text();
                List<String> tags = new ArrayList<>();
                for (Element tag : quoteElement.select(".tag")) {
                    tags.add(tag.text());
                }

                quote.setText(text);
                quote.setAuthor(author);
                quote.setTags(String.join(", ", tags)); 
                System.out.println(quote);
                quotes.add(quote);
            }

            nextElements = doc.select(".next");
        }

        // initializing the output CSV file
        File csvFile = new File("output.csv");
        // using the try-with-resources to handle the
        // release of the unused resources when the writing process ends
        try (PrintWriter printWriter = new PrintWriter(csvFile, StandardCharsets.UTF_8)) {
            // to handle BOM
            printWriter.write('\ufeff');

            // iterating over all quotes
            for (Quote quote : quotes) {
                // converting the quote data into a
                // list of strings
                List<String> row = new ArrayList<>();

                // wrapping each field with between quotes
                // to make the CSV file more consistent
                row.add("\"" + quote.getText() + "\"");
                row.add("\"" +quote.getAuthor() + "\"");
                row.add("\"" +quote.getTags() + "\"");

                // printing a CSV line
                printWriter.println(String.join(",", row));
            }
        }
        
        
        
    }
}