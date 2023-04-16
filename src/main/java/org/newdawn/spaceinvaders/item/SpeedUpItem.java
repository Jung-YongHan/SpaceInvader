package org.newdawn.spaceinvaders.item;

import org.newdawn.spaceinvaders.Game;
import org.newdawn.spaceinvaders.Inventory;

public class SpeedUpItem extends Item{
    public SpeedUpItem(Inventory inventory) {
        super(inventory);
        coins = 30;
        name = "SpeedUpItem";
    }

    public void useItem(Game game){

    }
}
