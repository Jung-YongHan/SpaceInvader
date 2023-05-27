package org.newdawn.spaceinvaders.user;

import com.google.firebase.auth.FirebaseAuthException;
import org.newdawn.spaceinvaders.Game;
import org.newdawn.spaceinvaders.Skin.AstronautCharacter;
import org.newdawn.spaceinvaders.Skin.CatCharacter;
import org.newdawn.spaceinvaders.Skin.SpaceShipCharacter;
import org.newdawn.spaceinvaders.Skin.Character;
import org.newdawn.spaceinvaders.dataBase.DB;
import org.newdawn.spaceinvaders.entity.ShipEntity;
import org.newdawn.spaceinvaders.theme.CatTheme;
import org.newdawn.spaceinvaders.theme.DesertTheme;
import org.newdawn.spaceinvaders.theme.SpaceTheme;
import org.newdawn.spaceinvaders.theme.Theme;

public class Player {
    private DB db;
    private int coins;
    private Inventory inventory;
    private Game game;
    private int characterId;
    private Theme configTheme = new SpaceTheme();
    private ShipEntity playerShip;
    private Character configCharacter = new SpaceShipCharacter();

    private int selectedSkinId; // 스킨 ID를 저장하기 위한 필드 추가

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
            case 1 -> this.configTheme = new CatTheme();
            case 2 -> this.configTheme = new DesertTheme();
            default -> this.configTheme = new SpaceTheme();
        }
    }

    public Theme getTheme() {
        return this.configTheme;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void setCharacter(int skinId) {
        switch (skinId) {
            case 1 -> {
                this.configCharacter = new CatCharacter();
                selectedSkinId = 1;
            }
            case 2 -> {
                this.configCharacter = new AstronautCharacter();
                selectedSkinId = 2;
            }
            default -> {
                this.configCharacter = new SpaceShipCharacter();
                selectedSkinId = 0;
            }
        }
    }
    public Character getCharacter() {return this.configCharacter;}

}