package Not_A_Default_Package;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

/**
 * Main menu window
 */
public class Softcandy {
    JFrame frame;
    JPanel gamePanel;
    int x, y, z, sx, sy, blx, bly;
    int cell_size = 50;
    JLabel hummus, labane;
    final int LINE_THICKNESS = 2;
    final String[] PLAYER = {"teemo", "jesus", "dan"};
    private Player[] players;
    Sashimi sushi;
    private int[] shadeMin = {0, 255, 255}; // RGBmin
    private int[] shadeMax = {255, 0, 255}; // RGBmin

    public Softcandy(CatSwing frame, int x, int y, int z, Player[] players) {
        sx = frame.getWidth(); sy = frame.getHeight();
        this.frame = frame; this.x = x; this.y = y; this.z = z; this.blx = x * cell_size;
        this.bly = y * cell_size; this.players = players;
        if(blx < (3 * sx / 4 - 16) || bly < sy - 4 * sy / (sy / 8) - 16) {
            cell_size = ((sy - 4 * sy / (sy / 8) - 16) / y) - 1;
            this.blx = x * cell_size; // need to consider insets for scrollbars
            this.bly = y * cell_size;
        }
        sushi = new Sashimi(x, y, z, players);
        // sx, sy JFrame size. x, y = Board size in cells. blx,  bly board size in pixels
        frame.cleer();
        buildthedevil();
        schnitzel();
        teemo();

        salad();
    }
    
    public void buildthedevil() {
        JButton ks = clickyKs();
        JButton button3 = clickyTempBoardReset();

        frame.add(button3);
        frame.add(ks);
        
    }

    private JButton clickyTempBoardReset() {
        JButton button3 = new JButton("Hsrhgbsfbeh");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gamePanel.getMouseListeners().length != 0){
                    //gamePanel.removeMouseListener(gamePanel.getMouseListeners()[0]);
                }
                sushi.reset();
                salad();
                frame.revalidate();
                frame.repaint();

            }
        });
        button3.setBounds(100, 100, 100, 100);
        button3.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
        button3.setForeground(Color.CYAN);
        return button3;
    }

    private JButton clickyKs() {
        JButton ks = new JButton("KILL ALL THE NEWBS");
        ks.setBounds(px(5), py(90), px(20), py(3));
        ks.addActionListener(e -> System.exit(0));
        return ks;
    }

    public void teemo() {
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.black);
                BufferedImage img = null;
                BufferedImage cimg;
                try {
                    img = ImageIO.read(new File("src/Not_A_Default_Package/tile.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for(int x = 0; x <= blx; x += cell_size) {
                    g.fillRect(x, 0, LINE_THICKNESS, bly + LINE_THICKNESS);
                }

                for(int y = 0; y <= bly; y += cell_size) {
                    g.fillRect(0, y, blx + LINE_THICKNESS, LINE_THICKNESS);
                }

                for(int x = LINE_THICKNESS; x < blx; x += cell_size){
                    for(int y = LINE_THICKNESS;y < bly; y += cell_size){
                        g.drawImage(img, x, y,
                                cell_size - LINE_THICKNESS, cell_size - LINE_THICKNESS,
                                null);
                    }
                }

                Cellulose[][] b = sushi.getCellBoard();
                Rectangle bounds = g.getClipBounds();
                int x = (int)bounds.getX();
                int y = (int)bounds.getY();

                int dcell_x = (x / cell_size);
                int dcell_x2 = (int)((x + bounds.getWidth() + cell_size - 1) / cell_size);
                int dcell_y = (y / cell_size);
                int dcell_y2 = (int)((y + bounds.getHeight() + cell_size - 1) / cell_size);
                int player_colour; int xStart; int yStart; int recWidth; int recHeight;
                for(int i = dcell_x; i < Math.min(dcell_x2, b.length); i++) {
                    for(int j = dcell_y; j < Math.min(dcell_y2, b[i].length); j++) {
                        if(!b[i][j].isFiber()){
                            try {
                                img = ImageIO.read(new File("src/Not_A_Default_Package/tile.png"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            } // when not reading image again it loses it's colour?
                            player_colour = (b[i][j].getP().getColour());
                            xStart = i * cell_size + LINE_THICKNESS;
                            yStart = j * cell_size + LINE_THICKNESS;
                            recWidth = cell_size - LINE_THICKNESS;
                            recHeight = cell_size - LINE_THICKNESS;
                            g.setColor(Color.white);
                            g.fillRect(xStart, yStart, recWidth, recHeight);
                            cimg = colourImage(img, argbToArr(rainbow(player_colour).getRGB()));
                            g.drawImage(cimg, xStart, yStart,
                                    recWidth, recHeight,
                                    null);
                        }
                    }
                }
            }

            private BufferedImage colourImage(BufferedImage img, int[] colour) {
                int[] xy; int[] pixor; int argb;
                for(int i = 0; i < img.getHeight(); i++){
                    for(int j = 0; j < img.getWidth(); j++){
                        xy = new int[] {i, j};
                        argb = img.getRGB(xy[0], xy[1]);
                        pixor = argbToArr(argb);
                        pixor = colourPix(pixor, colour);
                        img.setRGB(xy[0], xy[1], arrToArgb(pixor));
                    }
                }
                return img;
            }

            private int[] colourPix(int[] data, int[] colour) {
                if(data[0] == 0){
                    return data;
                }
                double colourIntensity;
                colourIntensity = (colourCurve(data[1])) / 255.;
                return new int[] {data[0], (int)(colour[1] * colourIntensity), (int)(colour[2] * colourIntensity), (int)(colour[3] * colourIntensity)};
            }

            private double colourCurve(double colourStuff) {
                return (Math.pow(colourStuff, 2) / 255);
            }

            private Color rainbow(int pln) {
                return new Color(shadeMin[0] + (pln * (shadeMax[0] - shadeMin[0]) / players.length), shadeMin[1] + (pln * (shadeMax[1] - shadeMin[1]) / players.length), shadeMin[2] + (pln * (shadeMax[2] - shadeMin[2])) / (players.length));
            }

            private int[] argbToArr(int argb) {
                return new int[] {(argb>>24) & 0xff, (argb>>16) & 0xff, (argb>>8) & 0xff, argb & 0xff};
            }

            private int arrToArgb(int[] arr) { // arr is A R G B
                return (arr[0]<<24) | (arr[1]<<16)| (arr[2]<<8) | arr[3] ;
            }
        };

        MouseInputAdapter miceThing = new MouseInputAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mousePressed(e);
            }

            public void mousePressed(MouseEvent l) {
                int x = l.getX();
                int y = l.getY();
                x = x / cell_size;
                y = y / cell_size;
                if (sushi.play(x, y)) {
                    if (sushi.win(x, y)) {
                        System.out.println("banana");
                        //Softcandy.win; // update gui for win stuff, kill the mouse listener and update labels
                        gamePanel.removeMouseListener(this);
                        gamePanel.removeMouseMotionListener(this);
                    }
                    sushi.nextPlayer();
                    salad();
                    gamePanel.repaint(x * cell_size, y * cell_size, cell_size, cell_size);
                }
                else {
                    hummus.setText("You can't play there try somewhere else");
                    Thread thread = new Thread(() -> {
                        try {
                            Thread.sleep(2000);
                            hummus.setText("");
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                    });
                    thread.start();
                }
            }
        };

        gamePanel.addMouseListener(miceThing);
        gamePanel.addMouseMotionListener(miceThing);

        //test.setBackground(Color.darkGray);
        JScrollPane scrollFrame = new JScrollPane(gamePanel);
        gamePanel.setPreferredSize(new Dimension(blx + 2,bly + 2));
        //scrollFrame.setPreferredSize(new Dimension(8000,3000));
        gamePanel.setAutoscrolls(true);
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
        labane = new JLabel("Hsrhgbsfbeh", SwingConstants.CENTER);
        labane.setBounds(sx / 20, 0, 2 * sx / 9, 100);
        labane.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
        frame.add(labane);
        hummus = new JLabel("", SwingConstants.CENTER);
        hummus.setBounds(sx / 20 + sx / 30, sy / 20, 2 * sx / 9 - 2 * sx / 30, 100);
        hummus.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
        hummus.setForeground(Color.RED);
        frame.add(hummus);
    }
    
    public void salad() {
        labane.setText("Current Player: " + sushi.getPlayer().toString());
        
    }

    private int px(double percents) {
        return (int)(frame.getBounds().getWidth() * percents) / 100;
    }

    private int lpx(double percents) { //left side percent coords, to make the side stuff easier;
        return (int)(frame.getBounds().getWidth() * percents) / 100;
    }

        private int py(double percents) {
        return(int)(frame.getBounds().getHeight() * percents) / 100;
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
