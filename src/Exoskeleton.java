//Exoskeleton monster
public class Exoskeleton extends Monster {

    private final String type = "Exoskeleton";

    public Exoskeleton(String name, int level, int HP, int defense, int damage, int dodge){
        super(name, level, HP, defense, damage, dodge);
    }

    public String getType() {
        return type;
    }




}
