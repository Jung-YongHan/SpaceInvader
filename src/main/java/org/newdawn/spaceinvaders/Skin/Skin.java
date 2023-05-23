package org.newdawn.spaceinvaders.Skin;

import org.newdawn.spaceinvaders.user.Player;

public abstract class Skin {
    protected Player player;
    protected String shipImage = "ship.png";
    protected String path = "src/main/resources/sprites/ship/";
    protected String catSkinImage = "ship.png";
    protected String shipSkinImage = "ship2.png";
    protected String AstronautSkinImage = "ship3.png";

    public abstract String getShipImageFolderPath();

    public String getShipImage() {return getShipImageFolderPath()+shipImage;}



}
