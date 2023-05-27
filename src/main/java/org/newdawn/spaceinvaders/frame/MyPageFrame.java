package org.newdawn.spaceinvaders.frame;

import org.newdawn.spaceinvaders.database.DB;
import org.newdawn.spaceinvaders.user.Player;
import com.google.firebase.auth.FirebaseAuthException;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;

public class MyPageFrame extends JFrame{
    private JLabel titleLabel;
    private JButton backButton;
    private DB db;
    private JLabel highScoreLabel;
    private JLabel playCountLabel;
    private JLabel playTimeLabel;
    private JLabel coinLabel;
    private Player player;

    public MyPageFrame(Player player) throws FirebaseAuthException {
        super("MyPage");
        db = new DB();
        this.player = player;

        setFrameLayout();
        loadContent();
        setVisible(true);
    }

    private void setFrameLayout() {
        setDefaultCloseOperation(EXIT_ON_CLOSE); // JFrame 닫히면 프로그램 종료
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null); // 창을 화면 중앙에 배치

        setContentPane(new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                Image backgroundImage = new ImageIcon(player.getTheme().getBackgroundImage()).getImage();
                g.drawImage(backgroundImage, 0, 0, 800, 600, this);
                repaint();
            }
        });

        setIgnoreRepaint(false);
        getContentPane().setLayout(null);
    }

    private void loadContent() {
        // titleLabel 추가
        titleLabel = new JLabel("MyPage");
        titleLabel.setForeground(Color.WHITE); // 기본 글씨 색을 검은색으로 설정합니다.
        titleLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 35));
        titleLabel.setHorizontalAlignment(CENTER);
        titleLabel.setBounds(300, 100, 200, 55);
        getContentPane().add(titleLabel);

        // 버튼 추가
        backButton = FrameHelper.createBackButton();
        backButton.addActionListener(e -> {
            new MainFrame(player);
            setVisible(false);
        });
        getContentPane().add(backButton);

        highScoreLabel = new JLabel();
        db.getHighScore(highScore -> highScoreLabel.setText("최고 점수: " + highScore));
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setBounds(300, 220, 200, 30);

        playCountLabel = new JLabel();
        db.getHighScore(count -> playCountLabel.setText("누적 플레이 수: " + count));
        playCountLabel.setForeground(Color.WHITE);
        playCountLabel.setBounds(300, 270, 200, 30);

        playTimeLabel = new JLabel();
        db.getPlayTime(time -> playTimeLabel.setText("누적 플레이 시간: " + time));
        playTimeLabel.setForeground(Color.WHITE);
        playTimeLabel.setBounds(300, 320, 200, 30);

        coinLabel = new JLabel();
        db.getCoin(coin -> coinLabel.setText("코인: " + coin));
        coinLabel.setForeground(Color.WHITE);
        coinLabel.setBounds(300, 370, 200, 30);

        getContentPane().add(highScoreLabel);
        getContentPane().add(playCountLabel);
        getContentPane().add(playTimeLabel);
        getContentPane().add(coinLabel);

    }
}