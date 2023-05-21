package org.newdawn.spaceinvaders.theme;

import org.newdawn.spaceinvaders.user.Player;

public abstract class Theme {
    protected Player player;

    String backgroundImage = "Background.png";
    String alienEntityImage = "alien.png";
    String bossEntityImage = "bossAlien";
    String obstacleImage = "obstacle.png";
    String path = "src\\main\\resources\\background\\";

    public abstract String getImageFolderPath();
    public String getBackgroundImage(){
        return getImageFolderPath() + backgroundImage;
    }
    public String getAlienEntityImage(){
        return getImageFolderPath() + alienEntityImage;
    }
    public String getBossEntityImage(){
        return getImageFolderPath() + bossEntityImage;
    }
    public String getObstacleImage(){
        return getImageFolderPath() + obstacleImage;
    }
}
