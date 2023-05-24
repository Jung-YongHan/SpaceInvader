package org.newdawn.spaceinvaders.frame;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{

    public GameFrame() {
        // create a frame to contain our game
        super("Space Invaders 102");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame 닫히면 프로그램 종료
        setSize(800, 600);
        setLocationRelativeTo(null); // 창을 화면 중앙에 배치

        // get hold the content of the frame and set up the resolution of the game
        JPanel panel = (JPanel) getContentPane();
        panel.setPreferredSize(new Dimension(800,600));
        panel.setLayout(null);

        // Tell AWT not to bother repainting our canvas since we're
        // going to do that our self in accelerated mode
        setIgnoreRepaint(true);
        getContentPane().setLayout(null);

        setVisible(true);

    }
}
