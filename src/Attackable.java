public interface Attackable {
    /**
     * calculate the amount of damage that is caused by this item
     * @param attribute The attribute of the item that is used to deal damage
     * @param m The targeted monster
     * @return
     */
    public int calcAttack(int attribute, Monster m);
}
