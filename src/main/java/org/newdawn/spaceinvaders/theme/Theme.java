package org.newdawn.spaceinvaders.theme;

import org.newdawn.spaceinvaders.user.Player;

public abstract class Theme {
    protected Player player;
    protected String backgroundImage = "background.png";
    protected String alienEntityImage = "alien.png";
    protected String obstacleImage = "obstacle.png";
    protected String contentRootPath = "src/main/resources/theme/";
    protected String sourceRootPath = "theme/";

    public abstract String getBackGroundImageFolderPath();
    public abstract String getEntityImageFolderPath();
    public String getBackgroundImage(){ return getBackGroundImageFolderPath() + backgroundImage; }
    public String getAlienEntityImage(){ return getEntityImageFolderPath() + alienEntityImage; }
    public String getObstacleImage(){
        return getEntityImageFolderPath() + obstacleImage;
    }
}
