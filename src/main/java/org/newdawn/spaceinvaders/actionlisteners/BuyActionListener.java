package org.newdawn.spaceinvaders.actionlisteners;

import org.newdawn.spaceinvaders.Shop;
import org.newdawn.spaceinvaders.frame.ShopFrame;
import org.newdawn.spaceinvaders.item.Item;
import org.newdawn.spaceinvaders.user.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyActionListener implements ActionListener {
    private Item item;
    private Player player;
    private Shop shop;
    private ShopFrame shopFrame;

    public BuyActionListener(Item item, Player player, Shop shop, ShopFrame shopFrame) {
        this.item = item;
        this.player = player;
        this.shop = shop;
        this.shopFrame = shopFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        shop.sellItem(item, player);
        shopFrame.updatePlayerCoins();
    }
}
