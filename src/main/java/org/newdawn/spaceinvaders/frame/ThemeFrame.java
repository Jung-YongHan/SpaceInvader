package org.newdawn.spaceinvaders.frame;

import org.newdawn.spaceinvaders.user.Player;

import javax.swing.*;
import java.awt.*;

public class ThemeFrame extends JFrame {
    private JButton[] skinSelectButton;
    private JButton backButton;
    private Player player;
    private final String[] buttonNames = {"Space", "Cafe", "Dessert"};

    public ThemeFrame(Player player) {
        super("Thema");
        this.player = player;

        setFrameLayout();
        loadContent();
        setVisible(true);
    }

    private void setFrameLayout() {
//        FrameHelper.setFrameLayout(this, new ImageIcon(player.getTheme().getBackgroundImage()));
        setDefaultCloseOperation(EXIT_ON_CLOSE); // JFrame 닫히면 프로그램 종료
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null); // 창을 화면 중앙에 배치

        setContentPane(new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                Image backgroundImage = new ImageIcon(player.getTheme().getBackgroundImage()).getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                repaint();
            }
        });

        getContentPane().setLayout(null);
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
        JButton button = new JButton(buttonNames[index]);
        button.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, buttonNames[index] + " 테마를 설정하겠습니까?", "테마 설정", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                player.setTheme(index);
                repaint();
            }
        });
        return button;
    }

}