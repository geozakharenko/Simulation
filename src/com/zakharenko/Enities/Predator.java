package com.zakharenko.Enities;

import com.zakharenko.Coordinates;
import com.zakharenko.FinderAlgorithm.BreadthFirstSearch;
import com.zakharenko.Map;

import java.util.List;

public class Predator extends Creature {
    private final int power;

    public Predator(Coordinates coordinates, int speed, int health, int power) {
        super(coordinates, speed, health);
        this.power = power;
    }

    public void bite(Coordinates start, Coordinates to, Map map) {
        Creature herbivore = (Creature) map.getEntity(to);
        if (herbivore.getHealth() - power <= 0) ate(start, to, map);
        else herbivore.setHealth(herbivore.getHealth() - power);

    }


    @Override
    public void makeMove(Coordinates start, Map map) {
        List<Coordinates> path = BreadthFirstSearch.get(start, map);
        if (path.size() > 1) {
            int amountOfSpeed = Math.min(getSpeed(), path.size() - 1);
            Coordinates to = path.get(amountOfSpeed);
            if (to == null || map.getEntity(to) instanceof Predator) return;
            if (map.getEntity(to) instanceof Herbivore) {
                bite(start, to, map);
                return;
            }
            map.moveEntity(start, to);
        }
            famine();
            if (getHealth() <= 0) map.removeEntity(start);

    }
}
