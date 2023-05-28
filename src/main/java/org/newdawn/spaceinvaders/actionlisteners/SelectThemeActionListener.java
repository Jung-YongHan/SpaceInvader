package org.newdawn.spaceinvaders.actionlisteners;

import org.newdawn.spaceinvaders.Main;
import org.newdawn.spaceinvaders.frame.MainFrame;
import org.newdawn.spaceinvaders.user.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectThemeActionListener implements ActionListener {
    private Player player;
    private JFrame jFrame;
    private int index;
    public SelectThemeActionListener(Player player, JFrame jFrame, int index){
        this.player = player;
        this.jFrame = jFrame;
        this.index = index;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int response = JOptionPane.showConfirmDialog(null, "Would you like to set this?", "Setting", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            player.setTheme(index);

            new MainFrame(player);
            jFrame.setVisible(false);
        }
    }
}
