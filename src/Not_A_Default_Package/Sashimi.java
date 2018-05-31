package Not_A_Default_Package;

public class Sashimi {
	snobord board;
	int x, y, z, p;
	Player[] players;

	public Sashimi(int x, int y, int z, Player[] players) { // board size and win size
		this.x = x; this.y = y; this.z = z; this.players = players; this.p = 0;
		board = new snobord(x, y, z);
	}

	public boolean play(int x, int y) { // played location and player who played there
		return board.playBrocoli(x, y, players[p]);
	}

	public void nextPlayer(){
		p = (p + 1) % players.length;
	}

	public boolean win(int x, int y) {
		return board.brocoliest(players[p], x, y);
	}

	public snobord getBoard() {
		return board;
	}

    public void reset() {
        board = new snobord(x, y, z);
        p = 0;
    }

    public Cellulose[][] getCellBoard() {
        return board.getBrocoli();
    }

    public static Player[] blade(int nuuu, String[] pls, String[] picz, int[] plc) { // create players
        Player[] pa = new Player[nuuu];
        for(int i = 0; i < nuuu; i++) {
            pa[i] = new Player(pls[i], picz[i], plc[i]);
        }
        return pa;
    }

    public static Player[] blade(int nmp) { // create players
        Player[] pa = new Player[nmp];
        for(int i = 0; i < nmp; i++) {
            pa[i] = new Player(String.valueOf(i), "null", i);
        }
        return pa;
    }

    public Player getPlayer() {
        return players[p];
    }
}