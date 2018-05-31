package Not_A_Default_Package;

public class Player {
    private int plc;
    private char pp;
    private String name;
    private String pic;
    
    public Player(String name, char pp) { // Can play.
        this.pp = pp;
        this.name = name;
    }
    
    public Player(String name, String pic, int colour) { // Can play.
        this.name = name;
        this.pic = pic;
        plc = colour;
    }

    public boolean isDefault() {
        return name.equals("DefaultPlayer");
    }
    
    public String toString() {
        return name;
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
