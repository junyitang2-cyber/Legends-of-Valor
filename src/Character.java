public abstract class Character {
    private String name;
    private int level;
    private int HP;
    private int maxHP;
    private boolean isFaint;

    public Character(String name, int level, int HP){
        this.name = name;
        this.level = level;
        this.HP = HP;
        maxHP = level*100;
        isFaint = false;
    }


    public boolean isFaint() {
        return isFaint;
    }

    public void setFaint(boolean faint) {
        isFaint = faint;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
