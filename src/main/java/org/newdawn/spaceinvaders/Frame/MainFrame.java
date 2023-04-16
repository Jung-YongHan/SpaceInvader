package org.newdawn.spaceinvaders.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MainFrame extends JFrame {
    private JButton startButton;
    private JButton myPageButton;
    private JButton shopButton;
    private JButton RankButton;

    public MainFrame() {
        super("Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame 닫히면 프로그램 종료
        setSize(800, 600);
        setResizable(false);
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
        startButton.setBounds(0, 300, 200, 50);

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
        myPageButton.setBounds(200, 300, 200, 50);

        myPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 마이페이지 넘어가는 로직
                MyPageFrame mypage = new MyPageFrame();
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
        shopButton.setBounds(400, 300, 200, 50);

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
                openImageChangePanel();
            }
        });
        openImageChangePanelButton.setBounds(600, 300, 200, 50);


        getContentPane().add(startButton);
        getContentPane().add(myPageButton);
        getContentPane().add(shopButton);
        getContentPane().add(openImageChangePanelButton);



        setVisible(true);

    }
    public void openImageChangePanel() {
        // 이미지 미리보기를 보여줄 패널 생성
        JPanel imagePreviewPanel = new JPanel(new GridLayout(0, 2));

        // 지정된 폴더에서 이미지 파일을 가져오기
        String imageFolderPath = "src/main/resources/sprites/ship"; // 이미지 폴더 경로를 지정해주세요


        File folder = new File(imageFolderPath);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && (file.getName().endsWith(".jpg") || file.getName().endsWith(".png")
                        || file.getName().endsWith(".jpeg") || file.getName().endsWith(".gif"))) {
                    try {
                        BufferedImage img = ImageIO.read(file);
                        ImageIcon icon = new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                        JLabel label = new JLabel(icon);
                        imagePreviewPanel.add(label);

                        JButton button = new JButton("Choose");
                        button.addActionListener(e -> {
                            try {
                                BufferedImage newImage = ImageIO.read(file);
                                ImageIO.write(newImage, "png", new File("ship.png"));
                                JOptionPane.showMessageDialog(this, "Image has been changed successfully!");
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(this, "Error: Could not change the image.");
                                ex.printStackTrace();
                            }
                        });
                        imagePreviewPanel.add(button);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        JOptionPane.showMessageDialog(this, imagePreviewPanel, "Select an image", JOptionPane.PLAIN_MESSAGE);
    }

}
