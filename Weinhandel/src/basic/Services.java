package basic;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.HashSet;
import java.util.Hashtable;

import com.mysql.jdbc.Field;

import object.*;
import basic.Globals;

public class Services {
	
	// wsdl-File : http://localhost:8080/Weinhandel/wsdl/Services.wsdl
	
	// Hashtables fuer Properties
	private  Hashtable<Integer,String> ArtHt;
	private  Hashtable<Integer,String> LandHt;
	private  Hashtable<Integer,String> RebsorteHt;
	private  Hashtable<Integer,String> TypHt;
	private  Hashtable<Integer,String> RegionHt;
	private  Hashtable<Integer,String> WeingutHt;
	private  Globals global = new  Globals();
	
	//############################################################################
	// Die Service Klasse stellt die Verbindung zur Weinhandel Datenbank her.
	// Sie enthaelt die Methoden zum Ausfuehren der Datenoperationen die von der
	// Oberflaeche gesendet werden.
	//############################################################################
	
	//############################################################################
	// Finder der einzelnen DTO's
	//############################################################################
	//****************************************************************************
	// Finder Methode um SQL String auszufuehren.
	// - Funktion ueberprueft ob im Where Teil Delete etc uebergeben wurde,
	// dies f���hrt zum Fehler.
	// Die R���ckgabe ist das Wein DTO
	//****************************************************************************
	private Object[] findObject(Object obj,String cFilter) throws NoSuchFieldException, SecurityException, ClassNotFoundException, SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		String cSQL="";
		Class<? extends Object> dtoclass = obj.getClass();
		// Tabellenname herrausfinden
		String cTablename = dtoclass.getName();
		int n = 0;
		n =  cTablename.indexOf(".") +1;
		cTablename = cTablename.substring(n);
		Utils.prs("Tabellenname", cTablename);
		
		// Select Statement erstellen:
		switch(cTablename){
		case "Wein"   : cSQL = global.gl_WeinSQL;
						break;
		case "weingut": cSQL = global.gl_WeingutSQL;
						break;
		case "Art"    : cSQL = global.gl_ArtSQL;
		} 
		java.util.Vector<Object> v = new java.util.Vector<Object>();
		ResultSet results          = selectSQLStatement(cSQL,cFilter);
		
		Object oRes = null;
		Object arglist[] = new Object[1];
		boolean notDone = results.next();
		while(notDone) { // Schleife über SQL Tabelle
			Method[] methods = obj.getClass().getDeclaredMethods();
			int i=1;
			for( Method m : methods){	// Schleife über alle Methoden
				 if( m.getName().contains("set") && ! m.getName().contains("Bezeichnung")){
					oRes = results.getObject(i);
					if(oRes != null){
						if(oRes instanceof java.math.BigDecimal)
							arglist[0] =  results.getFloat(i); 
						else
							arglist[0] = oRes; 
							
						m.invoke(obj, arglist);
					}
					 i++;
				 }
			}
			v.add(obj);
			notDone = results.next();
		} 
		Object[] result = new Object[v.size()];
		for(int i = 0; i < v.size(); i++){
			result[i] = (Object) v.elementAt(i);
		}
		return result;
	}
		
	//****************************************************************************
	// Die getArttable() gibt die Arttabelle als Data Transfer Object zurueck
	//****************************************************************************
	public Wein[] getWeintable(String cFilter) throws NoSuchFieldException, SecurityException, ClassNotFoundException, SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Wein oWein = new Wein();
		Object[] result = findObject((Object) oWein,cFilter);
		Wein[] res = new Wein[result.length];
		for(int i = 0; i < result.length; i++)
			res[i] = (Wein) result[i];
		
		return res;
	}
	
	//****************************************************************************
	// Die getArttable() gibt die Arttabelle als Data Transfer Object zurueck
	//****************************************************************************
	public Art[] getArtTable(String cFilter) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{ 
		Art oArt = new Art();
		Object[] result = findObject((Object) oArt,cFilter);
		Art[] res = new Art[result.length];
		for(int i = 0; i < result.length; i++)
			res[i] = (Art) result[i];
		
		return res;
	}
	
	//****************************************************************************
	// Die getArttable() gibt die LandTabelle als Data Transfer Object zur���ck
	//****************************************************************************
	public Land[] getLandTable(String cFilter) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{ 
		Land oLand = new Land();
		Object[] result = findObject((Object) oLand,cFilter);
		Land[] res = new Land[result.length];
		for(int i = 0; i < result.length; i++)
			res[i] = (Land) result[i];
		
		return res;
	}

	//****************************************************************************
	// Die getRebsorteTable() gibt die RebsorteTabelle als Data Transfer Object zur���ck
	//****************************************************************************
	public Rebsorte[] getRebsorteTable(String cFilter) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{ 
		Rebsorte oRebsorte = new Rebsorte();
		Object[] result = findObject((Object) oRebsorte,cFilter);
		Rebsorte[] res = new Rebsorte[result.length];
		for(int i = 0; i < result.length; i++)
			res[i] = (Rebsorte) result[i];
		
		return res;
	}
	
	//****************************************************************************
	// Die getRegionTable() gibt die RebsorteTabelle als Data Transfer Object zur���ck
	//****************************************************************************
	public Region[] getRegionTable(String cFilter) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{ 
		Region oRegion = new Region();
		Object[] result = findObject((Object) oRegion,cFilter);
		Region[] res = new Region[result.length];
		for(int i = 0; i < result.length; i++)
			res[i] = (Region) result[i];
		
		return res;
	}
	
	//****************************************************************************
	// Die getTypTable() gibt die RebsorteTabelle als Data Transfer Object zur���ck
	//****************************************************************************
	public Typ[] getTypTable(String cFilter) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{ 
		Typ oTyp = new Typ();
		Object[] result = findObject((Object) oTyp,cFilter);
		Typ[] res = new Typ[result.length];
		for(int i = 0; i < result.length; i++)
			res[i] = (Typ) result[i];
		
		return res;
	}
	
	//****************************************************************************
	// Die getWeingutTable() gibt die RebsorteTabelle als Data Transfer Object zur���ck
	//****************************************************************************
	public Weingut[] getWeingutTable(String cFilter) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{ 
		Weingut oWeingut = new Weingut();
		Object[] result = findObject((Object) oWeingut,cFilter);
		Weingut[] res = new Weingut[result.length];
		for(int i = 0; i < result.length; i++)
			res[i] = (Weingut) result[i];
		
		return res;
	}
	
	//****************************************************************************
	// Die getWeingutTable() gibt die RebsorteTabelle als Data Transfer Object zur���ck
	//****************************************************************************
	private Benutzer[] getBenutzer(String cFilter) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{ 
		Benutzer oBenutzer = new Benutzer();
		Object[] result = findObject((Object) oBenutzer,cFilter);
		Benutzer[] res = new Benutzer[result.length];
		for(int i = 0; i < result.length; i++)
			res[i] = (Benutzer) result[i];
		
		return res;
	}
	
	
	//############################################################################
	// Funktionen zur Aufbereitung der Daten.
	//############################################################################
	
	//****************************************************************************
	// Die Funktion postSQLStatment() stellt eine Verbindung zur Datenbank her
	// sendet den ���bergebenen SQL String, wartet auf die R���ckgabe der Datenbank
	// und schlie���t die Verbindung wieder.
	// Die R���ckgabe ist das Result aus dem SQL Statement.
	//****************************************************************************
	private ResultSet selectSQLStatement(String cSQL, String cFilter) throws ClassNotFoundException, SQLException{
		
		String url    = ServerConnection.GetDBConnectionString();
		String user   = ServerConnection.GetDBLoginUser();
		String userPw = ServerConnection.GetDBLoginPW();
		
		ResultSet results = null;
		Statement query = null;
		Connection connect = null;
		
		cSQL = cSQL.toUpperCase();
		if (global.gl_bAdmin && cFilter.length() != 0){
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
		System.out.println(cSQL);
		query = connect.createStatement();
   	    results = query.executeQuery(cSQL);
		return results;		
	}
	//****************************************************************************
	// Die Funktion postSQLStatment() stellt eine Verbindung zur Datenbank her
	// sendet den ���bergebenen SQL String, wartet auf die R���ckgabe der Datenbank
	// und schlie���t die Verbindung wieder.
	// Die R���ckgabe ist das Result aus dem SQL Statement.
	//****************************************************************************
	private void insertSQLStatement(String cSQL) throws ClassNotFoundException, SQLException{
		String url = ServerConnection.GetDBConnectionString();
		String user = ServerConnection.GetDBLoginUser();
		String userPw = ServerConnection.GetDBLoginPW();
		Statement query = null;
		Connection connect = null;
	
		//Execute Query
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection(url, user, userPw);
		Utils.prs("InsertSQLStatement", cSQL);
		query = connect.createStatement();
	    query.executeUpdate(cSQL);	
	}
	
	//****************************************************************************
	// Hashtable fuer Art
	//****************************************************************************		
	private void fillArtHt() throws ClassNotFoundException, SQLException, IOException, NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		Art[] art = getArtTable("");
		Hashtable <Integer,String> ht = new Hashtable<Integer,String>();
		
		for (int i = 0 ; i < art.length; i++){
			ht.put(art[i].getNr(), art[i].getBez());
		}
		ArtHt = ht;
	}
	
	//****************************************************************************
	// Hashtable fuer Land
	//****************************************************************************		
	private void fillLandHt() throws Exception {
		Land[] land = getLandTable("");
		Hashtable <Integer,String> ht = new Hashtable<Integer,String>();
		
		for (int i = 0 ; i < land.length; i++){
			ht.put(land[i].getNr(), land[i].getName());
		}
		LandHt = ht;
	}
	
	//****************************************************************************
	// Hashtable f��r Region
	//****************************************************************************		
	private void fillRebsorteHt() throws Exception{
		
		Rebsorte[] rebsorte = getRebsorteTable("");
		Hashtable <Integer,String> ht = new Hashtable<Integer,String>();
		
		for (int i = 0 ; i < rebsorte.length; i++){
			ht.put(rebsorte[i].getNr(), rebsorte[i].getName());
		}
		RebsorteHt = ht;
	}
	
	//****************************************************************************
	// Hashtable f��r Typ
	//****************************************************************************		
	private void fillTypHt() throws Exception {
		
		Typ[] typ = getTypTable("");
		Hashtable <Integer,String> ht = new Hashtable<Integer,String>();
		
		for (int i = 0 ; i < typ.length; i++){
			ht.put(typ[i].getNr(), typ[i].getBez());
		}
		TypHt = ht;
	}
	//****************************************************************************
	// Hashtable f��r Region
	//****************************************************************************		
	private void fillRegionHt() throws Exception{
		
		Region[] region = getRegionTable("");
		Hashtable <Integer,String> ht = new Hashtable<Integer,String>();
		
		for (int i = 0 ; i < region.length; i++){
			ht.put(region[i].getNr(), region[i].getName());
		}
		RegionHt = ht;
	}
	
	//****************************************************************************
	// Hashtable f��r Weingut
	//****************************************************************************		
	private void fillWeingutHt() throws Exception{
		
		Weingut[] weingut = getWeingutTable("");
		Hashtable <Integer,String> ht = new Hashtable<Integer,String>();
		
		for (int i = 0 ; i < weingut.length; i++){
			ht.put(weingut[i].getNr(), weingut[i].getName());
		}
		WeingutHt = ht;
	}

	

	
	//****************************************************************************
	// Die Funktion ���berpr���ft ob das Passwort des Mitarbeiters 
	//korrekt eingetippt wurde
	//****************************************************************************
	public String addFilter(String m_cFilter, String cFieldname, String cFieldValue){
	
		if (m_cFilter.length() > 1)
			m_cFilter += " AND ";
	
		if (!m_cFilter.contains(cFieldname))
			m_cFilter += cFieldname + " = '" + cFieldValue + "'";
		
		return m_cFilter;
	}
	
	//****************************************************************************
	// Die Funktion ���berpr���ft ob das Passwort des Mitarbeiters 
	//korrekt eingetippt wurde
	//****************************************************************************
	public boolean checkPW(String Username,String cPW) throws Exception{
		
		String cFilter ="";
		cFilter = "Username = '" + Username + "'";
		Benutzer[] oUser = getBenutzer(cFilter);
		if (oUser.length == 0)
			throw new Exception("Benutzer ist nicht vorhanden");
		
		Utils.prs("Passwort", oUser[0].getPassw());
		if(oUser[0].getPassw().equals(cPW))
			return true;
		
		else
			return false;
	}
	
	// Erstellt ein WeinDTO mit allen übergebenen Feldern an
	public Wein stringToWeinDto(int nId,String cWeinname, int nArtId, int nTyp, String cBeschreibung,double nPreis, int nJahrgang){
		
		Wein oWein = new Wein();
		oWein.setnr(nId);
		oWein.setart(nArtId);
		oWein.setbeschr(cBeschreibung);
		oWein.setjahrgang(nJahrgang);
		oWein.setname(cWeinname);
		oWein.setpreis(nPreis);
		oWein.settyp(nTyp);		
		return oWein;
	}
	
	//****************************************************************************
	// Die Funktion fügt einen neuen Datensatz zur Tabelle "Wein" hinzu oder
	// aktualisiert den Datensatz
	// Die Rückgabe ist der angefügte Datensatz
	//****************************************************************************
	public void refreshWeintable(Wein oWein) throws ClassNotFoundException, SQLException{
		
		// Wein ist noch nicht in der Datenbank vorhanden
		if (oWein.getnr() == -1)
			 insertWein(oWein); 
		else
			updateWein(oWein);
			
	}
	
	// Führt ein Update Befehl für die Tabelle durch. 
	private void updateWein(Wein oWein) throws ClassNotFoundException, SQLException{
		
		String cSQL = "";
		cSQL += "UPDATE wein SET name='" + oWein.getname() + "',";
		cSQL += "jahrgang =" + String.valueOf(oWein.getjahrgang()) + ";";
		cSQL += "beschr = '" + oWein.getbeschr() + "',";
		cSQL += "preis = " + String.valueOf(oWein.getpreis()) + ",";
		cSQL += "weingut = " + String.valueOf(oWein.getweingut()) + ",";  
		cSQL += "typ = " + String.valueOf(oWein.gettyp()) + ","; 
		cSQL += "art = " + String.valueOf(oWein.getart()) + ","; 
		insertSQLStatement(cSQL);
	}

	// Funktion erstellt in der Datenbank einen neuen Wein hinzu.
	private void insertWein(Wein oWein) throws ClassNotFoundException, SQLException {		
		String cSQL ="";
		
		cSQL += "INSERT INTO wein (name,jahrgang,beschr,preis,weingut,typ,art) ";
		cSQL += "VALUES('" + oWein.getname() + "', ";
		cSQL += String.valueOf(oWein.getjahrgang()) + ",";
		cSQL += "'" + oWein.getbeschr() + "',";
		cSQL += oWein.getpreis() + ",";
		cSQL += String.valueOf(oWein.getweingut()) + ",";
		cSQL += String.valueOf(oWein.getweingut()) + ",";
		cSQL += String.valueOf(oWein.getart()) + ")";
		insertSQLStatement(cSQL);
	}
	//****************************************************************************
	// Die Funktion getCustomerAdress() holt sich aus der Weinhandel Datenbank die 
	//Rechnungs und Lieferanschrift
	//****************************************************************************	
}
