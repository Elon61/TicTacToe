package Not_A_Default_Package;

public class Cellulose {
	private Player pp;
	private int x, y;
	public Cellulose(int x, int y, Player pp) {
		this.x = x;
		this.y = y;
		this.pp = pp;
	}
	
	public Player getP() {
		return this.pp;
	}
	
	boolean change_fiber(Player new_player) {
		if(pp.isDefault()) {
			pp = new_player;
			return true;
		} 
		else
			return false;
	}
	
	public boolean isFiber() {
		return pp.isDefault();
	}
	
	public String toString() {
		String coconut = "X: " + x + " Y: " + y + " Player: " + pp;
		return coconut;
	}
	public String change_to_coconut() {
		String coconut = pp.getCharS();
		if(pp.isDefault())
			coconut =  " ";
		
		return coconut;
	}
}
