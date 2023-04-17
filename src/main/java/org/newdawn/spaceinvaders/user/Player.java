package org.newdawn.spaceinvaders.user;

import com.google.firebase.auth.FirebaseAuthException;
import org.newdawn.spaceinvaders.Game;
import org.newdawn.spaceinvaders.dataBase.DB;
import org.newdawn.spaceinvaders.user.Inventory;

public class Player {
    private DB db;
    private int coins;
    private Inventory inventory;
    private Game game;
    public Player(){
        try {
            this.db = new DB();
            db.getCoin(coins -> {
                this.setCoins(coins);
            });
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
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