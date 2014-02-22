package object;

public class Wein {
	
	// Felder aus wein.tbl:
	// Weinnummer, Weinname, Weinjahrgang, Weinbeschreibung, Einzelpreis
	private int    Weinnummer;

	private String Weinname;
	private int    Weinjahrgang;
	private String Weinbeschreibung;
	private double Einzelpreis;

	private int    Weingutnummer;
	private int    Weinartnummer;
	private int    Weintypnummer;
	
	// zusätzlich für die Informationen aus INNER JOIN:
	private String Weingutname;
	private String Regionname;
	private String Weintypbeschreibung;
	
	private String Regionbeschreibung;
	private String Landname;
	private String Artbezeichnung;
	private Wein_Rebsorte[] Wein_Rebsorten ;
	
	
	
	public String getWeintypbeschreibung() {
		return Weintypbeschreibung;
	}
	public void setWeintypbeschreibung(String weintypbeschreibung) {
		Weintypbeschreibung = weintypbeschreibung;
	}
	
	public int getWeingutnummer() {
		return Weingutnummer;
	}
	public void setWeingutnummer(int weingutnummer) {
		Weingutnummer = weingutnummer;
	}
	public int getWeinartnummer() {
		return Weinartnummer;
	}
	public void setWeinartnummer(int weinartnummer) {
		Weinartnummer = weinartnummer;
	}
	public int getWeintypnummer() {
		return Weintypnummer;
	}
	public void setWeintypnummer(int weintypnummer) {
		Weintypnummer = weintypnummer;
	}
	
	public int getWeinnummer() {
		return Weinnummer;
	}
	public void setWeinnummer(int weinnummer) {
		Weinnummer = weinnummer;
	}
	public String getWeinname() {
		return Weinname;
	}
	public void setWeinname(String weinname) {
		Weinname = weinname;
	}
	public int getWeinjahrgang() {
		return Weinjahrgang;
	}
	public void setWeinjahrgang(int weinjahrgang) {
		Weinjahrgang = weinjahrgang;
	}
	public String getWeinbeschreibung() {
		return Weinbeschreibung;
	}
	public void setWeinbeschreibung(String weinbeschreibung) {
		Weinbeschreibung = weinbeschreibung;
	}
	public double getEinzelpreis() {
		return Einzelpreis;
	}
	public void setEinzelpreis(double einzelpreis) {
		Einzelpreis = einzelpreis;
	}
	public String getWeingutname() {
		return Weingutname;
	}
	public void setWeingutname(String weingutname) {
		Weingutname = weingutname;
	}
	public String getRegionname() {
		return Regionname;
	}
	public void setRegionname(String regionname) {
		Regionname = regionname;
	}
	public String getRegionbeschreibung() {
		return Regionbeschreibung;
	}
	public void setRegionbeschreibung(String regionbeschreibung) {
		Regionbeschreibung = regionbeschreibung;
	}
	public String getLandname() {
		return Landname;
	}
	public void setLandname(String landname) {
		Landname = landname;
	}
	public String getArtbezeichnung() {
		return Artbezeichnung;
	}
	public void setArtbezeichnung(String artbezeichnung) {
		Artbezeichnung = artbezeichnung;
	}
	
	public Wein_Rebsorte[] getWein_Rebsorten() {
		return Wein_Rebsorten;
	}
	public void setWein_Rebsorten(Wein_Rebsorte[] Wein_rebsorten) {
		Wein_Rebsorten = Wein_rebsorten;
	}
}
	
