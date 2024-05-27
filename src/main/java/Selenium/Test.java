package Selenium;
import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {
    private static String url;

    public static void main(String[] args) {
        try {
            String page = "page/";
            List<Article> articleList = new ArrayList<>();

            for (int i = 1; i < 472; i++) {
                if (i == 1) {
                    url = "https://www.the-blockchain.com/";
                } else {
                    String number = Integer.toString(i);
                    url = "https://www.the-blockchain.com/" + page + "number" + "/";
                }
                Document document = Jsoup.connect(url).get();
                System.out.println("Success connected");
                TimeUnit.SECONDS.sleep(10);
                Elements articles = document.select("div.td_module_flex");

                // Iterate through the articles and extract the required data
                for (Element article : articles) {
                    String link = article.select("a.td-image-wrap").attr("href");
                    String title = article.select("h3.entry-title a").attr("title");
                    String date = article.select("time.entry-date").attr("datetime");
   

                    // Create an Article object and add it to the list
                    Article newArticle = new Article(link, title, date);
                    articleList.add(newArticle);
                }
            }
            // Write data to CSV file
            writeToCSV(articleList);
            System.out.println("Done");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeToCSV(List<Article> articles) {
        String csvFile = "articles_test.csv";
        try (PrintWriter printWriter = new PrintWriter(csvFile, StandardCharsets.UTF_8)) {
            printWriter.write('\ufeff');
            List<String> row1 = new ArrayList<>();
            row1.add("\"" + "Link" + "\"");
            row1.add("\"" + "Title" + "\"");
            row1.add("\"" + "Date" + "\"");
    
            printWriter.println(String.join(",", row1));

            for (Article article : articles) {
                List<String> row = new ArrayList<>();
                row.add("\"" + article.getLink() + "\"");
                row.add("\"" + article.getTitle() + "\"");
                row.add("\"" + article.getDate() + "\"");
    
                printWriter.println(String.join(",", row));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Define the Article class
    static class Article {
        private String link;
        private String title;
        private String date;


        public Article(String link, String title, String date) {
            this.link = link;
            this.title = title;
            this.date = date;
    
        }

        public String getLink() {
            return link;
        }

        public String getTitle() {
            return title;
        }

        public String getDate() {
            return date;
        }

 
    }
}