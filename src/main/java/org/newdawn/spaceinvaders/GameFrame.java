package org.newdawn.spaceinvaders;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        // create a frame to contain our game
        super("Space Invaders 102");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame 닫히면 프로그램 종료
        setSize(800, 600);
        setLocationRelativeTo(null); // 창을 화면 중앙에 배치

        // get hold the content of the frame and set up the resolution of the game
        setContentPane(new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                Image backgroundImage = new ImageIcon("src/main/resources/background/mainPageBackground.jpg").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        });

        setIgnoreRepaint(false);

//        // get hold the content of the frame and set up the resolution of the game
//        JPanel panel = (JPanel) getContentPane();
//        panel.setPreferredSize(new Dimension(800,600));
//        panel.setLayout(null);

//        // setup our canvas size and put it into the content of the frame
//        setBounds(0,0,800,600);
//        getContentPane().add(this);

        // Tell AWT not to bother repainting our canvas since we're
        // going to do that our self in accelerated mode
        setIgnoreRepaint(true);

        // finally make the window visible
//        pack();
//        setResizable(false);
        setVisible(true);

    }

}
