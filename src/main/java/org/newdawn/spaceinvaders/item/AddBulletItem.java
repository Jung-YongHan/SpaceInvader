package org.newdawn.spaceinvaders.item;

import org.newdawn.spaceinvaders.Game;
import org.newdawn.spaceinvaders.Skin.CharacterStatus;
import org.newdawn.spaceinvaders.user.Inventory;

public class AddBulletItem extends Item {

    public AddBulletItem(Inventory inventory) {
        super(inventory);
        this.inventory = inventory;
        name = "AddBulletItem";
        coins = 0;
    }

    @Override
    public void useItem(CharacterStatus characterStatus){
        if (inventory.getItemCount(this.getName()) > 0) {
            characterStatus.setBulletCount(characterStatus.getBulletCount()+1);
            inventory.removeItem(this.getName());
        }
    }
}
