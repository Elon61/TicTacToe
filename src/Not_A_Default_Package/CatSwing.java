package Not_A_Default_Package;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.max;

/**
 * Main menu window
 */
public class CatSwing extends JFrame {
	int x = 15; // board size
	int y = 15; // board size
	int z = 5; // win size
	private String[] plnames = {"nam1", "nam2"};
	private String[] plimg = {"1", "2", "3"};
	//private String[] plimg = {"image.png", "image2.png"};

	public CatSwing() {
		setLayout(null);

		startRiceMaker();

		setVisible(true);
	}

	private void startRiceMaker() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int scx = screenSize.width;
		int scy = screenSize.height;
		setSize(scx * 9 / 10, scy * 9/10);
		setLocation(scx / 20, scy / 20);

		iks();

		clickyThings();
	}

	private void clickyThings() {
        JButton star = clickyStar();
        JButton opt = clickyOpt();
        JButton ks = clickyKs();

        clickyResizeMagic(Arrays.asList(star, opt, ks));

        this.add(star);
        this.add(opt);
        this.add(ks);

    }

    private void clickyResizeMagic(List<JButton> clickies) {
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                List<Integer> pyList = clickiesVerticalIndent(clickies.size());
                //Font mainFont = new Font(Font.DIALOG, Font.BOLD, max(px(15) / 15 - 1, 9));
                Font mainFont = new Font(Font.DIALOG, Font.BOLD, 15);

                for(int i = 0; i < clickies.size(); i++){
                    clickies.get(i).setBounds(px(35), pyList.get(i), px(20), py(5));
                    clickies.get(i).setFont(mainFont);
                }
                //Font mainFont = new Font(Font.DIALOG, Font.BOLD, (int)(px(1) * py(1) / ((px(1) + py(1)) * .33)));
                // use a min from a x percent calc and a y percent cal for font size, so that it never overflows in any direction
            }

            private List<Integer> clickiesVerticalIndent(int clickies) {
                //TODO Implement calculation thing
                return Arrays.asList(py(30), py(40), py(50));
            }
        });
    }

    private JButton clickyKs() {
        JButton ks = new JButton("KILL ALL THE NEWBS");
        ks.setBounds(px(35), py(55), px(20), py(5));
        ks.addActionListener(e -> System.exit(0));
        return ks;
    }

    private JButton clickyOpt() {
        JButton opt = new JButton("MAKE IT ALL THAT MUCH BETTER");
        opt.setBounds(px(35), py(35), px(20), py(5));
        opt.setDefaultCapable(true);
        //opt.addActionListener(e -> music());
        return opt;
    }

    private JButton clickyStar() {
        JButton star = new JButton("START THE GREATEST GAME EVER");
        star.setBounds(px(35), py(15), px(20), py(5));
        star.addActionListener(e -> new Softcandy(this, x, y, z, Sashimi.blade(plnames.length, plnames, plimg)));
        //star.setBackground();
        return star;
    }

    private void iks() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
					}
		});
	}

	public void cleer() {
		this.getContentPane().removeAll();
        this.revalidate();
        this.repaint();
	}

    private int px(double percents) {
		return (int)(this.getBounds().getWidth() * percents) / 100;
	}

	private int py(double percents) {
		return (int)(this.getBounds().getHeight() * percents) / 100;
	}

    public static void main(String[] args) {
        System.setProperty("sun.java2d.noddraw", Boolean.TRUE.toString());
        CatSwing s = new CatSwing();

    }
}
