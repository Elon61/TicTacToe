package nOTADEFAULTPACKAGE;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class CatSwing extends JFrame{
	int x = 5; // board size
	int y = 5; // board size
	int z = 5; // win size
	JFrame THIS;

	public CatSwing() {
		setLayout(null);
		
		startRiceMaker();
		
		setVisible(true);
	}
	
	private void startRiceMaker() {
		THIS = this;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int scx = screenSize.width;
		int scy = screenSize.height;
		setSize(scx * 9 / 10, scy * 9/10);
		setLocation(scx / 20, scy / 20);
		
		iks();
		
		clickyThings();
	}
	
	private void clickyThings() {
		JButton star = new JButton("START THE GREATEST GAME EVER");
		star.setBounds(300, 100, 200, 30);
		star.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Softcandy teemo = new Softcandy(THIS, x, y, z);
			}
			
		});
		//star.setBackground();
		this.add(star);
		
		JButton opt = new JButton("MAKE IT ALL THAT MUCH BETTER");
		opt.setBounds(300, 250, 200, 30);
		opt.setDefaultCapable(true);
		this.add(opt);
		
		JButton ks = new JButton("KILL ALL THE NEWBS");
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
	
	public static void cleer(JFrame dis) {
		dis.getContentPane().removeAll();
		dis.revalidate();
		dis.repaint();
	}
	
	private JFrame getThis() {
		return this;
	}

	public static void main(String[] args) {
		CatSwing s = new CatSwing();

	}

}
