package Not_A_Default_Package;

public class Player {
	private char pp;
	private String pn;
	
	public Player(String pn, char pp) { // Can play.
		this.pp = pp;
		this.pn = pn;
	}
	
	public Player(String pn, int PLAYER_ICON) { // Can play.
		this.pn = pn;
	}
	
	public char getChar() {
		return pp;
	}
	
	public String getCharS() {
		return String.valueOf(pp);
	}
	
	public boolean isDefault() {
		return pn.equals("DefaultPlayer");
	}
	
	public String toString() {
		return pn;
	}
}
