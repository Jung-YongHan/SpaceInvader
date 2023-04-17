package org.newdawn.spaceinvaders.item;

import org.newdawn.spaceinvaders.Game;
import org.newdawn.spaceinvaders.user.Inventory;

public class ShieldItem extends Item{
    private double activeShield = 10000; // 10sec
    public ShieldItem(Inventory inventory) {
        super(inventory);
        this.inventory = inventory;
        name = "ShieldItem";
        coins = 70;
    }

    @Override
    public void useItem(Game game) {

    }
}
