package org.newdawn.spaceinvaders.frame;

import org.newdawn.spaceinvaders.user.Player;

import javax.swing.*;
import java.awt.*;

public class ThemeFrame extends JFrame {
    private JButton[] skinSelectButton;
    private JButton backButton;
    private Player player;

    public ThemeFrame(Player player) {
        super("Configuration");
        setFrameLayout();
        loadContent();
        setVisible(true);

        this.player = player;
    }

    private void setFrameLayout() {
        FrameHelper.setFrameLayout(this, new ImageIcon(player.getTheme().getBackgroundImage()));
    }

    private void loadContent() {
        skinSelectButton = new JButton[3];
        for (int i = 0; i < skinSelectButton.length; i++) {
            skinSelectButton[i] = createSkinSelectButton(i);
            skinSelectButton[i].setOpaque(true);
            skinSelectButton[i].setBackground(Color.BLACK);
            skinSelectButton[i].setForeground(Color.WHITE);
            skinSelectButton[i].setFocusPainted(false);
            skinSelectButton[i].setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 25));
            skinSelectButton[i].setBounds(100 + i * 220, 350, 150, 30);
            getContentPane().add(skinSelectButton[i]);
        }

        // 버튼 추가
        backButton = FrameHelper.createBackButton();
        backButton.addActionListener(e -> {
            new MainFrame(player);
            setVisible(false);
        });
        getContentPane().add(backButton);
    }

    private JButton createSkinSelectButton(int index) {
        JButton button = new JButton("Select");
        button.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "해당 테마를 설정하겠습니까?", "테마 설정", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                player.setTheme(index);
                repaint();
            }
        });
        return button;
    }

}