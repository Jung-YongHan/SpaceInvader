package org.newdawn.spaceinvaders.frame;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.SwingConstants.CENTER;

public class RegisterFrame extends JFrame {
    private JLabel titleLabel;
    private JLabel emailLabel;
    private JLabel userNameLabel;
    private JTextField emailField;
    private JPasswordField userNameField;
    private JButton addAccountButton;
    private JButton backButton;
    public RegisterFrame() {
        super("RegisterPage");
        setFrameLayout();
        loadContent();
        setVisible(true);
    }

    private void setFrameLayout() {
        FrameHelper.setBasicFrameLayout(this, new ImageIcon("src/main/resources/background/white.png"));
    }

    private void loadContent() {
        // Title Label
        titleLabel = new JLabel("Register");
        titleLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 35));
        titleLabel.setHorizontalAlignment(CENTER);
        titleLabel.setBounds(300, 170, 200, 55);
        getContentPane().add(titleLabel);

        // 이메일 입력 필드
        emailLabel = new JLabel("Email");
        emailLabel.setHorizontalAlignment(CENTER);
        emailLabel.setBounds(280, 240, 60, 30);
        getContentPane().add(emailLabel);
        emailField = new JTextField(10);
        emailField.setBounds(340, 240, 160, 30);
        getContentPane().add(emailField);

        // 비밀번호 입력 필드
        userNameLabel = new JLabel("Name");
        userNameLabel.setHorizontalAlignment(CENTER);
        userNameLabel.setBounds(280, 280, 60, 30);
        getContentPane().add(userNameLabel);
        userNameField = new JPasswordField(10);
        userNameField.setBounds(340, 280, 160, 30);
        getContentPane().add(userNameField);

        // 계정 생성 버튼
        addAccountButton = new JButton("Create Account");
        addAccountButton.setOpaque(false);
        addAccountButton.setBackground(Color.WHITE); // 배경색
        addAccountButton.setFont(new Font("Arial", Font.PLAIN, 15)); // 폰트
        addAccountButton.setBounds(300, 330, 200, 35);
        addAccountButton.addActionListener(e -> {
            try {
                UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                        .setEmail(emailField.getText())
                        .setEmailVerified(false)
                        .setDisplayName(String.valueOf(userNameField.getPassword()));

                FirebaseAuth.getInstance().createUser(request);
                Logger.getLogger(RegisterFrame.class.getName()).log(Level.INFO, "SUCCESS");
                JOptionPane.showMessageDialog(null, "SUCCESS");
            }
            catch (FirebaseAuthException ex){
                Logger.getLogger(RegisterFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            new LoginFrame();
            dispose();
        });
        getContentPane().add(addAccountButton);

        // Back 버튼
        backButton = new JButton("Back");
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false); // 배경
        backButton.setBorderPainted(false); // 배경
        backButton.setFocusPainted(false); // 테두리
        backButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20)); // 폰트
        backButton.setBounds(0, 500, 100, 20); // set position and size
        backButton.addActionListener(e -> {
            new LoginFrame();
            setVisible(false);
        });
        getContentPane().add(backButton);
    }
}
