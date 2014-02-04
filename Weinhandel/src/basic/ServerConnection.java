package basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class ServerConnection {
		
	private static Connection connection = null;
	// Verbindungen erstellen
	public static Connection getConnection(){
			try {
				Properties properties = new Properties();			
				FileInputStream in = new FileInputStream("db.properties");				
				properties.load(in);
				// Entnehme aus der Properties-Datei die Einstellungen
				String driver = properties.getProperty("driver");
				String url = properties.getProperty("url");
				String user = properties.getProperty("user");
				String password = properties.getProperty("password");
				
				// Erstelle Datenbankverbindung
				Class.forName(driver);
				connection = DriverManager.getConnection(url, user, password);
			} catch (SQLException | ClassNotFoundException | IOException e) {
				System.out.println("Fehler: " + e.getStackTrace());
			}
			
			return connection;
	}
	
	// Verbindungen schlieﬂen
	public void closeConnection(Connection conn, ResultSet rs, PreparedStatement prepStatement){
		try{	
			if(conn != null){			
				conn.close();		
			}
			if(rs != null){
				rs.close();
			}
			if(prepStatement  != null){
				prepStatement.close();
			}
		} catch (SQLException e) {
			System.out.println("Fehler: " + e.getMessage());
		}	
	}
}
	
	
	
//    public static String GetDBConnectionString() {
//    	return "jdbc:mysql.localhost/db_weinhandel";
//    }
//    public static String GetDBLoginUser(){
//    	return "root";
//    }
//    public static String GetDBLoginPW(){
//    	return "";
//    }
