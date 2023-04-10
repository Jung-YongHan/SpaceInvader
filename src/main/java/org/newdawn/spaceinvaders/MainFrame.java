package org.newdawn.spaceinvaders;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainFrame extends JFrame {

    private JButton startButton;

    public MainFrame() {
        super("Main Page");
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

        startButton = new JButton("Start");
        // 버튼 서식
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false); // 배경
        startButton.setBorderPainted(false); // 배경
        startButton.setForeground(Color.WHITE); // 글자색
        startButton.setFocusPainted(false); // 테두리
        startButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20)); // 폰트
        startButton.setBounds(350, 275, 100, 50);

        getContentPane().setLayout(new GridLayout(1, 1));
        getContentPane().add(startButton, BorderLayout.SOUTH);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Game game = new Game(new GameFrame());
                        setVisible(false);
                        game.gameLoop();
                    }
                });
                thread.start();
            }
        });





        // finally make the window visible
//         pack();
//         setResizable(false);
        setVisible(true);

    }
}
