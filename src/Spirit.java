//spirit type monster
public class Spirit extends Monster {

    private final String type = "Spirit";

    public Spirit(String name, int level, int HP, int defense, int damage, int dodge){
        super(name, level, HP, defense, damage, dodge);

    }

    public String getType() {
        return type;
    }


}
