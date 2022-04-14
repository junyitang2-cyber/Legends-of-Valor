//read text file
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileParser {

    private Factory factory;

    public FileParser() {
        factory = new Factory();

    }

    public ArrayList<String[]> readFile(String fileName){
        String file = System.getProperty("user.dir") + "/src/ConfigFiles/" + fileName;
        ArrayList<String[]> result = new ArrayList<String[]>();
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Please enter the correct filepath");
            e.printStackTrace();
        }
        for(int i=1; i<lines.size(); i++){
        String[] s = lines.get(i).split("\\s+");
            result.add(s);
        }
        return result;

    }

    public int parseint(String s) {
        return Integer.parseInt(s);
    }

    /**
     * choose a random monster who has the input level from all monsters
     *
     * @param
     * @return
     */
    public Monster chooseRandMonster() {
        ArrayList<Monster> am = parseMonsters();
        Random r = new Random();
        int n = r.nextInt(am.size());
        return am.get(n);
    }


    public ArrayList<Armor> parseArmor() {
        ArrayList<Armor> armors = new ArrayList<Armor>();
        ArrayList<String[]> file = readFile("Armory.txt");
        for (String[] string : file) {
            Armor a = factory.newArmor(string[0], parseint(string[1]), parseint(string[2]), Integer.parseInt(string[3]));
            armors.add(a);
        }
        return armors;
    }

    public ArrayList<Spell> parseSpells() {
        ArrayList<Spell> spells = new ArrayList<Spell>();
        ArrayList<String[]> file = readFile("FireSpells.txt");
        for (String[] string : file) {
            Spell s = factory.newFireSpell(string[0], parseint(string[1]),
                    parseint(string[2]), parseint(string[4]), parseint(string[3]));
            spells.add(s);
        }
        file = readFile("IceSpells.txt");
        for (String[] string : file) {
            Spell s = factory.newIceSpell(string[0], parseint(string[1]),
                    parseint(string[2]), parseint(string[4]), parseint(string[3]));
            spells.add(s);
        }
        file = readFile("LightningSpells.txt");
        for (String[] string : file) {
            Spell s = factory.newLightningSpell(string[0], parseint(string[1]),
                    parseint(string[2]), parseint(string[4]), parseint(string[3]));
            spells.add(s);
        }
        return spells;
    }

    public HashMap<Potion, Integer> parsePotions() {
        HashMap<Potion, Integer> potions = new HashMap<Potion, Integer>();
        ArrayList<String[]> file = readFile("Potions.txt");
        for (String[] string : file) {
            Potion p = factory.newPotion(string[0], parseint(string[1]),
                    parseint(string[2]), parseint(string[3]), string[4]);
            potions.put(p, 0);
        }
        return potions;
    }

    public ArrayList<Weapon> parseWeapons() {
        ArrayList<Weapon> weapons = new ArrayList<Weapon>();
        ArrayList<String[]> file = readFile("Weaponry.txt");
        for (String[] string : file) {
            Weapon w = factory.newWeapon(string[0], parseint(string[1]),
                    parseint(string[2]), parseint(string[3]));
            weapons.add(w);
        }
        return weapons;
    }

    public ArrayList<Monster> parseMonsters() {
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        ArrayList<String[]> file = readFile("Dragons.txt");
        for (String[] string : file) {
            Monster m = factory.newDragon(string[0], parseint(string[1]),
                    parseint(string[1]) * 100, parseint(string[3]),
                    parseint(string[2]), parseint(string[4]));
            monsters.add(m);
        }
        file = readFile("Exoskeletons.txt");
        for (String[] string : file) {
            Monster m = factory.newExoskeleton(string[0], parseint(string[1]),
                    parseint(string[1]) * 100, parseint(string[3]),
                    parseint(string[2]), parseint(string[4]));
            monsters.add(m);
        }
        file = readFile("Spirits.txt");
        for (String[] string : file) {
            Monster m = factory.newSpirit(string[0], parseint(string[1]),
                    parseint(string[1]) * 100, parseint(string[3]),
                    parseint(string[2]), parseint(string[4]));
            monsters.add(m);
        }
        return monsters;
    }


    public ArrayList<Hero> parsePaladins() {
        ArrayList<Hero> paladins = new ArrayList<Hero>();

        ArrayList<String[]> file = readFile("Paladins.txt");
        for (String[] string : file) {
            Hero h = factory.newPaladin(string[0], 1,
                    100, parseint(string[1]),
                    parseint(string[2]), parseint(string[3]), parseint(string[4]),
                    parseint(string[5]), parseint(string[6]), null);
            paladins.add(h);

        }
        return paladins;
    }

    public ArrayList<Hero> parseSorcerers() {
        ArrayList<Hero> sorcerers = new ArrayList<Hero>();
        ArrayList<String[]> file = readFile("Sorcerers.txt");
        for (String[] string : file) {
            Hero h = factory.newSorcerer(string[0], 1,
                    100, parseint(string[1]),
                    parseint(string[2]), parseint(string[3]), parseint(string[4]),
                    parseint(string[5]), parseint(string[6]), null);
            sorcerers.add(h);
        }
        return sorcerers;
    }

    public ArrayList<Hero> parseWarriors() {
        ArrayList<Hero> warriors = new ArrayList<Hero>();
        ArrayList<String[]> file = readFile("Warriors.txt");
        for (String[] string : file) {
            Hero h = factory.newWarrior(string[0], 1,
                    100, parseint(string[1]),
                    parseint(string[2]), parseint(string[3]), parseint(string[4]),
                    parseint(string[5]), parseint(string[6]), null);

            warriors.add(h);
//                        for(String s: string) {
//                System.out.printf(s+" ");
//            }
        }
        return warriors;
    }


}
