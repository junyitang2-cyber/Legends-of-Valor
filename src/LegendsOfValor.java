//implement basic game logic
import java.util.ArrayList;


public class LegendsOfValor extends RPGGame {
    private ArrayList<Hero> heroes;
    private ArrayList<Monster> monsters;
    private boolean play;
    private Lane topLane;
    private Lane midLane;
    private Lane botLane;
    private Colors colors;
    private int numRounds;
    private ArrayList<Lane> lanes;

    public LegendsOfValor() {
        heroes = new ArrayList<Hero>();
        monsters = new ArrayList<Monster>();
        this.play = true;
        colors = new Colors();
        topLane = new Lane("Top");
        midLane = new Lane("Mid");
        botLane = new Lane("Bot");
        topLane.setLeftCol(0);
        midLane.setLeftCol(3);
        botLane.setLeftCol(6);
        numRounds = 0;
        lanes = new ArrayList<Lane>();
        lanes.add(topLane);
        lanes.add(midLane);
        lanes.add(botLane);
    }
    @Override
    public void begin() {
        AudioUtility.playSound(AudioUtility.DRAGON_ROAR,false,3f);
        BeginFrame begin = new BeginFrame(this);
    }

    @Override
    public void over() {
        AudioUtility.playSound(AudioUtility.GAMEOVER,false,3f);
        OverFrame over = new OverFrame(this);
    }
    public void playGame() {

        AudioUtility.playSound(AudioUtility.BGM,true,-5f);
        System.out.println(colors.addColor("purple", "Welcome to the Legends of Valor!!\n"));
        System.out.println(colors.addColor("cyan", "Here is the game map you are going to play:"));
        Factory fac = new Factory();
        LovMap grid = new LovMap(this);
        initHeroes(grid);
        initMonsters(grid);
        Round round = new Round();
        //-----------all good up till here
        while (play) {
            if(numRounds==8){
                System.out.println("New 3 monsters are spawned!");
                initMonsters(grid);
            }
            for (int i = 0; i < heroes.size(); i++) {
                play = round.playRound(heroes.get(i), monsters.get(i), grid, this, i);
                if(!play){
                    break;
                }

            }
            numRounds++;
        }
    }




    public void initMonsters(LovMap grid) {
        MonsterNexus monsterNexus = new MonsterNexus();
        for (int i = 0; i < heroes.size(); i++) {
            Monster monster = (Monster)monsterNexus.spawn(lanes.get(i));
            grid.getCells()[monster.getRow()][monster.getCol()].setHasMonster(true);
            addMonster(monster);
        }
    }

    public void initHeroes(LovMap grid) {
        HeroNexus heroNexus = new HeroNexus();
        for(int i=0; i<lanes.size(); i++){
            System.out.println(colors.addColor("black", "What type of hero is your " +lanes.get(i).getName()+" lane hero?"));
            System.out.println(" 1: Paladin\n 2: Sorcerer\n 3: Warrior");
            Hero hero = (Hero) heroNexus.spawn(lanes.get(i));
            grid.getCells()[hero.getRow()][hero.getCol()].setHasHero(true);
            addHero(hero);
        }
        System.out.println(colors.addColor("black", "Please select your heroes below ----\n"));
        Printer printer = new Printer();
        printer.printHeroes(heroes);
        System.out.println(colors.addColor("blue", "Now start your adventure!"));
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }



    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void addHero(Hero hero) {
        heroes.add(hero);
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }


    public Lane getLane(String name) {
        if (name.equals(topLane.getName())) {
            return topLane;
        } else if (name.equals(midLane.getName())) {
            return midLane;
        } else {
            return botLane;
        }
    }
}
