package javafx.Controller;

import org.bson.Document;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import java.util.ArrayList;
import java.util.List;
import Selenium.*;


public class GetContent extends MongoDb{
	public GetContent(String connectionString) {
        super(connectionString);
        //TODO Auto-generated constructor stub
    }


    public List<List<String>> getDocumentByFieldName(String keyword) {
        MongoClientSettings settings = this.getSettings();
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
        MongoClientSettings settings = this.getSettings();
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
    	String getContent = new GetContent("mongodb+srv://tuannamle256:k3lGLGcBHFvYgto9@phannam.t93kh0q.mongodb.net/?retryWrites=true&w=majority&appName=PhanNam").getContentByTitle("Solana Price Prediction as CEO Yakovenko Previews Post-Firedancer Era – Is SOL Set to Lead in Web3?");
        System.out.println(getContent);
    }

}