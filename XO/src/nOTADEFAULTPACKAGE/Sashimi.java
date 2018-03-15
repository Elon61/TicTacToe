package nOTADEFAULTPACKAGE;

import java.awt.Color;

public class Sashimi {
	snobord board;
	int x, y, z;

	public Sashimi(int x, int y, int z) { // board size and win size
		this.x = x; this.y = y; this.z = z;
		board = new snobord(x, y, z);
	}
	
	public boolean play(int x, int y, Player p) { // played location and player who played there
		return board.playBrocoli(x, y, p);
	}
	
	public boolean win(int x, int y, Player p) {
		return board.brocoliest(p, x, y);
	}
	
	public Celullose[][] getBoard() {
		return board.getBrocoli();
	}
}
