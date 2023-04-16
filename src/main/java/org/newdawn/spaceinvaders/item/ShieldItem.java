package org.newdawn.spaceinvaders.item;

import org.newdawn.spaceinvaders.Game;
import org.newdawn.spaceinvaders.user.Inventory;

public class ShieldItem extends Item{
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
