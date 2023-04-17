package org.newdawn.spaceinvaders.item;

import org.newdawn.spaceinvaders.Game;
import org.newdawn.spaceinvaders.user.Inventory;

public class HealItem extends Item{
    public HealItem(Inventory inventory) {
        super(inventory);
        coins = 5;
        name = "HealItem";
    }

    @Override
    public void useItem(Game game) {

    }
}
