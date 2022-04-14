//choose hero and spawn in nexus cell
import java.util.ArrayList;

public class HeroNexus extends Nexus {


    public HeroNexus() {
    }

    /**
     * initialize heroes to be spawned
     */
    public Character spawn(Lane lane) {
        int type = ScannerParser.parseInt();
        while (type != 1 && type != 2 && type != 3) {
            System.out.println("Please input a number within the given range:");
            type = ScannerParser.parseInt();
        }
        Hero hero = chooseHeroType(type);
        hero.setPosition(7, lane.getLeftCol());
        hero.setInitLane(lane);
        return hero;
    }

    public Hero chooseHeroType(int type) {
        FileParser fp = new FileParser();
        Hero h = null;
        System.out.println("Please choose hero from below:");
        System.out.println("   Name                 Mana Strength agility dexterity money experience");
        switch (type) {
            case 1:
                ArrayList<Hero> paladins = fp.parsePaladins();
                for (int index = 0;index<paladins.size();index++){
                    System.out.print(index+1 +". ");
                    paladins.get(index).getInfo();
                }
                int choose = ScannerParser.parseInt();
                while (choose > paladins.size()){
                    System.out.println("Please input a number within the given range:");
                    choose = ScannerParser.parseInt();
                }
                h = paladins.get(choose-1);
                break;

            case 2:
                ArrayList<Hero> sorcerers = fp.parseSorcerers();
                for (int index = 0;index<sorcerers.size();index++){
                    System.out.print(index+1 +". ");
                    sorcerers.get(index).getInfo();
                }
                choose = ScannerParser.parseInt();
                while (choose > sorcerers.size()){
                    System.out.println("Please input a number within the given range:");
                    choose = ScannerParser.parseInt();
                }
                h = sorcerers.get(choose-1);
                break;

            case 3:
                ArrayList<Hero> warriors = fp.parseWarriors();
                for (int index = 0;index<warriors.size();index++){
                    System.out.print(index+1 +". ");
                    warriors.get(index).getInfo();
                }
                choose = ScannerParser.parseInt();
                while (choose > warriors.size()){
                    System.out.println("Please input a number within the given range:");
                    choose = ScannerParser.parseInt();
                }
                h = warriors.get(choose-1);
                break;

        }
        return h;
    }


    public void shop(Hero h) {
        Factory fac = new Factory();
        Markets m = fac.newMarkets();
        m.storeConsole(h);
    }
}
