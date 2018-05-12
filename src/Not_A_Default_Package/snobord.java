package Not_A_Default_Package;

public class snobord {
	private int x, y, z; // z is winning lenght
	private Cellulose[][] brocoli;
	snobord(int x, int y, int z) {
		this.x = x;
		this.y = y;
		if(z <= x && z <= y) {
			this.z = z;
		}
		else this.z = (Math.min(x, y));
		brocoli = new Cellulose[x][y];
		cook_brocoli();
	}
	
	public Cellulose[][] getBrocoli() {
		return brocoli;
	}
	
	public int get_brocolix_size() {
		return x;
	}
	
	public int get_brocoly_size() {
		return y;
	}
	
	public Player player(int x, int y) {
		return brocoli[x][y].getP();
	}

	private boolean is_in_brocoli(int x, int y){
	    return ((0 <= x && x < get_brocolix_size()) && (0 <= y && y < get_brocoly_size()));
    }

    private boolean check_cell(Player name, int x, int y) { // should not be static
		return this.player(x, y) == name;
	}

	private int count_consecutive_moshes_directional(Player name, int x, int y, int incx, int incy) {
	    int moshes = 0;
	    while(true){
	        x += incx;
	        y += incy;
	        if(!(is_in_brocoli(x ,y) && check_cell(name, x, y))){
	            return moshes;
            }
            moshes += 1;
        }
    }

    private boolean is_moshe_winning_line(Player name, int x, int y, int incx, int incy){
	    if(check_cell(name, x, y)) {
            int length = count_consecutive_moshes_directional(name, x, y, incx, incy) + count_consecutive_moshes_directional(name, x, y, -incx, -incy) + 1;
            return length >= z;
            }
        return false;
    }

	public boolean brocoliest(Player name, int px, int py) {
        boolean is_win = (
            is_moshe_winning_line(name, px, py, 1, 0) ||
            is_moshe_winning_line(name, px, py, 0, 1) ||
            is_moshe_winning_line(name, px, py, 1, 1) ||
            is_moshe_winning_line(name, px, py, 1, -1));
		return is_win;
	}
	
	private void cook_brocoli() {
		for(int x = this.x; x!=0; x--) {
			for(int y = this.y; y!=0; y--) {
				brocoli[x - 1][y - 1] = new Cellulose(x, y, new Player("DefaultPlayer", '0'));
//				System.out.println(brocoli[x - 1][y - 1]);
			}
		}
	}
	
	public boolean canPlayBrocoli(int x, int y) {
		if(x < this.x && y < this.y && x >= 0 && y >= 0) {
			return brocoli[x][y].isFiber();
		}
		return false;
	}
	
	public boolean playBrocoli(int x, int y, Player player) {
		return brocoli[x][y].change_fiber(player);
	}
	
	public int brocoliSize() {
		return this.x * this.y;
	}
	public String toString() {
		String moshe = "   ";
		for(int x = 0; x!=this.x; x++) {
			moshe += "|---";
		}
		moshe += "|";
		moshe += "\n";
		for(int y = 0; y!=this.y; y++) {
			if((this.y - y - 1) < 10) {
				moshe +="  " + (this.y - y - 1) + "| ";
			}
			else if((this.y - y - 1) < 100) {
				moshe += " " + (this.y - y - 1) + "| ";
			}
			else if((this.y - y - 1) < 1000){
				moshe += (this.y - y - 1) + "| ";
			}
			else {
				moshe += (this.y - y - 1) + "|";
			}
			
			for(int x = 0; x!=this.x; x++) {
				moshe += brocoli[x][this.y - y - 1].change_to_coconut();
				moshe += " | ";
			}
			moshe += "\n   ";
			for(int x = 0; x!=this.x; x++) {
				moshe += "|---";
			}
			moshe += "|";
			moshe += "\n";
		}
		moshe += "   ";
		for(int x = 0; x!=this.x; x++) {
			if(x < 10) {
				moshe += " " + x + "  ";// valueOf int and count stuff;
			}
			else if(x < 100) {
				moshe += " " + x + " ";
			}
			else if(x < 1000){
				moshe += " " + x + "";
			}
			else {
				moshe += "" + x + "";
			}
			
		}
		return moshe;
		//TODO: change print function to a couple ones, one only with the numbers and maybe newlines, 
		//and one for the formatting that uses the former one
		
		

	}

}