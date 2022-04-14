//Dragon monster
public class Dragon extends Monster {
    private final String type = "Dragon";

    public Dragon(String name, int level, int HP, int defense, int damage, int dodge){
        super(name, level, HP, defense, damage, dodge);
    }

    public String getType() {
        return type;
    }
}

