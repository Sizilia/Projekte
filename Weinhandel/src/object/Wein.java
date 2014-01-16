package object;

public class Wein {
	
	private int m_nr;
	private String m_name;
	private int m_jahrgang;
	private String m_beschr;
	private float m_preis;
	private int m_weingut;
	private int m_typ;
	private int m_art;
	
	public int getnr() {
		return m_nr;
	}
	public void setnr(int m_nr) {
		this.m_nr = m_nr;
	}
	public String getname() {
		return m_name;
	}
	public void setname(String m_name) {
		this.m_name = m_name;
	}
	public int getjahrgang() {
		return m_jahrgang;
	}
	public void setjahrgang(int m_jahrgang) {
		this.m_jahrgang = m_jahrgang;
	}
	public String getbeschr() {
		return m_beschr;
	}
	public void setbeschr(String m_beschr) {
		this.m_beschr = m_beschr;
	}
	public float getpreis() {
		return m_preis;
	}
	public void setpreis(float m_preis) {
		this.m_preis = m_preis;
	}
	public int getweingut() {
		return m_weingut;
	}
	public void setweingut(int m_weingut) {
		this.m_weingut = m_weingut;
	}
	public int gettyp() {
		return m_typ;
	}
	public void settyp(int m_typ) {
		this.m_typ = m_typ;
	}
	public int getart() {
		return m_art;
	}
	public void setart(int m_art) {
		this.m_art = m_art;
	}

}
