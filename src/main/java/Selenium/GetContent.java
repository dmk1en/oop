package Selenium;
import org.bson.Document;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
import com.mongodb.client.model.Filters;

import oracle.net.aso.l;

import com.mongodb.client.FindIterable;
import com.mongodb.MongoClientSettings;
import com.mongodb.ConnectionString;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Filters;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;



public class GetContent {
	public List<List<String>> getDocumentByFieldName(String keyword) {
		 String connectionString = "mongodb+srv://tuannamle256:k3lGLGcBHFvYgto9@phannam.t93kh0q.mongodb.net/?retryWrites=true&w=majority&appName=PhanNam";
         MongoClientSettings settings = MongoClientSettings.builder()
                 .applyConnectionString(new ConnectionString(connectionString))
                 .build();
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("data");
            MongoCollection<Document> collection = database.getCollection("mycollection");
            
            FindIterable<Document> document1 = collection.find();
            List<List<String>> titleanddata = new ArrayList<>();
            
            for (Document document : document1) {
                String title = document.getString("title");
                String data = document.getString("data");
                
                if (isKeywordInData(keyword, data)) {
                    List<String> titleAndDataForThisDocument = new ArrayList<>();
                    titleAndDataForThisDocument.add(title);
                    titleAndDataForThisDocument.add(data);

                    titleanddata.add(titleAndDataForThisDocument);
                }

            }
            return titleanddata;
  
        } catch (MongoException e) {
            e.printStackTrace();
        }
		return null;
	}

    
    public String getContentByTitle(String title){
        String connectionString = "mongodb+srv://tuannamle256:k3lGLGcBHFvYgto9@phannam.t93kh0q.mongodb.net/?retryWrites=true&w=majority&appName=PhanNam";
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build();
       try (MongoClient mongoClient = MongoClients.create(settings)) {
           MongoDatabase database = mongoClient.getDatabase("data");
           MongoCollection<Document> collection = database.getCollection("mycollection");
           
           FindIterable<Document> document1 = collection.find();
           
           for (Document document : document1) {
               String getTitle = document.getString("title");
               
               if (getTitle.equals(title)) {
                   String data = document.getString("data");
                   return data;
               }

           }
           return null;
 
       } catch (MongoException e) {
           e.printStackTrace();
       }
       return null;
   }        


    
    
    public boolean isKeywordInData(String keyword, String data) {
        return data.toLowerCase().contains(keyword.toLowerCase());
    }

    public static void main(String[] args) {
    	String getContent = new GetContent().getContentByTitle("Solana Price Prediction as CEO Yakovenko Previews Post-Firedancer Era – Is SOL Set to Lead in Web3?");
        System.out.println(getContent);
    }

}