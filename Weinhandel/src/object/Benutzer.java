package object;

public class Benutzer {
	
	private int    nID;
	private String cUsername;
	private String cPassw;
	
	public int getID(){
		return nID;
	}
	public void setID(int nID){
		this.nID = nID;
	}
	
	public String getUsername() {
		return cUsername;
	}
	public void setUsername(String cUsername) {
		this.cUsername = cUsername;
	}
	public String getPassw() {
		return cPassw;
	}
	public void setPassw(String cPassw) {
		this.cPassw = cPassw;
	}
}
