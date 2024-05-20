package Selenium;


import java.sql.*;

public class JDBC extends abstractDatabase{
   public JDBC(String connectionString) {
      super(connectionString);
      //TODO Auto-generated constructor stub
   }

   static final String DB_URL = "jdbc:mysql://localhost/TUTORIALSPOINT";
   static final String USER = "guest";
   static final String PASS = "guest123";
   static final String QUERY = "SELECT id, first, last, age FROM Employees";

   public static void main(String[] args) {
      JDBC db = new JDBC(DB_URL);
      db.connect();
   }

   @Override
   public void connect(){
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(QUERY);) {
      // Extract data from result set
      while (rs.next()) {
         // Retrieve by column name
         System.out.print("ID: " + rs.getInt("id"));
         System.out.print(", Age: " + rs.getInt("age"));
         System.out.print(", First: " + rs.getString("first"));
         System.out.println(", Last: " + rs.getString("last"));
      }
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}