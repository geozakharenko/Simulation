package com.zakharenko.Enities;

import com.zakharenko.Coordinates;
import com.zakharenko.Map;

abstract public class Creature extends Entity {
    private final int speed;
    private int health;

    public int getHealth() {
        return this.health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public void famine() {
        this.health--;
    }
    public void ate(Coordinates start, Coordinates to, Map map) {
        map.moveEntity(start, to);
        this.health+=15;
    }
    public Creature(Coordinates coordinates, int speed, int health) {
        super(coordinates);
        this.speed = speed;
        this.health = health;
    }
    //Create normal realization
    public abstract void makeMove(Coordinates start, Map map);
}
