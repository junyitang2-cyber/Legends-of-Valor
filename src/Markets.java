import java.util.ArrayList;
import java.util.HashMap;

public class Markets {
    private Inventory market;
    private boolean isShopping;
    protected Colors colors;

    public Markets() {
        market = new Inventory();
        colors = new Colors();
    }

    /**
     * the main console to implement the store algorithms. Hero can choose to enter or not enter the store
     *
     * @param h
     */
    public void storeConsole(Hero h) {
        System.out.println(colors.addColor("purple", "You are at Nexus. Would you like to enter the market?(y/n)"));
        isShopping = ScannerParser.parseBoolean();
        while (isShopping) {
            System.out.println(colors.addColor("green", "Welcome to the Market!"));
            enterStore(h);
        }
        System.out.println(colors.addColor("red", "Hero left the market.\n"));
    }

    public void initMarket() {
        FileParser fp = new FileParser();
        market.setArmors(fp.parseArmor());
        market.setPotions(fp.parsePotions());
        market.setSpells(fp.parseSpells());
        market.setWeapons(fp.parseWeapons());
    }

    public void enterStore(Hero hero) {
        Factory fac = new Factory();
        Printer printer = new Printer();
        initMarket();
        System.out.println(colors.addColor("green", "Hero entered the store. View hero stats below ---"));
        printer.printHero(hero);
        System.out.println(colors.addColor("green", "Would you like to buy or sell an item?"));
        System.out.println(" 1: Buy\n 2: Sell\n 3: Exit market");
        int input = ScannerParser.parseInt();
        chooseBuySell(input, hero);
    }


    public void chooseBuySell(int input, Hero hero) {
        while (input != 1 && input != 2 && input != 3) {
            System.out.println(colors.addColor("red", "Invalid option! Please try again!"));
            input = ScannerParser.parseInt();
        }
        switch (input) {
            case 1:
                heroBuy(hero);
                enterStore(hero);
                break;
            case 2:
                heroSell(hero);
                enterStore(hero);
                break;
            case 3:
                isShopping = false;
                break;
        }
    }


    public void heroBuy(Hero h) {
        System.out.println(colors.addColor("green", "What would you like to purchase?"));
        System.out.println(colors.addColor("green", " 1: Weapon\n 2: Armor\n 3: Potion\n 4: Spell\n 5: Go back"));
        int input = ScannerParser.parseInt();
        while (input != 1 && input != 2 && input != 3 && input != 4 && input != 5) {
            System.out.println("Invalid input! Try again:");
            input = ScannerParser.parseInt();
        }
        if (input != 5) {
            Printer printer = new Printer();
            printer.printShop(market, input);
            System.out.println(colors.addColor("green", "Which item would you like to purchase? (or enter 0 to go back)"));
            int id = ScannerParser.parseInt() - 1;
            switch (input) {
                case 1:
                    if (id == -1) {
                        break;
                    } else {
                        while (h.getMoney() < market.getWeapons().get(id).getPrice()) {
                            System.out.println(colors.addColor("red", "Your hero doesn't have enough money to buy this weapon. Please try again"));
                            id = ScannerParser.parseInt() - 1;
                        }
                        h.buy(market.getWeapons().get(id));
                        System.out.println(colors.addColor("purple", "Item is purchased successfully!\n"));
                        break;
                    }
                case 2:
                    if (id == -1) {
                        break;
                    } else {
                        while (h.getMoney() < market.getArmors().get(id).getPrice()) {
                            System.out.println(colors.addColor("red", "Your hero doesn't have enough money to buy this armor. Please try again"));
                            id = ScannerParser.parseInt() - 1;
                        }
                        h.buy(market.getArmors().get(id));
                        System.out.println(colors.addColor("purple", "Item is purchased successfully!\n"));
                        break;
                    }
                case 3:
                    if (id == -1) {
                        break;
                    } else {

                        HashMap<Potion, Integer> potions = market.getPotions();
                        ArrayList<Potion> keys = new ArrayList<>(potions.keySet()) ;
                        while (h.getMoney() < keys.get(id).getPrice()) {
                            System.out.println(colors.addColor("red", "Your hero doesn't have enough money to buy this potion. Please try again"));
                            id = ScannerParser.parseInt() - 1;
                        }
                        h.buy(keys.get(id));
                        System.out.println(colors.addColor("purple", "Item is purchased successfully!\n"));
                        break;
                    }
                case 4:
                    if (id == -1) {
                        break;
                    } else {
                        while (h.getMoney() < market.getSpells().get(id).getPrice()) {
                            System.out.println(colors.addColor("red", "Your hero doesn't have enough money to buy this spell. Please try again"));
                            id = ScannerParser.parseInt() - 1;
                        }
                        h.buy(market.getSpells().get(id));
                        System.out.println(colors.addColor("purple", "Item is purchased successfully!\n"));
                        break;
                    }

            }
        }

    }


    public void heroSell(Hero h) {
        System.out.println(colors.addColor("green", "What would you like to sell?"));
        System.out.println(" 1: Weapon\n 2: Armor\n 3: Potion\n 4: Spell\n 5: Go back");
        int input = ScannerParser.parseInt();
        while (input != 1 && input != 2 && input != 3 && input != 4 && input != 5) {
            System.out.println("Invalid input! Try again:");
            input = ScannerParser.parseInt();
        }
        Printer printer = new Printer();
        if (input != 5) {
            switch (input) {
                case 1:
                    //sell a weapon
                    printer.printWeapons(h.getInventory().getWeapons());
                    System.out.println(colors.addColor("green", "Which item would you like to sell? (or enter 0 to go back)"));
                    int id = ScannerParser.parseInt() - 1;
                    if (id == -1) {
                        break;
                    } else {
                        while (id < 0 || id >= h.getInventory().getWeapons().size()) {
                            id = ScannerParser.parseInt() - 1;
                        }
                        h.sell(h.getInventory().getWeapons().get(id));
                        System.out.println(colors.addColor("purple", "Item is sold successfully!\n"));
                        break;
                    }
                case 2:
                    //sell an armor
                    printer.printArmors(h.getInventory().getArmors());
                    System.out.println(colors.addColor("green", "Which item would you like to sell? (or enter 0 to go back)"));
                    id = ScannerParser.parseInt() - 1;
                    if (id == -1) {
                        break;
                    } else {
                        while (id < 0 || id >= h.getInventory().getArmors().size()) {
                            id = ScannerParser.parseInt() - 1;
                        }
                        h.sell(h.getInventory().getArmors().get(id));
                        System.out.println(colors.addColor("purple", "Item is sold successfully!\n"));
                        break;
                    }

                case 3:
                    //sell a potion

                    printer.printPotions(h.getInventory().getPotions());
                    System.out.println(colors.addColor("green", "Which item would you like to sell? (or enter 0 to go back)"));
                    id = ScannerParser.parseInt() - 1;
                    HashMap<Potion, Integer> potions = h.getInventory().getPotions();
                    ArrayList<Potion> keys = new ArrayList<>(potions.keySet()) ;
                    if (id == -1) {
                        break;
                    } else {
                        while (id < 0 || id >= potions.size()) {
                            id = ScannerParser.parseInt() - 1;
                        }
                        h.sell(keys.get(id));
                        System.out.println(colors.addColor("purple", "Item is sold successfully!\n"));
                        break;
                    }
                case 4:
                    //sell a spell
                    printer.printSpells(h.getInventory().getSpells());
                    System.out.println(colors.addColor("green", "Which item would you like to sell? (or enter 0 to go back)"));

                    id = ScannerParser.parseInt() - 1;
                    if (id == -1) {
                        break;
                    } else {
                        while (id < 0 || id >= h.getInventory().getSpells().size()) {
                            id = ScannerParser.parseInt() - 1;
                        }
                        h.sell(h.getInventory().getArmors().get(id));
                        System.out.println(colors.addColor("purple", "Item is sold successfully!\n"));
                        break;
                    }
            }
        }
    }


}
