package org.newdawn.spaceinvaders.frame;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class FrameHelper {

    private FrameHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static void setFrameLayout(JFrame frame, ImageIcon imageIcon) {
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

    public static JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false); // 배경
        backButton.setBorderPainted(false); // 배경
        backButton.setForeground(Color.WHITE); // 글자색
        backButton.setFocusPainted(false); // 테두리
        backButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20)); // 폰트
        backButton.setBounds(0, 500, 100, 20); // set position and size
        return backButton;
    }
}
