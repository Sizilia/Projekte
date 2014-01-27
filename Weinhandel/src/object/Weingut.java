package object;

public class Weingut {
	
	private int m_nNr;
	private String m_cName;
	private int m_nRegion;
	
	public int getnr(){
		return this.m_nNr;
	}
	public void setnr(int m_nNr){
		this.m_nNr = m_nNr;
	}
	public String getname(){
		return this.m_cName;
	}
	public void setname(String m_cName){
		this.m_cName = m_cName;
	}
	public int getregion(){
		return this.m_nRegion;
	}
	public void setregion(int m_nRegion){
		this.m_nRegion = m_nRegion;
	}
}
