package com.zakharenko.Enities;

import com.zakharenko.Coordinates;
import com.zakharenko.FinderAlgorithm.BreadthFirstSearch;
import com.zakharenko.Map;

import java.util.List;

abstract public class Creature extends Entity {
    private final Class<? extends Entity> victim;
    private final int speed;
    private final int maxHealth;
    private int health;

    protected int getHealth() {
        return health;
    }

    protected int getSpeed() {
        return speed;
    }

    protected void setHealth(int health) {
        this.health = health;
    }

    protected void famine() {
        health--;
    }

    protected void eat(Coordinates start, Coordinates to, Map map) {
        map.moveEntity(start, to);
        health += 16;
        if (maxHealth < health) health = maxHealth;
    }

    public Creature(Coordinates coordinates) {
        super(coordinates);
        victim = this.getClass() == Predator.class ? Herbivore.class : Grass.class;
        speed = this.getClass() == Predator.class ? 1 : 2;
        health = this.getClass() == Predator.class ? 48 : 20;
        maxHealth = health;
    }

    public void makeMove(Coordinates start, Map map) {
        if (health > 0) {
            List<Coordinates> path = BreadthFirstSearch.get(start, map, victim);
            if (path.size() > 1) {
                int amountOfSpeed = Math.min(getSpeed(), path.size() - 1);
                Coordinates to = path.get(amountOfSpeed);
                if (to == null || this.getClass().isInstance(map.getEntity(to))) return;
                if (victim.isInstance(map.getEntity(to))) {
                    eat(start, to, map);
                    return;
                }
                map.moveEntity(start, to);
            }
            famine();
        } else map.removeEntity(start);
    }
}
