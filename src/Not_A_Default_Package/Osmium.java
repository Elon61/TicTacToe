package Not_A_Default_Package;

import javax.swing.*;

public class Osmium { // config window
    JFrame frame;

    public Osmium(CatSwing frame){
        this.frame = frame;
        frame.cleer();
        backToMM(frame);
    }

    private void backToMM(CatSwing frame) {
        frame.dispose();
        new CatSwing();
    }
}
