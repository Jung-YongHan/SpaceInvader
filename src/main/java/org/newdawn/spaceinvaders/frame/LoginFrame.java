package org.newdawn.spaceinvaders.frame;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.newdawn.spaceinvaders.user.Player;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.swing.SwingConstants.CENTER;

public class LoginFrame extends JFrame{
    private JLabel idLabel;
    private JLabel nameLabel;
    private JTextField idField;
    private JPasswordField pwField;
    private JLabel message;
    private JButton loginButton;
    private JButton registerButton;
    private String id = null;
    private String pw = null;
    private Player player;
    static String userName;

    public LoginFrame() {
        super("Login");
        setFrameLayout();
        loadContent();
        setVisible(true);
    }

    private void setFrameLayout() {
        FrameHelper.setBasicFrameLayout(this, new ImageIcon("src/main/resources/background/loginBackground.png"));
    }

    private void loadContent() {
        // 아이디 입력 필드
        idLabel = new JLabel("ID");
        idLabel.setHorizontalAlignment(CENTER);
        idLabel.setBounds(280, 240, 60, 30);
        idField = new JTextField(10);
        idField.setBounds(340, 240, 160, 30);
        getContentPane().add(idLabel);
        getContentPane().add(idField);

        // 비밀번호 입력 필드
        nameLabel = new JLabel("Name");
        nameLabel.setHorizontalAlignment(CENTER);
        nameLabel.setBounds(280, 280, 60, 30);
        pwField = new JPasswordField(10);
        pwField.setBounds(340, 280, 160, 30);
        getContentPane().add(nameLabel);
        getContentPane().add(pwField);

        // loninButton 클릭 시 조건에 따른 message
        message = new JLabel("");
        message.setHorizontalAlignment(CENTER);
        message.setBounds(300, 415, 200, 30);
        getContentPane().add(message);

        // 로그인 버튼
        loginButton = new JButton("Login");
        loginButton.setOpaque(false);
        loginButton.setBackground(Color.WHITE); // 배경색
        loginButton.setFont(new Font("Arial", Font.PLAIN, 15)); // 폰트
        loginButton.setBounds(300, 330, 200, 35);
        loginButton.addActionListener(e -> {
            // 로그인 버튼을 클릭한 경우
            if (e.getSource() == loginButton) {
                id = idField.getText();
                pw = new String(pwField.getPassword());

                if (id.isEmpty() || pw.isEmpty()) {
                    message.setText("ID 또는 PW를 입력하세요");
                } else {
                    getDataByEmail();
                }
            }
        });
        getContentPane().add(loginButton);

        // 회원가입 버튼
        registerButton = new JButton("Register");
        registerButton.setOpaque(false);
        registerButton.setBackground(Color.WHITE); // 배경색
        registerButton.setFont(new Font("Arial", Font.PLAIN, 15)); // 폰트
        registerButton.setBounds(300, 370, 200, 35);
        registerButton.addActionListener(e -> {
            new RegisterFrame();
            dispose();
        });
        getContentPane().add(registerButton);
    }

    private void getDataByEmail(){

        UserRecord userRecord = null;
        try {
            userRecord = FirebaseAuth.getInstance().getUserByEmail(idField.getText());
            String email = userRecord.getEmail();
            String uid = userRecord.getUid();
            userName = userRecord.getDisplayName();

            if (userName.equals(String.valueOf(pwField.getPassword()))){
                JOptionPane.showMessageDialog(null, "Hello" + " " + email);
                player = new Player();
                new MainFrame(player);
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
            }

            recoverUserData(uid);
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(RegisterFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void recoverUserData(String uid){
        UserRecord userRecord = null;
        try {
            userRecord = FirebaseAuth.getInstance().getUser(uid);

            Logger.getLogger(LoginFrame.class.getName()).log(Level.INFO, "유저 데이터를 성공적으로 Fetch:");
            Logger.getLogger(LoginFrame.class.getName()).log(Level.INFO, userRecord.getUid());
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
    }

    public static String getUserName() {
        return userName;
    }
}