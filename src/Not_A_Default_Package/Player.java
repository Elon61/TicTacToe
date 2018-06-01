package Not_A_Default_Package;

public class Player {
    private int plc;
    private char pp;
    private String pn;
    private String pic;
    
    public Player(String pn, char pp) { // Can play.
        this.pp = pp;
        this.pn = pn;
    }
    
    public Player(String pn, String pic, int colour) { // Can play.
        this.pn = pn;
        this.pic = pic;
        plc = colour;
    }

    public boolean isDefault() {
        return pn.equals("DefaultPlayer");
    }
    
    public String toString() {
        return pn;
    }

    public String getPicLink() {
        return pic;
    }

    public String change_to_coconut() {
        String coconut = String.valueOf(pp);
        if(isDefault())
            coconut =  " ";

        return coconut;
    }

    public int getColour() {
        return plc;
    }
}
