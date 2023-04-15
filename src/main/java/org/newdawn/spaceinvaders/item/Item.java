package org.newdawn.spaceinvaders.item;

public abstract class Item {
    private String name;
    private int coins;

    public Item(){}
    public int getPrice() {
        return coins;
    }

    public String getName() {
        return name;
    }
    public abstract void useItem();
}

