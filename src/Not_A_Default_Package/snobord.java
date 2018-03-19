package Not_A_Default_Package;

public class snobord {
	private int x, y, z; // z is winning lenght
	private Celullose[][] brocoli;
	snobord(int x, int y, int z) {
		this.x = x;
		this.y = y;
		if(z <= x && z <= y) {
			this.z = z;
		}
		else this.z = (Math.min(x, y));
		brocoli = new Celullose[x][y];
		cook_brocoli();
	}
	
	public Celullose[][] getBrocoli() {
		return brocoli;
	}
	
	public int get_brocolix() {
		return x;
	}
	
	public int get_brocoly() {
		return y;
	}
	
	public Player player(int x, int y) {
		return brocoli[x][y].getP();
	}
	
	public static boolean check_cell(snobord teemo, Player name, int x, int y) {
		return teemo.player(x, y) == name;
	}
	
	public static int moshe_vert(snobord teemo, Player name, int x, int y) {
		int l = 1, t = 1, max = teemo.get_brocolix();
		for(int i = 1; i < Math.min(y + 1, teemo.get_brocoly() - y); i++) {
			if(!check_cell(teemo, name, x, y-i) || !check_cell(teemo, name, x, y+i)) {
				t = i;
				break;
			}
			l += 2;
			t = i;
		}
		
		for(int i = t; y + i < max; i++) {
			if(!check_cell(teemo, name, x, y+i)) {
				break;
			}
			l++;
		}
		
		for(int i = t; y-i >= 0; i++) {
			if(!check_cell(teemo, name, x, y-i)) {
				break;
			}
			l++;
		}
		return l;
	}
	
	public static int moshe_horizontal(snobord teemo, Player name, int x, int y) {
		int l = 1, t = 1, max = teemo.get_brocolix();
		for(int i = 1; i < Math.min(x + 1, teemo.get_brocolix() - x); i++) {
			if(!check_cell(teemo, name, x-i, y) || !check_cell(teemo, name, x+i, y)) {
				t = i;
				break;
			}
			l += 2;
			t = i + 1;
		}
		
		
		for(int i = t; x + i < max; i++) {
			if(!check_cell(teemo, name, x+i, y)) {
				break;
			}
			l++;
		}
		
		for(int i = t; x-i >= 0; i++) {
			if(!check_cell(teemo, name, x-i, y)) {
				break;
			}
			l++;
		}
		return l;
	}
	
	public static int moshe_left_up(snobord teemo, Player name, int x, int y) {
		int l = 1, t = 1, max = teemo.get_brocolix(), may = teemo.get_brocoly(), mint = Math.min(Math.min(x + 1, teemo.get_brocolix() - x), Math.min(y + 1, teemo.get_brocoly() - y));
		for(int i = 1; i < mint; i++) {
			if(!check_cell(teemo, name, x + i, y + i) || !check_cell(teemo, name, x - i, y - i)) {
				t = i;
				break;
			}
			l += 2;
			t = i + 1;
		}
		
		
		for(int i = t; x + i < max && y + i < may; i++) {
			if(!check_cell(teemo, name, x + i, y + i)) {
				break;
			}
			l++;
		}
		for(int i = t; x - i >= 0 && y - i >= 0; i++) {
			if(!check_cell(teemo, name, x - i, y - i)) {
				break;
			}
			l++;
		}
		
		return l;
	}
	
	public static int moshe_left_down(snobord teemo, Player name, int x, int y) {
		int l = 1, t = 1, max = teemo.get_brocolix(), may = teemo.get_brocoly(), mint = Math.min(Math.min(x + 1, teemo.get_brocolix() - x), Math.min(y + 1, teemo.get_brocoly() - y));
		for(int i = 1; i < mint; i++) {
			if(!check_cell(teemo, name, x-i, y+i) || !check_cell(teemo, name, x+i, y-i)) {
				t = i;
				break;
			}
			l += 2;
			t = i + 1;
		}
		
		for(int i = t; x + i < max && y - i >= 0; i++) {
			if(!check_cell(teemo, name, x+i, y-i)) {
				break;
			}
			l++;
		}
		
		for(int i = t; x - i >= 0 && y + i < may; i++) {
			if(!check_cell(teemo, name, x-i, y+i)) {
				break;
			}
			l++;
		}
		return l;
	}
	
	public boolean brocoliest(Player name, int px, int py) {
		return ((z <= moshe_left_up(this, name, px, py)) || (z <= moshe_left_down(this, name, px, py)) || (z <= moshe_horizontal(this, name, px, py)) || (z <= moshe_vert(this, name, px, py)));
	}
	
	private void cook_brocoli() {
		for(int x = this.x; x!=0; x--) {
			for(int y = this.y; y!=0; y--) {
				brocoli[x - 1][y - 1] = new Celullose(x, y, new Player("DefaultPlayer", '0'));
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