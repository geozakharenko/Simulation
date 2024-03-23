package com.zakharenko.Enities;

import com.zakharenko.Coordinates;
import com.zakharenko.Map;


public class Predator extends Creature {
    private final int power;

    public Predator(Coordinates coordinates, int power) {
        super(coordinates);
        this.power = power;
    }

    @Override
    protected void eat(Coordinates start, Coordinates to, Map map) {
        Creature herbivore = (Creature) map.getEntity(to);
        if (herbivore.getHealth() - power <= 0) super.eat(start, to, map);
        else herbivore.setHealth(herbivore.getHealth() - power);
    }
}
