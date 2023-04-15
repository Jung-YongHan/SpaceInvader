package org.newdawn.spaceinvaders;

import org.newdawn.spaceinvaders.item.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private int coins;
    private HashMap<Item, Integer> items;
    private Inventory inventory;

    public Player() {
        coins = 0;
        items = new HashMap<>();
        inventory = new Inventory();
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addItemToInventory(Item item) {
        inventory.addItem(item);
    }

    public void removeItemFromInventory(Item item) {
        inventory.removeItem(item);
    }

    public HashMap<Item, Integer> getInventory() {
        return inventory.getItems();
    }
}