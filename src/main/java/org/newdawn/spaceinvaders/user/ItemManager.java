package org.newdawn.spaceinvaders.user;

import org.newdawn.spaceinvaders.Game;
import org.newdawn.spaceinvaders.Skin.CharacterStatus;

import java.util.HashMap;



public class ItemManager {
    private Inventory inventory;
    private HashMap<Character, String> keyToItem;
    private static final long ITEM_USE_INTERVAL = 2000;
    public ItemManager(Player player){
        this.inventory = player.getInventory();
        this.keyToItem = new HashMap<>();
        initializeKeyItemMap();
    }

    private void initializeKeyItemMap(){
        this.keyToItem.put('q', "AddBulletItem");
        this.keyToItem.put('w', "HealItem");
        this.keyToItem.put('e', "SpeedUpItem");
        this.keyToItem.put('r', "ShieldItem");
        this.keyToItem.put('t', "ReLoadSpeedUpItem");
    }

    public void useItem(char key, CharacterStatus characterStatus) {
        String itemName = keyToItem.get(key);
        if (itemName != null) {
            inventory.useItem(itemName, characterStatus);
        }
    }
    public boolean canUseItem(long lastItemUse) {
        return System.currentTimeMillis() - lastItemUse >= ITEM_USE_INTERVAL;
    }
}

