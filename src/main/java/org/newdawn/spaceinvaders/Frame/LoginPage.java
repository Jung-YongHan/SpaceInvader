package org.newdawn.spaceinvaders.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame{
    private JTextField idField;
    private JPasswordField pwField;
    private JButton loginButton;

    public LoginPage() {
        // Window Setting
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame 닫히면 프로그램 종료
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null); // 창을 화면 중앙에 배치

        // get hold the content of the frame and set up the resolution of the game
        setContentPane(new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                Image backgroundImage = new ImageIcon("src/main/resources/background/loginBackground.png").getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        });
        getContentPane().setLayout(null);

        JLabel titleLabel = new JLabel("Login");
//        titleLabel.setForeground(Color.BLACK); // 글자색
//        titleLabel.setOpaque(true);
//        titleLabel.setBackground(Color.);
        titleLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 35));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBounds(300, 170, 200, 55);
//        getContentPane().add(titleLabel);

        // 아이디 입력 필드
        JLabel idLabel = new JLabel("ID");
        idLabel.setHorizontalAlignment(JLabel.CENTER);
        idLabel.setBounds(280, 240, 60, 30);
        idField = new JTextField(10);
        idField.setBounds(340, 240, 160, 30);
        getContentPane().add(idLabel);
        getContentPane().add(idField);

        // 비밀번호 입력 필드
        JLabel pwLabel = new JLabel("PW");
        pwLabel.setHorizontalAlignment(JLabel.CENTER);
        pwLabel.setBounds(280, 280, 60, 30);
        pwField = new JPasswordField(10);
        pwField.setBounds(340, 280, 160, 30);
        getContentPane().add(pwLabel);
        getContentPane().add(pwField);

        // 로그인 버튼
        loginButton = new JButton("Login");
        // 버튼 서식
        loginButton.setOpaque(false);
//        loginButton.setContentAreaFilled(false); // 배경
        loginButton.setBackground(Color.WHITE); // 배경색
//        loginButton.setBorderPainted(false); // 외곽선
//        loginButton.setForeground(Color.WHITE); // 글자색
//        loginButton.setFocusPainted(false); // 테두리
        loginButton.setFont(new Font("Arial", Font.PLAIN, 15)); // 폰트
        loginButton.setBounds(300, 330, 200, 35);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 로그인 버튼을 클릭한 경우
                if (e.getSource() == loginButton) {
                    String id = idField.getText();
                    String pw = new String(pwField.getPassword());

                    // 아이디와 비밀번호를 확인하는 코드
                    // ...

                    // 로그인 성공한 경우
                    // 다음 페이지로 넘어감
                    // ...
                }
            }
        });
        add(loginButton);

        setVisible(true);
    }



}