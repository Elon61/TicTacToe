package Not_A_Default_Package;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class Hardcandy {
	Frame frame;
	int x, y, z, sx, sy;
	
	public Hardcandy(Frame frame, int x, int y, int z) {
		sx = frame.getWidth(); sy = frame.getHeight();
		this.frame = frame; this.x = x; this.y = y; this.z = z;
		frame.removeAll();
	}
	
	public void buildthedevil() {
		Button ks = new Button("KILL ALL THE NEWBS");
		ks.setBounds(300, 400, 200, 30);
		ks.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		frame.add(ks);
		
		Panel p = new Panel();
		p.setBounds(sx / 4, sy / 8, sx, sy);
		p.setBackground(Color.BLACK);
		frame.add(p);
		Scrollbar sc = new Scrollbar(Scrollbar.HORIZONTAL, 30, 1, 0, sx);
		sc.addAdjustmentListener(new AdjustmentListener(){
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				System.out.println(arg0.getValue());
			}
		});
		sc.setBounds(8, 30, sx - 16, 50);
		frame.add(sc);
	}

}
