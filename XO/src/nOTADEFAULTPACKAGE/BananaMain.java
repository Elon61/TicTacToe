package nOTADEFAULTPACKAGE;

import java.util.Scanner;

public class BananaMain {
	private static String[] ps = {"Elon61", "Jesus0001", "Danko17"};
	private static int pn = 2;//ps.length;//ps.length;
	private static char[] pc = {'Ω', '†', 'δ'};
	
	private static Player[] blade(int nuuu) { // create players /
		Player[] pa = new Player[nuuu];
		for(int i = 0; i < nuuu; i++) {
			pa[i] = new Player(ps[i], pc[i]);
		}
		return pa;
	}
		
	private static int[] eat_toppings(Player veggie, snobord flower, Scanner sc) {
		int x = sc.nextInt(), y = sc.nextInt();
		if(flower.canPlayBrocoli(x, y)) {
			flower.playBrocoli(x, y, veggie);
		}
		else {
			System.out.println("You're wrong try again thx");
			int[] xy = eat_toppings(veggie, flower, sc);
			x = xy[0];
			y = xy[1];
		}
		int[] xy = {x, y};
		return xy;
	}
	
	private static int[] add_toppings(snobord flower, Player veggie, Scanner sc) {
		System.out.println(veggie + " pls tell where u play k thx btw it's \"x, y\"");
		int[] xy = eat_toppings(veggie, flower, sc);
		return xy;
	}

	private static void cook_cake(snobord flower, int nump) {
		Player[] yu = blade(nump);
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i < flower.brocoliSize();i++) {
			System.out.println(flower);
			int[] xy = add_toppings(flower, yu[i % nump], sc);
			if(flower.brocoliest(yu[i % nump], xy[0], xy[1])) {
				System.out.println(flower);
				System.out.println(yu[i % nump] + " IS THE WINNER Ωδ∞φε∩");
				return;
			}
		}
		System.out.println("YOU'RE ALL LOSERS HAHAHAHA");
		
	}

	public static void main(String[] args) {
		
		int[] xy = {10, 10};
		int z = 6;
		snobord flower = new snobord(xy[0], xy[1], z);
		//shiv(flower, pn);
		//System.out.println(flower);
		cook_cake(flower, pn);

	}

}
