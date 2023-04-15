package org.newdawn.spaceinvaders.Frame;

import com.google.firebase.auth.FirebaseAuthException;
import org.newdawn.spaceinvaders.Rank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainFrame extends JFrame {
    private JButton startButton;
    private JButton myPageButton;
    private JButton shopButton;
    private JButton RankButton;

    public MainFrame() {
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

        getContentPane().setLayout(null);
        // 버튼 추가
        startButton = new JButton("Start");
        // 버튼 서식
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false); // 배경
        startButton.setBorderPainted(false); // 배경
        startButton.setForeground(Color.WHITE); // 글자색
        startButton.setFocusPainted(false); // 테두리
        startButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20)); // 폰트
        startButton.setBounds(50, 275, 100, 50);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StageFrame stageFrame = new StageFrame();
                setVisible(false);
            }
        });

        myPageButton = new JButton("MyPage");
        // 버튼 서식
        myPageButton.setOpaque(false);
        myPageButton.setContentAreaFilled(false); // 배경
        myPageButton.setBorderPainted(false); // 배경
        myPageButton.setForeground(Color.WHITE); // 글자색
        myPageButton.setFocusPainted(false); // 테두리
        myPageButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20)); // 폰트
        myPageButton.setBounds(180, 265, 120, 60);

        myPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 마이페이지 넘어가는 로직
                MypageFrame mypage = new MypageFrame();
                setVisible(false);
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
        shopButton.setBounds(350, 275, 100, 50);

        getContentPane().setLayout(new GridLayout(1, 1));

        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShopFrame shop = new ShopFrame();
                setVisible(false);
            }
        });
        // Add a JPanel to contain the button
        JPanel buttonPanel = new JPanel();
        getContentPane().add(buttonPanel);

        // Create a button to open the image change panel
        JButton openImageChangePanelButton = new JButton("이미지 변경");
        buttonPanel.add(openImageChangePanelButton);

        // Add action listener to the button
        openImageChangePanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 마이페이지 넘어가는 로직
                try {
                    Rank rank = new Rank();
                } catch (FirebaseAuthException ex) {
                    throw new RuntimeException(ex);
                }
                setVisible(false);
            }
        });
        openImageChangePanelButton.setBounds(500,275,100,50);


        getContentPane().add(startButton);
        getContentPane().add(myPageButton);
        getContentPane().add(shopButton);
        getContentPane().add(openImageChangePanelButton);



        setVisible(true);

    }
    private void openImageChangePanel() {
        // Create a new JFrame to hold the image change panel
        JFrame imageChangeFrame = new JFrame("이미지 변경");
        imageChangeFrame.setSize(500, 500);
        imageChangeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a custom JPanel or use an existing class for the image change panel
        JPanel imageChangePanel = new JPanel();
        // TODO: Add components and functionality for changing ShipEntity and MainFrame images

        imageChangeFrame.add(imageChangePanel);
        imageChangeFrame.setVisible(true);
    }

}
