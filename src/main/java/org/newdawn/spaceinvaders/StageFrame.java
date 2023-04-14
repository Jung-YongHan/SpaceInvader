package org.newdawn.spaceinvaders;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class StageFrame extends JFrame {
    private JButton[] levelButton;
    private final int levels = 5;
    private JButton backButton;
    public StageFrame() {
        super("Stage Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame 닫히면 프로그램 종료
        setSize(800, 600);
        setLocationRelativeTo(null); // 창을 화면 중앙에 배치

        // get hold the content of the frame and set up the resolution of the game
        setContentPane(new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                Image backgroundImage = new ImageIcon("src/main/resources/background/mainPageBackground0.png").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        });

        setIgnoreRepaint(false);

        getContentPane().setLayout(null);

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
            levelButton[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 해당 스테이지로 넘어가는 액션
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Game game = new Game(new GameFrame());
                            game.gameLoop();
                        }
                    });
                    thread.start();
                    setVisible(false);
                }
            });

            getContentPane().add(levelButton[i]);
        }


        backButton = new JButton("Back");
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false); // 배경
        backButton.setBorderPainted(false); // 배경
        backButton.setForeground(Color.WHITE); // 글자색
        backButton.setFocusPainted(false); // 테두리
        backButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20)); // 폰트
        backButton.setBounds(0, 500, 100, 20);
        getContentPane().add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                setVisible(false);
            }
        });

        setVisible(true);
    }
}
