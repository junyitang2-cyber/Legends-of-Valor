//abstract class extends Character representing a monster character
public abstract class Monster extends Character {
    private int defense;
    private int damage;
    private int dodge;
    private int row;
    private int col;
    private Lane lane;
    private Colors colors;
    public Monster(String name, int level, int HP, int defense, int damage, int dodge){
        super(name, level, HP);
        this.defense = defense;
        this.damage =damage;
        this.dodge = dodge;
        lane = null;
        colors = new Colors();
    }

    public void makeMove(Hero hero, LovMap grid, int index){
        if(withinRange(grid)){
            hero.takeDamage((int)damage/30, grid);
        }else{
            grid.getCells()[row][col].setHasMonster(false);
            row++;
            grid.getCells()[row][col].setHasMonster(true);
            lane.setMaxMonsterRow(lane.getMaxMonsterRow()+1);
            System.out.println(colors.addColorHOrM("yellow", "\nMonster's turn ---\nM" +(index+1)+" moved forward.", "purple"));
            AudioUtility.playSound(AudioUtility.DRAGON_ROAR,false,6f);

        }
    }

    // return boolean indicating whether there is a hero within the monster's attacking range
    public boolean withinRange(LovMap grid) {
        Cell[][] grids = grid.getCells();
        if (grids[Math.max(row - 1, 0)][Math.max(col - 1, 0)].isHasHero() || grids[Math.max(row - 1, 0)][col].isHasHero() || grids[Math.max(row - 1, 0)][Math.min(col + 1, 7)].isHasHero() ||
                grids[row][Math.max(col - 1, 0)].isHasHero()|| grids[row][col].isHasHero()|| grids[row][Math.min(col + 1, 7)].isHasHero() ||
                grids[Math.min(row + 1, 7)][Math.max(col - 1, 0)].isHasHero() || grids[Math.min(row + 1, 7)][col].isHasHero()|| grids[Math.min(row + 1, 7)][Math.min(col + 1, 7)].isHasHero()) {
            return true;
        } else {
            return false;
        }
    }

    public Lane getLane() {
        return lane;
    }

    public void setLane(Lane lane) {
        this.lane = lane;
    }

    public int getDefense() { return defense;}

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDodge() {
        return dodge;
    }

    public void setDodge(int dodge) {
        this.dodge = dodge;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
    }

//
//    public Hero getNeighborHero(LovMap grid, LegendsOfValor legendofvalor){
//        Cell[][] grids = grid.getGrid();
//        if (grids[Math.max(row - 1, 0)][Math.max(col-1,0)].isHashero()){
//            for (int i = 0; i < legendofvalor.getHeroes().size(); i++){
//                if (legendofvalor.getHeroes().get(i).getRow() == Math.max(row - 1, 0) && legendofvalor.getHeroes().get(i).getCol() == Math.max(col-1,0)){
//                    return legendofvalor.getHeroes().get(i);
//                }
//            }
//
//        }
//        else if (grids[Math.max(row-1,0)][col].isHashero()){
//            for (int i = 0; i < legendofvalor.getHeroes().size(); i++){
//                if (legendofvalor.getHeroes().get(i).getRow() == Math.max(row-1,0) && legendofvalor.getHeroes().get(i).getCol() == col){
//                    return legendofvalor.getHeroes().get(i);
//                }
//            }
//        }
//        else if (grids[Math.max(row-1,0)][Math.min(col+1,7)].isHashero()){
//            for (int i = 0; i < legendofvalor.getHeroes().size(); i++){
//                if (legendofvalor.getHeroes().get(i).getRow() == Math.max(row-1,0) && legendofvalor.getHeroes().get(i).getCol() == Math.min(col+1,7)){
//                    return legendofvalor.getHeroes().get(i);
//                }
//            }
//        }
//        else if (grids[row][Math.min(col-1,0)].isHashero()){
//            for (int i = 0; i < legendofvalor.getHeroes().size(); i++){
//                if (legendofvalor.getHeroes().get(i).getRow() == row && legendofvalor.getHeroes().get(i).getCol() == Math.min(col-1,0)){
//                    return legendofvalor.getHeroes().get(i);
//                }
//            }
//        }
//        else if (grids[row][col].isHashero()){
//            for (int i = 0; i < legendofvalor.getHeroes().size(); i++){
//                if (legendofvalor.getHeroes().get(i).getRow() == row && legendofvalor.getHeroes().get(i).getCol() == col){
//                    return legendofvalor.getHeroes().get(i);
//                }
//            }
//        }
//        else if (grids[row][Math.min(col+1,7)].isHashero()){
//            for (int i = 0; i < legendofvalor.getHeroes().size(); i++){
//                if (legendofvalor.getHeroes().get(i).getRow() == row && legendofvalor.getHeroes().get(i).getCol() == Math.min(col+1,7)){
//                    return legendofvalor.getHeroes().get(i);
//                }
//            }
//        }
//        else if (grids[Math.min(row+1,7)][Math.max(col-1,0)].isHashero()){
//            for (int i = 0; i < legendofvalor.getHeroes().size(); i++){
//                if (legendofvalor.getHeroes().get(i).getRow() == Math.min(row+1,7) && legendofvalor.getHeroes().get(i).getCol() == Math.max(col-1,0)){
//                    return legendofvalor.getHeroes().get(i);
//                }
//            }
//        }
//        else if (grids[Math.min(row+1,7)][col].isHashero()){
//            for (int i = 0; i < legendofvalor.getHeroes().size(); i++){
//                if (legendofvalor.getHeroes().get(i).getRow() == Math.min(row+1,7) && legendofvalor.getHeroes().get(i).getCol() == col){
//                    return legendofvalor.getHeroes().get(i);
//                }
//            }
//        }
//        else {
//            for (int i = 0; i < legendofvalor.getHeroes().size(); i++){
//                if (legendofvalor.getHeroes().get(i).getRow() == Math.min(row+1,7) && legendofvalor.getHeroes().get(i).getCol() == Math.min(col+1,7)){
//                    return legendofvalor.getHeroes().get(i);
//                }
//            }
//        }
//        return null;
//    }


}
