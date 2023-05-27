package org.newdawn.spaceinvaders.item;

import org.newdawn.spaceinvaders.Skin.CharacterStatus;
import org.newdawn.spaceinvaders.user.Inventory;

public class SpeedUpItem extends Item{
    public SpeedUpItem(Inventory inventory) {
        super(inventory);
        this.inventory = inventory;
        name = "SpeedUpItem";
        coins = 0;
    }

    public void useItem(CharacterStatus characterStatus){
        if (inventory.getItemCount(this.getName()) > 0) {
            characterStatus.setMoveSpeed(characterStatus.getMoveSpeed()*1.2);
            inventory.removeItem(this.getName());
        }
    }
}
