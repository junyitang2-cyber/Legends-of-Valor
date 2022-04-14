/**
 * Grid for the game.
 */
public abstract class Grid {
    protected Cell[][] cells; //cells
    protected int numRows;
    protected int numCols;
    protected String symbol;


    public Grid(){}
    public Grid(int numCols,int numRows){
        this.numRows = numRows;
        this.numCols = numCols;
        cells = new Cell[numRows][numCols];
//        grid = new Cell[numRows][numCols];
//        grid = new legends.LOVCell[numRows][numCols];
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }



}
