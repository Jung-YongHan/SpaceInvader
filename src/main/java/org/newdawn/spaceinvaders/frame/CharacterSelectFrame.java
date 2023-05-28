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

    public CharacterSelectFrame(Player player){
        super("Character Select");
        this.player = player;

        FrameHelper.setFrameLayout(this, player);
        loadContent();
        setVisible(true);
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
            selectButton[i] = FrameHelper.createCharacterSelectButton(player, "Select", buttonX[i], buttonY, i);
            getContentPane().add(selectButton[i]);
            getContentPane().setComponentZOrder(selectButton[i], 1); // 버튼을 라벨 아래에 배치
        }

        // back 버튼
        backButton = FrameHelper.createBackButton(player, this);
        getContentPane().add(backButton);
    }

}
