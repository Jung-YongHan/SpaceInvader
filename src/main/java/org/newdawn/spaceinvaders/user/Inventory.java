package org.newdawn.spaceinvaders.user;

import org.newdawn.spaceinvaders.item.AddBulletItem;
import org.newdawn.spaceinvaders.item.HealItem;
import org.newdawn.spaceinvaders.item.SpeedUpItem;

import java.util.HashMap;

public class Inventory {
    private HashMap<String, Integer> items;
    private AddBulletItem addBulletItem;
    private SpeedUpItem speedUpItem;
    private HealItem healItem;
    public Inventory() {
        items = new HashMap<>();
        addBulletItem= new AddBulletItem(this);
        healItem= new HealItem(this);
        speedUpItem= new SpeedUpItem(this);
        setInit();
    }

    public void setInit(){
        items.put(addBulletItem.getName(), 0);
        items.put(speedUpItem.getName(), 0);
        items.put(healItem.getName(), 0);
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

    public HashMap<String, Integer> getItems() {
        return items;
    }
    public void setItemList(HashMap<String, Integer> items) {
        this.items = items;
    }
}
