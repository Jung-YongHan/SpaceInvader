package org.newdawn.spaceinvaders.frame;

import org.newdawn.spaceinvaders.user.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MyPageFrame extends JFrame {
    private JLabel titleLabel;

    private JButton backButton;

    private Player player;

    public MyPageFrame(Player player) {
        super("MyPage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame 닫히면 프로그램 종료
        setSize(800, 600);
        setLocationRelativeTo(null); // 창을 화면 중앙에 배치

        // get hold the content of the frame and set up the resolution of the game
        setContentPane(new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                Image backgroundImage = new ImageIcon("src/main/resources/background/mainPageBackground.jpg").getImage();
                g.drawImage(backgroundImage, 0, 0, 800, 600, this);
            }
        });

        setIgnoreRepaint(false);
        getContentPane().setLayout(null);

        // titleLabel 추가
        titleLabel = new JLabel("MyPage");
        titleLabel.setForeground(Color.WHITE); // 기본 글씨 색을 검은색으로 설정합니다.
        titleLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 35));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(300, 100, 200, 55);
        getContentPane().add(titleLabel);

        // 버튼 추가
        backButton = new JButton("Back");
        // 버튼 서식
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false); // 배경
        backButton.setBorderPainted(false); // 배경
        backButton.setForeground(Color.WHITE); // 글자색
        backButton.setFocusPainted(false); // 테두리
        backButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20)); // 폰트
        backButton.setBounds(0, 500, 100, 20); // set position and size

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame(player);
                setVisible(false);
            }
        });
        getContentPane().add(backButton);


        // finally make the window visible
//         pack();
//         setResizable(false);
        setVisible(true);

    }
}