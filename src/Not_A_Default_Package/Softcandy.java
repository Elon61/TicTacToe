package Not_A_Default_Package;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class Softcandy {
	JFrame frame;
	int x, y, z, sx, sy, blx, bly;
	int cell_size = 50;
	JLabel hummus;
	final int LINE_THICKNESS = 2;
	final String[] PLAYER = {"teemo", "jesus", "dan"};
	Player[] players;
	Sashimi sushi;
	
	public Softcandy(JFrame frame, int x, int y, int z, Player[] players) {
		sx = frame.getWidth(); sy = frame.getHeight();
		this.frame = frame; this.players = players; this.x = x; this.y = y; this.z = z; this.blx = x * cell_size; this.bly = y * cell_size; 
		if(blx < (3 * sx / 4 - 16) || bly < sy - 4 * sy / (sy / 8) - 16) {
			cell_size = ((sy - 4 * sy / (sy / 8) - 16) / y) - 1;
			this.blx = x * cell_size; // need to consider insets for scrollbars
			this.bly = y * cell_size;
		}
		sushi = new Sashimi(x, y, z);
		// sx, sy JFrame size. x, y = Board size in cells. blx,  bly board size in pixels
		CatSwing.cleer(frame);
		buildthedevil();
		schnitzel();
		teemo();
	}
	
	public void buildthedevil() {
		JButton ks = new JButton("KILL ALL THE NEWBS");
		ks.setBounds(100, 100, 200, 30);
		ks.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		frame.add(ks);
		
	}
	
	public void teemo() {
		JPanel test = new JPanel() {
			@Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        g.setColor(Color.black);
		        for(int x = 0; x <= blx; x += cell_size) {
		        	g.fillRect(x, 0, LINE_THICKNESS, bly + LINE_THICKNESS);
		        }

		        for(int y = 0; y <= bly; y += cell_size) {
		        	g.fillRect(0, y, blx + LINE_THICKNESS, LINE_THICKNESS);
		        }


                Cellulose[][] b = sushi.getBoard();
                Rectangle bounds = g.getClipBounds();
                int x = (int)bounds.getX();
                int y = (int)bounds.getY();

                int dcell_x = (x / cell_size);
                int dcell_x2 = (int)((x + bounds.getWidth() + cell_size - 1) / cell_size);
                int dcell_y = (y / cell_size);
                int dcell_y2 = (int)((y + bounds.getHeight() + cell_size - 1) / cell_size);

                System.out.println("Rag");
                for(int i = dcell_x; i < Math.min(dcell_x2, b.length); i++) {
                    for(int j = dcell_y; j < Math.min(dcell_y2, b[i].length); j++) {
                        if(!b[i][j].isFiber()){
                            g.setColor(new Color((i * 256) / (b.length), (j * 256) / (b[i].length), (j * i * 256) / (b.length * b[i].length)));
                            g.fillRect(i * cell_size, j * cell_size, cell_size / 2, cell_size / 2);
                        }
                    }
                }

//                Cellulose[][] b = sushi.getBoard();
//                for(int i = 0; i < b.length; i++) {
//                    for(int j = 0; j < b[i].length; j++) {
//                        if(!b[i][j].isFiber()){
//                            g.setColor(new Color((i * 256) / (b.length), (j * 256) / (b[i].length), (j * i * 256) / (b.length * b[i].length)));
//                            g.fillRect(i * cell_size, j * cell_size, cell_size / 2, cell_size / 2);
//                        }
//                    }
//                }
            }
		};

		test.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent l) {
				int x = l.getX();
				int y = l.getY();
				x = x / cell_size;
				y = y / cell_size;
				sushi.play(x, y, players[0]);
				test.repaint(x * cell_size, y * cell_size, cell_size / 2, cell_size / 2);
				
				
			}
		});
		//test.setBackground(Color.white);
		JScrollPane scrollFrame = new JScrollPane(test);
		System.out.println(blx);
		System.out.println(bly);
		test.setPreferredSize(new Dimension(blx + 2,bly + 2));
		//scrollFrame.setPreferredSize(new Dimension(8000,3000));
		test.setAutoscrolls(true);
		//scrollFrame.getViewport().setViewPosition(new Point(((3 * sx / 4 - 16) - (sx / 4)),0));
		scrollFrame.setBounds(sx / 3, sy / (sy / 8),2 * sx / 3 - 16, sy - 4 * sy / (sy / 8) - 16); // this -16 is probably an inset too and i should use insets because.
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        scrollFrame.getHorizontalScrollBar().setUnitIncrement(16);
		frame.add(scrollFrame);
		frame.revalidate();
		frame.repaint();
		
	}
	
	private void schnitzel() {
		Insets insets = frame.getInsets();
		JLabel labane = new JLabel("Hsrhgbsfbeh", SwingConstants.CENTER);
		System.out.println(insets.top);
		labane.setBounds(sx / 20, 0, 2 * sx / 9, 100);
		labane.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
		frame.add(labane);
		hummus = new JLabel("Hsrhgbsfbeh", SwingConstants.CENTER);
		hummus.setBounds(sx / 20 + sx / 30, sy / 20, 2 * sx / 9 - 2 * sx / 30, 100);
		hummus.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
		hummus.setForeground(Color.CYAN);
		frame.add(hummus);
		
		
	}
	
	public void salad(String teemo) {
		hummus.setText(teemo);
		
	}
	
	private int px(double percents) {
		return (int)(sx * percents) / 100;
	}
	
	private int py(double percents) {
		return (int)(sy * percents) / 100;
	}
	
	private void iks(JPanel k) {
		k.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent l) {
				int x = l.getX();
				int y = l.getY();
				
			}
		});
	}

}
