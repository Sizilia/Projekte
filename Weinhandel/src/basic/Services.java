package basic;

import java.io.IOException;
import java.sql.*;

import object.Wein;

public class Services {
	
	//############################################################################
	// Die Service Klasse stellt die Verbindung zur Weinhandel Datenbank her.
	// Sie enth�lt die Methoden zum Ausf�hren der Datenoperationen die von der
	// Oberfl�che gesendet werden.
	//############################################################################
	
	//############################################################################
	// Funktionen zur Aufbereitung der Daten.
	//############################################################################
	
	//****************************************************************************
	// Die Funktion postSQLStatment() stellt eine Verbindung zur Datenbank her
	// sendet den �bergebenen SQL String, wartet auf die R�ckgabe der Datenbank
	// und schlie�t die Verbindung wieder.
	// Die R�ckgabe ist das Result aus dem SQL Statement.
	//****************************************************************************
	private ResultSet postSQLStatement(String cSQL, String cFilter) throws SQLException, ClassNotFoundException{
//		String url = ServerConnection.GetDBConnectionString();
		cSQL = cSQL.toUpperCase();
		if (cFilter.length() != 0){
			//Exception on edit-statements
			cFilter = cFilter.toUpperCase();
			int nPosDel = cFilter.indexOf("DELETE");
			int nPosIns = cFilter.indexOf("INSERT");
			int nPosUpd = cFilter.indexOf("UPDATE");
			int nPosDrp = cFilter.indexOf("DROP");
			if (nPosDel >= 0 || nPosIns >= 0 || nPosUpd >= 0 || nPosDrp>=0 )
				try {
					throw new IOException("Edit statements are not allowed!");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//insert filter in sql-statement
			int nPos = cSQL.indexOf("WHERE");
			int nPosOrd = cSQL.indexOf("GROUP BY");
			int nPosGrp = cSQL.indexOf("ORDER BY");
			String sqlOrdGrp = "";
			if (nPosOrd > 0 || nPosGrp > 0) {
				sqlOrdGrp = cSQL.substring(Math.max(nPosOrd, nPosGrp));
				cSQL = cSQL.substring(0, Math.max(nPosOrd, nPosGrp));
			}
			if (nPos >= 0)
				cSQL = cSQL + " and " +cFilter+" "+ sqlOrdGrp;
			else
				cSQL = cSQL + " where " +cFilter+" "+ sqlOrdGrp;	
		}
		//Execute Query
//		Class.forName("com.mysql.jdbc.Driver");
//		c = DriverManager.getConnection(url, ServerConnection.GetDBLoginUser(), ServerConnection.GetDBLoginPW());
		Statement query = ServerConnection.getConnection().createStatement();
		Utils.prs("SQL-Statement", cSQL);
		ResultSet results = query.executeQuery(cSQL);
		return results;
	}

	//****************************************************************************
	// Die Funktion createSQLStatement setzt aus dem gegeben Modus einen SQL String
	//****************************************************************************
	private String createSQLStatement(String cModus){
		String cResult = "";
		
		
		return cResult;
	}
	
	

	//****************************************************************************
	// Finder Methode um SQL String auszuf�hren.
	// - Funktion �berpr�ft ob im Where Teil Delete etc �bergeben wurde,
	// dies f�hrt zum Fehler.
	// Die R�ckgabe ist das Wein DTO
	//****************************************************************************
	private Wein[] findWein(String cFilter, String cOrderBy, int nMax) throws SQLException, IOException, ClassNotFoundException {
		java.util.Vector v=new java.util.Vector();
		Wein dto;
		String cSQL = "SELECT * FROM tbl_wein";
		ResultSet results = postSQLStatement(cSQL, cFilter);
		//insert filter in SQL-statement
		boolean notDone = results.next();
		int nCount = 1;
		while(notDone & (nCount <= nMax || nMax == -1)) {
			dto=new Wein();
			dto.setnr(results.getInt("WeinNr"));
			dto.setname(results.getString("Weinname"));
			dto.setjahrgang(results.getInt("Jahrgang"));
			dto.setbeschr(results.getString("Beschreibung"));
			dto.setpreis(results.getFloat("Preis"));
			dto.setweingut(results.getInt("Weingut"));
			dto.settyp(results.getInt("Typ"));
			dto.setart(results.getInt("Art"));
			v.add(dto);
			notDone = results.next();
			nCount++;
		}
		Wein[] result=new Wein[v.size()];
		for (int i=0; i<v.size();i++)
			result[i]=(Wein) v.elementAt(i);
		return result;
	}

	//****************************************************************************
	// Die getWeintable() gibt die Weintabelle als Data Transfer Object zur�ck
	//****************************************************************************
	public  Wein[] getWeintable(String cFilter) throws SQLException, IOException, ClassNotFoundException{ 
			return findWein(cFilter,"",-1);
	}
	
	//****************************************************************************
	// Diese Funktion legt nach Modus einen Datensatz an / l�scht einen Datensatz 
	// ver�ndert die Feldwerte des Datensatzes.
	// R�ckgabe der Funktion: R�ckmeldung der Datenbank.
	//****************************************************************************
	private String refreshDataBase(String TableName, int PK, String Values , boolean newDS){
		
		 
		// Wenn Datensatz noch nicht vorhanden ist.
		if(newDS){
			
		   return "Neuer Datensatz wurde angelegt";	
		}
		// Datensatz muss gel�scht werden.
		else if( Values.length() == 0){
			
			return "Datensatz wurde gel�scht";
		}
		// Datensatz wird angepasst.
		else{
			return "angelegt.";
		}
	}
	
	//############################################################################
	// Funktionen zum Bef�llen der einzelnen Data Transfer Objects
	//############################################################################
	
	//****************************************************************************
	// Die Funktion getCustomerAdress() holt sich aus der Weinhandel Datenbank die 
	//Rechnungs
	//****************************************************************************
	
	
}
