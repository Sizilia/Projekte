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


/** Die Service Klasse stellt die Verbindung zur Weinhandel Datenbank her.
  *  Sie enthaelt die Methoden zum Ausfuehren der Datenoperationen die von der
  *  Oberflaeche gesendet werden.
  * @author Sebastian Wötzel
  * @WSDL-File http://localhost:8080/Weinhandel/wsdl/Services.wsdl
 */
public class Services {

	private  Globals global = new  Globals();
	
	//############################################################################
	// Finder der einzelnen DTO's
	//############################################################################
	
	/** Finder Methode um SQL String auszufuehren.
	// - Funktion ueberprueft ob im Where Teil Delete etc uebergeben wurde,
	// dies fuehrt zum Fehler.
	 * @param obj Cast Object.
	 * @param cFilter SQL Filter für Tabelle
	 * @return Object[] befülltes DTO
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
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
		switch(cTablename.toUpperCase()){
		case "WEIN"   : cSQL = global.gl_WeinSQL;
						break;
		case "WEINGUT": cSQL = global.gl_WeingutSQL;
						break;
		case "ART"    : cSQL = global.gl_ArtSQL;
						break;
		case "LAND"   : cSQL = global.gl_LandSQL;
						break;
		case "BENUTZER": cSQL = global.gl_BenutzerSQL;
		} 
		java.util.Vector<Object> v = new java.util.Vector<Object>();
		ResultSet results          = selectSQLStatement(cSQL,cFilter);
		
		String cFieldName = "";
		Object arglist[] = new Object[1];
		Object oResObj;
		
		boolean notDone = results.next();
		while(notDone) { // Schleife über SQL Tabelle
			obj = new Object();
			Method[] methods = obj.getClass().getDeclaredMethods();
			
			for( Method m : methods){	// Schleife über alle Methoden
				cFieldName = m.getName();
				//System.out.println( m.getTypeParameters().getClass());
				if( cFieldName.contains("set") &&! cFieldName.contains("Wein_Rebsorten")){
					cFieldName = cFieldName.replace("set","");
					oResObj = results.getObject(cFieldName);
					if(oResObj != null){
					//	System.out.println("Feldname " + cFieldName);
					//	System.out.println("Wert "+ oResObj);
						if(oResObj instanceof java.math.BigDecimal)
							arglist[0] =  results.getDouble(cFieldName); 
						else
							arglist[0] = oResObj; 
							
						m.invoke(obj, arglist);
						arglist[0] = null;
					}
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
		
	/** Die Funktion gibt die Wein Tabelle als Data Transfer Object zurueck
	 * @param cFilter Filter für Weintabelle
	 * @return Wein DTO
	 * @throws Exception 
	 */
	public Wein[] getWeintable(String cFilter) throws Exception{
		Wein oWein = new Wein();
		Object[] result = findObject((Object) oWein,cFilter);
		Wein[] res = new Wein[result.length];
		for(int i = 0; i < result.length; i++)
			res[i] = (Wein) result[i];
		
		// ToDo : Logik für Hashtable hinzugügen wenn Zeit vorhanden ist.
		
		return res;
	}
	
	/** Die Funktion gibt die Art Tabelle als Data Transfer Object zurueck
	 * @param cFilter Filter für Arttabelle
	 * @return Art DTO
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Art[] getArtTable(String cFilter) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{ 
		Art oArt = new Art();
		Object[] result = findObject((Object) oArt,cFilter);
		Art[] res = new Art[result.length];
		for(int i = 0; i < result.length; i++)
			res[i] = (Art) result[i];
		
		return res;
	}
	
	/** Die Funktion gibt die Land Tabelle als Data Transfer Object zurueck
	 * @param cFilter Filter für Landtabelle
	 * @return Land DTO
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Land[] getLandTable(String cFilter) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{ 
		Land oLand = new Land();
		Object[] result = findObject((Object) oLand,cFilter);
		Land[] res = new Land[result.length];
		for(int i = 0; i < result.length; i++)
			res[i] = (Land) result[i];
		
		return res;
	}

	/** Die Funktion gibt die Rebsorten Tabelle als Data Transfer Object zurueck
	 * @param cFilter Filter für Rebsortentabelle
	 * @return Rebsorten DTO
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */public Rebsorte[] getRebsorteTable(String cFilter) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{ 
		Rebsorte oRebsorte = new Rebsorte();
		Object[] result = findObject((Object) oRebsorte,cFilter);
		Rebsorte[] res = new Rebsorte[result.length];
		for(int i = 0; i < result.length; i++)
			res[i] = (Rebsorte) result[i];
		getWein_RebsorteTable("");
		return res;
	}
	
	/** Die Funktion gibt die komplette Rebsortentabelle zurueck
	 * @param  SQL Filter für die Wein_Rebsortentabelle
	 * @return Die Rückgabe ist die Wein_Rebsorten Tabelle
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Wein_Rebsorte[] getWein_RebsorteTable(String cFilter) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{ 
		Wein_Rebsorte oWein_Rebsorte = new Wein_Rebsorte();
		Object[] result = findObject((Object) oWein_Rebsorte,cFilter);
		Wein_Rebsorte[] res = new Wein_Rebsorte[result.length];
		for(int i = 0; i < result.length; i++)
			res[i] = (Wein_Rebsorte) result[i];
		
		return res;
	}
	
	/** Die Funktion gibt die Region Tabelle als Data Transfer Object zurueck
	 * @param cFilter Filter für Regiontabelle
	 * @return Region DTO
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Region[] getRegionTable(String cFilter) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{ 
		Region oRegion = new Region();
		Object[] result = findObject((Object) oRegion,cFilter);
		Region[] res = new Region[result.length];
		for(int i = 0; i < result.length; i++)
			res[i] = (Region) result[i];
		
		return res;
	}
	
	/** Die Funktion gibt die Typ Tabelle als Data Transfer Object zurueck
	 * @param cFilter Filter für Typtabelle
	 * @return Typ DTO
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */public Typ[] getTypTable(String cFilter) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{ 
		Typ oTyp = new Typ();
		Object[] result = findObject((Object) oTyp,cFilter);
		Typ[] res = new Typ[result.length];
		for(int i = 0; i < result.length; i++)
			res[i] = (Typ) result[i];
		
		return res;
	}
	
	 
	
	/** Die Funktion gibt die Weingut Tabelle als Data Transfer Object zurueck
	 * @param cFilter Filter für Weinguttabelle
	 * @return Weingut DTO
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Weingut[] getWeingutTable(String cFilter) throws SQLException, IOException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{ 
		Weingut oWeingut = new Weingut();
		Object[] result = findObject((Object) oWeingut,cFilter);
		Weingut[] res = new Weingut[result.length];
		for(int i = 0; i < result.length; i++)
			res[i] = (Weingut) result[i];
	
		return res;
	}
	
	/** Die Funktion gibt die Benutzer Tabelle als Data Transfer Object zurueck
	 * @param cFilter Filter für Benutzertabelle
	 * @return Benutzer DTO
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
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
	
	/**Die Funktion stellt eine Verbindung zur Datenbank her
	// sendet den uebergebenen SQL String, wartet auf die Rueckgabe der Datenbank
	// und schliesst die Verbindung wieder.
	 * @param cSQL SQL Statement ohne Where Clause
	 * @param cFilter SQL Filter Statement
	 * @return Resultset
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private ResultSet selectSQLStatement(String cSQL, String cFilter) throws ClassNotFoundException, SQLException{
		
		String url    = ServerConnection.GetDBConnectionString();
		String user   = ServerConnection.GetDBLoginUser();
		String userPw = ServerConnection.GetDBLoginPW();
		
		ResultSet results = null;
		Statement query = null;
		Connection connect = null;
		
		//cSQL = cSQL.toUpperCase();
		if (!global.gl_bAdmin && cFilter.length() != 0){
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
		System.out.println("SQL Statement" + cSQL);
		query = connect.createStatement();
   	    results = query.executeQuery(cSQL);
		return results;		
	}

	/** Die Funktion führt einen Insert SQL Statement auf der Weinhandeldatenbank aus
	 * @param cSQL Insert SQL Statement
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
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
	
	
		
	/** Befuellt die Informationen aller Rebsorten alles in eine Hashtabelle
	 * @return
	 * @throws Exception
	 */
	private Hashtable <Integer,Wein_Rebsorte> fillRebsorteHt() throws Exception{
		
		Wein_Rebsorte[] oWein_Rebsorte = getWein_RebsorteTable("");
		Hashtable <Integer,Wein_Rebsorte> ht = new Hashtable<Integer,Wein_Rebsorte>();
		
		for (int i = 0 ; i < oWein_Rebsorte.length; i++){
			ht.put(oWein_Rebsorte[i].getRebsortennummer(), oWein_Rebsorte[i]);
		}
		return  ht;
	}
	

	/** Funktion fuegt einen SQL Filter zum vorhanden Filter hinzu.
	 * @param m_cFilter
	 * @param cFieldname
	 * @param cFieldValue
	 * @return neuer Filter mit hinzugefuegtem Statement
	 */
	public String addFilter(String m_cFilter, String cFieldname, String cFieldValue){
	
		if (m_cFilter.length() > 1)
			m_cFilter += " AND ";
	
		if (!m_cFilter.contains(cFieldname))
			m_cFilter += cFieldname + " = '" + cFieldValue + "'";
		
		return m_cFilter;
	}
	
	/** Die Funtkion such in der Datenbank nach dem Benutzer und ueberprueft ob das
	 * eingegebene Passwort Korrekt ist.
	 * @param Username
	 * @param cPW
	 * @return boolean Wenn Eingabe mit User und Passwort korrekt ist.
	 * @throws Exception
	 */
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
	
	
	/** Die Funktion wandelt aus den einzelnen Werten ein Wein DTO zusammen.
	 * @param nId
	 * @param cWeinname
	 * @param nArtId
	 * @param nTyp
	 * @param cBeschreibung
	 * @param nPreis
	 * @param nJahrgang
	 * @return
	 */
	public Wein stringToWeinDto(int nId,String cWeinname, int nArtId, int nTyp, String cBeschreibung,double nPreis, int nJahrgang){
		
		Wein oWein = new Wein();
		oWein.setWeinnummer(nId);
		oWein.setWeinartnummer(nArtId);
		oWein.setWeinbeschreibung(cBeschreibung);
		oWein.setWeinjahrgang(nJahrgang);
		oWein.setWeinname(cWeinname);
		oWein.setEinzelpreis(nPreis);
		oWein.setWeintypnummer(nTyp);
		return oWein;
	}

	/** Die Funktion ueberprueft anhand der WeinID ob es den Wein bereits in der Datenbank
	 * gibt, wenn nicht wird dieser neu angelegt.
	 * @param oWein
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void refreshWeintable(Wein oWein) throws ClassNotFoundException, SQLException{
		// Wein ist noch nicht in der Datenbank vorhanden
		if (oWein.getWeinnummer() == -1)
			 insertWein(oWein); 
		else
			updateWein(oWein);
	}
	
	/** Loescht einen Datensatz in der Weintabelle
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * 
	 */
	public void deleteWeinDS(Wein oWein) throws ClassNotFoundException, SQLException{
		String cSQL = "";
		cSQL = "DELETE FROM wein WHERE wein.nr = " + String.valueOf(oWein.getWeinnummer()); 
		insertSQLStatement(cSQL);	
	}


	/** Die Funktion fuehrt ueber den SQL Update Befehl eine aktualiserung auf 
	 * @param oWein
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void updateWein(Wein oWein) throws ClassNotFoundException, SQLException{
		String cSQL = "";
		cSQL += "UPDATE wein SET name='" + oWein.getWeinname() + "',";
		cSQL += "jahrgang =" + String.valueOf(oWein.getWeinjahrgang()) + ";";
		cSQL += "beschr = '" + oWein.getWeinbeschreibung() + "',";
		cSQL += "preis = " + String.valueOf(oWein.getEinzelpreis()) + ",";
		cSQL += "weingut = " + String.valueOf(oWein.getWeingutnummer()) + ",";  
		cSQL += "typ = " + String.valueOf(oWein.getWeintypnummer()) + ","; 
		cSQL += "art = " + String.valueOf(oWein.getWeinartnummer()) ; 
		cSQL += " WHERE wein.nr =" + String.valueOf(oWein.getWeinnummer());
		insertSQLStatement(cSQL);
	}
	
	/** Die Funktion fuegt ueber dden SQL Insert Befehl einen neuen Datensatz hinzu
	 * @param oWein  Wein DTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void insertWein(Wein oWein) throws ClassNotFoundException, SQLException {		
		String cSQL ="";
		cSQL += "INSERT INTO wein (name,jahrgang,beschr,preis,weingut,typ,art) ";
		cSQL += "VALUES('" + oWein.getWeinname() + "', ";
		cSQL += String.valueOf(oWein.getWeinjahrgang()) + ",";
		cSQL += "'" + oWein.getWeinbeschreibung() + "',";
		cSQL += oWein.getEinzelpreis() + ",";
		cSQL += String.valueOf(oWein.getWeingutnummer()) + ",";
		cSQL += String.valueOf(oWein.getWeintypnummer()) + ",";
		cSQL += String.valueOf(oWein.getWeinartnummer()) + ")";
		insertSQLStatement(cSQL);
	}
	
}
