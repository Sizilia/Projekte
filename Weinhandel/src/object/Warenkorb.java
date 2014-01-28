package object;

public class Warenkorb {
	

	private int m_nBestellID;
	private int m_nBestellPos;
	private int m_nArtikelNr;
	private int m_nMenge;
	private float m_nPosSumme;
	
	public int getBestellID() {
		return m_nBestellID;
	}
	public void setBestellID(int m_nBestellID) {
		this.m_nBestellID = m_nBestellID;
	}
	public int getBestellPos() {
		return m_nBestellPos;
	}
	public void setBestellPos(int m_nBestellPos) {
		this.m_nBestellPos = m_nBestellPos;
	}
	public int getArtikelNr() {
		return m_nArtikelNr;
	}
	public void setArtikelNr(int m_nArtikelNr) {
		this.m_nArtikelNr = m_nArtikelNr;
	}
	public int getMenge() {
		return m_nMenge;
	}
	public void setMenge(int m_nMenge) {
		this.m_nMenge = m_nMenge;
	}
	public float getPosSumme() {
		return m_nPosSumme;
	}
	public void setPosSumme(float m_nPosSumme) {
		this.m_nPosSumme = m_nPosSumme;
	}
}
