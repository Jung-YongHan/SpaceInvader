package org.newdawn.spaceinvaders;

import com.google.firebase.auth.FirebaseAuthException;
import org.newdawn.spaceinvaders.database.DB;
import org.newdawn.spaceinvaders.item.AddBulletItem;
import org.newdawn.spaceinvaders.item.HealItem;
import org.newdawn.spaceinvaders.item.Item;
import org.newdawn.spaceinvaders.item.SpeedUpItem;
import org.newdawn.spaceinvaders.item.*;
import org.newdawn.spaceinvaders.user.Inventory;
import org.newdawn.spaceinvaders.user.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Item> items;
    private Inventory inventory;
    private DB db;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public Shop(Player player) throws FirebaseAuthException {
        db = new DB();
        inventory = player.getInventory();
        items = new ArrayList<>();
        // 상점에 아이템 추가
        items.add(new AddBulletItem(inventory));
        items.add(new HealItem(inventory));
        items.add(new SpeedUpItem(inventory));
        items.add(new ShieldItem(inventory));
        items.add(new ReLoadSpeedUpItem(inventory));
    }

    public void sellItem(Item item, Player player) {
        db.getCoin(coin -> {
            log.debug("Coin: {}", coin);
            if (coin >= item.getPrice()) {
                db.updateCoin(-item.getPrice());
                player.addItemToInventory(item.getName());
                log.debug("You have purchased {}.", item.getName());
            } else {
                log.debug("You do not have enough money to purchase {}.", item.getName());
            }
        });
    }
}
