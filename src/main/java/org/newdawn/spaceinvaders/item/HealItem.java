package org.newdawn.spaceinvaders.item;

import org.newdawn.spaceinvaders.Game;
import org.newdawn.spaceinvaders.Skin.CharacterStatus;
import org.newdawn.spaceinvaders.user.Inventory;

public class HealItem extends Item{
    public HealItem(Inventory inventory) {
        super(inventory);
        this.inventory = inventory;
        coins = 0;
        name = "HealItem";
    }

    @Override
    public void useItem(CharacterStatus characterStatus){
        if (inventory.getItemCount(this.getName()) > 0) {
            characterStatus.setHp(characterStatus.getHp()+1);
            inventory.removeItem(this.getName());
        }
    }
}
