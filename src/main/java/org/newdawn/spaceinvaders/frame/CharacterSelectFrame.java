package org.newdawn.spaceinvaders.frame;

import org.newdawn.spaceinvaders.user.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CharacterSelectFrame extends JFrame{

    private final int buttonCount = 3; // button count
    private JButton[] selectButton;
    private JButton backButton;
    private JLabel[] characterIcon; // JLabel array to display images
    private int iconSize = 128;
    private int[] iconX = {100, 370, 590};
    private int iconY = 200;
    private int[] buttonX = {120, 360, 600};
    private int buttonY = 350;
    private String[] iconImage = {"src/main/resources/sprites/ship/space/", "src/main/resources/sprites/ship/cat/", "src/main/resources/sprites/ship/astronaut/"};
    private Player player;

    public CharacterSelectFrame(Player player) {
        super("Character Select");
        this.player = player;

        setFrameLayout();
        loadContent();
        setVisible(true);
    }

    private void setFrameLayout() {
        FrameHelper.setFrameLayout(this, new ImageIcon(player.getTheme().getBackgroundImage()));
    }

    private void loadContent() {
        characterIcon = new JLabel[buttonCount];
        for (int i = 0; i < buttonCount; i++) {
            characterIcon[i] = new JLabel();
            try {
                ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File(iconImage[i]+"ship.png")));
                Image image = imageIcon.getImage();

                // 원래 이미지의 높이와 너비
                int originalWidth = imageIcon.getIconWidth();
                int originalHeight = imageIcon.getIconHeight();

                // 원래 이미지의 비율 계산
                double ratio = (double)originalHeight / originalWidth;

                // 새로운 너비와 높이 계산
                int newWidth;
                int newHeight;

                if (ratio > 1) {
                    newHeight = iconSize;
                    newWidth = (int)(iconSize / ratio);
                } else {
                    newWidth = iconSize;
                    newHeight = (int)(iconSize * ratio);
                }

                Image scaledImage = image.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);  // 스케일링
                characterIcon[i].setIcon(new ImageIcon(scaledImage));

                // 아이콘 경계 조정
                characterIcon[i].setBounds(iconX[i], iconY, newWidth, newHeight);

            } catch (IOException e) {
                e.printStackTrace();
            }
            getContentPane().add(characterIcon[i]);
            getContentPane().setComponentZOrder(characterIcon[i], 0); // 라벨을 버튼 위에 배치
        }

        // select 버튼
        selectButton = new JButton[buttonCount];
        for (int i = 0; i < buttonCount; i++) {
            selectButton[i] = createSkinSelectButton(i);
            selectButton[i].setOpaque(true); // 배경색을 변경할 수 있도록 설정
            selectButton[i].setBackground(Color.BLACK); // 배경색을 검은색으로 설정
            selectButton[i].setForeground(Color.WHITE); // 전경색(글자색)을 흰색으로 설정
            selectButton[i].setFocusPainted(false); // 포커스 그림을 그리지 않도록 설정
            selectButton[i].setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 18)); // 폰트를 Arial, 볼드체 + 이탤릭체, 크기 20으로 설정
            selectButton[i].setBounds(buttonX[i], buttonY, 100, 30); // 버튼의 위치와 크기를 설정
            getContentPane().add(selectButton[i]);
            getContentPane().setComponentZOrder(selectButton[i], 1); // 버튼을 라벨 아래에 배치
        }

        // back 버튼
        backButton = FrameHelper.createBackButton();
        backButton.addActionListener(e -> {
            new MainFrame(player);
            setVisible(false);
        });
        getContentPane().add(backButton);
    }


    private JButton createSkinSelectButton(int index) {
        JButton button = new JButton("Select");
        button.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Would you like to set this skin?", "Skin setting", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                player.setCharacter(index);
                repaint();
            }
        });
        return button;
    }
}
