package Not_A_Default_Package;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Main menu window
 */
public class CatSwing extends JFrame {
	int x = 15; // board size
	int y = 15; // board size
	private int z = 5; // win size
	private String[] plnames = {"Moshe", "Moshe's friend teemo"};
	private String[] plimg = {"1", "2"};
	private int[] plc = {1, 2};
    private java.awt.geom.GeneralPath gp;
    //private String[] plimg = {"image.png", "image2.png"};

	public CatSwing() {
		setLayout(null);
		startRiceMaker();
		sizeMagic();
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
                int buttonSize = 5;
                List<Integer> pyList = clickiesVerticalIndent(clickies.size(), buttonSize);
                //Font mainFont = new Font(Font.DIALOG, Font.BOLD, max(px(15) / 15 - 1, 9));
                Font mainFont = new Font(Font.DIALOG, Font.BOLD, 15);

                for(int i = 0; i < clickies.size(); i++){
                    clickies.get(i).setBounds(px(35), pyList.get(i), px(30), py(buttonSize));
                    clickies.get(i).setFont(mainFont);
                }
            }

            private List<Integer> clickiesVerticalIndent(int clickies, int buttonSize) {
                //TODO Implement calculation thing
                List<Integer> list = new ArrayList<>();
                double buttonPlace;
                int buttonArea = 80;
                double buttonSpacing = clam(1.0, buttonSize * 1.8, (double)(buttonArea / clickies));
                double buttonSpan = clickies * buttonSpacing;
                if(clickies % 2 == 0){
                    buttonPlace = (int)(50 - buttonSpan / 2);
                }
                else{
                    buttonPlace = (50 - (clickies / 2) * buttonSpacing - buttonSize + 1);
                }
                for(int i = 0; i < clickies; i++){
                    list.add(py(buttonPlace));
                    buttonPlace += buttonSpacing;
                }
                return list;// middle of the screen i guess, then i need to extend to the sides, so; minimum space between buttons, maximum space between buttons, and rest is screen size / number of buttons?
            }
        });
    }

    private JButton clickyStar() {
        JButton star = new JButton("START THE GREATEST GAME EVER");
        star.setBounds(px(35), py(15), px(20), py(5));
        //star.addActionListener(e -> new Softcandy(this, x, y, z, Sashimi.blade(plnames.length, plnames, plimg, plc)));
        star.addActionListener(e -> new Softcandy(this, x, y, z, Sashimi.blade(2)));
        //star.setBackground();
        return star;
    }

    private JButton clickyKs() {
        JButton ks = new JButton("KILL ALL THE NEWBS");
        ks.setBounds(px(35), py(55), px(20), py(5));
        ks.setToolTipText("Text you choose");
        ks.addActionListener(e -> System.exit(0));
        return ks;
    }

    private JButton clickyOpt() {
        JButton opt = new JButton("MAKE IT ALL THAT MUCH BETTER");
        opt.setBounds(px(35), py(35), px(20), py(5));
        opt.setDefaultCapable(true);
        //opt.addActionListener(e -> moozic());
        opt.addActionListener(e -> new Osmium(this));
        return opt;
    }

    private void iks() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
					}
		});
	}

    @Override
    public void paint(Graphics g){
        super.paint(g);
        int w = getWidth();
        int h = getHeight();

        gp = new java.awt.geom.GeneralPath();
        gp.moveTo(w - 17, h);
        gp.lineTo(w, h - 17);
        gp.lineTo(w, h);
        gp.closePath();
    }

    private void sizeMagic() {
        MouseInputListener resizeHook = new MouseInputAdapter() {
            private Point startPos = null;

            public void mousePressed(MouseEvent e) {
                if (gp.contains(e.getPoint()))
                    startPos = new Point(getWidth()-e.getX(), getHeight()-e.getY());
            }

            public void mouseReleased(MouseEvent mouseEvent) {
                startPos = null;
            }

            public void mouseMoved(MouseEvent e) {
                if (gp.contains(e.getPoint()))
                    setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                else
                    setCursor(Cursor.getDefaultCursor());
            }

            public void mouseDragged(MouseEvent e) {
                if (startPos != null) {

                    int dx = e.getX() + startPos.x;
                    int dy = e.getY() + startPos.y;

                    setSize(dx, dy);
                    repaint();
                }
            }
        };

        this.addMouseMotionListener(resizeHook);
        this.addMouseListener(resizeHook);
        this.setResizable(false);
    }

	private static void moozic(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\hp250G3-00\\IdeaProjects\\TicTacToe\\src\\Not_A_Default_Package\\noc.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(99);
            //clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
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

    private int pof(double percents, double num) {
        return (int)(num * percents) / 100;
    }

    public static double clam(double min, double max, double num){
	    return Math.max(min, Math.min(max, num));
	}

    public static void main(String[] args) {
        CatSwing s = new CatSwing();

    }
}
