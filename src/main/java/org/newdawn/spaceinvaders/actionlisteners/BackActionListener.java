package org.newdawn.spaceinvaders.actionlisteners;
import java.awt.event.ActionEvent;

import org.newdawn.spaceinvaders.frame.MainFrame;
import org.newdawn.spaceinvaders.user.Player;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BackActionListener implements ActionListener {
    private Player player;
    private JFrame jFrame;

    public BackActionListener(Player player, JFrame jFrame){
        this.player = player;
        this.jFrame = jFrame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        new MainFrame(player);
        jFrame.setVisible(false);
    }
}
