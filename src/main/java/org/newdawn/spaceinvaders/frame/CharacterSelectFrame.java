package org.newdawn.spaceinvaders.frame;

import org.newdawn.spaceinvaders.user.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CharacterSelectFrame extends JFrame{

    private final int buttonCount = 3; // 버튼 개수
    private JButton[] selectButton;
    private JButton backButton;
    private JLabel[] characterIcon; // 이미지를 표시할 JLabel 배열
    private int iconSize = 128;
    private int[] iconX = {100, 350, 600};
    private int iconY = 200;
    private int[] buttonX = {140, 390, 640};
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
            characterIcon[i].setBounds(iconX[i], iconY, iconSize, iconSize);
            try {
                ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File(iconImage[i]+"ship.png")));
                Image image = imageIcon.getImage();
                Image scaledImage = image.getScaledInstance(iconSize, iconSize, java.awt.Image.SCALE_SMOOTH);  // 스케일링
                characterIcon[i].setIcon(new ImageIcon(scaledImage));
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
            selectButton[i].setOpaque(false);  // 버튼 투명하게 만들기
            selectButton[i].setContentAreaFilled(false);
            selectButton[i].setBorderPainted(false);
            selectButton[i].setBounds(buttonX[i], buttonY, 80, 30);
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
            int response = JOptionPane.showConfirmDialog(null, "해당 스킨을 설정하겠습니까?", "스킨 설정", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                player.setCharacter(index);
                repaint();
            }
        });
        return button;
    }
}
