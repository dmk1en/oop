package Selenium;

public class abstractDatabase {
    private String connectionString;


    public abstractDatabase(String connectionString) {
        this.connectionString = connectionString;
    }


    public String getConnectionString() {
        return connectionString;
    }


    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }


    public void connect(){

    }
}
