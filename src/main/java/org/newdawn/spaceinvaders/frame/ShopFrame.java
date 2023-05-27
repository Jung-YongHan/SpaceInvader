package org.newdawn.spaceinvaders.frame;

import com.google.firebase.auth.FirebaseAuthException;
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
    private AddBulletItem addBulletItem;
    private HealItem healItem;
    private SpeedUpItem speedUpItem;
    private ReLoadSpeedUpItem reLoadSpeedUpItem;
    private ShieldItem shieldItem;

    final int itemCount = 5;
    private JButton backButton;
    private JButton[] buyButton;
    private JLabel[] itemIcon;
    private JLabel[] itemName;

    private int iconSize = 128;
    private int[] iconX = {60, 180, 320, 460, 600};
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

        addBulletItem = new AddBulletItem(inventory);
        healItem = new HealItem(inventory);
        speedUpItem = new SpeedUpItem(inventory);
        shieldItem = new ShieldItem(inventory);
        reLoadSpeedUpItem = new ReLoadSpeedUpItem(inventory);

        items.add(addBulletItem);
        items.add(healItem);
        items.add(speedUpItem);
        items.add(shieldItem);
        items.add(reLoadSpeedUpItem);

        setFrameLayout();
        loadContent();
        setVisible(true);
    }

    private void setFrameLayout() {
        FrameHelper.setFrameLayout(this, new ImageIcon(player.getTheme().getBackgroundImage()));
    }

    private void loadContent() {
        // item 아이콘
        itemIcon = new JLabel[itemCount];
        for(int i=0; i<itemCount; i++){
            itemIcon[i] = new JLabel();
            itemIcon[i].setBounds(iconX[i], iconY, iconSize, iconSize);
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
            itemName[i] = new JLabel(iconName[i]);
            itemName[i].setOpaque(false);
            itemName[i].setForeground(Color.BLACK);
            itemName[i].setFont(new Font("Arial", Font.BOLD, 20));
            itemName[i].setBounds(iconNameX[i], iconNameY, 200, 20);
            getContentPane().add(itemName[i]);
        }

        // Buy 버튼
        buyButton = new JButton[itemCount];
        for(int i=0; i<itemCount; i++){
            buyButton[i] = new JButton("Buy");
            buyButton[i].setOpaque(true);
            buyButton[i].setBackground(Color.BLACK);
            buyButton[i].setForeground(Color.WHITE);
            buyButton[i].setFocusPainted(false);
            buyButton[i].setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 25));
            buyButton[i].setBounds(buttonX[i], buttonY, 80, 30);
            final int index = i;
            buyButton[i].addActionListener(e -> {
                Item item = items.get(index);
                shop.sellItem(item, player);
                updatePlayerCoins();
            });
            getContentPane().add(buyButton[i]);
        }


        // Back 버튼
        backButton = FrameHelper.createBackButton();
        backButton.addActionListener(e -> {
            new MainFrame(player);
            setVisible(false);
        });
        getContentPane().add(backButton);

        playerCoins = new JLabel("Coins: " + player.getCoins());
        playerCoins.setOpaque(false);
        playerCoins.setForeground(Color.WHITE);
        playerCoins.setFont(new Font("Arial", Font.BOLD, 20));
        playerCoins.setBounds(620, 500, 200, 20);
        getContentPane().add(playerCoins);
    }

    public void updatePlayerCoins() {
        playerCoins.setText("Coins: " + player.getCoins());
    }
}

