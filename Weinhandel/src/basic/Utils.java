package basic;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	//Funktion zum Debuggen der einzelnen Teilschritte
	public static void prs(String cMarke, String cParam) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String uhrzeit = sdf.format(new Date());
		long nTime=System.nanoTime()/1000000; 
		System.out.println(uhrzeit+":"+String.valueOf(nTime) + "/" + cMarke + "/" + cParam);		
	}
}
 