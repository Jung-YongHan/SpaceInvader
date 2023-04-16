package org.newdawn.spaceinvaders;

import org.newdawn.spaceinvaders.item.AddBulletItem;
import org.newdawn.spaceinvaders.item.HealItem;
import org.newdawn.spaceinvaders.item.Item;
import org.newdawn.spaceinvaders.item.SpeedUpItem;
import org.newdawn.spaceinvaders.user.Inventory;
import org.newdawn.spaceinvaders.user.Player;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Item> items;
    private Inventory inventory;

    public Shop(Player player) {
        inventory = player.getInventory();
        items = new ArrayList<>();
        // 상점에 아이템 추가
        items.add(new AddBulletItem(inventory));
        items.add(new HealItem(inventory));
        items.add(new SpeedUpItem(inventory));
    }

    public void sellItem(Item item, Player player) {
        if (player.getCoins() >= item.getPrice()) {
            player.setCoins(player.getCoins() - item.getPrice());
            player.addItemToInventory(item.getName());
            System.out.println("You have purchased " + item.getName() + ".");
        } else {
            System.out.println("You do not have enough money to purchase " + item.getName() + ".");
        }
    }
}
