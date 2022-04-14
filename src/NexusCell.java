public class NexusCell extends Cell {
    public NexusCell(int row, int col){
        super("N", row, col);
    }


    public void land(Hero hero){
        hasHero=true;
    }

    public void leave(Hero hero){
        hasHero=false;
    }

}
