
import java.util.Scanner;


public class Round {
    Colors colors = new Colors();

    public Round() {

    }


    /**
     * play one round for one hero
     *
     * @param hero hero in the round
     * @param monster monster in the round
     * @param grid
     * @param game
     * @return play indicates whether game should continue
     *  should return false if 1) game is over 2) player wants to quit game
     */
    public boolean playRound(Hero hero, Monster monster, LovMap grid, LegendsOfValor game, int index) {
        boolean play = true;

        grid.display();
        AudioUtility.playSound(AudioUtility.NOTIFICATION);

        Printer printer = new Printer();
        //print hero stats at the beginning of each round
        System.out.println(colors.addColor("purple","See stats below for H"+(index+1)+" ----"));
        printer.printHero(hero);
        //hero regains HP and mana at the start of each round
        hero.setHP((int) 1.1 * hero.getHP());
        hero.setMana((int) 1.1 * hero.getMana());

        System.out.println( colors.addColor("purple", "Please choose an action for H" + (index + 1) + " ----"));
        play = hero.takeAction(grid, game);

        if (!play) {
            return false;
        }

//        Scanner scanner=new Scanner(System.in);
//        System.out.println("Press any button to continue ---");
//        scanner.nextLine();
//

        hero.levelUp();
        if(!monster.isFaint()){
        monster.makeMove(hero, grid, index);
        }
        Scanner scanner=new Scanner(System.in);
        System.out.println("Press any button to continue ---");
        scanner.nextLine();


        if (isOver(hero, monster)) {
            winOrLose(hero, monster);
            return false;
        }
        //////////////////////
//        for(int a=0;a<8; a++){
//            for(int j=0; j<8; j++){
//                if (j == 7) {
//                    System.out.printf(grid.getCells()[a][j].isHasMonster()+" \n");
//
//                }else{
//                    System.out.printf(grid.getCells()[a][j].isHasMonster()+" ");}
//            }
//        }
        //////////////////////

        return true;
    }


    /**
     * check if a game is over
     *
     * @return
     */
    public boolean isOver(Hero hero, Monster monster) {
        if (hero.getRow() == 0 || monster.getRow() == 7) {
            return true;
        }
        return false;
    }


    /**
     * if hero wins, give the rewards; if lose, deduct the
     *
     * @param
     */
    public void winOrLose(Hero hero, Monster monster) {
        if (hero.getRow() == 0) {
            System.out.println("A hero landed on monster's Nexus. You won the game!");
            AudioUtility.playSound(AudioUtility.WIN);
            Scanner scanner=new Scanner(System.in);
            System.out.println("Press any button to end game ---");
            scanner.nextLine();
            return;
        } else if (monster.getRow() == 7) {
            AudioUtility.playSound(AudioUtility.LOSE);

            System.out.println("A monster landed on your Nexus. You lost!");
            AudioUtility.playSound(AudioUtility.WIN);
            Scanner scanner=new Scanner(System.in);
            System.out.println("Press any button to end game ---");
            scanner.nextLine();
            return;
        }
    }

}
