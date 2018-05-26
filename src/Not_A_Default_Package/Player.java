package Not_A_Default_Package;

public class Player {
    private char pp;
    private String pn;
    private String pic;
    
    public Player(String pn, char pp) { // Can play.
        this.pp = pp;
        this.pn = pn;
    }
    
    public Player(String pn, String pic) { // Can play.
        this.pn = pn;
        this.pic = pic;
    }
    
    public char getChar() {
        return pp;
    }
    
    public String getCharS() {
        return String.valueOf(pp);
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
}
