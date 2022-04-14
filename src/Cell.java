/**
 * abstract class for a Cell. Extends to three types of cells.
 */
public abstract class Cell {
    private String icon;
    private int row;
    private int col;

    protected Colors colors;
    protected boolean hasHero;
    protected boolean hasMonster;


    public void land(Hero hero){

    }

    public void leave(Hero hero){

    }

    public Cell(String icon, int row, int col){
        this.icon = icon;
        this.row = row;
        this.col = col;
        hasHero = false;
        hasMonster = false;
    }

    private static String getOuterCellStr(char c){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            str.append(c).append(" - ");
        }
        str.append(c).append("   ");
        return str.toString();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public boolean isHasMonster() {
        return hasMonster;
    }

    public void setHasMonster(boolean hasMonster) {
        this.hasMonster = hasMonster;
    }

    public boolean isHasHero() {
        return hasHero;
    }

    public void setHasHero(boolean hasHero) {
        this.hasHero = hasHero;
    }
}
