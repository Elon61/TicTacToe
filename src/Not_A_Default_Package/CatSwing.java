package Not_A_Default_Package;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class CatSwing extends JFrame{
	int x = 15; // board size
	int y = 15; // board size
	int z = 5; // win size
	JFrame THIS;
	private String[] plnames = {"nam1", "nam2"};
	private String[] plimg = {"1", "2", "3"};
	//private String[] plimg = {"image.png", "image2.png"};

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
		star.setBounds(px(35), py(15), px(20), py(5));
		star.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Softcandy teemo = new Softcandy(THIS, x, y, z, Sashimi.blade(plnames.length, plnames, plimg));
			}

		});
		//star.setBackground();
		this.add(star);

		JButton opt = new JButton("MAKE IT ALL THAT MUCH BETTER");
		opt.setBounds(px(35), py(35), px(20), py(5));
		opt.setDefaultCapable(true);
		this.add(opt);

		JButton ks = new JButton("KILL ALL THE NEWBS");
		ks.setBounds(px(35), py(55), px(20), py(5));
		ks.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		this.add(ks);
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                ks.setBounds(px(35), py(55), px(20), py(5));
                opt.setBounds(px(35), py(35), px(20), py(5));
                star.setBounds(px(35), py(15), px(20), py(5));
                star.setFont(new Font(Font.DIALOG, Font.BOLD, (int)(px(1) * py(1) / ((px(1) + py(1)) * .33))));
                System.out.println((int)(px(1) * py(1) / ((px(1) + py(1)) * .33)));
            }
        });
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

	private int px(double percents) {
		return (int)(this.getBounds().getWidth() * percents) / 100;
	}

	private int py(double percents) {
		return (int)(this.getBounds().getHeight() * percents) / 100;
	}

}
