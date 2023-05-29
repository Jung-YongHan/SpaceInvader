package org.newdawn.spaceinvaders.frame;

import org.newdawn.spaceinvaders.user.Player;

import javax.swing.*;

public class ThemeFrame extends JFrame {
    private JButton[] skinSelectButton;
    private JButton backButton;
    private Player player;
    private final String[] buttonNames = {"Space", "Cat", "Desert"};

    public ThemeFrame(Player player) {
        super("Theme");
        this.player = player;

        FrameHelper.setFrameLayout(this, player);
        loadContent();
        setVisible(true);
    }

    private void loadContent() {
        skinSelectButton = new JButton[3];
        for (int i = 0; i < skinSelectButton.length; i++) {
            skinSelectButton[i] = FrameHelper.createThemeSelectButton(player, this, buttonNames[i], 100+i*220, 350, i);
            getContentPane().add(skinSelectButton[i]);
        }

        // 버튼 추가
        backButton = FrameHelper.createBackButton(player, this);
        getContentPane().add(backButton);
    }

}