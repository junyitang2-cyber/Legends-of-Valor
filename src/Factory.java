//Factory design pattern
public class Factory {



    public Inventory newInventory(){
        return new Inventory();
    }

    public Markets newMarkets(){
        return new Markets();
    }

    public Paladin newPaladin(String name, int level, int HP, int mana, int strength, int agility, int dexterity, int money, int experience, Lane initLane){
        return new Paladin(name, level, HP, mana, strength, agility, dexterity, money, experience, initLane);
    }

    public Sorcerer newSorcerer(String name, int level, int HP, int mana, int strength, int agility, int dexterity, int money, int experience, Lane initLane){
        return new Sorcerer(name, level, HP, mana, strength, agility, dexterity, money, experience, initLane);
    }

    public Warrior newWarrior(String name, int level, int HP, int mana, int strength, int agility, int dexterity, int money, int experience, Lane initLane){
        return new Warrior(name, level, HP, mana, strength, agility, dexterity, money, experience, initLane);
    }

    public Exoskeleton newExoskeleton(String name, int level, int HP, int defense, int damage, int dodge){
        return new Exoskeleton(name, level, HP, defense, damage, dodge);
    }

    public Dragon newDragon(String name, int level, int HP, int defense, int damage, int dodge){
        return new Dragon(name, level, HP, defense, damage, dodge);
    }

    public Spirit newSpirit(String name, int level, int HP, int defense, int damage, int dodge){
        return new Spirit(name, level, HP, defense, damage, dodge);
    }

    public Weapon newWeapon(String name, int price, int minLevel, int damage){
        return new Weapon(name, price, minLevel, damage);
    }

    public Armor newArmor(String name, int price, int minLevel, int reduction){
        return new Armor(name, price, minLevel, reduction);
    }

    public Potion newPotion(String name, int price, int minLevel, int increase, String affectedAttr){
        return new Potion(name, price, minLevel, increase, affectedAttr);
    }

    public FireSpell newFireSpell(String name, int price, int minLevel, int mana, int damage){
        return new FireSpell(name, price, minLevel, mana, damage);
    }

    public IceSpell newIceSpell(String name, int price, int minLevel, int mana, int damage){
        return new IceSpell(name, price, minLevel, mana, damage);
    }

    public LightningSpell newLightningSpell(String name, int price, int minLevel, int mana, int damage){
        return new LightningSpell(name, price, minLevel, mana, damage);
    }

}


