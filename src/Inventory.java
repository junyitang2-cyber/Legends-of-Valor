//class contains weapons, potions, armors, and spells

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {

    private ArrayList<Weapon> weapons;
    private ArrayList<Armor> armors;
    private ArrayList<Spell> spells;
    private HashMap<Potion, Integer > potions; // key is potion, value is the number of this potion


    public Inventory(){
        weapons = new ArrayList<Weapon>();
        armors = new ArrayList<Armor>();
        spells = new ArrayList<Spell>();
        potions = new HashMap<Potion, Integer>();
    }


    public void removeItem(Item item){
        int index = -1;
        if(item instanceof Armor){
            for(int i=0; i<armors.size(); i++){
                if(armors.get(i).getName().equals(item.getName()) && armors.get(i).getClass().equals(item.getClass())){
                    index = i;
                    break;
                }
            }
            armors.remove(index);
        }else if(item instanceof Weapon){
            for(int i=0; i<weapons.size(); i++){
                if(weapons.get(i).getName().equals(item.getName()) && weapons.get(i).getClass().equals(item.getClass())){
                    index = i;
                    break;
                }
            }
            weapons.remove(index);
        }else if(item instanceof Spell){
            for(int i=0; i<spells.size(); i++){
                if(spells.get(i).getName().equals(item.getName()) && spells.get(i).getClass().equals(item.getClass())){
                    index = i;
                    break;
                }
            }
            spells.remove(index);
        }else if(item instanceof Potion){
            if(potions.containsKey((Potion)item)){
                if(potions.get((Potion)item)>=1){
                potions.replace((Potion)item, potions.get((Potion)item)-1);
                }else{
                    potions.replace((Potion)item, 0);
                }
            }else{
                System.out.println("There is no such potion in your inventory!");
            }
        }
    }

    public void addItem(Item i){
        if(i instanceof Armor){
            armors.add((Armor)i);
        }else if(i instanceof Weapon){
            weapons.add((Weapon) i);
        }else if(i instanceof Spell){
            spells.add((Spell) i);
        }else if(i instanceof Potion){
            if(potions.containsKey((Potion)i)){
                potions.replace((Potion)i, potions.get((Potion)i)+1);
            }else{
                potions.put((Potion)i, 1);
            }
        }

    }


    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    public ArrayList<Armor> getArmors() {
        return armors;
    }

    public void setArmors(ArrayList<Armor> armors) {
        this.armors = armors;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public void setSpells(ArrayList<Spell> spells) {
        this.spells = spells;
    }

    public HashMap<Potion, Integer> getPotions() {
        return potions;
    }

    public void setPotions(HashMap<Potion, Integer> potions) {
        this.potions = potions;
    }


}
