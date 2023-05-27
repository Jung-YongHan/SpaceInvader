package org.newdawn.spaceinvaders.Skin;

public class AstronautCharacter extends Character {
    public AstronautCharacter(){
        this.hp = 2;
        this.bulletCount = 3;
        this.moveSpeed = 150;
        this.firingInterval = 300;
    }
    @Override
    public String getShipImageFolderPath(){return path + "astronaut/";}
}
