package basic;

public class ServerConnection {
	
    public static String GetDBConnectionString() {
    	return "jdbc:mysql://localhost/weinhandlung";
    }
    
    public static String GetDBLoginUser(){
    	return "root";
    }
    
    public static String GetDBLoginPW(){
    	return "";
    }
    
}
