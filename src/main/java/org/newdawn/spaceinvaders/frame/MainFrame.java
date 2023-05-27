package org.newdawn.spaceinvaders.frame;

import com.google.firebase.auth.FirebaseAuthException;
import org.newdawn.spaceinvaders.user.Player;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class MainFrame extends JFrame{
    private JLabel titleLabel;
    private JLabel titleShadow;
    private JButton themeConfig;
    private JButton startButton;
    private JButton myPageButton;
    private JButton shopButton;
    private Player player;
    private JButton characterSelectButton;
    private JButton gameIntroduction;

    public MainFrame(Player player) {
        super("Main Page");
        this.player = player;

        setFrameLayout();
        loadContent();
        setVisible(true);
    }

    private void setFrameLayout() {
        FrameHelper.setFrameLayout(this, new ImageIcon(player.getTheme().getBackgroundImage()));
    }

    private void loadContent() {
        // Title
        titleLabel = new JLabel("SpaceInvaders ");
        titleLabel.setFont(new Font("Broadway", Font.BOLD + Font.ITALIC, 70));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE); // 글자색
        titleLabel.setBounds(0, 100, 800,100);

        titleShadow = new JLabel("SpaceInvaders ");
        titleShadow.setFont(new Font("Broadway", Font.BOLD + Font.ITALIC, 70));
        titleShadow.setHorizontalAlignment(SwingConstants.CENTER);
        titleShadow.setForeground(Color.DARK_GRAY); // 글자색
        titleShadow.setBounds(3, 103, 800,100);

        // Start 버튼
        startButton = new JButton("Start");
        FrameHelper.setButtonFormat(startButton);
        startButton.setBounds(0, 300, 200, 50);
        startButton.addActionListener(e -> {
            new StageFrame(player); // Stage 선택 창
            setVisible(false);
        });

        // MyPage 버튼
        myPageButton = new JButton("MyPage");
        FrameHelper.setButtonFormat(myPageButton);
        myPageButton.setBounds(200, 300, 200, 50);
        myPageButton.addActionListener(e -> {
            try {
                new MyPageFrame(player);
            } catch (FirebaseAuthException ex) {
                throw new RuntimeException(ex);
            }
            setVisible(false);
        });

        // Shop 버튼
        shopButton = new JButton("Shop");
        FrameHelper.setButtonFormat(shopButton);
        shopButton.setBounds(400, 300, 200, 50);
        shopButton.addActionListener(e -> {
            try {
                new ShopFrame(player);
            } catch (FirebaseAuthException ex) {
                throw new RuntimeException(ex);
            }
            setVisible(false);
        });

        // Game Introduction 버튼
        gameIntroduction = new JButton("Introduction");
        FrameHelper.setButtonFormat(gameIntroduction);
        gameIntroduction.setBounds(600, 300, 200, 50);
        gameIntroduction.addActionListener(e -> gameIntroductionDialog());

        // Theme 설정 버튼
        themeConfig = new JButton("Theme");
        FrameHelper.setButtonFormat(themeConfig);
        themeConfig.setBounds(300, 350, 200, 50);
        themeConfig.addActionListener(e -> {
            new ThemeFrame(player);
            setVisible(false);
        });

        // Character 설정 버튼
        characterSelectButton = new JButton("Character");
        FrameHelper.setButtonFormat(characterSelectButton);
        characterSelectButton.setBounds(600, 500, 200, 50);
        characterSelectButton.addActionListener(e -> {
            new CharacterSelectFrame(player);
            setVisible(false);
        });

        // 패널에 버튼 추가
        getContentPane().add(startButton);
        getContentPane().add(myPageButton);
        getContentPane().add(shopButton);
        getContentPane().add(gameIntroduction);
        getContentPane().add(themeConfig);
        getContentPane().add(titleLabel);
        getContentPane().add(titleShadow);
        getContentPane().add(characterSelectButton);
    }

    private void gameIntroductionDialog() {
        String introductionText = """
                1. 코인 획득 방법 : 몬스터 피격시 일정 확률로 코인이 drop 됩니다.
                2. 상점 이용 방법 : 획득한 코인은 DB에 저장됩니다. 저장된 코인으로 아이템을 구매하세요!
                3. Level 시스템 : 총 5가지의 단계로 게임은 구성되어 있습니다.
                4. Login 시스템 : 당신의 고유 아이디로 게임의 정보를 저장하고 플레이하세요!
                5. Mypage 확인 : Mypage에서 당신의 게임 기록 및 코인 개수를 확일할 수 있습니다.
                6. 캐릭터 선택 : 능력치가 다른 3가지 캐릭터 중 한가지를 선택할 수 있습니다.""";
        String highlightedText = "Introduction";

        JTextPane introductionPane = new JTextPane();
        introductionPane.setEditable(false);
        introductionPane.setBackground(new Color(0, 0, 0, 0)); // 배경 투명
        introductionPane.setOpaque(false);
        introductionPane.setForeground(Color.BLACK); // 텍스트 색상

        // 기본 텍스트 스타일 설정
        SimpleAttributeSet defaultAttributes = new SimpleAttributeSet();
        StyleConstants.setFontFamily(defaultAttributes, "Verdana");
        StyleConstants.setFontSize(defaultAttributes, 14);

        // 강조 텍스트 스타일 설정
        SimpleAttributeSet highlightedAttributes = new SimpleAttributeSet();
        StyleConstants.setFontFamily(highlightedAttributes, "Verdana");
        StyleConstants.setFontSize(highlightedAttributes, 20);
        StyleConstants.setBold(highlightedAttributes, true);

        // JTextPane의 Document에 텍스트와 스타일 적용
        Document doc = introductionPane.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), highlightedText + "\n\n", highlightedAttributes);
            doc.insertString(doc.getLength(), introductionText, defaultAttributes);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, introductionPane, "게임 설명", JOptionPane.INFORMATION_MESSAGE);
    }

}
