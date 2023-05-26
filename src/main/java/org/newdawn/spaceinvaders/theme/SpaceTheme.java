package org.newdawn.spaceinvaders.theme;

public class SpaceTheme extends Theme{

    @Override
    public String getBackGroundImageFolderPath() { return contentRootPath + "Space/"; }

    @Override
    public String getEntityImageFolderPath() {
        return sourceRootPath + "Space/";
    }
}
