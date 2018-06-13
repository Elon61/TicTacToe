package Not_A_Default_Package;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import static java.lang.Math.min;

/**
 * Main menu window
 */
class Softcandy {
    private JFrame frame;
    private JPanel gamePanel;
    private int x, y, z, sx, sy, blx, bly;
    private int cell_size = 50;
    private final int LINE_THICKNESS = 2;
    final String[] PLAYER = {"teemo", "jesus", "dan"};
    private Player[] players;
    private JLabel labane, hummus;
    private MouseInputAdapter miceThing;
    private Sashimi sushi;
    private Thread thread;
    private Color min = new Color(0xFF00FF); // RGBmin
    private Color max = new Color(0x00FF0E); // RGBmax

    Softcandy(CatSwing frame, int x, int y, int z, Player[] players) {
        sx = frame.getWidth(); sy = frame.getHeight();
        this.frame = frame; this.x = x; this.y = y; this.z = z; this.blx = x * cell_size;
        this.bly = y * cell_size; this.players = players;
        if(blx < (3 * sx / 4 - 16) || bly < sy - 4 * sy / (sy / 8) - 16) {
            cell_size = ((sy - 4 * sy / (sy / 8) - 16) / y) - 1;
            this.blx = x * cell_size; // need to consider insets for scrollbars
            this.bly = y * cell_size;
        }
        //TODO sushi stuff; move above if into function and clean up stuff like the players list.
        sushi = new Sashimi(x, y, z, players);
        // sx, sy JFrame size. x, y = Board size in cells. blx,  bly board size in pixels
        fillBoard();
        frame.cleer();
        buildthedevil();
    }

    private void buildthedevil() {
        JButton ks = clickyKs();
        JButton button3 = clickyTempBoardReset();

        fries();
        schnitzel();
        salad();

        frame.add(button3);
        frame.add(ks);
        frame.add(labane);
        frame.add(hummus);

        teemo();

    }

    private void teemo() {

        gamePanel = new JPanel() {
            private int[] shadeMin = argbToArr(min.getRGB()); // RGBmin
            private int[] shadeMax = argbToArr(max.getRGB()); // RGBmax
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
                Color[] player_colours; Color player_colour; int xStart; int yStart; int recWidth; int recHeight;

                player_colours = FullColour(players.length);
                for (Player one: players) {
                    player_colour = player_colours[one.num()];
                    cimg = copyCat(img);
                    cimg = colourImage(cimg, argbToArr(player_colour.getRGB()));
                    for(int i = dcell_x; i < min(dcell_x2, b.length); i++) {
                        for(int j = dcell_y; j < min(dcell_y2, b[i].length); j++) {
                            if(!b[i][j].isFiber()){
                                //player_colour = (b[i][j].getP().getColour());
                                xStart = i * cell_size + LINE_THICKNESS;
                                yStart = j * cell_size + LINE_THICKNESS;
                                recWidth = cell_size - LINE_THICKNESS;
                                recHeight = cell_size - LINE_THICKNESS;
                                //g.fillRect(xStart, yStart, recWidth, recHeight); // might need to use this to repaint bg
                                //cimg = colourImage(cimg, argbToArr(rainbow(player_colour).getRGB()));
                                if (one.equals(b[i][j].getP())) {
                                    g.drawImage(cimg, xStart, yStart,
                                            recWidth, recHeight,
                                            null);
                                }
                            }
                        }
                    }
                }
            }

            private BufferedImage colourImage(BufferedImage img, int[] colour) {
                int[] xy; int[] pixor; int argb; BufferedImage kk = img;
                if(img == null) return null;
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
                colourIntensity = (colourCurve((data[1] + data[2] + data[3]) / 3) / 255.);
                return new int[] {data[0], (int)(colour[1] * colourIntensity), (int)(colour[2] * colourIntensity), (int)(colour[3] * colourIntensity)};
            }

            private double colourCurve(double colourStuff) {
                return (Math.pow(colourStuff, 2) / 255);
            }

            private Color rainbow(int pln) {
                //TODO option to scroll through actual colors of the rainbow, instead of just between two colours; or two colours and all the other colours between them?
                return new Color(shadeMin[1] + (pln * (shadeMax[1] - shadeMin[1]) / (players.length - 1)), shadeMin[2] + (pln * (shadeMax[2] - shadeMin[2]) / (players.length - 1)), shadeMin[3] + (pln * (shadeMax[3] - shadeMin[3])) / (players.length - 1));
            }

            private int[] argbToArr(int argb) {
                return new int[] {(argb>>24) & 0xff, (argb>>16) & 0xff, (argb>>8) & 0xff, argb & 0xff};
            }

            private int arrToArgb(int[] arr) { // arr is A R G B
                return (arr[0]<<24) | (arr[1]<<16)| (arr[2]<<8) | arr[3] ;
            }

            private Color[] FullColour(int pn) {
                long a, b;
                a = System.nanoTime();
                Color[] clr = new Color[pn];
                int maxD = 255 * 6;
                double d = min((double)maxD / pn, 166);
                int[] rgb;
                for (int i = 0; i < pn; i++) {
                    rgb = ColourD((int) (d * i));
                    clr[i] = new Color(rgb[0], rgb[1], rgb[2]);
                }
                b = System.nanoTime();
                System.out.println(b - a);
                return clr;
            }

            private int[] ColourD(int dist) {
                int t = 0, r = 255, g = 0, b = 0;
                boolean rs = true, gs = false, bs = false;
                int[] ct;
                ct = ColourSD(dist, t, b, bs); b = ct[0]; t = ct[1]; bs = !bs;

                ct = ColourSD(dist, t, r, rs);r = ct[0]; t = ct[1]; rs = !rs;

                ct = ColourSD(dist, t, g, gs); g = ct[0]; t = ct[1]; gs = !gs;

                ct = ColourSD(dist, t, b, bs); b = ct[0]; t = ct[1]; bs = !bs;

                ct = ColourSD(dist, t, r, rs); r = ct[0]; t = ct[1]; rs = !rs;

                ct = ColourSD(dist, t, g, gs); g = ct[0]; t = ct[1]; gs = !gs;
                return new int[]{r, g, b};
            }

            private int[] ColourSD(int dist, int t, int c, boolean s) { // dist is the how far away i want to go from red(255, 0, 0). t is how far i am already, c is the shade(r, g or b) currently being modified, bool s is whether going up or down.
                int sp = min(dist - t, 255);
                t += sp;
                if (!s)
                    c += sp;
                else
                    c -= sp;
                return new int[]{c, t};
            }

            public BufferedImage copyCat(BufferedImage source){
                BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
                Graphics g = b.getGraphics();
                g.drawImage(source, 0, 0, null);
                g.dispose();
                return b;
            }
        };

        miceThing = new MouseInputAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mousePressed(e);
            }

            public void mousePressed(MouseEvent l) {
                String errortxt = "You can't play there try somewhere else";
                int x = l.getX();
                int y = l.getY();
                x = x / cell_size;
                y = y / cell_size;
                if (sushi.play(x, y)) {
                    if (sushi.win(x, y)) {
                        win(this);
                    }
                    else{
                        sushi.nextPlayer();
                        salad();
                    }
                    gamePanel.repaint(x * cell_size, y * cell_size, cell_size, cell_size);
                }
                else {
                    hummus.setText(errortxt);
                    if(thread != null) thread.interrupt();
                    thread = new Thread(() -> {
                        try {
                            Thread.sleep(2000);
                            if(hummus.getText().equals(errortxt)){
                                hummus.setText("");
                            }
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

        JScrollPane scrollFrame = scrollyThing();
        frame.add(scrollFrame);
        //TODO custom scroll list.

        frame.revalidate();
        frame.repaint();

    }

    private void fries() { // player button
        labane = new JLabel("Hsrhgbsfbeh", SwingConstants.CENTER);
        labane.setBounds(sx / 20, 0, 2 * sx / 9, 100);
        labane.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
    }

    private void schnitzel() { // error button
        hummus = new JLabel("", SwingConstants.CENTER);
        hummus.setBounds(sx / 20 + sx / 30, sy / 20, 2 * sx / 9 - 2 * sx / 30, 100);
        hummus.setFont(new Font(Font.SERIF, Font.ITALIC, 20));
        hummus.setForeground(Color.RED);
    }

    private JButton clickyTempBoardReset() {
        JButton button3 = new JButton("Hsrhgbsfbeh");
        button3.addActionListener(e -> {
            if(gamePanel.getMouseListeners().length != 0){
                //gamePanel.removeMouseListener(gamePanel.getMouseListeners()[0]);
            }
            else{
                gamePanel.addMouseListener(miceThing);
                gamePanel.addMouseMotionListener(miceThing);
            }
            sushi.reset();
            salad();
            hummus.setForeground(Color.red);
            hummus.setText("");
            frame.revalidate();
            frame.repaint();

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

    private JScrollPane scrollyThing() {
        JScrollPane scrollFrame = new JScrollPane(gamePanel);
        //gamePanel.setPreferredSize(new Dimension(blx + LINE_THICKNESS,bly + LINE_THICKNESS));
        gamePanel.setPreferredSize(new Dimension(x * cell_size,y * cell_size));
        //scrollFrame.setPreferredSize(new Dimension(8000,3000));
        gamePanel.setAutoscrolls(true);
        //scrollFrame.getViewport().setViewPosition(new Point(((3 * sx / 4 - 16) - (sx / 4)),0));
        scrollFrame.setBounds(px(10 / .3), sy / (sy / 8),2 * sx / 3 - 16, sy - 4 * sy / (sy / 8) - 16); // this -16 is probably an inset too and i should use insets because.

        //scrollFrame.setBounds(px(10 / .3), sy / (sy / 8), blx + LINE_THICKNESS * 3 - 1, bly + LINE_THICKNESS * 3 - 1); // this one is no good. one above is better.

        // TODO finish converting to new percent system and consider insets? also scale depending on how much of the board is actually used uup, to prevent empty spaces around the board.
        scrollFrame.getVerticalScrollBar().setUnitIncrement(16);
        scrollFrame.getHorizontalScrollBar().setUnitIncrement(16);
        //System.out.println(CatSwing.pif(sy, sy / (sy / 8)));
        return scrollFrame;
    }

    private void win(MouseInputAdapter mader) { // update gui for win stuff, kill the mouse listener and update labels
        thread.interrupt();
        System.out.println("banana");
        hummus.setForeground(Color.green);
        hummus.setText("Player " + sushi.getPlayer() + " wins!");
        gamePanel.removeMouseListener(mader);
        gamePanel.removeMouseMotionListener(mader);
    }

    private void salad() {
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

    private void fillBoard(){
        snobord a = sushi.getBoard();
        for(int i = 0; i < a.get_brocolix_size(); i++){
            for(int j = 0; j < a.get_brocoly_size(); j++){
                a.playBrocoli(i, j, players[i * j % players.length]);
            }
        }
    }

}
