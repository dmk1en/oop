package Selenium;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;



public class MongoDb {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://tuannamle256:k3lGLGcBHFvYgto9@phannam.t93kh0q.mongodb.net/?retryWrites=true&w=majority&appName=PhanNam";
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build();

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("admin");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
                
                MongoIterable<String> databaseNames = mongoClient.listDatabaseNames();
                System.out.println("Available databases:");
                for (String dbName : databaseNames) {
                    System.out.println(dbName);
                }
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }
}
