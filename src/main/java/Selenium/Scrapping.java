package Selenium;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Scrapping {
    public static void main(String[] args) {
        try {
            String connectionString = "mongodb+srv://tuannamle256:k3lGLGcBHFvYgto9@phannam.t93kh0q.mongodb.net/?retryWrites=true&w=majority&appName=PhanNam";
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString))
                    .build();

            File input = new File("output.html");
            Document document = Jsoup.parse(input, "UTF-8", "");
            System.out.println("Success connected");
            Elements articles = document.select("article.mb-30");
            int i = 0;

            // Create a list to store the article data
            List<Article> articleList = new ArrayList<>();

            // Iterate through the articles and extract the required data
            for (Element article : articles) {
                String enroll_link = article.selectFirst("a").attr("href");
                String title = article.select(".article__title").text();
                Document doc = Jsoup.connect(enroll_link).get();
                String author = doc.select(".author-title").text();
                Elements dateSectionDiv = doc.select("div.fs-14.date-section");
                String time = dateSectionDiv.select("time").attr("datetime");
                Element tagBoxDiv = doc.selectFirst("div.article-tag-box");
                Elements pElements = doc.select("p");
                StringBuilder allPTagsContent = new StringBuilder();

                for (Element pElement : pElements) {
                    allPTagsContent.append(pElement.text());
                }

                try (MongoClient mongoClient = MongoClients.create(settings)) {
                    MongoDatabase database = mongoClient.getDatabase("data");
                    String fieldName = title.replaceAll("[^a-zA-Z0-9].,\\s", "");
                    String Content = allPTagsContent.toString().replaceAll("[^a-zA-Z0-9.,\\s]", "");
                    org.bson.Document data = new org.bson.Document("title",fieldName).append("data",Content);             
                    MongoCollection<org.bson.Document> collection = database.getCollection("mycollection");
                    collection.insertOne(data);
                }

                if (tagBoxDiv != null) {
                    String tag = tagBoxDiv.select("a").text();
                    Article newArticle = new Article(enroll_link, title, author, time, tag);
                    articleList.add(newArticle);
                } else {
                    Article newArticle = new Article(enroll_link, title, author, time);
                    articleList.add(newArticle);
                }
            }

            // Write data to CSV file
            File csvFile = new File("article.csv");
            try (PrintWriter printWriter = new PrintWriter(csvFile, StandardCharsets.UTF_8)) {
                printWriter.write('\ufeff');
                List<String> row1 = new ArrayList<>();
                row1.add("\"" + "Link" + "\"");
                row1.add("\"" + "Title" + "\"");
                row1.add("\"" + "Author" + "\"");
                row1.add("\"" + "Time" + "\"");
                row1.add("\"" + "Tag" + "\"");
                printWriter.println(String.join(",", row1));

                for (Article quote : articleList) {
                    List<String> row = new ArrayList<>();
                    row.add("\"" + quote.getEnrollLink() + "\"");
                    row.add("\"" + quote.getTitle() + "\"");
                    row.add("\"" + quote.getAuthor() + "\"");
                    row.add("\"" + quote.getTime() + "\"");
                    if (quote.getTag() != null) {
                    	row.add("\"" + quote.getTag() + "\"");
                    }
                    printWriter.println(String.join(",", row));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    static class Article {
        private String title;
        private String enroll_link;
        private String author;
        private String time;
        private String tag;

        public Article(String enroll_link, String title, String author, String time, String tag) {
            this.title = title;
            this.enroll_link = enroll_link;
            this.author = author;
            this.time = time;
            this.tag = tag;
        }

        public Article(String enroll_link, String title, String author, String time) {
            this.title = title;
            this.enroll_link = enroll_link;
            this.author = author;
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public String getEnrollLink() {
            return enroll_link;
        }

        public String getAuthor() {
            return author;
        }

        public String getTime() {
            return time;
        }

        public String getTag() {
            return tag;
        }
    }
}
    

