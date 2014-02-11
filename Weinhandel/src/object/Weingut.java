package object;

public class Weingut {
	
	private int m_nNr;
	private String m_cName;
	private int m_nRegion;
	
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
	public int getRegionID(){
		return this.m_nRegion;
	}
	public void setRegionID(int m_nRegion){
		this.m_nRegion = m_nRegion;
	}
}
