package Selenium;

public class Article {
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
