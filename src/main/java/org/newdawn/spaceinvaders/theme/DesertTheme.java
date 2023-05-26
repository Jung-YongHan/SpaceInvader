package org.newdawn.spaceinvaders.theme;

public class DesertTheme extends Theme{

    @Override
    public String getBackGroundImageFolderPath() { return contentRootPath + "Desert/"; }

    @Override
    public String getEntityImageFolderPath() {
        return sourceRootPath + "Desert/";
    }
}
