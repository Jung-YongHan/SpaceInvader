package org.newdawn.spaceinvaders.user;

import org.newdawn.spaceinvaders.Game;
import org.newdawn.spaceinvaders.user.Inventory;

public class Player {
    private int coins;
    private Inventory inventory;
    private Game game;
    public Player(){
        coins = 100;
        this.inventory = new Inventory();
    }
    public void setGame(Game game){
        this.game = game;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addItemToInventory(String item) {
        inventory.addItem(item);
    }

    public void removeItemFromInventory(String item) {
        inventory.removeItem(item);
    }

    public Inventory getInventory() {
        return inventory;
    }
}