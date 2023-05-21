package org.newdawn.spaceinvaders.user;

import com.google.firebase.auth.FirebaseAuthException;
import org.newdawn.spaceinvaders.Game;
import org.newdawn.spaceinvaders.dataBase.DB;
import org.newdawn.spaceinvaders.theme.CatTheme;
import org.newdawn.spaceinvaders.theme.DesertTheme;
import org.newdawn.spaceinvaders.theme.SpaceTheme;
import org.newdawn.spaceinvaders.theme.Theme;
import org.newdawn.spaceinvaders.user.Inventory;

public class Player {
    private DB db;
    private int coins;
    private Inventory inventory;
    private Game game;
    private int characterId;
    private Theme theme;
    private Theme configTheme = new SpaceTheme();

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
        setCharacterId(this.characterId);
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

    public void setCharacterId(int characterId) { this.characterId = characterId; }
    public void setTheme(int themeId) {
        switch (themeId) {
            case 1:
                this.configTheme = new CatTheme();
                break;
            case 2:
                this.configTheme = new DesertTheme();
                break;
            default:
                this.configTheme = new SpaceTheme();
                break;
        }
    }
    public Theme getTheme() {
        return this.configTheme;
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}