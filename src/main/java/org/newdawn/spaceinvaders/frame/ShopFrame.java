package org.newdawn.spaceinvaders.frame;

import com.google.firebase.auth.FirebaseAuthException;
import org.newdawn.spaceinvaders.actionlisteners.BuyActionListener;
import org.newdawn.spaceinvaders.item.*;
import org.newdawn.spaceinvaders.user.Inventory;
import org.newdawn.spaceinvaders.user.Player;
import org.newdawn.spaceinvaders.Shop;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ShopFrame extends JFrame{

    private JLabel playerCoins;
    private Shop shop;
    private Player player;
    private Inventory inventory;
    final int itemCount = 5;
    private JButton backButton;
    private JButton[] buyButton;
    private JLabel[] itemIcon;
    private JLabel[] itemName;
    private int iconSize = 128;
    private int[] iconNameX = {70, 230, 340, 490, 590};
    private int iconY = 200;
    private int iconNameY = 350;
    private int[] buttonX = {90, 210, 340, 480, 630};
    private int buttonY = 400;
    private ArrayList<Item> items = new ArrayList<>();
    private String[] iconImage = {"bullet.png", "health.png", "speed.png", "shield.png", "reloadspeedup.png"};
    private String[] iconName = {"Add Bullets", "Heal", "Speed Up", "Shield", "ReLoad Speed Up"};

    public ShopFrame(Player player) throws FirebaseAuthException {
        super("Shop");

        this.player = player;
        inventory = player.getInventory();
        shop = new Shop(player);

        initializeItems();

        FrameHelper.setFrameLayout(this, player);
        loadContent();
        setVisible(true);
    }

    private void initializeItems() {
        items.add(new AddBulletItem(inventory));
        items.add(new HealItem(inventory));
        items.add(new SpeedUpItem(inventory));
        items.add(new ShieldItem(inventory));
        items.add(new ReLoadSpeedUpItem(inventory));
    }

    private void loadContent() {
        // item 아이콘
        itemIcon = new JLabel[itemCount];
        for(int i=0; i<itemCount; i++){
            itemIcon[i] = new JLabel();
            itemIcon[i].setBounds(40+140*i, 200, iconSize, iconSize);
            itemIcon[i].setOpaque(false);
            try {
                itemIcon[i].setIcon(new ImageIcon(ImageIO.read(new File("src/main/resources/itemIcon/" + iconImage[i]))));
            } catch (IOException e){
                e.printStackTrace();
            }
            getContentPane().add(itemIcon[i]);
        }

        // item 이름
        itemName = new JLabel[itemCount];
        for(int i=0; i<itemCount; i++){
            itemName[i] = FrameHelper.createLabelButton(iconName[i], iconNameX[i], iconNameY);
            getContentPane().add(itemName[i]);
        }

        // Buy 버튼
        buyButton = new JButton[itemCount];
        for(int i=0; i<itemCount; i++){
            final int index = i;
            buyButton[i] = FrameHelper.createBuyButton("Buy", buttonX[i], buttonY);
            buyButton[i].addActionListener(new BuyActionListener(items.get(index), player, shop, this));
            getContentPane().add(buyButton[i]);
        }

        // Back 버튼
        backButton = FrameHelper.createBackButton(player, this);
        getContentPane().add(backButton);

        // 플레이어 화폐
        playerCoins = FrameHelper.createLabelButton("Coins: " + player.getCoins(), 620, 500);
        getContentPane().add(playerCoins);
    }

    public void updatePlayerCoins() {
        playerCoins.setText("Coins: " + player.getCoins());
    }
}

