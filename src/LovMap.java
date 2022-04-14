//basic world map
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Legends of Valor Map
 * It consists of 8 x 8 cells;
 */
public class LovMap extends Grid {
    private static final int LOV_MAP_SIZE_OF_CELLS = 8;
    private static Colors colors;
    private LegendsOfValor legendsOfValor;
    private CellType[][] cellTypes = {
            {CellType.NEXUS, CellType.NEXUS, CellType.INACCESSIBLE, CellType.NEXUS, CellType.NEXUS, CellType.INACCESSIBLE, CellType.NEXUS, CellType.NEXUS},
            {generateCellTypeAtRandom(), generateCellTypeAtRandom(), CellType.INACCESSIBLE, generateCellTypeAtRandom(), generateCellTypeAtRandom(), CellType.INACCESSIBLE, generateCellTypeAtRandom(), generateCellTypeAtRandom()},
            {generateCellTypeAtRandom(), generateCellTypeAtRandom(), CellType.INACCESSIBLE, generateCellTypeAtRandom(), generateCellTypeAtRandom(), CellType.INACCESSIBLE, generateCellTypeAtRandom(), generateCellTypeAtRandom()},
            {generateCellTypeAtRandom(), generateCellTypeAtRandom(), CellType.INACCESSIBLE, generateCellTypeAtRandom(), generateCellTypeAtRandom(), CellType.INACCESSIBLE, generateCellTypeAtRandom(), generateCellTypeAtRandom()},
            {generateCellTypeAtRandom(), generateCellTypeAtRandom(), CellType.INACCESSIBLE, generateCellTypeAtRandom(), generateCellTypeAtRandom(), CellType.INACCESSIBLE, generateCellTypeAtRandom(), generateCellTypeAtRandom()},
            {generateCellTypeAtRandom(), generateCellTypeAtRandom(), CellType.INACCESSIBLE, generateCellTypeAtRandom(), generateCellTypeAtRandom(), CellType.INACCESSIBLE, generateCellTypeAtRandom(), generateCellTypeAtRandom()},
            {generateCellTypeAtRandom(), generateCellTypeAtRandom(), CellType.INACCESSIBLE, generateCellTypeAtRandom(), generateCellTypeAtRandom(), CellType.INACCESSIBLE, generateCellTypeAtRandom(), generateCellTypeAtRandom()},
            {CellType.NEXUS, CellType.NEXUS, CellType.INACCESSIBLE, CellType.NEXUS, CellType.NEXUS, CellType.INACCESSIBLE, CellType.NEXUS, CellType.NEXUS},
    };



    public LovMap(LegendsOfValor legendsOfValor) {
        super(LOV_MAP_SIZE_OF_CELLS, LOV_MAP_SIZE_OF_CELLS);
        this.legendsOfValor = legendsOfValor;
        colors = new Colors();
        initialize();

        display();
    }

    /**
     * generate a CellType at random
     *
     * @return a CellType
     */
    private CellType generateCellTypeAtRandom() {
        final double CAVE_PROPORTION = 0.2;
        final double BUSH_PROPORTION = 0.2;
        final double KOULOU_PROPORTION = 0.2;


        double randomValue = Math.random();
        if (randomValue <= CAVE_PROPORTION) {
            return CellType.CAVE;
        } else if (randomValue <= CAVE_PROPORTION + BUSH_PROPORTION) {
            return CellType.BUSH;
        } else if (randomValue <= CAVE_PROPORTION + BUSH_PROPORTION + KOULOU_PROPORTION) {
            return CellType.KOULOU;
        } else {
            return CellType.PLAIN;
        }
    }


    private static String getOuterCellStr(String cellIcon) {


        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            str.append(cellIcon).append(" - ");
        }
        str.append(cellIcon).append("   ");
        return str.toString();
    }

    private void createOuterCell(CellType[][] map, List<StringBuilder> printableMap, int row, int col) {
        int cellRow = row / 3;
        switch (map[cellRow][col]) {
            case NEXUS:
                //cells[cellRow][col] = new NexusCell(cellRow, col);
                printableMap.get(row).append(getOuterCellStr(cells[cellRow][col].getIcon()));
                break;
            case PLAIN:
                //cells[cellRow][col] = new PlainCell(cellRow, col);
                printableMap.get(row).append(getOuterCellStr(cells[cellRow][col].getIcon()));
                break;
            case KOULOU:
                //cells[cellRow][col] = new KoulouCell(cellRow, col);
                printableMap.get(row).append(getOuterCellStr(cells[cellRow][col].getIcon()));
                break;
            case CAVE:
                //cells[cellRow][col] = new CaveCell(cellRow, col);
                printableMap.get(row).append(getOuterCellStr(cells[cellRow][col].getIcon()));
                break;
            case BUSH:
                //cells[cellRow][col] = new BushCell(cellRow, col);
                printableMap.get(row).append(getOuterCellStr(cells[cellRow][col].getIcon()));
                break;
            case INACCESSIBLE:
                //cells[cellRow][col] = new InaccessibleCell(cellRow, col);
                printableMap.get(row).append(getOuterCellStr(cells[cellRow][col].getIcon()));
                break;
        }
    }


    private static String getInnerCellStr(String component) {
        return colors.addColor("cyan", ("| " + component + " |   "));
    }

    /**
     * @param row row of cells
     * @param col
     * @return
     */
    private String getCellComponent(int row, int col) {
        final String HERO_ICON = "H";
        final String MONSTER_ICON = "M";








        StringBuilder result = new StringBuilder();
        boolean hasNonFaintHero = false;
        for (int i = 0; i < legendsOfValor.getHeroes().size(); i++) {
            Hero hero=legendsOfValor.getHeroes().get(i);
            if (row == hero.getRow() && col == hero.getCol()&&!hero.isFaint()) {
                result.append(colors.addColorHOrM("magenta", HERO_ICON + (i + 1), "cyan"));
                hasNonFaintHero = true;
                break;
            }
        }
        if (!hasNonFaintHero) result.append("  ");

        boolean hasNotFaintMonster = false;
        for (int j = 0; j < legendsOfValor.getMonsters().size(); j++) {
            Monster monster=legendsOfValor.getMonsters().get(j);
            if (row == monster.getRow() && col == monster.getCol()&&!monster.isFaint()) {
                result.append(colors.addColorHOrM("red", " " + MONSTER_ICON + (j + 1) + "", "cyan"));
                hasNotFaintMonster = true;
                break;
            }
        }

        if (!hasNotFaintMonster) result.append("   ");

        return result.toString();
    }


    private void createInnerCell(CellType[][] map, List<StringBuilder> printableMap, int row, int col) {
        String component = getCellComponent(row / 3, col);
        if (map[row / 3][col] == CellType.INACCESSIBLE)
            component = "X X X";
        printableMap.get(row).append(getInnerCellStr(component));
    }




    public void initialize(){
        for(int row=0;row<LOV_MAP_SIZE_OF_CELLS;row++){
            for(int column=0;column<LOV_MAP_SIZE_OF_CELLS;column++){
                switch (cellTypes[row][column]) {
                    case NEXUS:
                        cells[row][column] = new NexusCell(row, column);
                        break;
                    case PLAIN:
                        cells[row][column] = new PlainCell(row, column);
                        break;
                    case KOULOU:
                        cells[row][column] = new KoulouCell(row, column);
                        break;
                    case CAVE:
                        cells[row][column] = new CaveCell(row, column);
                        break;
                    case BUSH:
                        cells[row][column] = new BushCell(row, column);
                        break;
                    case INACCESSIBLE:
                        cells[row][column] = new InaccessibleCell(row, column);
                        break;
                }
            }
        }
    }


    /**
     * check and update
     */


    private void displayDebugInformation(){
        /**
         * display hero's row and col
         */
        for(int heroIndex=0;heroIndex<legendsOfValor.getHeroes().size();heroIndex++){
            System.out.println("Hero "+heroIndex+" ( " + legendsOfValor.getHeroes().get(heroIndex).getRow()+" , "+ legendsOfValor.getHeroes().get(heroIndex).getCol()+" )");
        }

        System.out.println("Top Lane: "+" max alive Monster row: "+legendsOfValor.getLane("Top").getMaxMonsterRow()+" highest explored: "+legendsOfValor.getLane("Top").getMaxExplored());
        System.out.println("Mid Lane: "+" max alive Monster row: "+legendsOfValor.getLane("Mid").getMaxMonsterRow()+" highest explored: "+legendsOfValor.getLane("Mid").getMaxExplored());
        System.out.println("Bot Lane: "+" max alive Monster row: "+legendsOfValor.getLane("Bot").getMaxMonsterRow()+" highest explored: "+legendsOfValor.getLane("Bot").getMaxExplored());
    }

    public void checkAndUpdate(){



        /**
         * update the max monster row
         *
         */

        Map<Lane, Integer> maxAliveMonsterRows=new HashMap<>();
        for(int i=0;i<legendsOfValor.getMonsters().size();i++){
            Monster monster=legendsOfValor.getMonsters().get(i);

            if(monster.isFaint())continue;

            if(!maxAliveMonsterRows.containsKey(monster.getLane())){
                maxAliveMonsterRows.put(monster.getLane(),monster.getRow());
            }else {
                if(monster.getRow()>maxAliveMonsterRows.get(monster.getLane())){
                    maxAliveMonsterRows.put(monster.getLane(),monster.getRow());
                }
            }
        }

        legendsOfValor.getLane("Top").setMaxMonsterRow(-1);
        legendsOfValor.getLane("Mid").setMaxMonsterRow(-1);
        legendsOfValor.getLane("Bot").setMaxMonsterRow(-1);

        for (Lane lane:maxAliveMonsterRows.keySet()
             ) {
            lane.setMaxMonsterRow(maxAliveMonsterRows.get(lane));
        }

        /**
         * update the hero max explored
         *
         */

        for (int i = 0; i < legendsOfValor.getHeroes().size(); i++) {
            Hero hero=legendsOfValor.getHeroes().get(i);
            hero.getCurrLane().updateMaxExplored();
        }


        /**
         * self-check hero
         */
        for(int row=0;row<LOV_MAP_SIZE_OF_CELLS;row++){
            for (int col=0;col<LOV_MAP_SIZE_OF_CELLS;col++){
                /**
                 * Check hero
                 */
                int heroCounter=0;
                for (int i = 0; i < legendsOfValor.getHeroes().size(); i++) {
                    Hero hero=legendsOfValor.getHeroes().get(i);
                    if (row == hero.getRow() && col == hero.getCol()) {
                        heroCounter++;
                    }
                }
                if(heroCounter>1) {
                    //System.err.println("THERE MUST BE SOMETHING WRONG! PLEASE CHECK! ERROR_CODE: 1234123");
                    //System.err.println(row+" "+col);
                }
                if(heroCounter>0&&!cells[row][col].isHasHero()){
                    //System.err.println("THERE MUST BE SOMETHING WRONG! PLEASE CHECK! ERROR_CODE: 9089823");
                    //System.err.println(row+" "+col);
                }
                if(heroCounter==0&&cells[row][col].isHasHero()){
                    //System.err.println("THERE MUST BE SOMETHING WRONG! PLEASE CHECK! ERROR_CODE: 8712347");
                    //System.err.println(row+" "+col);
                }
                //check ends.
            }




        }

        /**
         * Check monster
         */

        for(int row=0;row<LOV_MAP_SIZE_OF_CELLS;row++){
            for (int col=0;col<LOV_MAP_SIZE_OF_CELLS;col++) {
                int nonFaintMonsterCounter = 0;
                for (int i = 0; i < legendsOfValor.getMonsters().size(); i++) {
                    Monster monster = legendsOfValor.getMonsters().get(i);
                    if (row == monster.getRow() && col == monster.getCol() && !monster.isFaint()) {
                        nonFaintMonsterCounter++;
                    }
                }
                if (nonFaintMonsterCounter > 1) {
                    //System.err.println("THERE MUST BE SOMETHING WRONG! PLEASE CHECK! ERROR_CODE: 1812444");
                }
                if (nonFaintMonsterCounter > 0 && !cells[row][col].isHasMonster()) {
                    //System.err.println("THERE MUST BE SOMETHING WRONG! PLEASE CHECK! ERROR_CODE: 88734243");
                }
                if (nonFaintMonsterCounter == 0 && cells[row][col].isHasMonster()) {
                    //System.err.println("THERE MUST BE SOMETHING WRONG! PLEASE CHECK! ERROR_CODE: 746124o");
                }
                //check ends.
            }
        }


    }

    /**
     * Display the map, heroes, and monsters.
     *
     * @author Gung
     */
    public void display() {

        displayDebugInformation();

        checkAndUpdate();

        List<StringBuilder> printable = new ArrayList<>();
        for (int row = 0; row < (LOV_MAP_SIZE_OF_CELLS + 1) * 3; row++) {
            printable.add(new StringBuilder());
            if (row >= (LOV_MAP_SIZE_OF_CELLS * 3)){
                continue;
            }
            if ((row / 3) % 2 == 0) {
                for (int col = 0; col < LOV_MAP_SIZE_OF_CELLS; col++) {
                    if (row % 2 == 0) {
                        createOuterCell(cellTypes, printable, row, col);
                    } else {
                        createInnerCell(cellTypes, printable, row, col);
                    }

                    if (col == LOV_MAP_SIZE_OF_CELLS - 1)
                        printable.get(row).append("\n");
                }
            } else {
                for (int col = 0; col < LOV_MAP_SIZE_OF_CELLS; col++) {
                    if (row % 2 == 1) {
                        createOuterCell(cellTypes, printable, row, col);
                    } else {
                        createInnerCell(cellTypes, printable, row, col);
                    }

                    if (col == LOV_MAP_SIZE_OF_CELLS - 1)
                        printable.get(row).append("\n");
                }
            }

            if (row % 3 == 2)
                printable.get(row).append("\n");
        }
        printable.get(24).append(colors.addColor("yellow", "      Top Lane        "));
        printable.get(25).append(colors.addColor("yellow","                     Mid Lane      "));
        printable.get(26).append(colors.addColor("yellow","                      Bot Lane        \n\n"));
        for (int i = 0; i < (LOV_MAP_SIZE_OF_CELLS + 1) * 3; i++) {
            System.out.print(colors.addColor("cyan", printable.get(i).toString()));
        }
    }





    /**
     *
     * @param destinationCellRow destination cell row
     * @param destinationCellColumn destination cell row
     * @param heroToMove heroToMove
     * @return if this move successful.
     */
    public boolean makeHeroMove(int destinationCellRow, int destinationCellColumn, Hero heroToMove){
        checkAndUpdate();

        boolean allowed=true;
        //Check the boundary
        if(destinationCellRow>LOV_MAP_SIZE_OF_CELLS-1||destinationCellColumn>LOV_MAP_SIZE_OF_CELLS-1||destinationCellRow<0||destinationCellColumn<0){
            System.out.println(colors.addColor("red", "You cannot step out of the map"));
            return false;
        }

        //Check if there is a hero in the destination cell;
        for (Hero heroInGame:legendsOfValor.getHeroes()
             ) {
            if (heroInGame.getRow()==destinationCellRow&&heroInGame.getCol()==destinationCellColumn){
                System.out.println(colors.addColor("red", "Sorry, there has already been a hero in the destination cell."));
                return false;
            }
        }



        /**
         * Cannot surpass the alive monster
         */

        if(destinationCellRow<heroToMove.getCurrLane().getMaxMonsterRow()){
            System.out.println("You can't surpass the alive monster!");
            return false;
        }


        Cell destinationCell=cells[destinationCellRow][destinationCellColumn];
        Cell startCell =cells[heroToMove.getRow()][heroToMove.getCol()];

        //if it's inaccessible cell. if it is , then reject.
        String cellIcon = destinationCell.getIcon();
        if (cellIcon.equals("I")){//Inaccessible Cell
            System.out.println(colors.addColor("red", "This space is inaccessible. Please try move another direction!"));
            return false;
        }

        if(cellIcon.equals("N")||cellIcon.equals("B")||cellIcon.equals("C")||cellIcon.equals("K")||cellIcon.equals("P")){
            allowed =true;
        }else{
            //System.err.println("THERE MUST BE SOMETHING WRONG! PLEASE CHECK! ERROR_CODE: 38424");
            return false;
        }

        /**
         *
         * MOVE!
         */
        if(allowed) {
            startCell.leave(heroToMove);
            destinationCell.land(heroToMove);
            heroToMove.setPosition(destinationCellRow,destinationCellColumn) ;
        }

        return true;
    }
}
