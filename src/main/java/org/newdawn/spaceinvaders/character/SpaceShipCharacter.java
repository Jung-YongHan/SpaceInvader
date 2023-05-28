package org.newdawn.spaceinvaders.character;

public class SpaceShipCharacter extends Character {
    public SpaceShipCharacter(){
        this.hp = 3;
        this.bulletCount = 1;
        this.moveSpeed = 300;
        this.firingInterval = 200;
    }
    @Override
    public String getShipImageFolderPath(){return path + "space/";}
}
