/**
 * abstract class for an Item - a weapon, spell,armor, or Potion
 */
public abstract class Item {
    private String name;
    private int price;
    private int minLevel;



    public Item(String name, int price, int minLevel) {
        this.name = name;
        this.price = price;
        this.minLevel = minLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }


}
