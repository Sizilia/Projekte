package basic;

import java.io.IOException;
import java.sql.*;

import object.*;

public class Services {
	
	//############################################################################
	// Die Service Klasse stellt die Verbindung zur Weinhandel Datenbank her.
	// Sie enthält die Methoden zum Ausführen der Datenoperationen die von der
	// Oberfläche gesendet werden.
	//############################################################################
	
	//############################################################################
	// Funktionen zur Aufbereitung der Daten.
	//############################################################################
	
	//****************************************************************************
	// Die Funktion postSQLStatment() stellt eine Verbindung zur Datenbank her
	// sendet den übergebenen SQL String, wartet auf die Rückgabe der Datenbank
	// und schließt die Verbindung wieder.
	// Die Rückgabe ist das Result aus dem SQL Statement.
	//****************************************************************************
	private ResultSet postSQLStatement(String cSQL, String cFilter) throws ClassNotFoundException, SQLException{
		String url = ServerConnection.GetDBConnectionString();
		String user = ServerConnection.GetDBLoginUser();
		String userPw = ServerConnection.GetDBLoginPW();
		ResultSet results = null;
		Statement query = null;
		Connection connect = null;
		
		
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

		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection(url, user, userPw);
		query = connect.createStatement();
		results = query.executeQuery(cSQL);
		Utils.prs("SQL-Statement", cSQL);
		
		
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
	// Finder Methode um SQL String auszuführen.
	// - Funktion überprüft ob im Where Teil Delete etc übergeben wurde,
	// dies führt zum Fehler.
	// Die Rückgabe ist das Wein DTO
	//****************************************************************************
	private Wein[] findWein(String cFilter, String cOrderBy, int nMax) throws SQLException, IOException, ClassNotFoundException {
		java.util.Vector<Wein> v=new java.util.Vector<Wein>();
		Wein dto;
		String cSQL = "SELECT * FROM wein";
		ResultSet results = postSQLStatement(cSQL, cFilter);
		//insert filter in SQL-statement
		boolean notDone = results.next();
		int nCount = 1;
		while(notDone & (nCount <= nMax || nMax == -1)) {
			dto=new Wein();
			dto.setnr(results.getInt("nr"));
			dto.setname(results.getString("name"));
			dto.setjahrgang(results.getInt("jahrgang"));
			dto.setbeschr(results.getString("beschr"));
			dto.setpreis(results.getFloat("preis"));
			dto.setweingut(results.getInt("weingut"));
			dto.settyp(results.getInt("typ"));
			dto.setart(results.getInt("art"));
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
	// Die getWeintable() gibt die Weintabelle als Data Transfer Object zurück
	//****************************************************************************
	public Wein[] getWeinTable(String cFilter) throws SQLException, IOException, ClassNotFoundException{ 
			return findWein(cFilter,"",-1);
	}
	
	
	//****************************************************************************
	// Finder Methode um SQL String auszuführen.
	// - Funktion überprüft ob im Where Teil Delete etc übergeben wurde,
	// dies führt zum Fehler.
	// Die Rückgabe ist das Art DTO
	//****************************************************************************
	public Art[] findArt(String cFilter, String cOrderBy, int nMax) throws SQLException, IOException, ClassNotFoundException{
		java.util.Vector<Art> v = new java.util.Vector<Art>();
		Art dto;
		String cSQL = "SELECT *  FROM art";
		ResultSet results = postSQLStatement(cSQL, cFilter);
		//insert filter in SQL-Statement
		boolean notDone = results.next();
		int nCount = 1;
		while(notDone & (nCount <= nMax || nMax == -1)){
			dto = new Art();
			dto.setBez(results.getString("bez"));
			dto.setNr(results.getInt("nr"));
			v.add(dto);
			notDone = results.next();
			nCount++;
		}
		Art[] result = new Art[v.size()];
		for(int i = 0; i < v.size(); i++){
			result[i] = (Art) v.elementAt(i);
		}
		
		return result;
	}
	
	//****************************************************************************
	// Die getArttable() gibt die Arttabelle als Data Transfer Object zurück
	//****************************************************************************
	public Art[] getArtTable(String cFilter) throws SQLException, IOException, ClassNotFoundException{ 
			return findArt(cFilter,"",-1);
	}
	
	
	//****************************************************************************
	// Finder Methode um SQL String auszuführen.
	// - Funktion überprüft ob im Where Teil Delete etc übergeben wurde,
	// dies führt zum Fehler.
	// Die Rückgabe ist das Land DTO
	//****************************************************************************
	public Land[] findLand(String cFilter, String cOrderBy, int nMax) throws SQLException, IOException, ClassNotFoundException{
		java.util.Vector<Land> v = new java.util.Vector<Land>();
		Land dto;
		String cSQL = "SELECT *  FROM land";
		ResultSet results = postSQLStatement(cSQL, cFilter);
		//insert filter in SQL-Statement
		boolean notDone = results.next();
		int nCount = 1;
		while(notDone & (nCount <= nMax || nMax == -1)){
			dto = new Land();
			dto.setName(results.getString("name"));
			dto.setNr(results.getInt("nr"));
			v.add(dto);
			notDone = results.next();
			nCount++;
		}
		Land[] result = new Land[v.size()];
		for(int i = 0; i < v.size(); i++){
			result[i] = (Land) v.elementAt(i);
		}
		
		return result;
	}
	
	//****************************************************************************
	// Die getArttable() gibt die LandTabelle als Data Transfer Object zur¸ck
	//****************************************************************************
	public Land[] getLandTable(String cFilter) throws SQLException, IOException, ClassNotFoundException{ 
			return findLand(cFilter,"",-1);
	}
	
	
	//****************************************************************************
	// Finder Methode um SQL String auszuführen.
	// - Funktion überprüft ob im Where Teil Delete etc übergeben wurde,
	// dies führt zum Fehler.
	// Die Rückgabe ist das Rebsorten DTO
	//****************************************************************************
	public Rebsorte[] findRebsorte(String cFilter, String cOrderBy, int nMax) throws SQLException, IOException, ClassNotFoundException{
		java.util.Vector<Rebsorte> v = new java.util.Vector<Rebsorte>();
		Rebsorte dto;
		String cSQL = "SELECT *  FROM rebsorte";
		ResultSet results = postSQLStatement(cSQL, cFilter);
		//insert filter in SQL-Statement
		boolean notDone = results.next();
		int nCount = 1;
		while(notDone & (nCount <= nMax || nMax == -1)){
			dto = new Rebsorte();
			dto.setName(results.getString("name"));
			dto.setNr(results.getInt("nr"));
			v.add(dto);
			notDone = results.next();
			nCount++;
		}
		Rebsorte[] result = new Rebsorte[v.size()];
		for(int i = 0; i < v.size(); i++){
			result[i] = (Rebsorte) v.elementAt(i);
		}
		
		return result;
	}
	
	//****************************************************************************
	// Die getRebsorteTable() gibt die RebsorteTabelle als Data Transfer Object zur¸ck
	//****************************************************************************
	public Rebsorte[] getRebsorteTable(String cFilter) throws SQLException, IOException, ClassNotFoundException{ 
			return findRebsorte(cFilter,"",-1);
	}
	
	
	//****************************************************************************
	// Finder Methode um SQL String auszuführen.
	// - Funktion überprüft ob im Where Teil Delete etc übergeben wurde,
	// dies führt zum Fehler.
	// Die Rückgabe ist das Region DTO
	//****************************************************************************
	public Region[] findRegion(String cFilter, String cOrderBy, int nMax) throws SQLException, IOException, ClassNotFoundException{
		java.util.Vector<Region> v = new java.util.Vector<Region>();
		Region dto;
		String cSQL = "SELECT *  FROM region";
		ResultSet results = postSQLStatement(cSQL, cFilter);
		//insert filter in SQL-Statement
		boolean notDone = results.next();
		int nCount = 1;
		while(notDone & (nCount <= nMax || nMax == -1)){
			dto = new Region();
			dto.setName(results.getString("name"));
			dto.setNr(results.getInt("nr"));
			dto.setBeschr(results.getString("beschr"));
			dto.setLandID(results.getInt("land"));
			v.add(dto);
			notDone = results.next();
			nCount++;
		}
		Region[] result = new Region[v.size()];
		for(int i = 0; i < v.size(); i++){
			result[i] = (Region) v.elementAt(i);
		}
		
		return result;
	}
	
	//****************************************************************************
	// Die getRegionTable() gibt die RebsorteTabelle als Data Transfer Object zur¸ck
	//****************************************************************************
	public Region[] getRegionTable(String cFilter) throws SQLException, IOException, ClassNotFoundException{ 
			return findRegion(cFilter,"",-1);
	}
	
	
	//****************************************************************************
	// Finder Methode um SQL String auszuführen.
	// - Funktion überprüft ob im Where Teil Delete etc übergeben wurde,
	// dies führt zum Fehler.
	// Die Rückgabe ist das Typ DTO
	//****************************************************************************
	public Typ[] findTyp(String cFilter, String cOrderBy, int nMax) throws SQLException, IOException, ClassNotFoundException{
		java.util.Vector<Typ> v = new java.util.Vector<Typ>();
		Typ dto;
		String cSQL = "SELECT *  FROM typ";
		ResultSet results = postSQLStatement(cSQL, cFilter);
		//insert filter in SQL-Statement
		boolean notDone = results.next();
		int nCount = 1;
		while(notDone & (nCount <= nMax || nMax == -1)){
			dto = new Typ();
			dto.setBez(results.getString("bez"));
			dto.setNr(results.getInt("nr"));
			v.add(dto);
			notDone = results.next();
			nCount++;
		}
		Typ[] result = new Typ[v.size()];
		for(int i = 0; i < v.size(); i++){
			result[i] = (Typ) v.elementAt(i);
		}
		
		return result;
	}
	
	//****************************************************************************
	// Die getTypTable() gibt die RebsorteTabelle als Data Transfer Object zur¸ck
	//****************************************************************************
	public Typ[] getTypTable(String cFilter) throws SQLException, IOException, ClassNotFoundException{ 
			return findTyp(cFilter,"",-1);
	}
	
	
	//****************************************************************************
	// Finder Methode um SQL String auszuführen.
	// - Funktion überprüft ob im Where Teil Delete etc übergeben wurde,
	// dies führt zum Fehler.
	// Die Rückgabe ist das Weingut DTO
	//****************************************************************************
	public Weingut[] findWeingut(String cFilter, String cOrderBy, int nMax) throws SQLException, IOException, ClassNotFoundException{
		java.util.Vector<Weingut> v = new java.util.Vector<Weingut>();
		Weingut dto;
		String cSQL = "SELECT *  FROM weingut";
		ResultSet results = postSQLStatement(cSQL, cFilter);
		//insert filter in SQL-Statement
		boolean notDone = results.next();
		int nCount = 1;
		while(notDone & (nCount <= nMax || nMax == -1)){
			dto = new Weingut();
			dto.setName(results.getString("name"));
			dto.setNr(results.getInt("nr"));
			dto.setRegionID(results.getInt("region"));
			v.add(dto);
			notDone = results.next();
			nCount++;
		}
		Weingut[] result = new Weingut[v.size()];
		for(int i = 0; i < v.size(); i++){
			result[i] = (Weingut) v.elementAt(i);
		}
		
		return result;
	}
	
	//****************************************************************************
	// Die getWeingutTable() gibt die RebsorteTabelle als Data Transfer Object zur¸ck
	//****************************************************************************
	public Weingut[] getWeingutTable(String cFilter) throws SQLException, IOException, ClassNotFoundException{ 
			return findWeingut(cFilter,"",-1);
	}
	
	//****************************************************************************
	// Diese Funktion legt nach Modus einen Datensatz an / lˆscht einen Datensatz 
	// verändert die Feldwerte des Datensatzes.
	// Rückgabe der Funktion: Rückmeldung der Datenbank.
	//****************************************************************************
	private String refreshDataBase(String TableName, int PK, String Values , boolean newDS){
		
		 
		// Wenn Datensatz noch nicht vorhanden ist.
		if(newDS){
			
		   return "Neuer Datensatz wurde angelegt";	
		}
		// Datensatz muss gelöscht werden.
		else if( Values.length() == 0){
			
			return "Datensatz wurde gelˆscht";
		}
		// Datensatz wird angepasst.
		else{
			return "angelegt.";
		}
	}
	
	//############################################################################
	// Funktionen zum Befüllen der einzelnen Data Transfer Objects
	//############################################################################
	
	//****************************************************************************
	// Die Funktion getCustomerAdress() holt sich aus der Weinhandel Datenbank die 
	//Rechnungs
	//****************************************************************************
	
	
}
