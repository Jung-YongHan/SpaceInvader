package org.newdawn.spaceinvaders.item;

import org.newdawn.spaceinvaders.entity.ShipEntity;

public class ShieldStatus {
    private boolean shieldActive;
    private long shieldStartTime;
    private long SHIELD_DURATION = 2000;
    private ShipEntity shipEntity;

    public void setShipEntity(ShipEntity shipEntity){
        this.shipEntity = shipEntity;
    }

    public void activateShield() {
        this.shieldActive = true;
        this.shieldStartTime = System.currentTimeMillis();
        shipEntity.setShieldedImage();
    }

    public void updateShieldStatus() {
        if (this.shieldActive && System.currentTimeMillis() - this.shieldStartTime > SHIELD_DURATION) {
            this.shieldActive = false;
            shipEntity.setNormalImage();
        }
    }

    public boolean isShieldActive() {
        return this.shieldActive;
    }
}

