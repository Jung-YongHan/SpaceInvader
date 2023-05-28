package org.newdawn.spaceinvaders.item;

import org.newdawn.spaceinvaders.character.CharacterStatus;
import org.newdawn.spaceinvaders.user.Inventory;

public class ShieldItem extends Item{
    public ShieldItem(Inventory inventory) {
        super(inventory);
        this.inventory = inventory;
        name = "ShieldItem";
        coins = 0;
    }

    @Override
    public void useItem(CharacterStatus characterStatus) {
        if (inventory.getItemCount(this.getName()) > 0) {
            characterStatus.getShieldStatus().activateShield();
            inventory.removeItem(this.getName());
        }
    }
}
