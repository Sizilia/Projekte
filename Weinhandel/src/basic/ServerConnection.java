package basic;

import java.sql.*;


public class ServerConnection {
 
    public static String GetDBConnectionString() {
    	return "jdbc:mysql.localhost/db_weinhandel";
    }
    public static String GetDBLoginUser(){
    	return "root";
    }
    public static String GetDBLoginPW(){
    	return "";
    }
}