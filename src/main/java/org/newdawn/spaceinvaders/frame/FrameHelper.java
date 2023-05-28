package org.newdawn.spaceinvaders.frame;

import org.newdawn.spaceinvaders.actionlisteners.BackActionListener;
import org.newdawn.spaceinvaders.actionlisteners.SelectCharacterActionListener;
import org.newdawn.spaceinvaders.actionlisteners.SelectThemeActionListener;
import org.newdawn.spaceinvaders.user.Player;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class FrameHelper {

    private FrameHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static void setFrameLayout(JFrame frame, Player player) {
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE); // JFrame 닫히면 프로그램 종료
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // 창을 화면 중앙에 배치

        frame.setContentPane(new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                Image backgroundImage = new ImageIcon(player.getTheme().getBackgroundImage()).getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), frame);
                repaint();
            }
        });

        frame.getContentPane().setLayout(null);
    }

    public static void setBasicFrameLayout(JFrame frame, ImageIcon imageIcon){
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE); // JFrame 닫히면 프로그램 종료
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // 창을 화면 중앙에 배치

        frame.setContentPane(new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                Image backgroundImage = imageIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                repaint();
            }
        });

        frame.getContentPane().setLayout(null);
    }

    public static void setTextButtonFormat(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false); // 배경
        button.setBorderPainted(false); // 배경
        button.setForeground(Color.WHITE); // 글자색
        button.setFocusPainted(false); // 테두리
        button.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20)); // 폰트
    }

    public static void setLabelFormat(JLabel label){
        label.setOpaque(false);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 20));
    }

    public static void setSelectButtonFormat(JButton button){
        button.setOpaque(true);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20));
    }

    public static JLabel createLabelButton(String text, int x, int y) {
        JLabel label = new JLabel(text);
        setLabelFormat(label);
        label.setBounds(x, y, 200, 20); // set position and size
        return label;
    }

    public static JButton createBackButton(Player player, JFrame jFrame) {
        JButton backButton = new JButton("Back");
        setTextButtonFormat(backButton);
        backButton.setBounds(0, 500, 100, 20); // set position and size
        backButton.addActionListener(new BackActionListener(player, jFrame));
        return backButton;
    }

    public static JButton createCharacterSelectButton(Player player, String text, int x, int y, int index) {
        JButton button = new JButton(text);
        setSelectButtonFormat(button);
        button.setBounds(x, y, 100, 20); // set position and size
        button.addActionListener(new SelectCharacterActionListener(player, index));
        return button;
    }

    public static JButton createThemeSelectButton(Player player, JFrame jFrame, String text, int x, int y, int index) {
        JButton button = new JButton(text);
        setSelectButtonFormat(button);
        button.setBounds(x, y, 100, 20); // set position and size
        button.addActionListener(new SelectThemeActionListener(player, jFrame, index));
        return button;
    }

    public static JButton createBuyButton(String text, int x, int y) {
        JButton button = new JButton(text);
        setSelectButtonFormat(button);
        button.setBounds(x, y, 100, 20); // set position and size
        return button;
    }

}
