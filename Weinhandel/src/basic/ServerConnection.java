package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServerConnection {
 
private static Connection connect = null;
 
 
 public ServerConnection(){
  establishDBConnection();
//  Test myTest = new Test(connect);
 }

 public void establishDBConnection(){
  System.out.println("JDBC-Connection Test");
  try {
   Class.forName("com.mysql.jdbc.Driver");
   connect = DriverManager.getConnection("jdbc:mysql://localhost/weinhandlung", "root", "");
   System.out.println("...verbunden");
  } catch (ClassNotFoundException e) {
   System.out.println("Fehler: " + e.getMessage());
  } catch (SQLException e) {
   System.out.println("Fehler: " + e.getMessage());
  }
  
 }


 public static void main(String[]args) throws SQLException{
  new ServerConnection();  
 }
}