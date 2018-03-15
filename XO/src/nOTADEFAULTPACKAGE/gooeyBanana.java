package nOTADEFAULTPACKAGE;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class gooeyBanana extends Frame {
	int x = 5;
	int y = 5;
	int z = 5;
	
	
	public gooeyBanana() {
		
		setLayout(null);
		
		startRiceMaker();
		
		setVisible(true);
		
	}
	
	private void startRiceMaker() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		x = screenSize.width;
		y = screenSize.height;
		setSize(x * 9 / 10, y * 9/10);
		setLocation(x / 20, y / 20);
		
		iks();
		
		clickyThings();
		
	}
	
	private void clickyThings() {
		Button star = new Button("START THE GREATEST GAME EVER");
		star.setBounds(300, 100, 200, 30);
		star.addActionListener(new ActionListener(){ //TODO move action listeners outside into another function
			public void actionPerformed(ActionEvent e) {
				Hardcandy teemo = new Hardcandy(getThis(), x, y, z);
				teemo.buildthedevil();
			}
		});
		//star.setBackground();
		this.add(star);
		
		Button opt = new Button("MAKE IT ALL THAT MUCH BETTER");
		opt.setBounds(300, 250, 200, 30);
		this.add(opt);
		
		Button ks = new Button("KILL ALL THE NEWBS");
		ks.setBounds(300, 400, 200, 30);
		ks.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		this.add(ks);
	}
	
	private void iks() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
					}
		});
	}
	
	private Frame getThis() {
		return this;
	}
	
	public static void main(String[] args) {
		gooeyBanana Masheou = new gooeyBanana();
		
	}
}