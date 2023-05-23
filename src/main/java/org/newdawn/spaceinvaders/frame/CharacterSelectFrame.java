package org.newdawn.spaceinvaders.frame;

import com.google.firebase.auth.FirebaseAuthException;
import org.newdawn.spaceinvaders.entity.ShipEntity;
import org.newdawn.spaceinvaders.user.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CharacterSelectFrame extends JFrame{

    final int buttonCount = 3; //버튼 갯수
    private JButton[] selectButton;
    private JLabel[] characterIcon;
    private int iconSize = 128;
    private int[] iconX = {100, 350, 600};
    private int iconY = 200;
    private int[] buttonX = {140, 390, 640};
    private int buttonY = 350;
    private String[] iconImage = {"ship.png", "ship2.png", "ship3.png"};
    private JButton backButton;
    private Player player;

    private ShipEntity playerShip;

    public CharacterSelectFrame(Player player, ShipEntity playerShip) {
        super("Character Select");

        this.player = player;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        setContentPane(new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                Image backgroundImage = new ImageIcon(player.getTheme().getBackgroundImage()).getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                repaint();
            }
        });

        setIgnoreRepaint(false);
        getContentPane().setLayout(null);

        // character 아이콘
        characterIcon = new JLabel[buttonCount];
        for(int i=0; i<buttonCount; i++){
            characterIcon[i] = new JLabel();
            characterIcon[i].setBounds(iconX[i], iconY, iconSize, iconSize);
            characterIcon[i].setOpaque(false);
            try {
                characterIcon[i].setIcon(new ImageIcon(ImageIO.read(new File("src/main/resources/characterIcon/" + iconImage[i]))));
            } catch (IOException e){
                e.printStackTrace();
            }
            getContentPane().add(characterIcon[i]);
        }


        this.playerShip = playerShip;
        // select 버튼
        selectButton = new JButton[buttonCount];
        for(int i=0; i<buttonCount; i++){
            selectButton[i] = new JButton("Select");
            selectButton[i].setOpaque(true);
            selectButton[i].setBackground(Color.BLACK);
            selectButton[i].setForeground(Color.WHITE);
            selectButton[i].setFocusPainted(false);
            selectButton[i].setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20));
            selectButton[i].setBounds(buttonX[i], buttonY, 80, 30);
            getContentPane().add(selectButton[i]);

            final int index = i;
            selectButton[i].addActionListener(e -> {
                selectCharacter(index);
                playerShip.setShipImage("sprites/ship/" + iconImage[index]); // 캐릭터 이미지 설정
                repaint();
            });
        }

        // back 버튼
        backButton = new JButton("Back");
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false); // 배경
        backButton.setBorderPainted(false); // 배경
        backButton.setForeground(Color.WHITE); // 글자색
        backButton.setFocusPainted(false); // 테두리
        backButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20)); // 폰트
        backButton.setBounds(0, 500, 100, 20); // set position and size
        getContentPane().add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame(player);
                setVisible(false);
            }
        });

        setVisible(true);
    }

    private void selectCharacter(int characterIndex) {
        // 로직 추가 : 선택된 캐릭터에 따라서 게임의 다른 부분에 영향을 줄 수 있도록 코드를 추가하십시오.
        System.out.println("Character " + (characterIndex + 1) + " has been selected.");
    }
}
