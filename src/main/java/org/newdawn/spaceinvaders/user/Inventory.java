package org.newdawn.spaceinvaders.user;

import org.newdawn.spaceinvaders.Game;
import org.newdawn.spaceinvaders.Skin.CharacterStatus;
import org.newdawn.spaceinvaders.item.*;

import java.util.HashMap;

public class Inventory {
    private HashMap<String, Integer> items;
    private HashMap<String, Item> nameToItem;
    public Inventory() {
        items = new HashMap<>();
        nameToItem = new HashMap<>();
        initializeItems();
    }

    public void initializeItems(){
        Item[] itemList = { new AddBulletItem(this),
                            new SpeedUpItem(this),
                            new HealItem(this),
                            new ShieldItem(this),
                            new ReLoadSpeedUpItem(this)};
        for (Item item : itemList) {
            items.put(item.getName(), 0);
            nameToItem.put(item.getName(), item);
        }
    }

    public int getItemCount(String item) {
        return items.get(item);
    }

    public void addItem(String item) {
        items.put(item, getItemCount(item)+1);
    }

    public void removeItem(String item) {
        items.put(item, getItemCount(item)-1);
    }
    public void useItem(String itemName, CharacterStatus characterStatus) {
        if (getItemCount(itemName) > 0) {
            Item item = nameToItem.get(itemName);
            if (item != null) {
                item.useItem(characterStatus);
            }
        }
    }
}
