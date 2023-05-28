package org.newdawn.spaceinvaders.actionlisteners;

import org.newdawn.spaceinvaders.user.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectCharacterActionListener implements ActionListener {
    private Player player;
    private int index;
    public SelectCharacterActionListener(Player player, int index){
        this.player = player;
        this.index = index;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int response = JOptionPane.showConfirmDialog(null, "Would you like to set this?", "Setting", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            player.setCharacter(index);
        }
    }
}
