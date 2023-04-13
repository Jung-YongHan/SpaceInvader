package org.newdawn.spaceinvaders;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Mypage extends JFrame {

    private JButton BackButton;
    private JButton shopButton;

    public Mypage() {
        super("Main Page");
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

        // 버튼 추가
        BackButton = new JButton("Back");
        // 버튼 서식
        BackButton.setOpaque(false);
        BackButton.setContentAreaFilled(false); // 배경
        BackButton.setBorderPainted(false); // 배경
        BackButton.setForeground(Color.WHITE); // 글자색
        BackButton.setFocusPainted(false); // 테두리
        BackButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20)); // 폰트
        BackButton.setBounds(350, 275, 100, 50);

        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                setVisible(false);
//                Thread thread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        MainFrame mainFrame = new MainFrame();
//                        setVisible(false);
//
//                    }
//                });
//                thread.start();
            }
        });


        shopButton = new JButton("Shop");
        // 버튼 서식
        shopButton.setOpaque(false);
        shopButton.setContentAreaFilled(false); // 배경
        shopButton.setBorderPainted(false); // 배경
        shopButton.setForeground(Color.WHITE); // 글자색
        shopButton.setFocusPainted(false); // 테두리
        shopButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20)); // 폰트
//        shopButton.setBounds(350, 275, 100, 50);

        getContentPane().setLayout(new GridLayout(1, 1));

        getContentPane().add(BackButton, BorderLayout.SOUTH);
        getContentPane().add(shopButton, BorderLayout.SOUTH);

        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 상점으로 넘어가는 로직
            }
        });


        // finally make the window visible
//         pack();
//         setResizable(false);
        setVisible(true);

    }
}
