package org.newdawn.spaceinvaders.frame;

import com.google.firebase.auth.FirebaseAuthException;
import org.newdawn.spaceinvaders.Game;
import org.newdawn.spaceinvaders.user.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StageFrame extends JFrame{
    private JButton[] levelButton;
    private Player player;
    private final int levels = 5;
    private JButton backButton;

    public StageFrame(Player player) {
        super("Stage Page");
        setFrameLayout();
        loadContent();
        setVisible(true);

        this.player = player;
    }

    private void setFrameLayout() {
        FrameHelper.setFrameLayout(this, new ImageIcon(player.getTheme().getBackgroundImage()));
    }

    private void loadContent() {
        levelButton = new JButton[levels];
        for(int i=0; i<5; i++){
            levelButton[i] = new JButton("Level " + (i+1));
            levelButton[i].setOpaque(false);
            levelButton[i].setContentAreaFilled(false);
            levelButton[i].setBorderPainted(false); // 배경
            levelButton[i].setForeground(Color.WHITE); // 글자색
            levelButton[i].setFocusPainted(false); // 테두리
            levelButton[i].setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 30)); // 폰트
            levelButton[i].setBounds(130*i+50, 250, 150, 60);
            final int level = i+1;
            levelButton[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 해당 스테이지로 넘어가는 액션
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Game game = null;
                            try {
                                game = new Game(new GameFrame(), player);
                            } catch (FirebaseAuthException ex) {
                                throw new RuntimeException(ex);
                            }
                            game.setLevel(level);
                            game.gameLoop();
                        }
                    });
                    thread.start();
                    setVisible(false);
                }
            });
            getContentPane().add(levelButton[i]);
        }

        // Back 버튼
        backButton = FrameHelper.createBackButton();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame(player);
                setVisible(false);
            }
        });
        getContentPane().add(backButton);
    }
}