package basic;

import java.io.IOException;
import java.sql.*;
import java.util.Hashtable;

import object.*;

public class Services {
	
	// wsdl : http://localhost:8080/Weinhandel/wsdl/Services.wsdl
	

	// Hashtables für Properties
	private  Hashtable<Integer,String> ArtHt;
	private  Hashtable<Integer,String> LandHt;
	private  Hashtable<Integer,String> RebsorteHt;
	private  Hashtable<Integer,String> TypHt;
	private  Hashtable<Integer,String> RegionHt;
	private  Hashtable<Integer,String> WeingutHt;
	
	
	
	//############################################################################
	// Die Service Klasse stellt die Verbindung zur Weinhandel Datenbank her.
	// Sie enth�lt die Methoden zum Ausf�hren der Datenoperationen die von der
	// Oberfl�che gesendet werden.
	//############################################################################
	
	//############################################################################
	// Finder der einzelnen DTO's
	//############################################################################
	//****************************************************************************
	// Finder Methode um SQL String auszuf�hren.
	// - Funktion �berpr�ft ob im Where Teil Delete etc �bergeben wurde,
	// dies f�hrt zum Fehler.
	// Die R�ckgabe ist das Wein DTO
	//****************************************************************************
	private Wein[] findWein(String cFilter, String cOrderBy, int nMax, boolean bAdmin) throws SQLException, IOException, ClassNotFoundException {
		java.util.Vector<Wein> v=new java.util.Vector<Wein>();
		Wein dto;
		String cSQL = "SELECT * FROM wein INNER JOIN weingut ON wein.weingut = weingut.nr INNER JOIN region  ON weingut.region = region.nr INNER JOIN land    ON region.land = land.nr INNER JOIN typ     ON wein.typ = typ.nr";
		ResultSet results = postSQLStatement(cSQL, cFilter, bAdmin);
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
	// Finder Methode um SQL String auszuf�hren.
	// - Funktion �berpr�ft ob im Where Teil Delete etc �bergeben wurde,
	// dies f�hrt zum Fehler.
	// Die R�ckgabe ist das Art DTO
	//****************************************************************************
	private Art[] findArt(String cFilter, String cOrderBy, int nMax, boolean bAdmin) throws SQLException, IOException, ClassNotFoundException{
		java.util.Vector<Art> v = new java.util.Vector<Art>();
		Art dto;
		String cSQL = "SELECT *  FROM art";
		ResultSet results = postSQLStatement(cSQL, cFilter, bAdmin);
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
	// Finder Methode um SQL String auszuf�hren.
	// - Funktion �berpr�ft ob im Where Teil Delete etc �bergeben wurde,
	// dies f�hrt zum Fehler.
	// Die R�ckgabe ist das Land DTO
	//****************************************************************************
	private Land[] findLand(String cFilter, String cOrderBy, int nMax, boolean bAdmin) throws SQLException, IOException, ClassNotFoundException{
		java.util.Vector<Land> v = new java.util.Vector<Land>();
		Land dto;
		String cSQL = "SELECT *  FROM land";
		ResultSet results = postSQLStatement(cSQL, cFilter, bAdmin);
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
	// Finder Methode um SQL String auszuf�hren.
	// - Funktion �berpr�ft ob im Where Teil Delete etc �bergeben wurde,
	// dies f�hrt zum Fehler.
	// Die R�ckgabe ist das Rebsorten DTO
	//****************************************************************************
	private Rebsorte[] findRebsorte(String cFilter, String cOrderBy, int nMax, boolean bAdmin) throws SQLException, IOException, ClassNotFoundException{
		java.util.Vector<Rebsorte> v = new java.util.Vector<Rebsorte>();
		Rebsorte dto;
		String cSQL = "SELECT *  FROM rebsorte";
		ResultSet results = postSQLStatement(cSQL, cFilter, bAdmin);
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
	
	private Weingut[] findWeingut(String cFilter, String cOrderBy, int nMax, boolean bAdmin) throws SQLException, IOException, ClassNotFoundException{
		java.util.Vector<Weingut> v = new java.util.Vector<Weingut>();
		Weingut dto;
		String cSQL = "SELECT *  FROM rebsorte";
		ResultSet results = postSQLStatement(cSQL, cFilter, bAdmin);
		//insert filter in SQL-Statement
		boolean notDone = results.next();
		int nCount = 1;
		while(notDone & (nCount <= nMax || nMax == -1)){
			dto = new Weingut();
			dto.setName(results.getString("name"));
			dto.setNr(results.getInt("nr"));
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
	// Finder Methode um SQL String auszuf�hren.
	// - Funktion �berpr�ft ob im Where Teil Delete etc �bergeben wurde,
	// dies f�hrt zum Fehler.
	// Die R�ckgabe ist das Region DTO
	//****************************************************************************
	private Region[] findRegion(String cFilter, String cOrderBy, int nMax, boolean bAdmin) throws SQLException, IOException, ClassNotFoundException{
		java.util.Vector<Region> v = new java.util.Vector<Region>();
		Region dto;
		String cSQL = "SELECT *  FROM region";
		ResultSet results = postSQLStatement(cSQL, cFilter, bAdmin);
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
	// Finder Methode um SQL String auszuf�hren.
	// - Funktion �berpr�ft ob im Where Teil Delete etc �bergeben wurde,
	// dies f�hrt zum Fehler.
	// Die R�ckgabe ist das Typ DTO
	//****************************************************************************
	private Typ[] findTyp(String cFilter, String cOrderBy, int nMax, boolean bAdmin) throws SQLException, IOException, ClassNotFoundException{
		java.util.Vector<Typ> v = new java.util.Vector<Typ>();
		Typ dto;
		String cSQL = "SELECT *  FROM typ";
		ResultSet results = postSQLStatement(cSQL, cFilter, bAdmin);
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
	// Finder für Benutzer
	private Benutzer[] findBenutzer(String cFilter, String cOrderBy, int nMax, boolean bAdmin) throws SQLException, IOException, ClassNotFoundException{
		java.util.Vector<Benutzer> v = new java.util.Vector<Benutzer>();
		Benutzer dto;
		String cSQL = "SELECT *  FROM Benutzer";
		ResultSet results = postSQLStatement(cSQL, cFilter, bAdmin);
		//insert filter in SQL-Statement
		boolean notDone = results.next();
		int nCount = 1;
		while(notDone & (nCount <= nMax || nMax == -1)){
			dto = new Benutzer();
			dto.setID(results.getInt("ID"));
			dto.setPassw(results.getString("Passwort"));
			v.add(dto);
			notDone = results.next();
			nCount++;
		}
		Benutzer[] result = new Benutzer[v.size()];
		for(int i = 0; i < v.size(); i++){
			result[i] = (Benutzer) v.elementAt(i);
		}
		
		return result;
	}
	
	
	
	
	//############################################################################
	// Funktionen zur Aufbereitung der Daten.
	//############################################################################
	
	//****************************************************************************
	// Die Funktion postSQLStatment() stellt eine Verbindung zur Datenbank her
	// sendet den �bergebenen SQL String, wartet auf die R�ckgabe der Datenbank
	// und schlie�t die Verbindung wieder.
	// Die R�ckgabe ist das Result aus dem SQL Statement.
	//****************************************************************************
	private ResultSet postSQLStatement(String cSQL, String cFilter, boolean bAdmin) throws ClassNotFoundException, SQLException{
		String url = ServerConnection.GetDBConnectionString();
		String user = ServerConnection.GetDBLoginUser();
		String userPw = ServerConnection.GetDBLoginPW();
		ResultSet results = null;
		Statement query = null;
		Connection connect = null;
		
		
		cSQL = cSQL.toUpperCase();
		if (!bAdmin && cFilter.length() != 0){
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
	// Hashtable für Art
	//****************************************************************************		
	private void fillArtHt() throws ClassNotFoundException, SQLException, IOException{
		
		Art[] art = findArt("","",-1,false);
		Hashtable <Integer,String> ht = new Hashtable<Integer,String>();
		
		for (int i = 0 ; i < art.length; i++){
			ht.put(art[i].getNr(), art[i].getBez());
		}
		ArtHt = ht;
	}
	
	
	//****************************************************************************
	// Hashtable für Land
	//****************************************************************************		
	private void fillLandHt() throws ClassNotFoundException, SQLException, IOException{
		
		Land[] land = findLand("","",-1,false);
		Hashtable <Integer,String> ht = new Hashtable<Integer,String>();
		
		for (int i = 0 ; i < land.length; i++){
			ht.put(land[i].getNr(), land[i].getName());
		}
		LandHt = ht;
	}
	
	//****************************************************************************
	// Hashtable für Region
	//****************************************************************************		
	private void fillRebsorteHt() throws ClassNotFoundException, SQLException, IOException{
		
		Rebsorte[] rebsorte = findRebsorte("","",-1,false);
		Hashtable <Integer,String> ht = new Hashtable<Integer,String>();
		
		for (int i = 0 ; i < rebsorte.length; i++){
			ht.put(rebsorte[i].getNr(), rebsorte[i].getName());
		}
		RebsorteHt = ht;
	}
	
	//****************************************************************************
	// Hashtable für Typ
	//****************************************************************************		
	private void fillTypHt() throws ClassNotFoundException, SQLException, IOException{
		
		Typ[] typ = findTyp("","",-1,false);
		Hashtable <Integer,String> ht = new Hashtable<Integer,String>();
		
		for (int i = 0 ; i < typ.length; i++){
			ht.put(typ[i].getNr(), typ[i].getBez());
		}
		TypHt = ht;
	}
	//****************************************************************************
	// Hashtable für Region
	//****************************************************************************		
	private void fillRegionHt() throws ClassNotFoundException, SQLException, IOException{
		
		Region[] region = findRegion("","",-1,false);
		Hashtable <Integer,String> ht = new Hashtable<Integer,String>();
		
		for (int i = 0 ; i < region.length; i++){
			ht.put(region[i].getNr(), region[i].getName());
		}
		RegionHt = ht;
	}
	
	//****************************************************************************
	// Hashtable für Weingut
	//****************************************************************************		
	private void fillWeingutHt() throws ClassNotFoundException, SQLException, IOException{
		
		Weingut[] weingut = findWeingut("","",-1,false);
		Hashtable <Integer,String> ht = new Hashtable<Integer,String>();
		
		for (int i = 0 ; i < weingut.length; i++){
			ht.put(weingut[i].getNr(), weingut[i].getName());
		}
		WeingutHt = ht;
	}

	

	
	
	
	//****************************************************************************
	// Die getWeintable() gibt die Weintabelle als Data Transfer Object zur�ck
	//****************************************************************************
	public Wein[] getWeinTable(String cFilter, boolean bAdmin) throws SQLException, IOException, ClassNotFoundException{ 
		
		Wein[] weintabelle = findWein(cFilter,"",-1, bAdmin);
		
		// Befüllung der HT
		fillArtHt();
		fillLandHt();
		fillRebsorteHt();
		fillTypHt();
		fillRegionHt();
		fillWeingutHt();
		
		for(int i=0 ; i < weintabelle.length ; i++){
			
			weintabelle[i].setWeinartBez(ArtHt.get(weintabelle[i].getart()));
			weintabelle[i].setWeingutBez(WeingutHt.get(weintabelle[i].getweingut()));
			weintabelle[i].setWeintypBez(TypHt.get(weintabelle[i].gettyp()));
		}
		
		return weintabelle;
	}
	
	//****************************************************************************
	// Die getArttable() gibt die Arttabelle als Data Transfer Object zur�ck
	//****************************************************************************
	public Art[] getArtTable(String cFilter, boolean bAdmin) throws SQLException, IOException, ClassNotFoundException{ 
			return findArt(cFilter,"",-1, bAdmin);
	}
	
	//****************************************************************************
	// Die getArttable() gibt die LandTabelle als Data Transfer Object zur�ck
	//****************************************************************************
	public Land[] getLandTable(String cFilter, boolean bAdmin) throws SQLException, IOException, ClassNotFoundException{ 
			return findLand(cFilter,"",-1, bAdmin);
	}

	//****************************************************************************
	// Die getRebsorteTable() gibt die RebsorteTabelle als Data Transfer Object zur�ck
	//****************************************************************************
	public Rebsorte[] getRebsorteTable(String cFilter, boolean bAdmin) throws SQLException, IOException, ClassNotFoundException{ 
			return findRebsorte(cFilter,"",-1, bAdmin);
	}
	
	//****************************************************************************
	// Die getRegionTable() gibt die RebsorteTabelle als Data Transfer Object zur�ck
	//****************************************************************************
	public Region[] getRegionTable(String cFilter, boolean bAdmin) throws SQLException, IOException, ClassNotFoundException{ 
			return findRegion(cFilter,"",-1, bAdmin);
	}
	
	//****************************************************************************
	// Die getTypTable() gibt die RebsorteTabelle als Data Transfer Object zur�ck
	//****************************************************************************
	public Typ[] getTypTable(String cFilter, boolean bAdmin) throws SQLException, IOException, ClassNotFoundException{ 
			return findTyp(cFilter,"",-1, bAdmin);
	}
	
	//****************************************************************************
	// Die getWeingutTable() gibt die RebsorteTabelle als Data Transfer Object zur�ck
	//****************************************************************************
	public Weingut[] getWeingutTable(String cFilter, boolean bAdmin) throws SQLException, IOException, ClassNotFoundException{ 
			return findWeingut(cFilter,"",-1, bAdmin);
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
	
	//****************************************************************************
	// Die Funktion �berpr�ft ob das Passwort des Mitarbeiters 
	//korrekt eingetippt wurde
	//****************************************************************************
	public void addFilter(String m_cFilter, String cFieldname, String cFieldValue){
		if (! m_cFilter.contains(cFieldname))
			m_cFilter += cFieldname + " = " + cFieldValue;
	}
	
	
	
	//****************************************************************************
	// Die Funktion �berpr�ft ob das Passwort des Mitarbeiters 
	//korrekt eingetippt wurde
	//****************************************************************************
	public boolean checkPW(String Username,String cPW) throws Exception{
		
		String cFilter ="";
		cFilter = "Username = '" + Username + "'";
		Benutzer[] oUser = findBenutzer(cFilter,"",1,false);
		if (oUser.length == 0)
			throw new Exception("Benutzer ist nicht vorhanden");
		
		Utils.prs("Passwort", oUser[0].getPassw());
		if(oUser[0].getPassw().equals(cPW))
			return true;
		
		else
			return false;
	}
	
	
	//############################################################################
	// Funktionen zum Bef�llen der einzelnen Data Transfer Objects
	//############################################################################
	
	//****************************************************************************
	// Die Funktion getCustomerAdress() holt sich aus der Weinhandel Datenbank die 
	//Rechnungs
	//****************************************************************************
	
	
}
