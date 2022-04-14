//class for hero move logic
import java.util.*;

public abstract class Hero extends Character {

    private int mana;
    private int strength;
    private int agility;
    private int dexterity;
    private int money;
    private int experience;
    private Inventory armedInventory;
    private Inventory inventory;
    private Weapon currentWeapon;
    private Armor currentArmor;
    protected Colors colors;
    private int row;
    private int col;
    private Lane currLane;
    private Lane initLane;


    private static final Map<String, Integer[]> MOVEMENT_DIRECTIONS = new HashMap<String, Integer[]>() {{
        put("w", new Integer[]{-1, 0});
        put("a", new Integer[]{0, -1});
        put("s", new Integer[]{1, 0});
        put("d", new Integer[]{0, 1});
    }};

    public Hero(String name, int level, int HP, int mana, int strength, int agility, int dexterity, int money, int experience, Lane initLane) {
        super(name, level, HP);
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.money = money;
        this.experience = experience;
        this.initLane = initLane;
        armedInventory = new Inventory();
        inventory = new Inventory();
        currLane = initLane;
        if(initLane!=null){initLane.getIn(this);}
        currentWeapon = null;
        currentArmor = null;
    }

    public abstract void levelUp();


    // hero chooses to attack, cast a spell, move, teleport, back, or quit game
    //return the play boolean which indicates whether player wants to quit the game
    public boolean takeAction(LovMap grid, LegendsOfValor lovgame) {

        boolean play = true;
        while (true) {
            boolean thisActionFinished = true;

            if (row == 7) {
                Markets market = new Markets();
                market.storeConsole(this);
                System.out.println(colors.addColor("purple", "Please choose another action for your hero."));
            }
            System.out.println(" 1: Attack\n 2: Cast spell\n 3: Change Weapon/Armor\n 4: Use a potion\n " +
                    "5: Move\n 6: Teleport\n 7: Back\n 8: Quit game\n");
            int move = ScannerParser.parseInt();
            while (move < 1 || move > 8) {
                System.out.println(colors.addColor("red", "Please input a number within the given range:"));
                move = ScannerParser.parseInt();
            }
            Printer printer = new Printer();
            switch (move) {
                case 1: //attack
                    if (withinRange(grid)) {
                        System.out.println(colors.addColor("red", "You are in a fight! Fight for yourself!!"));
                        attack(getNeighborMonster(grid, lovgame), null, grid);
                    } else {
                        System.out.println(colors.addColor("red", "No monster is within your attacking range. Please try another move!\n"));
                        thisActionFinished = false;
                    }
                    break;

                case 2: //cast spell
                    if (withinRange(grid)) {
                        ArrayList<Spell> spells = inventory.getSpells();
                        if (spells.size() != 0) {
                            System.out.println("Please choose a spell to cast (enter ID):");
                            printer.printSpells(spells);
                            int chosenSpell = ScannerParser.parseInt() - 1;
                            while (chosenSpell > inventory.getSpells().size()) {
                                System.out.println(colors.addColor("red", "Please input a number within the given range:"));
                                chosenSpell = ScannerParser.parseInt() - 1;
                            }
                            attack(getNeighborMonster(grid, lovgame), inventory.getSpells().get(chosenSpell), grid);
                        } else {
                            System.out.println(colors.addColor("red", "Your hero does not have any spell in their inventory! Choose another move!\n"));
                            thisActionFinished = false;
                        }
                    } else {
                        System.out.println(colors.addColor("red", "No monster is within your attacking range. Please try another move!\n"));
                        thisActionFinished = false;
                    }
                    break;

                case 3: //change weapon/armor

//                    if (currentArmor == null) {
//                        System.out.println("Sorry, you don't have a armor in your hand.");
//                        thisActionFinished = false;
//                        break;
//                    }

                    if (inventory.getWeapons().size() == 0 && inventory.getArmors().size() == 0) {
                        System.out.println("Hero doesn't have any weapon or armor in hand.");
                        thisActionFinished = false;
                    }
                    System.out.println(colors.addColor("purple", "What would you like to change?"));
                    System.out.println(" 1: Armor\n 2: Weapon");
                    int type = ScannerParser.parseInt();
                    while (type != 1 && type != 2) {
                        System.out.println(colors.addColor("red", "Please input a number within the given range:"));
                        type = ScannerParser.parseInt();
                    }
                    if (inventory.getWeapons().size() == 0 && type == 2) {
                        System.out.println("Hero doesn't have any weapon in hand.");
                        thisActionFinished = false;
                        break;
                    }
                    if (inventory.getArmors().size() == 0 && type == 1) {
                        System.out.println("Hero doesn't have any armor in hand.");
                        thisActionFinished = false;
                        break;
                    }

                    switch (type) {
                        case 1:
                            if (currentArmor == null) {
//                                thisActionFinished = false;
                                System.out.println("Which armor would you like to wear now?");
                                printer.printArmors(inventory.getArmors());
                                int newarmor = ScannerParser.parseInt() - 1;
                                while (newarmor > inventory.getArmors().size()) {
                                    System.out.println(colors.addColor("red", "Please input a number within the given range:"));
                                    newarmor = ScannerParser.parseInt() - 1;
                                }
//                        h.equip(h.getInventory().getArmors().get(newarmor));
                                equip(inventory.getArmors().get(newarmor));
                                System.out.println("Armor " + currentArmor.getName() + " is equipped now");
                                break;
                            } else {
//                                if (inventory.getArmors().size() < 2) {
//                                    System.out.println("Hero doesn't have an armor to switch to. Try another move!");
//                                    thisActionFinished = false;
//                                    break;
//                                } else {
                                System.out.println("Your current armor is:" + currentArmor.getName());
                                unequip(currentArmor);
                                System.out.println("Current armor is taken off.");
                                System.out.println("Which armor would you like to wear now?");
                                printer.printArmors(inventory.getArmors());
                                int newarmor = ScannerParser.parseInt() - 1;
                                while (newarmor > inventory.getArmors().size()) {
                                    System.out.println(colors.addColor("red", "Please input a number within the given range:"));
                                    newarmor = ScannerParser.parseInt() - 1;
                                }
//                        h.equip(h.getInventory().getArmors().get(newarmor));
                                changeArmor(currentArmor, inventory.getArmors().get(newarmor));
                                System.out.println("Armor " + currentArmor.getName() + " is equipped now");
                                break;
//                                }
                            }

                        case 2:
                            if (currentWeapon == null) {
                                System.out.println("Which weapon would you like to wear now?");
                                printer.printWeapons(inventory.getWeapons());
                                int newweapon = ScannerParser.parseInt() - 1;
                                while (newweapon > inventory.getWeapons().size()) {
                                    System.out.println(colors.addColor("red", "Please input a number within the given range:"));
                                    newweapon = ScannerParser.parseInt() - 1;
                                }
//                        h.equip(h.getInventory().getArmors().get(newarmor));
                                equip(inventory.getWeapons().get(newweapon));
                                System.out.println("Weapon " + currentWeapon.getName() + " is equipped now");
                                break;
                            } else {
                                System.out.println("Your current weapon is:" + currentWeapon.getName());
                                unequip(currentWeapon);
                                System.out.println("Current weapon is unarmed now.");
                                System.out.println("Which weapon would you like to arm now?");
                                printer.printWeapons(inventory.getWeapons());
                                int newWeapon = ScannerParser.parseInt() - 1;
                                while (newWeapon > inventory.getWeapons().size()) {
                                    System.out.println("Please input a number within the given range:");
                                    newWeapon = ScannerParser.parseInt() - 1;
                                }
//                      h.equip(h.getInventory().getWeapons().get(newWeapon));
                                changeWeapon(currentWeapon, inventory.getWeapons().get(newWeapon));
                                System.out.println("Weapon " + currentWeapon.getName() + " is equipped now");
                                break;

                            }
                    }
                    break;

                case 4: // use potion
                    HashMap<Potion, Integer> potions = inventory.getPotions();
                    if (potions.size() != 0) {
                        System.out.println("Please choose a potion to use (enter ID):");
                        ArrayList<Potion> keys = new ArrayList<>(potions.keySet());
                        printer.printPotions(potions);
                        int chosenPotion = ScannerParser.parseInt() - 1;
                        while (chosenPotion > inventory.getPotions().size()) {
                            System.out.println(colors.addColor("red", "Please input a number within the given range:"));
                            chosenPotion = ScannerParser.parseInt() - 1;
                        }
                        use(keys.get(chosenPotion));
                    } else {
                        System.out.println(colors.addColor("red", "You hero does not have any potion in their inventory! Choose another move!\n"));
                        thisActionFinished = false;
                    }
                    break;

                case 5: //make move
                    //makeMove(grid);
                    makeMove(grid);
                    break;

                case 6: //teleport

                    grid.checkAndUpdate();

                    System.out.println(colors.addColor("purple", "Rules of teleporting:\n 1. You shall not land on a row that surpass any monster\n" +
                            " 2. You shall not land on the same cell as another hero\n" +
                            " 3. You must teleport to a different lane than your current lane\n" +
                            " 4. You shall not go further than the max explored row in this lane"));


                    /**
                     * step 1. get the destination lane
                     */
                    String input = "";
                    while (true) {
                        boolean inputValid;
                        System.out.println(colors.addColor("purple", "Please enter the name of lane you wish to teleport to (Top/ Mid/ Bot):"));
                        input = ScannerParser.parseString();

                        if (!input.equalsIgnoreCase("Top") && !input.equalsIgnoreCase("Mid") && !input.equalsIgnoreCase("Bot")) {
                            System.out.println(colors.addColor("red", "Please check your spell"));
                            inputValid = false;
                        } else if (currLane.getName().equals(input)) {
                            System.out.println(colors.addColor("red", "You must teleport to a different lane! Try again:"));
                            inputValid = false;
                        } else {
                            inputValid = true;
                        }
                        if (inputValid) break;
                    }
                    Lane destinationLane = lovgame.getLane(input);

                    /**
                     * Step 2. Get the destination row
                     */

                    int destinationRow;
                    while (true) {
                        boolean inputValid;

                        System.out.println(colors.addColor("purple","Which row would you like to land on?(Between 0~7)"));
                        destinationRow = ScannerParser.parseInt() ;

                        if (destinationRow > 7 || destinationRow < 0) {
                            System.out.println("Hero's destination row is "+destinationRow);
                            System.out.println(colors.addColor("red", "Please input a number within the given range:"));
                            inputValid = false;
                        } else if (destinationLane.getMaxMonsterRow() > destinationRow) {
                            System.out.println("Hero's destination row is "+destinationRow);
                            System.out.println(colors.addColor("red","You shall not bypass any monster!"));
                            inputValid=false;
                        }else if(destinationLane.getMaxExplored() > destinationRow){
                            System.out.println("Hero's destination row is "+destinationRow) ;
                            System.out.println(colors.addColor("red", "You shall not exceed the max explored row of this lane!"));
                            inputValid = false;
                        } else {
                            inputValid = true;
                        }
                        if (inputValid) break;

                    }

                    System.out.println("Hero's destination row is " + destinationRow);


                    /**
                     * Step 3. check if both left and right are occupied.
                     * if yes, REJECT!!!
                     *
                     */
                    if (grid.getCells()[destinationRow][destinationLane.getLeftCol()].isHasHero()
                            && grid.getCells()[destinationRow][destinationLane.getLeftCol() + 1].isHasHero()) {
                        System.out.println(colors.addColor("red", "Sorry, both left and right columns of this lane are occupied. Try again:"));
                        thisActionFinished = false;
                        break;

                    }


                    /**
                     * Step 4.
                     * get a valid input 1 for left or 2 for right.
                     * get the destination column
                     */


                    int leftOfRight = 0;
                    Scanner scanner = new Scanner(System.in);
                    while (true) {
                        boolean inputValid;
                        System.out.println(colors.addColor("purple", "Would you like to land on left or right column of this lane?\n 1. Left\n 2. Right"));
                        if (scanner.hasNextInt()) {
                            leftOfRight = scanner.nextInt();
                            if (leftOfRight == 1 || leftOfRight == 2) {
                                inputValid = true;
                            } else {
                                inputValid = false;
                            }
                        } else {
                            scanner.next();
                            inputValid = false;
                        }
                        if (inputValid) break;
                    }
                    int destinationColumn = destinationLane.getLeftCol() + (leftOfRight - 1);
//                    System.out.println(colors.addColor("red", "Your destination column is " + destinationColumn));

                    /**
                     *
                     * Step 5
                     * judge if it has hero
                     * if yes, REJECT!!!
                     */
                    if (grid.getCells()[destinationRow][destinationColumn].isHasHero()) {
                        System.out.println(colors.addColor("red", "You shall not land on the same cell with another hero! Try again:"));
                        thisActionFinished = false;
                        break;
                    }

                    /**
                     *  Step 5.5
                     */
                    if(destinationRow<destinationLane.getMaxExplored()){
                        System.out.println(colors.addColor("red","Sorry, you cannot surpass the highest explored cell. Try again:"));
                        thisActionFinished=false;
                        break;
                    }


                    /**
                     *  Step 5.6
                     */
                    if(destinationRow<destinationLane.getMaxMonsterRow()){
                        System.out.println(colors.addColor("red","Sorry, you cannot surpass the alive monster. Try again:"));
                        thisActionFinished=false;
                        break;
                    }


                    /**
                     *
                     * Step 6
                     * MOVE TO THE DESTINATION LANE!!!
                     *
                     */
                    boolean moveSuccessful=grid.makeHeroMove(destinationRow,destinationColumn,this);
                    if(moveSuccessful){
                        setCurrLane(destinationLane);
                        thisActionFinished=true;
                    }else{
                        thisActionFinished=false;
                    }

                    grid.checkAndUpdate();
                    break;

                case 7: //back
                    setCurrLane(initLane);
                    grid.getCells()[row][col].setHasHero(false);
                    setPosition(7, currLane.getLeftCol());
                    grid.getCells()[row][col].setHasHero(true);
                    break;

                case 8: //quit
                    System.out.println(colors.addColor("red", "Thanks for playing! Exiting program..."));
                    play = false;
                    break;
            }
            if (thisActionFinished) break;
        }
//        System.out.println(row+" "+col);
        return play;

    }

    /**
     *
     */
    public void makeMove(LovMap lovMap) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
//            lovMap.display();
            System.out.println(colors.addColor("purple", "Please choose a move:"));
            System.out.println("W/w: move up\nA/a: move left\nS/s: move down\nD/d: move right\n");

            String inputString = "";
            while (true) {
                boolean valid = false;
                if (scanner.hasNext()) {
                    inputString = scanner.next().toLowerCase();
                    if (MOVEMENT_DIRECTIONS.containsKey(inputString)) {
                        valid = true;
                    }
                }
                if (valid) break;
            }

            int destinationRow = getRow() + MOVEMENT_DIRECTIONS.get(inputString)[0];
            int destinationColumn = getCol() + MOVEMENT_DIRECTIONS.get(inputString)[1];
            if (lovMap.makeHeroMove(destinationRow, destinationColumn, this)) {
                break;
            }
        }
    }


    public Monster getNeighborMonster(LovMap grid, LegendsOfValor legendofvalor) {
        Cell[][] grids = grid.getCells();
        if (grids[Math.max(row - 1, 0)][Math.max(col - 1, 0)].isHasMonster()) {
            for (int i = 0; i < legendofvalor.getMonsters().size(); i++) {
                if (legendofvalor.getMonsters().get(i).getRow() == Math.max(row - 1, 0) && legendofvalor.getMonsters().get(i).getCol() == Math.max(col - 1, 0)) {
                    return legendofvalor.getMonsters().get(i);
                }
            }

        } else if (grids[Math.max(row - 1, 0)][col].isHasMonster()) {
            for (int i = 0; i < legendofvalor.getMonsters().size(); i++) {
                if (legendofvalor.getMonsters().get(i).getRow() == Math.max(row - 1, 0) && legendofvalor.getMonsters().get(i).getCol() == col) {
                    return legendofvalor.getMonsters().get(i);
                }
            }
        } else if (grids[Math.max(row - 1, 0)][Math.min(col + 1, 7)].isHasMonster()) {
            for (int i = 0; i < legendofvalor.getMonsters().size(); i++) {
                if (legendofvalor.getMonsters().get(i).getRow() == Math.max(row - 1, 0) && legendofvalor.getMonsters().get(i).getCol() == Math.min(col + 1, 7)) {
                    return legendofvalor.getMonsters().get(i);
                }
            }
        } else if (grids[row][Math.max(col - 1, 0)].isHasMonster()) {
            for (int i = 0; i < legendofvalor.getMonsters().size(); i++) {
                if (legendofvalor.getMonsters().get(i).getRow() == row && legendofvalor.getMonsters().get(i).getCol() == Math.min(col - 1, 0)) {
                    return legendofvalor.getMonsters().get(i);
                }
            }
        } else if (grids[row][col].isHasMonster()) {
            for (int i = 0; i < legendofvalor.getMonsters().size(); i++) {
                if (legendofvalor.getMonsters().get(i).getRow() == row && legendofvalor.getMonsters().get(i).getCol() == col) {
                    return legendofvalor.getMonsters().get(i);
                }
            }
        } else if (grids[row][Math.min(col + 1, 7)].isHasMonster()) {
            for (int i = 0; i < legendofvalor.getMonsters().size(); i++) {
                if (legendofvalor.getMonsters().get(i).getRow() == row && legendofvalor.getMonsters().get(i).getCol() == Math.min(col + 1, 7)) {
                    return legendofvalor.getMonsters().get(i);
                }
            }
        } else if (grids[Math.min(row + 1, 7)][Math.max(col - 1, 0)].isHasMonster()) {
            for (int i = 0; i < legendofvalor.getMonsters().size(); i++) {
                if (legendofvalor.getMonsters().get(i).getRow() == Math.min(row + 1, 7) && legendofvalor.getMonsters().get(i).getCol() == Math.max(col - 1, 0)) {
                    return legendofvalor.getMonsters().get(i);
                }
            }
        } else if (grids[Math.min(row + 1, 7)][col].isHasMonster()) {
            for (int i = 0; i < legendofvalor.getMonsters().size(); i++) {
                if (legendofvalor.getMonsters().get(i).getRow() == Math.min(row + 1, 7) && legendofvalor.getMonsters().get(i).getCol() == col) {
                    return legendofvalor.getMonsters().get(i);
                }
            }
        } else {
            for (int i = 0; i < legendofvalor.getMonsters().size(); i++) {
                if (legendofvalor.getMonsters().get(i).getRow() == Math.min(row + 1, 7) && legendofvalor.getMonsters().get(i).getCol() == Math.min(col + 1, 7)) {
                    return legendofvalor.getMonsters().get(i);
                }
            }
        }
        return null;
    }

    // return boolean indicating whether there is a monster within the hero's attacking range
    public boolean withinRange(LovMap grid) {
        Cell[][] grids = grid.getCells();
        if (grids[Math.max(row - 1, 0)][Math.max(col - 1, 0)].isHasMonster() || grids[Math.max(row - 1, 0)][col].isHasMonster() || grids[Math.max(row - 1, 0)][Math.min(col + 1, 7)].isHasMonster() ||
                grids[row][Math.max(col - 1, 0)].isHasMonster() || grids[row][col].isHasMonster() || grids[row][Math.min(col + 1, 7)].isHasMonster() ||
                grids[Math.min(row + 1, 7)][Math.max(col - 1, 0)].isHasMonster() || grids[Math.min(row + 1, 7)][col].isHasMonster() || grids[Math.min(row + 1, 7)][Math.min(col + 1, 7)].isHasMonster()) {
            return true;
        } else {
            return false;
        }
    }


    public void buy(Item item) {
        inventory.addItem(item);
        money -= item.getPrice();
    }

    public void sell(Item item) {
        inventory.removeItem(item);
        money += item.getPrice();
    }


    public void equip(Item i) {
        if (i instanceof Weapon) {
            for (Weapon w : inventory.getWeapons()) {
                if (i.getMinLevel() < getLevel()) {
                    System.out.println("Hero level doesn't meet the minimum level requirement to use this weapon");
                } else {
                    if (i.getName().equals(w.getName())) {
                        w.equipItem();
                        currentWeapon = w;
                        return;
                    }
                }
            }
        } else if (i instanceof Armor) {
            for (Armor a : inventory.getArmors()) {
                if (i.getMinLevel() < getLevel()) {
                    System.out.println("Hero level doesn't meet the minimum level requirement to use this weapon");
                } else {
                    if (i.getName().equals(a.getName())) {
                        a.equipItem();
                        currentArmor = a;
                        return;
                    }
                }
            }

        } else {
            System.out.println("You shall not equip such item.");
        }
    }


    public void changeWeapon(Weapon orig, Weapon wear) {
        unequip(orig);
        equip(wear);
        setCurrentWeapon(wear);
    }

    public void changeArmor(Armor orig, Armor wear) {
        unequip(orig);
        equip(wear);
        setCurrentArmor(wear);
    }

    public void unequip(Item i) {
        for (Item item : getInventory().getArmors()) {
            if (item.getName().equals(i.getName())) {
                if (item instanceof Weapon) {
                    ((Weapon) item).equipItem();
                    currentWeapon = null;
                } else if (item instanceof Armor) {
                    ((Armor) item).equipItem();
                    currentArmor = null;
                }
            }
        }
    }

    public void use(Potion p) {
        int[] uses = p.calcUse();
        setHP(getHP() + p.getIncrease() * uses[0]);
        setMana(getMana() + p.getIncrease() * uses[1]);
        setStrength(getStrength() + p.getIncrease() * uses[2]);
        setDexterity(getDexterity() + p.getIncrease() * uses[3]);
        setAgility(getAgility() + p.getIncrease() * uses[4]);
    }

    public void attack(Monster m, Spell spell, LovMap grid) {
        int dmg = (int)(strength*0.1);
        int newHP;
        if (spell == null) {
            for (Weapon w : getInventory().getWeapons()) {
                if (w.isArmed()) {
                    dmg = w.calcAttack(strength, m);
                    break;
                }
            }
            System.out.println("Hero" + getName() + " has dealt " + dmg + " damage to " + m.getName());
            if (m.getHP() <= dmg) {
                AudioUtility.playSound(AudioUtility.EPIC_DAMAGE);
                m.setHP(0);
                m.setFaint(true);
                System.out.println("Monster " + m.getName() + " fainted!");
                AudioUtility.playSound(AudioUtility.LEVEL_UP);

                experience += 2;
                money += m.getLevel() * 100;
                grid.getCells()[m.getRow()][m.getCol()].setHasMonster(false);
                m.getLane().setMaxMonsterRow(0);
            } else {
                AudioUtility.playSound(AudioUtility.LIGHT_HIT);
                newHP = m.getHP() - dmg;
                m.setHP(newHP);
            }
        } else {
            mana -= spell.getMana();
            dmg = spell.calcAttack(dexterity, m);
            if (m.getHP() <= dmg) {
                AudioUtility.playSound(AudioUtility.EPIC_DAMAGE);
                m.setHP(0);
                System.out.println("Monster " + m.getName() + " fainted!");
                AudioUtility.playSound(AudioUtility.LEVEL_UP);
                experience += 2;
                money += m.getLevel() * 100;
                m.setFaint(true);
                grid.getCells()[m.getRow()][m.getCol()].setHasMonster(false);
                m.getLane().setMaxMonsterRow(0);
            } else {
                AudioUtility.playSound(AudioUtility.LIGHT_HIT);

                newHP = m.getHP() - dmg;
                m.setHP(newHP);
            }
        }
    }


    public void takeDamage(int dmg, LovMap grid) {
        int actualdmg = dmg;
        for (Armor a : getInventory().getArmors()) {
            if (a.isArmed()) {
                actualdmg -= a.getReduction();
                break;
            }
        }
        System.out.println("Monster" + getName() + " has dealt " + dmg + " damage to " + getName());
        AudioUtility.playSound(AudioUtility.GETTING_HIT);

        if (getHP() <= actualdmg) {
            grid.getCells()[row][col].setHasHero(false);
            System.out.println("Hero " + getName() + " fainted!");
            setCurrLane(initLane);
            setPosition(7, initLane.getLeftCol());
            setHP(getLevel() * 100);
            grid.getCells()[row][col].setHasHero(true);


        } else {
            setHP(getHP() - actualdmg);
        }
    }


    public Inventory getArmedInventory() {
        return armedInventory;
    }

    public void setArmedInventory(Inventory armedInventory) {
        this.armedInventory = armedInventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


    public void getInfo(){
        String name_temp = getName();
        for(int i = name_temp.length();i<20;i++)
            name_temp += " ";
        System.out.println(name_temp+"  "+getMana()+"   "+getStrength()+"     "+getAgility()+"     "+getDexterity()+"       "+money+"  "+experience);
    }
    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }


    public Armor getCurrentArmor() {
        return currentArmor;
    }

    public void setCurrentArmor(Armor currentArmor) {
        this.currentArmor = currentArmor;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
        if(currLane!=null){
            currLane.updateMaxExplored();
        };

    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }


    public void setInitLane(Lane initLane) {
        this.initLane = initLane;
        this.currLane = initLane;
        initLane.getIn(this);
    }

    public Lane getCurrLane() {
        return currLane;

    }

    public void setPosition(int row, int column) {
        setRow(row);
        setCol(column);
    }

    public void setCurrLane(Lane destinationLane) {

        this.currLane.getOut(this);
        destinationLane.getIn(this);
        this.currLane = destinationLane;

    }

    public Lane getInitLane() {
        return initLane;
    }


}
