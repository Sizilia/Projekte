package object;

public class Region {

	private int m_nNr, m_LandID;
	private String m_cName, m_Beschr;
	
	public int getNr(){
		return this.m_nNr;
	}
	public void setNr(int m_nNr){
		this.m_nNr = m_nNr;
	}
	
	public String getName(){
		return this.m_cName;
	}
	public void setName(String m_cName){
		this.m_cName = m_cName;
	}
	
	public String getBeschr() {
		return m_Beschr;
	}
	public void setBeschr(String m_Beschr) {
		this.m_Beschr = m_Beschr;
	}
	
	public int getLandID() {
		return m_LandID;
	}
	public void setLandID(int m_landID) {
		this.m_LandID = m_landID;
	}
	
	
}
