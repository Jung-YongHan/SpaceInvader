package org.newdawn.spaceinvaders.character;

import org.newdawn.spaceinvaders.item.ShieldStatus;

public class CharacterStatus {
    private double moveSpeed;
    private double firingInterval;
    private int hp;
    private int bulletCount;
    private Character character;
    private ShieldStatus shieldStatus;
    public CharacterStatus(Character character){
        this.character = character;
        this.shieldStatus = new ShieldStatus();
        initializeStatus();
    }

    public void initializeStatus(){
        this.hp = character.getHp();
        this.bulletCount = character.getBulletCount();
        this.firingInterval = character.getFiringInterval();
        this.moveSpeed = character.getMoveSpeed();
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public double getFiringInterval() { return this.firingInterval; }

    public void setFiringInterval(double firingInterval) {
        this.firingInterval = firingInterval;
    }

    public double getMoveSpeed() {
        return this.moveSpeed;
    }

    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public int getBulletCount() { return this.bulletCount; }

    public void setBulletCount(int bulletCount) {
        this.bulletCount = bulletCount;
    }

    public ShieldStatus getShieldStatus() {
        return shieldStatus;
    }
}
