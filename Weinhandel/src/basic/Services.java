package basic;

import java.io.IOException;
import java.sql.*;

import object.Wein;

public class Services {

	private Wein[] findWein(String cFilter, String cOrderBy, int nMax) throws SQLException, IOException, ClassNotFoundException {
		java.util.Vector v=new java.util.Vector();
		Wein dto;
		Connection c;
		String url = ServerConnection.GetDBConnectionString();
		String sqlStatement = "Select NONSTD_004 as Kundennummer,substring(KOMMISS, 1,6) as Kundenauftragsnummer,substring(KOMMISS, 7,3) as Kundenauftragsnummerposition,BESTPOSNR as Barcode,SOLL as Planmenge,IST as Istmenge,iif(X_TERMNEU is null,substring(TERM_INDEX,1,8),X_TERMNEU) as Wunschtermin,DAB010.ARTNR as Artikelnummer,NONSTD_007 as Revision,DAB010.ARTNR as Zeichnungsnummer,X_PRIORI as Prioritaet,substring(TERM_INDEX,9,1) as WunschterminArt,X_KDWUNSCH as KundenWunschtTermin,DAB010.X_PFAD1 as ZeichnungsPfad,DAB035.AKA as AK_Gedruckt,BEZ1 as Bezeichnung1,BEZ2 as Bezeichnung2,NONSTD_007 as \"Index\"  from \"DAB035.ADT\" DAB035 inner join \"DAB240.ADT\" DAB240 on DAB035.BESTPOSNR+'000'  = DAB240.B_POS_LFD inner join \"DAB010.DBF\" DAB010 on DAB035.ARTNR = DAB010.ARTNR where LIEFNR=-1 and DAB010.standort=0  " + cOrderBy;
		sqlStatement = sqlStatement.toUpperCase();
		if (cFilter.length() != 0) {
			//Exception on edit-statements
			cFilter = cFilter.toUpperCase();
			int nPosDel = cFilter.indexOf("DELETE");
			int nPosIns = cFilter.indexOf("INSERT");
			int nPosUpd = cFilter.indexOf("UPDATE");
			int nPosDrp = cFilter.indexOf("DROP");
			if (nPosDel >= 0 || nPosIns >= 0 || nPosUpd >= 0 || nPosDrp>=0 )
				throw new IOException("Edit statements are not allowed!");
			//insert filter in sql-statement
			int nPos = sqlStatement.indexOf("WHERE");
			int nPosOrd = sqlStatement.indexOf("GROUP BY");
			int nPosGrp = sqlStatement.indexOf("ORDER BY");
			String sqlOrdGrp = "";
			if (nPosOrd > 0 || nPosGrp > 0) {
				sqlOrdGrp = sqlStatement.substring(Math.max(nPosOrd, nPosGrp));
				sqlStatement = sqlStatement.substring(0, Math.max(nPosOrd, nPosGrp));
			}
			if (nPos >= 0)
				sqlStatement = sqlStatement + " and " +cFilter+" "+ sqlOrdGrp;
			else
				sqlStatement = sqlStatement + " where " +cFilter+" "+ sqlOrdGrp;
		}
		//Execute Query
		Class.forName("com.mysql.jdbc.Driver");
		c = DriverManager.getConnection(url, ServerConnection.GetDBLoginUser(), ServerConnection.GetDBLoginPW());
		Statement query = c.createStatement();
		Utils.prs("SQL-Statement", sqlStatement);
		ResultSet results = query.executeQuery(sqlStatement);
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
	
	public  Wein[] getWeintable() throws SQLException, IOException, ClassNotFoundException{ 

			return findWein("","",-1);
	}
	
	public Services() throws SQLException, IOException, ClassNotFoundException{
		getWeintable();
	}
	
	
	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException{
		
		new Services();
	}
}
