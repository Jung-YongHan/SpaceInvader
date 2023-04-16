package org.newdawn.spaceinvaders.Frame;

import com.google.firebase.auth.FirebaseAuthException;
import org.newdawn.spaceinvaders.DB;
import org.newdawn.spaceinvaders.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MyPageFrame extends JFrame {
    private JLabel titleLabel;
    private JButton backButton;
    private Player player;
    private DB db;
    private JLabel highScoreLabel;
    private JLabel playCountLabel;
    private JLabel playTimeLabel;
    private int playTime;

    public MyPageFrame(Player player) throws FirebaseAuthException {
        super("MyPage");
        db = new DB();

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

        LoadContent();


        // finally make the window visible
//         pack();
//         setResizable(false);
        setVisible(true);

    }

    public void LoadContent() {

//        highScoreLabel = new JLabel("최고 점수: " + db.getHighScore());
        highScoreLabel = new JLabel();
        db.getHighScore(highScore -> {
            highScoreLabel.setText("최고 점수: " + highScore);
        });
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setBounds(300, 230, 200, 30);

//        playCountLabel = new JLabel("누적 플레이 수: " + db.getPlayCount());
        playCountLabel = new JLabel();
        db.getHighScore(count -> {
            playCountLabel.setText("누적 플레이 수: " + count);
        });
        playCountLabel.setForeground(Color.WHITE);
        playCountLabel.setBounds(300, 280, 200, 30);

//        playTimeLabel = new JLabel("누적 플레이 시간: " + playTime);
        playTimeLabel = new JLabel();
        db.getPlayTime(time -> {
            playTimeLabel.setText("누적 플레이 시간: " + time);
        });
        playTimeLabel.setForeground(Color.WHITE);
        playTimeLabel.setBounds(300, 330, 200, 30);

        getContentPane().add(highScoreLabel);
        getContentPane().add(playCountLabel);
        getContentPane().add(playTimeLabel);

    }
}