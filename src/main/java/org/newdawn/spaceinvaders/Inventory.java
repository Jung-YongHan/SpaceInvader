package org.newdawn.spaceinvaders;

import org.newdawn.spaceinvaders.item.AddBulletItem;
import org.newdawn.spaceinvaders.item.HealItem;
import org.newdawn.spaceinvaders.item.Item;
import org.newdawn.spaceinvaders.item.SpeedUpItem;

import java.util.HashMap;

public class Inventory {
    private HashMap<Item, Integer> items;
    private AddBulletItem addBulletItem = new AddBulletItem();
    private SpeedUpItem speedUpItem = new SpeedUpItem();
    private HealItem healItem = new HealItem();
    public Inventory() {
        items = new HashMap<>();
        setInit();
    }

    public void setInit(){
        items.put(addBulletItem, 0);
        items.put(speedUpItem, 0);
        items.put(healItem, 0);
    }

    public int getItemCount(Item item){
        return items.get(item);
    }

    public void addItem(Item item) {
        items.put(item, getItemCount(item)+1);
    }

    public void removeItem(Item item) {
        items.put(item, getItemCount(item)-1);
    }

    public void useItem(Item item) {
        if (getItemCount(item) != 0) removeItem(item);
    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }
    public void setItems(HashMap<Item, Integer> items) {
        this.items = items;
    }
}
