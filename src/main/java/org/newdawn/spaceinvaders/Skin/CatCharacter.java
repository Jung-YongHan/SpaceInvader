package org.newdawn.spaceinvaders.Skin;

public class CatCharacter extends Character {
    public CatCharacter(){
        this.hp = 4;
        this.bulletCount = 2;
        this.moveSpeed = 200;
        this.firingInterval = 250;
    }
    @Override
    public String getShipImageFolderPath(){return path + "cat/";}

}