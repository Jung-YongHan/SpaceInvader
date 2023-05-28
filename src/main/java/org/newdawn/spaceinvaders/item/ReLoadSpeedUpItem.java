package org.newdawn.spaceinvaders.item;

import org.newdawn.spaceinvaders.character.CharacterStatus;
import org.newdawn.spaceinvaders.user.Inventory;

public class ReLoadSpeedUpItem extends Item{
    public ReLoadSpeedUpItem(Inventory inventory){
        super(inventory);
        this.inventory = inventory;
        name = "ReLoadSpeedUpItem";
        coins = 0;
    }
    @Override
    public void useItem(CharacterStatus characterStatus) {
        if (inventory.getItemCount(this.getName()) > 0) {
            characterStatus.setFiringInterval(characterStatus.getFiringInterval()*(0.75));
            inventory.removeItem(this.getName());
        }
    }
}
