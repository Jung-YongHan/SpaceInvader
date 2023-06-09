package org.newdawn.spaceinvaders.frame;

import com.google.firebase.auth.FirebaseAuthException;
import org.newdawn.spaceinvaders.Game;
import org.newdawn.spaceinvaders.user.Player;

import javax.swing.*;
import java.awt.*;

public class StageFrame extends JFrame{
    private JButton[] levelButton;
    private Player player;
    private final int levels = 6;
    private JButton backButton;

    public StageFrame(Player player) {
        super("Stage Page");
        this.player = player;

        FrameHelper.setFrameLayout(this, player);
        loadContent();
        setVisible(true);
    }

    private void loadContent() {
        levelButton = new JButton[levels];
        for(int i=0; i<levels; i++){
            levelButton[i] = new JButton("Level " + (i+1));
            levelButton[i].setOpaque(false);
            levelButton[i].setContentAreaFilled(false);
            levelButton[i].setBorderPainted(false); // 배경
            levelButton[i].setForeground(Color.WHITE); // 글자색
            levelButton[i].setFocusPainted(false); // 테두리
            levelButton[i].setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 30)); // 폰트
            levelButton[i].setBounds(120*i+10, 250, 150, 60);
            final int level = i+1;
            levelButton[i].addActionListener(e -> {
                // 해당 스테이지로 넘어가는 액션
                Thread thread = new Thread(() -> {
                    Game game;
                    try {
                        game = new Game(new GameFrame(), player);
                    } catch (FirebaseAuthException ex) {
                        throw new RuntimeException(ex);
                    }
                    game.setLevel(level);
                    game.gameLoop();
                });
                thread.start();
                setVisible(false);
            });
            getContentPane().add(levelButton[i]);
        }

        // Back 버튼
        backButton = FrameHelper.createBackButton(player, this);
        getContentPane().add(backButton);
    }
}