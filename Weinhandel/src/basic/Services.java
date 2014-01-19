package basic;

import java.io.IOException;
import java.sql.*;

import object.Wein;

public class Services {
	
	private ResultSet createSQLStatement(String cSQL, String cFilter) throws SQLException, ClassNotFoundException{
		Connection c;
		String url = ServerConnection.GetDBConnectionString();
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
		c = DriverManager.getConnection(url, ServerConnection.GetDBLoginUser(), ServerConnection.GetDBLoginPW());
		Statement query = c.createStatement();
		Utils.prs("SQL-Statement", cSQL);
		ResultSet results = query.executeQuery(cSQL);
		return results;
	}
	
	// Finder Methode um SQL String auszuführen.
	// - Funktion überprüft ob im Where Teil Delete etc übergeben wurde, dies führt zum Fehler.
	private Wein[] findWein(String cFilter, String cOrderBy, int nMax) throws SQLException, IOException, ClassNotFoundException {
		java.util.Vector v=new java.util.Vector();
		Wein dto;
		String cSQL = "SELECT * FROM tbl_wein";
		ResultSet results = createSQLStatement(cSQL, cFilter);
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
	
	public  Wein[] getWeintable(String cFilter) throws SQLException, IOException, ClassNotFoundException{ 
			return findWein(cFilter,"",-1);
	}
	
	
	// Diese Funktion legt nach Modus einen Datensatz an / löscht einen Datensatz / verändert die 
	// Feldwerte des Datensatzes.
	private String refreshDataBase(String TableName, int PK, String Values , boolean newDS){
		
		// Wenn Datensatz noch nicht vorhanden ist.
		if(newDS){
			
		   return "Neuer Datensatz wurde angelegt";	
		}
		// Datensatz muss gelöscht werden.
		else if( Values.length() == 0){
			
			return "Datensatz wurde gelöscht";
		}
		// Datensatz wird angepasst.
		else{
			return "angelegt.";
		}
	}
}
