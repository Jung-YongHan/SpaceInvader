package org.newdawn.spaceinvaders.frame;

import org.newdawn.spaceinvaders.entity.ShipEntity;
import org.newdawn.spaceinvaders.user.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterSelectFrame extends JFrame{

    final int buttonCount = 3; // 버튼 개수
    private JButton[] selectButton;
    private JLabel[] characterIcon;
    private int iconSize = 128;
    private int[] iconX = {100, 350, 600};
    private int iconY = 200;
    private int[] buttonX = {140, 390, 640};
    private int buttonY = 350;
    private String[] iconImage = {"ship.png", "ship.png", "ship3.png"};
    private JButton backButton;
    private Player player;
    private ShipEntity playerShip;

    public CharacterSelectFrame(Player player, ShipEntity playerShip) {
        super("Character Select");

        this.player = player;
        this.playerShip = playerShip;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Image backgroundImage = new ImageIcon(player.getSkin().getShipImage()).getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                repaint();
            }
        });

        setIgnoreRepaint(false);
        getContentPane().setLayout(null);

//        // 캐릭터 아이콘
//        characterIcon = new JLabel[buttonCount];
//        for (int i = 0; i < buttonCount; i++) {
//            characterIcon[i] = new JLabel();
//            characterIcon[i].setBounds(iconX[i], iconY, iconSize, iconSize);
//            characterIcon[i].setOpaque(false);
//            try {
//                characterIcon[i].setIcon(new ImageIcon(ImageIO.read(new File("src/main/resources/characterIcon/" + iconImage[i]))));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            getContentPane().add(characterIcon[i]);
//        }

        // select 버튼
        selectButton = new JButton[buttonCount];
        for (int i = 0; i < buttonCount; i++) {
            selectButton[i] = createSkinSelectButton(i);
            selectButton[i].setOpaque(true);
            selectButton[i].setBackground(Color.BLACK);
            selectButton[i].setForeground(Color.WHITE);
            selectButton[i].setFocusPainted(false);
            selectButton[i].setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 25));
            selectButton[i].setBounds(buttonX[i], buttonY, 80, 30);
            getContentPane().add(selectButton[i]);
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
    private JButton createSkinSelectButton(int index) {
        JButton button = new JButton("Select");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "해당 스킨을 설정하겠습니까?", "스킨 설정", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    player.setSkin(index);
                    repaint();
                }
            }
        });
        return button;
    }
}
