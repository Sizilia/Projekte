package object;

public class Region {

	private int m_nNr;
	private String m_cBez;
	
	public int getNr(){
		return this.m_nNr;
	}
	public void setNr(int m_nNr){
		this.m_nNr = m_nNr;
	}
	
	public String getBez(){
		return this.m_cBez;
	}
	public void setBez(String m_cBez){
		this.m_cBez = m_cBez;
	}
}
