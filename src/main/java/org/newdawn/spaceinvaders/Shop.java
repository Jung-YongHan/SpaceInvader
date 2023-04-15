package org.newdawn.spaceinvaders;

import org.newdawn.spaceinvaders.item.AddBulletItem;
import org.newdawn.spaceinvaders.item.HealItem;
import org.newdawn.spaceinvaders.item.Item;
import org.newdawn.spaceinvaders.item.SpeedUpItem;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Item> items;

    public Shop() {
        items = new ArrayList<>();
        // 상점에 아이템 추가
        items.add(new AddBulletItem());
        items.add(new HealItem());
        items.add(new SpeedUpItem());
    }

    public void sellItem(Item item, Player player) {
        if (player.getCoins() >= item.getPrice()) {
            player.setCoins(player.getCoins() - item.getPrice());
            player.addItemToInventory(item);
            System.out.println("You have purchased " + item.getName() + ".");
        } else {
            System.out.println("You do not have enough money to purchase " + item.getName() + ".");
        }
    }
}
