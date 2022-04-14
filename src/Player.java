//basic info of player
public class Player {
    public String name;
    public String pIcon;

    public Player(String name, String icon){
        this.name = name;
        this.pIcon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
